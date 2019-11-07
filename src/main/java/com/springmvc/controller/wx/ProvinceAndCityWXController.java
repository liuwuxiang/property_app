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
public class ProvinceAndCityWXController {
    @Resource
    private LoginSessionIdsService sessionIdsService;

    //进入省份选择页面
    @RequestMapping(value = "/joinProvinceChoose")
    @ResponseBody
    public ModelAndView joinProvinceChoose(HttpServletRequest request){
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
            modelAndView = new ModelAndView("wx/province_choose");
        }
        return modelAndView;
    }

    //进入城市选择页面
    @RequestMapping(value = "/joinCityChoose",method = RequestMethod.GET,params = {"province_id","province_name"})
    @ResponseBody
    public ModelAndView joinCityChoose(HttpServletRequest request,Integer province_id,String province_name){
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
            modelAndView = new ModelAndView("wx/city_choose");
            modelAndView.addObject("province_id",province_id);
            modelAndView.addObject("province_name",province_name);
        }
        return modelAndView;
    }
}
