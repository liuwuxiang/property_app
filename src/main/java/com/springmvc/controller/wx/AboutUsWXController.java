package com.springmvc.controller.wx;

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
@RequestMapping(value = "/wx/v1.0.0")
public class AboutUsWXController {
    @Resource
    private LoginSessionIdsService sessionIdsService;


    //进入万能卡说明页
    @RequestMapping(value = "/wnkPage")
    @ResponseBody
    public ModelAndView wnkPage(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("wx/wnk_jj");
        return modelAndView;
    }

    //进入关于我们界面
    @RequestMapping(value = "/aboutUs")
    @ResponseBody
    public ModelAndView aboutUs(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("wx/about_us");
        return modelAndView;
    }

    //进入首页
    @RequestMapping(value = "/index")
    @ResponseBody
    public ModelAndView index(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("wx/home");
        return modelAndView;
    }

    //进入物业中心
    @RequestMapping(value = "/propertyCenter")
    @ResponseBody
    public ModelAndView propertyCenter(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("wx/property_center");
        return modelAndView;
    }

    //进入生活缴费
    @RequestMapping(value = "/livingPayment")
    @ResponseBody
    public ModelAndView livingPayment(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("wx/living_payment");
        return modelAndView;
    }
}
