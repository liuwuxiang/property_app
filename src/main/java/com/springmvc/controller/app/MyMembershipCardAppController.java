package com.springmvc.controller.app;

import com.springmvc.entity.Users;
import com.springmvc.service.impl.LoginSessionIdsService;
import com.springmvc.service.impl.MyMembershipCardService;
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
public class MyMembershipCardAppController {
    @Resource
    private UsersService usersService;
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private MyMembershipCardService myMembershipCardService;

    // 获取我的会员卡
    @RequestMapping(value = "/getMyMemberCards", method = RequestMethod.POST,params = {"user_id"})
    @ResponseBody
    public Result getMyMemberCards(HttpServletRequest request, HttpServletResponse response, Integer user_id){
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
                    List<Map<Object,Object>> list = this.myMembershipCardService.selectAllMemberCards(user_id);
                    if (list.size() <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂无会员卡");
                    }
                    else{
                        for (int index = 0;index < list.size();index++){
                            Map<Object,Object> newMap = list.get(index);
                            if ((Integer)newMap.get("pay_percentage") == 100){
                                newMap.put("fold_number","0折");
                            }
                            else{
                                Double fold_number_double = (Integer)newMap.get("pay_percentage") / 10.0;
                                String fold_number_str = String.format("%.1f", fold_number_double);
                                newMap.put("fold_number",fold_number_str+"折");
                            }
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

}
