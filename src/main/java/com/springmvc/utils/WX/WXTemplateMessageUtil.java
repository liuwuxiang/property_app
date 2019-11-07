package com.springmvc.utils.WX;

import com.springmvc.utils.HttpRequest;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

//发送微信模板消息
public class WXTemplateMessageUtil {
    //模板id
    private static final String templateId = "2Yq4CdfJOMqsZJcHS3IaklOp8oqiwkPKzejQrmvBFc4";
    //发送模板消息url
    private static final String sendTemplateMessageUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";
    //模板消息点击跳转url
    private static final String jumpUrl  = "http://course.kmser.com/experts/wechatLoginCheck";

    //发送模板消息方法(open_id-用户openid,problem-问题名称,time_str-提问事件)
    public static void sendMessage(String open_id,String problem,String time_str){
        Map<String,Object> paramtsMap = new HashMap<String,Object>();
        Map<String,Object> dataMap = new HashMap<String,Object>();
        Map<String,Object> firstMap = new HashMap<String,Object>();
        Map<String,Object> keyword1Map = new HashMap<String,Object>();
        Map<String,Object> keyword2Map = new HashMap<String,Object>();
        Map<String,Object> remarkMap = new HashMap<String,Object>();
        paramtsMap.put("touser",open_id);
        paramtsMap.put("template_id",templateId);
        paramtsMap.put("url",jumpUrl);
        paramtsMap.put("data",dataMap);
        firstMap.put("value","用户发起一个新问题待解答");
        firstMap.put("color","#173177");
        dataMap.put("first",firstMap);
        keyword1Map.put("value",problem);
        keyword1Map.put("color","#757575");
        dataMap.put("keyword1",keyword1Map);
        keyword2Map.put("value",time_str);
        keyword2Map.put("color","#757575");
        dataMap.put("keyword2",keyword2Map);
        remarkMap.put("value","点击进入解答");
        remarkMap.put("color","#173177");
        dataMap.put("remark",remarkMap);

        //获取access_token
        String access_token = WechatUtil.obtainAccessToken();
        if (access_token != null){
            JSONObject jsonObject = JSONObject.fromObject(paramtsMap);
            String data= HttpRequest.sendPost(sendTemplateMessageUrl+access_token, jsonObject.toString());
            JSONObject resultJson = JSONObject.fromObject(data);
            if ((Integer)resultJson.get("errcode") == 0){
                System.out.println("-------模板消息发送成功!");
            }
            else{
                System.out.println("-------模板消息发送失败!"+resultJson.get("errmsg"));
            }
        }
    }

}
