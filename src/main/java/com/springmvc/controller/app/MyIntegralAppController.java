package com.springmvc.controller.app;

import com.springmvc.entity.*;
import com.springmvc.service.impl.GeneralIntegralIncomeService;
import com.springmvc.service.impl.IntegralDetailService;
import com.springmvc.service.impl.LoginSessionIdsService;
import com.springmvc.service.impl.UsersService;
import com.springmvc.utils.GeTuiUserUtil;
import com.springmvc.utils.LoginSessionCheckUtil;
import com.springmvc.utils.Result;
import com.springmvc.utils.UUDUtil;
import com.springmvc.utils.wechat.WechatPayLineUtil;
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

/**
 * @author: zhangfan
 * @Date: 2018/10/23 11:40
 * @Description:平台积分App接口
 */
@Controller
@RequestMapping(value = "/app/v1.0.0")
public class MyIntegralAppController {
    @Resource
    private UsersService usersService;
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private IntegralDetailService integralDetailService;
    @Resource
    private GeneralIntegralIncomeService generalIntegralIncomeService;


    // 我的积分提现
    @RequestMapping(value = "/myIntegralForward", method = RequestMethod.POST,params = {"user_id","withdraw_number","user_pay_pwd"})
    @ResponseBody
    public Result myIntegralForward(HttpServletRequest request, HttpServletResponse response, Integer user_id, Integer withdraw_number, String user_pay_pwd){
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
                    if (users.getUser_integral() < withdraw_number.doubleValue()){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("积分余额不足");
                    }
                    else if (!users.getPay_pwd().equals(user_pay_pwd)){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("支付密码不正确");
                    }
                    else if (withdraw_number % 100 != 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("提现积分个数必须为100的整数倍");
                    }
                    else{
                        IntegralDetail integralDetail = new IntegralDetail();
                        integralDetail.setName("提现");
                        integralDetail.setType(1);
                        integralDetail.setUser_id(user_id);
                        integralDetail.setTransaction_integral_number(withdraw_number.doubleValue());
                        integralDetail.setTransaction_date(new Date());
                        int addIntegralState = this.integralDetailService.insertIntrgralDetailRecord(user_id,"提现",withdraw_number.doubleValue(),1);
                        if (addIntegralState <= 0){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("提现失败");
                        }
                        else{
                            users.setUser_integral(users.getUser_integral() - withdraw_number);
                            int updateState = this.usersService.updateUserTYAndXFAndSilverCoinBalance(users);
                            if (updateState <= 0){
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("提现失败");
                            }
                            else{
                                Double generalNumberDouble = withdraw_number * 0.01;
                                Integer generalNumberIntegrl = generalNumberDouble.intValue();

                                users.setGeneral_integral(users.getGeneral_integral() + generalNumberIntegrl);
                                Integer balanceUpdateState = this.usersService.updateUserTYAndXFAndSilverCoinBalance(users);
                                if (balanceUpdateState <= 0){
                                    result.setData("");
                                    result.setStatus(Result.FAIL);
                                    result.setMsg("提现失败");
                                }
                                else{
                                    GeneralIntegralIncome generalIntegralIncome = new GeneralIntegralIncome();
                                    generalIntegralIncome.setName("平台积分提现");
                                    generalIntegralIncome.setIncome_date(new Date());
                                    generalIntegralIncome.setIncome_amount(generalNumberIntegrl.doubleValue());
                                    generalIntegralIncome.setIncome_after_balance(users.getGeneral_integral());
                                    generalIntegralIncome.setUser_id(user_id);
                                    generalIntegralIncome.setIncome_type(5);
                                    int addState = this.generalIntegralIncomeService.insertUserSilverCoinWithdrawRecord(generalIntegralIncome);
                                    if (addState <= 0){
                                        result.setData("");
                                        result.setStatus(Result.FAIL);
                                        result.setMsg("提现失败");
                                    }
                                    else{
                                        result.setData("");
                                        result.setStatus(Result.SUCCESS);
                                        result.setMsg("提现成功");

                                        if (users.getGetui_appid() != null && !users.getGetui_appid().equals("")){
                                            String pushTitle = "平台积分支出";
                                            String pushContent = "您已成功兑换"+withdraw_number+"个平台积分到通用积分中，请在猛戳-我的积分查看";
                                            GeTuiUserUtil.pushUser(users.getGetui_appid(),pushTitle,pushContent);
                                        }
                                    }
                                }
                            }
                        }
                    }
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
