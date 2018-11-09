package utils;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: c.fidan
 * Date: 12/16/15
 * Time: 2:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class MailUtiliity {
    public void sendHtmlMail(String email,String content,String fullname, String appId) throws Exception {
       try{


            Properties prop = new Properties();
            InputStream inputStream = getClass().getResourceAsStream("mail.properties");
            prop.load(inputStream);

            prop.put("mail.smtp.ssl.enable", "false");
            prop.setProperty("mail.smtp.**ssl.enable", "true");
            prop.setProperty("mail.smtp.**ssl.required", "true");
            prop.put("mail.smtp.socketFactory.port", 465);
            prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            prop.put("mail.smtp.socketFactory.fallback", "true") ;
            prop.put("mail.smtp.ssl.trust", "gmail.com");
           Session session1 = Session.getDefaultInstance(prop,
                   new Authenticator() {
                       protected PasswordAuthentication  getPasswordAuthentication() {
                           return new PasswordAuthentication(
                                   "", "");
                       }
                   });
            Transport bus = session1.getTransport("smtp");
            String host = prop.getProperty("mail.host");
            String from = prop.getProperty("mailFrom");
            String pass = prop.getProperty("mailPass");
            bus.connect(host, from, pass);
            MimeMessage message = new MimeMessage(session1);
            String encodingOptions = "text/html; charset=UTF-8";
            message.setHeader("Content-Type", encodingOptions);
            message.setFrom(new InternetAddress(from));
            InternetAddress[] address1 = {new InternetAddress(email)};
            message.setRecipients(Message.RecipientType.TO, address1);
            message.setSubject("ULTEMP New Message", "UTF-8");
            String content1 = "<div >\n" +content+
                    "<div class=\"im\">\n" +
                    "<h3></h3>\n" +
                    "<h4>Hörmətlə,   "+fullname+"</h4>\n" +
                    "</div>\n";
            message.setContent(content1, "text/html;charset=UTF-8");
            bus.sendMessage(message, address1);
            bus.close();
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
