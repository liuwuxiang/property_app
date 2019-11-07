package com.springmvc.controller.app;

import com.springmvc.controller.upload.ImageToolsController;
import com.springmvc.entity.MemberLevels;
import com.springmvc.entity.Users;
import com.springmvc.entity.WnkRealNameAuthentication;
import com.springmvc.service.impl.CodesService;
import com.springmvc.service.impl.LoginSessionIdsService;
import com.springmvc.service.impl.UsersService;
import com.springmvc.service.impl.WnkRealNameAuthenticationService;
import com.springmvc.utils.CookileUtil;
import com.springmvc.utils.IDCardNumberUtil;
import com.springmvc.utils.LoginSessionCheckUtil;
import com.springmvc.utils.Result;
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
@RequestMapping(value = "/app/v1.0.0")
public class AuthorizationAppController {
    @Resource
    private UsersService usersService;
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private WnkRealNameAuthenticationService wnkRealNameAuthenticationService;
    @Resource
    private CodesService codesService;

    // 提交万能卡实名认证记录
    @RequestMapping(value = "/submitWnkAuthentication", method = RequestMethod.POST,params = {"user_id","type","real_name","id_card_number","school","mobile","code"})
    @ResponseBody
    public Result submitWnkAuthentication(HttpServletRequest request, HttpServletResponse response, Integer user_id,Integer type,String real_name,String id_card_number,String school,String mobile,String code){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else if (mobile.length() != 11){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("手机号不正确");
            }
            else if (type != 0 && type != 1){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("数据不合法");
            }
            else {
                List<Map<Object,Object>> list = this.codesService.selectMobileCode(mobile,code,12);
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
                            Users users = this.usersService.selectById(user_id);
                            if (users == null){
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("此用户不存在");
                            }
                            else{
                                List<Map<Object,Object>> authenticationList = this.wnkRealNameAuthenticationService.selectRecordByUserId(user_id);
                                if (authenticationList.size() <= 0){
                                    Date birthdayDate = IDCardNumberUtil.checkIdCardNumber(id_card_number);
                                    if (birthdayDate == null){
                                        result.setData("");
                                        result.setStatus(Result.FAIL);
                                        result.setMsg("身份证号不正确");
                                    }
                                    else{
                                        Integer age = IDCardNumberUtil.calculationUserAge(birthdayDate);
                                        if (age > 24 && type == 0){
                                            result.setData("");
                                            result.setStatus(Result.FAIL);
                                            result.setMsg("您的年龄已超过24,不可开通学生卡");
                                        }
                                        else{
                                            WnkRealNameAuthentication wnkRealNameAuthentication = new WnkRealNameAuthentication();
                                            wnkRealNameAuthentication.setUser_id(user_id);
                                            wnkRealNameAuthentication.setType(type);
                                            wnkRealNameAuthentication.setReal_name(real_name);
                                            wnkRealNameAuthentication.setId_card_number(id_card_number);
                                            wnkRealNameAuthentication.setSchool(school);
                                            wnkRealNameAuthentication.setMobile(mobile);
                                            wnkRealNameAuthentication.setAuthentication_date(new Date());
                                            int addState = this.wnkRealNameAuthenticationService.insertNewRecord(wnkRealNameAuthentication);
                                            if (addState <= 0){
                                                result.setData("");
                                                result.setStatus(Result.FAIL);
                                                result.setMsg("提交失败");
                                            }
                                            else{
                                                result.setData("");
                                                result.setStatus(Result.SUCCESS);
                                                result.setMsg("提交成功");
                                            }
                                        }
                                    }
                                }
                                else{
                                    result.setData("");
                                    result.setStatus(Result.FAIL);
                                    result.setMsg("此用户已认证");
                                }
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
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }
}
