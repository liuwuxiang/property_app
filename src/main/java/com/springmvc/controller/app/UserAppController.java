package com.springmvc.controller.app;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.springmvc.controller.upload.ImageToolsController;
import com.springmvc.entity.*;
import com.springmvc.service.impl.*;
import com.springmvc.utils.*;
import com.springmvc.utils.mail.SendEmailUtil;
import com.springmvc.utils.qrCode.QRCode;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/app/v1.0.0")
public class UserAppController {
    @Resource
    private UsersService usersService;
    @Resource
    private MemberLevelsService memberLevelsService;
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private CodesService codesService;
    @Resource
    private SilverCoinDetailedService silverCoinDetailedService;
    @Resource
    private SystemMessagesService systemMessagesService;
    @Resource
    private UserIdCardAuthenticationService userIdCardAuthenticationService;
    @Resource
    private UserOpenCardsService userOpenCardsService;
    @Resource
    private WnkSendIntegralUserService wnkSendIntegralUserService;
    @Resource
    private WnkIntegralUserServer wnkIntegralUserServer;
    @Resource
    private DoingSpreadUserReadRecordService doingSpreadUserReadRecordService;
    @Resource
    private DoingsSpreadService doingsSpreadService;
    @Resource
    private WnkBusinessAccountService wnkBusinessAccountService;
    @Resource
    private WnkStoreInformationService wnkStoreInformationService;


    // 用户账号密码登录
    @RequestMapping(value = "/userAndPwdLogin", method = RequestMethod.POST,params = {"mobile","login_pwd"})
    @ResponseBody
    public Result userAndPwdLogin(HttpServletRequest request,
                                  HttpServletResponse response,
                                  String mobile,
                                  String login_pwd){
        Result result = new Result();
        try {
            Users users = this.usersService.selectByMobileAndLoginPWD(mobile,login_pwd);
            if (users == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("账号或密码不正确");
            }
            else{
                MemberLevels memberLevels = this.memberLevelsService.selectById(users.getMember_level_id());
                Users recommendUsers = null;
                WnkBusinessAccount wnkBusinessAccount = null;
                if (users.getRecommend_user_id() != -1 && users.getRecommend_type() == 0){
                    recommendUsers = this.usersService.selectById(users.getRecommend_user_id());
                }
                else if (users.getRecommend_user_id() != -1 && users.getRecommend_type() == 1){
                    wnkBusinessAccount = this.wnkBusinessAccountService.selectById(users.getRecommend_user_id());
                }

                Map<Object,Object> userMap = new HashMap<Object,Object>();
                userMap.put("user_id",users.getId());
                userMap.put("header",ImageToolsController.imageShowURL+"?imageid="+users.getHeader());
                userMap.put("nick_name",users.getNick_name());
                userMap.put("sex",users.getSex());
                if (users.getRecommend_type() == 0 && recommendUsers != null){
                    if (recommendUsers != null){
                        userMap.put("recommend_mobile",users.getRecommend_user_id()==-1?"无推荐人":recommendUsers.getMobile());
                    }
                    else{
                        userMap.put("recommend_mobile","无推荐人");
                    }
                }
                else{
                    if (wnkBusinessAccount != null){
                        userMap.put("recommend_mobile",users.getRecommend_user_id()==-1?"无推荐人":wnkBusinessAccount.getMobile());
                    }
                    else{
                        userMap.put("recommend_mobile","无推荐人");
                    }
                }

                userMap.put("level_icon",memberLevels==null?"": ImageToolsController.imageShowURL+"?imageid="+memberLevels.getIcon_url());
                userMap.put("level_name",memberLevels==null?"":memberLevels.getName());
                userMap.put("consumption_integral",users.getConsumption_integral());
                userMap.put("general_integral",users.getGeneral_integral());
                userMap.put("team_members_number",0);
                userMap.put("mobile",users.getMobile());
                userMap.put("email",users.getEmail());
                userMap.put("is_microfinance",users.getIs_microfinance());
                userMap.put("login_session",LoginSessionCheckUtil.saveSessionId(users.getId(),1,request,this.sessionIdsService));
                result.setData(userMap);
                result.setStatus(Result.SUCCESS);
                result.setMsg("登录成功");
                CookileUtil cookileUtil = new CookileUtil();
                String cookieUserId = cookileUtil.getCookie_user_id(request,2);
                if (cookieUserId == null){
                    cookileUtil.saveCookie(response,users.getId().toString(),2);
                }

            }
        }catch (Exception e){
            //result.setData("");
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("登录失败");
        }
        return result;
    }

