package com.springmvc.utils.wxpay.sdk;

import com.springmvc.utils.WX.WXPublicSettingUtil;
import com.springmvc.utils.WechatEnterprisePay.WeChatConfig;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class WXPayConfigImplUserApp extends WXPayConfig{

    private byte[] certData;
    private static WXPayConfigImplUserApp INSTANCE;

    public WXPayConfigImplUserApp() throws Exception{
        String certPath = WeChatConfig.CA_LICENSE_USER_APP_PEM;
        File file = new File(certPath);
        InputStream certStream = new FileInputStream(file);
        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);
        certStream.close();
    }

    public WXPayConfigImplUserApp(Integer state) throws Exception{
        String certPath = WeChatConfig.CA_LICENSE_USER_APP_P12;
        File file = new File(certPath);
        InputStream certStream = new FileInputStream(file);
        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);
        certStream.close();
    }

    public static WXPayConfigImplUserApp getInstance() throws Exception{
        if (INSTANCE == null) {
            synchronized (WXPayConfigImplUserApp.class) {
                if (INSTANCE == null) {
                    INSTANCE = new WXPayConfigImplUserApp();
                }
            }
        }
        return INSTANCE;
    }

    public static WXPayConfigImplUserApp getInstance(Integer state) throws Exception{
        if (INSTANCE == null) {
            synchronized (WXPayConfigImplUserApp.class) {
                if (INSTANCE == null) {
                    INSTANCE = new WXPayConfigImplUserApp(state);
                }
            }
        }
        return INSTANCE;
    }

    public String getAppID() {
        return WeChatConfig.MCH_USER_APP_APPID;
    }

    public String getMchID() {
        return WeChatConfig.MCHID_USER_APP;
    }

    public String getKey() {
        return WeChatConfig.API_KEY_USER;
    }

    public InputStream getCertStream() {
        ByteArrayInputStream certBis;
        certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }


    public int getHttpConnectTimeoutMs() {
        return 2000;
    }

    public int getHttpReadTimeoutMs() {
        return 10000;
    }

    IWXPayDomain getWXPayDomain() {
        return WXPayDomainSimpleImpl.instance();
    }

    public String getPrimaryDomain() {
        return "api.mch.weixin.qq.com";
    }

    public String getAlternateDomain() {
        return "api2.mch.weixin.qq.com";
    }

    @Override
    public int getReportWorkerNum() {
        return 1;
    }

    @Override
    public int getReportBatchSize() {
        return 2;
    }
}
