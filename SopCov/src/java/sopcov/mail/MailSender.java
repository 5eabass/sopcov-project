package sopcov.mail;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gb
 */
public class MailSender {
    
    //IL S'AGIT D'UN CODE DE TEST
    // DISPONIBLE A http://www.tutorialspoint.com/javamail_api/javamail_api_sending_simple_email.htm
   public static void main(String[] args) {
      // Recipient's email ID needs to be mentioned.
      String to = "gabi.mabs@gmail.com";

      // Sender's email ID needs to be mentioned
      String from = "mabille@etud.insa-toulouse.com";
      final String username = "mabille@etud.insa-toulouse.fr";//change accordingly
      final String password = "R€6aR2paM0nM02P";//change accordingly

      // Assuming you are sending email through relay.jangosmtp.net
      String host = "etud-mel.insa-toulouse.fr";

      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.port", "587");

      // Get the Session object.
      Session session = Session.getInstance(props,
         new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(username, password);
	   }
         });

      try {
	   // Create a default MimeMessage object.
	   Message message = new MimeMessage(session);
	
	   // Set From: header field of the header.
	   message.setFrom(new InternetAddress(from));
	
	   // Set To: header field of the header.
	   message.setRecipients(Message.RecipientType.TO,
               InternetAddress.parse(to));
	
	   // Set Subject: header field
	   message.setSubject("Testing Subject");
	
	   // Now set the actual message
	   message.setText("Hello, this is sample for to check send " +
		"email using JavaMailAPI ");

	   // Send message
	   Transport.send(message);

	   System.out.println("Sent message successfully....");

      } catch (MessagingException e) {
         throw new RuntimeException(e);
      }
   }

}
