package com.springmvc.controller.app;

import com.springmvc.entity.*;
import com.springmvc.service.impl.*;
import com.springmvc.utils.GeTuiBusinessUtil;
import com.springmvc.utils.LoginSessionCheckUtil;
import com.springmvc.utils.Result;
import com.springmvc.utils.UUDUtil;
import com.springmvc.utils.WechatEnterprisePay.WeChatConfig;
import com.springmvc.utils.qrCode.QRCode;
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
public class WnkCommodityOrderAppController {
    @Resource
    private LoginSessionIdsService sessionIdsService;
    @Resource
    private UsersBankCardService usersBankCardService;
    @Resource
    private UsersService usersService;
    @Resource
    private WnkCommoditiesService wnkCommoditiesService;
    @Resource
    private WnkOrdersService wnkOrdersService;
    @Resource
    private WnkOrderCommodityService wnkOrderCommodityService;
    @Resource
    private WnkCommoditySpecificationsService wnkCommoditySpecificationsService;
    @Resource
    private ConsumptionIntegralExpenditureService consumptionIntegralExpenditureService;
    @Resource
    private GeneralIntegralExpenditureService generalIntegralExpenditureService;
    @Resource
    private IntegralDetailService integralDetailService;
    @Resource
    private WnkIntegralUserServer wnkIntegralUserServer;
    @Resource
    private WnkIntegralIncomeService wnkIntegralIncomeService;
    @Resource
    private WnkBusinessAccountService wnkBusinessAccountService;
    @Resource
    private WnkBusinessConsumptionIntegralDetailService wnkBusinessConsumptionIntegralDetailService;
    @Resource
    private WnkBusinessBalanceDetailService wnkBusinessBalanceDetailService;
    @Resource
    private UserScanCodeWxPayService userScanCodeWxPayService;
    @Resource
    private WnkSendIntegralUserService wnkSendIntegralUserService;
    @Resource
    private WnkSendIntegralDetailUserService wnkSendIntegralDetailUserService;
    @Resource
    private WnkStoreInformationService wnkStoreInformationService;
    @Resource
    private WnkBusinessTypeService wnkBusinessTypeService;
    @Resource
    private UserCouponsService userCouponsService;
    @Resource
    private WnkBusinessTypeOpenCardService wnkBusinessTypeOpenCardService;
    @Resource
    private WnkBusinessLevelIntegralDetailService wnkBusinessLevelIntegralDetailService;
    @Resource
    private ExtensionMaterielService extensionMaterielService;

