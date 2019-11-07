package com.springmvc.controller.wx;

import com.springmvc.entity.Users;
import com.springmvc.service.impl.LoginSessionIdsService;
import com.springmvc.service.impl.UsersService;
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
public class MyMemberCardWXController {
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private UsersService usersService;

    //进入我的会员卡页面
    @RequestMapping(value = "/joinMyMemberCard")
    @ResponseBody
    public ModelAndView joinMyMemberCard(HttpServletRequest request){
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
            Users users = this.usersService.selectById(Integer.parseInt(user_id_str));
            if (users == null){
                modelAndView = new ModelAndView("wx/login");
            }
            else{
                if (users.getMember_card_level() == -1){
                    modelAndView = new ModelAndView("wx/open_member_card");
                    modelAndView.addObject("title","会员卡办理");
                    modelAndView.addObject("type",-1);
                    modelAndView.addObject("member_card_level",-1);
                }
                else{
                    modelAndView = new ModelAndView("wx/my_member_card");
                }
            }

        }
        return modelAndView;

    }

    //进入升级会员卡页面
    @RequestMapping(value = "/joinUpgradeMyMemberCard",method = RequestMethod.GET,params = {"open_card_type"})
    @ResponseBody
    public ModelAndView joinUpgradeMyMemberCard(HttpServletRequest request,Integer open_card_type){
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
            Users users = this.usersService.selectById(Integer.parseInt(user_id_str));
            if (users == null){
                modelAndView = new ModelAndView("wx/login");
            }
            else{
                if (users.getMember_card_level() == -1){
                    modelAndView = new ModelAndView("wx/open_member_card");
                    modelAndView.addObject("title","会员卡办理");
                    modelAndView.addObject("type",-1);
                    modelAndView.addObject("member_card_level",-1);
                    modelAndView.addObject("open_card_type",open_card_type);
                }
                else{
                    modelAndView = new ModelAndView("wx/open_member_card");
                    modelAndView.addObject("title","会员卡升级");
                    modelAndView.addObject("type",0);
                    modelAndView.addObject("member_card_level",users.getMember_card_level());
                    modelAndView.addObject("open_card_type",open_card_type);
                }
            }

        }
        return modelAndView;

    }
}
