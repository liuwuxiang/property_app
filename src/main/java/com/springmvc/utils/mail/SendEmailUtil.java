package com.springmvc.utils.mail;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * 邮箱工具类
 */
public class SendEmailUtil {
    /**发件人邮箱地址*/
    private static final String from     = "zhuojingsoft@163.com";
    /** 发件人称号，同邮箱地址*/
    private static final String user     = "zhuojingsoft@163.com";
    /**发件人邮箱客户端授权码*/
    private static final String password = "zf250455";

    public static boolean sendEmail(String to, String text, String title){
        Properties props = new Properties();
        // 设置发送邮件的邮件服务器的属性（这里使用网易的smtp服务器）
        props.setProperty("mail.smtp.host", "smtp.163.com");
        // 需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条）
        props.put("mail.smtp.host", "smtp.163.com");
        // 用刚刚设置好的props对象构建一个session
        props.put("mail.smtp.auth", "true");
        // 有了这句便可以在发送邮件的过程中在console处显示过程信息，供调试使
        Session session = Session.getDefaultInstance(props);
        props.setProperty("mail.smtp.ssl.enable", "true");
        // 用（你可以在控制台（console)上看到发送邮件的过程）
        // 用session为参数定义消息对象
        session.setDebug(true);
        // 加载发件人地址
        MimeMessage message = new MimeMessage(session);
        try {
            //防止成为垃圾邮件，披上outlook的马甲
            message.addHeader("X-Mailer","Microsoft Outlook Express 6.00.2900.2869");
            message.setFrom(new InternetAddress(from));
            // 加载收件人地址
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            // 加载标题
            message.setSubject(title);
            // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            Multipart multipart = new MimeMultipart();
            // 设置邮件的文本内容
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setContent(text, "text/html;charset=utf-8");
            multipart.addBodyPart(contentPart);
            message.setContent(multipart);
            // 保存变化
            message.saveChanges();
            // 连接服务器的邮箱
            Transport transport = session.getTransport("smtp");
            // 把邮件发送出去
            transport.connect("smtp.163.com", user, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            System.out.println("邮件发送错误:"+e);
            e.printStackTrace();
            return false;
        }
        return true;
    }


//    public static void main(String[] args) { // 做测试用
//        SendEmailUtil.sendEmail("917785909@qq.com", "你好，这是一封测试邮件，无需回复。", "测试邮件");
//    }
}
