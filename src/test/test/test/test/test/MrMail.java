package test.test.test.test.test;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MrMail {
  
  public static void main(String[] args) {
    new MrMail().sendEmail("563981441@qq.com", "1", "1");
  }

  public boolean sendEmail(String email, String subject, String content) {
    boolean ret = true;
    String username = "18613800499@163.com";
    String password = "xiaoya0630.com";
    String smtp_server = "smtp.163.com";
    String from_mail_address = "fangyajun@sunnybs.com";
    // username = this.configService.getSysConfig().getEmailUserName();
    // password = this.configService.getSysConfig().getEmailPws();
    // smtp_server = this.configService.getSysConfig().getEmailHost();
    // from_mail_address = this.configService.getSysConfig().getEmailUser();
    String to_mail_address = email;
    if ((username != null) && (password != null) && (!username.equals(""))
        && (!password.equals("")) && (smtp_server != null) && (!smtp_server.equals(""))
        && (to_mail_address != null) && (!to_mail_address.trim().equals(""))) {
      Authenticator auth = new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
          return new PasswordAuthentication(username, password);
        }
      };
      // Properties mailProps = new Properties();
      Properties mailProps = System.getProperties();
      mailProps.put("mail.smtp.auth", "true");
      mailProps.put("mail.smtp.sender", username);
      mailProps.put("mail.smtp.password", password);
      mailProps.put("mail.smtp.host", smtp_server);
      // mailProps.put("mail.smtp.sender", "18613800499@163.com");
      // mailProps.put("mail.smtp.password", "xiaoya0630.com");
      // mailProps.put("mail.smtp.host", "smtp.163.com");

      try {
        Session mailSession = Session.getInstance(mailProps, auth);
        MimeMessage message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress(username));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to_mail_address));


        message.setSubject(subject);
        MimeMultipart multi = new MimeMultipart("related");
        BodyPart bodyPart = new MimeBodyPart();
        bodyPart.setDataHandler(new DataHandler(content, "text/html;charset=UTF-8"));

        multi.addBodyPart(bodyPart);
        message.setContent(multi);
        message.saveChanges();
        Transport.send(message);
        ret = true;
      } catch (AddressException e) {
        ret = false;
        e.printStackTrace();
      } catch (MessagingException e) {
        ret = false;
        e.printStackTrace();
      }
    } else {
      ret = false;
    }
    return ret;
  }
}
