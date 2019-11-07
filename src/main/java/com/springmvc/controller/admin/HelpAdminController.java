package com.springmvc.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
/**
 * @Author: 刘武祥
 * @Date: 2019/2/25 16:25
 * 
 */
@Controller
@RequestMapping(value = "/admin")
public class HelpAdminController {

    /**
     *
     * 功能描述：进入帮助说明界面
     *
     * @param   [request]
     * @return: org.springframework.web.servlet.ModelAndView
     * @auther: 刘武祥
     * @date：  2019/2/25 16:24
     */
    @RequestMapping(value = "/help")
    @ResponseBody
    public ModelAndView help(HttpServletRequest request){
        ModelAndView modelAndView;
//        CookileUtil cookileUtil = new CookileUtil();
//        if (cookileUtil.getCookie_user_id(request,0) == null){
//            modelAndView = new ModelAndView("admin/login");
//        }else {
        modelAndView = new ModelAndView("admin/control/help/help");
//        }
        return modelAndView;
    }
}
