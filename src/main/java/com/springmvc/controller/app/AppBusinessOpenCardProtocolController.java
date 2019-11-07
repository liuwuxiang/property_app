package com.springmvc.controller.app;

import com.springmvc.dao.WnkBusinessOpenCardProtocolMapper;
import com.springmvc.entity.Users;
import com.springmvc.service.impl.LoginSessionIdsService;
import com.springmvc.service.impl.UsersService;
import com.springmvc.service.impl.WnkBusinessOpenCardProtocolService;
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

/**
 * 功能描述:
 *
 * @author 杨新杰
 * @date 2019/1/2 17:26
 */
@Controller
@RequestMapping(value = "/app/v1.0.0")
public class AppBusinessOpenCardProtocolController {

    @Resource
    private UsersService usersService;
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private WnkBusinessOpenCardProtocolService wnkBusinessOpenCardProtocolService;

    /**
     *
     * 功能描述:根据商家ID获取会员卡开卡协议
     *
     * @return  com.springmvc.utils.Result
     * @author  杨新杰
     * @date    17:28 2019/1/2
     */
    @RequestMapping(value = "/selectBusinessOpenCardProtocolByBusinessId", method = RequestMethod.POST)
    @ResponseBody
    public Result selectBusinessOpenCardProtocolByBusinessId(HttpServletRequest request, HttpServletResponse response, Integer user_id,Integer business_id){
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
                   Map<String,Object>  map = this.wnkBusinessOpenCardProtocolService.selectBusinessOpenCardProtocolByBusinessId(business_id);
                    result.setData(map);
                    result.setStatus(Result.SUCCESS);
                    result.setMsg("查询成功");
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
