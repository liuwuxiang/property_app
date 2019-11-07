package com.springmvc.controller.wx;

import com.springmvc.entity.Users;
import com.springmvc.service.impl.LoginSessionIdsService;
import com.springmvc.service.impl.UsersService;
import com.springmvc.service.impl.WnkRealNameAuthenticationService;
import com.springmvc.utils.CookileUtil;
import com.springmvc.utils.LoginSessionCheckUtil;
import com.springmvc.utils.WX.WXPublicSettingUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/wx/v1.0.0")
public class AuthorizationManagementWXController {
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private WnkRealNameAuthenticationService wnkRealNameAuthenticationService;
    @Resource
    private UsersService usersService;

    //进入授权管理主页
    @RequestMapping(value = "/joinAuthorizationManagement")
    @ResponseBody
    public ModelAndView joinAuthorizationManagement(HttpServletRequest request){
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
            modelAndView = new ModelAndView("wx/property_center/authorization_management");
        }
        return modelAndView;

    }

    //进入学生认证主页
    @RequestMapping(value = "/joinStudentAuthorization")
    @ResponseBody
    public ModelAndView joinStudentAuthorization(HttpServletRequest request,HttpServletResponse response) throws IOException {
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
            if (users != null && users.getMember_card_level() != -1){
                modelAndView = new ModelAndView("wx/student_authentication");
                response.sendRedirect("/property_system/wx/v1.0.0/cardBagHome");
            }
            else{
                List<Map<Object,Object>> list = this.wnkRealNameAuthenticationService.selectRecordByUserId(Integer.parseInt(user_id_str));
                if (list.size() <= 0){
                    modelAndView = new ModelAndView("wx/student_authentication");
                }
                else{
                    modelAndView = new ModelAndView("wx/student_authentication");
                    response.sendRedirect("/property_system/wx/v1.0.0/joinUpgradeMyMemberCard?open_card_type=1");
//                    Integer type = -1;
//                    for (Integer index = 0;index<list.size();index++){
//                        Map<Object,Object> map = list.get(index);
//                        type = (Integer) map.get("type");
//                    }
//                    if (type == 0){
//                        modelAndView = new ModelAndView("wx/student_authentication");
//                        response.sendRedirect("/property_system/wx/v1.0.0/joinUpgradeMyMemberCard?open_card_type=1");
//                    }
//                    else{
//                        modelAndView = new ModelAndView("wx/student_authentication");
//                        response.sendRedirect("/property_system/wx/v1.0.0/joinUpgradeMyMemberCard?open_card_type=2");
//                    }
                }
            }
        }
        return modelAndView;

    }

    //进入成人认证主页
    @RequestMapping(value = "/joinAdultAuthorization")
    @ResponseBody
    public ModelAndView joinAdultAuthorization(HttpServletRequest request,HttpServletResponse response) throws IOException {
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
            if (users != null && users.getMember_card_level() != -1){
                modelAndView = new ModelAndView("wx/adult_authentication");
                response.sendRedirect("/property_system/wx/v1.0.0/cardBagHome");
            }
            else{
                List<Map<Object,Object>> list = this.wnkRealNameAuthenticationService.selectRecordByUserId(Integer.parseInt(user_id_str));
                if (list.size() <= 0){
                    modelAndView = new ModelAndView("wx/adult_authentication");
                }
                else{
                    modelAndView = new ModelAndView("wx/adult_authentication");
                    response.sendRedirect("/property_system/wx/v1.0.0/joinUpgradeMyMemberCard?open_card_type=2");
//                    Integer type = -1;
//                    for (Integer index = 0;index<list.size();index++){
//                        Map<Object,Object> map = list.get(index);
//                        type = (Integer) map.get("type");
//                    }
//                    if (type == 0){
//                        modelAndView = new ModelAndView("wx/adult_authentication");
//                        response.sendRedirect("/property_system/wx/v1.0.0/joinUpgradeMyMemberCard?open_card_type=1");
//                    }
//                    else{
//                        modelAndView = new ModelAndView("wx/adult_authentication");
//                        response.sendRedirect("/property_system/wx/v1.0.0/joinUpgradeMyMemberCard?open_card_type=2");
//                    }
                }
            }
        }
        return modelAndView;

    }

    //进入实名认证授权书页面
    @RequestMapping(value = "/joinCertificateAuthorization")
    @ResponseBody
    public ModelAndView joinCertificateAuthorization(HttpServletRequest request){
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
            modelAndView = new ModelAndView("wx/certificate_authorization");
        }
        return modelAndView;

    }
}
