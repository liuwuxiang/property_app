package com.springmvc.controller.wnk_business_app;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.springmvc.controller.upload.ImageToolsController;
import com.springmvc.entity.*;
import com.springmvc.service.impl.*;
import com.springmvc.utils.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/wnk_business_app/v1.0.0")
public class LoginWnkBusinessAppController {
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private WnkBusinessAccountService wnkBusinessAccountService;
    @Resource
    private CodesService codesService;
    @Resource
    private WnkBusinessTypeService wnkBusinessTypeService;
    @Resource
    private WnkStoreInformationService wnkStoreInformationService;

    // 用户账号密码登录
    @RequestMapping(value = "/mobileAndPwdLogin", method = RequestMethod.POST, params = {"mobile", "login_pwd"})
    @ResponseBody
    public Result mobileAndPwdLogin(HttpServletRequest request, HttpServletResponse response, String mobile, String login_pwd) {
        Result result = new Result();
        try {
            WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectByMobileAndLoginPWD(mobile, login_pwd);
            if (wnkBusinessAccount == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("账号或密码不正确");
            } else {
                if (wnkBusinessAccount.getState() == 0) {
                    Map<Object, Object> userMap = new HashMap<Object, Object>();
                    userMap.put("business_id", wnkBusinessAccount.getId());
                    userMap.put("login_session", LoginSessionCheckUtil.saveSessionId(wnkBusinessAccount.getId(), 3, request, this.sessionIdsService));
                    result.setData(userMap);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("登录成功");
                    CookileUtil cookileUtil = new CookileUtil();
                    String cookieUserId = cookileUtil.getCookie_user_id(request, 3);
                    if (cookieUserId == null) {
                        cookileUtil.saveCookie(response, wnkBusinessAccount.getId().toString(), 3);
                    }
                } else {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此账号已停用");
                }
            }
        } catch (Exception e) {
            System.out.println("登录失败-----------" + e.getMessage());
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("登录失败");
        }
        return result;
    }

    // 获取短信验证码(8-万能卡商家登录,9-万能卡商家修改登录密码,10-万能卡商家修改支付密码)
    @RequestMapping(value = "/getMobileCode", method = RequestMethod.POST, params = {"mobile", "type"})
    @ResponseBody
    public Result getMobileCode(HttpServletRequest request, HttpServletResponse response, String mobile, Integer type) {
        Result result = new Result();
        try {
            if (type != 8 && LoginSessionCheckUtil.checkIsLogin(null, 3, request, response, sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂时未登录");
            } else if (type != 8 && type != 9 && type != 10) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("数据不合法");
            } else {
                String uuid = UUDUtil.getOrderIdByUUId();
                String code = uuid.substring(uuid.length() - 6, uuid.length());
                Codes codes = new Codes();
                codes.setSend_number(mobile);
                codes.setCode(code);
                codes.setSend_time(new Date());
                codes.setMake_type(type);
                SendSmsResponse sendSmsResponse = MobileCodeUtil.sendSms(code, mobile);
                if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
                    Integer state = this.codesService.addMobileCode(codes);
                    if (state >= 1) {
                        Map<Object, Object> map = new HashMap<Object, Object>();
                        map.put("code", code);
                        result.setData(map);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("发送成功");
                    } else {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("发送失败");
                    }

                } else {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg(sendSmsResponse.getMessage());
                }
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("发送失败");
        }
        return result;
    }