    // 用户微信第三方登录
    @RequestMapping(value = "/userWxLogin", method = RequestMethod.POST,params = {"wx_unionid"})
    @ResponseBody
    public Result userWxLogin(HttpServletRequest request, HttpServletResponse response, String wx_unionid){
        Result result = new Result();
        try {
            Users users = this.usersService.selectByWxUnionid(wx_unionid);
            if (users == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("此用户暂未注册");
            }
            else{
                MemberLevels memberLevels = this.memberLevelsService.selectById(users.getMember_level_id());
                Users recommendUsers = null;
                WnkBusinessAccount wnkBusinessAccount = null;
                if (users.getRecommend_user_id() != -1 && users.getRecommend_type() == 0){
                    recommendUsers = this.usersService.selectById(users.getRecommend_user_id());
                }
                else if (users.getRecommend_user_id() != -1 && users.getRecommend_type() == 1){
                    wnkBusinessAccount = this.wnkBusinessAccountService.selectById(users.getRecommend_user_id());
                }
                Map<Object,Object> userMap = new HashMap<Object,Object>();
                userMap.put("user_id",users.getId());
                userMap.put("header",ImageToolsController.imageShowURL+"?imageid="+users.getHeader());
                userMap.put("nick_name",users.getNick_name());
                userMap.put("sex",users.getSex());
                if (users.getRecommend_type() == 0){
                    if (recommendUsers != null){
                        userMap.put("recommend_mobile",users.getRecommend_user_id()==-1?"无推荐人":recommendUsers.getMobile());
                    }
                    else{
                        userMap.put("recommend_mobile","无推荐人");
                    }
                }
                else{
                    if (wnkBusinessAccount != null){
                        userMap.put("recommend_mobile",users.getRecommend_user_id()==-1?"无推荐人":wnkBusinessAccount.getMobile());
                    }
                    else{
                        userMap.put("recommend_mobile","无推荐人");
                    }
                }
                userMap.put("level_icon",memberLevels==null?"": ImageToolsController.imageShowURL+"?imageid="+memberLevels.getIcon_url());
                userMap.put("level_name",memberLevels==null?"":memberLevels.getName());
                userMap.put("consumption_integral",users.getConsumption_integral());
                userMap.put("general_integral",users.getGeneral_integral());
                userMap.put("team_members_number",0);
                userMap.put("mobile",users.getMobile());
                userMap.put("email",users.getEmail());
                userMap.put("is_microfinance",users.getIs_microfinance());
                userMap.put("login_session",LoginSessionCheckUtil.saveSessionId(users.getId(),1,request,this.sessionIdsService));
                result.setData(userMap);
                result.setStatus(Result.SUCCESS);
                result.setMsg("登录成功");
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("登录失败");
        }
        return result;
    }

    // 手机号+短信验证码登录
    @RequestMapping(value = "/userMobileAndCodeLogin", method = RequestMethod.POST,params = {"mobile","code"})
    @ResponseBody
    public Result userMobileAndCodeLogin(HttpServletRequest request, HttpServletResponse response, String mobile,String code){
        Result result = new Result();
        try {
            Users users = this.usersService.selectByMobile(mobile);
            if (users == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("此手机号未注册");
            }
            else{
                List<Map<Object,Object>> list = this.codesService.selectMobileCode(mobile,code,1);
                if (list.size() > 0){
                    Integer code_id = -1;
                    for (int index = 0;index < list.size();index ++){
                        Map<Object,Object> map = list.get(index);
                        if ((Integer)map.get("state") == 0){
                            code_id = (Integer)map.get("id");
                        }
                    }
                    if (code_id != -1){
                        int state = this.codesService.updateCodesState(code_id);
                        if (state >= 1){
                            MemberLevels memberLevels = this.memberLevelsService.selectById(users.getMember_level_id());
                            Users recommendUsers = null;
                            WnkBusinessAccount wnkBusinessAccount = null;
                            if (users.getRecommend_user_id() != -1 && users.getRecommend_type() == 0){
                                recommendUsers = this.usersService.selectById(users.getRecommend_user_id());
                            }
                            else if (users.getRecommend_user_id() != -1 && users.getRecommend_type() == 1){
                                wnkBusinessAccount = this.wnkBusinessAccountService.selectById(users.getRecommend_user_id());
                            }
                            Map<Object,Object> userMap = new HashMap<Object,Object>();
                            userMap.put("user_id",users.getId());
                            userMap.put("header",ImageToolsController.imageShowURL+"?imageid="+users.getHeader());
                            userMap.put("nick_name",users.getNick_name());
                            userMap.put("sex",users.getSex());
                            if (users.getRecommend_type() == 0 && recommendUsers != null){
                                if (recommendUsers != null){
                                    userMap.put("recommend_mobile",users.getRecommend_user_id()==-1?"无推荐人":recommendUsers.getMobile());
                                }
                                else{
                                    userMap.put("recommend_mobile","无推荐人");
                                }
                            }
                            else{
                                if (wnkBusinessAccount != null){
                                    userMap.put("recommend_mobile",users.getRecommend_user_id()==-1?"无推荐人":wnkBusinessAccount.getMobile());
                                }
                                else{
                                    userMap.put("recommend_mobile","无推荐人");
                                }
                            }
                            userMap.put("level_icon",memberLevels==null?"": ImageToolsController.imageShowURL+"?imageid="+memberLevels.getIcon_url());
                            userMap.put("level_name",memberLevels==null?"":memberLevels.getName());
                            userMap.put("consumption_integral",users.getConsumption_integral());
                            userMap.put("general_integral",users.getGeneral_integral());
                            userMap.put("team_members_number",0);
                            userMap.put("mobile",users.getMobile());
                            userMap.put("email",users.getEmail());
                            userMap.put("is_microfinance",users.getIs_microfinance());
                            userMap.put("login_session",LoginSessionCheckUtil.saveSessionId(users.getId(),1,request,this.sessionIdsService));
                            result.setData(userMap);
                            result.setStatus(Result.SUCCESS);
                            result.setMsg("登录成功");
                            CookileUtil cookileUtil = new CookileUtil();
                            cookileUtil.saveCookie(response,users.getId().toString(),2);
                        }
                        else{
                            result.setStatus(Result.FAIL);
                            result.setMsg("验证失败");
                        }
                    }
                    else{
                        result.setStatus(Result.FAIL);
                        result.setMsg("验证码无效");
                    }
                }
                else{
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("验证码不存在");
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("登录失败");
        }
        return result;
    }

    // 获取短信验证码(type=0:注册获取验证码,type=1:登录获取验证码,type=2:修改手机号获取验证码,type=3:修改登录密码获取验证码,type=4:修改支付密码获取验证码,type=5:修改密保问题获取验证码,type=6:手机号绑定关联手机号，type=7:找回密码)
    @RequestMapping(value = "/getMobileCode", method = RequestMethod.POST,params = {"mobile","type"})
    @ResponseBody
    public Result getMobileCode(HttpServletRequest request, HttpServletResponse response, String mobile,Integer type){
        Result result = new Result();
        try {
            if (type != 0 && type != 1 && type != 7 && LoginSessionCheckUtil.checkIsLogin(null,1,request,response,sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂时未登录");
            }
            else{
                Users users = this.usersService.selectByMobile(mobile);
                if (type == 0 && users != null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此手机号已存在");
                }
                else{
                    String uuid = UUDUtil.getOrderIdByUUId();
                    String code = uuid.substring(uuid.length()-6,uuid.length());
                    Codes codes = new Codes();
                    codes.setSend_number(mobile);
                    codes.setCode(code);
                    codes.setSend_time(new Date());
                    codes.setMake_type(type);
                    SendSmsResponse sendSmsResponse = null;
                    if (type == 7 || type == 3){
                        sendSmsResponse = MobileCodeUtil.sendBusinessGetLoginPWDCodeSms(mobile,code);
                    }
                    else if(type == 4){
                        sendSmsResponse = MobileCodeUtil.sendUserSetPayPWDCodeSms(mobile,code);
                    }
                    else{
                        sendSmsResponse = MobileCodeUtil.sendSms(code,mobile);
                    }
                    if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")){
                        Integer state = this.codesService.addMobileCode(codes);
                        if (state >= 1){
                            Map<Object,Object> map = new HashMap<Object,Object>();
                            map.put("code",code);
                            result.setData(map);
                            result.setStatus(Result.SUCCESS);
                            result.setMsg("发送成功");
                        }
                        else{
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("发送失败");
                        }

                    }
                    else{
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg(sendSmsResponse.getMessage());
                    }
                }

            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("发送失败");
        }
        return result;
    }


    // 手机号+短信验证码+登录密码注册
    @RequestMapping(value = "/userMobileAndCodeRegister", method = RequestMethod.POST,params = {"mobile","code","login_pwd","invitation_mobile","type"})
    @ResponseBody
    public Result userMobileAndCodeRegister(HttpServletRequest request, HttpServletResponse response, String mobile,String code,String login_pwd,String invitation_mobile,Integer type){
        Result result = new Result();
        try {
            Users users = this.usersService.selectByMobile(mobile);
            if (users != null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("此手机号已被注册");
            }
            else if (mobile.equals("") || mobile.length() != 11){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("请输入合法的手机号");
            }
            else if (login_pwd.equals("") || login_pwd.length() < 6){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("登录密码不可小于6位");
            }
            else{
                Integer recommend_id = -1;
                if(type == 0){
                    Users recommendUsers = this.usersService.selectByMobile(invitation_mobile);
                    if (recommendUsers != null){
                        recommend_id = recommendUsers.getId();
                    }
                    else{
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此邀请码不存在");

                        return result;
                    }
                }
                else{
                    WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectByMobile(invitation_mobile);
                    if (wnkBusinessAccount != null){
                        recommend_id = wnkBusinessAccount.getId();
                    }
                    else{
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此邀请码不存在");

                        return result;
                    }
                }

                    List<Map<Object,Object>> list = this.codesService.selectMobileCode(mobile,code,0);
                    if (list.size() > 0){
                        Integer code_id = -1;
                        for (int index = 0;index < list.size();index ++){
                            Map<Object,Object> map = list.get(index);
                            if ((Integer)map.get("state") == 0){
                                code_id = (Integer)map.get("id");
                            }
                        }
                        if (code_id != -1){
                            int state = this.codesService.updateCodesState(code_id);
                            if (state >= 1){
                                users = new Users();
                                users.setMobile(mobile);
                                users.setLogin_pwd(login_pwd);
                                users.setNick_name("无");
//                                users.setHeader("default_header.jpg");
//                                users.setSex(2);
                                MemberLevels memberLevels = this.memberLevelsService.selectDefaultLevel();
                                if (memberLevels != null){
                                    users.setMember_level_id(memberLevels.getId());
                                }
                                users.setRegister_time(new Date());
                                users.setRecommend_user_id(recommend_id);
                                users.setRecommend_type(type);
                                int registerState = this.usersService.userMobileRegister(users);
                                if (registerState >= 1){
                                    Map<Object,Object> userMap = new HashMap<Object,Object>();
                                    userMap.put("user_id",users.getId());
                                    result.setData(userMap);
                                    result.setStatus(Result.SUCCESS);
                                    result.setMsg("注册成功");
                                    //生成用户二维码
                                    //二维码内容
                                    String qrcodeContent = "http://m.chenlankeji.cn/wx/v1.0.0/wxRecommentLogin?recommen_id="+users.getId();
                                    String photoName = UUDUtil.getOrderIdByUUId();
                                    //万能卡二维码
                                    Map<Object,Object> wnkQrcodeMap = new HashMap<Object,Object>();
                                    wnkQrcodeMap.put("user_id",users.getId());
                                    wnkQrcodeMap.put("type",1);
                                    try {
                                        JSONObject json = JSONObject.fromObject(wnkQrcodeMap);
                                        String wnkQrcodeName = UUDUtil.getOrderIdByUUId();
                                        String wnkQrcodeUrl = QRCode.generateQRCode(json.toString(),wnkQrcodeName);
                                        String url = QRCode.generateQRCode(qrcodeContent,photoName);
                                        users.setUser_qrcode_url(photoName+".png");
                                        users.setWnk_qrcode(wnkQrcodeName+".png");
                                        this.usersService.updateUserQrcode(users);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    this.systemMessagesService.addRecommendSuccessMessgae(users.getId(),recommend_id);
                                    if (type == 1){
                                        WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(recommend_id);
                                        if (wnkStoreInformation != null){
                                            if (wnkStoreInformation.getGetui_appid() != null && !wnkStoreInformation.getGetui_appid().equals("")){
                                                String pushTitle = "团队新成员加入通知";
                                                String pushContent = users.getMobile()+"注册为会员，您的团队又增加一位成员啦！";
                                                GeTuiBusinessUtil.pushUser(wnkStoreInformation.getGetui_appid(),pushTitle,pushContent);
                                            }
                                        }
                                    }
                                }
                                else{
                                    result.setData("");
                                    result.setStatus(Result.FAIL);
                                    result.setMsg("注册失败");
                                }
                            }
                            else{
                                result.setStatus(Result.FAIL);
                                result.setMsg("验证失败");
                            }
                        }
                        else{
                            result.setStatus(Result.FAIL);
                            result.setMsg("验证码无效");
                        }
                    }

            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("注册失败");
        }
        return result;
    }


    // 手机号+短信验证码+登录密码注册
    @RequestMapping(value = "/userMobileAndCodeRegisterTwo", method = RequestMethod.POST,params = {"mobile","code","login_pwd","invitation_mobile","type"})
    @ResponseBody
    public Result userMobileAndCodeRegisterTwo(HttpServletRequest request, HttpServletResponse response, String mobile,String code,String login_pwd,String invitation_mobile,Integer type){
        Result result = new Result();
        try {
            Users users = this.usersService.selectByMobile(mobile);
            if (users != null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("此手机号已被注册");
            }
            else if (mobile.equals("") || mobile.length() != 11){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("请输入合法的手机号");
            }
            else if (login_pwd.equals("") || login_pwd.length() < 6){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("登录密码不可小于6位");
            }
            else{
                Integer recommend_id = -1;
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectByMobile(invitation_mobile);
                if (wnkBusinessAccount != null){
                    recommend_id = wnkBusinessAccount.getId();
                    type = 1;
                }
                if(recommend_id == -1){
                    Users recommendUsers = this.usersService.selectByMobile(invitation_mobile);
                    if (recommendUsers != null){
                        recommend_id = recommendUsers.getId();
                        type = 0;
                    }
                }
                if(recommend_id == -1){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此邀请码不存在");

                    return result;
                }

                List<Map<Object,Object>> list = this.codesService.selectMobileCode(mobile,code,0);
                if (list.size() > 0){
                    Integer code_id = -1;
                    for (int index = 0;index < list.size();index ++){
                        Map<Object,Object> map = list.get(index);
                        if ((Integer)map.get("state") == 0){
                            code_id = (Integer)map.get("id");
                        }
                    }
                    if (code_id != -1){
                        int state = this.codesService.updateCodesState(code_id);
                        if (state >= 1){
                            users = new Users();
                            users.setMobile(mobile);
                            users.setLogin_pwd(login_pwd);
                            users.setNick_name("无");
//                                users.setHeader("default_header.jpg");
//                                users.setSex(2);
                            MemberLevels memberLevels = this.memberLevelsService.selectDefaultLevel();
                            if (memberLevels != null){
                                users.setMember_level_id(memberLevels.getId());
                            }
                            users.setRegister_time(new Date());
                            users.setRecommend_user_id(recommend_id);
                            users.setRecommend_type(type);
                            int registerState = this.usersService.userMobileRegister(users);
                            if (registerState >= 1){
                                Map<Object,Object> userMap = new HashMap<Object,Object>();
                                userMap.put("user_id",users.getId());
                                result.setData(userMap);
                                result.setStatus(Result.SUCCESS);
                                result.setMsg("注册成功");
                                //生成用户二维码
                                //二维码内容
                                String qrcodeContent = "http://m.chenlankeji.cn/wx/v1.0.0/wxRecommentLogin?recommen_id="+users.getId();
                                String photoName = UUDUtil.getOrderIdByUUId();
                                //万能卡二维码
                                Map<Object,Object> wnkQrcodeMap = new HashMap<Object,Object>();
                                wnkQrcodeMap.put("user_id",users.getId());
                                wnkQrcodeMap.put("type",1);
                                try {
                                    JSONObject json = JSONObject.fromObject(wnkQrcodeMap);
                                    String wnkQrcodeName = UUDUtil.getOrderIdByUUId();
                                    String wnkQrcodeUrl = QRCode.generateQRCode(json.toString(),wnkQrcodeName);
                                    String url = QRCode.generateQRCode(qrcodeContent,photoName);
                                    users.setUser_qrcode_url(photoName+".png");
                                    users.setWnk_qrcode(wnkQrcodeName+".png");
                                    this.usersService.updateUserQrcode(users);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                this.systemMessagesService.addRecommendSuccessMessgae(users.getId(),recommend_id);
                                if (type == 1){
                                    WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(recommend_id);
                                    if (wnkStoreInformation != null){
                                        if (wnkStoreInformation.getGetui_appid() != null && !wnkStoreInformation.getGetui_appid().equals("")){
                                            String pushTitle = "团队新成员加入通知";
                                            String pushContent = users.getMobile()+"注册为会员，您的团队又增加一位成员啦！";
                                            GeTuiBusinessUtil.pushUser(wnkStoreInformation.getGetui_appid(),pushTitle,pushContent);
                                        }
                                    }
                                }
                            }
                            else{
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("注册失败");
                            }
                        }
                        else{
                            result.setStatus(Result.FAIL);
                            result.setMsg("验证失败");
                        }
                    }
                    else{
                        result.setStatus(Result.FAIL);
                        result.setMsg("验证码无效");
                    }
                }

            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("注册失败");
        }
        return result;
    }


    // 通过user_id获取用户基础信息
    @RequestMapping(value = "/getUserBaseInformation", method = RequestMethod.POST,params = {"user_id"})
    @ResponseBody
    public Result getUserBaseInformation(HttpServletRequest request, HttpServletResponse response, Integer user_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }else {
                Users users = this.usersService.selectById(user_id);
                if (users == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                }
                else{
                    MemberLevels memberLevels = this.memberLevelsService.selectById(users.getMember_level_id());
                    Users recommendUsers = null;
                    WnkBusinessAccount wnkBusinessAccount = null;
                    if (users.getRecommend_user_id() != -1 && users.getRecommend_type() == 0){
                        recommendUsers = this.usersService.selectById(users.getRecommend_user_id());
                    }
                    else if (users.getRecommend_user_id() != -1 && users.getRecommend_type() == 1){
                        wnkBusinessAccount = this.wnkBusinessAccountService.selectById(users.getRecommend_user_id());
                    }
                    UserIdCardAuthentication userIdCardAuthentication = this.userIdCardAuthenticationService.selectAuthenticationByUserId(user_id);
                    Map<Object,Object> userMap = new HashMap<Object,Object>();
                    userMap.put("user_id",users.getId());
                    userMap.put("header",ImageToolsController.imageShowURL+"?imageid="+users.getHeader());
                    userMap.put("nick_name",users.getNick_name());
                    userMap.put("sex",users.getSex());
                    if (users.getRecommend_type() == 0){
                        if (recommendUsers != null){
                            userMap.put("recommend_mobile",users.getRecommend_user_id()==-1?"无推荐人":recommendUsers.getMobile());
                        }
                        else{
                            userMap.put("recommend_mobile","无推荐人");
                        }
                    }
                    else{
                        if (wnkBusinessAccount != null){
                            userMap.put("recommend_mobile",users.getRecommend_user_id()==-1?"无推荐人":wnkBusinessAccount.getMobile());
                        }
                        else{
                            userMap.put("recommend_mobile","无推荐人");
                        }
                    }
                    userMap.put("level_icon",memberLevels==null?"": ImageToolsController.imageShowURL+"?imageid="+memberLevels.getIcon_url());
                    userMap.put("level_name",memberLevels==null?"":memberLevels.getName());
                    userMap.put("consumption_integral",users.getConsumption_integral());
                    userMap.put("general_integral",users.getGeneral_integral());
                    userMap.put("team_members_number",this.usersService.selectUserByRecommendUserId(user_id).size());
                    userMap.put("mobile",users.getMobile());
                    userMap.put("email",users.getEmail());
                    userMap.put("is_microfinance",users.getIs_microfinance());
                    userMap.put("user_integral",users.getUser_integral());
                    userMap.put("my_rose",users.getSilver_coin_balance());
                    // 我的积分
                    userMap.put("user_integral",users.getUser_integral());
                    if (userIdCardAuthentication == null){
                        //未认证
                        userMap.put("id_card_state",-1);
                    }
                    else if (userIdCardAuthentication.getState() == 0 || userIdCardAuthentication.getState() == 1){
                        //已提交认证
                        userMap.put("id_card_state",0);
                    }
                    if (users.getMember_card_level() == -1){
                        userMap.put("member_card_name","普通会员");
                        userMap.put("wnk_state",-1);
                    }
                    else if (users.getMember_card_level() == 0){
                        userMap.put("member_card_name","银卡会员");
                        userMap.put("wnk_state",0);
                    }
                    else if (users.getMember_card_level() == 1){
                        userMap.put("member_card_name","金卡会员");
                        userMap.put("wnk_state",0);
                    }
                    if (users.getPay_pwd() == null || users.getPay_pwd().equals("")){
                        userMap.put("is_pay_state",0);
                    }
                    else{
                        userMap.put("is_pay_state",1);
                    }
                    Double send_integral = this.wnkSendIntegralUserService.selectUserCountIntegralByUserId(user_id);
                    if (send_integral == null){
                        send_integral = 0.00;
                    }
                    userMap.put("send_integral",send_integral);
                    Integer business_integral = this.wnkIntegralUserServer.selectUserCountIntegralByUserId(user_id);
                    if (business_integral == null){
                        business_integral = 0;
                    }

                    //计算用户未读消息条数STR
                    Integer no_read_message_count = 0;
                    List<Map<Object,Object>> systemMessageList = this.systemMessagesService.selectUserAllMessage(user_id);
                    //所有会员和商家均可查看的推广消息
                    List<Map<Object,Object>> allUserCanLookList = this.doingsSpreadService.selectNotDownTextMessageAndExaminePassJurisdictionForAllUserAndBusinessOrUser();
                    //只有商家会员可查看的推广消息
                    List<Map<Object,Object>> businessMemeberCanLookList = null;
                    if (users.getRecommend_type() == 1 && users.getRecommend_user_id() != -1){
                        businessMemeberCanLookList = this.doingsSpreadService.selectNotDownTextMessageAndExaminePassJurisdictionForBusinessMember(users.getRecommend_user_id());
                        for (Integer index = 0;index < businessMemeberCanLookList.size();index++){
                            allUserCanLookList.add(businessMemeberCanLookList.get(index));
                        }
                    }
                    for (Integer index = 0;index<systemMessageList.size();index++){
                        Map<Object,Object> map = systemMessageList.get(index);
                        Integer read_state = (Integer) map.get("read_status");
                        if (read_state == 0){
                            no_read_message_count++;
                        }
                    }
                    for (Integer index = 0;index<allUserCanLookList.size();index++){
                        Map<Object,Object> map = allUserCanLookList.get(index);
                        Long message_id = (Long) map.get("id");
                        DoingSpreadUserReadRecord doingSpreadUserReadRecord = this.doingSpreadUserReadRecordService.selectByUserIdAndMessageId(user_id,message_id.intValue());
                        if (doingSpreadUserReadRecord == null){
                            no_read_message_count++;
                        }
                    }
                    userMap.put("no_read_message_count",no_read_message_count);
                    userMap.put("business_integral",business_integral);
                    result.setData(userMap);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("获取成功");
                }
            }
        }catch (Exception e){
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }

    // 通过user_id修改用户昵称
    @RequestMapping(value = "/setUserNickName", method = RequestMethod.POST,params = {"user_id","nick_name"})
    @ResponseBody
    public Result setUserNickName(HttpServletRequest request, HttpServletResponse response, Integer user_id,String nick_name){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }else {
                Users users = this.usersService.selectById(user_id);
                if (users == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                }
                else if (nick_name.equals("")){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("请输入用户昵称");
                }
                else{
                    users.setNick_name(nick_name);
                    int updateState = this.usersService.updateUserNickName(users);
                    if (updateState >= 1){
                        result.setData("");
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("修改成功");
                    }
                    else{
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("修改失败");
                    }
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("修改失败");
        }
        return result;
    }

    // 修改用户手机号(mobile-用户新手机号，code-验证码)
    @RequestMapping(value = "/setUserMobile", method = RequestMethod.POST,params = {"user_id","mobile","code"})
    @ResponseBody
    public Result setUserMobile(HttpServletRequest request, HttpServletResponse response, Integer user_id,String mobile,String code){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }else {
                Users users = this.usersService.selectById(user_id);
                if (users == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                }
                else if (mobile.equals("")){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("请输入新手机号");
                }
                else if (code.equals("")){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("请输入验证码");
                }
                else{
                    List<Map<Object,Object>> list = this.codesService.selectMobileCode(mobile,code,2);
                    if (list.size() > 0){
                        Integer code_id = -1;
                        for (int index = 0;index < list.size();index ++){
                            Map<Object,Object> map = list.get(index);
                            if ((Integer)map.get("state") == 0){
                                code_id = (Integer)map.get("id");
                            }
                        }
                        if (code_id != -1){
                            int state = this.codesService.updateCodesState(code_id);
                            if (state >= 1){
                                users.setMobile(mobile);
                                int updateState = this.usersService.updateUserMobile(users);
                                if (updateState >= 1){
                                    result.setData("");
                                    result.setStatus(Result.SUCCESS);
                                    result.setMsg("修改成功");
                                }
                                else{
                                    result.setData("");
                                    result.setStatus(Result.FAIL);
                                    result.setMsg("修改失败");
                                }
                            }
                            else{
                                result.setStatus(Result.FAIL);
                                result.setMsg("验证失败");
                            }
                        }
                        else{
                            result.setStatus(Result.FAIL);
                            result.setMsg("验证码无效");
                        }
                    }
                    else{
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("验证码不存在");
                    }
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("修改失败");
        }
        return result;
    }

    // 修改用户邮箱(email-用户新邮箱号，code-验证码)
    @RequestMapping(value = "/setUserEmail", method = RequestMethod.POST,params = {"user_id","email","code"})
    @ResponseBody
    public Result setUserEmail(HttpServletRequest request, HttpServletResponse response, Integer user_id,String email,String code){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }else {
                Users users = this.usersService.selectById(user_id);
                if (users == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                }
                else if (email.equals("")){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("请输入新邮箱号");
                }
                else if (code.equals("")){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("请输入验证码");
                }
                else{
                    List<Map<Object,Object>> list = this.codesService.selectEmailCode(email,code);
                    if (list.size() > 0){
                        Integer code_id = -1;
                        for (int index = 0;index < list.size();index ++){
                            Map<Object,Object> map = list.get(index);
                            if ((Integer)map.get("state") == 0){
                                code_id = (Integer)map.get("id");
                            }
                        }
                        if (code_id != -1){
                            int state = this.codesService.updateCodesState(code_id);
                            if (state >= 1){
                                users.setEmail(email);
                                int updateState = this.usersService.updateUserEmail(users);
                                if (updateState >= 1){
                                    result.setData("");
                                    result.setStatus(Result.SUCCESS);
                                    result.setMsg("修改成功");
                                }
                                else{
                                    result.setData("");
                                    result.setStatus(Result.FAIL);
                                    result.setMsg("修改失败");
                                }
                            }
                            else{
                                result.setStatus(Result.FAIL);
                                result.setMsg("验证失败");
                            }
                        }
                        else{
                            result.setStatus(Result.FAIL);
                            result.setMsg("验证码无效");
                        }
                    }
                    else{
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("验证码不存在");
                    }
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("修改失败");
        }
        return result;
    }

    // 修改用户性别(sex-性别(0-0-男,1-女,2-保密))
    @RequestMapping(value = "/setUserSex", method = RequestMethod.POST,params = {"user_id","sex"})
    @ResponseBody
    public Result setUserSex(HttpServletRequest request, HttpServletResponse response, Integer user_id,Integer sex){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }else {
                Users users = this.usersService.selectById(user_id);
                if (users == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                }
                else if (sex != 0 && sex != 1 && sex != 2){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("数据不合法");
                }
                else{
                    users.setSex(sex);
                    int updateState = this.usersService.updateUserSex(users);
                    if (updateState >= 1){
                        result.setData("");
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("修改成功");
                    }
                    else{
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("修改失败");
                    }
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("修改失败");
        }
        return result;
    }


    // 获取用户二维码信息
    @RequestMapping(value = "/getUserQrcode", method = RequestMethod.POST,params = {"user_id"})
    @ResponseBody
    public Result getUserQrcode(HttpServletRequest request, HttpServletResponse response, Integer user_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }else {
                Users users = this.usersService.selectById(user_id);
                if (users == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                }
                else{
                    Map<Object,Object> qrcodeMap = new HashMap<Object,Object>();
                    qrcodeMap.put("user_header",ImageToolsController.imageShowURL+"?imageid="+users.getHeader());
                    qrcodeMap.put("nick_name",users.getNick_name());
                    qrcodeMap.put("sex",users.getSex());
                    qrcodeMap.put("mobile",users.getMobile());
                    qrcodeMap.put("qrcode_url",users.getUser_qrcode_url().equals("")?"":ImageToolsController.qrcodeShowURL+"?imageid="+users.getUser_qrcode_url());

                    result.setData(qrcodeMap);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("获取成功");
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }

    // 修改用户头像(imageid-图片id,图片id为通过图片上传接口获取)
    @RequestMapping(value = "/setUserHeader", method = RequestMethod.POST,params = {"user_id","imageid"})
    @ResponseBody
    public Result setUserHeader(HttpServletRequest request,
                                HttpServletResponse response,
                                Integer user_id,
                                String imageid){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }else {
                Users users = this.usersService.selectById(user_id);
                if (users == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                }
                else if (imageid.equals("")){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("请先上传图片");
                }
                else{
                    users.setHeader(imageid);
                    int updateState = this.usersService.updateUserHeader(users);
                    if (updateState >= 1){
                        Map<Object,Object> headerMap = new HashMap<Object,Object>();
                        headerMap.put("header_url",ImageToolsController.imageShowURL+"?imageid="+imageid);
                        result.setData(headerMap);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("修改成功");
                    }
                    else{
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("修改失败");
                    }
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("修改失败");
        }
        return result;
    }


    // 获取邮箱验证码
    @RequestMapping(value = "/getEmailCode", method = RequestMethod.POST,params = {"email"})
    @ResponseBody
    public Result getEmailCode(HttpServletRequest request, HttpServletResponse response, String email){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(null,1,request,response,sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂时未登录");
            }else {
                String uuid = UUDUtil.getOrderIdByUUId();
                String code = uuid.substring(uuid.length()-6,uuid.length());
                boolean state = SendEmailUtil.sendEmail(email, "【云南辰蓝网络科技有限公司】您的验证码为："+code+"，该验证码5分钟内有效，请勿泄露于他人。", "邮箱验证码【辰蓝网络科技】");
                if (state == true){
                    Codes codes = new Codes();
                    codes.setSend_number(email);
                    codes.setCode(code);
                    codes.setSend_time(new Date());
                    int addState = this.codesService.addEmailCode(codes);
                    if (addState >= 1){
                        Map<Object,Object> map = new HashMap<Object,Object>();
                        map.put("code",code);
                        result.setData(map);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("发送成功");
                    }
                    else{
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("发送失败");
                    }
                }
                else{
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("发送失败");
                }

            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("发送失败");
        }
        return result;
    }

    // 修改登录密码(code-验证码)
    @RequestMapping(value = "/setLoginPwd", method = RequestMethod.POST,params = {"new_login_pwd","user_id","code"})
    @ResponseBody
    public Result setLoginPwd(HttpServletRequest request, HttpServletResponse response, String new_login_pwd,Integer user_id,String code){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                Users users = this.usersService.selectById(user_id);
                if (users == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                }
                else{
                    users.setLogin_pwd(new_login_pwd);
                    int updateState = this.usersService.updateLoginPwd(users);
                    if (updateState >= 1){
                        result.setData("");
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("修改成功");
                    }
                    else{
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("修改失败");
                    }
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("修改失败");
        }
        return result;
    }

    // 修改支付密码(code-验证码)
    @RequestMapping(value = "/setPayPwd", method = RequestMethod.POST,params = {"new_pay_pwd","user_id","code"})
    @ResponseBody
    public Result setPayPwd(HttpServletRequest request, HttpServletResponse response, String new_pay_pwd,Integer user_id,String code){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                Users users = this.usersService.selectById(user_id);
                if (users == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                }
                else{
                    users.setPay_pwd(new_pay_pwd);
                    int updateState = this.usersService.updatePayPwd(users);
                    if (updateState >= 1){
                        result.setData("");
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("修改成功");
                    }
                    else{
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("修改失败");
                    }
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("修改失败");
        }
        return result;
    }

    // 开启/关闭小额免密支付(state-是否开启小额免密支付,0-不开启,1-开启)
    @RequestMapping(value = "/setMicrofinancePay", method = RequestMethod.POST,params = {"state","user_id"})
    @ResponseBody
    public Result setMicrofinancePay(HttpServletRequest request, HttpServletResponse response, Integer state,Integer user_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else if (state != 0 && state != 1){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("数据不合法");
            }
            else {
                Users users = this.usersService.selectById(user_id);
                if (users == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                }
                else{
                    users.setIs_microfinance(state);
                    int updateState = this.usersService.updateIsMicrofinance(users);
                    if (updateState >= 1){
                        result.setData("");
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("修改成功");
                    }
                    else{
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("修改失败");
                    }
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("修改失败");
        }
        return result;
    }

    // 查询用户会员等级信息
    @RequestMapping(value = "/getUserMemberLevelInformation", method = RequestMethod.POST,params = {"user_id"})
    @ResponseBody
    public Result getUserMemberLevelInformation(HttpServletRequest request, HttpServletResponse response, Integer user_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                Users users = this.usersService.selectById(user_id);
                if (users == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                }
                else{
                    MemberLevels memberLevels = this.memberLevelsService.selectById(users.getMember_level_id());
                    if (memberLevels == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("无等级信息");
                    }
                    else{
                        Map<Object,Object> map = new HashMap<Object,Object>();
                        map.put("level_icon",ImageToolsController.imageShowURL+"?imageid="+memberLevels.getIcon_url());
                        map.put("level_name",memberLevels.getName());
                        map.put("member_star",users.getMember_star());
                        map.put("brief_introduction",memberLevels.getBrief_introduction());
                        Integer maxLevelId = this.memberLevelsService.selectMaxValueId();
                        if (maxLevelId == users.getMember_level_id()){
                            //是否可升级(0-不可升级,1-可升级)
                            map.put("is_can_upgrade",0);
                        }
                        else{
                            //是否可升级(0-不可升级,1-可升级)
                            map.put("is_can_upgrade",1);
                        }
                        List<Map<Object,Object>> recommendBeforeFiveUsers = this.usersService.selectUserRecommendBeforeFiveUsersLevelName(users.getId());
                        List<String> xxIconList = new ArrayList<String>();
                        for (Integer index = 0;index < recommendBeforeFiveUsers.size();index++){
                            Map<Object,Object> recommendUsers = recommendBeforeFiveUsers.get(index);
                            if (recommendUsers.get("level_name").equals("普通会员")){
                                xxIconList.add(ImageToolsController.projectImageShowURL+"images/wx/putong.png");
                            }
                            else if(recommendUsers.get("level_name").equals("铂金会员")){
                                xxIconList.add(ImageToolsController.projectImageShowURL+"images/wx/bojing.png");
                            }
                            else if(recommendUsers.get("level_name").equals("钻石会员")){
                                xxIconList.add(ImageToolsController.projectImageShowURL+"images/wx/zuanshi.png");
                            }
                            else{
                                xxIconList.add(ImageToolsController.projectImageShowURL+"images/wx/putong.png");
                            }

                        }
                        for (Integer index = 0;index < 5 - recommendBeforeFiveUsers.size();index++){
                            xxIconList.add(ImageToolsController.projectImageShowURL+"images/wx/default_xx.png");
                        }
                        map.put("xx_icon_urls",xxIconList);
                        result.setData(map);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("获取成功");
                    }
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }

    // 查询当前用户的团队成员
    @RequestMapping(value = "/getUserTeamMembers", method = RequestMethod.POST,params = {"user_id"})
    @ResponseBody
    public Result getUserTeamMembers(HttpServletRequest request, HttpServletResponse response, Integer user_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                Users users = this.usersService.selectById(user_id);
                if (users == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                }
                else{
                    Map<Object,Object> map = new HashMap<Object,Object>();
                    List<Map<Object,Object>> list = this.usersService.selectUserByRecommendUserId(user_id);
                    for (Integer index = 0;index < list.size();index++){
                        Map<Object,Object> objMap = list.get(index);
                        if ((Integer)objMap.get("member_card_level") == 0){
                            objMap.put("card_name","-银卡会员");
                        }
                        else if ((Integer)objMap.get("member_card_level") == 1){
                            objMap.put("card_name","-金卡会员");
                        }
                        else{
                            objMap.put("card_name","");
                        }
                    }
                    if (list.size() <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂无成员");
                    }
                    else{
                        for (Integer index = 0;index < list.size();index++){
                            Map<Object,Object> objectMap = list.get(index);
                            objectMap.put("header",ImageToolsController.imageShowURL+"?imageid="+objectMap.get("header"));
                        }
                        map.put("list",list);
                        result.setData(map);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("获取成功");
                    }
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }

    // 获取用户各项余额
    @RequestMapping(value = "/getUserBalance", method = RequestMethod.POST,params = {"user_id"})
    @ResponseBody
    public Result getUserBalance(HttpServletRequest request, HttpServletResponse response, Integer user_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                Users users = this.usersService.selectById(user_id);
                if (users == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                }
                else{
                    Map<Object,Object> map = new HashMap<Object,Object>();
                    map.put("consumption_integral",users.getConsumption_integral());
                    map.put("general_integral",users.getGeneral_integral());
                    map.put("silver_coin_balance",users.getSilver_coin_balance());
                    result.setData(map);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("获取成功");
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }

    // 获取用户银币可提现余额以及提现后到账金额
    @RequestMapping(value = "/getUserSliverCanWithdrawBalance", method = RequestMethod.POST,params = {"user_id"})
    @ResponseBody
    public Result getUserSliverCanWithdrawBalance(HttpServletRequest request, HttpServletResponse response, Integer user_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                Users users = this.usersService.selectById(user_id);
                if (users == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                }
                else{
                    Integer canWithdrawSilverCoinBalance = this.silverCoinDetailedService.getCanWithdrawSilverCoinBalance(user_id);
                    Double accountNumberDouble = canWithdrawSilverCoinBalance * 0.3;
                    Integer accountNumber = Integer.parseInt(new java.text.DecimalFormat("0").format(accountNumberDouble));
                    Map<Object,Object> map = new HashMap<Object,Object>();
                    map.put("can_withdraw_balance",canWithdrawSilverCoinBalance);
                    map.put("account_number",accountNumber);
                    result.setData(map);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("获取成功");
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }


    // 查询用户账号手机以及微信关联状态
    @RequestMapping(value = "/getUserMobileWXRelationState", method = RequestMethod.POST,params = {"user_id"})
    @ResponseBody
    public Result getUserMobileWXRelationState(HttpServletRequest request, HttpServletResponse response, Integer user_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                Users users = this.usersService.selectById(user_id);
                if (users == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                }
                else{
                    Map<Object,Object> map = new HashMap<Object,Object>();
                    if (users.getMobile() != null && !users.getMobile().equals("")){
                        map.put("mobile_state",1);
                        map.put("relation_mobile",users.getMobile());
                    }
                    else{
                        map.put("mobile_state",0);
                        map.put("relation_mobile","");
                    }

                    if (users.getWx_unionid() == null || users.getWx_unionid().equals("")){
                        map.put("wx_state",0);
                    }
                    else{
                        map.put("wx_state",1);
                    }

                    result.setData(map);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("获取成功");
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }


    // 关联微信号或关联手机号(type为0时表示关联微信号，type为1时表示关联手机号，type为0时code可传空值，type为0时relation_number传入的为微信的uniod,type为1时传入的relation_number为手机号)
    @RequestMapping(value = "/relationMobileOrWX", method = RequestMethod.POST,params = {"user_id","type","code","relation_number"})
    @ResponseBody
    public Result relationMobileOrWX(HttpServletRequest request, HttpServletResponse response, Integer user_id,Integer type,String code,String relation_number){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                Users users = this.usersService.selectById(user_id);
                if (users == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                }
                else if (type != 0 && type != 1){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("数据不合法");
                }
                else if (type == 1 && (code == null || code.equals(""))){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("请输入验证码");
                }
                else if (type == 1 && relation_number.length() != 11){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("请输入正确手机号");
                }
                else{
                    if (type == 0){
                        if (users.getWx_unionid() != null && !users.getWx_unionid().equals("")){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("此账号已关联微信");
                        }
                        else{
                            users.setWx_unionid(relation_number);
                            Users wxUser = this.usersService.selectByWxUnionid(relation_number);
                            if (wxUser != null){
                                users.setConsumption_integral(users.getConsumption_integral()+wxUser.getConsumption_integral());
                                users.setGeneral_integral(users.getGeneral_integral()+wxUser.getGeneral_integral());
                                users.setSilver_coin_balance(users.getSilver_coin_balance()+wxUser.getSilver_coin_balance());
                                int relationState = this.usersService.relationAccount(users.getId(),wxUser.getId());
                                if (relationState <= 0){
                                    result.setData("");
                                    result.setStatus(Result.FAIL);
                                    result.setMsg("关联失败");

                                    return result;
                                }
                            }
                            Integer state = this.usersService.relationAccountUpdateInformation(users);
                            if (state >= 1){
                                result.setData("");
                                result.setStatus(Result.SUCCESS);
                                result.setMsg("关联成功");
                            }
                            else{
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("关联失败");
                            }
                        }
                    }
                    else{
                        if (users.getMobile() != null && !users.getMobile().equals("")){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("此账号已关联手机号");
                        }
                        else{
                            List<Map<Object,Object>> list = this.codesService.selectMobileCode(relation_number,code,0);
                            if (list.size() > 0){
                                Integer code_id = -1;
                                for (int index = 0;index < list.size();index ++){
                                    Map<Object,Object> map = list.get(index);
                                    if ((Integer)map.get("state") == 0){
                                        code_id = (Integer)map.get("id");
                                    }
                                }
                                if (code_id != -1){
                                    int state = this.codesService.updateCodesState(code_id);
                                    if (state >= 1){
                                        users.setMobile(relation_number);
                                        Users mobileUser = this.usersService.selectByMobile(relation_number);
                                        if (mobileUser != null){
                                            users.setLogin_pwd(mobileUser.getLogin_pwd());
                                            users.setConsumption_integral(users.getConsumption_integral()+mobileUser.getConsumption_integral());
                                            users.setGeneral_integral(users.getGeneral_integral()+mobileUser.getGeneral_integral());
                                            users.setSilver_coin_balance(users.getSilver_coin_balance()+mobileUser.getSilver_coin_balance());
                                            int relationState = this.usersService.relationAccount(users.getId(),mobileUser.getId());
                                            if (relationState <= 0){
                                                result.setData("");
                                                result.setStatus(Result.FAIL);
                                                result.setMsg("关联失败");

                                                return result;
                                            }
                                        }
                                        Integer relationState = this.usersService.relationAccountUpdateInformation(users);
                                        if (relationState >= 1){
                                            result.setData("");
                                            result.setStatus(Result.SUCCESS);
                                            result.setMsg("关联成功");
                                        }
                                        else{
                                            result.setData("");
                                            result.setStatus(Result.FAIL);
                                            result.setMsg("关联失败");
                                        }
                                    }
                                    else{
                                        result.setStatus(Result.FAIL);
                                        result.setMsg("验证失败");
                                    }
                                }
                                else{
                                    result.setStatus(Result.FAIL);
                                    result.setMsg("验证码无效");
                                }
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("关联失败");
        }
        return result;
    }


    // 手机短信验证码验证(type=0:注册获取验证码,type=1:登录获取验证码,type=2:修改手机号获取验证码,type=3:修改登录密码获取验证码,type=4:修改支付密码获取验证码,type=5:修改密保问题获取验证码,type=6-关联手机号，type=7-找回密码)
    @RequestMapping(value = "/checkMobileCode", method = RequestMethod.POST,params = {"code","mobile","type"})
    @ResponseBody
    public Result checkMobileCode(HttpServletRequest request, HttpServletResponse response, String code, String mobile, Integer type){
        Result result = new Result();
        try {
            List<Map<Object,Object>> list = this.codesService.selectMobileCode(mobile,code,type);
            if (list.size() > 0){
                Integer code_id = -1;
                for (int index = 0;index < list.size();index ++){
                    Map<Object,Object> map = list.get(index);
                    if ((Integer)map.get("state") == 0){
                        code_id = (Integer)map.get("id");
                    }
                }
                if (code_id != -1){
                    int state = this.codesService.updateCodesState(code_id);
                    if (state >= 1){
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("验证成功");
                    }
                    else{
                        result.setStatus(Result.FAIL);
                        result.setMsg("验证失败");
                    }
                }
                else{
                    result.setStatus(Result.FAIL);
                    result.setMsg("验证码无效");
                }
            }
            else{
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("验证码不存在");
            }

        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("验证失败");
        }
        return result;
    }


    // 邮箱验证码验证
    @RequestMapping(value = "/checkEmailCode", method = RequestMethod.POST,params = {"email","code"})
    @ResponseBody
    public Result checkEmailCode(HttpServletRequest request, HttpServletResponse response, String email,String code){
        Result result = new Result();
        try {
            List<Map<Object,Object>> list = this.codesService.selectEmailCode(email,code);
            if (list.size() > 0){
                Integer code_id = -1;
                for (int index = 0;index < list.size();index ++){
                    Map<Object,Object> map = list.get(index);
                    if ((Integer)map.get("state") == 0){
                        code_id = (Integer)map.get("id");
                    }
                }
                if (code_id != -1){
                    int state = this.codesService.updateCodesState(code_id);
                    if (state >= 1){
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("验证成功");
                    }
                    else{
                        result.setStatus(Result.FAIL);
                        result.setMsg("验证失败");
                    }
                }
                else{
                    result.setStatus(Result.FAIL);
                    result.setMsg("验证码无效");
                }
            }
            else{
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("验证码不存在");
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("验证失败");
        }
        return result;
    }


    // 账号退出登录
    @RequestMapping(value = "/exitLogin", method = RequestMethod.POST,params = {"user_id"})
    @ResponseBody
    public Result checkEmailCode(HttpServletRequest request, HttpServletResponse response, Integer user_id){
        Result result = new Result();
        try {
            LoginSessionCheckUtil.exitLogin(user_id,1,request,this.sessionIdsService);
            result.setData("");
            result.setStatus(Result.SUCCESS);
            result.setMsg("成功退出");
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("退出失败");
        }
        return result;
    }

    // 找回登录密码
    @RequestMapping(value = "/retrieveLoginPWD", method = RequestMethod.POST,params = {"mobile","code","new_login_pwd"})
    @ResponseBody
    public Result setLoginPWD(HttpServletRequest request, HttpServletResponse response, String mobile,String code,String new_login_pwd){
        Result result = new Result();
        try {
            if (mobile.equals("") || mobile == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("请输入手机号");
            }
            else if (mobile.length() != 11){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("手机号不合法");
            }
            else{
                Users users = this.usersService.selectByMobile(mobile);
                if (users == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("用户不存在");
                }
                else{
                    List<Map<Object,Object>> list = this.codesService.selectMobileCode(mobile,code,7);
                    if (list.size() > 0){
                        Integer code_id = -1;
                        for (int index = 0;index < list.size();index ++){
                            Map<Object,Object> map = list.get(index);
                            if ((Integer)map.get("state") == 0){
                                code_id = (Integer)map.get("id");
                            }
                        }
                        if (code_id != -1){
                            int state = this.codesService.updateCodesState(code_id);
                            if (state >= 1){
                                users.setLogin_pwd(new_login_pwd);
                                int pwdstate = this.usersService.updateLoginPwd(users);
                                if (pwdstate >= 1){
                                    result.setData("");
                                    result.setStatus(Result.SUCCESS);
                                    result.setMsg("修改成功");
                                }
                                else{
                                    result.setData("");
                                    result.setStatus(Result.FAIL);
                                    result.setMsg("修改失败");
                                }
                            }
                            else{
                                result.setStatus(Result.FAIL);
                                result.setMsg("验证失败");
                            }
                        }
                        else{
                            result.setStatus(Result.FAIL);
                            result.setMsg("验证码无效");
                        }
                    }
                    else{
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("验证码不存在");
                    }
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("修改失败");
        }
        return result;
    }


    // 查询用户可选择升级的所有会员等级
    @RequestMapping(value = "/getUserCanChooseLevel", method = RequestMethod.POST,params = {"user_id"})
    @ResponseBody
    public Result getUserCanChooseLevel(HttpServletRequest request, HttpServletResponse response, Integer user_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                Users users = this.usersService.selectById(user_id);
                if (users == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                }
                else{
                    MemberLevels memberLevels = this.memberLevelsService.selectById(users.getMember_level_id());
                    if (memberLevels == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此会员等级不存在");
                    }
                    else{
                            List<Map<Object,Object>> list = this.memberLevelsService.selectUserCanChooseLevel(memberLevels.getRecharge_consumption_integral());
                            for (Integer index = 0;index < list.size();index++){
                                Map<Object,Object> map = list.get(index);
                                map.put("icon_url",ImageToolsController.imageShowURL+"?imageid="+map.get("icon_url"));
                            }
                            Map<Object,Object> map = new HashMap<Object,Object>();
                            map.put("list",list);
                            result.setData(map);
                            result.setStatus(Result.SUCCESS);
                            result.setMsg("获取成功");
                    }
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }


    // 查询用户会员卡等级信息
    @RequestMapping(value = "/getUserMemberCardInformation", method = RequestMethod.POST,params = {"user_id"})
    @ResponseBody
    public Result getUserMemberCardInformation(HttpServletRequest request, HttpServletResponse response, Integer user_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                Users users = this.usersService.selectById(user_id);
                if (users == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                }
                else{
//                    MemberLevels memberLevels = this.memberLevelsService.selectById(users.getMember_level_id());
                    if (users.getMember_card_level() == -1){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("未开卡");
                    }
                    else{
                        UserOpenCards userOpenCards = this.userOpenCardsService.selectByUserId(user_id);
                        if (userOpenCards == null){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("未开卡");
                        }
                        else{
                            //如果想比较日期则写成"yyyy-MM-dd"就可以了
                            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                            //将字符串形式的时间转化为Date类型的时间
                            Date currentDate = new Date();
                            if (!currentDate.before(userOpenCards.getTerm_validity())){
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("卡片已过期");
                            }
                            else{
                                Map<Object,Object> map = new HashMap<Object,Object>();
                                map.put("card_level",users.getMember_card_level());
                                if (users.getMember_card_level() == 0){
                                    map.put("brief_introduction","<h1>银卡会员权益</h1>");
                                }
                                else{
                                    map.put("brief_introduction","<h1>金卡会员权益</h1>");
                                }
                                List<Map<Object,Object>> recommendBeforeFiveUsers = new ArrayList<Map<Object,Object>>();
                                if (users.getMember_card_level() == 0){
                                    recommendBeforeFiveUsers = this.usersService.selectUserRecommendBeforeFiveUsersMemberCardLevelYKOrJK(users.getId());
                                }
                                else if (users.getMember_card_level() == 1){
                                    recommendBeforeFiveUsers = this.usersService.selectUserRecommendBeforeFiveUsersMemberCardLevelJK(users.getId());
                                }
                                List<String> xxIconList = new ArrayList<String>();
                                for (Integer index = 0;index < recommendBeforeFiveUsers.size();index++){
                                    Map<Object,Object> recommendUsers = recommendBeforeFiveUsers.get(index);
                                    if ((Integer)recommendUsers.get("member_card_level") == 0){
                                        xxIconList.add(ImageToolsController.projectImageShowURL+"images/wx/wjx_yinka.png");
                                    }
                                    else if((Integer)recommendUsers.get("member_card_level") == 1){
                                        xxIconList.add(ImageToolsController.projectImageShowURL+"images/wx/wjx_jinka.png");
                                    }
                                    else{
                                        xxIconList.add(ImageToolsController.projectImageShowURL+"images/wx/putong.png");
                                    }

                                }
                                for (Integer index = 0;index < 5 - recommendBeforeFiveUsers.size();index++){
                                    xxIconList.add(ImageToolsController.projectImageShowURL+"images/wx/default_xx.png");
                                }
                                SimpleDateFormat sdf2=new SimpleDateFormat("yyyy.MM.dd");
                                map.put("tuirenshu",recommendBeforeFiveUsers.size());
                                map.put("xx_icon_urls",xxIconList);
                                map.put("term_date",sdf2.format(userOpenCards.getTerm_validity()));
                                map.put("card_no",userOpenCards.getCard_no());
                                map.put("wnk_qrcode_img",ImageToolsController.qrcodeShowURL+"?imageid="+users.getWnk_qrcode());
                                result.setData(map);
                                result.setStatus(Result.SUCCESS);
                                result.setMsg("获取成功");
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }


    // 通过user_id更新用户个推APPID
    @RequestMapping(value = "/updateUserGeTuiAppId", method = RequestMethod.POST,params = {"user_id","getui_app_id"})
    @ResponseBody
    public Result updateUserGeTuiAppId(HttpServletRequest request, HttpServletResponse response, Integer user_id,String getui_app_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }else {
                Users users = this.usersService.selectById(user_id);
                if (users == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                }
                else{
                    users.setGetui_appid(getui_app_id);
                    this.usersService.updateUserGeTuiAppId(users);
                    result.setData("");
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("更新成功");
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("更新失败");
        }
        return result;
    }
}