    // 万能卡商品订单微信支付
    @RequestMapping(value = "/wnkCommodityWxPay", method = RequestMethod.POST,params = {"user_id","commodity_id","guige_id","commodity_number"})
    @ResponseBody
    public Result wnkCommodityWxPay(HttpServletRequest request, HttpServletResponse response, Integer user_id, Integer commodity_id,Integer guige_id,Integer commodity_number){
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
                        result.setStatus(Result.FAIL);
                        result.setMsg("请先绑定微信");
                    }
                    else{
                        WnkCommodities wnkCommodities = this.wnkCommoditiesService.selectById(commodity_id);
                        WnkCommoditySpecifications wnkCommoditySpecifications = this.wnkCommoditySpecificationsService.selectById(guige_id);
                        if (wnkCommodities == null || wnkCommodities.getState() != 0){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("商品不存在或已下架");
                        }
                        else if (wnkCommoditySpecifications == null){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("商品规格不存在");
                        }
                        else{
                            WnkOrders wnkOrders = new WnkOrders();
                            wnkOrders.setBusiness_id(wnkCommodities.getBusiness_id());
                            wnkOrders.setUser_id(user_id);
                            wnkOrders.setOrder_no(UUDUtil.getOrderIdByUUId());
                            wnkOrders.setSubmit_time(new Date());
                            wnkOrders.setAmount(wnkCommodities.getPrice() * commodity_number);
                            wnkOrders.setPay_way(3);
                            wnkOrders.setState(0);
                            int state = this.wnkOrdersService.insertNewOrder(wnkOrders);
                            if (state <= 0){
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("下单失败");
                            }
                            else{
                                Double amount = Double.valueOf(wnkOrders.getAmount() * commodity_number);
                                Map<String,Object> resutMap = WechatPayLineUtil.wechatPayWnkCommodity(wnkOrders.getOrder_no(), amount, users.getWx_unionid(), request, response);
                                if ((Boolean) resutMap.get("status") == true){
                                    messageMap.put("wx_state",1);
                                    messageMap.put("order_no",wnkOrders.getOrder_no());
                                    messageMap.put("wx_config",resutMap.get("wx_config"));
                                    messageMap.put("order_id",wnkOrders.getId());
                                    result.setData(messageMap);
                                    result.setStatus(Result.SUCCESS);
                                    result.setMsg("微信下单成功");
                                    WnkOrderCommodity wnkOrderCommodity = new WnkOrderCommodity();
                                    wnkOrderCommodity.setOrder_id(wnkOrders.getId());
                                    wnkOrderCommodity.setCommodity_id(commodity_id);
                                    wnkOrderCommodity.setBuy_number(commodity_number);
                                    wnkOrderCommodity.setCount_amount(wnkCommodities.getPrice());
                                    wnkOrderCommodity.setCommodity_guige_id(guige_id);
                                    this.wnkOrderCommodityService.insertNewOrderCommodity(wnkOrderCommodity);
                                }
                                else{
                                    result.setData("");
                                    result.setStatus(Result.FAIL);
                                    String msg = (String)resutMap.get("msg");
                                    if (msg == null || msg.equals("")){
                                        result.setMsg("未知错误");
                                    }
                                    else{
                                        result.setMsg((String)resutMap.get("msg"));
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
            result.setMsg("下单失败");
        }
        return result;
    }


    // 万能卡商品订单微信App支付
    @RequestMapping(value = "/wnkCommodityWxPayApp", method = RequestMethod.POST,params = {"user_id","commodity_id","guige_id","commodity_number"})
    @ResponseBody
    public Result wnkCommodityWxPayApp(HttpServletRequest request, HttpServletResponse response, Integer user_id, Integer commodity_id,Integer guige_id,Integer commodity_number){
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
                    WnkCommodities wnkCommodities = this.wnkCommoditiesService.selectById(commodity_id);
                    WnkCommoditySpecifications wnkCommoditySpecifications = this.wnkCommoditySpecificationsService.selectById(guige_id);
                    if (wnkCommodities == null || wnkCommodities.getState() != 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("商品不存在或已下架");
                    }
                    else if (wnkCommoditySpecifications == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("商品规格不存在");
                    }
                    else if(wnkCommoditySpecifications.getInventory() != -1 && wnkCommoditySpecifications.getInventory() < commodity_number){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("当前规格库存不足");
                    }
                    else{
                        Double price = wnkCommoditySpecifications.getPrice() * commodity_number;
                        WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(wnkCommodities.getBusiness_id());
                        if (wnkBusinessAccount == null){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("商户不存在");
                        }
                        else if (wnkBusinessAccount.getLevel_integral() < price.intValue()){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("当前商户积分不足");
                        }
                        else{
                            if (users.getMember_card_level() != -1){
                                WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(wnkCommodities.getBusiness_id());
                                if (wnkStoreInformation != null){
                                    if (wnkStoreInformation != null){
                                        WnkBusinessType wnkBusinessType = this.wnkBusinessTypeService.selectById(wnkStoreInformation.getBusiness_type_id());
                                        if (wnkBusinessType != null && wnkBusinessType.getCommdity_charge_way() == 0 && wnkBusinessType.getMake_wnk_state() == 1 && wnkCommoditySpecifications.getIs_wnk() == 0){
                                            if (wnkBusinessType.getDiscount_type() == 0){
                                                price = wnkBusinessType.getCommodifty_price() * commodity_number;
                                            }
                                            else{
                                                Double bili = wnkBusinessType.getCommodifty_price() / 100;
                                                price = wnkCommoditySpecifications.getPrice() * commodity_number * bili;
                                                price = Double.valueOf(String.format("%.2f", price));
                                            }

                                        }
                                        else if (wnkBusinessType != null && wnkBusinessType.getCommdity_charge_way() == 1 && wnkBusinessType.getMake_wnk_state() == 1 && wnkCommoditySpecifications.getIs_wnk() == 0){
                                            WnkBusinessTypeOpenCard wnkBusinessTypeOpenCard = this.wnkBusinessTypeOpenCardService.selectOpenCardStateByUserIdAndBusinessId(user_id,wnkStoreInformation.getBusiness_id());
                                            if (wnkBusinessTypeOpenCard != null){
                                                price = 0.00;
                                            }
                                        }
                                    }
                                }
                            }

                            WnkOrders wnkOrders = new WnkOrders();
                            wnkOrders.setBusiness_id(wnkCommodities.getBusiness_id());
                            wnkOrders.setUser_id(user_id);
                            wnkOrders.setOrder_no(UUDUtil.getOrderIdByUUId());
                            wnkOrders.setSubmit_time(new Date());
                            wnkOrders.setAmount(price);
                            wnkOrders.setPay_way(3);
                            wnkOrders.setState(0);
                            int state = this.wnkOrdersService.insertNewOrder(wnkOrders);
                            if (state <= 0){
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("下单失败");
                            }
                            else{
                                Map<String,Object> resutMap = WechatPayLineUtil.wechatPayWnkCommodityApp(wnkOrders.getOrder_no(), price, request, response);
                                if ((Boolean) resutMap.get("status") == true){
                                    SortedMap<String, Object> parameterMap = (SortedMap<String, Object>)resutMap.get("wx_config");
                                    JSONObject json = JSONObject.fromObject(parameterMap);

                                    WnkOrderCommodity wnkOrderCommodity = new WnkOrderCommodity();
                                    wnkOrderCommodity.setOrder_id(wnkOrders.getId());
                                    wnkOrderCommodity.setCommodity_id(commodity_id);
                                    wnkOrderCommodity.setBuy_number(commodity_number);
                                    wnkOrderCommodity.setCount_amount(price);
                                    wnkOrderCommodity.setCommodity_guige_id(guige_id);
                                    this.wnkOrderCommodityService.insertNewOrderCommodity(wnkOrderCommodity);

                                    Integer timestamp = (Integer) json.get("timestamp");

                                    String payData = "{\"appid\":\""+json.get("appid")+"\",\"noncestr\":\""+json.get("noncestr")+"\",\"package\":\"Sign=WXPay\",\"partnerid\":\""+json.get("partnerid")+"\",\"prepayid\":\""+json.get("prepayid")+"\",\"timestamp\":"+timestamp+",\"sign\":\""+json.get("sign")+"\"}";
                                    Map<String,Object> returnMap = new HashMap<String,Object>();
                                    returnMap.put("config",payData);
                                    returnMap.put("order_id",wnkOrders.getId());
                                    result.setData(returnMap);
                                    result.setStatus(Result.SUCCESS);
                                    result.setMsg("下单成功");

                                    //更新规格库存
                                    if (wnkCommoditySpecifications.getInventory() != -1){
                                        wnkCommoditySpecifications.setInventory(wnkCommoditySpecifications.getInventory() - commodity_number);
                                        this.wnkCommoditySpecificationsService.updateSpecificationStock(wnkCommoditySpecifications);
                                    }

                                }
                                else{
                                    result.setData("");
                                    result.setStatus(Result.FAIL);
                                    String msg = (String)resutMap.get("msg");
                                    if (msg == null || msg.equals("")){
                                        result.setMsg("未知错误");
                                    }
                                    else{
                                        result.setMsg((String)resutMap.get("msg"));
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
            result.setMsg("下单失败");
        }
        return result;
    }


    // 万能卡商品订单微信App二次支付
    @RequestMapping(value = "/wnkCommodityWxTwoPayApp", method = RequestMethod.POST,params = {"user_id","order_no"})
    @ResponseBody
    public Result wnkCommodityWxTwoPayApp(HttpServletRequest request, HttpServletResponse response, Integer user_id, String order_no){
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
                    WnkOrders wnkOrders = this.wnkOrdersService.selectByOrderNo(order_no);
                    if (wnkOrders == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("订单不存在");
                    }
                    else if (wnkOrders.getState() != 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("订单已支付");
                    }
                    else{
                        Double amount = wnkOrders.getAmount();
                        Map<String,Object> resutMap = WechatPayLineUtil.wechatPayWnkCommodityApp(wnkOrders.getOrder_no(), amount, request, response);
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
                            String msg = (String)resutMap.get("msg");
                            if (msg == null || msg.equals("")){
                                result.setMsg("未知错误");
                            }
                            else{
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


    // 万能卡商品订单通用积分/消费积分支付(pay_way-2通用积分支付，pay_way-3消费积分支付)
    @RequestMapping(value = "/wnkCommodityIntegralPay", method = RequestMethod.POST,params = {"user_id","commodity_id","guige_id","pay_way","pay_pwd","commodity_number"})
    @ResponseBody
    public Result wnkCommodityIntegralPay(HttpServletRequest request, HttpServletResponse response, Integer user_id, Integer commodity_id,Integer guige_id,Integer pay_way,String pay_pwd,Integer commodity_number){
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
                else if (!pay_pwd.equals(users.getPay_pwd())){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("支付密码不正确");
                }
                else{
                    WnkCommodities wnkCommodities = this.wnkCommoditiesService.selectById(commodity_id);
                    WnkCommoditySpecifications wnkCommoditySpecifications = this.wnkCommoditySpecificationsService.selectById(guige_id);
                    if (wnkCommodities == null || wnkCommodities.getState() != 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("商品不存在或已下架");
                    }
                    else if (wnkCommoditySpecifications == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("商品规格不存在");
                    }
                    else if (wnkCommoditySpecifications.getInventory() != -1 && wnkCommoditySpecifications.getInventory() < commodity_number){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("当前规格库存不足");
                    }
                    else{

                        //<editor-fold desc="Description">
                        Double price = wnkCommoditySpecifications.getPrice() * commodity_number;
                        if (users.getMember_card_level() != -1){
                            WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(wnkCommodities.getBusiness_id());
                            if (wnkStoreInformation != null){
                                WnkBusinessType wnkBusinessType = this.wnkBusinessTypeService.selectById(wnkStoreInformation.getBusiness_type_id());
                                if (wnkBusinessType != null && wnkBusinessType.getCommdity_charge_way() == 0 && wnkBusinessType.getMake_wnk_state() == 1 && wnkCommoditySpecifications.getIs_wnk() == 0){
                                    if (wnkBusinessType.getDiscount_type() == 0){
                                        price = wnkBusinessType.getCommodifty_price() * commodity_number;
                                    }
                                    else{
                                        Double bili = wnkBusinessType.getCommodifty_price() / 100;
                                        price = wnkCommoditySpecifications.getPrice() * commodity_number * bili;
                                        price = Double.valueOf(String.format("%.2f", price));
                                    }
                                }
                                else if (wnkBusinessType != null && wnkBusinessType.getCommdity_charge_way() == 1 && wnkBusinessType.getMake_wnk_state() == 1 && wnkCommoditySpecifications.getIs_wnk() == 0){
                                    WnkBusinessTypeOpenCard wnkBusinessTypeOpenCard = this.wnkBusinessTypeOpenCardService.selectOpenCardStateByUserIdAndBusinessId(user_id,wnkStoreInformation.getBusiness_id());
                                    if (wnkBusinessTypeOpenCard != null){
                                        price = 0.00;
                                    }
                                }
                            }
                        }
                        //</editor-fold>
                        WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(wnkCommodities.getBusiness_id());
                        if (wnkBusinessAccount == null){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("商户不存在");
                        }
                        else if (wnkBusinessAccount.getLevel_integral() < price){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("当前商户积分不足");
                        }
                        else{
                            if (pay_way == 2 && users.getGeneral_integral() < price){
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("通用积分余额不足");
                            }
                            else if (pay_way == 3 && users.getConsumption_integral() < price){
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("消费积分余额不足");
                            }
                            else{
                                WnkOrders wnkOrders = new WnkOrders();
                                wnkOrders.setBusiness_id(wnkCommodities.getBusiness_id());
                                wnkOrders.setUser_id(user_id);
                                wnkOrders.setOrder_no(UUDUtil.getOrderIdByUUId());
                                wnkOrders.setSubmit_time(new Date());
                                wnkOrders.setAmount(price);
                                wnkOrders.setPay_way(pay_way==2?0:1);
                                wnkOrders.setState(0);
                                int state = this.wnkOrdersService.insertNewOrder(wnkOrders);
                                if (state <= 0){
                                    result.setData("");
                                    result.setStatus(Result.FAIL);
                                    result.setMsg("下单失败");
                                }
                                else{
                                    WnkOrderCommodity wnkOrderCommodity = new WnkOrderCommodity();
                                    wnkOrderCommodity.setOrder_id(wnkOrders.getId());
                                    wnkOrderCommodity.setCommodity_id(commodity_id);
                                    wnkOrderCommodity.setBuy_number(commodity_number);
                                    wnkOrderCommodity.setCount_amount(price);
                                    wnkOrderCommodity.setCommodity_guige_id(guige_id);
                                    int commodityAddState = this.wnkOrderCommodityService.insertNewOrderCommodity(wnkOrderCommodity);
                                    if (commodityAddState <= 0){
                                        result.setData("");
                                        result.setStatus(Result.FAIL);
                                        result.setMsg("下单失败");
                                    }
                                    else{
                                        if (pay_way == 2){
                                            users.setGeneral_integral(users.getGeneral_integral() - price);
                                        }
                                        else{
                                            users.setConsumption_integral(users.getConsumption_integral() - price);
                                        }

                                        //<editor-fold desc="更新用户余额 返回>0则是更新成功">
                                        int updateBalance = this.usersService.updateUserTYAndXFAndSilverCoinBalance(users);
                                        //</editor-fold>
                                        if (updateBalance <= 0){
                                            result.setData("");
                                            result.setStatus(Result.FAIL);
                                            result.setMsg("支付失败");
                                        }
                                        else{
                                            Map<Object,Object> map = new HashMap<Object,Object>();
                                            map.put("order_id",wnkOrders.getId());

                                            result.setData(map);
                                            result.setStatus(Result.SUCCESS);
                                            result.setMsg("支付成功");
                                            this.wnkOrdersService.updateOrderStateByOrderNo(1,wnkOrders.getOrder_no());

                                            //<editor-fold desc="生成订单二维码">
                                            Map<Object,Object> orderQrcodeMap = new HashMap<Object,Object>();
                                            orderQrcodeMap.put("user_id",wnkOrders.getUser_id());
                                            orderQrcodeMap.put("type",2);
                                            orderQrcodeMap.put("order_no",wnkOrders.getOrder_no());
                                            JSONObject json = JSONObject.fromObject(orderQrcodeMap);
                                            String wnkQrcodeName = UUDUtil.getOrderIdByUUId();
                                            String wnkQrcodeUrl = QRCode.generateQRCode(json.toString(),wnkQrcodeName);
                                            this.wnkOrdersService.updateOrderQrcodeByOrderNo(wnkQrcodeName+".png",wnkOrders.getOrder_no());
                                            //</editor-fold>

                                            //<editor-fold desc="更新规格库存">
                                            if (wnkCommoditySpecifications.getInventory() != -1){
                                                wnkCommoditySpecifications.setInventory(wnkCommoditySpecifications.getInventory() - commodity_number);
                                                this.wnkCommoditySpecificationsService.updateSpecificationStock(wnkCommoditySpecifications);
                                            }
                                            //</editor-fold>

                                            if (price > (Double) 0.00){
                                                this.integralDetailService.insertIntrgralDetailRecord(user_id,"商品购买",price,0);
                                                Map<String,Object> wnkIntegralMap = this.wnkIntegralUserServer.getUserIntegral(wnkCommodities.getBusiness_id(),users.getId());
                                                Integer addWnkIntegralUserState = 1;
                                                if (wnkIntegralMap == null){
                                                    addWnkIntegralUserState = this.wnkIntegralUserServer.addUserIntegral(wnkCommodities.getBusiness_id(),users.getId());
                                                }
//                                                if (addWnkIntegralUserState > 0 && users.getMember_card_level() != -1){
                                                if (addWnkIntegralUserState > 0){
                                                    Map<String,Object> recommendIntegralMap = this.wnkIntegralUserServer.getUserIntegral(wnkCommodities.getBusiness_id(),user_id);
                                                    if (recommendIntegralMap == null){
                                                        this.wnkIntegralUserServer.addUserIntegral(wnkCommodities.getBusiness_id(),user_id);
                                                    }
                                                    // 增加用户在此商户的积分余额
                                                    this.wnkIntegralUserServer.increaseUserIntegral(user_id,wnkCommodities.getBusiness_id(),price);
                                                    // 插入积分记录 0=收入
                                                    this.wnkIntegralIncomeService.addIntegralRecord("商品购买",price,user_id,wnkCommodities.getBusiness_id(),0);

                                                    WnkBusinessLevelIntegralDetail wnkBusinessLevelIntegralDetail = new WnkBusinessLevelIntegralDetail();
                                                    wnkBusinessLevelIntegralDetail.setBusiness_id(wnkCommodities.getBusiness_id());
                                                    wnkBusinessLevelIntegralDetail.setName("客户消费");
                                                    wnkBusinessLevelIntegralDetail.setIntegral_number(price);
                                                    wnkBusinessLevelIntegralDetail.setTransactions_date(new Date());
                                                    wnkBusinessLevelIntegralDetail.setTransactions_type(2);
                                                    wnkBusinessLevelIntegralDetail.setUser_id(users.getId());
                                                    wnkBusinessLevelIntegralDetail.setPay_type(pay_way);
                                                    // 扣除商家等级积分
                                                    wnkBusinessAccount.setLevel_integral(wnkBusinessAccount.getLevel_integral() - price);
                                                    this.wnkBusinessAccountService.updateRelevantBalance(wnkBusinessAccount);
                                                    this.wnkBusinessLevelIntegralDetailService.insertNewRecordUserXF(wnkBusinessLevelIntegralDetail);
                                                }

                                                //<editor-fold desc="更新用户再此商家的兑换积分余额">
                                                if (users.getRecommend_user_id() != -1 && price > 0){
                                                    if (users.getRecommend_type() == 0){
                                                        Users recommendUser = this.usersService.selectById(users.getRecommend_user_id());
                                                        if (recommendUser.getMember_card_level() != -1){
                                                            Map<String,Object> recommendIntegralMap = this.wnkIntegralUserServer.getUserIntegral(wnkCommodities.getBusiness_id(),recommendUser.getId());
                                                            if (recommendIntegralMap == null){
                                                                this.wnkIntegralUserServer.addUserIntegral(wnkCommodities.getBusiness_id(),recommendUser.getId());
                                                            }
                                                            this.integralDetailService.insertIntrgralDetailRecord(users.getRecommend_user_id(),"朋友购买商品",price,0);
                                                            // 增加用户下级在此商户的积分余额
                                                            this.wnkIntegralUserServer.increaseUserIntegral(users.getRecommend_user_id(),wnkCommodities.getBusiness_id(),price);
                                                            this.wnkIntegralIncomeService.addIntegralRecord("朋友购买商品",price,users.getRecommend_user_id(),wnkCommodities.getBusiness_id(),0);
                                                        }
                                                    }
                                                    else{
                                                        WnkBusinessAccount wnkBusinessAccount2 = this.wnkBusinessAccountService.selectById(users.getRecommend_user_id());
                                                        if (wnkBusinessAccount2 != null){
                                                            WnkBusinessConsumptionIntegralDetail wnkBusinessConsumptionIntegralDetail = new WnkBusinessConsumptionIntegralDetail();
                                                            wnkBusinessConsumptionIntegralDetail.setBusiness_id(users.getRecommend_user_id());
                                                            wnkBusinessConsumptionIntegralDetail.setName("用户购买商品");
                                                            wnkBusinessConsumptionIntegralDetail.setIntegral_number(price);
                                                            wnkBusinessConsumptionIntegralDetail.setTransactions_date(new Date());
                                                            wnkBusinessConsumptionIntegralDetail.setTransactions_type(0);
                                                            wnkBusinessAccount2.setConsumption_integral(wnkBusinessAccount2.getConsumption_integral() + price.intValue());
                                                            int businessIntegralUpdateState = this.wnkBusinessAccountService.updateRelevantBalance(wnkBusinessAccount2);
                                                            if (businessIntegralUpdateState > 0){
                                                                this.wnkBusinessConsumptionIntegralDetailService.insertNewRecord(wnkBusinessConsumptionIntegralDetail);
                                                            }
                                                        }
                                                    }
                                                }
                                                //</editor-fold>

                                                //<editor-fold desc="添加积分增加记录">
                                                if (pay_way == 2){
                                                    GeneralIntegralExpenditure generalIntegralExpenditure = new GeneralIntegralExpenditure();
                                                    generalIntegralExpenditure.setName("商品购买");
                                                    generalIntegralExpenditure.setExpenditure_date(new Date());
                                                    generalIntegralExpenditure.setExpenditure_amount(price);
                                                    generalIntegralExpenditure.setExpenditure_after_balance(users.getGeneral_integral());
                                                    generalIntegralExpenditure.setUser_id(user_id);
                                                    generalIntegralExpenditure.setExpenditure_type(4);
                                                    this.generalIntegralExpenditureService.insertExchangeSilverCoinRecord(generalIntegralExpenditure);
                                                }
                                                else{
                                                    ConsumptionIntegralExpenditure consumptionIntegralExpenditure = new ConsumptionIntegralExpenditure();
                                                    consumptionIntegralExpenditure.setName("商品购买");
                                                    consumptionIntegralExpenditure.setExpenditure_date(new Date());
                                                    consumptionIntegralExpenditure.setExpenditure_amount(price);
                                                    consumptionIntegralExpenditure.setExpenditure_after_balance(users.getConsumption_integral());
                                                    consumptionIntegralExpenditure.setUser_id(user_id);
                                                    consumptionIntegralExpenditure.setExpenditure_type(3);
                                                    this.consumptionIntegralExpenditureService.insertExchangeSilverCoinRecord(consumptionIntegralExpenditure);
                                                }
                                                //</editor-fold>
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
            result.setMsg("下单失败");
        }
        return result;
    }


    // 万能卡商品订单赠送积分支付
    @RequestMapping(value = "/wnkCommoditySendIntegralPay", method = RequestMethod.POST,params = {"user_id","commodity_id","guige_id","pay_pwd","commodity_number"})
    @ResponseBody
    public Result wnkCommoditySendIntegralPay(HttpServletRequest request, HttpServletResponse response, Integer user_id, Integer commodity_id,Integer guige_id,String pay_pwd,Integer commodity_number){
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
                else if (!pay_pwd.equals(users.getPay_pwd())){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("支付密码不正确");
                }
                else{
                    WnkCommodities wnkCommodities = this.wnkCommoditiesService.selectById(commodity_id);
                    WnkCommoditySpecifications wnkCommoditySpecifications = this.wnkCommoditySpecificationsService.selectById(guige_id);
                    if (wnkCommodities == null || wnkCommodities.getState() != 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("商品不存在或已下架");
                    }
                    else if (wnkCommoditySpecifications == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("商品规格不存在");
                    }
                    else if(wnkCommoditySpecifications.getInventory() != -1 && wnkCommoditySpecifications.getInventory() < commodity_number){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("当前库存规格不足");
                    }
                    else{
                        Double price = wnkCommoditySpecifications.getPrice() * commodity_number;
                        if (users.getMember_card_level() != -1){
                            WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(wnkCommodities.getBusiness_id());
                            if (wnkStoreInformation != null){
                                WnkBusinessType wnkBusinessType = this.wnkBusinessTypeService.selectById(wnkStoreInformation.getBusiness_type_id());
                                if (wnkBusinessType != null && wnkBusinessType.getCommdity_charge_way() == 0 && wnkBusinessType.getMake_wnk_state() == 1 && wnkCommoditySpecifications.getIs_wnk() == 0){
                                    if (wnkBusinessType.getDiscount_type() == 0){
                                        price = wnkBusinessType.getCommodifty_price() * commodity_number;
                                    }
                                    else{
                                        Double bili = wnkBusinessType.getCommodifty_price() / 100;
                                        price = wnkCommoditySpecifications.getPrice() * commodity_number * bili;
                                        price = Double.valueOf(String.format("%.2f", price));
                                    }

                                }
                                else if (wnkBusinessType != null && wnkBusinessType.getCommdity_charge_way() == 1 && wnkBusinessType.getMake_wnk_state() == 1 && wnkCommoditySpecifications.getIs_wnk() == 0){
                                    WnkBusinessTypeOpenCard wnkBusinessTypeOpenCard = this.wnkBusinessTypeOpenCardService.selectOpenCardStateByUserIdAndBusinessId(user_id,wnkStoreInformation.getBusiness_id());
                                    if (wnkBusinessTypeOpenCard != null){
                                        price = 0.00;
                                    }
                                }
                            }
                        }
                        // 业务开始
                        Map<String, Object> userSendIntegralmap = wnkSendIntegralUserService.getUserIntegral(wnkCommodities.getBusiness_id(), user_id);
                        if (userSendIntegralmap == null || (Double)userSendIntegralmap.get("integral") < price){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("赠送积分余额不足");
                        }
                        else{
                            WnkOrders wnkOrders = new WnkOrders();
                            wnkOrders.setBusiness_id(wnkCommodities.getBusiness_id());
                            wnkOrders.setUser_id(user_id);
                            wnkOrders.setOrder_no(UUDUtil.getOrderIdByUUId());
                            wnkOrders.setSubmit_time(new Date());
                            wnkOrders.setAmount(price);
                            wnkOrders.setPay_way(4);
                            wnkOrders.setState(0);
                            int state = this.wnkOrdersService.insertNewOrder(wnkOrders);
                            if (state <= 0){
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("下单失败");
                            }
                            else{
                                WnkOrderCommodity wnkOrderCommodity = new WnkOrderCommodity();
                                wnkOrderCommodity.setOrder_id(wnkOrders.getId());
                                wnkOrderCommodity.setCommodity_id(commodity_id);
                                wnkOrderCommodity.setBuy_number(commodity_number);
                                wnkOrderCommodity.setCount_amount(price);
                                wnkOrderCommodity.setCommodity_guige_id(guige_id);
                                int commodityAddState = this.wnkOrderCommodityService.insertNewOrderCommodity(wnkOrderCommodity);
                                if (commodityAddState <= 0){
                                    result.setData("");
                                    result.setStatus(Result.FAIL);
                                    result.setMsg("下单失败");
                                }
                                else{
                                    Integer updateBalance = this.wnkSendIntegralUserService.updateIntegral(user_id,wnkCommodities.getBusiness_id(),price);
                                    if (updateBalance <= 0){
                                        result.setData("");
                                        result.setStatus(Result.FAIL);
                                        result.setMsg("支付失败");
                                    }
                                    else{
                                        Map<Object,Object> map = new HashMap<Object,Object>();
                                        map.put("order_id",wnkOrders.getId());

                                        result.setData(map);
                                        result.setStatus(Result.SUCCESS);
                                        result.setMsg("支付成功");
                                        this.wnkOrdersService.updateOrderStateByOrderNo(1,wnkOrders.getOrder_no());
                                        //订单二维码
                                        Map<Object,Object> orderQrcodeMap = new HashMap<Object,Object>();
                                        orderQrcodeMap.put("user_id",wnkOrders.getUser_id());
                                        orderQrcodeMap.put("type",2);
                                        orderQrcodeMap.put("order_no",wnkOrders.getOrder_no());
                                        JSONObject json = JSONObject.fromObject(orderQrcodeMap);
                                        String wnkQrcodeName = UUDUtil.getOrderIdByUUId();
                                        String wnkQrcodeUrl = QRCode.generateQRCode(json.toString(),wnkQrcodeName);
                                        this.wnkOrdersService.updateOrderQrcodeByOrderNo(wnkQrcodeName+".png",wnkOrders.getOrder_no());

                                        //更新规格库存
                                        if (wnkCommoditySpecifications.getInventory() != -1){
                                            wnkCommoditySpecifications.setInventory(wnkCommoditySpecifications.getInventory() - commodity_number);
                                            this.wnkCommoditySpecificationsService.updateSpecificationStock(wnkCommoditySpecifications);
                                        }

                                        if (price > (Double)0.00){
                                            // 插入积分记录 1=支出
                                            this.wnkSendIntegralDetailUserService.addIntegralRecord("商品购买",price,users.getId(),wnkCommodities.getBusiness_id(),1);
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
            result.setMsg("下单失败");
        }
        return result;
    }


    // 万能卡订单支付不调用微信系统
    @RequestMapping(value = "/wnkOrderWXPayNoMakeWXSystem", method = RequestMethod.POST,params = {"user_id","commodity_id","guige_id","commodity_number"})
    @ResponseBody
    public Result wnkOrderWXPayNoMakeWXSystem(HttpServletRequest request, HttpServletResponse response, Integer user_id, Integer commodity_id,Integer guige_id,Integer commodity_number){
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
                    WnkCommodities wnkCommodities = this.wnkCommoditiesService.selectById(commodity_id);
                    WnkCommoditySpecifications wnkCommoditySpecifications = this.wnkCommoditySpecificationsService.selectById(guige_id);
                    if (wnkCommodities == null || wnkCommodities.getState() != 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("商品不存在或已下架");
                    }
                    else if (wnkCommoditySpecifications == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("商品规格不存在");
                    }
                    else if (wnkCommoditySpecifications.getInventory() != -1 && wnkCommoditySpecifications.getInventory() < commodity_number){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("当前规格库存不足");
                    }
                    else{
                        Double price = wnkCommoditySpecifications.getPrice() * commodity_number;
                        if (users.getMember_card_level() != -1){
                            WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(wnkCommodities.getBusiness_id());
                            if (wnkStoreInformation != null){
                                WnkBusinessType wnkBusinessType = this.wnkBusinessTypeService.selectById(wnkStoreInformation.getBusiness_type_id());
                                if (wnkBusinessType != null && wnkBusinessType.getCommdity_charge_way() == 0 && wnkBusinessType.getMake_wnk_state() == 1 && wnkCommoditySpecifications.getIs_wnk() == 0){
                                    if (wnkBusinessType.getDiscount_type() == 0){
                                        price = wnkBusinessType.getCommodifty_price() * commodity_number;
                                    }
                                    else{
                                        Double bili = wnkBusinessType.getCommodifty_price() / 100;
                                        price = wnkCommoditySpecifications.getPrice() * commodity_number * bili;
                                        price = Double.valueOf(String.format("%.2f", price));
                                    }

                                }
                                else if (wnkBusinessType != null && wnkBusinessType.getCommdity_charge_way() == 1 && wnkBusinessType.getMake_wnk_state() == 1 && wnkCommoditySpecifications.getIs_wnk() == 0){
                                    WnkBusinessTypeOpenCard wnkBusinessTypeOpenCard = this.wnkBusinessTypeOpenCardService.selectOpenCardStateByUserIdAndBusinessId(user_id,wnkStoreInformation.getBusiness_id());
                                    if (wnkBusinessTypeOpenCard != null){
                                        price = 0.00;
                                    }
                                }
                            }
                        }
                        if (price > 0.00){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("此商品不止该支付方式");
                        }
                        else{
                            WnkOrders wnkOrders = new WnkOrders();
                            wnkOrders.setBusiness_id(wnkCommodities.getBusiness_id());
                            wnkOrders.setUser_id(user_id);
                            wnkOrders.setOrder_no(UUDUtil.getOrderIdByUUId());
                            wnkOrders.setSubmit_time(new Date());
                            wnkOrders.setAmount(price);
                            wnkOrders.setPay_way(3);
                            wnkOrders.setState(0);
                            int state = this.wnkOrdersService.insertNewOrder(wnkOrders);
                            if (state <= 0){
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("下单失败");
                            }
                            else{
                                WnkOrderCommodity wnkOrderCommodity = new WnkOrderCommodity();
                                wnkOrderCommodity.setOrder_id(wnkOrders.getId());
                                wnkOrderCommodity.setCommodity_id(commodity_id);
                                wnkOrderCommodity.setBuy_number(commodity_number);
                                wnkOrderCommodity.setCount_amount(price);
                                wnkOrderCommodity.setCommodity_guige_id(guige_id);
                                int commodityAddState = this.wnkOrderCommodityService.insertNewOrderCommodity(wnkOrderCommodity);
                                if (commodityAddState <= 0){
                                    result.setData("");
                                    result.setStatus(Result.FAIL);
                                    result.setMsg("下单失败");
                                }
                                else{
                                    Map<Object,Object> map = new HashMap<Object,Object>();
                                    map.put("order_id",wnkOrders.getId());

                                    result.setData(map);
                                    result.setStatus(Result.SUCCESS);
                                    result.setMsg("购买成功");
                                    this.wnkOrdersService.updateOrderStateByOrderNo(1,wnkOrders.getOrder_no());
                                    //订单二维码
                                    Map<Object,Object> orderQrcodeMap = new HashMap<Object,Object>();
                                    orderQrcodeMap.put("user_id",wnkOrders.getUser_id());
                                    orderQrcodeMap.put("type",2);
                                    orderQrcodeMap.put("order_no",wnkOrders.getOrder_no());
                                    JSONObject json = JSONObject.fromObject(orderQrcodeMap);
                                    String wnkQrcodeName = UUDUtil.getOrderIdByUUId();
                                    String wnkQrcodeUrl = QRCode.generateQRCode(json.toString(),wnkQrcodeName);
                                    this.wnkOrdersService.updateOrderQrcodeByOrderNo(wnkQrcodeName+".png",wnkOrders.getOrder_no());

                                    //更新规格库存
                                    if (wnkCommoditySpecifications.getInventory() != -1){
                                        wnkCommoditySpecifications.setInventory(wnkCommoditySpecifications.getInventory() - commodity_number);
                                        this.wnkCommoditySpecificationsService.updateSpecificationStock(wnkCommoditySpecifications);
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
            result.setMsg("下单失败");
        }
        return result;
    }


    // 万能卡商品订单优惠券支付
    @RequestMapping(value = "/wnkCommodityCouponsPay", method = RequestMethod.POST,params = {"user_id","commodity_id","guige_id","pay_pwd","commodity_number"})
    @ResponseBody
    public Result wnkCommodityCouponsPay(HttpServletRequest request, HttpServletResponse response, Integer user_id, Integer commodity_id,Integer guige_id,String pay_pwd,Integer commodity_number){
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
                else if (!pay_pwd.equals(users.getPay_pwd())){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("支付密码不正确");
                }
                else{
                    WnkCommodities wnkCommodities = this.wnkCommoditiesService.selectById(commodity_id);
                    WnkCommoditySpecifications wnkCommoditySpecifications = this.wnkCommoditySpecificationsService.selectById(guige_id);
                    if (wnkCommodities == null || wnkCommodities.getState() != 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("商品不存在或已下架");
                    }
                    else if (wnkCommoditySpecifications == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("商品规格不存在");
                    }
                    else if (wnkCommoditySpecifications.getInventory() != -1 && wnkCommoditySpecifications.getInventory() < commodity_number){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("当前规格库存不足");
                    }
                    else{
                        WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(wnkCommodities.getBusiness_id());
                        if (wnkStoreInformation != null){
                            WnkBusinessType wnkBusinessType = this.wnkBusinessTypeService.selectById(wnkStoreInformation.getBusiness_type_id());
                            if (wnkBusinessType == null){
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("商户分类不存在");
                            }
                            else{
                                List<Map<Object,Object>> materielList = this.extensionMaterielService.selectMatensionByTypeId(wnkBusinessType.getId());
                                if (materielList.size() <= 0){
                                    result.setData("");
                                    result.setStatus(Result.FAIL);
                                    result.setMsg("此类型暂不支持优惠券");
                                }
                                else{
                                    Integer materielId = (Integer)materielList.get(0).get("id");
                                    UserCoupons userCoupons = this.userCouponsService.selectByBusinessTypeIdAndUserId(materielId,user_id);
                                    if (materielList.size() >= 2){
                                        Integer materielId2 = (Integer)materielList.get(1).get("id");
                                        UserCoupons userCoupons2 = this.userCouponsService.selectByBusinessTypeIdAndUserId(materielId2,user_id);
                                        if (userCoupons2 != null && userCoupons2.getSurplus_number() > userCoupons.getSurplus_number()){
                                            userCoupons = userCoupons2;
                                        }
                                    }
                                    if (userCoupons == null || userCoupons.getSurplus_number() < commodity_number){
                                        result.setData("");
                                        result.setStatus(Result.FAIL);
                                        result.setMsg("优惠券余额不足");
                                    }
                                    else{
                                        WnkOrders wnkOrders = new WnkOrders();
                                        wnkOrders.setBusiness_id(wnkCommodities.getBusiness_id());
                                        wnkOrders.setUser_id(user_id);
                                        wnkOrders.setOrder_no(UUDUtil.getOrderIdByUUId());
                                        wnkOrders.setSubmit_time(new Date());
                                        wnkOrders.setAmount(0.00);
                                        wnkOrders.setPay_way(5);
                                        wnkOrders.setState(0);
                                        int state = this.wnkOrdersService.insertNewOrder(wnkOrders);
                                        if (state <= 0){
                                            result.setData("");
                                            result.setStatus(Result.FAIL);
                                            result.setMsg("下单失败");
                                        }
                                        else{
                                            WnkOrderCommodity wnkOrderCommodity = new WnkOrderCommodity();
                                            wnkOrderCommodity.setOrder_id(wnkOrders.getId());
                                            wnkOrderCommodity.setCommodity_id(commodity_id);
                                            wnkOrderCommodity.setBuy_number(commodity_number);
                                            wnkOrderCommodity.setCount_amount(0.00);
                                            wnkOrderCommodity.setCommodity_guige_id(guige_id);
                                            int commodityAddState = this.wnkOrderCommodityService.insertNewOrderCommodity(wnkOrderCommodity);
                                            if (commodityAddState <= 0){
                                                result.setData("");
                                                result.setStatus(Result.FAIL);
                                                result.setMsg("下单失败");
                                            }
                                            else{
                                                userCoupons.setSurplus_number(userCoupons.getSurplus_number() - commodity_number);
                                                Integer updateBalance = this.userCouponsService.updateSurplusNumber(userCoupons);
                                                if (updateBalance <= 0){
                                                    result.setData("");
                                                    result.setStatus(Result.FAIL);
                                                    result.setMsg("支付失败");
                                                }
                                                else{
                                                    Map<Object,Object> map = new HashMap<Object,Object>();
                                                    map.put("order_id",wnkOrders.getId());

                                                    result.setData(map);
                                                    result.setStatus(Result.SUCCESS);
                                                    result.setMsg("支付成功");
                                                    this.wnkOrdersService.updateOrderStateByOrderNo(1,wnkOrders.getOrder_no());
                                                    //订单二维码
                                                    Map<Object,Object> orderQrcodeMap = new HashMap<Object,Object>();
                                                    orderQrcodeMap.put("user_id",wnkOrders.getUser_id());
                                                    orderQrcodeMap.put("type",2);
                                                    orderQrcodeMap.put("order_no",wnkOrders.getOrder_no());
                                                    JSONObject json = JSONObject.fromObject(orderQrcodeMap);
                                                    String wnkQrcodeName = UUDUtil.getOrderIdByUUId();
                                                    String wnkQrcodeUrl = QRCode.generateQRCode(json.toString(),wnkQrcodeName);
                                                    this.wnkOrdersService.updateOrderQrcodeByOrderNo(wnkQrcodeName+".png",wnkOrders.getOrder_no());

                                                    //更新规格库存
                                                    if (wnkCommoditySpecifications.getInventory() != -1){
                                                        wnkCommoditySpecifications.setInventory(wnkCommoditySpecifications.getInventory() - commodity_number);
                                                        this.wnkCommoditySpecificationsService.updateSpecificationStock(wnkCommoditySpecifications);
                                                    }

                                                }
                                            }
                                        }
                                    }
                                }

                            }
                        }
                        else{
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("商户不存在");
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


    // 万能卡商品订单通用积分/消费积分二次支付(pay_way-2通用积分支付，pay_way-3消费积分支付)
    @RequestMapping(value = "/wnkCommodityIntegralTwoPay", method = RequestMethod.POST,params = {"user_id","order_no","pay_pwd","pay_way"})
    @ResponseBody
    public Result wnkCommodityIntegralTwoPay(HttpServletRequest request, HttpServletResponse response, Integer user_id, String order_no,String pay_pwd,Integer pay_way){
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
                else if (!pay_pwd.equals(users.getPay_pwd())){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("支付密码不正确");
                }
                else{
                    WnkOrders wnkOrders = this.wnkOrdersService.selectByOrderNo(order_no);
                    if (wnkOrders == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("订单不存在");
                    }
                    else if (wnkOrders.getState() != 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("订单已支付");
                    }
                    else{
                        Double price = wnkOrders.getAmount();
                        if (pay_way == 2 && users.getGeneral_integral() < price){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("通用积分余额不足");
                        }
                        else if (pay_way == 3 && users.getConsumption_integral() < price){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("消费积分余额不足");
                        }
                        else{
                            if (pay_way == 2){
                                users.setGeneral_integral(users.getGeneral_integral() - price);
                            }
                            else{
                                users.setConsumption_integral(users.getConsumption_integral() - price);
                            }
                            int updateBalance = this.usersService.updateUserTYAndXFAndSilverCoinBalance(users);
                            if (updateBalance <= 0){
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("支付失败");
                            }
                            else{
                                Map<Object,Object> map = new HashMap<Object,Object>();
                                map.put("order_id",wnkOrders.getId());

                                result.setData(map);
                                result.setStatus(Result.SUCCESS);
                                result.setMsg("支付成功");
                                this.wnkOrdersService.updateOrderStateByOrderNo(1,wnkOrders.getOrder_no());
                                //订单二维码
                                Map<Object,Object> orderQrcodeMap = new HashMap<Object,Object>();
                                orderQrcodeMap.put("user_id",wnkOrders.getUser_id());
                                orderQrcodeMap.put("type",2);
                                orderQrcodeMap.put("order_no",wnkOrders.getOrder_no());
                                JSONObject json = JSONObject.fromObject(orderQrcodeMap);
                                String wnkQrcodeName = UUDUtil.getOrderIdByUUId();
                                String wnkQrcodeUrl = QRCode.generateQRCode(json.toString(),wnkQrcodeName);
                                this.wnkOrdersService.updateOrderQrcodeByOrderNo(wnkQrcodeName+".png",wnkOrders.getOrder_no());
                                this.wnkOrdersService.updateOrderStateByOrderNo(1,order_no);
                                this.integralDetailService.insertIntrgralDetailRecord(user_id,"商品购买",price,0);
                                Map<String,Object> integralMap = this.wnkIntegralUserServer.getUserIntegral(wnkOrders.getBusiness_id(),user_id);
                                if (integralMap == null){
                                    this.wnkIntegralUserServer.addUserIntegral(wnkOrders.getBusiness_id(),user_id);
                                }
                                // 增加用户在此商户的积分余额
                                this.wnkIntegralUserServer.increaseUserIntegral(user_id,wnkOrders.getBusiness_id(),price);
                                // 插入积分记录 0=收入
                                this.wnkIntegralIncomeService.addIntegralRecord("商品购买",price,user_id,wnkOrders.getBusiness_id(),0);

                                if (users.getRecommend_user_id() != -1){
                                    if (users.getRecommend_type() == 0){
                                        Users recommendUser = this.usersService.selectById(users.getRecommend_user_id());
                                        if (recommendUser.getMember_card_level() != -1){
                                            Map<String,Object> recommendIntegralMap = this.wnkIntegralUserServer.getUserIntegral(wnkOrders.getBusiness_id(),recommendUser.getId());
                                            if (recommendIntegralMap == null){
                                                this.wnkIntegralUserServer.addUserIntegral(wnkOrders.getBusiness_id(),recommendUser.getId());
                                            }
                                            this.integralDetailService.insertIntrgralDetailRecord(users.getRecommend_user_id(),"朋友购买商品",price,0);
                                            // 增加用户下级在此商户的积分余额
                                            this.wnkIntegralUserServer.increaseUserIntegral(users.getRecommend_user_id(),wnkOrders.getBusiness_id(),price);
                                            this.wnkIntegralIncomeService.addIntegralRecord("朋友购买商品",price,users.getRecommend_user_id(),wnkOrders.getBusiness_id(),0);
                                        }

                                    }
                                    else{
                                        WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(users.getRecommend_user_id());
                                        if (wnkBusinessAccount != null){
                                            WnkBusinessConsumptionIntegralDetail wnkBusinessConsumptionIntegralDetail = new WnkBusinessConsumptionIntegralDetail();
                                            wnkBusinessConsumptionIntegralDetail.setBusiness_id(users.getRecommend_user_id());
                                            wnkBusinessConsumptionIntegralDetail.setName("用户购买商品");
                                            wnkBusinessConsumptionIntegralDetail.setIntegral_number(price);
                                            wnkBusinessConsumptionIntegralDetail.setTransactions_date(new Date());
                                            wnkBusinessConsumptionIntegralDetail.setTransactions_type(0);
                                            wnkBusinessAccount.setConsumption_integral(wnkBusinessAccount.getConsumption_integral() + price.intValue());
                                            int businessIntegralUpdateState = this.wnkBusinessAccountService.updateRelevantBalance(wnkBusinessAccount);
                                            if (businessIntegralUpdateState > 0){
                                                this.wnkBusinessConsumptionIntegralDetailService.insertNewRecord(wnkBusinessConsumptionIntegralDetail);
                                            }
                                        }
                                    }


                                }
                                if (pay_way == 2){
                                    GeneralIntegralExpenditure generalIntegralExpenditure = new GeneralIntegralExpenditure();
                                    generalIntegralExpenditure.setName("商品购买");
                                    generalIntegralExpenditure.setExpenditure_date(new Date());
                                    generalIntegralExpenditure.setExpenditure_amount(price.doubleValue());
                                    generalIntegralExpenditure.setExpenditure_after_balance(users.getGeneral_integral());
                                    generalIntegralExpenditure.setUser_id(user_id);
                                    generalIntegralExpenditure.setExpenditure_type(4);
                                    this.generalIntegralExpenditureService.insertExchangeSilverCoinRecord(generalIntegralExpenditure);
                                }
                                else{
                                    ConsumptionIntegralExpenditure consumptionIntegralExpenditure = new ConsumptionIntegralExpenditure();
                                    consumptionIntegralExpenditure.setName("商品购买");
                                    consumptionIntegralExpenditure.setExpenditure_date(new Date());
                                    consumptionIntegralExpenditure.setExpenditure_amount(price.doubleValue());
                                    consumptionIntegralExpenditure.setExpenditure_after_balance(users.getConsumption_integral());
                                    consumptionIntegralExpenditure.setUser_id(user_id);
                                    consumptionIntegralExpenditure.setExpenditure_type(3);
                                    this.consumptionIntegralExpenditureService.insertExchangeSilverCoinRecord(consumptionIntegralExpenditure);
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


    // 万能卡商品订单赠送积分二次支付
    @RequestMapping(value = "/wnkCommoditySendIntegralTwoPay", method = RequestMethod.POST,params = {"user_id","order_no","pay_pwd"})
    @ResponseBody
    public Result wnkCommoditySendIntegralTwoPay(HttpServletRequest request, HttpServletResponse response, Integer user_id, String order_no,String pay_pwd){
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
                else if (!pay_pwd.equals(users.getPay_pwd())){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("支付密码不正确");
                }
                else{
                    WnkOrders wnkOrders = this.wnkOrdersService.selectByOrderNo(order_no);
                    if (wnkOrders == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("订单不存在");
                    }
                    else if (wnkOrders.getState() != 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("订单已支付");
                    }
                    else{
                        Double price = wnkOrders.getAmount();
                        // 业务开始
                        Map<String, Object> userSendIntegralmap = wnkSendIntegralUserService.getUserIntegral(wnkOrders.getBusiness_id(), user_id);
                        if (userSendIntegralmap == null || (Double)userSendIntegralmap.get("integral") < price){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("赠送积分余额不足");
                        }
                        else{
                            Integer updateBalance = this.wnkSendIntegralUserService.updateIntegral(user_id,wnkOrders.getBusiness_id(),wnkOrders.getAmount());
                            if (updateBalance <= 0){
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("支付失败");
                            }
                            else{
                                Map<Object,Object> map = new HashMap<Object,Object>();
                                map.put("order_id",wnkOrders.getId());

                                result.setData(map);
                                result.setStatus(Result.SUCCESS);
                                result.setMsg("支付成功");
                                this.wnkOrdersService.updateOrderStateByOrderNo(1,wnkOrders.getOrder_no());
                                //订单二维码
                                Map<Object,Object> orderQrcodeMap = new HashMap<Object,Object>();
                                orderQrcodeMap.put("user_id",wnkOrders.getUser_id());
                                orderQrcodeMap.put("type",2);
                                orderQrcodeMap.put("order_no",wnkOrders.getOrder_no());
                                JSONObject json = JSONObject.fromObject(orderQrcodeMap);
                                String wnkQrcodeName = UUDUtil.getOrderIdByUUId();
                                String wnkQrcodeUrl = QRCode.generateQRCode(json.toString(),wnkQrcodeName);
                                this.wnkOrdersService.updateOrderQrcodeByOrderNo(wnkQrcodeName+".png",wnkOrders.getOrder_no());
                                this.wnkOrdersService.updateOrderStateByOrderNo(1,order_no);
                                // 插入积分记录 1=支出
                                this.wnkSendIntegralDetailUserService.addIntegralRecord("商品购买",wnkOrders.getAmount(),users.getId(),wnkOrders.getBusiness_id(),1);
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

    // 用户扫码向商家支付-积分支付(pay_type=1通用积分支付，pay_type=0消费积分支付)
    @RequestMapping(value = "/userScanCodeToWnkBusinessIntegralPay", method = RequestMethod.POST,params = {"user_id","business_id","pay_type","pay_pwd","pay_amount"})
    @ResponseBody
    public Result userScanCodeToWnkBusinessIntegralPay(HttpServletRequest request, HttpServletResponse response, Integer user_id, Integer business_id,Integer pay_type,String pay_pwd,Double pay_amount){
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
                else if (!pay_pwd.equals(users.getPay_pwd())){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("支付密码不正确");
                }
                else{
                    //用户应获得的兑换积分额度及平台积分额度
                    Integer getIntegralNumber = pay_amount.intValue();
                    WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                    if (wnkBusinessAccount == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此商家不存在");
                    }
                    else if (wnkBusinessAccount.getLevel_integral() < getIntegralNumber){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("当前商户积分不足");
                    }
                    else if (pay_type == 0 && users.getConsumption_integral() < pay_amount){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("消费积分余额不足");
                    }
                    else if (pay_type == 1 && users.getGeneral_integral() < pay_amount){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("通用积分余额不足");
                    }
                    else{
                        Integer payState = 0;
                        if (pay_type == 0){
                            users.setConsumption_integral(users.getConsumption_integral() - pay_amount);
                        }
                        else{
                            users.setGeneral_integral(users.getGeneral_integral() - pay_amount);
                        }
                        payState = this.usersService.updateUserTYAndXFAndSilverCoinBalance(users);
                        if (pay_type > 0){
                            if (pay_type == 1){
                                GeneralIntegralExpenditure generalIntegralExpenditure = new GeneralIntegralExpenditure();
                                generalIntegralExpenditure.setName("扫码支付");
                                generalIntegralExpenditure.setExpenditure_date(new Date());
                                generalIntegralExpenditure.setExpenditure_amount(pay_amount);
                                generalIntegralExpenditure.setExpenditure_after_balance(users.getGeneral_integral());
                                generalIntegralExpenditure.setUser_id(user_id);
                                generalIntegralExpenditure.setExpenditure_type(4);
                                this.generalIntegralExpenditureService.insertExchangeSilverCoinRecord(generalIntegralExpenditure);
                            }
                            else{
                                ConsumptionIntegralExpenditure consumptionIntegralExpenditure = new ConsumptionIntegralExpenditure();
                                consumptionIntegralExpenditure.setName("扫码支付");
                                consumptionIntegralExpenditure.setExpenditure_date(new Date());
                                consumptionIntegralExpenditure.setExpenditure_amount(pay_amount);
                                consumptionIntegralExpenditure.setExpenditure_after_balance(users.getConsumption_integral());
                                consumptionIntegralExpenditure.setUser_id(user_id);
                                consumptionIntegralExpenditure.setExpenditure_type(3);
                                this.consumptionIntegralExpenditureService.insertExchangeSilverCoinRecord(consumptionIntegralExpenditure);
                            }
                            wnkBusinessAccount.setBalance(wnkBusinessAccount.getBalance() + pay_amount);
                            int businessBalanceUpdateState = this.wnkBusinessAccountService.updateRelevantBalance(wnkBusinessAccount);
                            if (businessBalanceUpdateState <= 0){
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("支付失败");
                            }
                            else{
                                result.setData("");
                                result.setStatus(Result.SUCCESS);
                                result.setMsg("支付成功");

                                WnkBusinessBalanceDetail wnkBusinessBalanceDetail = new WnkBusinessBalanceDetail();
                                wnkBusinessBalanceDetail.setBusiness_id(business_id);
                                wnkBusinessBalanceDetail.setName("用户扫码付款");
                                wnkBusinessBalanceDetail.setTransaction_amount(pay_amount);
                                wnkBusinessBalanceDetail.setJoin_time(new Date());
                                wnkBusinessBalanceDetail.setAfter_balance(wnkBusinessAccount.getBalance());
                                wnkBusinessBalanceDetail.setType(0);
                                wnkBusinessBalanceDetail.setState(0);
                                this.wnkBusinessBalanceDetailService.insertNewRecord(wnkBusinessBalanceDetail);

                                WnkBusinessLevelIntegralDetail wnkBusinessLevelIntegralDetail = new WnkBusinessLevelIntegralDetail();
                                wnkBusinessLevelIntegralDetail.setBusiness_id(business_id);
                                wnkBusinessLevelIntegralDetail.setName("客户扫描消费");
                                wnkBusinessLevelIntegralDetail.setIntegral_number(getIntegralNumber.doubleValue());
                                wnkBusinessLevelIntegralDetail.setTransactions_date(new Date());
                                wnkBusinessLevelIntegralDetail.setTransactions_type(2);
                                wnkBusinessLevelIntegralDetail.setUser_id(users.getId());
                                wnkBusinessLevelIntegralDetail.setPay_type(5);

                                wnkBusinessAccount.setLevel_integral(wnkBusinessAccount.getLevel_integral() - getIntegralNumber);
                                this.wnkBusinessAccountService.updateRelevantBalance(wnkBusinessAccount);
                                this.wnkBusinessLevelIntegralDetailService.insertNewRecordUserXF(wnkBusinessLevelIntegralDetail);

                                WnkStoreInformation wnkStoreInformation = this.wnkStoreInformationService.selectByBusinessId(wnkBusinessAccount.getId());
                                if (wnkStoreInformation != null){
                                    if (wnkStoreInformation.getGetui_appid() != null && !wnkStoreInformation.getGetui_appid().equals("")){
                                        String pushTitle = "收款通知";
                                        String pushContent = "二维码收款到账"+pay_amount+"元，请在猛戳商家版-我的账户查看";
                                        GeTuiBusinessUtil.pushUser(wnkStoreInformation.getGetui_appid(),pushTitle,pushContent);
                                    }
                                }

                                this.integralDetailService.insertIntrgralDetailRecord(users.getId(),"扫码支付",getIntegralNumber.doubleValue(),0);
                                Map<String,Object> wnkIntegralMap = this.wnkIntegralUserServer.getUserIntegral(business_id,users.getId());
                                Integer addWnkIntegralUserState = 1;
                                if (wnkIntegralMap == null){
                                    addWnkIntegralUserState = this.wnkIntegralUserServer.addUserIntegral(business_id,users.getId());
                                }
                                if (addWnkIntegralUserState > 0){
                                    Map<String,Object> recommendIntegralMap = this.wnkIntegralUserServer.getUserIntegral(business_id,users.getId());
                                    if (recommendIntegralMap == null){
                                        this.wnkIntegralUserServer.addUserIntegral(business_id,users.getId());
                                    }
                                    // 增加用户在此商户的积分余额
                                    this.wnkIntegralUserServer.increaseUserIntegral(users.getId(),business_id,getIntegralNumber.doubleValue());
                                    // 插入积分记录 0=收入
                                    this.wnkIntegralIncomeService.addIntegralRecord("扫码支付",getIntegralNumber.doubleValue(),users.getId(),business_id,0);
                                }
                            }
                        }
                        else{
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("支付失败");
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

    // 用户扫码向商家支付-商家现金劵支付
    @RequestMapping(value = "/userScanCodeToWnkBusinessSendIntegralPay", method = RequestMethod.POST,params = {"user_id","business_id","pay_pwd","pay_amount"})
    @ResponseBody
    public Result userScanCodeToWnkBusinessIntegralPay(HttpServletRequest request, HttpServletResponse response, Integer user_id, Integer business_id,String pay_pwd,Double pay_amount){
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
                else if (!pay_pwd.equals(users.getPay_pwd())){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("支付密码不正确");
                }
                else{
                    //用户应获得的兑换积分额度及平台积分额度
                    Integer getIntegralNumber = pay_amount.intValue();
                    WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                    Map<String, Object> userSendIntegralmap = wnkSendIntegralUserService.getUserIntegral(business_id, user_id);
                    if (wnkBusinessAccount == null){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("此商家不存在");
                    }
                    else if (wnkBusinessAccount.getLevel_integral() < getIntegralNumber){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("当前商户积分不足");
                    }
                    else if (userSendIntegralmap == null || (Double)userSendIntegralmap.get("integral") < pay_amount){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("商家现金劵余额不足");
                    }
                    else{
                        Integer updateSendIntegralBalance = this.wnkSendIntegralUserService.updateIntegral(user_id,business_id,pay_amount);
                        if (updateSendIntegralBalance <= 0){
                            result.setData("");
                            result.setStatus(Result.FAIL);
                            result.setMsg("支付失败");
                        }
                        else{
                            this.wnkSendIntegralDetailUserService.addIntegralRecord("扫码支付",pay_amount,users.getId(),business_id,1);
                            wnkBusinessAccount.setBalance(wnkBusinessAccount.getBalance() + pay_amount);
                            int businessBalanceUpdateState = this.wnkBusinessAccountService.updateRelevantBalance(wnkBusinessAccount);
                            if (businessBalanceUpdateState <= 0){
                                result.setData("");
                                result.setStatus(Result.FAIL);
                                result.setMsg("支付失败");
                            }
                            else{
                                result.setData("");
                                result.setStatus(Result.SUCCESS);
                                result.setMsg("支付成功");

                                WnkBusinessBalanceDetail wnkBusinessBalanceDetail = new WnkBusinessBalanceDetail();
                                wnkBusinessBalanceDetail.setBusiness_id(business_id);
                                wnkBusinessBalanceDetail.setName("用户扫码付款");
                                wnkBusinessBalanceDetail.setTransaction_amount(Double.valueOf(pay_amount));
                                wnkBusinessBalanceDetail.setJoin_time(new Date());
                                wnkBusinessBalanceDetail.setAfter_balance(wnkBusinessAccount.getBalance());
                                wnkBusinessBalanceDetail.setType(0);
                                wnkBusinessBalanceDetail.setState(0);
                                this.wnkBusinessBalanceDetailService.insertNewRecord(wnkBusinessBalanceDetail);

                                WnkBusinessLevelIntegralDetail wnkBusinessLevelIntegralDetail = new WnkBusinessLevelIntegralDetail();
                                wnkBusinessLevelIntegralDetail.setBusiness_id(business_id);
                                wnkBusinessLevelIntegralDetail.setName("客户扫描消费");
                                wnkBusinessLevelIntegralDetail.setIntegral_number(getIntegralNumber.doubleValue());
                                wnkBusinessLevelIntegralDetail.setTransactions_date(new Date());
                                wnkBusinessLevelIntegralDetail.setTransactions_type(2);
                                wnkBusinessLevelIntegralDetail.setUser_id(users.getId());
                                wnkBusinessLevelIntegralDetail.setPay_type(5);

                                wnkBusinessAccount.setLevel_integral(wnkBusinessAccount.getLevel_integral() - getIntegralNumber);
                                this.wnkBusinessAccountService.updateRelevantBalance(wnkBusinessAccount);
                                this.wnkBusinessLevelIntegralDetailService.insertNewRecordUserXF(wnkBusinessLevelIntegralDetail);

                                this.integralDetailService.insertIntrgralDetailRecord(users.getId(),"扫码支付",getIntegralNumber.doubleValue(),0);
                                Map<String,Object> wnkIntegralMap = this.wnkIntegralUserServer.getUserIntegral(business_id,users.getId());
                                Integer addWnkIntegralUserState = 1;
                                if (wnkIntegralMap == null){
                                    addWnkIntegralUserState = this.wnkIntegralUserServer.addUserIntegral(business_id,users.getId());
                                }
                                if (addWnkIntegralUserState > 0){
                                    Map<String,Object> recommendIntegralMap = this.wnkIntegralUserServer.getUserIntegral(business_id,users.getId());
                                    if (recommendIntegralMap == null){
                                        this.wnkIntegralUserServer.addUserIntegral(business_id,users.getId());
                                    }
                                    // 增加用户在此商户的积分余额
                                    this.wnkIntegralUserServer.increaseUserIntegral(users.getId(),business_id,getIntegralNumber.doubleValue());
                                    // 插入积分记录 0=收入
                                    this.wnkIntegralIncomeService.addIntegralRecord("扫码支付",getIntegralNumber.doubleValue(),users.getId(),business_id,0);
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


    // 会员扫码支付微信App支付
    @RequestMapping(value = "/userScanCodeWXAppPay", method = RequestMethod.POST,params = {"user_id","business_id","pay_amount"})
    @ResponseBody
    public Result userScanCodeWXAppPay(HttpServletRequest request, HttpServletResponse response, Integer user_id,Integer business_id,Double pay_amount){
        Result result = new Result();
        try {
            if (LoginSessionCheckUtil.checkIsLogin(user_id,1,request,response,this.sessionIdsService) == null){
                result.setData("");
                result.setStatus(Result.NOLOGIN);
                result.setMsg("此账户暂未登录");
            }
            else {
                //用户应获得的兑换积分额度及平台积分额度
                Integer getIntegralNumber = pay_amount.intValue();
                Users users = this.usersService.selectById(user_id);
                WnkBusinessAccount wnkBusinessAccount = this.wnkBusinessAccountService.selectById(business_id);
                if (users == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("此用户不存在");
                }
                else if(wnkBusinessAccount == null){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("商户不存在");
                }
                else if (wnkBusinessAccount.getLevel_integral() < getIntegralNumber){
                    result.setData("");
                    result.setStatus(Result.FAIL);
                    result.setMsg("当前商户积分不足");
                }
                else{
                    UserScanCodeWxPay userScanCodeWxPay = new UserScanCodeWxPay();
                    userScanCodeWxPay.setUser_id(user_id);
                    userScanCodeWxPay.setBusiness_id(business_id);
                    userScanCodeWxPay.setOrder_no(UUDUtil.getOrderIdByUUId());
                    userScanCodeWxPay.setCreate_date(new Date());
                    userScanCodeWxPay.setPay_amount(pay_amount);
                    userScanCodeWxPay.setState(0);
                    int state = this.userScanCodeWxPayService.insertBusinessUpgradeOrder(userScanCodeWxPay);
                    if (state <= 0){
                        result.setData("");
                        result.setStatus(Result.FAIL);
                        result.setMsg("下单失败");
                    }
                    else{
                        Double amount = Double.valueOf(userScanCodeWxPay.getPay_amount());
                        Map<String,Object> resutMap = WechatPayLineUtil.userScanCodePayWxAppLineOrder(userScanCodeWxPay.getOrder_no(), amount, users.getWx_unionid(), request, response);
                        if ((Boolean) resutMap.get("status") == true){
                            SortedMap<String, Object> parameterMap = (SortedMap<String, Object>)resutMap.get("wx_config");
                            JSONObject json = JSONObject.fromObject(parameterMap);
                            Integer timestamp = (Integer) json.get("timestamp");

                            String payData = "{\"appid\":\""+json.get("appid")+"\",\"noncestr\":\""+json.get("noncestr")+"\",\"package\":\"Sign=WXPay\",\"partnerid\":\""+json.get("partnerid")+"\",\"prepayid\":\""+json.get("prepayid")+"\",\"timestamp\":"+timestamp+",\"sign\":\""+json.get("sign")+"\"}";
                            Map<String,Object> returnMap = new HashMap<String,Object>();
                            returnMap.put("config",payData);
                            returnMap.put("order_id",userScanCodeWxPay.getId());
                            result.setData(returnMap);
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
            result.setMsg("下单失败");
        }
        return result;
    }
}
