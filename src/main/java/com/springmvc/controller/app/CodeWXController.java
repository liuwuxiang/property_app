package com.springmvc.controller.app;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.springmvc.entity.Codes;
import com.springmvc.service.impl.CodesService;
import com.springmvc.service.impl.LoginSessionIdsService;
import com.springmvc.service.impl.UsersService;
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
import java.util.Map;

@Controller
@RequestMapping(value = "/app/v1.0.0")
public class CodeWXController {
    @Resource
    private UsersService usersService;
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private CodesService codesService;

    // 获取万能卡实名认证验证码
    @RequestMapping(value = "/getWnkAuthenticationMobileCode", method = RequestMethod.POST,params = {"mobile"})
    @ResponseBody
    public Result getWnkAuthenticationMobileCode(HttpServletRequest request, HttpServletResponse response, String mobile){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(null,1,request,response,sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂时未登录");
            }
            else{
                String uuid = UUDUtil.getOrderIdByUUId();
                String code = uuid.substring(uuid.length()-6,uuid.length());
                Codes codes = new Codes();
                codes.setSend_number(mobile);
                codes.setCode(code);
                codes.setSend_time(new Date());
                codes.setMake_type(12);
                SendSmsResponse sendSmsResponse = MobileCodeUtil.sendSms(code,mobile);
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
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("发送失败");
        }
        return result;
    }
}
