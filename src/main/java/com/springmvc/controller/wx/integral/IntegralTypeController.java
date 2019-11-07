package com.springmvc.controller.wx.integral;


import com.springmvc.entity.Users;
import com.springmvc.service.IntegralTypeService;
import com.springmvc.service.impl.LoginSessionIdsService;
import com.springmvc.service.impl.UsersService;
import com.springmvc.utils.LoginSessionCheckUtil;
import com.springmvc.utils.Result;
import org.apache.log4j.Logger;
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
@RequestMapping(value = "/wx/v1.0.0")
public class IntegralTypeController {

    @Resource
    private UsersService usersService;

    @Resource
    private LoginSessionIdsService sessionIdsService;

    @Resource
    private IntegralTypeService integralTypeService;


    /**
     *
     * 功能描述: 获取所有已经启用的商品分类
     *
     * @param  request HttpServletRequest服务类
     * @param  response HttpServletResponse服务类
     * @param  user_id 用户ID
     * @return <br>返回类型:com.springmvc.utils.Result<br>
     *         <br>
     * @author 杨新杰
     * @date   2018/10/8 10:35
     */
    @RequestMapping(value = "/getAllIntegralTypeTrue",method = RequestMethod.POST)
    @ResponseBody
    public Result getAllIntegralTypeTrue(HttpServletRequest request, HttpServletResponse response, Integer user_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id, 1, request, response, this.sessionIdsService) == null) {
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            } else {
                Users users = this.usersService.selectById(user_id);
                if (users == null) {
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                } else {
                    // 业务开始
                    List<Map<String,Object>> list = integralTypeService.getAllIntegralTypeTrue();
                    if (list == null || list.size() <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("获取失败");
                    } else {
                        Map<String,Object> map = new HashMap<>();
                        map.put("list",list);
                        result.setData(map);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("获取成功");
                    }// 业务结束
                }
            }
        } catch (Exception e) {
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }


}
