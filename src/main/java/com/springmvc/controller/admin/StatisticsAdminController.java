package com.springmvc.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/admin")
public class StatisticsAdminController {

    //进入统计界面
    @RequestMapping(value = "/statistics")
    @ResponseBody
    public ModelAndView statistics(HttpServletRequest request){
        ModelAndView modelAndView;
//        CookileUtil cookileUtil = new CookileUtil();
//        if (cookileUtil.getCookie_user_id(request,0) == null){
//            modelAndView = new ModelAndView("admin/login");
//        }else {
        modelAndView = new ModelAndView("admin/control/statistics/statistics");
//        }
        return modelAndView;
    }
}
