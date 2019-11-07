package com.springmvc.controller.admin;

import com.springmvc.entity.AboutUs;
import com.springmvc.service.impl.AboutUsService;
import com.springmvc.utils.CookileUtil;
import com.springmvc.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin")
public class IntegralSettingAdminController {
    @Resource
    private AboutUsService aboutUsService;

    //进入积分设置界面
    @RequestMapping(value = "/integralSetting")
    @ResponseBody
    public ModelAndView integralSetting(HttpServletRequest request){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/integral_setting/integral_setting");
            AboutUs integralAndCoin = this.aboutUsService.selectIntegralAbout(7);
            AboutUs integralAndRMB = this.aboutUsService.selectIntegralAbout(9);
            if (integralAndCoin == null){
                modelAndView.addObject("integral_coin","");
            }
            else{
                modelAndView.addObject("integral_coin",Integer.parseInt(integralAndCoin.getContent()));
            }
            if (integralAndRMB == null){
                modelAndView.addObject("integral_rmb","");
            }
            else{
                modelAndView.addObject("integral_rmb",Integer.parseInt(integralAndRMB.getContent()));
            }
        }
        return modelAndView;
    }

    // 设置积分信息
    @RequestMapping(value = "/setIntegralInformation", method = RequestMethod.POST,params = {"integral_rmb","integral_coin"})
    @ResponseBody
    public Result setIntegralInformation(HttpServletRequest request, HttpServletResponse response,Integer integral_rmb,Integer integral_coin){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            String userId = cookileUtil.getCookie_user_id(request,0);
            if (userId == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂未登录");
            }else {
                if (integral_rmb <= 0 || integral_coin <= 0){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("比例不可小于等于0");
                }
                else{
                    int integralRmbState = this.aboutUsService.setIntegralAndRMB(integral_rmb.toString());
                    if (integralRmbState<= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("保存失败");
                    }
                    else{
                        int integralCoinState = this.aboutUsService.setIntegralAndCoin(integral_coin.toString());
                        if (integralCoinState <= 0){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("积分与银币兑换比例设置失败");
                        }
                        else{
                            result.setData("");
                            result.setStatus(Result.SUCCESS);
                            result.setMsg("保存成功");
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
}
