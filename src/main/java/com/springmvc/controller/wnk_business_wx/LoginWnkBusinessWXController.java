package com.springmvc.controller.wnk_business_wx;

import com.springmvc.service.impl.LoginSessionIdsService;
import com.springmvc.service.impl.WnkBusinessTypeService;
import com.springmvc.utils.CookileUtil;
import com.springmvc.utils.LoginSessionCheckUtil;
import com.springmvc.utils.Result;
import com.springmvc.utils.WX.WXPublicSettingUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/wnk_business")
public class LoginWnkBusinessWXController {
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private WnkBusinessTypeService wnkBusinessTypeService;

    //进入登录界面
    @RequestMapping(value = "")
    @ResponseBody
    public ModelAndView login(HttpServletRequest request,HttpServletResponse response){
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
            try {
                response.sendRedirect("/property_system/wnk_business/joinOrders");
            } catch (IOException e) {
                e.printStackTrace();
            }
            modelAndView = new ModelAndView("wnk_business/orders");

        }

        return modelAndView;
    }


    //进入商家注册界面
    @RequestMapping(value = "register",method = RequestMethod.GET,params = {"mobile"})
    @ResponseBody
    public ModelAndView register(HttpServletRequest request,HttpServletResponse response,String mobile){
        ModelAndView modelAndView = new ModelAndView("wnk_business/business_register_page");
        modelAndView.addObject("mobile",mobile);

        return modelAndView;
    }


    //进入商家推荐注册界面
    @RequestMapping(value = "recommendRegisterBusiness",method = RequestMethod.GET,params = {"mobile"})
    @ResponseBody
    public ModelAndView recommendRegisterBusiness(HttpServletRequest request,HttpServletResponse response,String mobile){
        ModelAndView modelAndView = new ModelAndView("wnk_business/business_register_page");
        modelAndView.addObject("mobile",mobile);

        return modelAndView;
    }


}
