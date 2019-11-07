package com.springmvc.controller.wx;

import com.springmvc.controller.upload.ImageToolsController;
import com.springmvc.entity.AboutUs;
import com.springmvc.entity.RechargeOrder;
import com.springmvc.entity.UserIdCardAuthentication;
import com.springmvc.entity.Users;
import com.springmvc.service.impl.*;
import com.springmvc.utils.LoginSessionCheckUtil;
import com.springmvc.utils.Result;
import com.springmvc.utils.UUDUtil;
import com.springmvc.utils.wechat.WechatPayLineUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping(value = "/wx/v1.0.0")
public class RechargeWXController {
    @Resource
    private UsersService usersService;
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private UsersCertificatesService usersCertificatesService;
    @Resource
    private AboutUsService aboutUsService;
    @Resource
    private RechargeOrderService rechargeOrderService;
    @Resource
    private UserIdCardAuthenticationService userIdCardAuthenticationService;

    // 查询用户是否已绑定微信
    @RequestMapping(value = "/searchBindWX", method = RequestMethod.POST,params = {"user_id"})
    @ResponseBody
    public Result getUserCertificates(HttpServletRequest request, HttpServletResponse response, Integer user_id){
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
                    if (!users.getWx_unionid().equals("") && users.getWx_unionid() != null){
                        if (users.getPay_pwd() == null || users.getPay_pwd().equals("")){
                            Map<Object,Object> statusMap = new HashMap<Object,Object>();
                            statusMap.put("jump_state",1);
                            result.setData(statusMap);
                            result.setStatus(Result.FAIL);
                            result.setMsg("请先设置支付密码");
                        }
                        else{
                            result.setData("");
                            result.setStatus(Result.SUCCESS);
                            result.setMsg("获取成功");
                        }
                    }
                    else{
                        Map<Object,Object> statusMap = new HashMap<Object,Object>();
                        statusMap.put("jump_state",0);
                        result.setData(statusMap);
                        result.setStatus(Result.FAIL);
                        result.setMsg("请先绑定微信");
                    }
                }
            }
        }catch (Exception e){
            Map<Object,Object> statusMap = new HashMap<Object,Object>();
            statusMap.put("jump_state",-1);
            result.setData(statusMap);
            result.setStatus(Result.FAIL);
            result.setMsg("获取失败");
        }
        return result;
    }

    // 微信下单支付
    @RequestMapping(value = "/wxUnlineOrderPay", method = RequestMethod.POST,params = {"user_id","amount"})
    @ResponseBody
    public Result wxUnlineOrderPay(HttpServletRequest request, HttpServletResponse response, Integer user_id,Double amount){
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
                    if (users.getWx_unionid().equals("") && users.getWx_unionid() == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此账号暂未绑定微信");
                    }
                    else{
                        AboutUs integralAndRMB = this.aboutUsService.selectIntegralAbout(9);
                        if (integralAndRMB == null){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("当前不可充值");
                        }
                        else if (amount % Integer.parseInt(integralAndRMB.getContent()) != 0){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("充值金额需为"+integralAndRMB.getContent()+"的整数倍");
                        }
                        else{
                            Double integralNumber = amount / Integer.parseInt(integralAndRMB.getContent());
                            RechargeOrder rechargeOrder = new RechargeOrder();
                            rechargeOrder.setUser_id(users.getId());
                            rechargeOrder.setRecharge_amount(amount);
                            rechargeOrder.setRecharge_type(0);
                            rechargeOrder.setPay_type(0);
                            rechargeOrder.setState(1);
                            rechargeOrder.setRecharge_time(new Date());
                            rechargeOrder.setSystem_order_no(UUDUtil.getOrderIdByUUId());
                            rechargeOrder.setPay_time(new Date());
                            rechargeOrder.setIntegral_number(integralNumber);
                            int rechargeState = this.rechargeOrderService.addUnderLineRechargeOrder(rechargeOrder);
                            if (rechargeState <= 0){
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("下单失败");
                            }
                            else{
                                Double amount_double = Double.valueOf(amount);
                                //准备调起微信支付
                                Map<String,Object> resutMap = WechatPayLineUtil.wechatPayFileBuy(rechargeOrder.getSystem_order_no(), amount_double, users.getWx_unionid(), request, response);
                                if ((Boolean) resutMap.get("status") == true){
                                    Map<String,Object> messageMap = new HashMap<String,Object>();
                                    messageMap.put("order_no",rechargeOrder.getSystem_order_no());
                                    messageMap.put("wx_config",resutMap.get("wx_config"));
                                    result.setData(messageMap);
                                    result.setStatus(Result.SUCCESS);
                                    result.setMsg("微信下单成功");
                                }
                                else{
                                    result.setData("");
                                    result.setStatus(Result.FAIL);
                                    result.setMsg((String)resutMap.get("msg"));
                                }
                            }
                        }

                    }
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("下单失败");
        }
        return result;
    }


    // 微信下单支付
    @RequestMapping(value = "/wxUnlineOrderPayApp", method = RequestMethod.POST,params = {"user_id","amount"})
    @ResponseBody
    public Result wxUnlineOrderPayApp(HttpServletRequest request, HttpServletResponse response, Integer user_id,Double amount){
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
                    AboutUs integralAndRMB = this.aboutUsService.selectIntegralAbout(9);
                    if (integralAndRMB == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("当前不可充值");
                    }
                    else if (amount % Integer.parseInt(integralAndRMB.getContent()) != 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("充值金额需为"+integralAndRMB.getContent()+"的整数倍");
                    }
                    else{
                        Double integralNumber = amount / Integer.parseInt(integralAndRMB.getContent());
                        RechargeOrder rechargeOrder = new RechargeOrder();
                        rechargeOrder.setUser_id(users.getId());
                        rechargeOrder.setRecharge_amount(amount);
                        rechargeOrder.setRecharge_type(0);
                        rechargeOrder.setPay_type(0);
                        rechargeOrder.setState(1);
                        rechargeOrder.setRecharge_time(new Date());
                        rechargeOrder.setSystem_order_no(UUDUtil.getOrderIdByUUId());
                        rechargeOrder.setPay_time(new Date());
                        rechargeOrder.setIntegral_number(integralNumber);
                        int rechargeState = this.rechargeOrderService.addUnderLineRechargeOrder(rechargeOrder);
                        if (rechargeState <= 0){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("下单失败");
                        }
                        else{
                            Double amount_double = Double.valueOf(amount);
                            //准备调起微信支付
                            Map<String,Object> resutMap = WechatPayLineUtil.wechatPayAppFileBuy(rechargeOrder.getSystem_order_no(), amount_double, request, response);
                            if ((Boolean) resutMap.get("status") == true){
                                Map<String,Object> messageMap = new HashMap<String,Object>();

                                SortedMap<String, String> parameterMap = (SortedMap<String, String>)resutMap.get("wx_config");
                                JSONObject json = JSONObject.fromObject(parameterMap);

                                Integer timestamp = (Integer) json.get("timestamp");

                                String payData = "{\"appid\":\""+json.get("appid")+"\",\"noncestr\":\""+json.get("noncestr")+"\",\"package\":\"Sign=WXPay\",\"partnerid\":\""+json.get("partnerid")+"\",\"prepayid\":\""+json.get("prepayid")+"\",\"timestamp\":"+timestamp+",\"sign\":\""+json.get("sign")+"\"}";
                                result.setData(payData);
                                result.setStatus(Result.SUCCESS);
                                result.setMsg("下单成功");
                            }
                            else{
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg((String)resutMap.get("msg"));
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("下单失败");
        }
        return result;
    }
}
