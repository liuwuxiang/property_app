package com.springmvc.controller.wx;

import com.springmvc.entity.*;
import com.springmvc.service.impl.*;
import com.springmvc.utils.CookileUtil;
import com.springmvc.utils.LoginSessionCheckUtil;
import com.springmvc.utils.Result;
import com.springmvc.utils.UUDUtil;
import com.springmvc.utils.wechat.WechatPayLineUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/wx/v1.0.0")
public class WnkCommodityOrderWXController {
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private UsersBankCardService usersBankCardService;
    @Resource
    private UsersService usersService;
    @Resource
    private WnkCommoditiesService wnkCommoditiesService;
    @Resource
    private WnkOrdersService wnkOrdersService;

    //进入万能卡商品订单结算页面(pay_way-支付方式，0-微信支付,1-万能卡支付)
    @RequestMapping(value = "/joinWnkCommodityOrder",method = RequestMethod.GET,params = {"commodity_id","guige_id","pay_way"})
    @ResponseBody
    public ModelAndView joinWnkCommodityOrder(HttpServletRequest request,Integer commodity_id,Integer guige_id,Integer pay_way){
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
            modelAndView = new ModelAndView("wx/wnk_order_confirm");
            modelAndView.addObject("commodity_id",commodity_id);
            modelAndView.addObject("guige_id",guige_id);
            modelAndView.addObject("pay_way",pay_way);

        }
        return modelAndView;

    }


}
