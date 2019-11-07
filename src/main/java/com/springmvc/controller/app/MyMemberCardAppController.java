package com.springmvc.controller.app;

import com.springmvc.entity.*;
import com.springmvc.service.impl.*;
import com.springmvc.utils.LoginSessionCheckUtil;
import com.springmvc.utils.MobileCodeUtil;
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
@RequestMapping(value = "/app/v1.0.0")
public class MyMemberCardAppController {
    @Resource
    private UsersService usersService;
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private ConsumptionIntegralExpenditureService consumptionIntegralExpenditureService;
    @Resource
    private GeneralIntegralExpenditureService generalIntegralExpenditureService;
    @Resource
    private SilverCoinDetailedService silverCoinDetailedService;
    @Resource
    private MemberLevelsService memberLevelsService;
    @Resource
    private OpenMemberCardWxPayOrderService openMemberCardWxPayOrderService;
    @Resource
    private UserOpenCardsService userOpenCardsService;
    @Resource
    private WnkBuyMealService wnkBuyMealService;

    // 会员卡升级/办理(type=-1:开卡,type=0:升级,pay_way=0:消费积分支付,pay_way=1:通用积分支付),card_type=0-青春万能卡,1-超级万能卡,user_type=0-商户开卡，user_type=1-个人开卡
    @RequestMapping(value = "/myMemberCardUpgradeOrHandle", method = RequestMethod.POST,params = {"user_id","type","member_card_level","pay_way","pay_pwd","card_type","user_type"})
    @ResponseBody
    public Result getMyCoupon(HttpServletRequest request, HttpServletResponse response, Integer user_id,Integer type,Integer member_card_level,Integer pay_way,String pay_pwd,Integer card_type,Integer user_type){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else if (pay_pwd.equals("") || pay_pwd == null){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("请输入支付密码");
            }
            else if (member_card_level != 0 && member_card_level != 1){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("数据不合法");
            }
            else if (type != 0 && type != -1){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("数据不合法");
            }
            else if (pay_way != 0 && pay_way != 1){
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
                else if (!users.getPay_pwd().equals(pay_pwd)){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("支付密码不正确");
                }
//                else if (users.getMember_card_level() == 0 && member_card_level == 0){
//                    result.setData("");
//                    result.setStatus(Result.FAIL);
//                    result.setMsg("您已是银卡会员");
//                }
                else{
                    WnkBuyMeal wnkBuyMeal = this.wnkBuyMealService.selectById(card_type);
                    if (wnkBuyMeal == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此万能卡套餐不存在");
                    }
                    else{
                        Integer consumtionIntegralNumber = wnkBuyMeal.getPrice();
//                    Integer getSoliverCoinNumber = card_type == 0?1:2;
                        if (pay_way == 0 && users.getConsumption_integral() < consumtionIntegralNumber.doubleValue()){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("余额不足");
                        }
                        else if (pay_way == 1 && users.getGeneral_integral() < consumtionIntegralNumber.doubleValue()){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("余额不足");
                        }
                        else{
                            //是否进行下一步操作(是否有推荐人)
                            Boolean status = true;
                            //升级
                            if (type == 0){
                                ConsumptionIntegralExpenditure consumptionIntegralExpenditure = null;
                                GeneralIntegralExpenditure generalIntegralExpenditure = null;
                                //消费积分支付
                                if (pay_way == 0){
                                    consumptionIntegralExpenditure = new ConsumptionIntegralExpenditure();
                                    consumptionIntegralExpenditure.setName("会员卡办理");
                                    consumptionIntegralExpenditure.setExpenditure_date(new Date());
                                    consumptionIntegralExpenditure.setExpenditure_amount(consumtionIntegralNumber.doubleValue());
                                    consumptionIntegralExpenditure.setExpenditure_after_balance(users.getConsumption_integral() - consumtionIntegralNumber.doubleValue());
                                    consumptionIntegralExpenditure.setUser_id(user_id);
                                    consumptionIntegralExpenditure.setExpenditure_type(2);
                                    users.setConsumption_integral(users.getConsumption_integral() - consumtionIntegralNumber.doubleValue());
                                }
                                //通用积分支付
                                else{
                                    generalIntegralExpenditure = new GeneralIntegralExpenditure();
//                                name,expenditure_date,expenditure_amount,expenditure_after_balance,user_id,expenditure_type
                                    generalIntegralExpenditure.setName("会员卡办理");
                                    generalIntegralExpenditure.setExpenditure_date(new Date());
                                    generalIntegralExpenditure.setExpenditure_amount(consumtionIntegralNumber.doubleValue());
                                    generalIntegralExpenditure.setExpenditure_after_balance(users.getGeneral_integral() - consumtionIntegralNumber.doubleValue());
                                    generalIntegralExpenditure.setUser_id(user_id);
                                    generalIntegralExpenditure.setExpenditure_type(3);
                                    users.setGeneral_integral(users.getGeneral_integral() - consumtionIntegralNumber.doubleValue());
                                }
                                int updateBalanceState = this.usersService.updateUserTYAndXFAndSilverCoinBalance(users);
                                if (updateBalanceState <= 0){
                                    status = false;
                                    result.setData("");
                                    result.setStatus(Result.FAIL);
                                    result.setMsg("办理失败");
                                }
                                else{
                                    users.setMember_card_level(wnkBuyMeal.getCard_type());
                                    int upgradeState = this.usersService.updateMemberCardLevel(users);
                                    if (updateBalanceState <= 0){
                                        status = false;
                                        result.setData("");
                                        result.setStatus(Result.FAIL);
                                        result.setMsg("办理失败");
                                    }
                                    else{
                                        int addState = 0;
                                        if (pay_way == 0){
                                            addState = this.consumptionIntegralExpenditureService.insertExchangeSilverCoinRecord(consumptionIntegralExpenditure);
                                        }
                                        else{
                                            addState = this.generalIntegralExpenditureService.insertExchangeSilverCoinRecord(generalIntegralExpenditure);
                                        }
                                        status = true;
                                        this.userOpenCardsService.userOpenCardOrRenew(user_id,card_type,user_type);
                                        this.userOpenCardsService.userOpenCardRecommendOption(user_id,wnkBuyMeal);
                                        result.setData("");
                                        result.setStatus(Result.SUCCESS);
                                        result.setMsg("办理成功");

                                        //发送开卡短信通知
                                        MobileCodeUtil.sendUserBuyWnkSms(users.getMobile(),0,consumtionIntegralNumber.doubleValue(),wnkBuyMeal.getName());
                                    }
                                }
                            }
                            //开卡
                            else{
                                ConsumptionIntegralExpenditure consumptionIntegralExpenditure = null;
                                GeneralIntegralExpenditure generalIntegralExpenditure = null;
                                //消费积分支付
                                if (pay_way == 0){
                                    consumptionIntegralExpenditure = new ConsumptionIntegralExpenditure();
                                    consumptionIntegralExpenditure.setName("会员卡办理");
                                    consumptionIntegralExpenditure.setExpenditure_date(new Date());
                                    consumptionIntegralExpenditure.setExpenditure_amount(consumtionIntegralNumber.doubleValue());
                                    consumptionIntegralExpenditure.setExpenditure_after_balance(users.getConsumption_integral() - consumtionIntegralNumber.doubleValue());
                                    consumptionIntegralExpenditure.setUser_id(user_id);
                                    consumptionIntegralExpenditure.setExpenditure_type(1);
                                    users.setConsumption_integral(users.getConsumption_integral() - consumtionIntegralNumber.doubleValue());
                                }
                                //通用积分支付
                                else{
                                    generalIntegralExpenditure = new GeneralIntegralExpenditure();
                                    generalIntegralExpenditure.setName("会员卡办理");
                                    generalIntegralExpenditure.setExpenditure_date(new Date());
                                    generalIntegralExpenditure.setExpenditure_amount(consumtionIntegralNumber.doubleValue());
                                    generalIntegralExpenditure.setExpenditure_after_balance(users.getGeneral_integral() - consumtionIntegralNumber.doubleValue());
                                    generalIntegralExpenditure.setUser_id(user_id);
                                    generalIntegralExpenditure.setExpenditure_type(2);
                                    users.setGeneral_integral(users.getGeneral_integral() - consumtionIntegralNumber.doubleValue());
                                }

                                int updateBalanceState = this.usersService.updateUserTYAndXFAndSilverCoinBalance(users);
                                if (updateBalanceState <= 0){
                                    status = false;
                                    result.setData("");
                                    result.setStatus(Result.FAIL);
                                    result.setMsg("办理失败");
                                }
                                else{
                                    users.setMember_card_level(wnkBuyMeal.getCard_type());
                                    int upgradeState = this.usersService.updateMemberCardLevel(users);
                                    if (updateBalanceState <= 0){
                                        status = false;
                                        result.setData("");
                                        result.setStatus(Result.FAIL);
                                        result.setMsg("办理失败");
                                    }
                                    else{
                                        int addState = 0;
                                        if (pay_way == 0){
                                            addState = this.consumptionIntegralExpenditureService.insertExchangeSilverCoinRecord(consumptionIntegralExpenditure);
                                        }
                                        else{
                                            addState = this.generalIntegralExpenditureService.insertExchangeSilverCoinRecord(generalIntegralExpenditure);
                                        }
                                        status = true;
                                        this.userOpenCardsService.userOpenCardOrRenew(user_id,card_type,user_type);
                                        this.userOpenCardsService.userOpenCardRecommendOption(user_id,wnkBuyMeal);
                                        result.setData("");
                                        result.setStatus(Result.SUCCESS);
                                        result.setMsg("办理成功");

                                        //发送开卡短信通知
                                        MobileCodeUtil.sendUserBuyWnkSms(users.getMobile(),0,consumtionIntegralNumber.doubleValue(),wnkBuyMeal.getName());
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



    // 会员卡开卡微信支付,card_type=0-青春万能卡,1-超级万能卡,user_type=0商家开卡，user_type=1个人开卡
    @RequestMapping(value = "/myMemberCardUpgradeOrHandleWXPay", method = RequestMethod.POST,params = {"user_id","member_card_level","card_type","user_type"})
    @ResponseBody
    public Result wxUnlineOrderPay(HttpServletRequest request, HttpServletResponse response, Integer user_id,Integer member_card_level,Integer card_type,Integer user_type){
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
                    Map<String,Object> messageMap = new HashMap<String,Object>();
                    if (users.getWx_unionid() == null || users.getWx_unionid().equals("")){
                        messageMap.put("wx_state",0);
                        result.setData(messageMap);
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("请先绑定微信");
                    }
                    else{
                        WnkBuyMeal wnkBuyMeal = this.wnkBuyMealService.selectById(card_type);
                        if (wnkBuyMeal == null){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("万能卡套餐不存在");
                        }
                        else{
                            card_type = wnkBuyMeal.getId();
                            OpenMemberCardWxPayOrder openMemberCardWxPayOrder = new OpenMemberCardWxPayOrder();
                            openMemberCardWxPayOrder.setOrder_no(UUDUtil.getOrderIdByUUId());
                            openMemberCardWxPayOrder.setUser_id(user_id);
                            openMemberCardWxPayOrder.setState(0);
                            openMemberCardWxPayOrder.setCreate_time(new Date());
                            openMemberCardWxPayOrder.setCard_type_id(card_type);
                            openMemberCardWxPayOrder.setUser_type(user_type);
                            int state = this.openMemberCardWxPayOrderService.insertOpenCardOrder(openMemberCardWxPayOrder);
                            if (state <= 0){
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("下单失败");
                            }
                            else{
                                Double amount = Double.valueOf(wnkBuyMeal.getPrice());
                                Map<String,Object> resutMap = WechatPayLineUtil.wechatPayOpenMemberCard(openMemberCardWxPayOrder.getOrder_no(), amount, users.getWx_unionid(), request, response);
                                if ((Boolean) resutMap.get("status") == true){
                                    messageMap.put("wx_state",1);
                                    messageMap.put("order_no",openMemberCardWxPayOrder.getOrder_no());
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


    // 会员卡开卡微信App支付,card_type=0-青春万能卡,1-超级万能卡,user_type=0商家开卡，user_type=1个人开卡
    @RequestMapping(value = "/myMemberCardUpgradeOrHandleWXPayApp", method = RequestMethod.POST,params = {"user_id","member_card_level","card_type","user_type"})
    @ResponseBody
    public Result myMemberCardUpgradeOrHandleWXPayApp(HttpServletRequest request, HttpServletResponse response, Integer user_id,Integer member_card_level,Integer card_type,Integer user_type){
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
                    Map<String,Object> messageMap = new HashMap<String,Object>();
                    WnkBuyMeal wnkBuyMeal = this.wnkBuyMealService.selectById(card_type);
                    if (wnkBuyMeal == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("万能卡套餐不存在");
                    }
                    else{
                        card_type = wnkBuyMeal.getId();
                        OpenMemberCardWxPayOrder openMemberCardWxPayOrder = new OpenMemberCardWxPayOrder();
                        openMemberCardWxPayOrder.setOrder_no(UUDUtil.getOrderIdByUUId());
                        openMemberCardWxPayOrder.setUser_id(user_id);
                        openMemberCardWxPayOrder.setState(0);
                        openMemberCardWxPayOrder.setCreate_time(new Date());
                        openMemberCardWxPayOrder.setCard_type_id(card_type);
                        openMemberCardWxPayOrder.setUser_type(user_type);
                        int state = this.openMemberCardWxPayOrderService.insertOpenCardOrder(openMemberCardWxPayOrder);
                        if (state <= 0){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("下单失败");
                        }
                        else{
                            Double amount = Double.valueOf(wnkBuyMeal.getPrice());
                            Map<String,Object> resutMap = WechatPayLineUtil.wechatPayOpenMemberCardApp(openMemberCardWxPayOrder.getOrder_no(), amount, request, response);
                            if ((Boolean) resutMap.get("status") == true){
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


    // 查询会员开卡微信支付是否成功
    @RequestMapping(value = "/searchMemberOpenCardWxPayState", method = RequestMethod.POST,params = {"user_id","order_no"})
    @ResponseBody
    public Result searchMemberOpenCardWxPayState(HttpServletRequest request, HttpServletResponse response, Integer user_id,String order_no){
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
                    OpenMemberCardWxPayOrder openMemberCardWxPayOrder = this.openMemberCardWxPayOrderService.selectRecordByOrderNo(order_no);
                    if (openMemberCardWxPayOrder.getState() == 1){
                        result.setData("");
                        result.setStatus(Result.SUCCESS);
                        result.setMsg("支付成功");
                    }
                    else if (openMemberCardWxPayOrder.getState() == 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("订单未支付");
                    }
                    else{
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("订单支付失败");
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
