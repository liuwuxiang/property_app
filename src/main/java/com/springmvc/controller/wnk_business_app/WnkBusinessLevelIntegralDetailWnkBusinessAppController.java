package com.springmvc.controller.wnk_business_app;

import com.springmvc.entity.*;
import com.springmvc.service.impl.*;
import com.springmvc.utils.*;
import com.springmvc.utils.wechat.WechatPayLineUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: zhangfan
 * @Date: 2018/10/28 23:24
 * @Description:万能卡商家等级积分接口类
 */
@Controller
@RequestMapping(value = "/wnk_business_app/v1.0.0")
public class WnkBusinessLevelIntegralDetailWnkBusinessAppController {
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private WnkBusinessAccountService wnkBusinessAccountService;
    @Resource
    private WnkBusinessLevelIntegralDetailService wnkBusinessLevelIntegralDetailService;
    @Resource
    private WnkBusinessLevelService wnkBusinessLevelService;
    @Resource
    private WnkBusinessUpgradeOrderService wnkBusinessUpgradeOrderService;
    @Resource
    private WnkBusinessRechargeOrderService wnkBusinessRechargeOrderService;
    @Resource
    private UsersService usersService;
    @Resource
    private WnkIntegralIncomeService wnkIntegralIncomeService;
    @Resource
    private WnkIntegralUserServer wnkIntegralUserServer;
    @Resource
    private WnkBusinessBalanceDetailService wnkBusinessBalanceDetailService;
    @Resource
    private WnkStoreInformationService wnkStoreInformationService;
    @Resource
    private WnkSendIntegralUserService wnkSendIntegralUserService;
    @Resource
    private WnkSendIntegralDetailUserService wnkSendIntegralDetailUserService;
    @Resource
    private SystemMessagesService systemMessagesService;
    @Resource
    private WnkBusinessSystemMessageService wnkBusinessSystemMessageService;
    @Resource
    private IntegralDetailService integralDetailService;

