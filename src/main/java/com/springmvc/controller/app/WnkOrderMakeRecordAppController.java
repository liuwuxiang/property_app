package com.springmvc.controller.app;

import com.springmvc.entity.Users;
import com.springmvc.entity.WnkOrdersTwo;
import com.springmvc.service.impl.*;
import com.springmvc.utils.LoginSessionCheckUtil;
import com.springmvc.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/12/9 03:37
 * @Description:
 */
@Controller
@RequestMapping(value = "/app/v2.0.0")
public class WnkOrderMakeRecordAppController {
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private UsersService usersService;
    @Resource
    private WnkOrderMakeRecordService wnkOrderMakeRecordService;
    @Resource
    private WnkOrdersTwoService wnkOrdersTwoService;

    // 获取万能卡订单使用记录
    @RequestMapping(value = "/wnkOrderMakeRecord", method = RequestMethod.POST,params = {"user_id","order_id"})
    @ResponseBody
    public Result wnkOrderMakeRecord(HttpServletRequest request, HttpServletResponse response, Integer user_id,Integer order_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,this.sessionIdsService) == null){
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
                    WnkOrdersTwo wnkOrdersTwo = this.wnkOrdersTwoService.selectByOrderId(order_id);
                    if (wnkOrdersTwo == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("订单不存在");
                    }
                    else{
                        List<Map<Object,Object>> list = this.wnkOrderMakeRecordService.selectByOrderNo(wnkOrdersTwo.getOrder_no());
                        if (list.size() <= 0){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("暂无数据");
                        }
                        else{
                            result.setData(list);
                            result.setStatus(Result.SUCCESS);
                            result.setMsg("查询成功");
                        }
                    }

                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }
}
