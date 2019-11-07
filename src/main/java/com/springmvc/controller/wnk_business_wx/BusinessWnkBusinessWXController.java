package com.springmvc.controller.wnk_business_wx;

import com.springmvc.service.impl.LoginSessionIdsService;
import com.springmvc.utils.CookileUtil;
import com.springmvc.utils.LoginSessionCheckUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/wnk_business")
public class BusinessWnkBusinessWXController {
    @Resource
    private LoginSessionIdsService sessionIdsService;

    //进入商户中心
    @RequestMapping(value = "/joinBusinessCenter")
    @ResponseBody
    public ModelAndView joinBusinessCenter(HttpServletRequest request){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        String user_id_str = cookileUtil.getCookie_user_id(request,3);
        //标记是否已登录(false-未登录,已登录)
        Boolean isLogin = false;
        if (user_id_str == null){
            isLogin = false;
        }else {
            Integer user_id = Integer.parseInt(user_id_str);
            if (LoginSessionCheckUtil.wxPageCheckIsLogin(user_id,3,this.sessionIdsService) == null) {
                isLogin = false;
            }
            else{
                isLogin = true;
            }
        }
        if (isLogin == false){
            modelAndView = new ModelAndView("wnk_business/login");
        }
        else{
            modelAndView = new ModelAndView("wnk_business/my");
        }

        return modelAndView;
    }

    //进入店铺信息设置页面
    @RequestMapping(value = "/joinShopInformation")
    @ResponseBody
    public ModelAndView joinShopInformation(HttpServletRequest request){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        String user_id_str = cookileUtil.getCookie_user_id(request,3);
        //标记是否已登录(false-未登录,已登录)
        Boolean isLogin = false;
        if (user_id_str == null){
            isLogin = false;
        }else {
            Integer user_id = Integer.parseInt(user_id_str);
            if (LoginSessionCheckUtil.wxPageCheckIsLogin(user_id,3,this.sessionIdsService) == null) {
                isLogin = false;
            }
            else{
                isLogin = true;
            }
        }
        if (isLogin == false){
            modelAndView = new ModelAndView("wnk_business/login");
        }
        else{
        modelAndView = new ModelAndView("wnk_business/shop_information");
        }
        return modelAndView;
    }

    //进入账户余额页面
    @RequestMapping(value = "/joinAccountBalance")
    @ResponseBody
    public ModelAndView joinAccountBalance(HttpServletRequest request){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        String user_id_str = cookileUtil.getCookie_user_id(request,3);
        //标记是否已登录(false-未登录,已登录)
        Boolean isLogin = false;
        if (user_id_str == null){
            isLogin = false;
        }else {
            Integer user_id = Integer.parseInt(user_id_str);
            if (LoginSessionCheckUtil.wxPageCheckIsLogin(user_id,3,this.sessionIdsService) == null) {
                isLogin = false;
            }
            else{
                isLogin = true;
            }
        }
        if (isLogin == false){
            modelAndView = new ModelAndView("wnk_business/login");
        }
        else{
        modelAndView = new ModelAndView("wnk_business/account_balance");
        }
        return modelAndView;
    }

    //进入消息中心页面
    @RequestMapping(value = "/joinMessageCenter")
    @ResponseBody
    public ModelAndView joinMessageCenter(HttpServletRequest request){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        String user_id_str = cookileUtil.getCookie_user_id(request,3);
        //标记是否已登录(false-未登录,已登录)
        Boolean isLogin = false;
        if (user_id_str == null){
            isLogin = false;
        }else {
            Integer user_id = Integer.parseInt(user_id_str);
            if (LoginSessionCheckUtil.wxPageCheckIsLogin(user_id,3,this.sessionIdsService) == null) {
                isLogin = false;
            }
            else{
                isLogin = true;
            }
        }
        if (isLogin == false){
            modelAndView = new ModelAndView("wnk_business/login");
        }
        else{
        modelAndView = new ModelAndView("wnk_business/message_center");
        }
        return modelAndView;
    }

