package com.springmvc.controller.wx;

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

@Controller
@RequestMapping(value = "/wx/v1.0.0")
public class ConsumptionIntegralWXController {
    @Resource
    private LoginSessionIdsService sessionIdsService;

    //进入消费积分首页
    @RequestMapping(value = "/consumptionIntegralHome")
    @ResponseBody
    public ModelAndView consumptionIntegralHome(HttpServletRequest request){
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
            modelAndView = new ModelAndView("wx/consumption_integral");
        }
        return modelAndView;
    }

    //进入积分帮助说明页面(type=0:消费积分，type=1:通用积分)
    @RequestMapping(value = "/integralMakeHelp",method = RequestMethod.GET,params = {"type"})
    @ResponseBody
    public ModelAndView integralMakeHelp(HttpServletRequest request,Integer type){
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
            modelAndView = new ModelAndView("wx/integral_make_help");
            modelAndView.addObject("title",type==0?"消费积分":"通用积分");
            modelAndView.addObject("type",type);
        }
        return modelAndView;
    }

    //进入积分兑换页面(type=0:消费积分，type=1:通用积分)
    @RequestMapping(value = "/integralExchange",method = RequestMethod.GET,params = {"type"})
    @ResponseBody
    public ModelAndView integralExchange(HttpServletRequest request,Integer type){
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
            modelAndView = new ModelAndView("wx/integral_exchange");
            modelAndView.addObject("title",type==0?"消费积分":"通用积分");
            modelAndView.addObject("type",type);
        }
        return modelAndView;
    }

    //进入积分充值页面(type=0:消费积分，type=1:通用积分)
    @RequestMapping(value = "/integralRecharge",method = RequestMethod.GET,params = {"type"})
    @ResponseBody
    public ModelAndView integralRecharge(HttpServletRequest request,Integer type){
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
            modelAndView = new ModelAndView("wx/integral_recharge");
            modelAndView.addObject("title",type==0?"消费积分":"通用积分");
            modelAndView.addObject("type",type);
        }
        return modelAndView;
    }
}
