package zms.util.email;


import com.sun.mail.util.MailSSLSocketFactory;
import zms.util.RandomWord;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;

/**
 *  发送邮件
 * @author 19448
 */
public class GetJavaMail {
    private static Properties prop =null;
    private static String username="mycompany_z@qq.com";
    private static Transport ts = null;
    /**
     * 初始化邮箱
     */
    private static void init(){
        prop = new Properties();
        // 开启SSL加密，否则会失败
        MailSSLSocketFactory sf = null;
        // 设置邮件服务器主机名
        prop.setProperty("mail.host", "smtp.qq.com");
        // 发送服务器需要身份验证
        prop.setProperty("mail.smtp.auth", "true");
        // 发送邮件协议名称
        prop.setProperty("mail.transport.protocol", "smtp");
        try {
            sf = new MailSSLSocketFactory();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        sf.setTrustAllHosts(true);
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);
    }
    private static Session getSession(){
        // 创建session
        Session session = Session.getInstance(prop);
        // 通过session得到transport对象

        try {
            ts = session.getTransport();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        // 连接邮件服务器：邮箱类型，帐号，授权码代替密码（更安全）
        try {
            ts.connect("smtp.qq.com", username, "aprepqqfokxvbbab");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return session;
    }

    /**
     * 生成随机码
     * @return
     */
    public static String param=RandomWord.getWord(6);
    /**
     * 发送验证码
     * @param userEmail
     * @return
     */
    public static Boolean sendEmail(String userEmail){
        init();
        Message message = getMessage(userEmail,param);
        try {
            ts.sendMessage(message, message.getAllRecipients());
            ts.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
    private static Message getMessage(String toUserEmail,String getText){
        Session session = getSession();
        // 创建邮件对象
        MimeMessage message = new MimeMessage(session);
        // 指明邮件的发件人
        try {
            message.setFrom(new InternetAddress(username));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        // 指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
        try {
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(toUserEmail));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        // 邮件的标
        try {
            message.setSubject("森森科技企业内部注册验证");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        // 邮件的文本内容
        String Text="<div style='padding:50px;'><div style='width:400px;height:400px;margin:auto 0;display:block;'><p style='color:blue;font-size:30px;'>尊敬的用户：</p><p style='color:red;margin-top:15px;'>您的验证码是:<span style='font-size:30px;margin-top:10px;'>"+getText+"</span></p><p>如果你没有收到验证码，请重新发送</p><p margin-top:10px;><b>请勿将此验证码转发给或提供给任何人！</b></p></div></div>";
        try {
            message.setContent(Text, "text/html;charset=UTF-8");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        // 返回创建好的邮件对象
        return message;
    }

    /*public static void main(String[] args) {
        Boolean aBoolean = GetJavaMail.sendEmail("1944801730@qq.com");
        System.out.println(aBoolean);
    }*/
}
