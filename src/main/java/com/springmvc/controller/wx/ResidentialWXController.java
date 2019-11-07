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
public class ResidentialWXController {
    @Resource
    private LoginSessionIdsService sessionIdsService;

    //进入小区选择页面
    @RequestMapping(value = "/joinResidentialChoose",method = RequestMethod.GET,params = {"city_id"})
    @ResponseBody
    public ModelAndView joinResidentialChoose(HttpServletRequest request,Integer city_id){
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
            modelAndView = new ModelAndView("wx/residential");
            modelAndView.addObject("city_id",city_id);
        }
        return modelAndView;
    }

    //进入小区栋号选择页面
    @RequestMapping(value = "/joinResidentialBuildChoose",method = RequestMethod.GET,params = {"residential_id"})
    @ResponseBody
    public ModelAndView joinResidentialBuildChoose(HttpServletRequest request,Integer residential_id){
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
            modelAndView = new ModelAndView("wx/building_choose");
            modelAndView.addObject("residential_id",residential_id);
        }
        return modelAndView;
    }

    //进入小区单元号选择页面
    @RequestMapping(value = "/joinResidentialUnitChoose",method = RequestMethod.GET,params = {"build_id","building_number"})
    @ResponseBody
    public ModelAndView joinResidentialUnitChoose(HttpServletRequest request,Integer build_id,String building_number){
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
            modelAndView = new ModelAndView("wx/unit_choose");
            modelAndView.addObject("build_id",build_id);
            modelAndView.addObject("building_number",building_number);
        }
        return modelAndView;
    }

    //进入小区房间号选择页面
    @RequestMapping(value = "/joinResidentialHouseNumberChoose",method = RequestMethod.GET,params = {"build_id","building_number","unit_id","unit_number"})
    @ResponseBody
    public ModelAndView joinResidentialHouseNumberChoose(HttpServletRequest request,Integer build_id,String building_number,Integer unit_id,String unit_number){
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
            modelAndView = new ModelAndView("wx/house_number_choose");
            modelAndView.addObject("build_id",build_id);
            modelAndView.addObject("building_number",building_number);
            modelAndView.addObject("unit_id",unit_id);
            modelAndView.addObject("unit_number",unit_number);
        }
        return modelAndView;
    }
}
