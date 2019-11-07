package com.springmvc.controller.admin;

import com.springmvc.entity.WithdrawSetting;
import com.springmvc.service.impl.WithdrawSettingService;
import com.springmvc.utils.CookileUtil;
import com.springmvc.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.Timestamp;
import java.util.Date;

@Controller
@RequestMapping(value = "/admin")
public class WithdrawSettingAdminController {

    // 提现业务层
    @Resource
    private WithdrawSettingService withdrawSettingService;

    //进入提现设置界面
    @RequestMapping(value = "/withdrawSetting")
    @ResponseBody
    public ModelAndView withdrawSetting(HttpServletRequest request, String type) {
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request, 0) == null) {
            modelAndView = new ModelAndView("admin/login");
        } else {
            modelAndView = new ModelAndView("admin/control/withdraw_setting/withdraw_setting");
            modelAndView.addObject("type", type); // 商家-0  用户-1
        }
        return modelAndView;
    }



    /**
     *
     * 功能描述:提现设置跟新
     *
     * @param   withdraw_type    更新或修改类型 0-商家 1-用户
     * @param   withdrawSetting  设置实体类
     * @return  com.springmvc.utils.Result
     * @author  杨新杰
     * @date    12:25 2018/12/22
     */
    @RequestMapping(value = "/adminUpdateWithdrawSetting", method = RequestMethod.POST)
    @ResponseBody
    public Result adminUpdateWithdrawSetting(HttpServletRequest request,String withdraw_type,WithdrawSetting withdrawSetting){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                withdrawSetting.setType(Integer.valueOf(withdraw_type));
                // 更新或新增提现设置
               if ( withdrawSettingService.adminInsertOrUpdateWithdrawSetting(withdrawSetting) > 0){
                   result.setData("");
                   result.setStatus(Result.SUCCESS);
                   result.setMsg("保存成功");
               } else {
                   result.setData("");
                   result.setStatus(Result.FAIL);
                   result.setMsg("保存失败");
               }
            }
        }catch (Exception e){
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }



    /**
     *
     * 功能描述: 查询指定设置
     *
     * @param   withdraw_type    更新或修改类型 0-商家 1-用户 2-用户端平台积分
     * @return  com.springmvc.utils.Result
     * @author  杨新杰
     * @date    12:25 2018/12/22
     */
    @RequestMapping(value = "/adminSelectWithdrawSetting", method = RequestMethod.POST)
    @ResponseBody
    public Result adminSelectWithdrawSetting(HttpServletRequest request,String  withdraw_type){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                WithdrawSetting withdrawSetting =  this.withdrawSettingService.adminSelectWithdrawSetting(withdraw_type);
                result.setData(withdrawSetting);
                result.setStatus(Result.SUCCESS);
                result.setMsg("查询成功");
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }



}
