package com.springmvc.utils.WX;

import com.springmvc.utils.HttpRequest;
import com.springmvc.utils.WechatEnterprisePay.WeChatConfig;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class WechatUtil {
    /*
    * 生成获取微信用户openid、refresh_token请求参数
    * */
    public static String obtainOpenIdParamts(String code){
        String param = "appid="+WXPublicSettingUtil.appId+"&secret="+WXPublicSettingUtil.appSecret+"&code="+code+"&grant_type=authorization_code";
        return param;
    }

    //获取公众号access_token
    public static String obtainAccessToken(){
        String paramts = "grant_type=client_credential&appid="+ WXPublicSettingUtil.appId+"&secret="+WXPublicSettingUtil.appSecret;
        String data= HttpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/token", paramts);
        JSONObject dataJson = JSONObject.fromObject(data);
        System.out.println("token获取结果："+dataJson.toString());
        if (dataJson.get("errcode") == null){
            return (String)dataJson.get("access_token");
        }
        else{
            return null;
        }
    }
}
