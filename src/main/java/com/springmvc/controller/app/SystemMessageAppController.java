package com.springmvc.controller.app;

import com.springmvc.entity.SystemMessages;
import com.springmvc.entity.Users;
import com.springmvc.service.impl.LoginSessionIdsService;
import com.springmvc.service.impl.SystemMessagesService;
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
public class SystemMessageAppController {
    @Resource
    private UsersService usersService;
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private SystemMessagesService systemMessagesService;

    // 获取用户系统消息
    @RequestMapping(value = "/getUserSystemMessage", method = RequestMethod.POST,params = {"user_id"})
    @ResponseBody
    public Result getUserSystemMessage(HttpServletRequest request, HttpServletResponse response, Integer user_id){
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
                    List<Map<Object,Object>> systemMessageList = this.systemMessagesService.selectUserAllMessage(user_id);
                    if (systemMessageList.size() <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂无数据");
                    }
                    else{
                        Map<Object,Object> map = new HashMap<Object,Object>();
                        map.put("list",systemMessageList);
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


    // 通过系统消息id获取消息详情
    @RequestMapping(value = "/getSystemMessageByMessageId", method = RequestMethod.POST,params = {"user_id","message_id"})
    @ResponseBody
    public Result getSystemMessageByMessageId(HttpServletRequest request, HttpServletResponse response, Integer user_id,Integer message_id){
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
                    SystemMessages systemMessages = this.systemMessagesService.selectMessageDetailByMessageId(message_id);
                    if (systemMessages == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此消息不存在");
                    }
                    else{
                        result.setData(systemMessages);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("获取成功");
                        if (systemMessages.getRead_status() == 0){
                            this.systemMessagesService.updateMessageForRead(message_id);
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
}
