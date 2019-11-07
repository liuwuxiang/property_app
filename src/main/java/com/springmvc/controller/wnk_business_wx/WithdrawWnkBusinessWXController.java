package com.springmvc.controller.wnk_business_wx;

import com.springmvc.entity.WnkBusinessAccount;
import com.springmvc.service.impl.LoginSessionIdsService;
import com.springmvc.service.impl.WnkBusinessAccountService;
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
public class WithdrawWnkBusinessWXController {
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private WnkBusinessAccountService wnkBusinessAccountService;

    //进入提现页面
    @RequestMapping(value = "/joinWithdraw")
    @ResponseBody
    public ModelAndView joinWithdraw(HttpServletRequest request){
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
        modelAndView = new ModelAndView("wnk_business/wnk_business");
        }
        return modelAndView;
    }

    //进入商家推荐注册页面
    @RequestMapping(value = "/joinBusinessRecommendRegister",method = RequestMethod.GET,params = {"business_id"})
    @ResponseBody
    public ModelAndView joinBusinessRecommendRegister(HttpServletRequest request,Integer business_id){
        ModelAndView modelAndView = new ModelAndView("wnk_business/business_recommend");

        WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
        if (wnkBusinessAccount == null){
            modelAndView.addObject("mobile","");
        }
        else{
            modelAndView.addObject("mobile",wnkBusinessAccount.getMobile());
        }
        return modelAndView;
    }
}
