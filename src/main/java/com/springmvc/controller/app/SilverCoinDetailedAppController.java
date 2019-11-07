package com.springmvc.controller.app;

import com.springmvc.entity.AboutUs;
import com.springmvc.entity.GeneralIntegralIncome;
import com.springmvc.entity.SilverCoinDetailed;
import com.springmvc.entity.Users;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/app/v1.0.0")
public class SilverCoinDetailedAppController {
    @Resource
    private UsersService usersService;
    @Resource
    private SilverCoinDetailedService silverCoinDetailedService;
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private AboutUsService aboutUsService;
    @Resource
    private GeneralIntegralIncomeService generalIntegralIncomeService;

    // 获取银币收支明细
    @RequestMapping(value = "/getSilverCoinDetail", method = RequestMethod.POST,params = {"user_id"})
    @ResponseBody
    public Result getSilverCoinDetail(HttpServletRequest request, HttpServletResponse response, Integer user_id){
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
                    List<Map<Object,Object>> list = this.silverCoinDetailedService.selectSilverCoinDetailByUser(user_id);
                    if (list.size() <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂无数据");
                    }
                    else{
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

    // 获取所有可提现的银币记录
    @RequestMapping(value = "/getCanWithdrawSilverCoinDetail", method = RequestMethod.POST,params = {"user_id"})
    @ResponseBody
    public Result getCanWithdrawSilverCoinDetail(HttpServletRequest request, HttpServletResponse response, Integer user_id){
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
                    List<Map<Object,Object>> list = this.silverCoinDetailedService.getCanWithdrawSilverCoinDetail(user_id);
                    if (list.size() <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂无数据");
                    }
                    else{
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

    // 用户银币提现
    @RequestMapping(value = "/userSilverCoinWithdraw", method = RequestMethod.POST,params = {"user_id","pay_pwd"})
    @ResponseBody
    public Result userSilverCoinWithdraw(HttpServletRequest request, HttpServletResponse response, Integer user_id,String pay_pwd){
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
                    Integer canWithdrawSilverCoinBalance = this.silverCoinDetailedService.getCanWithdrawSilverCoinBalance(user_id);
                    Integer serviceCharge = Integer.parseInt(new java.text.DecimalFormat("0").format(canWithdrawSilverCoinBalance * 0.1));
                    Integer accountNumber = Integer.parseInt(new java.text.DecimalFormat("0").format(canWithdrawSilverCoinBalance * 0.3));
                    if (accountNumber <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("当前无可提现银币");
                    }
                    else if (!users.getPay_pwd().equals(pay_pwd)){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("支付密码不正确");
                    }
                    else{
                        AboutUs aboutUs = this.aboutUsService.selectIntegralAbout(8);
                        if (aboutUs == null){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("提现失败");
                        }
                        else if (aboutUs.getContent() == null || aboutUs.getContent().equals("")){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("提现失败");
                        }
                        else{
                            users.setSilver_coin_balance(users.getSilver_coin_balance() - serviceCharge - accountNumber);
                            users.setGeneral_integral(users.getGeneral_integral() + accountNumber * Integer.parseInt(aboutUs.getContent()));
                            int silverCoinUpdateState = this.silverCoinDetailedService.updateCanWithdrawState(user_id);
                            if (silverCoinUpdateState >= 1){
                                int userBalanceUpdateState = this.usersService.updateUserTYAndXFAndSilverCoinBalance(users);
                                if (userBalanceUpdateState >= 1){
                                    result.setData("");
                                    result.setStatus(Result.SUCCESS);
                                    result.setMsg("提现成功");
                                    //新增通用积分收入明细
                                    GeneralIntegralIncome generalIntegralIncome = new GeneralIntegralIncome();
                                    generalIntegralIncome.setIncome_amount(accountNumber * Double.parseDouble(aboutUs.getContent()));
                                    generalIntegralIncome.setIncome_after_balance(users.getGeneral_integral());
                                    generalIntegralIncome.setUser_id(user_id);
                                    this.generalIntegralIncomeService.insertUserSilverCoinWithdrawRecord(generalIntegralIncome);
                                    //新增银币支出明细
                                    SilverCoinDetailed silverCoinDetailed = new SilverCoinDetailed();
                                    silverCoinDetailed.setTransaction_amount(serviceCharge+accountNumber);
                                    silverCoinDetailed.setTransaction_after_balance(users.getSilver_coin_balance());
                                    silverCoinDetailed.setUser_id(user_id);
                                    silverCoinDetailed.setService_charge(serviceCharge);
                                    this.silverCoinDetailedService.insertWithdrawRecord(silverCoinDetailed);

                                }
                                else{
                                    result.setData("");
                                    result.setStatus(Result.FAIL);
                                    result.setMsg("提现失败");
                                }
                            }
                            else{
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("提现失败");
                            }
                        }

                    }
                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("提现失败");
        }
        return result;
    }
}
