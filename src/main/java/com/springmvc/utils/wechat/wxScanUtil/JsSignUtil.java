package com.springmvc.utils.wechat.wxScanUtil;


import com.springmvc.utils.SHA1;
import com.springmvc.utils.UUDUtil;
import com.springmvc.utils.WX.WXPublicSettingUtil;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;


public class JsSignUtil {

    public static String accessToken = null;

    public static Map<String, String> sign(String url) throws IOException {
        JSONObject accesTokenObject = AuthUtil.doGetJson(WXPublicSettingUtil.ACCESS_TOKEN_URL);
        String accesToken = (String) accesTokenObject.get("access_token");
        System.out.println("微信返回accesTokenObject"+accesTokenObject);
        JSONObject jsapiTicketObject = AuthUtil.doGetJson("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + accesToken + "&type=jsapi");
        String jsapiTicket = (String) jsapiTicketObject.get("ticket");
        System.out.println("微信返回jsapiTicketObject"+jsapiTicketObject);
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapiTicket +
                "&noncestr=" + nonce_str +
                "&timestamp=" + timestamp +
                "&url=" + url;
        System.out.println("string1="+string1);

        signature = SHA1.encode(string1);

        ret.put("url", url);
        ret.put("jsapi_ticket", jsapiTicket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);
        ret.put("appId", WXPublicSettingUtil.appId);

        System.out.println("1.ticket(原始)="+jsapiTicket);
        System.out.println("2.url="+ret.get("url"));
        System.out.println("3.jsapi_ticket（处理后）="+ret.get("jsapi_ticket"));
        System.out.println("4.nonceStr="+ret.get("nonceStr"));
        System.out.println("5.signature="+ret.get("signature"));
        System.out.println("6.timestamp="+ret.get("timestamp"));

        return ret;
    }


    /**
     * 随机加密
     * @param hash
     * @return
     */
    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

    /**
     * 产生随机串--由程序自己随机产生
     * @return
     */
    private static String create_nonce_str() {
        return UUDUtil.getOrderIdByUUId();
    }

    /**
     * 由程序自己获取当前时间
     * @return
     */
    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
}
