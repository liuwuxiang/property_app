package com.springmvc.controller.app;

import com.springmvc.entity.*;
import com.springmvc.service.impl.*;
import com.springmvc.utils.LoginSessionCheckUtil;
import com.springmvc.utils.Result;
import com.springmvc.utils.UUDUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/app/v1.0.0")
public class SilverCoinExchangeAppController {
    @Resource
    private UsersService usersService;
    @Resource
    private SilverCoinDetailedService silverCoinDetailedService;
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private GeneralIntegralExpenditureService generalIntegralExpenditureService;
    @Resource
    private ConsumptionIntegralExpenditureService consumptionIntegralExpenditureService;
    @Resource
    private AboutUsService aboutUsService;
    @Resource
    private SilverCoinExchangeService silverCoinExchangeService;
    @Resource
    private SystemMessagesService systemMessagesService;
    @Resource
    private UserIdCardAuthenticationService userIdCardAuthenticationService;
    @Resource
    private UsersCertificatesService usersCertificatesService;

    // 银币兑换(type为兑换类型,0-消费积分兑换银币,1-通用积分兑换银币)
    @RequestMapping(value = "/silverCoinExchange", method = RequestMethod.POST,params = {"user_id","user_pay_pwd","silver_coin_number","type"})
    @ResponseBody
    public Result silverCoinExchange(HttpServletRequest request, HttpServletResponse response, Integer user_id,String user_pay_pwd,Integer silver_coin_number,Integer type){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else if (silver_coin_number <= 0){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("兑换银币个数需大于0且为整数");
            }
            else if (silver_coin_number != 20 && silver_coin_number != 50){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("兑换银币个数需为20个或50个");
            }
            else if (type != 0 && type != 1){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("数据不合法");
            }
            else {
                Users users = this.usersService.selectById(user_id);
                if (users == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                }
                else if (users.getPay_pwd() == null || users.getPay_pwd().equals("")){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("请先设置支付密码");
                }
                else if (!users.getPay_pwd().equals(user_pay_pwd)){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("支付密码不正确");
                }
                else{
                    UserIdCardAuthentication userIdCardAuthentication = this.userIdCardAuthenticationService.selectAuthenticationByUserId(users.getId());
                    if (userIdCardAuthentication == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("请先进行实名认证");
                    }
                    else{//所需积分个数
                        Integer integralNumber = 0;
                        AboutUs aboutUs = this.aboutUsService.selectIntegralAbout(type == 0?7:8);
                        ConsumptionIntegralExpenditure consumptionIntegralExpenditure = null;
                        GeneralIntegralExpenditure generalIntegralExpenditure = null;
                        SilverCoinExchange silverCoinExchange = new SilverCoinExchange();
                        SilverCoinDetailed silverCoinDetailed = new SilverCoinDetailed();
                        Date nowDate = new Date();
                        if (aboutUs == null){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg(type==0?"消费积分":"通用积分"+"当前不可兑换");
                        }
                        else{
                            integralNumber = silver_coin_number * Integer.parseInt(aboutUs.getContent());
                            if (type == 0){
                                if (users.getConsumption_integral() < integralNumber.doubleValue()){
                                    result.setData("");
                                    result.setStatus(Result.FAIL);
                                    result.setMsg("积分余额不足");
                                    return  result;
                                }
                                else{
                                    users.setConsumption_integral(users.getConsumption_integral() - integralNumber.doubleValue());
                                    consumptionIntegralExpenditure = new ConsumptionIntegralExpenditure();
                                    consumptionIntegralExpenditure.setName("兑换银币");
                                    consumptionIntegralExpenditure.setExpenditure_date(nowDate);
                                    consumptionIntegralExpenditure.setExpenditure_amount(integralNumber.doubleValue());
                                    consumptionIntegralExpenditure.setExpenditure_after_balance(users.getConsumption_integral());
                                    consumptionIntegralExpenditure.setUser_id(user_id);
                                    consumptionIntegralExpenditure.setExpenditure_type(0);
                                }
                            }
                            else{
                                if (users.getGeneral_integral() < integralNumber.doubleValue()){
                                    result.setData("");
                                    result.setStatus(Result.FAIL);
                                    result.setMsg("积分余额不足");
                                    return  result;
                                }
                                else{
                                    users.setGeneral_integral(users.getGeneral_integral() - integralNumber.doubleValue());
                                    generalIntegralExpenditure = new GeneralIntegralExpenditure();
                                    generalIntegralExpenditure.setName("银币兑换");
                                    generalIntegralExpenditure.setExpenditure_date(nowDate);
                                    generalIntegralExpenditure.setExpenditure_amount(integralNumber.doubleValue());
                                    generalIntegralExpenditure.setExpenditure_after_balance(users.getGeneral_integral());
                                    generalIntegralExpenditure.setUser_id(user_id);
                                    generalIntegralExpenditure.setExpenditure_type(0);
                                }
                            }
                            int updateUsersBalanceState = this.usersService.updateUserTYAndXFAndSilverCoinBalance(users);
                            if (updateUsersBalanceState >= 1){
                                int insertState = 0;
                                if (type == 0){
                                    insertState = this.consumptionIntegralExpenditureService.insertExchangeSilverCoinRecord(consumptionIntegralExpenditure);
                                }
                                else{
                                    insertState = this.generalIntegralExpenditureService.insertExchangeSilverCoinRecord(generalIntegralExpenditure);
                                }
                                if (insertState >= 1){
                                    users.setSilver_coin_balance(users.getSilver_coin_balance() + silver_coin_number);
                                    int updaeSilverCionBalanceState = this.usersService.updateUserTYAndXFAndSilverCoinBalance(users);
                                    if (updaeSilverCionBalanceState >= 1){
                                        silverCoinDetailed.setName(type == 0?"消费积分兑换":"通用积分兑换");
                                        silverCoinDetailed.setRecord_date(nowDate);
                                        silverCoinDetailed.setTransaction_amount(silver_coin_number);
                                        silverCoinDetailed.setTransaction_after_balance(users.getSilver_coin_balance());
                                        silverCoinDetailed.setIncome_expenditure_type(1);
                                        silverCoinDetailed.setTransaction_type(type == 0?1:2);
                                        silverCoinDetailed.setUser_id(user_id);
                                        int silverCoinDetailInsertState = this.silverCoinDetailedService.insertExchangeRecord(silverCoinDetailed);
                                        if (silverCoinDetailInsertState >= 1){
                                            silverCoinExchange.setSilver_coin_number(silver_coin_number);
                                            silverCoinExchange.setConsume_integral_number(integralNumber);
                                            silverCoinExchange.setType(type);
                                            silverCoinExchange.setExchange_date(nowDate);
                                            silverCoinExchange.setOrder_no(UUDUtil.getOrderIdByUUId());
                                            silverCoinExchange.setUser_id(user_id);
                                            silverCoinExchange.setExpenditure_id(consumptionIntegralExpenditure==null?generalIntegralExpenditure.getId():consumptionIntegralExpenditure.getId());
                                            silverCoinExchange.setSilver_coin_detail_id(silverCoinDetailed.getId());
                                            int silverCoinExchangeAddState = this.silverCoinExchangeService.insertExchangeRecord(silverCoinExchange);
                                            if (silverCoinExchangeAddState >= 1){
                                                result.setData("");
                                                result.setStatus(Result.SUCCESS);
                                                result.setMsg("兑换成功");
                                                this.usersCertificatesService.generateCertificate(userIdCardAuthentication.getReal_name(),users.getId(),integralNumber,silver_coin_number,silverCoinExchange.getId());
                                                this.silverCoinDetailedService.insertTeamExchangeRecord(user_id,silver_coin_number);
                                                this.usersService.userExchangeSlivreCoinUpdateUserLevle(integralNumber,users);
                                                this.systemMessagesService.addSoliverExahngeSuccessMessgae(user_id,integralNumber,silver_coin_number);
                                            }
                                            else{
                                                if (type == 0){
                                                    users.setConsumption_integral(users.getConsumption_integral()+integralNumber);
                                                    this.consumptionIntegralExpenditureService.deleteRecordById(consumptionIntegralExpenditure.getId());
                                                }
                                                else{
                                                    users.setGeneral_integral(users.getGeneral_integral() + integralNumber);
                                                    this.generalIntegralExpenditureService.deleteRecordById(generalIntegralExpenditure.getId());
                                                }
                                                users.setSilver_coin_balance(users.getSilver_coin_balance() - silver_coin_number);
                                                this.usersService.updateUserTYAndXFAndSilverCoinBalance(users);
                                                this.silverCoinDetailedService.deleteRecordById(silverCoinDetailed.getId());
                                                result.setData("");
                                                result.setStatus(Result.FAIL);
                                                result.setMsg("兑换失败");
                                            }
                                        }
                                        else{
                                            if (type == 0){
                                                users.setConsumption_integral(users.getConsumption_integral()+integralNumber);
                                                this.consumptionIntegralExpenditureService.deleteRecordById(consumptionIntegralExpenditure.getId());
                                            }
                                            else{
                                                users.setGeneral_integral(users.getGeneral_integral() + integralNumber);
                                                this.generalIntegralExpenditureService.deleteRecordById(generalIntegralExpenditure.getId());
                                            }
                                            users.setSilver_coin_balance(users.getSilver_coin_balance() - silver_coin_number);
                                            this.usersService.updateUserTYAndXFAndSilverCoinBalance(users);
                                            result.setData("");
                                            result.setStatus(Result.FAIL);
                                            result.setMsg("兑换失败");
                                        }

                                    }
                                    else{
                                        if (type == 0){
                                            users.setConsumption_integral(users.getConsumption_integral()+integralNumber);
                                            this.consumptionIntegralExpenditureService.deleteRecordById(consumptionIntegralExpenditure.getId());
                                        }
                                        else{
                                            users.setGeneral_integral(users.getGeneral_integral() + integralNumber);
                                            this.generalIntegralExpenditureService.deleteRecordById(generalIntegralExpenditure.getId());
                                        }
                                        users.setSilver_coin_balance(users.getSilver_coin_balance() - silver_coin_number);
                                        this.usersService.updateUserTYAndXFAndSilverCoinBalance(users);
                                        result.setData("");
                                        result.setStatus(Result.FAIL);
                                        result.setMsg("兑换失败");
                                    }
                                }
                                else{
                                    if (type == 0){
                                        users.setConsumption_integral(users.getConsumption_integral()+integralNumber);
                                    }
                                    else{
                                        users.setGeneral_integral(users.getGeneral_integral() + integralNumber);
                                    }
                                    this.usersService.updateUserTYAndXFAndSilverCoinBalance(users);
                                    result.setData("");
                                    result.setStatus(Result.FAIL);
                                    result.setMsg("兑换失败");
                                }
                            }
                            else{
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("兑换失败");
                            }
                        }
                    }

                }
            }
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("兑换失败");
        }
        return result;
    }
}
