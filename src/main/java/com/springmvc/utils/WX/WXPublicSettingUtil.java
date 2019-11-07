package com.springmvc.utils.WX;

public class WXPublicSettingUtil {
    //appid
    public static final String appId = "wxdaa65edb93ab977f";
    //appSecret
    public static final String appSecret = "4703fe0b646901eccacd275402035cc3";
    public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+WXPublicSettingUtil.appId+"&secret="+WXPublicSettingUtil.appSecret;
}
