package com.springmvc.utils.WechatEnterprisePay;

import com.springmvc.utils.WX.WXPublicSettingUtil;

public class WeChatConfig {
	//请求接口
	public static final String  POST_URL ="https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
	public static final String  CHARSET = "UTF-8";//编码格式
	//证书存放目录
	public static final String  CA_LICENSE = "/usr/local/property_system/apiclient_cert.p12";
//	public static final String  CA_LICENSE = "/Users/zhangfan/Downloads/cert/apiclient_cert.p12";

	//注意商户平台需要与微信公众号有关联。通常申请流程是从公众号->微信支付，进入申请
	//商户apikey
	public static final String  API_KEY = "CL201807241658111133123CL0000100";
	//商户平台：https://pay.weixin.qq.com/index.php/home/login
	//商户平台号
	public static final String  MCHID = "1507781641";

	
	//微信公众平台appid
	public static final String  MCH_APPID = WXPublicSettingUtil.appId;

	//微信公众平台secret
	public static final String  SECRET = WXPublicSettingUtil.appSecret;
	//公众号网址：https://mp.weixin.qq.com/


	//用户版App支付信息
	//App支付appid
	public static final String  MCH_USER_APP_APPID = "wxd787de2c34c3e72b";
	//secret
	public static final String  SECRET_USER_APP = "02be4555f6fa720aa709bdc71e64bc01";
	//商户平台号
	public static final String  MCHID_USER_APP = "1518908251";
	//商户apikey
	public static final String  API_KEY_USER = "CL201807241658111133123CL0000100";
	//微信支付证书P12文件
//	public static final String  CA_LICENSE_USER_APP_P12 = "/svn/company/zhangfan/项目/物业App/微信App支付/user/apiclient_cert.p12";
	public static final String  CA_LICENSE_USER_APP_P12 = "/usr/local/property_system/cert/userApp/apiclient_cert.p12";
	//微信支付证书PEM文件
//	public static final String  CA_LICENSE_USER_APP_PEM = "/svn/company/zhangfan/项目/物业App/微信App支付/user/apiclient_cert.pem";
	public static final String  CA_LICENSE_USER_APP_PEM = "/usr/local/property_system/cert/userApp/apiclient_cert.pem";



	//商户版App支付信息
	//App支付appid
	public static final String  MCH_BUSINESS_APP_APPID = "wx93f34f6ebec3f69c";
	//secret
	public static final String  SECRET_BUSINESS_APP = "fb97e87759dc07af70643569cb8231a5";
	//商户平台号
	public static final String  MCHID_BUSINESS_APP = "1518998681";
	//商户apikey
	public static final String  API_KEY_BUSINESS = "CL201807241658111133123CL0000100";
	//微信支付证书P12文件
//	public static final String  CA_LICENSE_BUSINESS_APP_P12 = "/svn/company/zhangfan/项目/物业App/微信App支付/business/apiclient_cert.p12";
	public static final String  CA_LICENSE_BUSINESS_APP_P12 = "/usr/local/property_system/cert/businessApp/apiclient_cert.p12";
	//微信支付证书PEM文件
//	public static final String  CA_LICENSE_BUSINESS_APP_PEM = "/svn/company/zhangfan/项目/物业App/微信App支付/business/apiclient_cert.pem";
	public static final String  CA_LICENSE_BUSINESS_APP_PEM = "/usr/local/property_system/cert/businessApp/apiclient_cert.pem";
	
}
