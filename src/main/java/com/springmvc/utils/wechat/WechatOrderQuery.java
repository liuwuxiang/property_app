package com.springmvc.utils.wechat;

import com.springmvc.utils.UUDUtil;
import com.springmvc.utils.wxpay.sdk.WXPay;
import com.springmvc.utils.wxpay.sdk.WXPayConfigImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class WechatOrderQuery {

    /**
     * 查询订单支付状态(返回SUCCESS为支付成功)
     * @param order_no
     * @param request
     * @param response
     * @return
     */
    public static String orderPayResultQuery(String order_no, HttpServletRequest request, HttpServletResponse response){
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("appid", "wx7a68f27919c16340");
        data.put("mch_id", "1499871772");
        data.put("out_trade_no", order_no);
        data.put("nonce_str", UUDUtil.getOrderIdByUUId());
        data.put("sign_type", "MD5");

        RequestHandler reqHandler = new RequestHandler(request, response);
        reqHandler.init( "wx7a68f27919c16340",  "730d40003144650c444aa2d7f8c30d1a",  "CL201807241658111133123CL0000100");
                    /*------7.将预支付订单的id和其他信息生成签名并一起返回到jsp页面 ------- */
        SortedMap<String, Object> finalpackage = new TreeMap<String, Object>();
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        finalpackage.put("appid",  data.get("appid"));
        finalpackage.put("mch_id", data.get("mch_id"));
        finalpackage.put("out_trade_no", data.get("out_trade_no"));
        finalpackage.put("nonce_str", data.get("nonce_str"));
        finalpackage.put("sign_type", data.get("sign_type"));
        String finalsign = reqHandler.createSign(finalpackage);
        data.put("sign", finalsign);

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
        //支付状态(SUCCESS—支付成功,REFUND—转入退款,NOTPAY—未支付,CLOSED—已关闭,REVOKED—已撤销（刷卡支付）,USERPAYING--用户支付中,PAYERROR--支付失败(其他原因，如银行返回失败))
        String trade_state = "NOTPAY";
        try {
            Map<String, String> r = wxpay.orderQuery(data);
            return_code = r.get("return_code");
            if (return_code.equals("SUCCESS")){
                if (r.get("result_code").equals("SUCCESS")){
                    trade_state = r.get("trade_state");
                }
            }
            System.out.println(r);
        } catch (Exception e) {
            return_code = "FAIL";
            return_msg = e.getMessage();
        }

        return trade_state;
    }
}
