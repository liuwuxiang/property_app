package com.springmvc.controller.admin.doings_spread;

import com.springmvc.utils.CookileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 杨新杰
 * @Date 2018/11/3 10:14
 */
@Controller
@RequestMapping(value = "/admin")
public class JumpDoingsSpreadController {


    @RequestMapping(value = "/JumpDoingsSpreadIndex",method = RequestMethod.GET)
    public ModelAndView JumpDoingsSpreadIndex(HttpServletRequest request){
        ModelAndView modelAndView;
        CookileUtil cookileUtil = new CookileUtil();
        if (cookileUtil.getCookie_user_id(request,0) == null){
            modelAndView = new ModelAndView("admin/login");
        }else {
            modelAndView = new ModelAndView("admin/control/doings_spread/doings_spread");
        }
        return modelAndView;
    }


}
