package com.springmvc.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * 阿里云短信发送工具类
 */
public class MobileCodeUtil {

    /**产品名称:云通信短信API产品,开发者无需替换*/
    static final String product = "Dysmsapi";
    /**产品域名,开发者无需替换*/
    static final String domain = "dysmsapi.aliyuncs.com";

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId = "LTAIYM6HqOpalZw5";
    static final String accessKeySecret = "KmyV5fb7O1IzLfhtxXlF1dQFGUSkpp";

    public static SendSmsResponse sendSms(String code,String mobile) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(mobile);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("云南辰蓝网络科技");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_137665964");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"code\":\""+code+"\"}");

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return sendSmsResponse;
    }


    //发送账号注册审核通过通知
    public static SendSmsResponse sendAccountRegisterPassSms(String account,String passwd) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(account);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("云南辰蓝网络科技");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_150740524");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"account\":\""+account+"\",\"passwd\":\""+passwd+"\"}");

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return sendSmsResponse;
    }


    //发送账号注册审核未通过通知
    public static SendSmsResponse sendAccountRegisterNoPassSms(String account,String reason) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(account);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("云南辰蓝网络科技");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_150740532");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"account\":\""+account+"\",\"reason\":\""+reason+"\"}");

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return sendSmsResponse;
    }

    //发送账号认证审核通过通知
    public static SendSmsResponse sendAccountAuthenticationPassSms(String account) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(account);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("云南辰蓝网络科技");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_150735578");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"account\":\""+account+"\"}");

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return sendSmsResponse;
    }


    //发送账号认证审核未通过通知
    public static SendSmsResponse sendAccountAuthenticationNoPassSms(String account,String reason) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(account);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("云南辰蓝网络科技");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_150735579");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"account\":\""+account+"\",\"reason\":\""+reason+"\"}");

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return sendSmsResponse;
    }


    /**
     *
     * 功能描述: 发送赠送物料成功短信通知
     *
     * @param   account        手机号码
     * @param   giftNumber     赠送数量
     * @param   extensionName  物料名称
     * @return  com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse
     * @author  杨新杰
     * @date    10:32 2019/1/5
     */
    public static SendSmsResponse sendGiftExtensionSms(String account,String extensionName,Integer giftNumber) throws ClientException {


        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(account);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("云南辰蓝网络科技");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_154961419");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"extension_name\":\""+extensionName+"\",\"gift_number\":\""+giftNumber+"\"}");

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return sendSmsResponse;
    }


    /**
     *
     * 功能描述: 商家等级升级通知
     *
     * @param mobile 接收短信手机号
     * @param pay_type 支付方式(0-微信支付,1-余额支付)
     * @param pay_amount 支付金额
     * @param level_name 等级名称
     * @param get_integral 获得积分
     * @return:
     * @author: zhangfan
     * @date: 2018/12/18 3:24 PM
     */
    public static SendSmsResponse sendBusinessLevelUpgradeSms(String mobile,Integer pay_type,Double pay_amount,String level_name,Double get_integral) throws ClientException {

        String pay_way = "微信支付";
        if (pay_type == 0){
            pay_way = "微信支付";
        }
        else{
            pay_way = "余额支付";
        }

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(mobile);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("云南辰蓝网络科技");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_152825140");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"pay_type\":\""+pay_way+"\",\"pay_amount\":\""+pay_amount+"\",\"level_name\":\""+level_name+"\",\"get_integral\":\""+get_integral+"\"}");

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return sendSmsResponse;
    }

    /**
     *
     * 功能描述: 用户端设置登录密码短信验证码
     *
     * @param mobile 接收短信手机号
     * @param code 短信验证码
     * @return:
     * @author: zhangfan
     * @date: 2018/12/18 3:24 PM
     */
    public static SendSmsResponse sendUserSetLoginPWDCodeSms(String mobile,String code) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(mobile);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("云南辰蓝网络科技");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_152825112");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"code\":\""+code+"\"}");

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return sendSmsResponse;
    }

    /**
     *
     * 功能描述: 用户端设置支付密码短信验证码
     *
     * @param mobile 接收短信手机号
     * @param code 短信验证码
     * @return:
     * @author: zhangfan
     * @date: 2018/12/18 3:24 PM
     */
    public static SendSmsResponse sendUserSetPayPWDCodeSms(String mobile,String code) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(mobile);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("云南辰蓝网络科技");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_152825198");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"code\":\""+code+"\"}");

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return sendSmsResponse;
    }

    /**
     *
     * 功能描述: 商家端设置支付密码短信验证码
     *
     * @param mobile 接收短信手机号
     * @param code 短信验证码
     * @return:
     * @author: zhangfan
     * @date: 2018/12/18 3:24 PM
     */
    public static SendSmsResponse sendBusinessSetPayPWDCodeSms(String mobile,String code) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(mobile);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("云南辰蓝网络科技");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_152850090");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"code\":\""+code+"\"}");

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return sendSmsResponse;
    }

    /**
     *
     * 功能描述: 商家端设置登录密码短信验证码
     *
     * @param mobile 接收短信手机号
     * @param code 短信验证码
     * @return:
     * @author: zhangfan
     * @date: 2018/12/18 3:24 PM
     */
    public static SendSmsResponse sendBusinessSetLoginPWDCodeSms(String mobile,String code) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(mobile);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("云南辰蓝网络科技");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_152855058");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"code\":\""+code+"\"}");

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return sendSmsResponse;
    }

    /**
     *
     * 功能描述: 商家端找回登录密码短信验证码
     *
     * @param mobile 接收短信手机号
     * @param code 短信验证码
     * @return:
     * @author: zhangfan
     * @date: 2018/12/18 3:24 PM
     */
    public static SendSmsResponse sendBusinessGetLoginPWDCodeSms(String mobile,String code) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(mobile);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("云南辰蓝网络科技");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_152825124");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"code\":\""+code+"\"}");

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return sendSmsResponse;
    }

    /**
     *
     * 功能描述: 用户端通用积分提现通知
     *
     * @param mobile 接收短信手机号
     * @param year 年份
     * @param month 月份
     * @param day 日期(号数)
     * @param bank_card_no 银行卡后四位
     * @param integral_number 提现积分个数
     * @param arrival_amount 到账金额(人民币)
     * @param service_charge 手续费(人民币)
     * @return:
     * @author: zhangfan
     * @date: 2018/12/18 3:24 PM
     */
    public static SendSmsResponse sendUserGeneralIntegralWithdrawSms(String mobile,String year,String month,String day,String bank_card_no,Double integral_number,Double arrival_amount,Double service_charge) throws ClientException {
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(mobile);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("云南辰蓝网络科技");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_152825117");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"account\":\""+mobile+"\",\"year\":\""+year+"\",\"month\":\""+month+"\",\"day\":\""+day+"\",\"order_no\":\""+bank_card_no+"\",\"integral_number\":\""+integral_number+"\",\"arrival_amount\":\""+arrival_amount+"\",\"service_charge\":\""+service_charge+"\"}");

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return sendSmsResponse;
    }


    /**
     *
     * 功能描述: 用户端购买万能卡通知
     *
     * @param mobile 接收短信手机号
     * @param pay_type 支付方式(0-通用积分,1-微信支付)
     * @param pay_amount 支付金额
     * @param buy_name 万能卡名称(万能银卡,万能金额)
     * @return:
     * @author: zhangfan
     * @date: 2018/12/18 3:24 PM
     */
    public static SendSmsResponse sendUserBuyWnkSms(String mobile,Integer pay_type,Double pay_amount,String buy_name) throws ClientException {
        String pay_way = "通用积分支付";
        if (pay_type == 0){
            pay_way = "通用积分支付";
        }
        else{
            pay_way = "微信支付";
        }
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(mobile);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("云南辰蓝网络科技");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_152830112");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"pay_type\":\""+pay_way+"\",\"pay_amount\":\""+pay_amount+"\",\"buy_name\":\""+buy_name+"\"}");

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return sendSmsResponse;
    }

    /**
     *
     * 功能描述: 商家端购买推广物料通知
     *
     * @param mobile 接收短信手机号
     * @param pay_type 支付方式(0-微信支付,1-余额支付)
     * @param pay_amount 支付金额
     * @param mater_name 物料名称
     * @param buy_number 购买数量
     * @return:
     * @author: zhangfan
     * @date: 2018/12/18 3:24 PM
     */
    public static SendSmsResponse sendBusinessBuyRecommendExtensionWnkSms(String mobile,Integer pay_type,Double pay_amount,String mater_name,Integer buy_number) throws ClientException {
        String pay_way = "微信支付";
        if (pay_type == 0){
            pay_way = "微信支付";
        }
        else{
            pay_way = "余额支付";
        }
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(mobile);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("云南辰蓝网络科技");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_152830136");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"pay_type_and_amount\":\""+pay_way+pay_amount+"\",\"mater_name_and_number\":\""+mater_name+buy_number+"\"}");

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return sendSmsResponse;
    }


    /**
     *
     * 功能描述: 商家端账户提现通知
     *
     * @param mobile 接收短信手机号
     * @param year 年份
     * @param month 月份
     * @param day 日期(天)
     * @param bank_card_no 银行卡后四位
     * @param withdraw_amount 提现金额
     * @param arrival_amount  到账金额
     * @param service_charge  手续费
     * @return:
     * @author: zhangfan
     * @date: 2018/12/18 3:24 PM
     */
    public static SendSmsResponse sendBusinessAccountWithdrawSms(String mobile,String year,String month,String day,String bank_card_no,Double withdraw_amount,Double arrival_amount,Double service_charge) throws ClientException {

        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(mobile);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("云南辰蓝网络科技");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_152830126");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"year\":\""+year+"\",\"month\":\""+month+"\",\"day\":\""+day+"\",\"bank_card_no\":\""+bank_card_no+"\",\"withdraw_amount\":\""+withdraw_amount+"\",\"arrival_amount\":\""+arrival_amount+"\",\"service_charge\":\""+service_charge+"\"}");

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return sendSmsResponse;
    }

    /**
     * 获取发送状态字符串
     * @param code  状态码
     * @return 获取到的状态字符串
     */
    public static String getResult(String code){
        if (code.equals("isp.RAM_PERMISSION_DENY")){
            return "RAM权限DENY";
        }
        else if (code.equals("isp.OUT_OF_SERVICE")){
            return "业务停机";
        }
        else if (code.equals("isp.PRODUCT_UN_SUBSCRIPT")){
            return "未开通云通信产品的阿里云客户";
        }
        else if (code.equals("isp.PRODUCT_UNSUBSCRIBE")){
            return "产品未开通";
        }
        else if (code.equals("isp.ACCOUNT_NOT_EXISTS")){
            return "账户不存在";
        }
        else if (code.equals("isp.ACCOUNT_ABNORMAL")){
            return "账户异常";
        }
        else if (code.equals("isp.SMS_TEMPLATE_ILLEGAL")){
            return "短信模板不合法";
        }
        else if (code.equals("isp.SMS_SIGNATURE_ILLEGAL")){
            return "短信签名不合法";
        }
        else if (code.equals("isp.INVALID_PARAMETERS")){
            return "参数异常";
        }
        else if (code.equals("isp.SYSTEM_ERROR")){
            return "系统错误";
        }
        else if (code.equals("isp.MOBILE_NUMBER_ILLEGAL")){
            return "非法手机号";
        }
        else if (code.equals("isp.MOBILE_COUNT_OVER_LIMIT")){
            return "手机号码数量超过限制";
        }
        else if (code.equals("isp.TEMPLATE_MISSING_PARAMETERS")){
            return "模板缺少变量";
        }
        else if (code.equals("isp.BUSINESS_LIMIT_CONTROL")){
            return "业务限流";
        }
        else if (code.equals("isp.INVALID_JSON_PARAM")){
            return "JSON参数不合法，只接受字符串值";
        }
        else if (code.equals("isp.BLACK_KEY_CONTROL_LIMIT")){
            return "黑名单管控";
        }
        else if (code.equals("isp.PARAM_LENGTH_LIMIT")){
            return "参数超出长度限制";
        }
        else if (code.equals("isp.PARAM_NOT_SUPPORT_URL")){
            return "不支持URL";
        }
        else if (code.equals("isp.AMOUNT_NOT_ENOUGH")){
            return "账户余额不足";
        }
        return "未知错误";
    }

}
