package com.springmvc.controller.admin.materiel_gitf_record;

import com.springmvc.entity.MyMateriel;
import com.springmvc.entity.WnkBusinessMaterielGiftRecord;
import com.springmvc.service.impl.MyMaterielService;
import com.springmvc.service.impl.WnkBusinessMaterielGiftRecordService;
import com.springmvc.utils.CookileUtil;
import com.springmvc.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述:物料赠送记录操作
 *
 * @author 杨新杰
 * @date 2019/1/5 10:58
 */

@Controller
@RequestMapping(value = "/admin")
public class AdminMaterielGiftRecordController {

    @Resource
    private WnkBusinessMaterielGiftRecordService wnkBusinessMaterielGiftRecordService;

    @Resource
    private MyMaterielService myMaterielService;

    /**
     * 功能描述: 进入物料赠送记录页面
     *
     * @return org.springframework.web.servlet.ModelAndView
     * @author 杨新杰
     * @date 11:04 2019/1/5
     */
    @RequestMapping(value = "/joinMaterielGiftRecord")
    @ResponseBody
    public ModelAndView joinMaterielGiftRecord(HttpServletRequest request) {
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request, 0) == null) {
            modelAndView = new ModelAndView("admin/login");
        } else {
            modelAndView = new ModelAndView("admin/control/extension_materiel/materiel_gift_record");
        }
        return modelAndView;
    }

    /**
     *
     * 功能描述：查询所有物料赠送记录
     *
     * @param   request         请求
     * @param   adminName       操作人
     * @param   businessName    受赠商家
     * @param   businessMobile  受赠商家账号
     * @param   materielId      物料类型
     * @param   gift_number     赠送数量
     * @param   back_number     撤回数量
     * @param   backStatus      撤回状态
     * @param   gift_time_str   赠送时间
     * @param   page            分页数
     * @param   limit           分页条目数
     * @return: 返回所有管理员状态信息
     * @auther: 刘武祥
     * @date：  2019/2/26 10:48
     */
    @RequestMapping(value = "/selectMaterielGiftRecordAll", method = RequestMethod.POST)
    @ResponseBody
    public Result selectMaterielGiftRecordAll(HttpServletRequest request,
                                              String adminName,
                                              String businessName,
                                              String businessMobile,
                                              Integer materielId,
                                              Integer gift_number,
                                              Integer back_number,
                                              String backStatus,
                                              String gift_time_str,
                                              Integer page ,
                                              Integer limit) {

        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {

                Map<String, Object> map = new HashMap<>(5);
                // 条件查询
                map.put("adminName", ("".equals(adminName) || adminName == null) ? null : "%" + adminName + "%");
                map.put("businessName", ("".equals(businessName) || businessName == null) ? null : "%" + businessName + "%");
                map.put("businessMobile", ("".equals(businessMobile) || businessMobile == null) ? null : "%" + businessMobile + "%");
                map.put("materielId", ("".equals(materielId) || materielId == null) ? null : materielId);
                map.put("gift_number",("".equals(gift_number) || gift_number == null) ? null : "%"+ gift_number + "%");
                map.put("back_number",("".equals(back_number) || back_number == null) ? null : "%"+ back_number +"%");
                map.put("backStatus", ("".equals(backStatus) || backStatus == null) ? null : backStatus);
                map.put("gift_time_str",gift_time_str);
                // 分页
                map.put("limit",limit);
                map.put("total",(page - 1) * limit);

                List<Map<String, Object>> list = this.wnkBusinessMaterielGiftRecordService.selectMaterielGiftRecordAll(map);
                if (list.size() > 0) {
                    result.setCount(list.get(0).get("count"));
                    result.setData(list);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("查询成功");
                } else {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("暂无数据");
                }
            }
        } catch (Exception e) {
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }

    /**
     * 功能描述:  管理员撤回赠送
     *
     * @param request HttpServletRequest
     * @return 返回所有管理员状态信息
     * @author 杨新杰
     */
    @RequestMapping(value = "/adminBackGiftMaterielByGiftId", method = RequestMethod.POST)
    @ResponseBody
    public Result adminBackGiftMaterielByGiftId(HttpServletRequest request, Integer gift_id) {
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request, 0);
            if (userId == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            } else {
                WnkBusinessMaterielGiftRecord wbmgr = this.wnkBusinessMaterielGiftRecordService.selectMaterielGiftRecordById(gift_id);
                // 如果已经是撤销了的就不应该继续往下操作
                if (wbmgr.getBack_status() == 1) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("已经撤销,请勿重复操作");
                    return result;
                }
                // 查看商家对应的剩余物料
                MyMateriel myMateriel = myMaterielService.selectByMaterielIdAndBusinessId(wbmgr.getBusiness_id(), wbmgr.getMateriel_id());
                // 没有对应物料
                if (myMateriel == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("商家无对应物料");
                    return result;
                }
                // 已经全部使用完了
                if (myMateriel.getSurplus_number() <= 0) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("商家对应物料已经全部使用,无法扣除");
                    return result;
                }
                // 开始扣除
                // 1. 创建更新记录
                // 如果商家剩余数量大于需要扣除的数量就扣除全部, 否则扣除商家剩余所有数量
                Integer back_number = myMateriel.getSurplus_number() >= wbmgr.getGift_number() ? wbmgr.getGift_number() : myMateriel.getSurplus_number();
                wbmgr.setBack_status(1);
                wbmgr.setBack_time(new Date());
                wbmgr.setBack_number(back_number);
                // 2 . 减去商家物料
                if (this.myMaterielService.backMaterielNumber(wbmgr.getBusiness_id(), wbmgr.getMateriel_id(), back_number) <= 0) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("扣除商家物料失败,请重试");
                    return result;
                }
                // 3. 更新赠送记录
                if (this.wnkBusinessMaterielGiftRecordService.updateMaterielGiftRecordByBack(wbmgr) <= 0) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("商家物料已扣除,但是更新记录失败");
                    return result;
                }
                // 全部成功后返回成功信息
                result.setData("");
                result.setStatus(Result.SUCCESS);
                result.setMsg("撤销成功");
                // 扣除结束
            }
        } catch (Exception e) {
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }

}
