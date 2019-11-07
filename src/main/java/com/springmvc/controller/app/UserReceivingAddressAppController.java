package com.springmvc.controller.app;

import com.springmvc.entity.UserReceivingAddress;
import com.springmvc.entity.Users;
import com.springmvc.service.impl.LoginSessionIdsService;
import com.springmvc.service.impl.UserReceivingAddressService;
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
import java.util.Map;

@Controller
@RequestMapping(value = "/app/v1.0.0")
public class UserReceivingAddressAppController {
    @Resource
    private UsersService usersService;
    @Resource
    private UserReceivingAddressService userReceivingAddressService;
    @Resource
    private LoginSessionIdsService sessionIdsService;


    // 获取用户收货地址
    @RequestMapping(value = "/getUserReceivingAddress", method = RequestMethod.POST,params = {"user_id"})
    @ResponseBody
    public Result getUserReceivingAddress(HttpServletRequest request, HttpServletResponse response, Integer user_id){
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
                    Map<Object,Object> userReceivingAddressMap = this.userReceivingAddressService.selectReceivingAddressByUserIdReturnMap(user_id);
                    if (userReceivingAddressMap == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("未设置收货地址");
                    }
                    else{
                        result.setData(userReceivingAddressMap);
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

    // 设置用户收货地址
    @RequestMapping(value = "/setUserReceivingAddress", method = RequestMethod.POST,params = {"user_id","name","mobile","province_id","city_id","detailed_address"})
    @ResponseBody
    public Result setUserReceivingAddress(HttpServletRequest request, HttpServletResponse response, Integer user_id, String name, String mobile, Integer province_id, Integer city_id, String detailed_address){
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
                    //操作方式(0-添加,1-修改)
                    Integer operationType = 1;
                    UserReceivingAddress userReceivingAddress = this.userReceivingAddressService.selectReceivingAddressByUserId(user_id);
                    if (userReceivingAddress == null){
                        userReceivingAddress = new UserReceivingAddress();
                        operationType = 0;
                    }
                    userReceivingAddress.setName(name);
                    userReceivingAddress.setMobile(mobile);
                    userReceivingAddress.setProvince_id(province_id);
                    userReceivingAddress.setCity_id(city_id);
                    userReceivingAddress.setDetailed_address(detailed_address);
                    userReceivingAddress.setUser_id(user_id);
                    Integer operationState = 0;
                    if (operationType == 0){
                        operationState = this.userReceivingAddressService.addUserReceivingAddress(userReceivingAddress);
                    }
                    else{
                        operationState = this.userReceivingAddressService.updateUserReceivingAddress(userReceivingAddress);
                    }

                    result.setData("");
                    result.setStatus(operationState >= 1?Result.SUCCESS:Result.FAIL);
                    result.setMsg(operationState >= 1?"设置成功":"设置失败");
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("设置失败");
        }
        return result;
    }
}