    /**
     *
     * 功能描述: 获取万能卡商家等级积分明细
     *
     * @param: business_id,type(交易类型(0-收入,1-支出))
     * @return: Result
     * @author: zhangfan
     * @date: 2018/10/28 10:14 PM
     */
    @RequestMapping(value = "/getWnkBusinessLevelIntegralDetail", method = RequestMethod.POST,params = {"business_id","type"})
    @ResponseBody
    public Result getWnkBusinessLevelIntegralDetail(HttpServletRequest request, HttpServletResponse response, Integer business_id, Integer type){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id,3,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else if(type != 0 && type != 1 && type != 2){
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
                    List<Map<Object,Object>> list = this.wnkBusinessLevelIntegralDetailService.selectRecordByBusinessId(business_id,type);
                    if (list.size() <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("暂无数据");
                    }
                    else{
                        for (Integer index = 0;index < list.size();index++){
                            Map<Object,Object> map = list.get(index);
                            String user_mobile = (String)map.get("user_mobile");
                            if (user_mobile == null){
                                map.put("user_mobile","");
                            }
                            else{
                                String phoneNumber = user_mobile.substring(0, 3) + "****" + user_mobile.substring(7, user_mobile.length());
                                map.put("user_mobile",phoneNumber);
                            }
                        }

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
     * 功能描述: 获取万能卡商家所有可选的等级
     *
     * @param: business_id
     * @return: Result
     * @author: zhangfan
     * @date: 2018/10/28 10:14 PM
     */
    @RequestMapping(value = "/getWnkBusinessAllLevel", method = RequestMethod.POST,params = {"business_id"})
    @ResponseBody
    public Result getWnkBusinessAllLevel(HttpServletRequest request, HttpServletResponse response, Integer business_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id,3,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                }
                else{
                    List<Map<Object,Object>> list = this.wnkBusinessLevelService.selectNoDefaultLevel();
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
     * 功能描述: 商户升级商家等级下单 - 微信
     *
     * @param: business_id,level_id(等级id)
     * @return: Result
     * @author: zhangfan
     * @date: 2018/10/28 10:14 PM
     */
    @RequestMapping(value = "/wnkBusinessUpgradeLevelLineOrder", method = RequestMethod.POST,params = {"business_id","level_id"})
    @ResponseBody
    public Result wnkBusinessUpgradeLevelLineOrder(HttpServletRequest request, HttpServletResponse response, Integer business_id, Integer level_id){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id,3,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                }
                else{
                    WnkBusinessLevel wnkBusinessLevel = this.wnkBusinessLevelService.selectById(level_id);
                    if (wnkBusinessLevel == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此等级不存在");
                    }
                    else{
                        Map<String,Object> messageMap = new HashMap<String,Object>();
                        WnkBusinessUpgradeOrder wnkBusinessUpgradeOrder = new WnkBusinessUpgradeOrder();
                        wnkBusinessUpgradeOrder.setOrder_no(UUDUtil.getOrderIdByUUId());
                        wnkBusinessUpgradeOrder.setBusiness_id(business_id);
                        wnkBusinessUpgradeOrder.setState(0);
                        wnkBusinessUpgradeOrder.setCreate_time(new Date());
                        wnkBusinessUpgradeOrder.setLevel_id(wnkBusinessLevel.getId());
                        int state = this.wnkBusinessUpgradeOrderService.insertBusinessUpgradeOrder(wnkBusinessUpgradeOrder);
                        if (state <= 0){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("下单失败");
                        }
                        else{
                            Double amount = Double.valueOf(wnkBusinessLevel.getPrice());
                            Map<String,Object> resutMap = WechatPayLineUtil.wechatPayWnkBusinessLevelUpgrade(wnkBusinessUpgradeOrder.getOrder_no(), amount, request, response);
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
            result.setMsg("查询失败");
        }
        return result;
    }

    /**
     *
     * 功能描述: 商户升级商家等级下单 - 余额
     *
     * @param: business_id,level_id(等级id)
     * @return: Result
     * @author: zhangfan
     * @date: 2018/10/28 10:14 PM
     */
    @RequestMapping(value = "/wnkBusinessUpgradeLevelLineOrderByBalance", method = RequestMethod.POST,params = {"business_id","level_id","pay_text"})
    @ResponseBody
    public Result wnkBusinessUpgradeLevelLineOrderByBalance(HttpServletRequest request, HttpServletResponse response, Integer business_id, Integer level_id,String pay_text){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id,3,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else if(pay_text == null || pay_text.equals("")){
                result.setData("");
                result.setStatus(Result.FAIL);
                result.setMsg("请输入支付密码");
            }
            else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                }
                else if(wnkBusinessAccount.getPay_pwd() == null || wnkBusinessAccount.getPay_pwd().equals("")){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("请先设置支付密码");
                }
                else if (!wnkBusinessAccount.getPay_pwd().equals(pay_text)){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("支付密码不正确");
                }
                else{
                    WnkBusinessLevel wnkBusinessLevel = this.wnkBusinessLevelService.selectById(level_id);
                    if (wnkBusinessLevel == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此等级不存在");
                    }
                    else if (wnkBusinessLevel.getPrice() > wnkBusinessAccount.getBalance()){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("账户余额不足");
                    }
                    else{

                        wnkBusinessAccount.setBalance(wnkBusinessAccount.getBalance() - wnkBusinessLevel.getPrice());
                        int updateBalanceState = this.wnkBusinessAccountService.updateRelevantBalance(wnkBusinessAccount);
                        if (updateBalanceState <= 0){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("购买失败");
                        }
                        else{

                            Map<String,Object> messageMap = new HashMap<String,Object>();
                            WnkBusinessUpgradeOrder wnkBusinessUpgradeOrder = new WnkBusinessUpgradeOrder();
                            wnkBusinessUpgradeOrder.setOrder_no(UUDUtil.getOrderIdByUUId());
                            wnkBusinessUpgradeOrder.setBusiness_id(business_id);
                            wnkBusinessUpgradeOrder.setState(0);
                            wnkBusinessUpgradeOrder.setCreate_time(new Date());
                            wnkBusinessUpgradeOrder.setLevel_id(wnkBusinessLevel.getId());
                            int state = this.wnkBusinessUpgradeOrderService.insertBusinessUpgradeOrder(wnkBusinessUpgradeOrder);
                            if (state <= 0){
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("下单失败");
                            }else{
                                // 添加记录
                                WnkBusinessBalanceDetail wnkBusinessBalanceDetail = new WnkBusinessBalanceDetail();
                                wnkBusinessBalanceDetail.setBusiness_id(business_id);
                                wnkBusinessBalanceDetail.setName("购买商户等级-"+wnkBusinessLevel.getLevel_name());
                                wnkBusinessBalanceDetail.setTransaction_amount(wnkBusinessLevel.getPrice());
                                wnkBusinessBalanceDetail.setJoin_time(new Date());
                                wnkBusinessBalanceDetail.setAfter_balance(wnkBusinessAccount.getBalance());
                                wnkBusinessBalanceDetail.setType(1);
                                wnkBusinessBalanceDetail.setState(0);
                                this.wnkBusinessBalanceDetailService.insertNewRecord(wnkBusinessBalanceDetail);

                                wnkBusinessLevel = this.wnkBusinessLevelService.selectById(wnkBusinessUpgradeOrder.getLevel_id());
                                if (wnkBusinessLevel != null){
                                    WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(wnkBusinessUpgradeOrder.getBusiness_id());
                                    if (wnkStoreInformation != null){
                                        WnkBusinessLevel wnkBusinessLevelCurrent = this.wnkBusinessLevelService.selectById(wnkStoreInformation.getBusiness_level_id());
                                        if (wnkBusinessLevelCurrent != null && wnkBusinessLevelCurrent.getLebel_max() < wnkBusinessLevel.getLebel_max()){
                                            wnkStoreInformation.setBusiness_level_id(wnkBusinessLevel.getId());
                                        }


                                        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd,hh:mm:ss");
                                        Calendar calendar = Calendar.getInstance();
                                        if (wnkStoreInformation.getLevel_term_validity() == null){
                                            calendar.setTime(new Date());
                                        }
                                        else{
                                            calendar.setTime(wnkStoreInformation.getLevel_term_validity());
                                        }
                                        calendar.add(Calendar.MONTH, wnkBusinessLevel.getTerm_validity());

                                        wnkStoreInformation.setLevel_term_validity(calendar.getTime());

                                        int upgradeState = this.wnkStoreInformationService.updateBusinessLevel(wnkStoreInformation);
                                        if (upgradeState > 0){
                                            wnkBusinessAccount = this.wnkBusinessAccountService.selectById(wnkBusinessUpgradeOrder.getBusiness_id());
                                            if (wnkBusinessAccount != null){
                                                wnkBusinessAccount.setLevel_integral(wnkBusinessAccount.getLevel_integral() + wnkBusinessLevel.getObtain_integral());
                                                int balanceUpdateState = this.wnkBusinessAccountService.updateRelevantBalance(wnkBusinessAccount);
                                                WnkBusinessLevelIntegralDetail wnkBusinessLevelIntegralDetail = new WnkBusinessLevelIntegralDetail();
                                                wnkBusinessLevelIntegralDetail.setBusiness_id(wnkBusinessAccount.getId());
                                                wnkBusinessLevelIntegralDetail.setName("等级升级");
                                                wnkBusinessLevelIntegralDetail.setIntegral_number(wnkBusinessLevel.getObtain_integral());
                                                wnkBusinessLevelIntegralDetail.setTransactions_date(new Date());
                                                wnkBusinessLevelIntegralDetail.setTransactions_type(0);
                                                this.wnkBusinessLevelIntegralDetailService.insertNewRecord(wnkBusinessLevelIntegralDetail);
                                                if (wnkStoreInformation.getRecommend_business_id() != -1){
                                                    WnkBusinessAccount wnkBusinessAccountRecommend = this.wnkBusinessAccountService.selectById(wnkStoreInformation.getRecommend_business_id());
                                                    if (wnkBusinessAccountRecommend != null){
                                                        wnkBusinessAccountRecommend.setBalance(wnkBusinessAccountRecommend.getBalance() + wnkBusinessLevel.getPrice() * 0.1);
                                                        int recommendBusinessBalanceState = this.wnkBusinessAccountService.updateRelevantBalance(wnkBusinessAccountRecommend);
                                                        if (recommendBusinessBalanceState > 0){
                                                            wnkBusinessBalanceDetail = new WnkBusinessBalanceDetail();
                                                            wnkBusinessBalanceDetail.setBusiness_id(wnkBusinessAccountRecommend.getId());
                                                            wnkBusinessBalanceDetail.setName("下级商家升级");
                                                            wnkBusinessBalanceDetail.setTransaction_amount(wnkBusinessLevel.getPrice() * 0.1);
                                                            wnkBusinessBalanceDetail.setJoin_time(new Date());
                                                            wnkBusinessBalanceDetail.setAfter_balance(wnkBusinessAccount.getBalance());
                                                            wnkBusinessBalanceDetail.setType(0);
                                                            wnkBusinessBalanceDetail.setState(0);
                                                            this.wnkBusinessBalanceDetailService.insertNewRecord(wnkBusinessBalanceDetail);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }

                                    result.setMsg("购买成功");
                                    result.setStatus(Result.SUCCESS);
                                    result.setData("");

                                    //发送短信通知
                                    MobileCodeUtil.sendBusinessLevelUpgradeSms(wnkBusinessAccount.getMobile(),1,wnkBusinessLevel.getPrice(),wnkBusinessLevel.getLevel_name().replace("商家",""),wnkBusinessLevel.getObtain_integral());
                                    if (wnkStoreInformation.getGetui_appid() != null && !wnkStoreInformation.getGetui_appid().equals("")){
                                        String pushTitle = "等级积分收入";
                                        Double integralNumberWan = wnkBusinessLevel.getObtain_integral().doubleValue() / 10000;
                                        String pushContent = "您已通过余额支付向猛戳平台成功购买"+integralNumberWan+"万积分，请在猛戳-我的积分-等级积分查看";
                                        GeTuiBusinessUtil.pushUser(wnkStoreInformation.getGetui_appid(),pushTitle,pushContent);
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




    /**
     *
     * 功能描述: 万能卡商家余额充值订单
     *
     * @param: business_id,amount(充值金额)
     * @return: Result
     * @author: zhangfan
     * @date: 2018/10/28 10:14 PM
     */
    @RequestMapping(value = "/wnkBusinessRechargeLineOrder", method = RequestMethod.POST,params = {"business_id","amount"})
    @ResponseBody
    public Result wnkBusinessRechargeLineOrder(HttpServletRequest request, HttpServletResponse response, Integer business_id, Double amount){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id,3,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else if (amount <= 0.00){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("充值金额不可小于0");
            }
            else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (wnkBusinessAccount == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                }
                else{
                    Map<String,Object> messageMap = new HashMap<String,Object>();
                    WnkBusinessRechargeOrder wnkBusinessRechargeOrder = new WnkBusinessRechargeOrder();
                    wnkBusinessRechargeOrder.setOrder_no(UUDUtil.getOrderIdByUUId());
                    wnkBusinessRechargeOrder.setBusiness_id(business_id);
                    wnkBusinessRechargeOrder.setState(0);
                    wnkBusinessRechargeOrder.setCreate_time(new Date());
                    wnkBusinessRechargeOrder.setAmount(amount);
                    int state = this.wnkBusinessRechargeOrderService.insertBusinessRechargeOrder(wnkBusinessRechargeOrder);
                    if (state <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("下单失败");
                    }
                    else{
                        Map<String,Object> resutMap = WechatPayLineUtil.wechatPayWnkBusinessRecharge(wnkBusinessRechargeOrder.getOrder_no(), amount, request, response);
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
        }catch (Exception e){
            result.setData("");
            result.setStatus(Result.FAIL);
            result.setMsg("查询失败");
        }
        return result;
    }

    /**
     *
     * 功能描述: 万能卡商家向用户赠送积分
     *
     * @param: business_id,integral_number(赠送积分个数),user_mobile(用户手机号)
     * @param integral_type 积分类型(0-兑换积分,1-赠送积分(现金劵))
     * @return: Result
     * @author: zhangfan
     * @date: 2018/10/28 10:14 PM
     */
    @RequestMapping(value = "/wnkBusinessSendIntegralToUser", method = RequestMethod.POST,params = {"business_id","integral_number","user_mobile","pay_pwd","integral_type"})
    @ResponseBody
    public Result wnkBusinessSendIntegralToUser(HttpServletRequest request, HttpServletResponse response, Integer business_id, Double integral_number,String user_mobile,String pay_pwd,Integer integral_type){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id,3,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else if (integral_number <= 0){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("赠送积分不可小于0");
            }
            else if (integral_type != 0 && integral_type != 1){
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
                else if (wnkBusinessAccount.getPay_pwd() == null || wnkBusinessAccount.getPay_pwd().equals("")){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("请先设置支付密码");
                }
                else if (!wnkBusinessAccount.getPay_pwd().equals(pay_pwd)){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("支付密码不正确");
                }
                else{
                    Users users = this.usersService.selectByMobile(user_mobile);
                    if (users == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此用户不存在");
                    }
                    else if (wnkBusinessAccount.getLevel_integral() < integral_number){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("等级积分余额不足");
                    }
                    else{
                        WnkBusinessLevelIntegralDetail wnkBusinessLevelIntegralDetail = new WnkBusinessLevelIntegralDetail();
                        wnkBusinessLevelIntegralDetail.setBusiness_id(business_id);
                        wnkBusinessLevelIntegralDetail.setName("向用户赠送积分");
                        wnkBusinessLevelIntegralDetail.setIntegral_number(integral_number);
                        wnkBusinessLevelIntegralDetail.setTransactions_date(new Date());
                        wnkBusinessLevelIntegralDetail.setTransactions_type(1);
                        wnkBusinessLevelIntegralDetail.setUser_id(users.getId());
                        wnkBusinessLevelIntegralDetail.setIntegral_type(integral_type);

                        wnkBusinessAccount.setLevel_integral(wnkBusinessAccount.getLevel_integral() - integral_number);
                        int updateBusinessIntegralState = this.wnkBusinessAccountService.updateRelevantBalance(wnkBusinessAccount);
                        if (updateBusinessIntegralState <= 0){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("赠送失败");
                        }
                        else{
                            this.wnkBusinessLevelIntegralDetailService.insertSendNewRecord(wnkBusinessLevelIntegralDetail);
                            if (integral_type == 0){
                                Map<String,Object> wnkIntegralMap = this.wnkIntegralUserServer.getUserIntegral(business_id,users.getId());
                                Integer addWnkIntegralUserState = 1;
                                if (wnkIntegralMap == null){
                                    addWnkIntegralUserState = this.wnkIntegralUserServer.addUserIntegral(business_id,users.getId());
                                }
                                if (addWnkIntegralUserState <= 0){
                                    result.setData("");
                                    result.setStatus(Result.FAIL);
                                    result.setMsg("赠送失败");
                                }
                                else{
                                    // 增加用户在此商户的积分余额
                                    int addIntegral =  this.wnkIntegralUserServer.increaseUserIntegral(users.getId(),business_id,integral_number);
                                    if (addIntegral <= 0){
                                        result.setData("");
                                        result.setStatus(Result.FAIL);
                                        result.setMsg("赠送失败");
                                    }
                                    else{
                                        // 插入积分记录 0=收入
                                        this.wnkIntegralIncomeService.addIntegralRecord("商家赠送",integral_number,users.getId(),business_id,0);
                                        result.setData("");
                                        result.setStatus(Result.FAIL);
                                        result.setMsg("赠送成功");
                                        this.integralDetailService.insertIntrgralDetailRecord(users.getId(),"商家赠送",integral_number,0);
                                        WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(wnkBusinessAccount.getId());
                                        if (wnkStoreInformation != null){
                                            SystemMessages systemMessages = new SystemMessages();
                                            systemMessages.setTitle("商家赠送兑换积分");
                                            systemMessages.setContent(wnkStoreInformation.getStore_name()+"商家成功向您赠送了"+integral_number+"商家兑换积分");
                                            systemMessages.setUser_id(users.getId());
                                            systemMessages.setSend_date(new Date());
                                            systemMessages.setType(2);
                                            this.systemMessagesService.insertSystemMessage(systemMessages);

                                            WnkBusinessSystemMessage wnkBusinessSystemMessage = new WnkBusinessSystemMessage();
                                            wnkBusinessSystemMessage.setTitle("等级积分赠送");
                                            wnkBusinessSystemMessage.setContent("您成功使用积分支付向"+user_mobile+"用户赠送了"+integral_number+"兑换积分");
                                            wnkBusinessSystemMessage.setBusiness_id(wnkBusinessAccount.getId());
                                            wnkBusinessSystemMessage.setSend_date(new Date());
                                            this.wnkBusinessSystemMessageService.insertSystemMessage(wnkBusinessSystemMessage);

                                            if (users.getGetui_appid() != null && !users.getGetui_appid().equals("")){
                                                String pushTitle = "商家赠送兑换积分";
                                                String pushContent = wnkStoreInformation.getStore_name()+"商家成功向您赠送了"+integral_number+"个商家兑换积分，系统额外赠送"+integral_number.intValue()+"个平台积分，请在猛戳-我的积分-兑换积分券查看";
                                                GeTuiUserUtil.pushUser(users.getGetui_appid(),pushTitle,pushContent);
                                            }
                                            if (wnkStoreInformation.getGetui_appid() != null && !wnkStoreInformation.getGetui_appid().equals("")){
                                                String pushTitle = "兑换积分赠送";
                                                String pushContent = "您成功使用积分支付向"+users.getMobile()+"用户赠送了"+integral_number.intValue()+"兑换积分，请在猛戳商家版-我的积分-等级积分-赠送支出查看";
                                                GeTuiBusinessUtil.pushUser(wnkStoreInformation.getGetui_appid(),pushTitle,pushContent);
                                            }
                                        }
                                    }

                                }

                            }
                            else{
                                //查询用户在此商家处是否已有积分记录
                                Map<String,Object> userIntegralMap = this.wnkSendIntegralUserService.getUserIntegral(business_id,users.getId());
                                int insertRecordState = 1;
                                if (userIntegralMap == null){
                                    insertRecordState = 0;
                                    insertRecordState = this.wnkSendIntegralUserService.addUserIntegral(business_id,users.getId());
                                }
                                if (insertRecordState <= 0){
                                    result.setData("");
                                    result.setStatus(Result.FAIL);
                                    result.setMsg("赠送失败");
                                }
                                else{
                                    int addState = this.wnkSendIntegralUserService.increaseUserIntegral(users.getId(),business_id,integral_number.doubleValue());
                                    if (addState <= 0){
                                        result.setData("");
                                        result.setStatus(Result.FAIL);
                                        result.setMsg("赠送失败");
                                    }
                                    else{
                                        result.setData("");
                                        result.setStatus(Result.SUCCESS);
                                        result.setMsg("赠送成功");
                                        // 插入积分记录 0=收入
                                        this.wnkSendIntegralDetailUserService.addIntegralRecord("商家赠送积分",integral_number.doubleValue(),users.getId(),business_id,0);
                                        WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(wnkBusinessAccount.getId());
                                        if (wnkStoreInformation != null){
                                            SystemMessages systemMessages = new SystemMessages();
                                            systemMessages.setTitle("商家赠送积分");
                                            systemMessages.setContent(wnkStoreInformation.getStore_name()+"商家成功向您赠送了"+integral_number+"商家积分");
                                            systemMessages.setUser_id(users.getId());
                                            systemMessages.setSend_date(new Date());
                                            systemMessages.setType(2);
                                            this.systemMessagesService.insertSystemMessage(systemMessages);

                                            WnkBusinessSystemMessage wnkBusinessSystemMessage = new WnkBusinessSystemMessage();
                                            wnkBusinessSystemMessage.setTitle("等级积分赠送");
                                            wnkBusinessSystemMessage.setContent("您成功使用积分支付向"+user_mobile+"用户赠送了"+integral_number+"赠送积分");
                                            wnkBusinessSystemMessage.setBusiness_id(wnkBusinessAccount.getId());
                                            wnkBusinessSystemMessage.setSend_date(new Date());
                                            this.wnkBusinessSystemMessageService.insertSystemMessage(wnkBusinessSystemMessage);

                                            if (users.getGetui_appid() != null && !users.getGetui_appid().equals("")){
                                                String pushTitle = "商家赠送现金券";
                                                String pushContent = wnkStoreInformation.getStore_name()+"商家成功向您赠送了"+integral_number+"个现金券，请在猛戳-我的积分-现金券查看";
                                                GeTuiUserUtil.pushUser(users.getGetui_appid(),pushTitle,pushContent);
                                            }
                                            if (wnkStoreInformation.getGetui_appid() != null && !wnkStoreInformation.getGetui_appid().equals("")){
                                                String pushTitle = "现金劵赠送";
                                                String pushContent = "您成功使用积分支付向"+users.getMobile()+"用户赠送了"+integral_number.intValue()+"现金劵，请在猛戳商家版-我的积分-等级积分-赠送支出查看";
                                                GeTuiBusinessUtil.pushUser(wnkStoreInformation.getGetui_appid(),pushTitle,pushContent);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            result.setData(e.getMessage());
            result.setStatus(Result.FAIL);
            result.setMsg("操作失败");
        }
        return result;
    }

    /**
     *
     * 功能描述: 万能卡商家向用户赠送余额支付
     *
     * @param: business_id,integral_number(赠送积分个数),user_mobile(用户手机号),pay_pwd(支付密码)
     * @param integral_type 积分类型(0-兑换积分,1-赠送积分)
     * @return: Result
     * @author: zhangfan
     * @date: 2018/10/28 10:14 PM
     */
    @RequestMapping(value = "/wnkBusinessSendIntegralToUserByBalancePay", method = RequestMethod.POST,params = {"business_id","integral_number","user_mobile","pay_pwd","integral_type"})
    @ResponseBody
    public Result wnkBusinessSendIntegralToUserByBalancePay(HttpServletRequest request, HttpServletResponse response, Integer business_id, Double integral_number,String user_mobile,String pay_pwd,Integer integral_type){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(business_id,3,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else if (integral_number <= 0){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("赠送积分不可小于0");
            }
            else {
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                Double price = integral_number / 10.00;
                if (wnkBusinessAccount == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此商家不存在");
                }
                else if (!wnkBusinessAccount.getPay_pwd().equals(pay_pwd)){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("支付密码错误");
                }
                else{
                    Users users = this.usersService.selectByMobile(user_mobile);
                    if (users == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此用户不存在");
                    }
                    else if ((Double)wnkBusinessAccount.getBalance() < price){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("余额不足");
                    }
                    else{
                        String integralTypeName = integral_type==0?"兑换积分":"赠送积分";
                        WnkBusinessBalanceDetail wnkBusinessBalanceDetail = new WnkBusinessBalanceDetail();
                        wnkBusinessBalanceDetail.setBusiness_id(business_id);
                        wnkBusinessBalanceDetail.setName("赠送"+integralTypeName+"(余额支付)");
                        wnkBusinessBalanceDetail.setTransaction_amount(price);
                        wnkBusinessBalanceDetail.setJoin_time(new Date());
                        wnkBusinessBalanceDetail.setAfter_balance(wnkBusinessAccount.getBalance() - price);
                        wnkBusinessBalanceDetail.setType(1);
                        wnkBusinessBalanceDetail.setState(0);

                        WnkBusinessLevelIntegralDetail wnkBusinessLevelIntegralDetail = new WnkBusinessLevelIntegralDetail();
                        wnkBusinessLevelIntegralDetail.setBusiness_id(business_id);
                        wnkBusinessLevelIntegralDetail.setName("赠送用户"+integralTypeName+"(余额支付)");
                        wnkBusinessLevelIntegralDetail.setIntegral_number(Double.valueOf(integral_number));
                        wnkBusinessLevelIntegralDetail.setTransactions_date(new Date());
                        wnkBusinessLevelIntegralDetail.setTransactions_type(1);
                        wnkBusinessLevelIntegralDetail.setUser_id(users.getId());
                        wnkBusinessLevelIntegralDetail.setIntegral_type(integral_type);

                        wnkBusinessAccount.setBalance(wnkBusinessAccount.getBalance() - price);
                        int updateBusinessIntegralState = this.wnkBusinessAccountService.updateBalance(business_id,wnkBusinessAccount.getBalance());
                        if (updateBusinessIntegralState <= 0){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("赠送失败");
                        }
                        else{
                            if (integral_type == 0){
                                Map<String,Object> wnkIntegralMap = this.wnkIntegralUserServer.getUserIntegral(business_id,users.getId());
                                Integer addWnkIntegralUserState = 1;
                                if (wnkIntegralMap == null){
                                    addWnkIntegralUserState = this.wnkIntegralUserServer.addUserIntegral(business_id,users.getId());
                                }
                                if (addWnkIntegralUserState <= 0){
                                    result.setData("");
                                    result.setStatus(Result.FAIL);
                                    result.setMsg("赠送失败");
                                }
                                else{
                                    // 增加用户在此商户的积分余额
                                    int addIntegral =  this.wnkIntegralUserServer.increaseUserIntegral(users.getId(),business_id,integral_number);
                                    if (addIntegral <= 0){
                                        result.setData("");
                                        result.setStatus(Result.FAIL);
                                        result.setMsg("赠送失败");
                                    }
                                    else{
                                        // 插入积分记录 0=收入
                                        this.wnkIntegralIncomeService.addIntegralRecord("商家赠送",integral_number,users.getId(),business_id,0);
                                        result.setData("");
                                        result.setStatus(Result.SUCCESS);
                                        result.setMsg("赠送成功");

                                        this.integralDetailService.insertIntrgralDetailRecord(users.getId(),"商家赠送",integral_number,0);
                                        this.wnkBusinessLevelIntegralDetailService.insertSendNewRecord(wnkBusinessLevelIntegralDetail);
                                        this.wnkBusinessBalanceDetailService.insertNewRecord(wnkBusinessBalanceDetail);
                                        WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(wnkBusinessAccount.getId());
                                        if (wnkStoreInformation != null){
                                            SystemMessages systemMessages = new SystemMessages();
                                            systemMessages.setTitle("商家赠送兑换积分");
                                            systemMessages.setContent(wnkStoreInformation.getStore_name()+"商家成功向您赠送了"+integral_number+"商家兑换积分");
                                            systemMessages.setUser_id(users.getId());
                                            systemMessages.setSend_date(new Date());
                                            systemMessages.setType(2);
                                            this.systemMessagesService.insertSystemMessage(systemMessages);

                                            WnkBusinessSystemMessage wnkBusinessSystemMessage = new WnkBusinessSystemMessage();
                                            wnkBusinessSystemMessage.setTitle("等级积分赠送");
                                            wnkBusinessSystemMessage.setContent("您成功使用余额支付向"+user_mobile+"用户赠送了"+integral_number+"兑换积分");
                                            wnkBusinessSystemMessage.setBusiness_id(wnkBusinessAccount.getId());
                                            wnkBusinessSystemMessage.setSend_date(new Date());
                                            this.wnkBusinessSystemMessageService.insertSystemMessage(wnkBusinessSystemMessage);

                                            if (users.getGetui_appid() != null && !users.getGetui_appid().equals("")){
                                                String pushTitle = "商家赠送兑换积分";
                                                String pushContent = wnkStoreInformation.getStore_name()+"商家成功向您赠送了"+integral_number+"个商家兑换积分，系统额外赠送"+integral_number.intValue()+"个平台积分，请在猛戳-我的积分-兑换积分券查看";
                                                GeTuiUserUtil.pushUser(users.getGetui_appid(),pushTitle,pushContent);
                                            }
                                            if (wnkStoreInformation.getGetui_appid() != null && !wnkStoreInformation.getGetui_appid().equals("")){
                                                String pushTitle = "兑换积分赠送";
                                                String pushContent = "您成功使用余额支付向"+users.getMobile()+"用户赠送了"+integral_number.intValue()+"兑换积分，请在猛戳商家版-我的积分-等级积分-赠送支出查看";
                                                GeTuiBusinessUtil.pushUser(wnkStoreInformation.getGetui_appid(),pushTitle,pushContent);
                                            }
                                        }
                                    }

                                }
                            }
                            else{
                                //查询用户在此商家处是否已有积分记录
                                Map<String,Object> userIntegralMap = this.wnkSendIntegralUserService.getUserIntegral(business_id,users.getId());
                                int insertRecordState = 1;
                                if (userIntegralMap == null){
                                    insertRecordState = 0;
                                    insertRecordState = this.wnkSendIntegralUserService.addUserIntegral(business_id,users.getId());
                                }
                                if (insertRecordState <= 0){
                                    result.setData("");
                                    result.setStatus(Result.FAIL);
                                    result.setMsg("赠送失败");
                                }
                                else{
                                    int addState = this.wnkSendIntegralUserService.increaseUserIntegral(users.getId(),business_id,integral_number.doubleValue());
                                    if (addState <= 0){
                                        result.setData("");
                                        result.setStatus(Result.FAIL);
                                        result.setMsg("赠送失败");
                                    }
                                    else{
                                        result.setData("");
                                        result.setStatus(Result.SUCCESS);
                                        result.setMsg("赠送成功");
                                        this.wnkBusinessLevelIntegralDetailService.insertSendNewRecord(wnkBusinessLevelIntegralDetail);
                                        this.wnkBusinessBalanceDetailService.insertNewRecord(wnkBusinessBalanceDetail);
                                        // 插入积分记录 0=收入
                                        this.wnkSendIntegralDetailUserService.addIntegralRecord("商家赠送积分",integral_number.doubleValue(),users.getId(),business_id,0);
                                        WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(wnkBusinessAccount.getId());
                                        if (wnkStoreInformation != null){
                                            SystemMessages systemMessages = new SystemMessages();
                                            systemMessages.setTitle("商家赠送积分");
                                            systemMessages.setContent(wnkStoreInformation.getStore_name()+"商家成功向您赠送了"+integral_number+"商家积分");
                                            systemMessages.setUser_id(users.getId());
                                            systemMessages.setSend_date(new Date());
                                            systemMessages.setType(2);
                                            this.systemMessagesService.insertSystemMessage(systemMessages);

                                            WnkBusinessSystemMessage wnkBusinessSystemMessage = new WnkBusinessSystemMessage();
                                            wnkBusinessSystemMessage.setTitle("等级积分赠送");
                                            wnkBusinessSystemMessage.setContent("您成功使用余额支付向"+user_mobile+"用户赠送了"+integral_number+"赠送积分");
                                            wnkBusinessSystemMessage.setBusiness_id(wnkBusinessAccount.getId());
                                            wnkBusinessSystemMessage.setSend_date(new Date());
                                            this.wnkBusinessSystemMessageService.insertSystemMessage(wnkBusinessSystemMessage);

                                            if (users.getGetui_appid() != null && !users.getGetui_appid().equals("")){
                                                String pushTitle = "商家赠送现金券";
                                                String pushContent = wnkStoreInformation.getStore_name()+"商家成功向您赠送了"+integral_number+"个现金券，请在猛戳-我的积分-现金券查看";
                                                GeTuiUserUtil.pushUser(users.getGetui_appid(),pushTitle,pushContent);
                                            }
                                            if (wnkStoreInformation.getGetui_appid() != null && !wnkStoreInformation.getGetui_appid().equals("")){
                                                String pushTitle = "现金券赠送";
                                                String pushContent = "您成功使用余额支付向"+users.getMobile()+"用户赠送了"+integral_number.intValue()+"现金券，请在猛戳商家版-我的积分-等级积分-赠送支出查看";
                                                GeTuiBusinessUtil.pushUser(wnkStoreInformation.getGetui_appid(),pushTitle,pushContent);
                                            }
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
