package com.springmvc.utils.WX;

import java.util.Map;
import java.util.HashMap;

import com.springmvc.utils.HttpRequest;
import com.springmvc.utils.WechatEnterprisePay.WeChatConfig;
import net.sf.json.JSONObject;
import java.util.Iterator;
import java.util.UUID;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.InputStream;

public class JSSDKSign {

    public static String sign(String noncestr,String timestamp,String url){
//        String noncestr = UUID.randomUUID().toString();
//        String timestamp = Long.toString(System.currentTimeMillis() / 1000);
//        String url="http://mp.weixin.qq.com";

        //获取access_token
        Map<String, String> params = new HashMap<String, String>();
        params.put("corpid", WXPublicSettingUtil.appId);
        params.put("corpsecret",WXPublicSettingUtil.appSecret);
        String tokenData = HttpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/token","grant_type=client_credential&appid="+WeChatConfig.MCH_APPID+"&secret="+WeChatConfig.SECRET);
        JSONObject jsonMap  = JSONObject.fromObject(tokenData);
        String access_token = (String)jsonMap.get("access_token");
        System.out.println("access_token=" + access_token);

        String jsapi_ticket = null;
        String jsapi_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+ access_token +"&type=jsapi";//这个url链接和参数不能变
        try {
            URL urlGet = new URL(jsapi_url);
            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
            http.setRequestMethod("GET"); // 必须是get方式请求
            http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
            System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
            http.connect();
            InputStream is = http.getInputStream();
            int size = is.available();
            byte[] jsonBytes = new byte[size];
            is.read(jsonBytes);
            String message = new String(jsonBytes, "UTF-8");
            JSONObject demoJson = JSONObject.fromObject(message);
            System.out.println("JSON字符串："+demoJson);
            jsapi_ticket = demoJson.getString("ticket");
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("jsapi_ticket=" + jsapi_ticket);

        //获取签名signature
        String str = "jsapi_ticket=" + jsapi_ticket +
                "&noncestr=" + noncestr +
                "&timestamp=" + timestamp +
                "&url=" + url;
        //sha1加密
        String signature = SHA1.SHA1(str);

        return signature;

        //最终获得调用微信js接口验证需要的三个参数noncestr、timestamp、signature
    }
}