    //进入安全中心页面
    @RequestMapping(value = "/joinAccountSecurity")
    @ResponseBody
    public ModelAndView joinAccountSecurity(HttpServletRequest request){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        String user_id_str = cookileUtil.getCookie_user_id(request,3);
        //标记是否已登录(false-未登录,已登录)
        Boolean isLogin = false;
        if (user_id_str == null){
            isLogin = false;
        }else {
            Integer user_id = Integer.parseInt(user_id_str);
            if (LoginSessionCheckUtil.wxPageCheckIsLogin(user_id,3,this.sessionIdsService) == null) {
                isLogin = false;
            }
            else{
                isLogin = true;
            }
        }
        if (isLogin == false){
            modelAndView = new ModelAndView("wnk_business/login");
        }
        else{
        modelAndView = new ModelAndView("wnk_business/account_security");
        }
        return modelAndView;
    }

    //进入关于我们页面
    @RequestMapping(value = "/joinAboutUs")
    @ResponseBody
    public ModelAndView joinAboutUs(HttpServletRequest request){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        String user_id_str = cookileUtil.getCookie_user_id(request,3);
        //标记是否已登录(false-未登录,已登录)
        Boolean isLogin = false;
        if (user_id_str == null){
            isLogin = false;
        }else {
            Integer user_id = Integer.parseInt(user_id_str);
            if (LoginSessionCheckUtil.wxPageCheckIsLogin(user_id,3,this.sessionIdsService) == null) {
                isLogin = false;
            }
            else{
                isLogin = true;
            }
        }
        if (isLogin == false){
            modelAndView = new ModelAndView("wnk_business/login");
        }
        else{
        modelAndView = new ModelAndView("wnk_business/about_us");
        }
        return modelAndView;
    }

    //进入登录密码修改页面
    @RequestMapping(value = "/joinSetLoginPwd")
    @ResponseBody
    public ModelAndView joinSetLoginPwd(HttpServletRequest request){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        String user_id_str = cookileUtil.getCookie_user_id(request,3);
        //标记是否已登录(false-未登录,已登录)
        Boolean isLogin = false;
        if (user_id_str == null){
            isLogin = false;
        }else {
            Integer user_id = Integer.parseInt(user_id_str);
            if (LoginSessionCheckUtil.wxPageCheckIsLogin(user_id,3,this.sessionIdsService) == null) {
                isLogin = false;
            }
            else{
                isLogin = true;
            }
        }
        if (isLogin == false){
            modelAndView = new ModelAndView("wnk_business/login");
        }
        else{
        modelAndView = new ModelAndView("wnk_business/set_login_pwd");
        }
        return modelAndView;
    }

    //进入支付密码修改页面
    @RequestMapping(value = "/joinSetPayPwd")
    @ResponseBody
    public ModelAndView joinSetPayPwd(HttpServletRequest request){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        String user_id_str = cookileUtil.getCookie_user_id(request,3);
        //标记是否已登录(false-未登录,已登录)
        Boolean isLogin = false;
        if (user_id_str == null){
            isLogin = false;
        }else {
            Integer user_id = Integer.parseInt(user_id_str);
            if (LoginSessionCheckUtil.wxPageCheckIsLogin(user_id,3,this.sessionIdsService) == null) {
                isLogin = false;
            }
            else{
                isLogin = true;
            }
        }
        if (isLogin == false){
            modelAndView = new ModelAndView("wnk_business/login");
        }
        else{
        modelAndView = new ModelAndView("wnk_business/set_pay_pwd");
        }
        return modelAndView;
    }

    //进入积分系统页面
    @RequestMapping(value = "/joinIntegral")
    @ResponseBody
    public ModelAndView joinIntegral(HttpServletRequest request){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        String user_id_str = cookileUtil.getCookie_user_id(request,3);
        //标记是否已登录(false-未登录,已登录)
        Boolean isLogin = false;
        if (user_id_str == null){
            isLogin = false;
        }else {
            Integer user_id = Integer.parseInt(user_id_str);
            if (LoginSessionCheckUtil.wxPageCheckIsLogin(user_id,3,this.sessionIdsService) == null) {
                isLogin = false;
            }
            else{
                isLogin = true;
            }
        }
        if (isLogin == false){
            modelAndView = new ModelAndView("wnk_business/login");
        }
        else{
            modelAndView = new ModelAndView("wnk_business/integral/index");
        }
        return modelAndView;
    }

}
