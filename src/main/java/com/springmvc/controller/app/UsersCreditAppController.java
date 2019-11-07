package com.springmvc.controller.app;

import com.springmvc.entity.Users;
import com.springmvc.service.impl.LoginSessionIdsService;
import com.springmvc.service.impl.UsersCreditService;
import com.springmvc.service.impl.UsersService;
import com.springmvc.utils.LoginSessionCheckUtil;
import com.springmvc.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/app/v1.0.0")
public class UsersCreditAppController {
    @Resource
    private UsersService usersService;
    @Resource
    private UsersCreditService usersCreditService;
    @Resource
    private LoginSessionIdsService sessionIdsService;

    // 获取用户信用评级记录
    @RequestMapping(value = "/getUserCreditRecord", method = RequestMethod.POST,params = {"user_id"})
    @ResponseBody
    public Result getUserCreditRecord(HttpServletRequest request, HttpServletResponse response, Integer user_id){
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
                    List<Map<Object,Object>> creditRecord = this.usersCreditService.selectUserCreditRecord(user_id);
                    if (creditRecord == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂无评级记录");
                    }
                    else{
                        Map<Object,Object> map = new HashMap<Object,Object>();
                        map.put("list",creditRecord);
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
}
