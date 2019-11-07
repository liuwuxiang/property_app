package com.springmvc.controller.wx;

import com.springmvc.entity.UsersBankCard;
import com.springmvc.entity.WithdrawSupportBank;
import com.springmvc.service.impl.LoginSessionIdsService;
import com.springmvc.service.impl.UsersBankCardService;
import com.springmvc.service.impl.WithdrawSupportBankService;
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
public class GeneralIntegralWXController {
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private UsersBankCardService usersBankCardService;
    @Resource
    private WithdrawSupportBankService withdrawSupportBankService;

    //进入用户通用积分首页
    @RequestMapping(value = "/generalIntegralHome")
    @ResponseBody
    public ModelAndView generalIntegralHome(HttpServletRequest request){
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
            modelAndView = new ModelAndView("admin/control/admin_message/set_admin_information");
        }
        if (isLogin == false){
            modelAndView = new ModelAndView("wx/login");
        }
        else{
            modelAndView = new ModelAndView("wx/general_integral");
        }
        return modelAndView;

    }


    //进入通用积分提现页面
    @RequestMapping(value = "/generalIntegralWithdraw")
    @ResponseBody
    public ModelAndView generalIntegralWithdraw(HttpServletRequest request){
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
            modelAndView = new ModelAndView("admin/control/admin_message/set_admin_information");
        }
        if (isLogin == false){
            modelAndView = new ModelAndView("wx/login");
        }
        else{
            modelAndView = new ModelAndView("wx/general_integral_withdraw");
            UsersBankCard usersBankCard = this.usersBankCardService.selectRecordByUserId(Integer.parseInt(user_id_str));
            if (usersBankCard == null){
                modelAndView.addObject("bank_id",-1);
                modelAndView.addObject("bank_name","");
                modelAndView.addObject("bank_card_number","");
                modelAndView.addObject("real_name","");
            }
            else{
                WithdrawSupportBank withdrawSupportBank = this.withdrawSupportBankService.selectById(usersBankCard.getBank_id());
                if (withdrawSupportBank == null){
                    modelAndView.addObject("bank_id",-1);
                    modelAndView.addObject("bank_name","");
                    modelAndView.addObject("bank_card_number","");
                    modelAndView.addObject("real_name","");
                }
                else{
                    modelAndView.addObject("bank_id",usersBankCard.getBank_id());
                    modelAndView.addObject("bank_name",withdrawSupportBank.getName());
                    modelAndView.addObject("bank_card_number",usersBankCard.getBank_number());
                    modelAndView.addObject("real_name",usersBankCard.getBank_card_name());
                }
            }
        }
        return modelAndView;

    }
}
