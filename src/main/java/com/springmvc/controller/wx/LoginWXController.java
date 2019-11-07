package com.springmvc.controller.wx;

import com.springmvc.entity.MemberLevels;
import com.springmvc.entity.Users;
import com.springmvc.service.impl.LoginSessionIdsService;
import com.springmvc.service.impl.MemberLevelsService;
import com.springmvc.service.impl.UsersService;
import com.springmvc.utils.CookileUtil;
import com.springmvc.utils.HttpRequest;
import com.springmvc.utils.LoginSessionCheckUtil;
import com.springmvc.utils.UUDUtil;
import com.springmvc.utils.WX.WXPublicSettingUtil;
import com.springmvc.utils.WX.WechatUtil;
import com.springmvc.utils.qrCode.QRCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/wx/v1.0.0")
public class LoginWXController {
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private UsersService usersService;
    @Resource
    private MemberLevelsService memberLevelsService;

    /*
     * 微信登录
     * */
    @RequestMapping(value = "/wxLogin", method = RequestMethod.GET)
    @ResponseBody
    public void wxLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CookileUtil cookileUtil = new CookileUtil();
        String user_id_str = cookileUtil.getCookie_user_id(request, 2);
        //标记是否已登录(false-未登录,已登录)
        Boolean isLogin = false;
        if (user_id_str == null) {
            isLogin = false;
        } else {
            Integer user_id = Integer.parseInt(user_id_str);
            if (LoginSessionCheckUtil.wxPageCheckIsLogin(user_id, 1, this.sessionIdsService) == null) {
                isLogin = false;
            } else {
                isLogin = true;
            }
        }
        //未登录
        if (isLogin == false) {
            response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + WXPublicSettingUtil.appId + "&redirect_uri=http%3a%2f%2fm.chenlankeji.cn%2fwx%2fv1.0.0%2fwxLoginRegisterJoinMy&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect");
        }
        //已登录
        else {
            Integer user_id = Integer.valueOf(cookileUtil.getCookie_user_id(request, 2));
            Users users = this.usersService.selectById(user_id);
            if (users == null) {
                response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + WXPublicSettingUtil.appId + "&redirect_uri=http%3a%2f%2fm.chenlankeji.cn%2fwx%2fv1.0.0%2fwxLoginRegisterJoinMy&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect");
            } else {
                response.sendRedirect("http://m.chenlankeji.cn/wx/v1.0.0/my");
            }
        }
    }


    /*
     * 微信登录注册并进入我的首页
     * */
    @RequestMapping(value = "/wxLoginRegisterJoinMy", method = RequestMethod.GET)
    @ResponseBody
    public void wxLoginRegisterJoinMy(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "code", defaultValue = "", required = false) String code, @RequestParam(value = "state", defaultValue = "", required = false) String state) throws IOException {
        String data = HttpRequest.sendGet("https://api.weixin.qq.com/sns/oauth2/access_token", WechatUtil.obtainOpenIdParamts(code));
        JSONObject dataJson = JSONObject.fromObject(data);
        String openId = (String) dataJson.get("openid");
        Users users = this.usersService.selectByWxUnionid(openId);
        if (users == null) {
            users = new Users();
            users.setWx_unionid(openId);
            users.setNick_name("无");
            users.setHeader("default_header.jpg");
            MemberLevels memberLevels = this.memberLevelsService.selectDefaultLevel();
            if (memberLevels != null) {
                users.setMember_level_id(memberLevels.getId());
            }
            users.setRegister_time(new Date());
            this.usersService.userWXRegister(users);
            //生成用户二维码
            //二维码内容
            String qrcodeContent = "http://m.chenlankeji.cn/wx/v1.0.0/wxRecommentLogin?recommen_id=" + users.getId();
            String photoName = UUDUtil.getOrderIdByUUId();
            try {
                String url = QRCode.generateQRCode(qrcodeContent, photoName);
                users.setUser_qrcode_url(photoName + ".png");
                this.usersService.updateUserQrcode(users);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        CookileUtil cookileUtil = new CookileUtil();
        cookileUtil.saveCookie(response, users.getId().toString(), 2);
        String loginSession = LoginSessionCheckUtil.saveSessionId(users.getId(), 1, request, this.sessionIdsService);
        response.sendRedirect("http://m.chenlankeji.cn/wx/v1.0.0/my");
    }


    /*
     * 微信推荐注册
     * */
    @RequestMapping(value = "/wxRecommentLogin", method = RequestMethod.GET, params = {"recommen_id"})
    @ResponseBody
    public void wxRecommentLogin(HttpServletRequest request, HttpServletResponse response, Integer recommen_id) throws IOException {
        CookileUtil cookileUtil = new CookileUtil();
        String user_id_str = cookileUtil.getCookie_user_id(request, 2);
        //标记是否已登录(false-未登录,已登录)
        Boolean isLogin = false;
        if (user_id_str == null) {
            isLogin = false;
        } else {
            Integer user_id = Integer.parseInt(user_id_str);
            if (LoginSessionCheckUtil.wxPageCheckIsLogin(user_id, 1, this.sessionIdsService) == null) {
                isLogin = false;
            } else {
                isLogin = true;
            }
        }
        //未登录
        if (isLogin == false) {
            Users recommendUsers = this.usersService.selectById(recommen_id);
            if (recommendUsers == null) {
                response.sendRedirect("http://m.chenlankeji.cn/wx/v1.0.0/login");
            } else {
                response.sendRedirect("http://m.chenlankeji.cn/wx/v1.0.0/register?invitation_code=" + recommendUsers.getMobile());
            }

//            response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+ WXPublicSettingUtil.appId+"&redirect_uri=http%3a%2f%2fm.chenlankeji.cn%2fwx%2fv1.0.0%2fwxLoginRegisterJoinMy&response_type=code&scope=snsapi_userinfo&state="+recommen_id+"#wechat_redirect");
        }
        //已登录
        else {
            Integer user_id = Integer.valueOf(cookileUtil.getCookie_user_id(request, 2));
            Users users = this.usersService.selectById(user_id);
            if (users == null) {
                Users recommendUsers = this.usersService.selectById(recommen_id);
                if (recommendUsers == null) {
                    response.sendRedirect("http://m.chenlankeji.cn/wx/v1.0.0/login");
                } else {
                    response.sendRedirect("http://m.chenlankeji.cn/wx/v1.0.0/register?invitation_code=" + recommendUsers.getMobile());
                }
//                response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+ WXPublicSettingUtil.appId+"&redirect_uri=http%3a%2f%2fm.chenlankeji.cn%2fwx%2fv1.0.0%2fwxLoginRegisterJoinMy&response_type=code&scope=snsapi_userinfo&state="+recommen_id+"#wechat_redirect");
            } else {
                response.sendRedirect("http://m.chenlankeji.cn/wx/v1.0.0/my");
            }
        }
    }


    /*
     * 微信登录注册并进入我的首页
     * */
    @RequestMapping(value = "/wxRecommentLoginJoinMy", method = RequestMethod.GET)
    @ResponseBody
    public void wxRecommentLoginJoinMy(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "code", defaultValue = "", required = false) String code, @RequestParam(value = "state", defaultValue = "", required = false) String state) throws IOException {
        String data = HttpRequest.sendGet("https://api.weixin.qq.com/sns/oauth2/access_token", WechatUtil.obtainOpenIdParamts(code));
        JSONObject dataJson = JSONObject.fromObject(data);
        String openId = (String) dataJson.get("openid");
        Users users = this.usersService.selectByWxUnionid(openId);
        if (users == null) {
            users = new Users();
            users.setWx_unionid(openId);
            users.setNick_name("无");
            users.setHeader("default_header.jpg");
            users.setRecommend_user_id(Integer.parseInt(state));
            MemberLevels memberLevels = this.memberLevelsService.selectDefaultLevel();
            if (memberLevels != null) {
                users.setMember_level_id(memberLevels.getId());
            }
            users.setRegister_time(new Date());
            this.usersService.userWXRegister(users);
            //生成用户二维码
            //二维码内容
            String qrcodeContent = "http://m.chenlankeji.cn/wx/v1.0.0/wxRecommentLogin?recommen_id=" + users.getId();
            String photoName = UUDUtil.getOrderIdByUUId();
            try {
                String url = QRCode.generateQRCode(qrcodeContent, photoName);
                users.setUser_qrcode_url(photoName + ".png");
                this.usersService.updateUserQrcode(users);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        CookileUtil cookileUtil = new CookileUtil();
        cookileUtil.saveCookie(response, users.getId().toString(), 2);
        String loginSession = LoginSessionCheckUtil.saveSessionId(users.getId(), 1, request, this.sessionIdsService);
        response.sendRedirect("http://m.chenlankeji.cn/wx/v1.0.0/my");
    }


    /*
     * 网页端用户注册成功快捷登录
     * */
    @RequestMapping(value = "/pageRegisterSuccessLogin", method = RequestMethod.GET, params = {"user_id"})
    @ResponseBody
    public void pageRegisterSuccessLogin(HttpServletRequest request, HttpServletResponse response, Integer user_id) throws IOException {
        Users users = this.usersService.selectById(user_id);
        if (users == null) {
//            response.sendRedirect("http://m.chenlankeji.cn/wx/v1.0.0/login");
            response.sendRedirect("/property_system/wx/v1.0.0/login");
        } else {
            CookileUtil cookileUtil = new CookileUtil();
            cookileUtil.saveCookie(response, users.getId().toString(), 2);
            String loginSession = LoginSessionCheckUtil.saveSessionId(users.getId(), 1, request, this.sessionIdsService);
//            response.sendRedirect("http://m.chenlankeji.cn/wx/v1.0.0/my");
            response.sendRedirect("/property_system/wx/v1.0.0/my");
        }

    }


    /*
     * 获取用openid
     * */
    @RequestMapping(value = "/getOpenId", method = RequestMethod.GET)
    @ResponseBody
    public void getOpenId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CookileUtil cookileUtil = new CookileUtil();
        String user_id_str = cookileUtil.getCookie_user_id(request, 2);
        //标记是否已登录(false-未登录,已登录)
        Boolean isLogin = false;
        if (user_id_str == null) {
            isLogin = false;
        } else {
            Integer user_id = Integer.parseInt(user_id_str);
            if (LoginSessionCheckUtil.wxPageCheckIsLogin(user_id, 1, this.sessionIdsService) == null) {
                isLogin = false;
            } else {
                isLogin = true;
            }
        }
        //未登录
        if (isLogin == false) {
            response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + WXPublicSettingUtil.appId + "&redirect_uri=http%3a%2f%2fm.chenlankeji.cn%2fwx%2fv1.0.0%2fwxLoginRegisterJoinMy&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect");
        }
        //已登录
        else {
            Integer user_id = Integer.valueOf(cookileUtil.getCookie_user_id(request, 2));
            Users users = this.usersService.selectById(user_id);
            if (users == null) {
                response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + WXPublicSettingUtil.appId + "&redirect_uri=http%3a%2f%2fm.chenlankeji.cn%2fwx%2fv1.0.0%2fwxLoginRegisterJoinMy&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect");
            } else {
                response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + WXPublicSettingUtil.appId + "&redirect_uri=http%3a%2f%2fm.chenlankeji.cn%2fwx%2fv1.0.0%2fgetOpenIdAction&response_type=code&scope=snsapi_userinfo&state=" + user_id + "#wechat_redirect");
            }
        }
    }


    /*
     * 微信登录注册并进入我的首页
     * */
    @RequestMapping(value = "/getOpenIdAction", method = RequestMethod.GET)
    @ResponseBody
    public void getOpenIdAction(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "code", defaultValue = "", required = false) String code, @RequestParam(value = "state", defaultValue = "", required = false) String state) throws IOException {
        String data = HttpRequest.sendGet("https://api.weixin.qq.com/sns/oauth2/access_token", WechatUtil.obtainOpenIdParamts(code));
        JSONObject dataJson = JSONObject.fromObject(data);
        String openId = (String) dataJson.get("openid");
        Users users = this.usersService.selectById(Integer.parseInt(state));
        if (users == null) {
            response.sendRedirect("http://m.chenlankeji.cn/wx/v1.0.0/login");
        } else {
            users.setWx_unionid(openId);
            this.usersService.updateUserWXOpenId(users);
            response.sendRedirect("http://m.chenlankeji.cn/wx/v1.0.0/integralRecharge?type=0");
        }
    }

    /*
     * 关联微信号
     * */
    @RequestMapping(value = "/associationWX", method = RequestMethod.GET)
    @ResponseBody
    public void associationWX(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CookileUtil cookileUtil = new CookileUtil();
        String user_id_str = cookileUtil.getCookie_user_id(request, 2);
        //标记是否已登录(false-未登录,已登录)
        Boolean isLogin = false;
        if (user_id_str == null) {
            isLogin = false;
        } else {
            Integer user_id = Integer.parseInt(user_id_str);
            if (LoginSessionCheckUtil.wxPageCheckIsLogin(user_id, 1, this.sessionIdsService) == null) {
                isLogin = false;
            } else {
                isLogin = true;
            }
        }
        //未登录
        if (isLogin == false) {
            response.sendRedirect("http://m.chenlankeji.cn/wx/v1.0.0/my");
        }
        //已登录
        else {
            Integer user_id = Integer.valueOf(cookileUtil.getCookie_user_id(request, 2));
            Users users = this.usersService.selectById(user_id);
            if (users == null) {
                response.sendRedirect("http://m.chenlankeji.cn/wx/v1.0.0/my");
            } else {
                response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + WXPublicSettingUtil.appId + "&redirect_uri=http%3a%2f%2fm.chenlankeji.cn%2fwx%2fv1.0.0%2fjoinAssociationWX&response_type=code&scope=snsapi_userinfo&state=" + user_id + "#wechat_redirect");
            }
        }
    }


    /*
     * 微信登录注册并进入我的首页
     * */
    @RequestMapping(value = "/joinAssociationWX", method = RequestMethod.GET)
    @ResponseBody
    public void joinAssociationWX(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "code", defaultValue = "", required = false) String code, @RequestParam(value = "state", defaultValue = "", required = false) String state) throws IOException {
        String data = HttpRequest.sendGet("https://api.weixin.qq.com/sns/oauth2/access_token", WechatUtil.obtainOpenIdParamts(code));
        JSONObject dataJson = JSONObject.fromObject(data);
        String openId = (String) dataJson.get("openid");
        Users users = this.usersService.selectById(Integer.parseInt(state));
        if (users == null) {
            response.sendRedirect("http://m.chenlankeji.cn/wx/v1.0.0/my");
        } else {
            users.setWx_unionid(openId);
            this.usersService.updateUserWXOpenId(users);
            response.sendRedirect("http://m.chenlankeji.cn/wx/v1.0.0/accountAssociatio");
        }
    }

}
