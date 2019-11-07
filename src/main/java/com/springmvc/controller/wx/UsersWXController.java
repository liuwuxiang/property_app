package com.springmvc.controller.wx;

import com.springmvc.entity.AboutUs;
import com.springmvc.entity.MemberLevels;
import com.springmvc.entity.Users;
import com.springmvc.service.impl.AboutUsService;
import com.springmvc.service.impl.LoginSessionIdsService;
import com.springmvc.service.impl.MemberLevelsService;
import com.springmvc.utils.CookileUtil;
import com.springmvc.utils.LoginSessionCheckUtil;
import com.springmvc.utils.Result;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/wx/v1.0.0")
public class UsersWXController {
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private MemberLevelsService memberLevelsService;
    @Resource
    private AboutUsService aboutUsService;


    private static Logger logger = Logger.getLogger(UsersWXController.class);

    //进入登录页面
    @RequestMapping(value = "/login")
    @ResponseBody
    public ModelAndView login(HttpServletRequest request){
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
            modelAndView = new ModelAndView("wx/my");
        }
        return modelAndView;

    }


    //进入注册页面(type=0-用户推荐,type=1商家推荐)
    @RequestMapping(value = "/register")
    @ResponseBody
    public ModelAndView register(HttpServletRequest request,@RequestParam(value="invitation_code",defaultValue = "",required=false) String invitation_code,@RequestParam(value="type",defaultValue = "0",required=false) Integer type){
        ModelAndView modelAndView = new ModelAndView("wx/account_register");
        if (invitation_code == null || invitation_code.equals("")){
            modelAndView.addObject("invitation_code","");
        }
        else{
            modelAndView.addObject("invitation_code",invitation_code);
        }
        modelAndView.addObject("type",type);
        return modelAndView;
    }

    //进入找回密码界面
    @RequestMapping(value = "/retrievePassword")
    @ResponseBody
    public ModelAndView retrievePassword(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView("wx/retrieve_password");
        return modelAndView;
    }


    //进入个人中心
    @RequestMapping(value = "/my")
    @ResponseBody
    public ModelAndView my(HttpServletRequest request){
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
            modelAndView = new ModelAndView("wx/my");
            Integer user_id = Integer.parseInt(user_id_str);
            modelAndView.addObject("login_session",LoginSessionCheckUtil.wxPageGetUserLoginSession(user_id,1,this.sessionIdsService));
        }
        return modelAndView;

    }

    //进入用户个人信息页面
    @RequestMapping(value = "/personalInformation")
    @ResponseBody
    public ModelAndView personalInformation(HttpServletRequest request){
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
            modelAndView = new ModelAndView("wx/my_information");
        }
        return modelAndView;

    }

    //进入会员中心首页
    @RequestMapping(value = "/memberCenter")
    @ResponseBody
    public ModelAndView memberCenter(HttpServletRequest request){
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
            modelAndView = new ModelAndView("wx/member_center");
        }
        return modelAndView;

    }

    //进入我的团队首页
    @RequestMapping(value = "/myTeamHome")
    @ResponseBody
    public ModelAndView myTeamHome(HttpServletRequest request){
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
            modelAndView = new ModelAndView("wx/my_team");
        }
        return modelAndView;

    }

    //进入我的积分首页
    @RequestMapping(value = "/myIntegral")
    @ResponseBody
    public ModelAndView myIntegral(HttpServletRequest request){
        logger.info("进入我的积分首页");
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
            modelAndView = new ModelAndView("wx/integral_mall/index");
        }
        return modelAndView;

    }


    //进入系统设置首页
    @RequestMapping(value = "/systemSettingHome")
    @ResponseBody
    public ModelAndView systemSettingHome(HttpServletRequest request){
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
            modelAndView = new ModelAndView("wx/system_setting");
        }
        return modelAndView;

    }

    //进入用户昵称设置页面
    @RequestMapping(value = "/setNickName")
    @ResponseBody
    public ModelAndView setNickName(HttpServletRequest request){
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
            modelAndView = new ModelAndView("wx/set_nickName");
        }
        return modelAndView;

    }

    //进入用户性别设置页面
    @RequestMapping(value = "/sexChoose")
    @ResponseBody
    public ModelAndView sexChoose(HttpServletRequest request){
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
            modelAndView = new ModelAndView("wx/sex_choose");
        }
        return modelAndView;

    }

    //进入手机号或邮箱修改页面
    @RequestMapping(value = "/setMobileOrEmail",method = RequestMethod.GET,params = {"type"})
    @ResponseBody
    public ModelAndView setMobileOrEmail(HttpServletRequest request,Integer type){
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
            modelAndView = new ModelAndView("wx/setMobileOrEmail");
            modelAndView.addObject("type",type);
        }
        return modelAndView;
    }

    //进入实名认证首页
    @RequestMapping(value = "/realNameAuthenticationHome")
    @ResponseBody
    public ModelAndView realNameAuthenticationHome(HttpServletRequest request){
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
            modelAndView = new ModelAndView("wx/real_name_authentication");
        }
        return modelAndView;
    }

    //进入安全中心首页
    @RequestMapping(value = "/securityCenterHome")
    @ResponseBody
    public ModelAndView securityCenterHome(HttpServletRequest request){
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
            modelAndView = new ModelAndView("wx/security_center");
        }
        return modelAndView;
    }

    //进入收货地址管理页面
    @RequestMapping(value = "/receivingAddressManager")
    @ResponseBody
    public ModelAndView receivingAddressManager(HttpServletRequest request){
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
            modelAndView = new ModelAndView("wx/receiving_address_manager");
        }
        return modelAndView;
    }

    //进入业主认证页面
    @RequestMapping(value = "/ownerAuthentication")
    @ResponseBody
    public ModelAndView ownerAuthentication(HttpServletRequest request){
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
            modelAndView = new ModelAndView("wx/owner_authentication");
        }
        return modelAndView;
    }

    //进入车主认证页面
    @RequestMapping(value = "/cardAuthentication")
    @ResponseBody
    public ModelAndView cardAuthentication(HttpServletRequest request){
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
            modelAndView = new ModelAndView("wx/card_authentication");
        }
        return modelAndView;
    }

    //进入身份证认证页面
    @RequestMapping(value = "/idCardAuthentication")
    @ResponseBody
    public ModelAndView idCardAuthentication(HttpServletRequest request){
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
            modelAndView = new ModelAndView("wx/id_card_authentication");
        }
        return modelAndView;
    }

    //进入登录密码修改页面
    @RequestMapping(value = "/setLoginPwd",method = RequestMethod.GET,params = {"current_code"})
    @ResponseBody
    public ModelAndView setLoginPwd(HttpServletRequest request,String current_code){
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
            modelAndView = new ModelAndView("wx/set_login_pwd");
            modelAndView.addObject("current_code",current_code);
        }
        return modelAndView;
    }

    //进入支付密码修改页面
    @RequestMapping(value = "/setPayPwd",method = RequestMethod.GET,params = {"current_code"})
    @ResponseBody
    public ModelAndView setPayPwd(HttpServletRequest request,String current_code){
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
            modelAndView = new ModelAndView("wx/set_pay_pwd");
            modelAndView.addObject("current_code",current_code);
        }
        return modelAndView;
    }

    //进入密保问题修改页面
    @RequestMapping(value = "/setSecurityQuestion",method = RequestMethod.GET,params = {"current_code"})
    @ResponseBody
    public ModelAndView setSecurityQuestion(HttpServletRequest request,String current_code){
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
            modelAndView = new ModelAndView("wx/set_security_question");
            modelAndView.addObject("current_code",current_code);
        }
        return modelAndView;
    }


    //进入账号关联页面
    @RequestMapping(value = "/accountAssociatio")
    @ResponseBody
    public ModelAndView accountAssociatio(HttpServletRequest request){
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
            modelAndView = new ModelAndView("wx/account_associatio");
        }
        return modelAndView;
    }

    //进入手机号关联页面
    @RequestMapping(value = "/mobileAssociation")
    @ResponseBody
    public ModelAndView mobileAssociation(HttpServletRequest request){
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
            modelAndView = new ModelAndView("wx/mobile_associatio");
        }
        return modelAndView;
    }

    // 退出登录
    @RequestMapping(value = "/exitLogin", method = RequestMethod.POST,params = {"user_id"})
    @ResponseBody
    public Result aboutUsData(HttpServletRequest request, HttpServletResponse response,Integer user_id){
        Result result = new Result();
        try {
            CookileUtil cookileUtil = new CookileUtil();
            cookileUtil.clearCookie(2,user_id.toString(),response);
            LoginSessionCheckUtil.exitLogin(user_id,1,request,this.sessionIdsService);
            Map<Object,Object> map = new HashMap<Object,Object>();
            map.put("url","/property_system/wx/v1.0.0/login");
            result.setData(map);
            result.setStatus(Result.SUCCESS);
            result.setMsg("退出成功");
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("退出失败");
        }
        return result;
    }


    //进入会员等级充值升级界面
    @RequestMapping(value = "/memberLevelRechargeUpgradePage",method = RequestMethod.GET,params = {"member_level_id"})
    @ResponseBody
    public ModelAndView memberLevelRechargeUpgradePage(HttpServletRequest request,Integer member_level_id){
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
            modelAndView = new ModelAndView("wx/recharge_upgrade");
            MemberLevels memberLevels = this.memberLevelsService.selectById(member_level_id);
            if (memberLevels == null){
                modelAndView.addObject("member_level_id",-1);
                modelAndView.addObject("integral_number",-1);
                modelAndView.addObject("title","充值升级");
                modelAndView.addObject("silver_coin_number",0);
            }
            else{
                modelAndView.addObject("member_level_id",member_level_id);
                modelAndView.addObject("integral_number",memberLevels.getRecharge_consumption_integral());
                modelAndView.addObject("title","升级至"+memberLevels.getName());
                AboutUs aboutUs = this.aboutUsService.selectIntegralAbout(7);
                if (aboutUs == null){
                    modelAndView.addObject("silver_coin_number",0);
                }
                else{

                    modelAndView.addObject("silver_coin_number",memberLevels.getRecharge_consumption_integral() / Integer.parseInt(aboutUs.getContent()));
                }
            }
        }
        return modelAndView;

    }
}
