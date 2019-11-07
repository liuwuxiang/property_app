package com.springmvc.controller.wechat;

import com.springmvc.controller.upload.ImageToolsController;
import com.springmvc.entity.*;
import com.springmvc.service.impl.*;
import com.springmvc.utils.*;
import com.springmvc.utils.WX.WXTemplateMessageUtil;
import com.springmvc.utils.qrCode.QRCode;
import com.springmvc.utils.wxpay.sdk.WXPay;
import com.springmvc.utils.wxpay.sdk.WXPayConfigImpl;
import com.springmvc.utils.wxpay.sdk.WXPayUtil;
import net.sf.json.JSONObject;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import org.xml.sax.InputSource;
import org.jdom2.input.SAXBuilder;
import org.jdom2.Document;
import org.jdom2.Element;

import static com.springmvc.utils.Qrcode.createQrCodePic;
import static com.springmvc.utils.Qrcode.generateCode;
/**
 *
 * 功能描述:
 * @Author: 刘武祥
 * @Date: 2019/2/19 0019 14:22
 */
@Controller
@RequestMapping(value = "/weChat")
public class WechatCallbackController {
    @Resource
    private RechargeOrderService rechargeOrderService;
    @Resource
    private UsersService usersService;
    @Resource
    private ConsumptionIntegralIncomeService consumptionIntegralIncomeService;
    @Resource
    private OpenMemberCardWxPayOrderService openMemberCardWxPayOrderService;
    @Resource
    private SilverCoinDetailedService silverCoinDetailedService;
    @Resource
    private MemberLevelsService memberLevelsService;
    @Resource
    private UserOpenCardsService userOpenCardsService;
    @Resource
    private WnkOrdersService wnkOrdersService;
    @Resource
    private WnkBuyMealService wnkBuyMealService;
    @Resource
    private IntegralDetailService integralDetailService;
    @Resource
    private WnkIntegralUserServer wnkIntegralUserServer;
    @Resource
    private WnkIntegralIncomeService wnkIntegralIncomeService;
    @Resource
    private WnkBusinessUpgradeOrderService wnkBusinessUpgradeOrderService;
    @Resource
    private WnkBusinessLevelService wnkBusinessLevelService;
    @Resource
    private WnkStoreInformationService wnkStoreInformationService;
    @Resource
    private WnkBusinessAccountService wnkBusinessAccountService;
    @Resource
    private WnkBusinessLevelIntegralDetailService wnkBusinessLevelIntegralDetailService;
    @Resource
    private WnkBusinessBalanceDetailService wnkBusinessBalanceDetailService;
    @Resource
    private WnkBusinessRechargeOrderService wnkBusinessRechargeOrderService;
    @Resource
    private WnkBusinessConsumptionIntegralDetailService wnkBusinessConsumptionIntegralDetailService;
    @Resource
    private UserScanCodeWxPayService userScanCodeWxPayService;
    @Resource
    private ExtensionMaterielMealBuyWxOrderService extensionMaterielMealBuyWxOrderService;
    @Resource
    private ExtensionMaterielBuyMealService extensionMaterielBuyMealService;
    @Resource
    private MyMaterielService myMaterielService;
    @Resource
    private UserOpenBusinessMemberCardWXOrdersService userOpenBusinessMemberCardWXOrdersService;
    @Resource
    private WnkBusinessTypeOpenCardService wnkBusinessTypeOpenCardService;
    @Resource
    private GeneralIntegralIncomeService generalIntegralIncomeService;
    @Resource
    private MaterielBuyRecordService materielBuyRecordService;
    @Resource
    private WnkCommoditySpecificationsService wnkCommoditySpecificationsService;
    @Resource
    private WnkOrdersTwoService wnkOrdersTwoService;
    @Resource
    private GeneralIntegralExpenditureService generalIntegralExpenditureService;
    @Resource
    private WnkSendIntegralUserService wnkSendIntegralUserService;
    @Resource
    private ExtensionMaterielService extensionMaterielService;
    @Resource
    private UserCouponsService userCouponsService;
    @Resource
    private WnkSendIntegralDetailUserService wnkSendIntegralDetailUserService;

    @Resource
    private WnkBusinessTypeService wnkBusinessTypeService;

    /**
     *
     * 功能描述: 文件订单支付回调接口
     *
     * @param   request
     * @return: java.lang.String
     * @author: 刘武祥
     * @date: 2019/2/19 0019 14:21
     */
    @RequestMapping(value = "/payCallback")
    @ResponseBody
    public String payCallback(HttpServletRequest request){

        //订单号
        String order_no = null;
        String return_code = null;
        try {
            InputStream inStream = request.getInputStream();
            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
            outSteam.close();
            inStream.close();
            String resultStr  = new String(outSteam.toByteArray(),"utf-8");
            WXPayConfigImpl config = new WXPayConfigImpl();
            WXPay wxpay = new WXPay(config);
            Map<String, String> resultMap = WXPayUtil.xmlToMap(resultStr);  // 转换成map

            if (wxpay.isPayResultNotifySignatureValid(resultMap)) {
                // 签名正确
                // 进行处理。
                // 注意特殊情况：订单已经退款，但收到了支付结果成功的通知，不应把商户侧订单状态从退款改成支付成功
                //            Map<String, Object> resultMap = parseXmlToList(resultStr);
                String result_code = (String) resultMap.get("result_code");
                String is_subscribe = (String) resultMap.get("is_subscribe");
                String transaction_id = (String) resultMap.get("transaction_id");
                String sign = (String) resultMap.get("sign");
                String time_end = (String) resultMap.get("time_end");
                String bank_type = (String) resultMap.get("bank_type");

                order_no = (String) resultMap.get("out_trade_no");
                return_code = (String) resultMap.get("return_code");
            }
            else {
                // 签名错误，如果数据里没有sign字段，也认为是签名错误
                return "FAIL";
            }
        }  catch (Exception e) {
            return "FAIL";
        }
        try {
            RechargeOrder rechargeOrder = this.rechargeOrderService.selectBySystemOrderNo(order_no);
            if (rechargeOrder != null && rechargeOrder.getState() != 2 && return_code.equals("SUCCESS")) {
                rechargeOrder.setPay_time(new Date());
                rechargeOrder.setState(2);
                this.rechargeOrderService.updatePayState(rechargeOrder);
                Users users = this.usersService.selectById(rechargeOrder.getUser_id());
                if (users != null){
                    users.setGeneral_integral(users.getGeneral_integral()+rechargeOrder.getIntegral_number());
                    this.usersService.updateUserTYAndXFAndSilverCoinBalance(users);


                    GeneralIntegralIncome generalIntegralIncome = new GeneralIntegralIncome();
                    generalIntegralIncome.setName("线上充值");
                    generalIntegralIncome.setIncome_date(new Date());
                    generalIntegralIncome.setIncome_amount(rechargeOrder.getIntegral_number().doubleValue());
                    generalIntegralIncome.setIncome_after_balance(users.getGeneral_integral());
                    generalIntegralIncome.setUser_id(users.getId());
                    generalIntegralIncome.setIncome_type(0);


                    this.generalIntegralIncomeService.insertUserSilverCoinWithdrawRecord(generalIntegralIncome);
                }
                return "SUCCESS";

            }
        }catch (Exception e){
            e.printStackTrace();
            return "FAIL";
        }

        return "SUCCESS";
    }


