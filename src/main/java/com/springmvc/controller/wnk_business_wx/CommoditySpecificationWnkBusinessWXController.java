package com.springmvc.controller.wnk_business_wx;

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
@RequestMapping(value = "/wnk_business")
public class CommoditySpecificationWnkBusinessWXController {
    @Resource
    private LoginSessionIdsService sessionIdsService;

    //进入商品规格管理页面
    @RequestMapping(value = "/joinCommoditySpecification",method = RequestMethod.GET,params = {"commodity_id"})
    @ResponseBody
    public ModelAndView joinCommoditySpecification(HttpServletRequest request,Integer commodity_id){
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
            modelAndView = new ModelAndView("wnk_business/commodity_specifications");
            modelAndView.addObject("commodity_id",commodity_id);
        }
        return modelAndView;
    }
}
