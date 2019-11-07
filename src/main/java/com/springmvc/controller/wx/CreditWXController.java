package com.springmvc.controller.wx;

import com.springmvc.entity.Users;
import com.springmvc.service.impl.LoginSessionIdsService;
import com.springmvc.service.impl.UsersService;
import com.springmvc.utils.CookileUtil;
import com.springmvc.utils.LoginSessionCheckUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping(value = "/wx/v1.0.0")
public class CreditWXController {
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private UsersService usersService;

    //进入信用评级
    @RequestMapping(value = "/joinCredit")
    @ResponseBody
    public ModelAndView joinCredit(HttpServletRequest request){
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
            modelAndView = new ModelAndView("wx/credit");
            Users users = this.usersService.selectById(Integer.parseInt(user_id_str));
            if (users == null){
                modelAndView.addObject("credit_number",0);
                modelAndView.addObject("credit_date","");
            }
            else{
                modelAndView.addObject("credit_number",users.getCredit_number());
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
                String dateString = formatter.format(users.getCredit_date());
                modelAndView.addObject("credit_date",dateString);
            }
        }
        return modelAndView;
    }
}
