package com.springmvc.controller.wnk_business_app.legalize;

import com.springmvc.entity.WnkBusinessAccount;
import com.springmvc.entity.WnkBusinessLegalize;
import com.springmvc.service.impl.LoginSessionIdsService;
import com.springmvc.service.impl.WnkBusinessAccountService;
import com.springmvc.service.impl.WnkBusinessBalanceDetailService;
import com.springmvc.service.impl.WnkBusinessLegalizeService;
import com.springmvc.utils.LoginSessionCheckUtil;
import com.springmvc.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * 万能卡商家认证 - Controller
 *
 * @author 杨新杰
 * @Date 2018/11/9 10:39
 */
@Controller
@RequestMapping(value = "/wnk_business_app/v1.0.0")
public class WnkBusinessLegalizeController {

    @Resource
    private WnkBusinessLegalizeService wnkBusinessLegalizeService;

    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private WnkBusinessAccountService wnkBusinessAccountService;

    /**
     *
     * 功能描述: 新增商家认证信息. 用于审核
     *
     * @param  business_id         商家ID
     * @param  wnkBusinessLegalize 万能卡商家认证实体类
     * @return
     * @author 杨新杰
     * @date   2018/11/9 10:43
     */
    @RequestMapping(value = "/addBusinessLegalize", method = RequestMethod.POST)
    @ResponseBody
    public Result addBusinessLegalize(HttpServletRequest request, HttpServletResponse response, Integer business_id, WnkBusinessLegalize wnkBusinessLegalize){

        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id,3,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                }
                else {
                    // 参数检查
                    if (wnkBusinessLegalize.getBusiness_id()          == null ||
                        wnkBusinessLegalize.getId_card_facade_img()   == null ||
                        wnkBusinessLegalize.getId_card_rear_img()     == null ||
                        wnkBusinessLegalize.getBusiness_license_img() == null
                    ){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("非法参数");
                    } else {
                        if (wnkBusinessLegalize.getLicense_img().equals("")){
                            wnkBusinessLegalize.setLicense_img(null);
                        }
                        Map<String,Object> map = this.wnkBusinessLegalizeService.selectBusinessLegalizeStatusById(business_id);
                        if (map == null){
                            WnkBusinessLegalize wnkBusinessLegalizeNew = new WnkBusinessLegalize();
                            wnkBusinessLegalizeNew.setBusiness_id(business_id);
                            wnkBusinessLegalizeNew.setLegalize_status(-1);
                            Integer addState = this.wnkBusinessLegalizeService.insertLegalizeInfo(wnkBusinessLegalizeNew);
                            if (addState <= 0){
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("提交失败");

                                return result;
                            }
                        }
                        else{
                            if(wnkBusinessLegalizeService.updateBusinessLegalizeInfoById(wnkBusinessLegalize) > 0){
                                result.setData("");
                                result.setStatus(Result.SUCCESS);
                                result.setMsg("提交审核成功");
                            } else {
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("提交失败");
                            }
                        }

                    }
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }
    /**
     *
     * 功能描述: 查询认证状态
     *
     * @param  business_id         商家ID
     * @return
     * @author 杨新杰
     * @date   2018/11/9 10:43
     */
    @RequestMapping(value = "/selectBusinessLegalize", method = RequestMethod.POST)
    @ResponseBody
    public Result selectBusinessLegalize(HttpServletRequest request, HttpServletResponse response, Integer business_id){

        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id,3,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                }
                else {
                    Map<String,Object> map = this.wnkBusinessLegalizeService.selectBusinessLegalizeStatusById(business_id);
                    if (map == null){
                        map = new HashMap<String,Object>();
                        map.put("status","未上传认证信息");
                    }
                    result.setData(map);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("查询成功");
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }


}
