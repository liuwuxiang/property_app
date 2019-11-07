package com.springmvc.controller.wnk_business_app;

import com.springmvc.dao.WnkBusinessConsumptionIntegralDetailMapper;
import com.springmvc.entity.*;
import com.springmvc.service.impl.*;
import com.springmvc.utils.GeTuiBusinessUtil;
import com.springmvc.utils.LoginSessionCheckUtil;
import com.springmvc.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangfan
 * @Date: 2018/10/28 22:13
 * @Description:万能卡商家App商家消费积分接口类
 */
@Controller
@RequestMapping(value = "/wnk_business_app/v1.0.0")
public class WnkBusinessConsumptionIntegralWnkBusinessAppController {
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private WnkBusinessAccountService wnkBusinessAccountService;
    @Resource
    private WnkBusinessConsumptionIntegralDetailService wnkBusinessConsumptionIntegralDetailService;
    @Resource
    private WnkBusinessBalanceDetailService wnkBusinessBalanceDetailService;
    @Resource
    private UsersService usersService;
    @Resource
    private IntegralDetailService integralDetailService;
    @Resource
    private WnkIntegralUserServer wnkIntegralUserServer;
    @Resource
    private WnkIntegralIncomeService wnkIntegralIncomeService;
    @Resource
    private WnkStoreInformationService wnkStoreInformationService;

    /**
     *
     * 功能描述: 获取万能卡商家消费积分明细
     *
     * @param: business_id,type(交易类型(0-收入,1-支出))
     * @return: Result
     * @author: zhangfan
     * @date: 2018/10/28 10:14 PM
     */
    @RequestMapping(value = "/getWnkBusinessConsumptionIntegralDetail", method = RequestMethod.POST,params = {"business_id","type"})
    @ResponseBody
    public Result getWnkBusinessConsumptionIntegralDetail(HttpServletRequest request, HttpServletResponse response, Integer business_id, Integer type){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id,3,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else if(type != 0 && type != 1){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("数据不合法");
            }
            else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                }
                else{
                    List<Map<Object,Object>> list = this.wnkBusinessConsumptionIntegralDetailService.selectRecordByBusinessId(business_id,type);
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
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }

    /**
     *
     * 功能描述: 万能卡商家消费积分兑换
     *
     * @param: business_id,integral_number(兑换积分个数)
     * @return: Result
     * @author: zhangfan
     * @date: 2018/10/28 10:14 PM
     */
    @RequestMapping(value = "/wnkBusinessConsumptionIntegralExchange", method = RequestMethod.POST,params = {"business_id","integral_number"})
    @ResponseBody
    public Result wnkBusinessConsumptionIntegralExchange(HttpServletRequest request, HttpServletResponse response, Integer business_id, Integer integral_number){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id,3,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else if(integral_number % 100 != 0){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("兑换积分个数需为100的整数倍");
            }
            else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                }
                else{
                    if (wnkBusinessAccount.getConsumption_integral() < integral_number){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("积分余额不足");
                    }
                    else{
                        //可获得账户余额
                        Double getBalance = integral_number * 0.01;

                        WnkBusinessConsumptionIntegralDetail wnkBusinessConsumptionIntegralDetail = new WnkBusinessConsumptionIntegralDetail();
                        wnkBusinessConsumptionIntegralDetail.setBusiness_id(business_id);
                        wnkBusinessConsumptionIntegralDetail.setName("积分兑换");
                        wnkBusinessConsumptionIntegralDetail.setIntegral_number(integral_number.doubleValue());
                        wnkBusinessConsumptionIntegralDetail.setTransactions_date(new Date());
                        wnkBusinessConsumptionIntegralDetail.setTransactions_type(1);

                        wnkBusinessAccount.setConsumption_integral(wnkBusinessAccount.getConsumption_integral() - integral_number);
                        wnkBusinessAccount.setBalance(wnkBusinessAccount.getBalance() + getBalance);
                        int balanceState = this.wnkBusinessAccountService.updateRelevantBalance(wnkBusinessAccount);
                        if (balanceState <= 0){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("兑换失败");
                        }
                        else{
                            WnkBusinessBalanceDetail wnkBusinessBalanceDetail = new WnkBusinessBalanceDetail();
                            wnkBusinessBalanceDetail.setBusiness_id(business_id);
                            wnkBusinessBalanceDetail.setName("消费积分兑换");
                            wnkBusinessBalanceDetail.setTransaction_amount(getBalance);
                            wnkBusinessBalanceDetail.setJoin_time(new Date());
                            wnkBusinessBalanceDetail.setAfter_balance(wnkBusinessAccount.getBalance());
                            wnkBusinessBalanceDetail.setType(0);
                            wnkBusinessBalanceDetail.setState(0);
                            this.wnkBusinessBalanceDetailService.insertNewRecord(wnkBusinessBalanceDetail);
                            this.wnkBusinessConsumptionIntegralDetailService.insertNewRecord(wnkBusinessConsumptionIntegralDetail);
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("兑换成功");

                            WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(wnkBusinessAccount.getId());
                            if (wnkStoreInformation != null){
                                if (wnkStoreInformation.getGetui_appid() != null && !wnkStoreInformation.getGetui_appid().equals("")){
                                    String pushTitle = "推广积分支出";
                                    String pushContent = "您已成功提现"+integral_number+"个推广积分到我的账户中，请在猛戳-我的积分-支出明细查看";
                                    GeTuiBusinessUtil.pushUser(wnkStoreInformation.getGetui_appid(),pushTitle,pushContent);
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


    /**
     *
     * 功能描述: 用户线下现金支付商家积分及平台积分充值
     *
     * @param: business_id,integral_number(充值积分个数),user_mobile
     * @return: Result
     * @author: zhangfan
     * @date: 2018/10/28 10:14 PM
     */
    @RequestMapping(value = "/userUnderLinePayWnkBusinessRechargeIntegral", method = RequestMethod.POST,params = {"business_id","integral_number","user_mobile"})
    @ResponseBody
    public Result userUnderLinePayWnkBusinessRechargeIntegral(HttpServletRequest request, HttpServletResponse response, Integer business_id, Double integral_number,String user_mobile){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id,3,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else if(integral_number % 1 != 0){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("充值积分必须为正整数");
            }

            else if (user_mobile.length() != 11){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("用户手机号不合法");
            }
            else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                }
                else{
                    Users users = this.usersService.selectByMobile(user_mobile);
                    if (users == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此用户手机号不存在");
                    }
                    else{

                        int plamtState = this.integralDetailService.insertIntrgralDetailRecord(users.getId(),"现金支付消费",integral_number,0);
                        if (plamtState <= 0){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("充值失败");
                        }
                        else{
                            Map<String,Object> businessIntegralMap = this.wnkIntegralUserServer.getUserIntegral(business_id,users.getId());
                            if (businessIntegralMap == null){
                                this.wnkIntegralUserServer.addUserIntegral(business_id,users.getId());
                            }
                            int state = this.wnkIntegralUserServer.increaseUserIntegral(users.getId(),business_id,integral_number);
                            if (state <= 0){
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("充值失败");
                            }
                            else{
                                this.wnkIntegralIncomeService.addIntegralRecord("现金支付消费",integral_number,users.getId(),business_id,0);
                                result.setData("");
                                result.setStatus(Result.SUCCESS);
                                result.setMsg("充值成功");
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