    // 手机号+短信验证码登录
    @RequestMapping(value = "/businessMobileAndCodeLogin", method = RequestMethod.POST, params = {"mobile", "code"})
    @ResponseBody
    public Result businessMobileAndCodeLogin(HttpServletRequest request, HttpServletResponse response, String mobile, String code) {
        Result result = new Result();
        try {
            WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectByMobile(mobile);
            if (wnkBusinessAccount == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("此手机号不是商家");
            } else {
                List<Map<Object, Object>> list = this.codesService.selectMobileCode(mobile, code, 8);
                if (list.size() > 0) {
                    Integer code_id = -1;
                    for (int index = 0; index < list.size(); index++) {
                        Map<Object, Object> map = list.get(index);
                        if ((Integer) map.get("state") == 0) {
                            code_id = (Integer) map.get("id");
                        }
                    }
                    if (code_id != -1) {
                        int state = this.codesService.updateCodesState(code_id);
                        if (state >= 1) {
                            if (wnkBusinessAccount.getState() == 0) {
                                Map<Object, Object> userMap = new HashMap<Object, Object>();
                                userMap.put("business_id", wnkBusinessAccount.getId());
                                userMap.put("login_session", LoginSessionCheckUtil.saveSessionId(wnkBusinessAccount.getId(), 3, request, this.sessionIdsService));
                                result.setData(userMap);
                                result.setStatus(Result.SUCCESS);
                                result.setMsg("登录成功");
                                CookileUtil cookileUtil = new CookileUtil();
                                String cookieUserId = cookileUtil.getCookie_user_id(request, 3);
                                if (cookieUserId == null) {
                                    cookileUtil.saveCookie(response, wnkBusinessAccount.getId().toString(), 3);
                                }
                            } else {
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("此账号已停用");
                            }
                        } else {
                            result.setStatus(Result.FAIL);
                            result.setMsg("验证失败");
                        }
                    } else {
                        result.setStatus(Result.FAIL);
                        result.setMsg("验证码无效");
                    }
                } else {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("验证码不存在");
                }
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("登录失败");
        }
        return result;
    }


    // 获取短信验证码(8-万能卡商家登录,9-万能卡商家修改登录密码,10-万能卡商家修改支付密码)
    @RequestMapping(value = "/getMobileCodeSetPwd", method = RequestMethod.POST, params = {"business_id", "type"})
    @ResponseBody
    public Result getMobileCodeSetPwd(HttpServletRequest request, HttpServletResponse response, Integer business_id, Integer type) {
        Result result = new Result();
        try {
            if (type != 8 && LoginSessionCheckUtil.checkIsLogin(business_id, 3, request, response, sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂时未登录");
            } else if (type != 8 && type != 9 && type != 10) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("数据不合法");
            } else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("商户不存在");
                } else {
                    String uuid = UUDUtil.getOrderIdByUUId();
                    String code = uuid.substring(uuid.length() - 6, uuid.length());
                    Codes codes = new Codes();
                    codes.setSend_number(wnkBusinessAccount.getMobile());
                    codes.setCode(code);
                    codes.setSend_time(new Date());
                    codes.setMake_type(type);
                    SendSmsResponse sendSmsResponse = null;
                    if (type == 8) {
                        sendSmsResponse = MobileCodeUtil.sendSms(code, wnkBusinessAccount.getMobile());
                    } else if (type == 9) {
                        sendSmsResponse = MobileCodeUtil.sendBusinessSetLoginPWDCodeSms(wnkBusinessAccount.getMobile(), code);
                    } else if (type == 10) {
                        sendSmsResponse = MobileCodeUtil.sendBusinessSetPayPWDCodeSms(wnkBusinessAccount.getMobile(), code);
                    }
                    if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
                        Integer state = this.codesService.addMobileCode(codes);
                        if (state >= 1) {
                            Map<Object, Object> map = new HashMap<Object, Object>();
                            map.put("code", code);
                            result.setData(map);
                            result.setStatus(Result.SUCCESS);
                            result.setMsg("发送成功");
                        } else {
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("发送失败");
                        }

                    } else {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg(sendSmsResponse.getMessage());
                    }
                }

            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("发送失败");
        }
        return result;
    }


    // 修改登录密码
    @RequestMapping(value = "/setLoginPwd", method = RequestMethod.POST, params = {"business_id", "new_login_pwd", "code"})
    @ResponseBody
    public Result setLoginPwd(HttpServletRequest request, HttpServletResponse response, Integer business_id, String new_login_pwd, String code) {
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id, 3, request, response, this.sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            } else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                } else {
                    List<Map<Object, Object>> list = this.codesService.selectMobileCode(wnkBusinessAccount.getMobile(), code, 9);
                    if (list.size() > 0) {
                        Integer code_id = -1;
                        for (int index = 0; index < list.size(); index++) {
                            Map<Object, Object> map = list.get(index);
                            if ((Integer) map.get("state") == 0) {
                                code_id = (Integer) map.get("id");
                            }
                        }
                        if (code_id != -1) {
                            int state = this.codesService.updateCodesState(code_id);
                            if (state >= 1) {
                                if (wnkBusinessAccount.getState() == 0) {
                                    int pwdUpdateState = this.wnkBusinessAccountService.updateLoginPwd(business_id, new_login_pwd);
                                    if (pwdUpdateState <= 0) {
                                        result.setData("");
                                        result.setStatus(Result.FAIL);
                                        result.setMsg("设置失败");
                                    } else {
                                        LoginSessionCheckUtil.exitLogin(business_id, 3, request, this.sessionIdsService);
                                        CookileUtil cookileUtil = new CookileUtil();
                                        cookileUtil.clearCookie(3, business_id.toString(), response);
                                        result.setData("");
                                        result.setStatus(Result.SUCCESS);
                                        result.setMsg("设置成功");
                                    }
                                } else {
                                    result.setData("");
                                    result.setStatus(Result.FAIL);
                                    result.setMsg("此账号已停用");
                                }
                            } else {
                                result.setStatus(Result.FAIL);
                                result.setMsg("验证失败");
                            }
                        } else {
                            result.setStatus(Result.FAIL);
                            result.setMsg("验证码无效");
                        }
                    } else {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("验证码不存在");
                    }
                }
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }


    // 修改支付密码
    @RequestMapping(value = "/setPwdPwd", method = RequestMethod.POST, params = {"business_id", "new_pay_pwd", "code"})
    @ResponseBody
    public Result setPwdPwd(HttpServletRequest request, HttpServletResponse response, Integer business_id, String new_pay_pwd, String code) {
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id, 3, request, response, this.sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            } else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                } else {
                    List<Map<Object, Object>> list = this.codesService.selectMobileCode(wnkBusinessAccount.getMobile(), code, 10);
                    if (list.size() > 0) {
                        Integer code_id = -1;
                        for (int index = 0; index < list.size(); index++) {
                            Map<Object, Object> map = list.get(index);
                            if ((Integer) map.get("state") == 0) {
                                code_id = (Integer) map.get("id");
                            }
                        }
                        if (code_id != -1) {
                            int state = this.codesService.updateCodesState(code_id);
                            if (state >= 1) {
                                if (wnkBusinessAccount.getState() == 0) {
                                    int pwdUpdateState = this.wnkBusinessAccountService.updatePayPwd(business_id, new_pay_pwd);
                                    if (pwdUpdateState <= 0) {
                                        result.setData("");
                                        result.setStatus(Result.FAIL);
                                        result.setMsg("设置失败");
                                    } else {
                                        result.setData("");
                                        result.setStatus(Result.SUCCESS);
                                        result.setMsg("设置成功");
                                    }
                                } else {
                                    result.setData("");
                                    result.setStatus(Result.FAIL);
                                    result.setMsg("此账号已停用");
                                }
                            } else {
                                result.setStatus(Result.FAIL);
                                result.setMsg("验证失败");
                            }
                        } else {
                            result.setStatus(Result.FAIL);
                            result.setMsg("验证码无效");
                        }
                    } else {
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("验证码不存在");
                    }
                }
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }


    // 修改商户店铺个推AppID
    @RequestMapping(value = "/updateStoreGeTuiAppId", method = RequestMethod.POST, params = {"business_id", "getui_app_id"})
    @ResponseBody
    public Result updateStoreGeTuiAppId(HttpServletRequest request, HttpServletResponse response, Integer business_id, String getui_app_id) {
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id, 3, request, response, this.sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            } else {
                WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(business_id);
                if (wnkStoreInformation == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                } else {
                    wnkStoreInformation.setGetui_appid(getui_app_id);
                    this.wnkStoreInformationService.updateStoreGeTuiAppId(wnkStoreInformation);
                    result.setData("");
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("更新成功");
                }
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("更新失败");
        }
        return result;
    }


    // 退出登录
    @RequestMapping(value = "/exitLogin", method = RequestMethod.POST, params = {"business_id",})
    @ResponseBody
    public Result exitLogin(HttpServletRequest request, HttpServletResponse response, Integer business_id) {
        Result result = new Result();
        try {
            LoginSessionCheckUtil.exitLogin(business_id, 3, request, this.sessionIdsService);
            CookileUtil cookileUtil = new CookileUtil();
            cookileUtil.clearCookie(3, business_id.toString(), response);
            result.setData("");
            result.setStatus(Result.SUCCESS);
            result.setMsg("成功退出");
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("退出失败");
        }
        return result;
    }


    // 获取万能卡商户分类
    @RequestMapping(value = "/getWnkBusinessType", method = RequestMethod.POST)
    @ResponseBody
    public Result getWnkBusinessType(HttpServletRequest request, HttpServletResponse response) {
        Result result = new Result();
        try {
            List<Map<Object, Object>> list = this.wnkBusinessTypeService.selectAllRecord();
            if (list.size() <= 0) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("暂无商户分类");
            } else {
                result.setData(list);
                result.setStatus(Result.SUCCESS);
                result.setMsg("查询成功");
            }
        } catch (Exception e) {
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }

    /**
     * 功能描述: 根据商家ID获取商户分类
     *
     * @param businessId 商家ID
     * @return com.springmvc.utils.Result
     * @author 杨新杰
     * @date 12:22 2019/1/11
     */
    @RequestMapping(value = "/getWnkBusinessTypeByBusinessId", method = RequestMethod.POST)
    @ResponseBody
    public Result getWnkBusinessTypeByBusinessId(HttpServletRequest request, HttpServletResponse response, Integer businessId) {
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(businessId, 3, request, response, this.sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
                return result;
            }
            WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(businessId);
            if (wnkStoreInformation == null) {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("此商家不存在");
                return result;
            }

            //<editor-fold desc="业务开始">
            WnkBusinessType wnkBusinessType = this.wnkBusinessTypeService.selectById(wnkStoreInformation.getBusiness_type_id());
            if (wnkBusinessType != null){
                result.setData(wnkBusinessType);
                result.setStatus(Result.SUCCESS);
                result.setMsg("查询成功");
            } else {
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("查询失败");
            }
            //</editor-fold>

        } catch (Exception e) {
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }
}
