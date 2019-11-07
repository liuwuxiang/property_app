package com.springmvc.utils;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.style.Style0;
import com.gexin.rp.sdk.base.impl.Target;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhangfan
 * @Date: 2018/12/18 23:06
 * @Description:用户端个推工具类
 */
public class GeTuiUserUtil {
    //定义常量, appId、appKey、masterSecret 采用本文档 "第二步 获取访问凭证 "中获得的应用配置
    private static String appId = "PAIOV2W2p27THgKzy8Y4d";
    private static String appKey = "H3c1YTEPqB9SF2UZ4H9y18";
    private static String masterSecret = "6IqR25VMn78eqstM4k96L";
    private static String host = "http://sdk.open.api.igexin.com/apiex.htm";

    //推送通知
    public static void pushUser(String user_appId,String push_title,String push_content){
        // 配置返回每个用户返回用户状态，可选
        System.setProperty("gexin_pushList_needDetails", "true");
        // 配置返回每个别名及其对应cid的用户状态，可选
        // System.setProperty("gexin_pushList_needAliasDetails", "true");
        IGtPush push = new IGtPush(GeTuiUserUtil.host, GeTuiUserUtil.appKey, GeTuiUserUtil.masterSecret);
        // 通知透传模板
        NotificationTemplate template = notificationTemplateDemo(push_title,push_content);
        ListMessage message = new ListMessage();
        message.setData(template);
        // 设置消息离线，并设置离线时间
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 1000 * 3600);
        // 配置推送目标
        List targets = new ArrayList();
        Target target1 = new Target();
        target1.setAppId(GeTuiUserUtil.appId);
        target1.setClientId(user_appId);
        //     target1.setAlias(Alias1);
        targets.add(target1);
        // taskId用于在推送时去查找对应的message
        String taskId = push.getContentId(message);
        IPushResult ret = push.pushMessageToList(taskId, targets);
        System.out.println(ret.getResponse().toString());
    }

    public static NotificationTemplate notificationTemplateDemo(String push_title,String push_content) {
        NotificationTemplate template = new NotificationTemplate();
        // 设置APPID与APPKEY
        template.setAppId(GeTuiUserUtil.appId);
        template.setAppkey(GeTuiUserUtil.appKey);

        Style0 style = new Style0();
        // 设置通知栏标题与内容
        style.setTitle(push_title);
        style.setText(push_content);
        // 配置通知栏图标
        style.setLogo("icon.png");
        // 配置通知栏网络图标
        style.setLogoUrl("");
        // 设置通知是否响铃，震动，或者可清除
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);
        template.setStyle(style);

        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(1);
        //要透传的内容
        template.setTransmissionContent(push_content);
        return template;
    }
}
