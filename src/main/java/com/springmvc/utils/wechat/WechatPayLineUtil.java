package com.springmvc.utils.wechat;

import com.springmvc.entity.UsersWithdrawOrder;
import com.springmvc.entity.WnkBusinessWithdrawOrder;
import com.springmvc.utils.UUDUtil;
import com.springmvc.utils.WX.WXPublicSettingUtil;
import com.springmvc.utils.WechatEnterprisePay.WeChatConfig;
import com.springmvc.utils.wxpay.sdk.WXPay;
import com.springmvc.utils.wxpay.sdk.WXPayConfigImpl;
import com.springmvc.utils.wxpay.sdk.WXPayConfigImplBusinessApp;
import com.springmvc.utils.wxpay.sdk.WXPayConfigImplUserApp;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class WechatPayLineUtil {

    /**
     * 积分充值微信支付下单方法
     * @param order_no
     * @param amount
     * @param user_openId
     * @param request
     * @param response
     * @return
     */
    public static Map<String,Object> wechatPayFileBuy(String order_no, Double amount, String user_openId, HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> resutMap = new HashMap<String,Object>();

        String prepay_id="";
        String packages = "prepay_id="+prepay_id;
        //随机数
        String nonce_str= UUDUtil.getOrderIdByUUId();

        String notify_url = "http://m.chenlankeji.cn/weChat/payCallback";
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("body", "积分在线充值");
        data.put("out_trade_no", order_no);
        data.put("device_info", "");
        data.put("fee_type", "CNY");
        Double total_fee = amount*100;
        data.put("total_fee", String.valueOf(total_fee.intValue()));
        data.put("spbill_create_ip", "101.201.67.30");
        data.put("notify_url", notify_url);
        data.put("trade_type", "JSAPI");
        data.put("openid", user_openId);
        data.put("product_id", order_no);
        WXPayConfigImpl config = null;
        try {
            config = WXPayConfigImpl.getInstance();
        } catch (Exception e) {
            resutMap.put("status",false);
            resutMap.put("msg",e.getMessage());
            e.printStackTrace();
        }
        WXPay wxpay = null;
        try {
            wxpay = new WXPay(config);
        } catch (Exception e) {
            resutMap.put("status",false);
            resutMap.put("msg",e.getMessage());
            e.printStackTrace();
        }
        String return_code = "";
        String return_msg = "";
        try {
            Map<String, String> r = wxpay.unifiedOrder(data);
            return_code = r.get("return_code");
            return_msg = r.get("return_msg");
            if (return_code.equals("SUCCESS")){
                if (r.get("result_code").equals("SUCCESS")){
                    packages = "prepay_id="+r.get("prepay_id");
                }
                else{
                    resutMap.put("status",false);
                    resutMap.put("msg","下单失败");
                }
            }
            else{
                resutMap.put("status",false);
                resutMap.put("msg",return_msg);
            }
            System.out.println("微信支付下单结果="+r.toString());
        } catch (Exception e) {
            return_code = "FAIL";
            return_msg = e.getMessage();
            resutMap.put("status",false);
            resutMap.put("msg",return_msg);
            //e.printStackTrace();
        }
        if (return_code.equals("SUCCESS")){
            RequestHandler reqHandler = new RequestHandler(request, response);
            reqHandler.init(  WXPublicSettingUtil.appId,  WXPublicSettingUtil.appSecret,  "CL201807241658111133123CL0000100");
                    /*------7.将预支付订单的id和其他信息生成签名并一起返回到jsp页面 ------- */
            SortedMap<String, Object> finalpackage = new TreeMap<String, Object>();
            String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
            finalpackage.put("appId",  WXPublicSettingUtil.appId);
            finalpackage.put("timeStamp", timestamp);
            finalpackage.put("nonceStr", nonce_str);
            finalpackage.put("package", packages);
            finalpackage.put("signType", "MD5");
            String finalsign = reqHandler.createSign(finalpackage);
            Map<String,String> map = new HashMap<String,String>();
            map.put("appid",WXPublicSettingUtil.appId);
            map.put("timeStamp",timestamp);
            map.put("nonceStr",nonce_str);
            map.put("packageValue",packages);
            map.put("paySign",finalsign);
            map.put("order_no",order_no);
            resutMap.put("status",true);
            resutMap.put("wx_config",map);
        }
        else{
            resutMap.put("status",false);
            resutMap.put("msg",return_msg);
        }

        return resutMap;
    }

    /**
     * App积分充值微信支付下单方法
     * @param order_no
     * @param amount
     * @param request
     * @param response
     * @return
     */
    public static Map<String,Object> wechatPayAppFileBuy(String order_no, Double amount, HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> resutMap = new HashMap<String,Object>();

        String prepay_id="";
        String packages = "prepay_id="+prepay_id;
        //随机数
        String nonce_str= UUDUtil.getOrderIdByUUId();

        Double total_fee = amount*100;
        String notify_url = "http://m.chenlankeji.cn/weChat/payCallback";
        //交易类型 app支付
        String trade_type = "APP";
        SortedMap<String,Object> packageParams = new TreeMap<String,Object>();
        packageParams.put("appid", WeChatConfig.MCH_USER_APP_APPID);
        packageParams.put("mch_id", WeChatConfig.MCHID_USER_APP);
        packageParams.put("nonce_str", nonce_str);
        packageParams.put("body", "通用积分充值");
        packageParams.put("out_trade_no", order_no);
        packageParams.put("total_fee",  String.valueOf(total_fee.intValue())); //价格的单位为分
        packageParams.put("spbill_create_ip", "101.201.67.30");
        packageParams.put("notify_url", notify_url);
        packageParams.put("trade_type", trade_type);
        RequestHandler reqHandler = new RequestHandler(request, response);
        reqHandler.init(  WeChatConfig.MCH_USER_APP_APPID,  WeChatConfig.SECRET_USER_APP,  WeChatConfig.API_KEY_USER);
        String finalsign = reqHandler.createSign(packageParams);
        packageParams.put("sign", finalsign);


        HashMap<String, String> data = new HashMap<String, String>();
        data.put("appid", WeChatConfig.MCH_USER_APP_APPID);
        data.put("mch_id", WeChatConfig.MCHID_USER_APP);
        data.put("nonce_str", nonce_str);
        data.put("body", "通用积分充值");
        data.put("out_trade_no", order_no);
        data.put("total_fee",  String.valueOf(total_fee.intValue())); //价格的单位为分
        data.put("spbill_create_ip", "101.201.67.30");
        data.put("notify_url", notify_url);
        data.put("trade_type", trade_type);
        data.put("sign", finalsign);
        WXPayConfigImplUserApp config = null;
        try {
            config = WXPayConfigImplUserApp.getInstance();
        } catch (Exception e) {
            resutMap.put("status",false);
            resutMap.put("msg",e.getMessage());
            e.printStackTrace();
        }
        WXPay wxpay = null;
        try {
            wxpay = new WXPay(config);
        } catch (Exception e) {
            resutMap.put("status",false);
            resutMap.put("msg",e.getMessage());
            e.printStackTrace();
        }
        String return_code = "";
        String return_msg = "";

        try {
            Map<String, String> r = wxpay.unifiedOrder(data);
            return_code = r.get("return_code");
            return_msg = r.get("return_msg");
            if (return_code.equals("SUCCESS")){
                if (r.get("result_code").equals("SUCCESS")){
                    packages = r.get("prepay_id");
                    nonce_str = r.get("nonce_str");
                }
                else{
                    resutMap.put("status",false);
                    resutMap.put("msg","下单失败");
                }
            }
            else{
                resutMap.put("status",false);
                resutMap.put("msg",return_msg);
            }
            System.out.println("微信支付下单结果="+r.toString());
        } catch (Exception e) {
            return_code = "FAIL";
            return_msg = e.getMessage();
            resutMap.put("status",false);
            resutMap.put("msg",return_msg);
            //e.printStackTrace();
        }
        if (return_code.equals("SUCCESS")){
            SortedMap<String, Object> parameterMap = new TreeMap<String, Object>();
            parameterMap.put("appid", WeChatConfig.MCH_USER_APP_APPID);
            parameterMap.put("partnerid", WeChatConfig.MCHID_USER_APP);
            parameterMap.put("prepayid", packages);
            parameterMap.put("package", "Sign=WXPay");
            parameterMap.put("noncestr", nonce_str);
            int mis = Integer.parseInt(String.valueOf(System.currentTimeMillis()).toString().substring(0, 10));
            parameterMap.put("timestamp",mis);
            finalsign = reqHandler.createSign(parameterMap);
            parameterMap.put("sign", finalsign);

            resutMap.put("status",true);
            resutMap.put("wx_config",parameterMap);
        }
        else{
            resutMap.put("status",false);
            resutMap.put("msg",return_msg);
        }

        return resutMap;
    }


    /**
     * 万能卡商品App下单
     * @param order_no
     * @param amount
     * @param request
     * @param response
     * @return
     */
    public static Map<String,Object> wechatPayWnkCommodityApp(String order_no, Double amount, HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> resutMap = new HashMap<String,Object>();

        String prepay_id="";
        String packages = prepay_id;
        //随机数
        String nonce_str= UUDUtil.getOrderIdByUUId();

        Double total_fee = amount*100;
        String notify_url = "http://m.chenlankeji.cn/weChat/wnkCommodityWXPayCallback";
        //交易类型 app支付
        String trade_type = "APP";
        SortedMap<String,Object> packageParams = new TreeMap<String,Object>();
        packageParams.put("appid", WeChatConfig.MCH_USER_APP_APPID);
        packageParams.put("mch_id", WeChatConfig.MCHID_USER_APP);
        packageParams.put("nonce_str", nonce_str);
        packageParams.put("body", "商品购买");
        packageParams.put("out_trade_no", order_no);
        packageParams.put("total_fee",  String.valueOf(total_fee.intValue())); //价格的单位为分
        packageParams.put("spbill_create_ip", "101.201.67.30");
        packageParams.put("notify_url", notify_url);
        packageParams.put("trade_type", trade_type);
        RequestHandler reqHandler = new RequestHandler(request, response);
        reqHandler.init(  WeChatConfig.MCH_USER_APP_APPID,  WeChatConfig.SECRET_USER_APP,  WeChatConfig.API_KEY_USER);
        String finalsign = reqHandler.createSign(packageParams);
        packageParams.put("sign", finalsign);


        HashMap<String, String> data = new HashMap<String, String>();
        data.put("appid", WeChatConfig.MCH_USER_APP_APPID);
        data.put("mch_id", WeChatConfig.MCHID_USER_APP);
        data.put("nonce_str", nonce_str);
        data.put("body", "商品购买");
        data.put("out_trade_no", order_no);
        data.put("total_fee",  String.valueOf(total_fee.intValue())); //价格的单位为分
        data.put("spbill_create_ip", "101.201.67.30");
        data.put("notify_url", notify_url);
        data.put("trade_type", trade_type);
        data.put("sign", finalsign);
        WXPayConfigImplUserApp config = null;
        try {
            config = WXPayConfigImplUserApp.getInstance();
        } catch (Exception e) {
            resutMap.put("status",false);
            resutMap.put("msg",e.getMessage());
            e.printStackTrace();
        }
        WXPay wxpay = null;
        try {
            wxpay = new WXPay(config);
        } catch (Exception e) {
            resutMap.put("status",false);
            resutMap.put("msg",e.getMessage());
            e.printStackTrace();
        }
        String return_code = "";
        String return_msg = "";

        try {
            Map<String, String> r = wxpay.unifiedOrder(data);
            return_code = r.get("return_code");
            return_msg = r.get("return_msg");
            if (return_code.equals("SUCCESS")){
                if (r.get("result_code").equals("SUCCESS")){
                    packages = r.get("prepay_id");
                    nonce_str = r.get("nonce_str");
                }
                else{
                    resutMap.put("status",false);
                    resutMap.put("msg","下单失败");
                }
            }
            else{
                resutMap.put("status",false);
                resutMap.put("msg",return_msg);
            }
            System.out.println("微信支付下单结果="+r.toString());
        } catch (Exception e) {
            return_code = "FAIL";
            return_msg = e.getMessage();
            resutMap.put("status",false);
            resutMap.put("msg",return_msg);
            //e.printStackTrace();
        }
        if (return_code.equals("SUCCESS")){
            SortedMap<String, Object> parameterMap = new TreeMap<String, Object>();
            parameterMap.put("appid", WeChatConfig.MCH_USER_APP_APPID);
            parameterMap.put("partnerid", WeChatConfig.MCHID_USER_APP);
            parameterMap.put("prepayid", packages);
            parameterMap.put("package", "Sign=WXPay");
            parameterMap.put("noncestr", nonce_str);
            int mis = Integer.parseInt(String.valueOf(System.currentTimeMillis()).toString().substring(0, 10));
            parameterMap.put("timestamp",mis);
            finalsign = reqHandler.createSign(parameterMap);
            parameterMap.put("sign", finalsign);

            resutMap.put("status",true);
            resutMap.put("wx_config",parameterMap);
        }
        else{
            resutMap.put("status",false);
            resutMap.put("msg",return_msg);
        }

        return resutMap;
    }


    /**
     * 万能卡商品2.0App下单
     * @param order_no
     * @param amount
     * @param request
     * @param response
     * @return
     */
    public static Map<String,Object> wechatPayWnkCommodityAppTwo(String order_no, Double amount, HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> resutMap = new HashMap<String,Object>();

        String prepay_id="";
        String packages = prepay_id;
        //随机数
        String nonce_str= UUDUtil.getOrderIdByUUId();

        Double total_fee = amount*100;
        String notify_url = "http://m.chenlankeji.cn/weChat/wnkCommodityWXPayTwoCallback";
        //交易类型 app支付
        String trade_type = "APP";
        SortedMap<String,Object> packageParams = new TreeMap<String,Object>();
        packageParams.put("appid", WeChatConfig.MCH_USER_APP_APPID);
        packageParams.put("mch_id", WeChatConfig.MCHID_USER_APP);
        packageParams.put("nonce_str", nonce_str);
        packageParams.put("body", "商品购买");
        packageParams.put("out_trade_no", order_no);
        packageParams.put("total_fee",  String.valueOf(total_fee.intValue())); //价格的单位为分
        packageParams.put("spbill_create_ip", "101.201.67.30");
        packageParams.put("notify_url", notify_url);
        packageParams.put("trade_type", trade_type);
        RequestHandler reqHandler = new RequestHandler(request, response);
        reqHandler.init(  WeChatConfig.MCH_USER_APP_APPID,  WeChatConfig.SECRET_USER_APP,  WeChatConfig.API_KEY_USER);
        String finalsign = reqHandler.createSign(packageParams);
        packageParams.put("sign", finalsign);


        HashMap<String, String> data = new HashMap<String, String>();
        data.put("appid", WeChatConfig.MCH_USER_APP_APPID);
        data.put("mch_id", WeChatConfig.MCHID_USER_APP);
        data.put("nonce_str", nonce_str);
        data.put("body", "商品购买");
        data.put("out_trade_no", order_no);
        data.put("total_fee",  String.valueOf(total_fee.intValue())); //价格的单位为分
        data.put("spbill_create_ip", "101.201.67.30");
        data.put("notify_url", notify_url);
        data.put("trade_type", trade_type);
        data.put("sign", finalsign);
        WXPayConfigImplUserApp config = null;
        try {
            config = WXPayConfigImplUserApp.getInstance();
        } catch (Exception e) {
            resutMap.put("status",false);
            resutMap.put("msg",e.getMessage());
            e.printStackTrace();
        }
        WXPay wxpay = null;
        try {
            wxpay = new WXPay(config);
        } catch (Exception e) {
            resutMap.put("status",false);
            resutMap.put("msg",e.getMessage());
            e.printStackTrace();
        }
        String return_code = "";
        String return_msg = "";

        try {
            Map<String, String> r = wxpay.unifiedOrder(data);
            return_code = r.get("return_code");
            return_msg = r.get("return_msg");
            if (return_code.equals("SUCCESS")){
                if (r.get("result_code").equals("SUCCESS")){
                    packages = r.get("prepay_id");
                    nonce_str = r.get("nonce_str");
                }
                else{
                    resutMap.put("status",false);
                    resutMap.put("msg","下单失败");
                }
            }
            else{
                resutMap.put("status",false);
                resutMap.put("msg",return_msg);
            }
            System.out.println("微信支付下单结果="+r.toString());
        } catch (Exception e) {
            return_code = "FAIL";
            return_msg = e.getMessage();
            resutMap.put("status",false);
            resutMap.put("msg",return_msg);
            //e.printStackTrace();
        }
        if (return_code.equals("SUCCESS")){
            SortedMap<String, Object> parameterMap = new TreeMap<String, Object>();
            parameterMap.put("appid", WeChatConfig.MCH_USER_APP_APPID);
            parameterMap.put("partnerid", WeChatConfig.MCHID_USER_APP);
            parameterMap.put("prepayid", packages);
            parameterMap.put("package", "Sign=WXPay");
            parameterMap.put("noncestr", nonce_str);
            int mis = Integer.parseInt(String.valueOf(System.currentTimeMillis()).toString().substring(0, 10));
            parameterMap.put("timestamp",mis);
            finalsign = reqHandler.createSign(parameterMap);
            parameterMap.put("sign", finalsign);

            resutMap.put("status",true);
            resutMap.put("wx_config",parameterMap);
        }
        else{
            resutMap.put("status",false);
            resutMap.put("msg",return_msg);
        }

        return resutMap;
    }


    /**
     * 万能卡商品2.0App下单 - 酒店类商家
     * @param order_no
     * @param amount
     * @param request
     * @param response
     * @return
     */
    public static Map<String,Object> wechatPayWnkCommodityAppTwoByHotel(String order_no, Double amount, HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> resutMap = new HashMap<String,Object>();

        String prepay_id="";
        String packages = prepay_id;
        //随机数
        String nonce_str= UUDUtil.getOrderIdByUUId();

        Double total_fee = amount*100;
        String notify_url = "http://m.chenlankeji.cn/weChat/wnkCommodityWXPayTwoCallbackByHotel";
        //交易类型 app支付
        String trade_type = "APP";
        SortedMap<String,Object> packageParams = new TreeMap<String,Object>();
        packageParams.put("appid", WeChatConfig.MCH_USER_APP_APPID);
        packageParams.put("mch_id", WeChatConfig.MCHID_USER_APP);
        packageParams.put("nonce_str", nonce_str);
        packageParams.put("body", "商品购买");
        packageParams.put("out_trade_no", order_no);
        packageParams.put("total_fee",  String.valueOf(total_fee.intValue())); //价格的单位为分
        packageParams.put("spbill_create_ip", "101.201.67.30");
        packageParams.put("notify_url", notify_url);
        packageParams.put("trade_type", trade_type);
        RequestHandler reqHandler = new RequestHandler(request, response);
        reqHandler.init(  WeChatConfig.MCH_USER_APP_APPID,  WeChatConfig.SECRET_USER_APP,  WeChatConfig.API_KEY_USER);
        String finalsign = reqHandler.createSign(packageParams);
        packageParams.put("sign", finalsign);


        HashMap<String, String> data = new HashMap<String, String>();
        data.put("appid", WeChatConfig.MCH_USER_APP_APPID);
        data.put("mch_id", WeChatConfig.MCHID_USER_APP);
        data.put("nonce_str", nonce_str);
        data.put("body", "商品购买");
        data.put("out_trade_no", order_no);
        data.put("total_fee",  String.valueOf(total_fee.intValue())); //价格的单位为分
        data.put("spbill_create_ip", "101.201.67.30");
        data.put("notify_url", notify_url);
        data.put("trade_type", trade_type);
        data.put("sign", finalsign);
        WXPayConfigImplUserApp config = null;
        try {
            config = WXPayConfigImplUserApp.getInstance();
        } catch (Exception e) {
            resutMap.put("status",false);
            resutMap.put("msg",e.getMessage());
            e.printStackTrace();
        }
        WXPay wxpay = null;
        try {
            wxpay = new WXPay(config);
        } catch (Exception e) {
            resutMap.put("status",false);
            resutMap.put("msg",e.getMessage());
            e.printStackTrace();
        }
        String return_code = "";
        String return_msg = "";

        try {
            Map<String, String> r = wxpay.unifiedOrder(data);
            return_code = r.get("return_code");
            return_msg = r.get("return_msg");
            if (return_code.equals("SUCCESS")){
                if (r.get("result_code").equals("SUCCESS")){
                    packages = r.get("prepay_id");
                    nonce_str = r.get("nonce_str");
                }
                else{
                    resutMap.put("status",false);
                    resutMap.put("msg","下单失败");
                }
            }
            else{
                resutMap.put("status",false);
                resutMap.put("msg",return_msg);
            }
            System.out.println("微信支付下单结果="+r.toString());
        } catch (Exception e) {
            return_code = "FAIL";
            return_msg = e.getMessage();
            resutMap.put("status",false);
            resutMap.put("msg",return_msg);
            //e.printStackTrace();
        }
        if (return_code.equals("SUCCESS")){
            SortedMap<String, Object> parameterMap = new TreeMap<String, Object>();
            parameterMap.put("appid", WeChatConfig.MCH_USER_APP_APPID);
            parameterMap.put("partnerid", WeChatConfig.MCHID_USER_APP);
            parameterMap.put("prepayid", packages);
            parameterMap.put("package", "Sign=WXPay");
            parameterMap.put("noncestr", nonce_str);
            int mis = Integer.parseInt(String.valueOf(System.currentTimeMillis()).toString().substring(0, 10));
            parameterMap.put("timestamp",mis);
            finalsign = reqHandler.createSign(parameterMap);
            parameterMap.put("sign", finalsign);

            resutMap.put("status",true);
            resutMap.put("wx_config",parameterMap);
        }
        else{
            resutMap.put("status",false);
            resutMap.put("msg",return_msg);
        }

        return resutMap;
    }


    /**
     * 万能卡商品App退款
     * @param order_no
     * @param amount
     * @param request
     * @param response
     * @return
     */
    public static Map<String,Object> wechatPayWnkCommodityAppRefund(String order_no,String out_refund_no,Double refund_fee, Double amount, HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> resutMap = new HashMap<String,Object>();

//        String prepay_id="";
//        String packages = prepay_id;
        //随机数
        String nonce_str= UUDUtil.getOrderIdByUUId();

        Double total_fee = amount*100;
        refund_fee = refund_fee * 100;
//        String notify_url = "http://m.chenlankeji.cn/weChat/wnkCommodityWXPayTwoCallback";
        //交易类型 app支付
//        String trade_type = "APP";
        SortedMap<String,Object> packageParams = new TreeMap<String,Object>();
        packageParams.put("appid", WeChatConfig.MCH_USER_APP_APPID);
        packageParams.put("mch_id", WeChatConfig.MCHID_USER_APP);
        packageParams.put("nonce_str", nonce_str);
//        packageParams.put("body", "商品购买");
        packageParams.put("out_trade_no", order_no);
        packageParams.put("out_refund_no", out_refund_no);
        packageParams.put("total_fee",  String.valueOf(total_fee.intValue())); //价格的单位为分
        packageParams.put("refund_fee",  String.valueOf(refund_fee.intValue())); //价格的单位为分
//        packageParams.put("spbill_create_ip", "101.201.67.30");
//        packageParams.put("notify_url", notify_url);
//        packageParams.put("trade_type", trade_type);
        RequestHandler reqHandler = new RequestHandler(request, response);
        reqHandler.init(  WeChatConfig.MCH_USER_APP_APPID,  WeChatConfig.SECRET_USER_APP,  WeChatConfig.API_KEY_USER);
        String finalsign = reqHandler.createSign(packageParams);
        packageParams.put("sign", finalsign);


        HashMap<String, String> data = new HashMap<String, String>();
        data.put("appid", WeChatConfig.MCH_USER_APP_APPID);
        data.put("mch_id", WeChatConfig.MCHID_USER_APP);
        data.put("nonce_str", nonce_str);
//        data.put("body", "商品购买");
        data.put("out_trade_no", order_no);
        data.put("out_refund_no", out_refund_no);
        data.put("total_fee",  String.valueOf(total_fee.intValue())); //价格的单位为分
        data.put("refund_fee",  String.valueOf(refund_fee.intValue())); //价格的单位为分
//        packageParams.put("spbill_create_ip", "101.201.67.30");
//        packageParams.put("notify_url", notify_url);
//        packageParams.put("trade_type", trade_type);
        data.put("sign", finalsign);
        WXPayConfigImplUserApp config = null;
        try {
            config = WXPayConfigImplUserApp.getInstance(1);
        } catch (Exception e) {
            resutMap.put("status",false);
            resutMap.put("msg",e.getMessage()+"1");
            e.printStackTrace();
        }
        WXPay wxpay = null;
        try {
            wxpay = new WXPay(config);
        } catch (Exception e) {
            resutMap.put("status",false);
            resutMap.put("msg",e.getMessage()+"2");
            e.printStackTrace();
        }
        String return_code = "";
        String return_msg = "";

        try {
            Map<String, String> r = wxpay.refund(data);
            return_code = r.get("return_code");
            return_msg = r.get("return_msg");
            if (return_code.equals("SUCCESS")){
                if (r.get("result_code").equals("SUCCESS")){
                    resutMap.put("status",true);
                    resutMap.put("msg","退款成功");
                }
                else{
                    resutMap.put("status",false);
                    resutMap.put("msg","退款失败"+"3");
                }
            }
            else{
                resutMap.put("status",false);
                resutMap.put("msg",return_msg+"4");
            }
            System.out.println("微信支付下单结果="+r.toString());
        } catch (Exception e) {
            return_code = "FAIL";
            return_msg = e.getMessage();
            resutMap.put("status",false);
            resutMap.put("msg",return_msg+"5");
            //e.printStackTrace();
        }

        return resutMap;
    }


    /**
     * 微信开卡App下单
     * @param order_no
     * @param amount
     * @param request
     * @param response
     * @return
     */
    public static Map<String,Object> wechatPayOpenMemberCardApp(String order_no, Double amount, HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> resutMap = new HashMap<String,Object>();

        String prepay_id="";
        String packages = "prepay_id="+prepay_id;
        //随机数
        String nonce_str= UUDUtil.getOrderIdByUUId();

        Double total_fee = amount*100;
        String notify_url = "http://m.chenlankeji.cn/weChat/openCardWXPayCallback";
        //交易类型 app支付
        String trade_type = "APP";
        SortedMap<String,Object> packageParams = new TreeMap<String,Object>();
        packageParams.put("appid", WeChatConfig.MCH_USER_APP_APPID);
        packageParams.put("mch_id", WeChatConfig.MCHID_USER_APP);
        packageParams.put("nonce_str", nonce_str);
        packageParams.put("body", "万能卡开卡");
        packageParams.put("out_trade_no", order_no);
        packageParams.put("total_fee",  String.valueOf(total_fee.intValue())); //价格的单位为分
        packageParams.put("spbill_create_ip", "101.201.67.30");
        packageParams.put("notify_url", notify_url);
        packageParams.put("trade_type", trade_type);
        RequestHandler reqHandler = new RequestHandler(request, response);
        reqHandler.init(  WeChatConfig.MCH_USER_APP_APPID,  WeChatConfig.SECRET_USER_APP,  WeChatConfig.API_KEY_USER);
        String finalsign = reqHandler.createSign(packageParams);
        packageParams.put("sign", finalsign);


        HashMap<String, String> data = new HashMap<String, String>();
        data.put("appid", WeChatConfig.MCH_USER_APP_APPID);
        data.put("mch_id", WeChatConfig.MCHID_USER_APP);
        data.put("nonce_str", nonce_str);
        data.put("body", "万能卡开卡");
        data.put("out_trade_no", order_no);
        data.put("total_fee",  String.valueOf(total_fee.intValue())); //价格的单位为分
        data.put("spbill_create_ip", "101.201.67.30");
        data.put("notify_url", notify_url);
        data.put("trade_type", trade_type);
        data.put("sign", finalsign);
        WXPayConfigImplUserApp config = null;
        try {
            config = WXPayConfigImplUserApp.getInstance();
        } catch (Exception e) {
            resutMap.put("status",false);
            resutMap.put("msg",e.getMessage());
            e.printStackTrace();
        }
        WXPay wxpay = null;
        try {
            wxpay = new WXPay(config);
        } catch (Exception e) {
            resutMap.put("status",false);
            resutMap.put("msg",e.getMessage());
            e.printStackTrace();
        }
        String return_code = "";
        String return_msg = "";

        try {
            Map<String, String> r = wxpay.unifiedOrder(data);
            return_code = r.get("return_code");
            return_msg = r.get("return_msg");
            if (return_code.equals("SUCCESS")){
                if (r.get("result_code").equals("SUCCESS")){
                    packages = r.get("prepay_id");
                    nonce_str = r.get("nonce_str");
                }
                else{
                    resutMap.put("status",false);
                    resutMap.put("msg","下单失败");
                }
            }
            else{
                resutMap.put("status",false);
                resutMap.put("msg",return_msg);
            }
            System.out.println("微信支付下单结果="+r.toString());
        } catch (Exception e) {
            return_code = "FAIL";
            return_msg = e.getMessage();
            resutMap.put("status",false);
            resutMap.put("msg",return_msg);
            //e.printStackTrace();
        }
        if (return_code.equals("SUCCESS")){
            SortedMap<String, Object> parameterMap = new TreeMap<String, Object>();
            parameterMap.put("appid", WeChatConfig.MCH_USER_APP_APPID);
            parameterMap.put("partnerid", WeChatConfig.MCHID_USER_APP);
            parameterMap.put("prepayid", packages);
            parameterMap.put("package", "Sign=WXPay");
            parameterMap.put("noncestr", nonce_str);
            int mis = Integer.parseInt(String.valueOf(System.currentTimeMillis()).toString().substring(0, 10));
            parameterMap.put("timestamp",mis);
            finalsign = reqHandler.createSign(parameterMap);
            parameterMap.put("sign", finalsign);

            resutMap.put("status",true);
            resutMap.put("wx_config",parameterMap);
        }
        else{
            resutMap.put("status",false);
            resutMap.put("msg",return_msg);
        }

        return resutMap;
    }


    /**
     * 微信开卡下单
     * @param order_no
     * @param amount
     * @param user_openId
     * @param request
     * @param response
     * @return
     */
    public static Map<String,Object> wechatPayOpenMemberCard(String order_no, Double amount, String user_openId, HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> resutMap = new HashMap<String,Object>();

        String prepay_id="";
        String packages = "prepay_id="+prepay_id;
        //随机数
        String nonce_str= UUDUtil.getOrderIdByUUId();

        String notify_url = "http://m.chenlankeji.cn/weChat/openCardWXPayCallback";
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("body", "会员开卡");
        data.put("out_trade_no", order_no);
        data.put("device_info", "");
        data.put("fee_type", "CNY");
        Double total_fee = amount*100;
        data.put("total_fee", String.valueOf(total_fee.intValue()));
        data.put("spbill_create_ip", "101.201.67.30");
        data.put("notify_url", notify_url);
        data.put("trade_type", "JSAPI");
        data.put("openid", user_openId);
        data.put("product_id", order_no);
        WXPayConfigImpl config = null;
        try {
            config = WXPayConfigImpl.getInstance();
        } catch (Exception e) {
            resutMap.put("status",false);
            resutMap.put("msg",e.getMessage());
            e.printStackTrace();
        }
        WXPay wxpay = null;
        try {
            wxpay = new WXPay(config);
        } catch (Exception e) {
            resutMap.put("status",false);
            resutMap.put("msg",e.getMessage());
            e.printStackTrace();
        }
        String return_code = "";
        String return_msg = "";
        try {
            Map<String, String> r = wxpay.unifiedOrder(data);
            return_code = r.get("return_code");
            return_msg = r.get("return_msg");
            if (return_code.equals("SUCCESS")){
                if (r.get("result_code").equals("SUCCESS")){
                    packages = "prepay_id="+r.get("prepay_id");
                }
                else{
                    resutMap.put("status",false);
                    resutMap.put("msg","下单失败");
                }
            }
            else{
                resutMap.put("status",false);
                resutMap.put("msg",return_msg);
            }
            System.out.println("微信支付下单结果="+r.toString());
        } catch (Exception e) {
            return_code = "FAIL";
            return_msg = e.getMessage();
            resutMap.put("status",false);
            resutMap.put("msg",return_msg);
            //e.printStackTrace();
        }
        if (return_code.equals("SUCCESS")){
            RequestHandler reqHandler = new RequestHandler(request, response);
            reqHandler.init(  WXPublicSettingUtil.appId,  WXPublicSettingUtil.appSecret,  "CL201807241658111133123CL0000100");
                    /*------7.将预支付订单的id和其他信息生成签名并一起返回到jsp页面 ------- */
            SortedMap<String, Object> finalpackage = new TreeMap<String, Object>();
            String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
            finalpackage.put("appId",  WXPublicSettingUtil.appId);
            finalpackage.put("timeStamp", timestamp);
            finalpackage.put("nonceStr", nonce_str);
            finalpackage.put("package", packages);
            finalpackage.put("signType", "MD5");
            String finalsign = reqHandler.createSign(finalpackage);
            Map<String,String> map = new HashMap<String,String>();
            map.put("appid",WXPublicSettingUtil.appId);
            map.put("timeStamp",timestamp);
            map.put("nonceStr",nonce_str);
            map.put("packageValue",packages);
            map.put("paySign",finalsign);
            map.put("order_no",order_no);
            resutMap.put("status",true);
            resutMap.put("wx_config",map);
        }
        else{
            resutMap.put("status",false);
            resutMap.put("msg",return_msg);
        }

        return resutMap;
    }


    /**
     * 商家等级升级下单App支付
     * @param order_no
     * @param amount
     * @param request
     * @param response
     * @return
     */
    public static Map<String,Object> wechatPayWnkBusinessLevelUpgrade(String order_no, Double amount, HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> resutMap = new HashMap<String,Object>();

        String prepay_id="";
        String packages = "prepay_id="+prepay_id;
        //随机数
        String nonce_str= UUDUtil.getOrderIdByUUId();

        Double total_fee = amount*100;
        String notify_url = "http://m.chenlankeji.cn/weChat/wnkBusinessLevelUpgradeWXPayCallback";
        //交易类型 app支付
        String trade_type = "APP";
        SortedMap<String,Object> packageParams = new TreeMap<String,Object>();
        packageParams.put("appid", WeChatConfig.MCH_BUSINESS_APP_APPID);
        packageParams.put("mch_id", WeChatConfig.MCHID_BUSINESS_APP);
        packageParams.put("nonce_str", nonce_str);
        packageParams.put("body", "商家升级");
        packageParams.put("out_trade_no", order_no);
        packageParams.put("total_fee",  String.valueOf(total_fee.intValue())); //价格的单位为分
        packageParams.put("spbill_create_ip", "101.201.67.30");
        packageParams.put("notify_url", notify_url);
        packageParams.put("trade_type", trade_type);
        RequestHandler reqHandler = new RequestHandler(request, response);
        reqHandler.init(  WeChatConfig.MCH_BUSINESS_APP_APPID,  WeChatConfig.SECRET_BUSINESS_APP,  WeChatConfig.API_KEY_USER);
        String finalsign = reqHandler.createSign(packageParams);
        packageParams.put("sign", finalsign);


        HashMap<String, String> data = new HashMap<String, String>();
        data.put("appid", WeChatConfig.MCH_BUSINESS_APP_APPID);
        data.put("mch_id", WeChatConfig.MCHID_BUSINESS_APP);
        data.put("nonce_str", nonce_str);
        data.put("body", "商家升级");
        data.put("out_trade_no", order_no);
        data.put("total_fee",  String.valueOf(total_fee.intValue())); //价格的单位为分
        data.put("spbill_create_ip", "101.201.67.30");
        data.put("notify_url", notify_url);
        data.put("trade_type", trade_type);
        data.put("sign", finalsign);
        WXPayConfigImplBusinessApp config = null;
        try {
            config = WXPayConfigImplBusinessApp.getInstance();
        } catch (Exception e) {
            resutMap.put("status",false);
            resutMap.put("msg",e.getMessage());
            e.printStackTrace();
        }
        WXPay wxpay = null;
        try {
            wxpay = new WXPay(config);
        } catch (Exception e) {
            resutMap.put("status",false);
            resutMap.put("msg",e.getMessage());
            e.printStackTrace();
        }
        String return_code = "";
        String return_msg = "";

        try {
            Map<String, String> r = wxpay.unifiedOrder(data);
            return_code = r.get("return_code");
            return_msg = r.get("return_msg");
            if (return_code.equals("SUCCESS")){
                if (r.get("result_code").equals("SUCCESS")){
                    packages = r.get("prepay_id");
                    nonce_str = r.get("nonce_str");
                }
                else{
                    resutMap.put("status",false);
                    resutMap.put("msg","下单失败");
                }
            }
            else{
                resutMap.put("status",false);
                resutMap.put("msg",return_msg);
            }
            System.out.println("微信支付下单结果="+r.toString());
        } catch (Exception e) {
            return_code = "FAIL";
            return_msg = e.getMessage();
            resutMap.put("status",false);
            resutMap.put("msg",return_msg);
            //e.printStackTrace();
        }
        if (return_code.equals("SUCCESS")){
            SortedMap<String, Object> parameterMap = new TreeMap<String, Object>();
            parameterMap.put("appid", WeChatConfig.MCH_BUSINESS_APP_APPID);
            parameterMap.put("partnerid", WeChatConfig.MCHID_BUSINESS_APP);
            parameterMap.put("prepayid", packages);
            parameterMap.put("package", "Sign=WXPay");
            parameterMap.put("noncestr", nonce_str);
            int mis = Integer.parseInt(String.valueOf(System.currentTimeMillis()).toString().substring(0, 10));
            parameterMap.put("timestamp",mis);
            finalsign = reqHandler.createSign(parameterMap);
            parameterMap.put("sign", finalsign);

            resutMap.put("status",true);
            resutMap.put("wx_config",parameterMap);
        }
        else{
            resutMap.put("status",false);
            resutMap.put("msg",return_msg);
        }

        return resutMap;
    }


    /**
     * 商家购买推广物料微信下单App支付
     * @param order_no
     * @param amount
     * @param request
     * @param response
     * @return
     */
    public static Map<String,Object> wechatPayWnkBusinessBuyMaterielMeal(String order_no, Double amount, HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> resutMap = new HashMap<String,Object>();

        String prepay_id="";
        String packages = "prepay_id="+prepay_id;
        //随机数
        String nonce_str= UUDUtil.getOrderIdByUUId();

        Double total_fee = amount*100;
        String notify_url = "http://m.chenlankeji.cn/weChat/wechatPayWnkBusinessBuyMaterielMealCallback";
        //交易类型 app支付
        String trade_type = "APP";
        SortedMap<String,Object> packageParams = new TreeMap<String,Object>();
        packageParams.put("appid", WeChatConfig.MCH_BUSINESS_APP_APPID);
        packageParams.put("mch_id", WeChatConfig.MCHID_BUSINESS_APP);
        packageParams.put("nonce_str", nonce_str);
        packageParams.put("body", "商户购买推广物料");
        packageParams.put("out_trade_no", order_no);
        packageParams.put("total_fee",  String.valueOf(total_fee.intValue())); //价格的单位为分
        packageParams.put("spbill_create_ip", "101.201.67.30");
        packageParams.put("notify_url", notify_url);
        packageParams.put("trade_type", trade_type);
        RequestHandler reqHandler = new RequestHandler(request, response);
        reqHandler.init(  WeChatConfig.MCH_BUSINESS_APP_APPID,  WeChatConfig.SECRET_BUSINESS_APP,  WeChatConfig.API_KEY_USER);
        String finalsign = reqHandler.createSign(packageParams);
        packageParams.put("sign", finalsign);


        HashMap<String, String> data = new HashMap<String, String>();
        data.put("appid", WeChatConfig.MCH_BUSINESS_APP_APPID);
        data.put("mch_id", WeChatConfig.MCHID_BUSINESS_APP);
        data.put("nonce_str", nonce_str);
        data.put("body", "商户购买推广物料");
        data.put("out_trade_no", order_no);
        data.put("total_fee",  String.valueOf(total_fee.intValue())); //价格的单位为分
        data.put("spbill_create_ip", "101.201.67.30");
        data.put("notify_url", notify_url);
        data.put("trade_type", trade_type);
        data.put("sign", finalsign);
        WXPayConfigImplBusinessApp config = null;
        try {
            config = WXPayConfigImplBusinessApp.getInstance();
        } catch (Exception e) {
            resutMap.put("status",false);
            resutMap.put("msg",e.getMessage());
            e.printStackTrace();
        }
        WXPay wxpay = null;
        try {
            wxpay = new WXPay(config);
        } catch (Exception e) {
            resutMap.put("status",false);
            resutMap.put("msg",e.getMessage());
            e.printStackTrace();
        }
        String return_code = "";
        String return_msg = "";

        try {
            Map<String, String> r = wxpay.unifiedOrder(data);
            return_code = r.get("return_code");
            return_msg = r.get("return_msg");
            if (return_code.equals("SUCCESS")){
                if (r.get("result_code").equals("SUCCESS")){
                    packages = r.get("prepay_id");
                    nonce_str = r.get("nonce_str");
                }
                else{
                    resutMap.put("status",false);
                    resutMap.put("msg","下单失败");
                }
            }
            else{
                resutMap.put("status",false);
                resutMap.put("msg",return_msg);
            }
            System.out.println("微信支付下单结果="+r.toString());
        } catch (Exception e) {
            return_code = "FAIL";
            return_msg = e.getMessage();
            resutMap.put("status",false);
            resutMap.put("msg",return_msg);
            //e.printStackTrace();
        }
        if (return_code.equals("SUCCESS")){
            SortedMap<String, Object> parameterMap = new TreeMap<String, Object>();
            parameterMap.put("appid", WeChatConfig.MCH_BUSINESS_APP_APPID);
            parameterMap.put("partnerid", WeChatConfig.MCHID_BUSINESS_APP);
            parameterMap.put("prepayid", packages);
            parameterMap.put("package", "Sign=WXPay");
            parameterMap.put("noncestr", nonce_str);
            int mis = Integer.parseInt(String.valueOf(System.currentTimeMillis()).toString().substring(0, 10));
            parameterMap.put("timestamp",mis);
            finalsign = reqHandler.createSign(parameterMap);
            parameterMap.put("sign", finalsign);

            resutMap.put("status",true);
            resutMap.put("wx_config",parameterMap);
        }
        else{
            resutMap.put("status",false);
            resutMap.put("msg",return_msg);
        }

        return resutMap;
    }


    /**
     * 商家充值下单App支付
     * @param order_no
     * @param amount
     * @param request
     * @param response
     * @return
     */
    public static Map<String,Object> wechatPayWnkBusinessRecharge(String order_no, Double amount, HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> resutMap = new HashMap<String,Object>();

        String prepay_id="";
        String packages = "prepay_id="+prepay_id;
        //随机数
        String nonce_str= UUDUtil.getOrderIdByUUId();

        Double total_fee = amount*100;
        String notify_url = "http://m.chenlankeji.cn/weChat/wnkBusinessRechargeLineOrderCallBack";
        //交易类型 app支付
        String trade_type = "APP";
        SortedMap<String,Object> packageParams = new TreeMap<String,Object>();
        packageParams.put("appid", WeChatConfig.MCH_BUSINESS_APP_APPID);
        packageParams.put("mch_id", WeChatConfig.MCHID_BUSINESS_APP);
        packageParams.put("nonce_str", nonce_str);
        packageParams.put("body", "商家余额充值");
        packageParams.put("out_trade_no", order_no);
        packageParams.put("total_fee",  String.valueOf(total_fee.intValue())); //价格的单位为分
        packageParams.put("spbill_create_ip", "101.201.67.30");
        packageParams.put("notify_url", notify_url);
        packageParams.put("trade_type", trade_type);
        RequestHandler reqHandler = new RequestHandler(request, response);
        reqHandler.init(  WeChatConfig.MCH_BUSINESS_APP_APPID,  WeChatConfig.SECRET_BUSINESS_APP,  WeChatConfig.API_KEY_USER);
        String finalsign = reqHandler.createSign(packageParams);
        packageParams.put("sign", finalsign);


        HashMap<String, String> data = new HashMap<String, String>();
        data.put("appid", WeChatConfig.MCH_BUSINESS_APP_APPID);
        data.put("mch_id", WeChatConfig.MCHID_BUSINESS_APP);
        data.put("nonce_str", nonce_str);
        data.put("body", "商家余额充值");
        data.put("out_trade_no", order_no);
        data.put("total_fee",  String.valueOf(total_fee.intValue())); //价格的单位为分
        data.put("spbill_create_ip", "101.201.67.30");
        data.put("notify_url", notify_url);
        data.put("trade_type", trade_type);
        data.put("sign", finalsign);
        WXPayConfigImplBusinessApp config = null;
        try {
            config = WXPayConfigImplBusinessApp.getInstance();
        } catch (Exception e) {
            resutMap.put("status",false);
            resutMap.put("msg",e.getMessage());
            e.printStackTrace();
        }
        WXPay wxpay = null;
        try {
            wxpay = new WXPay(config);
        } catch (Exception e) {
            resutMap.put("status",false);
            resutMap.put("msg",e.getMessage());
            e.printStackTrace();
        }
        String return_code = "";
        String return_msg = "";

        try {
            Map<String, String> r = wxpay.unifiedOrder(data);
            return_code = r.get("return_code");
            return_msg = r.get("return_msg");
            if (return_code.equals("SUCCESS")){
                if (r.get("result_code").equals("SUCCESS")){
                    packages = r.get("prepay_id");
                    nonce_str = r.get("nonce_str");
                }
                else{
                    resutMap.put("status",false);
                    resutMap.put("msg","下单失败");
                }
            }
            else{
                resutMap.put("status",false);
                resutMap.put("msg",return_msg);
            }
            System.out.println("微信支付下单结果="+r.toString());
        } catch (Exception e) {
            return_code = "FAIL";
            return_msg = e.getMessage();
            resutMap.put("status",false);
            resutMap.put("msg",return_msg);
            //e.printStackTrace();
        }
        if (return_code.equals("SUCCESS")){
            SortedMap<String, Object> parameterMap = new TreeMap<String, Object>();
            parameterMap.put("appid", WeChatConfig.MCH_BUSINESS_APP_APPID);
            parameterMap.put("partnerid", WeChatConfig.MCHID_BUSINESS_APP);
            parameterMap.put("prepayid", packages);
            parameterMap.put("package", "Sign=WXPay");
            parameterMap.put("noncestr", nonce_str);
            int mis = Integer.parseInt(String.valueOf(System.currentTimeMillis()).toString().substring(0, 10));
            parameterMap.put("timestamp",mis);
            finalsign = reqHandler.createSign(parameterMap);
            parameterMap.put("sign", finalsign);

            resutMap.put("status",true);
            resutMap.put("wx_config",parameterMap);
        }
        else{
            resutMap.put("status",false);
            resutMap.put("msg",return_msg);
        }

        return resutMap;

    }


    /**
     * 用户扫码支付App下单
     * @param order_no
     * @param amount
     * @param user_openId
     * @param request
     * @param response
     * @return
     */
    public static Map<String,Object> userScanCodePayWxAppLineOrder(String order_no, Double amount, String user_openId, HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> resutMap = new HashMap<String,Object>();

        String prepay_id="";
        String packages = prepay_id;
        //随机数
        String nonce_str= UUDUtil.getOrderIdByUUId();

        Double total_fee = amount*100;
        String notify_url = "http://m.chenlankeji.cn/weChat/userScanCodeWXAppPayCallback";
        //交易类型 app支付
        String trade_type = "APP";
        SortedMap<String,Object> packageParams = new TreeMap<String,Object>();
        packageParams.put("appid", WeChatConfig.MCH_USER_APP_APPID);
        packageParams.put("mch_id", WeChatConfig.MCHID_USER_APP);
        packageParams.put("nonce_str", nonce_str);
        packageParams.put("body", "扫码支付");
        packageParams.put("out_trade_no", order_no);
        packageParams.put("total_fee",  String.valueOf(total_fee.intValue())); //价格的单位为分
        packageParams.put("spbill_create_ip", "101.201.67.30");
        packageParams.put("notify_url", notify_url);
        packageParams.put("trade_type", trade_type);
        RequestHandler reqHandler = new RequestHandler(request, response);
        reqHandler.init(  WeChatConfig.MCH_USER_APP_APPID,  WeChatConfig.SECRET_USER_APP,  WeChatConfig.API_KEY_USER);
        String finalsign = reqHandler.createSign(packageParams);
        packageParams.put("sign", finalsign);


        HashMap<String, String> data = new HashMap<String, String>();
        data.put("appid", WeChatConfig.MCH_USER_APP_APPID);
        data.put("mch_id", WeChatConfig.MCHID_USER_APP);
        data.put("nonce_str", nonce_str);
        data.put("body", "扫码支付");
        data.put("out_trade_no", order_no);
        data.put("total_fee",  String.valueOf(total_fee.intValue())); //价格的单位为分
        data.put("spbill_create_ip", "101.201.67.30");
        data.put("notify_url", notify_url);
        data.put("trade_type", trade_type);
        data.put("sign", finalsign);
        WXPayConfigImplUserApp config = null;
        try {
            config = WXPayConfigImplUserApp.getInstance();
        } catch (Exception e) {
            resutMap.put("status",false);
            resutMap.put("msg",e.getMessage());
            e.printStackTrace();
        }
        WXPay wxpay = null;
        try {
            wxpay = new WXPay(config);
        } catch (Exception e) {
            resutMap.put("status",false);
            resutMap.put("msg",e.getMessage());
            e.printStackTrace();
        }
        String return_code = "";
        String return_msg = "";

        try {
            Map<String, String> r = wxpay.unifiedOrder(data);
            return_code = r.get("return_code");
            return_msg = r.get("return_msg");
            if (return_code.equals("SUCCESS")){
                if (r.get("result_code").equals("SUCCESS")){
                    packages = r.get("prepay_id");
                    nonce_str = r.get("nonce_str");
                }
                else{
                    resutMap.put("status",false);
                    resutMap.put("msg","下单失败");
                }
            }
            else{
                resutMap.put("status",false);
                resutMap.put("msg",return_msg);
            }
            System.out.println("微信支付下单结果="+r.toString());
        } catch (Exception e) {
            return_code = "FAIL";
            return_msg = e.getMessage();
            resutMap.put("status",false);
            resutMap.put("msg",return_msg);
            //e.printStackTrace();
        }
        if (return_code.equals("SUCCESS")){
            SortedMap<String, Object> parameterMap = new TreeMap<String, Object>();
            parameterMap.put("appid", WeChatConfig.MCH_USER_APP_APPID);
            parameterMap.put("partnerid", WeChatConfig.MCHID_USER_APP);
            parameterMap.put("prepayid", packages);
            parameterMap.put("package", "Sign=WXPay");
            parameterMap.put("noncestr", nonce_str);
            int mis = Integer.parseInt(String.valueOf(System.currentTimeMillis()).toString().substring(0, 10));
            parameterMap.put("timestamp",mis);
            finalsign = reqHandler.createSign(parameterMap);
            parameterMap.put("sign", finalsign);

            resutMap.put("status",true);
            resutMap.put("wx_config",parameterMap);
        }
        else{
            resutMap.put("status",false);
            resutMap.put("msg",return_msg);
        }

        return resutMap;
    }


    /**
     * 提现放款
     * @param usersWithdrawOrder
     * @param request
     * @param response
     * @return
     */
    public static Map<String,Object> wechatCashCash(UsersWithdrawOrder usersWithdrawOrder, HttpServletRequest request, HttpServletResponse response){
        String prepay_id="";
        String packages = "prepay_id="+prepay_id;
        //随机数
        String nonce_str= UUDUtil.getOrderIdByUUId();

        String notify_url = "http://m.chenlankeji.cn/weChat/payCallback";
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("mch_id", WeChatConfig.MCHID);
        data.put("partner_trade_no", usersWithdrawOrder.getOrder_no());
        data.put("nonce_str", nonce_str);
        data.put("enc_bank_no", usersWithdrawOrder.getBank_card_number());
        data.put("enc_true_name", usersWithdrawOrder.getBank_card_name());
        data.put("bank_code", usersWithdrawOrder.getBack_code());
        Double total_fee = usersWithdrawOrder.getReal_payment_rmb_amount()*100;
        data.put("amount", String.valueOf(total_fee.intValue()));
        data.put("desc", usersWithdrawOrder.getBank_card_name()+"用户提现");

        RequestHandler reqHandler = new RequestHandler(request, response);
        reqHandler.init( WXPublicSettingUtil.appId,  WXPublicSettingUtil.appSecret,  WeChatConfig.API_KEY);
        SortedMap<String, Object> finalpackage = new TreeMap<String, Object>();
        finalpackage.put("mch_id", WeChatConfig.MCHID);
        finalpackage.put("partner_trade_no", usersWithdrawOrder.getOrder_no());
        finalpackage.put("nonce_str", nonce_str);
        finalpackage.put("enc_bank_no", usersWithdrawOrder.getBank_card_number());
        finalpackage.put("enc_true_name", usersWithdrawOrder.getBank_card_name());
        finalpackage.put("bank_code", usersWithdrawOrder.getBack_code());
        finalpackage.put("amount", String.valueOf(total_fee.intValue()));
        finalpackage.put("desc", usersWithdrawOrder.getBank_card_name()+"用户提现");

        String finalsign = reqHandler.createSign(finalpackage);
        data.put("sign", finalsign);

        String paramts = JSONObject.fromObject(data).toString();

        WXPayConfigImpl config = null;
        try {
            config = WXPayConfigImpl.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        WXPay wxpay = null;
        try {
            wxpay = new WXPay(config);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String return_code = "";
        String return_msg = "";
        String payment_no = "";
        try {
            Map<String, String> r = wxpay.withdrawToBankCard(data);
            return_code = r.get("return_code");
            if (return_code.equals("SUCCESS")){
                if (r.get("result_code").equals("SUCCESS")){
                    return_code = "SUCCESS";
                    payment_no = r.get("payment_no");
                }
                else{
                    return_code = "FAIL";
                    String err_code = r.get("err_code");
                    if (err_code.equals("INVALID_REQUEST")){
                        return_msg = "无效的请求";
                    }
                    else if (err_code.equals("SYSTEMERROR")){
                        return_msg = "业务错误导致交易失败";
                    }
                    else if (err_code.equals("PARAM_ERROR")){
                        return_msg = "参数错误";
                    }
                    else if (err_code.equals("SIGNERROR")){
                        return_msg = "签名错误";
                    }
                    else if (err_code.equals("AMOUNT_LIMIT")){
                        return_msg = "超额；已达到今日付款金额上限或已达到今日银行卡收款金额上限";
                    }
                    else if (err_code.equals("ORDERPAID")){
                        return_msg = "超过付款重入有效期";
                    }
                    else if (err_code.equals("FATAL_ERROR")){
                        return_msg = "已存在该单，并且订单信息不一致；或订单太老";
                    }
                    else if (err_code.equals("NOTENOUGH")){
                        return_msg = "余额不足";
                    }
                    else if (err_code.equals("FREQUENCY_LIMITED")){
                        return_msg = "超过每分钟600次的频率限制";
                    }
                    else if (err_code.equals("FREQUENCY_LIMITED")){
                        return_msg = "超过每分钟600次的频率限制";
                    }
                    else{
                        return_msg = r.get("err_code_des");
                    }

                }
            }
            else{
                return_code = "FAIL";
                return_msg = r.get("return_msg");
            }
        } catch (Exception e) {
            return_code = "FAIL";
            return_msg = e.getMessage();
        }

        Map<String,Object> resutMap = new HashMap<String,Object>();
        resutMap.put("return_code",return_code);
        resutMap.put("return_msg",return_msg);
        resutMap.put("payment_no",payment_no);
        return resutMap;
    }


    /**
     * 万能卡商家提现放款
     * @param wnkBusinessWithdrawOrder
     * @param request
     * @param response
     * @return
     */
    public static Map<String,Object> wechatCashCashWnkBusiness(WnkBusinessWithdrawOrder wnkBusinessWithdrawOrder, HttpServletRequest request, HttpServletResponse response){
        String prepay_id="";
        String packages = "prepay_id="+prepay_id;
        //随机数
        String nonce_str= UUDUtil.getOrderIdByUUId();

        String notify_url = "http://m.chenlankeji.cn/weChat/payCallback";
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("mch_id", WeChatConfig.MCHID);
        data.put("partner_trade_no", wnkBusinessWithdrawOrder.getOrder_no());
        data.put("nonce_str", nonce_str);
        data.put("enc_bank_no", wnkBusinessWithdrawOrder.getBank_card_number());
        data.put("enc_true_name", wnkBusinessWithdrawOrder.getBank_card_name());
        data.put("bank_code", wnkBusinessWithdrawOrder.getBack_code());
        Double total_fee = wnkBusinessWithdrawOrder.getReal_payment_rmb_amount()*100;
        data.put("amount", String.valueOf(total_fee.intValue()));
        data.put("desc", wnkBusinessWithdrawOrder.getBank_card_name()+"商家提现");

        RequestHandler reqHandler = new RequestHandler(request, response);
        reqHandler.init( WXPublicSettingUtil.appId,  WXPublicSettingUtil.appSecret,  WeChatConfig.API_KEY);
        SortedMap<String, Object> finalpackage = new TreeMap<String, Object>();
        finalpackage.put("mch_id", WeChatConfig.MCHID);
        finalpackage.put("partner_trade_no", wnkBusinessWithdrawOrder.getOrder_no());
        finalpackage.put("nonce_str", nonce_str);
        finalpackage.put("enc_bank_no", wnkBusinessWithdrawOrder.getBank_card_number());
        finalpackage.put("enc_true_name", wnkBusinessWithdrawOrder.getBank_card_name());
        finalpackage.put("bank_code", wnkBusinessWithdrawOrder.getBack_code());
        finalpackage.put("amount", String.valueOf(total_fee.intValue()));
        finalpackage.put("desc", wnkBusinessWithdrawOrder.getBank_card_name()+"商家提现");

        String finalsign = reqHandler.createSign(finalpackage);
        data.put("sign", finalsign);

        String paramts = JSONObject.fromObject(data).toString();

        WXPayConfigImpl config = null;
        try {
            config = WXPayConfigImpl.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        WXPay wxpay = null;
        try {
            wxpay = new WXPay(config);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String return_code = "";
        String return_msg = "";
        String payment_no = "";
        try {
            Map<String, String> r = wxpay.withdrawToBankCard(data);
            return_code = r.get("return_code");
            if (return_code.equals("SUCCESS")){
                if (r.get("result_code").equals("SUCCESS")){
                    return_code = "SUCCESS";
                    payment_no = r.get("payment_no");
                }
                else{
                    return_code = "FAIL";
                    String err_code = r.get("err_code");
                    if (err_code.equals("INVALID_REQUEST")){
                        return_msg = "无效的请求";
                    }
                    else if (err_code.equals("SYSTEMERROR")){
                        return_msg = "业务错误导致交易失败";
                    }
                    else if (err_code.equals("PARAM_ERROR")){
                        return_msg = "参数错误";
                    }
                    else if (err_code.equals("SIGNERROR")){
                        return_msg = "签名错误";
                    }
                    else if (err_code.equals("AMOUNT_LIMIT")){
                        return_msg = "超额；已达到今日付款金额上限或已达到今日银行卡收款金额上限";
                    }
                    else if (err_code.equals("ORDERPAID")){
                        return_msg = "超过付款重入有效期";
                    }
                    else if (err_code.equals("FATAL_ERROR")){
                        return_msg = "已存在该单，并且订单信息不一致；或订单太老";
                    }
                    else if (err_code.equals("NOTENOUGH")){
                        return_msg = "余额不足";
                    }
                    else if (err_code.equals("FREQUENCY_LIMITED")){
                        return_msg = "超过每分钟600次的频率限制";
                    }
                    else if (err_code.equals("FREQUENCY_LIMITED")){
                        return_msg = "超过每分钟600次的频率限制";
                    }
                    else{
                        return_msg = r.get("err_code_des");
                    }

                }
            }
            else{
                return_code = "FAIL";
                return_msg = r.get("return_msg");
            }
        } catch (Exception e) {
            return_code = "FAIL";
            return_msg = e.getMessage();
        }

        Map<String,Object> resutMap = new HashMap<String,Object>();
        resutMap.put("return_code",return_code);
        resutMap.put("return_msg",return_msg);
        resutMap.put("payment_no",payment_no);
        return resutMap;
    }


    /**
     * 万能卡商品下单
     * @param order_no
     * @param amount
     * @param user_openId
     * @param request
     * @param response
     * @return
     */
    public static Map<String,Object> wechatPayWnkCommodity(String order_no, Double amount, String user_openId, HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> resutMap = new HashMap<String,Object>();

        String prepay_id="";
        String packages = "prepay_id="+prepay_id;
        //随机数
        String nonce_str= UUDUtil.getOrderIdByUUId();

        String notify_url = "http://m.chenlankeji.cn/weChat/wnkCommodityWXPayCallback";
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("body", "商品购买");
        data.put("out_trade_no", order_no);
        data.put("device_info", "");
        data.put("fee_type", "CNY");
        Double total_fee = amount*100;
        data.put("total_fee", String.valueOf(total_fee.intValue()));
        data.put("spbill_create_ip", "101.201.67.30");
        data.put("notify_url", notify_url);
        data.put("trade_type", "JSAPI");
        data.put("openid", user_openId);
        data.put("product_id", order_no);
        WXPayConfigImpl config = null;
        try {
            config = WXPayConfigImpl.getInstance();
        } catch (Exception e) {
            resutMap.put("status",false);
            resutMap.put("msg",e.getMessage());
            e.printStackTrace();
        }
        WXPay wxpay = null;
        try {
            wxpay = new WXPay(config);
        } catch (Exception e) {
            resutMap.put("status",false);
            resutMap.put("msg",e.getMessage());
            e.printStackTrace();
        }
        String return_code = "";
        String return_msg = "";
        try {
            Map<String, String> r = wxpay.unifiedOrder(data);
            return_code = r.get("return_code");
            return_msg = r.get("return_msg");
            if (return_code.equals("SUCCESS")){
                if (r.get("result_code").equals("SUCCESS")){
                    packages = "prepay_id="+r.get("prepay_id");
                }
                else{
                    resutMap.put("status",false);
                    resutMap.put("msg","下单失败");
                }
            }
            else{
                resutMap.put("status",false);
                resutMap.put("msg",return_msg);
            }
            System.out.println("微信支付下单结果="+r.toString());
        } catch (Exception e) {
            return_code = "FAIL";
            return_msg = e.getMessage();
            resutMap.put("status",false);
            resutMap.put("msg",return_msg);
            //e.printStackTrace();
        }
        if (return_code.equals("SUCCESS")){
            RequestHandler reqHandler = new RequestHandler(request, response);
            reqHandler.init(  WXPublicSettingUtil.appId,  WXPublicSettingUtil.appSecret,  "CL201807241658111133123CL0000100");
                    /*------7.将预支付订单的id和其他信息生成签名并一起返回到jsp页面 ------- */
            SortedMap<String, Object> finalpackage = new TreeMap<String, Object>();
            String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
            finalpackage.put("appId",  WXPublicSettingUtil.appId);
            finalpackage.put("timeStamp", timestamp);
            finalpackage.put("nonceStr", nonce_str);
            finalpackage.put("package", packages);
            finalpackage.put("signType", "MD5");
            String finalsign = reqHandler.createSign(finalpackage);
            Map<String,String> map = new HashMap<String,String>();
            map.put("appid",WXPublicSettingUtil.appId);
            map.put("timeStamp",timestamp);
            map.put("nonceStr",nonce_str);
            map.put("packageValue",packages);
            map.put("paySign",finalsign);
            map.put("order_no",order_no);
            resutMap.put("status",true);
            resutMap.put("wx_config",map);
        }
        else{
            resutMap.put("status",false);
            resutMap.put("msg",return_msg);
        }

        return resutMap;
    }



    /**
     * 用户办理商家会员卡微信App下单支付
     * @param order_no
     * @param amount
     * @param request
     * @param response
     * @return
     */
    public static Map<String,Object> wechatPayBuyBusinessMemberCardWXAppPay(String order_no, Double amount, HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> resutMap = new HashMap<String,Object>();

        String prepay_id="";
        String packages = "prepay_id="+prepay_id;
        //随机数
        String nonce_str= UUDUtil.getOrderIdByUUId();

        Double total_fee = amount*100;
        String notify_url = "http://m.chenlankeji.cn/weChat/wechatPayBuyBusinessMemberCardWXAppPayCallback";
        //交易类型 app支付
        String trade_type = "APP";
        SortedMap<String,Object> packageParams = new TreeMap<String,Object>();
        packageParams.put("appid", WeChatConfig.MCH_USER_APP_APPID);
        packageParams.put("mch_id", WeChatConfig.MCHID_USER_APP);
        packageParams.put("nonce_str", nonce_str);
        packageParams.put("body", "办理商家会员卡");
        packageParams.put("out_trade_no", order_no);
        packageParams.put("total_fee",  String.valueOf(total_fee.intValue())); //价格的单位为分
        packageParams.put("spbill_create_ip", "101.201.67.30");
        packageParams.put("notify_url", notify_url);
        packageParams.put("trade_type", trade_type);
        RequestHandler reqHandler = new RequestHandler(request, response);
        reqHandler.init(  WeChatConfig.MCH_USER_APP_APPID,  WeChatConfig.SECRET_USER_APP,  WeChatConfig.API_KEY_USER);
        String finalsign = reqHandler.createSign(packageParams);
        packageParams.put("sign", finalsign);


        HashMap<String, String> data = new HashMap<String, String>();
        data.put("appid", WeChatConfig.MCH_USER_APP_APPID);
        data.put("mch_id", WeChatConfig.MCHID_USER_APP);
        data.put("nonce_str", nonce_str);
        data.put("body", "办理商家会员卡");
        data.put("out_trade_no", order_no);
        data.put("total_fee",  String.valueOf(total_fee.intValue())); //价格的单位为分
        data.put("spbill_create_ip", "101.201.67.30");
        data.put("notify_url", notify_url);
        data.put("trade_type", trade_type);
        data.put("sign", finalsign);
        WXPayConfigImplUserApp config = null;
        try {
            config = WXPayConfigImplUserApp.getInstance();
        } catch (Exception e) {
            resutMap.put("status",false);
            resutMap.put("msg",e.getMessage());
            e.printStackTrace();
        }
        WXPay wxpay = null;
        try {
            wxpay = new WXPay(config);
        } catch (Exception e) {
            resutMap.put("status",false);
            resutMap.put("msg",e.getMessage());
            e.printStackTrace();
        }
        String return_code = "";
        String return_msg = "";

        try {
            Map<String, String> r = wxpay.unifiedOrder(data);
            return_code = r.get("return_code");
            return_msg = r.get("return_msg");
            if (return_code.equals("SUCCESS")){
                if (r.get("result_code").equals("SUCCESS")){
                    packages = r.get("prepay_id");
                    nonce_str = r.get("nonce_str");
                }
                else{
                    resutMap.put("status",false);
                    resutMap.put("msg","下单失败");
                }
            }
            else{
                resutMap.put("status",false);
                resutMap.put("msg",return_msg);
            }
            System.out.println("微信支付下单结果="+r.toString());
        } catch (Exception e) {
            return_code = "FAIL";
            return_msg = e.getMessage();
            resutMap.put("status",false);
            resutMap.put("msg",return_msg);
            //e.printStackTrace();
        }
        if (return_code.equals("SUCCESS")){
            SortedMap<String, Object> parameterMap = new TreeMap<String, Object>();
            parameterMap.put("appid", WeChatConfig.MCH_USER_APP_APPID);
            parameterMap.put("partnerid", WeChatConfig.MCHID_USER_APP);
            parameterMap.put("prepayid", packages);
            parameterMap.put("package", "Sign=WXPay");
            parameterMap.put("noncestr", nonce_str);
            int mis = Integer.parseInt(String.valueOf(System.currentTimeMillis()).toString().substring(0, 10));
            parameterMap.put("timestamp",mis);
            finalsign = reqHandler.createSign(parameterMap);
            parameterMap.put("sign", finalsign);

            resutMap.put("status",true);
            resutMap.put("wx_config",parameterMap);
        }
        else{
            resutMap.put("status",false);
            resutMap.put("msg",return_msg);
        }

        return resutMap;
    }
}
