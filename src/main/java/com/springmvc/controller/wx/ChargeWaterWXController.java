package com.springmvc.controller.wx;

import com.springmvc.entity.UsersCertificates;
import com.springmvc.service.impl.LoginSessionIdsService;
import com.springmvc.utils.CookileUtil;
import com.springmvc.utils.LoginSessionCheckUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping(value = "/wx/v1.0.0")
public class ChargeWaterWXController {
    @Resource
    private LoginSessionIdsService sessionIdsService;

    //进入水费账单主页
    @RequestMapping(value = "/joinChargeWater")
    @ResponseBody
    public ModelAndView joinChargeWater(HttpServletRequest request){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        String user_id_str = cookileUtil.getCookie_user_id(request,2);
        //标记是否已登录(false-未登录,已登录)
        Boolean isLogin = false;
        if (user_id_str == null){
            isLogin = false;
        }else {
            Integer user_id = Integer.parseInt(user_id_str);
            if (LoginSessionCheckUtil.wxPageCheckIsLogin(user_id,1,this.sessionIdsService) == null) {
                isLogin = false;
            }
            else{
                isLogin = true;
            }
        }
        if (isLogin == false){
            modelAndView = new ModelAndView("wx/login");
        }
        else{
            modelAndView = new ModelAndView("wx/property_center/charge_water");
        }
        return modelAndView;

    }

    //进入水费账单详情页
    @RequestMapping(value = "/joinChargeWaterDetail")
    @ResponseBody
    public ModelAndView joinChargeWaterDetail(HttpServletRequest request){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        String user_id_str = cookileUtil.getCookie_user_id(request,2);
        //标记是否已登录(false-未登录,已登录)
        Boolean isLogin = false;
        if (user_id_str == null){
            isLogin = false;
        }else {
            Integer user_id = Integer.parseInt(user_id_str);
            if (LoginSessionCheckUtil.wxPageCheckIsLogin(user_id,1,this.sessionIdsService) == null) {
                isLogin = false;
            }
            else{
                isLogin = true;
            }
        }
        if (isLogin == false){
            modelAndView = new ModelAndView("wx/login");
        }
        else{
            modelAndView = new ModelAndView("wx/property_center/water_detail");
        }
        return modelAndView;

    }

    //进入账单投诉页
    @RequestMapping(value = "/joinBillComplaint")
    @ResponseBody
    public ModelAndView joinBillComplaint(HttpServletRequest request){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        String user_id_str = cookileUtil.getCookie_user_id(request,2);
        //标记是否已登录(false-未登录,已登录)
        Boolean isLogin = false;
        if (user_id_str == null){
            isLogin = false;
        }else {
            Integer user_id = Integer.parseInt(user_id_str);
            if (LoginSessionCheckUtil.wxPageCheckIsLogin(user_id,1,this.sessionIdsService) == null) {
                isLogin = false;
            }
            else{
                isLogin = true;
            }
        }
        if (isLogin == false){
            modelAndView = new ModelAndView("wx/login");
        }
        else{
            modelAndView = new ModelAndView("wx/property_center/bill_complaint");
        }
        return modelAndView;

    }
}