    /**
     *
     * 功能描述: 会员卡开卡微信支付回调
     *
     * @param   request
     * @return: java.lang.String
     * @author: 刘武祥
     * @date: 2019/2/19 0019 14:23
     */
    @RequestMapping(value = "/openCardWXPayCallback")
    @ResponseBody
    public String openCardWXPayCallback(HttpServletRequest request){

        //订单号
        String order_no = null;
        String return_code = null;
        try {
            InputStream inStream = request.getInputStream();
            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
            outSteam.close();
            inStream.close();
            String resultStr  = new String(outSteam.toByteArray(),"utf-8");
            WXPayConfigImpl config = new WXPayConfigImpl();
            WXPay wxpay = new WXPay(config);
            Map<String, String> resultMap = WXPayUtil.xmlToMap(resultStr);  // 转换成map

            if (wxpay.isPayResultNotifySignatureValid(resultMap)) {
                // 签名正确
                // 进行处理。
                // 注意特殊情况：订单已经退款，但收到了支付结果成功的通知，不应把商户侧订单状态从退款改成支付成功
                //            Map<String, Object> resultMap = parseXmlToList(resultStr);
                String result_code = (String) resultMap.get("result_code");
                String is_subscribe = (String) resultMap.get("is_subscribe");
                String transaction_id = (String) resultMap.get("transaction_id");
                String sign = (String) resultMap.get("sign");
                String time_end = (String) resultMap.get("time_end");
                String bank_type = (String) resultMap.get("bank_type");

                order_no = (String) resultMap.get("out_trade_no");
                return_code = (String) resultMap.get("return_code");
            }
            else {
                // 签名错误，如果数据里没有sign字段，也认为是签名错误
                return "FAIL";
            }
        }  catch (Exception e) {
            return "FAIL";
        }
        try {
            OpenMemberCardWxPayOrder openMemberCardWxPayOrder = this.openMemberCardWxPayOrderService.selectRecordByOrderNo(order_no);
            if (openMemberCardWxPayOrder != null && openMemberCardWxPayOrder.getState() != 1 && return_code.equals("SUCCESS")) {
                this.openMemberCardWxPayOrderService.updateStateByOrderNo(1,order_no);
                WnkBuyMeal wnkBuyMeal = this.wnkBuyMealService.selectById(openMemberCardWxPayOrder.getCard_type_id());
                if (wnkBuyMeal != null){
                    Users users = this.usersService.selectById(openMemberCardWxPayOrder.getUser_id());
                    if (users != null){
                        users.setMember_card_level(wnkBuyMeal.getCard_type());
                        int upgradeState = this.usersService.updateMemberCardLevel(users);
                        if (upgradeState > 0){
                            this.userOpenCardsService.userOpenCardOrRenew(users.getId(),wnkBuyMeal.getCard_type(),1);
                            this.userOpenCardsService.userOpenCardRecommendOption(users.getId(),wnkBuyMeal);
                            //发送开卡短信通知
                            MobileCodeUtil.sendUserBuyWnkSms(users.getMobile(),1,wnkBuyMeal.getPrice().doubleValue(),wnkBuyMeal.getName());
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return "FAIL";
        }

        return "SUCCESS";
    }


    /**
     *
     * 功能描述: 用户扫码微信App支付回调
     *
     * @param   request
     * @return: java.lang.String
     * @author: 刘武祥
     * @date: 2019/2/19 0019 14:25
     */
    @RequestMapping(value = "/userScanCodeWXAppPayCallback")
    @ResponseBody
    public String userScanCodeWXAppPayCallback(HttpServletRequest request){

        //订单号
        String order_no = null;
        String return_code = null;
        try {
            InputStream inStream = request.getInputStream();
            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
            outSteam.close();
            inStream.close();
            String resultStr  = new String(outSteam.toByteArray(),"utf-8");
            WXPayConfigImpl config = new WXPayConfigImpl();
            WXPay wxpay = new WXPay(config);
            Map<String, String> resultMap = WXPayUtil.xmlToMap(resultStr);  // 转换成map

            if (wxpay.isPayResultNotifySignatureValid(resultMap)) {
                // 签名正确
                // 进行处理。
                // 注意特殊情况：订单已经退款，但收到了支付结果成功的通知，不应把商户侧订单状态从退款改成支付成功
                //            Map<String, Object> resultMap = parseXmlToList(resultStr);
                String result_code = (String) resultMap.get("result_code");
                String is_subscribe = (String) resultMap.get("is_subscribe");
                String transaction_id = (String) resultMap.get("transaction_id");
                String sign = (String) resultMap.get("sign");
                String time_end = (String) resultMap.get("time_end");
                String bank_type = (String) resultMap.get("bank_type");

                order_no = (String) resultMap.get("out_trade_no");
                return_code = (String) resultMap.get("return_code");
            }
            else {
                // 签名错误，如果数据里没有sign字段，也认为是签名错误
                return "FAIL";
            }
        }  catch (Exception e) {
            return "FAIL";
        }
        try {
            UserScanCodeWxPay userScanCodeWxPay = this.userScanCodeWxPayService.selectRecordByOrderNo(order_no);
            if (userScanCodeWxPay != null && userScanCodeWxPay.getState() != 1 && return_code.equals("SUCCESS")) {
                userScanCodeWxPay.setState(1);
                this.userScanCodeWxPayService.updateStateByOrderNo(userScanCodeWxPay);

                Users users = this.usersService.selectById(userScanCodeWxPay.getUser_id());
                if (users != null){
                    WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(userScanCodeWxPay.getBusiness_id());
                    if (wnkBusinessAccount != null){
                        wnkBusinessAccount.setBalance(wnkBusinessAccount.getBalance() + userScanCodeWxPay.getPay_amount());
                        this.wnkBusinessAccountService.updateRelevantBalance(wnkBusinessAccount);

                        WnkBusinessBalanceDetail wnkBusinessBalanceDetail = new WnkBusinessBalanceDetail();
                        wnkBusinessBalanceDetail.setBusiness_id(userScanCodeWxPay.getBusiness_id());
                        wnkBusinessBalanceDetail.setName("用户扫码付款");
                        wnkBusinessBalanceDetail.setTransaction_amount(Double.valueOf(userScanCodeWxPay.getPay_amount()));
                        wnkBusinessBalanceDetail.setJoin_time(new Date());
                        wnkBusinessBalanceDetail.setAfter_balance(wnkBusinessAccount.getBalance());
                        wnkBusinessBalanceDetail.setType(0);
                        wnkBusinessBalanceDetail.setState(0);
                        this.wnkBusinessBalanceDetailService.insertNewRecord(wnkBusinessBalanceDetail);

                        WnkBusinessLevelIntegralDetail wnkBusinessLevelIntegralDetail = new WnkBusinessLevelIntegralDetail();
                        wnkBusinessLevelIntegralDetail.setBusiness_id(userScanCodeWxPay.getBusiness_id());
                        wnkBusinessLevelIntegralDetail.setName("客户扫描消费");
                        wnkBusinessLevelIntegralDetail.setIntegral_number(userScanCodeWxPay.getPay_amount());
                        wnkBusinessLevelIntegralDetail.setTransactions_date(new Date());
                        wnkBusinessLevelIntegralDetail.setTransactions_type(2);
                        wnkBusinessLevelIntegralDetail.setUser_id(users.getId());
                        wnkBusinessLevelIntegralDetail.setPay_type(5);

                        wnkBusinessAccount.setLevel_integral(wnkBusinessAccount.getLevel_integral() - userScanCodeWxPay.getPay_amount().intValue());
                        this.wnkBusinessAccountService.updateRelevantBalance(wnkBusinessAccount);
                        this.wnkBusinessLevelIntegralDetailService.insertNewRecordUserXF(wnkBusinessLevelIntegralDetail);

                        this.integralDetailService.insertIntrgralDetailRecord(users.getId(),"扫码支付",userScanCodeWxPay.getPay_amount(),0);
                        Map<String,Object> wnkIntegralMap = this.wnkIntegralUserServer.getUserIntegral(userScanCodeWxPay.getBusiness_id(),users.getId());
                        Integer addWnkIntegralUserState = 1;
                        if (wnkIntegralMap == null){
                            addWnkIntegralUserState = this.wnkIntegralUserServer.addUserIntegral(userScanCodeWxPay.getBusiness_id(),users.getId());
                        }
                        if (addWnkIntegralUserState > 0){
                            Map<String,Object> recommendIntegralMap = this.wnkIntegralUserServer.getUserIntegral(userScanCodeWxPay.getBusiness_id(),users.getId());
                            if (recommendIntegralMap == null){
                                this.wnkIntegralUserServer.addUserIntegral(userScanCodeWxPay.getBusiness_id(),users.getId());
                            }
                            // 增加用户在此商户的积分余额
                            this.wnkIntegralUserServer.increaseUserIntegral(users.getId(),userScanCodeWxPay.getBusiness_id(),userScanCodeWxPay.getPay_amount());
                            // 插入积分记录 0=收入
                            this.wnkIntegralIncomeService.addIntegralRecord("扫码支付",userScanCodeWxPay.getPay_amount(),users.getId(),userScanCodeWxPay.getBusiness_id(),0);
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return "FAIL";
        }

        return "SUCCESS";
    }


    /*
    * 商家等级升级微信支付
    * */
    @RequestMapping(value = "/wnkBusinessLevelUpgradeWXPayCallback")
    @ResponseBody
    public String wnkBusinessLevelUpgradeWXPayCallback(HttpServletRequest request){
        //订单号
        String order_no = null;
        String return_code = null;
        try {
            InputStream inStream = request.getInputStream();
            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
            outSteam.close();
            inStream.close();
            String resultStr  = new String(outSteam.toByteArray(),"utf-8");
            WXPayConfigImpl config = new WXPayConfigImpl();
            WXPay wxpay = new WXPay(config);
            Map<String, String> resultMap = WXPayUtil.xmlToMap(resultStr);  // 转换成map

            if (wxpay.isPayResultNotifySignatureValid(resultMap)) {
                // 签名正确
                // 进行处理。
                // 注意特殊情况：订单已经退款，但收到了支付结果成功的通知，不应把商户侧订单状态从退款改成支付成功
                //            Map<String, Object> resultMap = parseXmlToList(resultStr);
                String result_code = (String) resultMap.get("result_code");
                String is_subscribe = (String) resultMap.get("is_subscribe");
                String transaction_id = (String) resultMap.get("transaction_id");
                String sign = (String) resultMap.get("sign");
                String time_end = (String) resultMap.get("time_end");
                String bank_type = (String) resultMap.get("bank_type");

                order_no = (String) resultMap.get("out_trade_no");
                return_code = (String) resultMap.get("return_code");
            }
            else {
                // 签名错误，如果数据里没有sign字段，也认为是签名错误
                return "FAIL";
            }
        }  catch (Exception e) {
            return "FAIL";
        }
        try {
            WnkBusinessUpgradeOrder wnkBusinessUpgradeOrder = this.wnkBusinessUpgradeOrderService.selectRecordByOrderNo(order_no);
            if (wnkBusinessUpgradeOrder != null && wnkBusinessUpgradeOrder.getState() != 1 && return_code.equals("SUCCESS")) {
                wnkBusinessUpgradeOrder.setState(1);
                this.wnkBusinessUpgradeOrderService.updateStateByOrderNo(wnkBusinessUpgradeOrder);
                WnkBusinessLevel wnkBusinessLevel = this.wnkBusinessLevelService.selectById(wnkBusinessUpgradeOrder.getLevel_id());
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
                            WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(wnkBusinessUpgradeOrder.getBusiness_id());
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
                                            WnkBusinessBalanceDetail wnkBusinessBalanceDetail = new WnkBusinessBalanceDetail();
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
                                //发送短信通知
                                MobileCodeUtil.sendBusinessLevelUpgradeSms(wnkBusinessAccount.getMobile(),0,wnkBusinessLevel.getPrice(),wnkBusinessLevel.getLevel_name().replace("商家",""),wnkBusinessLevel.getObtain_integral());
                                if (wnkStoreInformation.getGetui_appid() != null && !wnkStoreInformation.getGetui_appid().equals("")){
                                    String pushTitle = "等级积分收入";
                                    Double integralNumberWan = wnkBusinessLevel.getObtain_integral().doubleValue() / 10000;
                                    String pushContent = "您已通过微信支付向猛戳平台成功购买"+integralNumberWan+"万积分，请在猛戳-我的积分-等级积分查看";
                                    GeTuiBusinessUtil.pushUser(wnkStoreInformation.getGetui_appid(),pushTitle,pushContent);
                                }
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return "FAIL";
        }

        return "SUCCESS";
    }



    /*
    * 商家购买推广物料微信下单App支付回调
    * */
    @RequestMapping(value = "/wechatPayWnkBusinessBuyMaterielMealCallback")
    @ResponseBody
    public String wechatPayWnkBusinessBuyMaterielMealCallback(HttpServletRequest request){
        //订单号
        String order_no = null;
        String return_code = null;
        try {
            InputStream inStream = request.getInputStream();
            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
            outSteam.close();
            inStream.close();
            String resultStr  = new String(outSteam.toByteArray(),"utf-8");
            WXPayConfigImpl config = new WXPayConfigImpl();
            WXPay wxpay = new WXPay(config);
            Map<String, String> resultMap = WXPayUtil.xmlToMap(resultStr);  // 转换成map

            if (wxpay.isPayResultNotifySignatureValid(resultMap)) {
                // 签名正确
                // 进行处理。
                // 注意特殊情况：订单已经退款，但收到了支付结果成功的通知，不应把商户侧订单状态从退款改成支付成功
                //            Map<String, Object> resultMap = parseXmlToList(resultStr);
                String result_code = (String) resultMap.get("result_code");
                String is_subscribe = (String) resultMap.get("is_subscribe");
                String transaction_id = (String) resultMap.get("transaction_id");
                String sign = (String) resultMap.get("sign");
                String time_end = (String) resultMap.get("time_end");
                String bank_type = (String) resultMap.get("bank_type");

                order_no = (String) resultMap.get("out_trade_no");
                return_code = (String) resultMap.get("return_code");
            }
            else {
                // 签名错误，如果数据里没有sign字段，也认为是签名错误
                return "FAIL";
            }
        }  catch (Exception e) {
            return "FAIL";
        }
        try {
            ExtensionMaterielMealBuyWxOrder extensionMaterielMealBuyWxOrder = this.extensionMaterielMealBuyWxOrderService.selectRecordByOrderNo(order_no);
            if (extensionMaterielMealBuyWxOrder != null && extensionMaterielMealBuyWxOrder.getState() != 1 && return_code.equals("SUCCESS")) {
                extensionMaterielMealBuyWxOrder.setState(1);
                this.extensionMaterielMealBuyWxOrderService.updateStateByOrderNo(extensionMaterielMealBuyWxOrder);
                ExtensionMaterielBuyMeal extensionMaterielBuyMeal = this.extensionMaterielBuyMealService.selectById(extensionMaterielMealBuyWxOrder.getMateriel_meal_id());
                if (extensionMaterielBuyMeal != null){
                    int addState = 0;
                    MyMateriel myMateriel = this.myMaterielService.selectByMaterielIdAndBusinessId(extensionMaterielMealBuyWxOrder.getBusiness_id(),extensionMaterielBuyMeal.getMateriel_id());
                    if (myMateriel == null){
                        myMateriel = new MyMateriel();
                        myMateriel.setBusiness_id(extensionMaterielMealBuyWxOrder.getBusiness_id());
                        myMateriel.setMateriel_id(extensionMaterielBuyMeal.getMateriel_id());
                        myMateriel.setSurplus_number(extensionMaterielBuyMeal.getNumber());
                        addState = this.myMaterielService.insertMaterielBuyRecord(myMateriel);
                    }
                    else{
                        myMateriel.setSurplus_number(myMateriel.getSurplus_number() + extensionMaterielBuyMeal.getNumber());
                        addState = this.myMaterielService.updateSurplusNumber(myMateriel);
                    }
                    MaterielBuyRecord materielBuyRecord = new MaterielBuyRecord();
                    materielBuyRecord.setMateriel_id(extensionMaterielBuyMeal.getMateriel_id());
                    materielBuyRecord.setBusiness_id(extensionMaterielMealBuyWxOrder.getBusiness_id());
                    materielBuyRecord.setBuy_number(extensionMaterielBuyMeal.getNumber());
                    materielBuyRecord.setBuy_date(new Date());
                    this.materielBuyRecordService.inserRecord(materielBuyRecord);

                    WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(extensionMaterielMealBuyWxOrder.getBusiness_id());
                    ExtensionMateriel extensionMaterielA = this.extensionMaterielService.selectById(extensionMaterielBuyMeal.getMateriel_id());
                    if (wnkBusinessAccount != null && extensionMaterielA != null){
                        //发送短信通知
                        MobileCodeUtil.sendBusinessBuyRecommendExtensionWnkSms(wnkBusinessAccount.getMobile(),0,extensionMaterielBuyMeal.getPrice(),extensionMaterielA.getName(),extensionMaterielBuyMeal.getNumber());
                    }

                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return "FAIL";
        }

        return "SUCCESS";
    }


    /*
   * 商家充值微信支付
   * */
    @RequestMapping(value = "/wnkBusinessRechargeLineOrderCallBack")
    @ResponseBody
    public String wnkBusinessRechargeLineOrderCallBack(HttpServletRequest request){
        //订单号
        String order_no = null;
        String return_code = null;
        try {
            InputStream inStream = request.getInputStream();
            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
            outSteam.close();
            inStream.close();
            String resultStr  = new String(outSteam.toByteArray(),"utf-8");
            WXPayConfigImpl config = new WXPayConfigImpl();
            WXPay wxpay = new WXPay(config);
            Map<String, String> resultMap = WXPayUtil.xmlToMap(resultStr);  // 转换成map

            if (wxpay.isPayResultNotifySignatureValid(resultMap)) {
                // 签名正确
                // 进行处理。
                // 注意特殊情况：订单已经退款，但收到了支付结果成功的通知，不应把商户侧订单状态从退款改成支付成功
                //            Map<String, Object> resultMap = parseXmlToList(resultStr);
                String result_code = (String) resultMap.get("result_code");
                String is_subscribe = (String) resultMap.get("is_subscribe");
                String transaction_id = (String) resultMap.get("transaction_id");
                String sign = (String) resultMap.get("sign");
                String time_end = (String) resultMap.get("time_end");
                String bank_type = (String) resultMap.get("bank_type");

                order_no = (String) resultMap.get("out_trade_no");
                return_code = (String) resultMap.get("return_code");
            }
            else {
                // 签名错误，如果数据里没有sign字段，也认为是签名错误
                return "FAIL";
            }
        }  catch (Exception e) {
            return "FAIL";
        }
        try {
            WnkBusinessRechargeOrder wnkBusinessRechargeOrder = this.wnkBusinessRechargeOrderService.selectRecordByOrderNo(order_no);
            if (wnkBusinessRechargeOrder != null && wnkBusinessRechargeOrder.getState() != 1 && return_code.equals("SUCCESS")) {
                wnkBusinessRechargeOrder.setState(1);
                this.wnkBusinessRechargeOrderService.updateStateByOrderNo(wnkBusinessRechargeOrder);
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(wnkBusinessRechargeOrder.getBusiness_id());
                if (wnkBusinessAccount != null){
                    wnkBusinessAccount.setBalance(wnkBusinessAccount.getBalance() + wnkBusinessRechargeOrder.getAmount());
                    int balanceUpdateState = this.wnkBusinessAccountService.updateRelevantBalance(wnkBusinessAccount);
                    WnkBusinessBalanceDetail wnkBusinessBalanceDetail = new WnkBusinessBalanceDetail();
                    wnkBusinessBalanceDetail.setBusiness_id(wnkBusinessAccount.getId());
                    wnkBusinessBalanceDetail.setName("余额充值");
                    wnkBusinessBalanceDetail.setTransaction_amount(wnkBusinessRechargeOrder.getAmount());
                    wnkBusinessBalanceDetail.setJoin_time(new Date());
                    wnkBusinessBalanceDetail.setAfter_balance(wnkBusinessAccount.getBalance());
                    wnkBusinessBalanceDetail.setType(0);
                    wnkBusinessBalanceDetail.setState(0);
                    this.wnkBusinessBalanceDetailService.insertNewRecord(wnkBusinessBalanceDetail);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return "FAIL";
        }

        return "SUCCESS";
    }


    /**
     * description: 解析微信通知xml
     *
     * @param xml
     * @return
     * @author ex_yangxiaoyi
     * @see
     */
    @SuppressWarnings({ "unused", "rawtypes", "unchecked" })
    private static Map parseXmlToList(String xml) {
        Map retMap = new HashMap();
        try {
            StringReader read = new StringReader(xml);
            // 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
            InputSource source = new InputSource(read);
            // 创建一个新的SAXBuilder
            SAXBuilder sb = new SAXBuilder();
            // 通过输入源构造一个Document
            Document doc = (Document) sb.build(source);
            Element root = doc.getRootElement();// 指向根节点
            List<Element> es = root.getChildren();
            if (es != null && es.size() != 0) {
                for (Element element : es) {
                    retMap.put(element.getName(), element.getValue());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retMap;
    }



    /*
    * 万能卡商品支付回调
    * */
    @RequestMapping(value = "/wnkCommodityWXPayCallback")
    @ResponseBody
    public String wnkCommodityWXPayCallback(HttpServletRequest request){
        //订单号
        String order_no = null;
        String return_code = null;
        try {
            InputStream inStream = request.getInputStream();
            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
            outSteam.close();
            inStream.close();
            String resultStr  = new String(outSteam.toByteArray(),"utf-8");
            WXPayConfigImpl config = new WXPayConfigImpl();
            WXPay wxpay = new WXPay(config);
            Map<String, String> resultMap = WXPayUtil.xmlToMap(resultStr);  // 转换成map

            if (wxpay.isPayResultNotifySignatureValid(resultMap)) {
                // 签名正确
                // 进行处理。
                // 注意特殊情况：订单已经退款，但收到了支付结果成功的通知，不应把商户侧订单状态从退款改成支付成功
                //            Map<String, Object> resultMap = parseXmlToList(resultStr);
                String result_code = (String) resultMap.get("result_code");
                String is_subscribe = (String) resultMap.get("is_subscribe");
                String transaction_id = (String) resultMap.get("transaction_id");
                String sign = (String) resultMap.get("sign");
                String time_end = (String) resultMap.get("time_end");
                String bank_type = (String) resultMap.get("bank_type");

                order_no = (String) resultMap.get("out_trade_no");
                return_code = (String) resultMap.get("return_code");
            }
            else {
                // 签名错误，如果数据里没有sign字段，也认为是签名错误
                return "FAIL";
            }
        }  catch (Exception e) {
            return "FAIL";
        }
        try {
            WnkOrders wnkOrders = this.wnkOrdersService.selectByOrderNo(order_no);
            if (wnkOrders != null && wnkOrders.getState() != 1 && wnkOrders.getState() != 2 && return_code.equals("SUCCESS")) {
                this.wnkOrdersService.updateOrderStateByOrderNo(1,order_no);

                //订单二维码
                Map<Object,Object> orderQrcodeMap = new HashMap<Object,Object>();
                orderQrcodeMap.put("user_id",wnkOrders.getUser_id());
                orderQrcodeMap.put("type",2);
                orderQrcodeMap.put("order_no",wnkOrders.getOrder_no());
                JSONObject json = JSONObject.fromObject(orderQrcodeMap);
                String wnkQrcodeName = UUDUtil.getOrderIdByUUId();
                String wnkQrcodeUrl = QRCode.generateQRCode(json.toString(),wnkQrcodeName);
                this.wnkOrdersService.updateOrderQrcodeByOrderNo(wnkQrcodeName+".png",wnkOrders.getOrder_no());



                Users users = this.usersService.selectById(wnkOrders.getUser_id());
                if (users != null){
                    this.integralDetailService.insertIntrgralDetailRecord(users.getId(),"商品购买",wnkOrders.getAmount(),0);

                    Map<String,Object> wnkIntegralMap = this.wnkIntegralUserServer.getUserIntegral(wnkOrders.getBusiness_id(),users.getId());
                    Integer addWnkIntegralUserState = 1;
                    if (wnkIntegralMap == null){
                        addWnkIntegralUserState = this.wnkIntegralUserServer.addUserIntegral(wnkOrders.getBusiness_id(),users.getId());
                    }
//                    if (addWnkIntegralUserState > 0 && users.getMember_card_level() != -1){
                    if (addWnkIntegralUserState > 0){
                        Map<String,Object> recommendIntegralMap = this.wnkIntegralUserServer.getUserIntegral(wnkOrders.getBusiness_id(),users.getId());
                        if (recommendIntegralMap == null){
                            this.wnkIntegralUserServer.addUserIntegral(wnkOrders.getBusiness_id(),users.getId());
                        }
                        // 增加用户在此商户的积分余额
                        this.wnkIntegralUserServer.increaseUserIntegral(users.getId(),wnkOrders.getBusiness_id(),wnkOrders.getAmount());
                        // 插入积分记录 0=收入
                        this.wnkIntegralIncomeService.addIntegralRecord("商品购买",wnkOrders.getAmount(),users.getId(),wnkOrders.getBusiness_id(),0);

                        WnkBusinessLevelIntegralDetail wnkBusinessLevelIntegralDetail = new WnkBusinessLevelIntegralDetail();
                        wnkBusinessLevelIntegralDetail.setBusiness_id(wnkOrders.getBusiness_id());
                        wnkBusinessLevelIntegralDetail.setName("客户消费");
                        wnkBusinessLevelIntegralDetail.setIntegral_number(wnkOrders.getAmount());
                        wnkBusinessLevelIntegralDetail.setTransactions_date(new Date());
                        wnkBusinessLevelIntegralDetail.setTransactions_type(2);
                        wnkBusinessLevelIntegralDetail.setUser_id(users.getId());
                        wnkBusinessLevelIntegralDetail.setPay_type(4);
                        WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(wnkOrders.getBusiness_id());
                        if (wnkBusinessAccount != null){
                            wnkBusinessAccount.setLevel_integral(wnkBusinessAccount.getLevel_integral() - wnkOrders.getAmount().intValue());
                            this.wnkBusinessAccountService.updateRelevantBalance(wnkBusinessAccount);
                            this.wnkBusinessLevelIntegralDetailService.insertNewRecordUserXF(wnkBusinessLevelIntegralDetail);
                        }

                    }

                    if (users.getRecommend_user_id() != -1){
                        if (users.getRecommend_type() == 0){
                            Users recommendUser = this.usersService.selectById(users.getRecommend_user_id());
                            if (recommendUser.getMember_card_level() != -1){
                                Map<String,Object> recommendIntegralMap = this.wnkIntegralUserServer.getUserIntegral(wnkOrders.getBusiness_id(),recommendUser.getId());
                                if (recommendIntegralMap == null){
                                    this.wnkIntegralUserServer.addUserIntegral(wnkOrders.getBusiness_id(),recommendUser.getId());
                                }
                                this.integralDetailService.insertIntrgralDetailRecord(users.getRecommend_user_id(),"朋友购买商品",wnkOrders.getAmount(),0);
                                // 增加用户下级在此商户的积分余额
                                this.wnkIntegralUserServer.increaseUserIntegral(users.getRecommend_user_id(),wnkOrders.getBusiness_id(),wnkOrders.getAmount());
                                this.wnkIntegralIncomeService.addIntegralRecord("朋友购买商品",wnkOrders.getAmount(),users.getRecommend_user_id(),wnkOrders.getBusiness_id(),0);
                            }
                        }
                        else{
                            WnkBusinessAccount wnkBusinessAccount2 = this.wnkBusinessAccountService.selectById(users.getRecommend_user_id());
                            if (wnkBusinessAccount2 != null){
                                WnkBusinessConsumptionIntegralDetail wnkBusinessConsumptionIntegralDetail = new WnkBusinessConsumptionIntegralDetail();
                                wnkBusinessConsumptionIntegralDetail.setBusiness_id(users.getRecommend_user_id());
                                wnkBusinessConsumptionIntegralDetail.setName("用户购买商品");
                                wnkBusinessConsumptionIntegralDetail.setIntegral_number(wnkOrders.getAmount());
                                wnkBusinessConsumptionIntegralDetail.setTransactions_date(new Date());
                                wnkBusinessConsumptionIntegralDetail.setTransactions_type(0);
                                wnkBusinessAccount2.setConsumption_integral(wnkBusinessAccount2.getConsumption_integral() + wnkOrders.getAmount().intValue());
                                int businessIntegralUpdateState = this.wnkBusinessAccountService.updateRelevantBalance(wnkBusinessAccount2);
                                if (businessIntegralUpdateState > 0){
                                    this.wnkBusinessConsumptionIntegralDetailService.insertNewRecord(wnkBusinessConsumptionIntegralDetail);
                                }
                            }
                        }


                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return "FAIL";
        }

        return "SUCCESS";
    }


    /*
    * 万能卡商品支付2.0版本支付回调
    * */
    @RequestMapping(value = "/wnkCommodityWXPayTwoCallback")
    @ResponseBody
    public String wnkCommodityWXPayTwoCallback(HttpServletRequest request)  {
        //订单号
        String order_no = null;
        String return_code = null;
        try {
            InputStream inStream = request.getInputStream();
            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
            outSteam.close();
            inStream.close();
            String resultStr  = new String(outSteam.toByteArray(),"utf-8");
            WXPayConfigImpl config = new WXPayConfigImpl();
            WXPay wxpay = new WXPay(config);
            Map<String, String> resultMap = WXPayUtil.xmlToMap(resultStr);  // 转换成map

            if (wxpay.isPayResultNotifySignatureValid(resultMap)) {
                // 签名正确
                // 进行处理。
                // 注意特殊情况：订单已经退款，但收到了支付结果成功的通知，不应把商户侧订单状态从退款改成支付成功
                //            Map<String, Object> resultMap = parseXmlToList(resultStr);
                String result_code = (String) resultMap.get("result_code");
                String is_subscribe = (String) resultMap.get("is_subscribe");
                String transaction_id = (String) resultMap.get("transaction_id");
                String sign = (String) resultMap.get("sign");
                String time_end = (String) resultMap.get("time_end");
                String bank_type = (String) resultMap.get("bank_type");

                order_no = (String) resultMap.get("out_trade_no");
                return_code = (String) resultMap.get("return_code");
            }
            else {
                // 签名错误，如果数据里没有sign字段，也认为是签名错误
                return "FAIL";
            }
        }  catch (Exception e) {
            return "FAIL";
        }
        try {
            WnkOrdersTwo wnkOrdersTwo = this.wnkOrdersTwoService.selectByOrderNo(order_no);
            if (wnkOrdersTwo != null && wnkOrdersTwo.getState() != 1 && wnkOrdersTwo.getState() != 2 && return_code.equals("SUCCESS")) {
                Users users = this.usersService.selectById(wnkOrdersTwo.getUser_id());
                if (users != null){
                    //支付结果,true-支付成功,false-支付失败
                    Boolean pay_status = true;
                    if (wnkOrdersTwo.getGeneral_integral() > 0.00){
                        users.setGeneral_integral(users.getGeneral_integral() - wnkOrdersTwo.getGeneral_integral());
                    }
                    int updateBalance = this.usersService.updateUserTYAndXFAndSilverCoinBalance(users);
                    if (updateBalance <= 0){
                        pay_status = false;
                    }

                    if (wnkOrdersTwo.getSend_integral() > 0.00){
                        Integer updateSendIntegralBalance = this.wnkSendIntegralUserService.updateIntegral(wnkOrdersTwo.getUser_id(),wnkOrdersTwo.getBusiness_id(),wnkOrdersTwo.getSend_integral());
                        if (updateSendIntegralBalance <= 0){
                            pay_status = false;
                        }
                    }

                    if(wnkOrdersTwo.getCoupon() > 0){
                        WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(wnkOrdersTwo.getBusiness_id());
                        List<Map<Object,Object>> materielList = this.extensionMaterielService.selectMatensionByTypeId(wnkStoreInformation.getBusiness_type_id());
                        Integer materielId = (Integer)materielList.get(0).get("id");
                        UserCoupons userCoupons = this.userCouponsService.selectByBusinessTypeIdAndUserId(materielId,wnkOrdersTwo.getUser_id());
                        if (wnkOrdersTwo.getCoupon() > 0){
                            if (materielList.size() <= 0){
                                pay_status = false;
                            }
                            else{
                                if (materielList.size() >= 2){
                                    Integer materielId2 = (Integer)materielList.get(1).get("id");
                                    UserCoupons userCoupons2 = this.userCouponsService.selectByBusinessTypeIdAndUserId(materielId2,wnkOrdersTwo.getUser_id());
                                    if (userCoupons2 != null && userCoupons2.getSurplus_number() > userCoupons.getSurplus_number()){
                                        userCoupons = userCoupons2;
                                    }
                                }
                                if (userCoupons == null || userCoupons.getSurplus_number() < wnkOrdersTwo.getCoupon()){
                                    pay_status = false;
                                }
                            }
                        }

                        if (wnkOrdersTwo.getCoupon() > 0){
                            userCoupons.setSurplus_number(userCoupons.getSurplus_number() - wnkOrdersTwo.getCoupon());
                            Integer updateCouponBalance = this.userCouponsService.updateSurplusNumber(userCoupons);
                            if (updateCouponBalance <= 0){
                                pay_status = false;
                            }
                        }
                    }
                    if (pay_status == true){
                        //订单二维码
                        Map<Object,Object> orderQrcodeMap = new HashMap<Object,Object>();
                        orderQrcodeMap.put("user_id",wnkOrdersTwo.getUser_id());
                        orderQrcodeMap.put("type",2);
                        orderQrcodeMap.put("order_no",wnkOrdersTwo.getOrder_no());
                        JSONObject json = JSONObject.fromObject(orderQrcodeMap);
                        String wnkQrcodeName = UUDUtil.getOrderIdByUUId();
                        String wnkQrcodeUrl = QRCode.generateQRCode(json.toString(),wnkQrcodeName);
                        this.wnkOrdersTwoService.updateOrderQrCodeByOrderNo(wnkQrcodeName+".png",wnkOrdersTwo.getOrder_no());

                        this.wnkOrdersTwoService.updateOrderStateByOrderNo(1,order_no);
                        this.wnkOrdersTwoService.updateOrderPayDateByOrderNo(new Date(),order_no);

                        if (wnkOrdersTwo.getGeneral_integral() > 0.00 || wnkOrdersTwo.getSend_integral() > 0.00 || wnkOrdersTwo.getCash_amount() > 0L){

                            WnkBusinessLevelIntegralDetail wblid = new WnkBusinessLevelIntegralDetail();
                            wblid.setBusiness_id(wnkOrdersTwo.getBusiness_id());
                            wblid.setName("客户消费");
                            wblid.setIntegral_number(wnkOrdersTwo.getGeneral_integral() + wnkOrdersTwo.getSend_integral() + wnkOrdersTwo.getCash_amount());
                            wblid.setTransactions_date(new Date());
                            wblid.setTransactions_type(2);
                            wblid.setUser_id(users.getId());
                            wblid.setPay_type(5);

                            WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(wnkOrdersTwo.getBusiness_id());
                            wnkBusinessAccount.setLevel_integral(wnkBusinessAccount.getLevel_integral() - wnkOrdersTwo.getGeneral_integral() - wnkOrdersTwo.getSend_integral() - wnkOrdersTwo.getCash_amount());
                            this.wnkBusinessAccountService.updateRelevantBalance(wnkBusinessAccount);
                            this.wnkBusinessLevelIntegralDetailService.insertNewRecordUserXF(wblid);

                            WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(wnkBusinessAccount.getId());
                            if (wnkStoreInformation != null){
                                //扣除等级积分数量
                                Double kcLevelIntegralNumber = wnkOrdersTwo.getGeneral_integral() + wnkOrdersTwo.getSend_integral() + wnkOrdersTwo.getCash_amount();

                                //  扣除等级积分
                                if (wnkStoreInformation.getGetui_appid() != null && !"".equals(wnkStoreInformation.getGetui_appid())){
                                    String pushTitle = "商品购买";
                                    String pushContent = "用户成功购买了商品，等级积分消费支出"+kcLevelIntegralNumber+"个，注意处理，请在猛戳商家版-订单-新订单查看";
                                    GeTuiBusinessUtil.pushUser(wnkStoreInformation.getGetui_appid(),pushTitle,pushContent);
                                }
                            }


                            if (wnkOrdersTwo.getGeneral_integral() > 0.00){
                                GeneralIntegralExpenditure generalIntegralExpenditure = new GeneralIntegralExpenditure();
                                generalIntegralExpenditure.setName("商品购买");
                                generalIntegralExpenditure.setExpenditure_date(new Date());
                                generalIntegralExpenditure.setExpenditure_amount(wnkOrdersTwo.getGeneral_integral());
                                generalIntegralExpenditure.setExpenditure_after_balance(users.getGeneral_integral());
                                generalIntegralExpenditure.setUser_id(wnkOrdersTwo.getUser_id());
                                generalIntegralExpenditure.setExpenditure_type(4);
                                this.generalIntegralExpenditureService.insertExchangeSilverCoinRecord(generalIntegralExpenditure);
                            }
                            if (wnkOrdersTwo.getSend_integral() > 0.00){
                                this.wnkSendIntegralDetailUserService.addIntegralRecord("商品购买",wnkOrdersTwo.getSend_integral(),users.getId(),wnkOrdersTwo.getBusiness_id(),1);
                                if (users.getGetui_appid() != null && !"".equals(users.getGetui_appid())){
                                    String pushTitle = "现金劵消费";
                                    String pushContent = "您已成功使用"+wnkOrdersTwo.getSend_integral()+"现金劵兑换了商品，请在猛戳-我的订单查看";
                                    GeTuiUserUtil.pushUser(users.getGetui_appid(),pushTitle,pushContent);
                                }
                            }

                        }
                    }

                }

                if (users.getGetui_appid() != null && !"".equals(users.getGetui_appid())){
                    String pushTitle = "支付成功";
                    String pushContent = "您已支付成功，订单号为"+wnkOrdersTwo.getOrder_no()+"不要忘记使用哟！请在猛戳-订单中心-待使用查看";
                    GeTuiUserUtil.pushUser(users.getGetui_appid(),pushTitle,pushContent);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return "FAIL";
        }
        return "SUCCESS";
    }


    /*
    * 万能卡商品支付2.0版本支付回调 - 酒店类商家
    * */
    @RequestMapping(value = "/wnkCommodityWXPayTwoCallbackByHotel")
    @ResponseBody
    public String wnkCommodityWXPayTwoCallbackByHotel(HttpServletRequest request)  {
        //订单号
        String order_no = null;
        String return_code = null;
        try {
            InputStream inStream = request.getInputStream();
            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
            outSteam.close();
            inStream.close();
            String resultStr  = new String(outSteam.toByteArray(),"utf-8");
            WXPayConfigImpl config = new WXPayConfigImpl();
            WXPay wxpay = new WXPay(config);
            Map<String, String> resultMap = WXPayUtil.xmlToMap(resultStr);  // 转换成map

            if (wxpay.isPayResultNotifySignatureValid(resultMap)) {
                // 签名正确
                // 进行处理。
                // 注意特殊情况：订单已经退款，但收到了支付结果成功的通知，不应把商户侧订单状态从退款改成支付成功
                //            Map<String, Object> resultMap = parseXmlToList(resultStr);
                String result_code = (String) resultMap.get("result_code");
                String is_subscribe = (String) resultMap.get("is_subscribe");
                String transaction_id = (String) resultMap.get("transaction_id");
                String sign = (String) resultMap.get("sign");
                String time_end = (String) resultMap.get("time_end");
                String bank_type = (String) resultMap.get("bank_type");

                order_no = (String) resultMap.get("out_trade_no");
                return_code = (String) resultMap.get("return_code");
            }
            else {
                // 签名错误，如果数据里没有sign字段，也认为是签名错误
                return "FAIL";
            }
        }  catch (Exception e) {
            return "FAIL";
        }
        try {
            WnkOrdersTwo wnkOrdersTwo = this.wnkOrdersTwoService.selectByOrderNo(order_no);
            if (wnkOrdersTwo != null && wnkOrdersTwo.getState() != 1 && wnkOrdersTwo.getState() != 2 && return_code.equals("SUCCESS")) {
                Users users = this.usersService.selectById(wnkOrdersTwo.getUser_id());
                if (users != null){

                    //支付结果,true-支付成功,false-支付失败
                    Boolean pay_status = true;
                    if (wnkOrdersTwo.getGeneral_integral() > 0.00){
                        users.setGeneral_integral(users.getGeneral_integral() - wnkOrdersTwo.getGeneral_integral());
                    }
                    int updateBalance = this.usersService.updateUserTYAndXFAndSilverCoinBalance(users);
                    if (updateBalance <= 0){
                        pay_status = false;
                    }

                    if (wnkOrdersTwo.getSend_integral() > 0.00){
                        Integer updateSendIntegralBalance = this.wnkSendIntegralUserService.updateIntegral(wnkOrdersTwo.getUser_id(),wnkOrdersTwo.getBusiness_id(),wnkOrdersTwo.getSend_integral());
                        if (updateSendIntegralBalance <= 0){
                            pay_status = false;
                        }
                    }

                    if(wnkOrdersTwo.getCoupon() > 0){
                        WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(wnkOrdersTwo.getBusiness_id());
                        List<Map<Object,Object>> materielList = this.extensionMaterielService.selectMatensionByTypeId(wnkStoreInformation.getBusiness_type_id());
                        Integer materielId = (Integer)materielList.get(0).get("id");
                        UserCoupons userCoupons = this.userCouponsService.selectByBusinessTypeIdAndUserId(materielId,wnkOrdersTwo.getUser_id());
                        if (wnkOrdersTwo.getCoupon() > 0){
                            if (materielList.size() <= 0){
                                pay_status = false;
                            }
                            else{
                                if (materielList.size() >= 2){
                                    Integer materielId2 = (Integer)materielList.get(1).get("id");
                                    UserCoupons userCoupons2 = this.userCouponsService.selectByBusinessTypeIdAndUserId(materielId2,wnkOrdersTwo.getUser_id());
                                    if (userCoupons2 != null && userCoupons2.getSurplus_number() > userCoupons.getSurplus_number()){
                                        userCoupons = userCoupons2;
                                    }
                                }
                                if (userCoupons == null || userCoupons.getSurplus_number() < wnkOrdersTwo.getCoupon()){
                                    pay_status = false;
                                }
                            }
                        }

                        if (wnkOrdersTwo.getCoupon() > 0){
                            userCoupons.setSurplus_number(userCoupons.getSurplus_number() - wnkOrdersTwo.getCoupon());
                            Integer updateCouponBalance = this.userCouponsService.updateSurplusNumber(userCoupons);
                            if (updateCouponBalance <= 0){
                                pay_status = false;
                            }
                        }
                    }
                    if (pay_status == true){
                        //订单二维码
                        Map<Object,Object> orderQrcodeMap = new HashMap<Object,Object>();
                        orderQrcodeMap.put("user_id",wnkOrdersTwo.getUser_id());
                        orderQrcodeMap.put("type",2);
                        orderQrcodeMap.put("order_no",wnkOrdersTwo.getOrder_no());
                        JSONObject json = JSONObject.fromObject(orderQrcodeMap);
                        String wnkQrcodeName = UUDUtil.getOrderIdByUUId();
                        String wnkQrcodeUrl = QRCode.generateQRCode(json.toString(),wnkQrcodeName);
                        this.wnkOrdersTwoService.updateOrderQrCodeByOrderNo(wnkQrcodeName+".png",wnkOrdersTwo.getOrder_no());

                        this.wnkOrdersTwoService.updateOrderStateByOrderNo(3,order_no);
                        this.wnkOrdersTwoService.updateOrderPayDateByOrderNo(new Date(),order_no);

                        if (wnkOrdersTwo.getGeneral_integral() > 0.00 || wnkOrdersTwo.getSend_integral() > 0.00 || wnkOrdersTwo.getCash_amount() > 0L){

                            WnkBusinessLevelIntegralDetail wblid = new WnkBusinessLevelIntegralDetail();
                            wblid.setBusiness_id(wnkOrdersTwo.getBusiness_id());
                            wblid.setName("客户消费");
                            wblid.setIntegral_number(wnkOrdersTwo.getGeneral_integral() + wnkOrdersTwo.getSend_integral() + wnkOrdersTwo.getCash_amount());
                            wblid.setTransactions_date(new Date());
                            wblid.setTransactions_type(2);
                            wblid.setUser_id(users.getId());
                            wblid.setPay_type(5);

                            WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(wnkOrdersTwo.getBusiness_id());
                            wnkBusinessAccount.setLevel_integral(wnkBusinessAccount.getLevel_integral() - wnkOrdersTwo.getGeneral_integral() - wnkOrdersTwo.getSend_integral() - wnkOrdersTwo.getCash_amount());
                            this.wnkBusinessAccountService.updateRelevantBalance(wnkBusinessAccount);
                            this.wnkBusinessLevelIntegralDetailService.insertNewRecordUserXF(wblid);

                            WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(wnkBusinessAccount.getId());
                            if (wnkStoreInformation != null){
                                //扣除等级积分数量
                                Double kcLevelIntegralNumber = wnkOrdersTwo.getGeneral_integral() + wnkOrdersTwo.getSend_integral() + wnkOrdersTwo.getCash_amount();

                                //  扣除等级积分
                                if (wnkStoreInformation.getGetui_appid() != null && !"".equals(wnkStoreInformation.getGetui_appid())){
                                    String pushTitle = "商品购买";
                                    String pushContent = "用户成功购买了商品，等级积分消费支出"+kcLevelIntegralNumber+"个，注意处理，请在猛戳商家版-订单-新订单查看";
                                    GeTuiBusinessUtil.pushUser(wnkStoreInformation.getGetui_appid(),pushTitle,pushContent);
                                }
                            }


                            if (wnkOrdersTwo.getGeneral_integral() > 0.00){
                                GeneralIntegralExpenditure generalIntegralExpenditure = new GeneralIntegralExpenditure();
                                generalIntegralExpenditure.setName("商品购买");
                                generalIntegralExpenditure.setExpenditure_date(new Date());
                                generalIntegralExpenditure.setExpenditure_amount(wnkOrdersTwo.getGeneral_integral());
                                generalIntegralExpenditure.setExpenditure_after_balance(users.getGeneral_integral());
                                generalIntegralExpenditure.setUser_id(wnkOrdersTwo.getUser_id());
                                generalIntegralExpenditure.setExpenditure_type(4);
                                this.generalIntegralExpenditureService.insertExchangeSilverCoinRecord(generalIntegralExpenditure);
                            }
                            if (wnkOrdersTwo.getSend_integral() > 0.00){
                                this.wnkSendIntegralDetailUserService.addIntegralRecord("商品购买",wnkOrdersTwo.getSend_integral(),users.getId(),wnkOrdersTwo.getBusiness_id(),1);
                                if (users.getGetui_appid() != null && !"".equals(users.getGetui_appid())){
                                    String pushTitle = "现金劵消费";
                                    String pushContent = "您已成功使用"+wnkOrdersTwo.getSend_integral()+"现金劵兑换了商品，请在猛戳-我的订单查看";
                                    GeTuiUserUtil.pushUser(users.getGetui_appid(),pushTitle,pushContent);
                                }
                            }

                        }
                    }
//
//
//
//
                }

                if (users.getGetui_appid() != null && !"".equals(users.getGetui_appid())){
                    String pushTitle = "支付成功";
                    String pushContent = "您已支付成功，订单号为"+wnkOrdersTwo.getOrder_no()+"不要忘记使用哟！请在猛戳-订单中心-待使用查看";
                    GeTuiUserUtil.pushUser(users.getGetui_appid(),pushTitle,pushContent);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return "FAIL";
        }
        return "SUCCESS";
    }



    /*
    * 用户办理商家会员卡微信App下单支付
    * */
    @RequestMapping(value = "/wechatPayBuyBusinessMemberCardWXAppPayCallback")
    @ResponseBody
    public String wechatPayBuyBusinessMemberCardWXAppPayCallback(HttpServletRequest request){
        //订单号
        String order_no = null;
        String return_code = null;
        try {
            InputStream inStream = request.getInputStream();
            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
            outSteam.close();
            inStream.close();
            String resultStr  = new String(outSteam.toByteArray(),"utf-8");
            WXPayConfigImpl config = new WXPayConfigImpl();
            WXPay wxpay = new WXPay(config);
            Map<String, String> resultMap = WXPayUtil.xmlToMap(resultStr);  // 转换成map

            if (wxpay.isPayResultNotifySignatureValid(resultMap)) {
                // 签名正确
                // 进行处理。
                // 注意特殊情况：订单已经退款，但收到了支付结果成功的通知，不应把商户侧订单状态从退款改成支付成功
                //            Map<String, Object> resultMap = parseXmlToList(resultStr);
                String result_code = (String) resultMap.get("result_code");
                String is_subscribe = (String) resultMap.get("is_subscribe");
                String transaction_id = (String) resultMap.get("transaction_id");
                String sign = (String) resultMap.get("sign");
                String time_end = (String) resultMap.get("time_end");
                String bank_type = (String) resultMap.get("bank_type");

                order_no = (String) resultMap.get("out_trade_no");
                return_code = (String) resultMap.get("return_code");
            }
            else {
                // 签名错误，如果数据里没有sign字段，也认为是签名错误
                return "FAIL";
            }
        }  catch (Exception e) {
            return "FAIL";
        }
        try {
            UserOpenBusinessMemberCardWXOrders userOpenBusinessMemberCardWXOrders = this.userOpenBusinessMemberCardWXOrdersService.selectByOrderNo(order_no);
            if (userOpenBusinessMemberCardWXOrders != null && userOpenBusinessMemberCardWXOrders.getState() != 1 && return_code.equals("SUCCESS")) {
                this.userOpenBusinessMemberCardWXOrdersService.updateOrderStateByOrderNo(1,order_no);

                Users users = this.usersService.selectById(userOpenBusinessMemberCardWXOrders.getUser_id());
                if (users != null){
                    //进行开卡操作
                    WnkBusinessTypeOpenCard wnkBusinessTypeOpenCard = this.wnkBusinessTypeOpenCardService.selectOpenCardStateByUserIdAndBusinessId(users.getId(),userOpenBusinessMemberCardWXOrders.getBusiness_id());
                    if (wnkBusinessTypeOpenCard == null){
                        wnkBusinessTypeOpenCard = new WnkBusinessTypeOpenCard();
                        wnkBusinessTypeOpenCard.setUser_id(users.getId());
                        wnkBusinessTypeOpenCard.setBusiness_type_id(userOpenBusinessMemberCardWXOrders.getBusiness_type_id());
                        wnkBusinessTypeOpenCard.setOpen_card_time(new Date());
                        wnkBusinessTypeOpenCard.setBusiness_id(userOpenBusinessMemberCardWXOrders.getBusiness_id());

                        WnkBusinessType wnkBusinessType = wnkBusinessTypeService.selectById(userOpenBusinessMemberCardWXOrders.getBusiness_type_id());

                        if (wnkBusinessType.getName().equals("健身")) {
                            //生成健身卡二维码
                            String wnkQrcodeName = UUDUtil.getOrderIdByUUId();
                            String text = "{\"type\":\"4\",\"user_id\":\"" + users.getId() + "\"}";
                            String logoPath = ImageToolsController.qrcodeURL +  "/logo.jpg";
                            StringBuilder sb = new StringBuilder(logoPath);
                            BufferedImage sourceImg1 = ImageIO.read(new File(logoPath));
                            sb.append(120).append("w_").append(120).append("h_1e_1c.src");
                            BufferedImage qrImg3 = generateCode(text, sb.toString(), 250, 250, 250, 250);
                            File file = new File(ImageToolsController.qrcodeURL + "/" + wnkQrcodeName + ".png");
                            FileOutputStream out = new FileOutputStream(file);
                            createQrCodePic(250, 250, qrImg3, 250, 250, 0, 0, sourceImg1, 50, 50, 100, 100, out);
                            wnkBusinessTypeOpenCard.setQrcode(wnkQrcodeName + ".png");
                            wnkBusinessTypeOpenCard.setType(1); // 类型:健身卡
                        } else {
                            wnkBusinessTypeOpenCard.setType(0); // 普通商家会员卡
                        }


                        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd,hh:mm:ss");
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(new Date());
                        calendar.add(Calendar.MONTH, 12);
                        wnkBusinessTypeOpenCard.setCard_end_time(calendar.getTime());
                        this.wnkBusinessTypeOpenCardService.insertOpenCardRecord(wnkBusinessTypeOpenCard);

                    }
                    else{
                        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd,hh:mm:ss");
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(wnkBusinessTypeOpenCard.getCard_end_time());
                        calendar.add(Calendar.MONTH, 12);
                        wnkBusinessTypeOpenCard.setCard_end_time(calendar.getTime());
                        WnkBusinessType wnkBusinessType = wnkBusinessTypeService.selectById(userOpenBusinessMemberCardWXOrders.getBusiness_type_id());
                        if (wnkBusinessType.getName().equals("健身")) {
                            //生成健身卡二维码
                            String wnkQrcodeName = UUDUtil.getOrderIdByUUId();
                            String text = "{\"type\":\"4\",\"user_id\":\"" + users.getId() + "\"}";
                            String logoPath = ImageToolsController.qrcodeURL +  "/logo.jpg";
                            StringBuilder sb = new StringBuilder(logoPath);
                            BufferedImage sourceImg1 = ImageIO.read(new File(logoPath));
                            sb.append(120).append("w_").append(120).append("h_1e_1c.src");
                            BufferedImage qrImg3 = generateCode(text, sb.toString(), 250, 250, 250, 250);
                            File file = new File(ImageToolsController.qrcodeURL + "/" + wnkQrcodeName + ".png");
                            FileOutputStream out = new FileOutputStream(file);
                            createQrCodePic(250, 250, qrImg3, 250, 250, 0, 0, sourceImg1, 50, 50, 100, 100, out);
                            wnkBusinessTypeOpenCard.setQrcode(wnkQrcodeName + ".png");
                        }
                        this.wnkBusinessTypeOpenCardService.updateCardEndTime(wnkBusinessTypeOpenCard);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return "FAIL";
        }

        return "SUCCESS";
    }
}
