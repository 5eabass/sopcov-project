package sopcov.mail;
import database.DB;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.jboss.weld.util.Observers;

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

/**
 *
 * @author gb
 */
public class MailSender extends Thread{
    
    protected DB dbmanager;
    String from ;
    String username ;
    String password ;
    String host ;
    Properties props;
    Message message ;
    
    public void init() {
        // initialization
        dbmanager = new DB();
        
        // Sender's email ID needs to be mentioned
        from = "neumann@etud.insa-toulouse.com";
        username = "neumann@etud.insa-toulouse.fr";//should be static ( the application send a email
        password = "U456sujxuk";//should be static it's the application that send a email
        
        // Assuming you are sending email through etud-mel.insa-toulouse.fr
        host = "etud-mel.insa-toulouse.fr";
        
        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
               
    }
    
    
    public void sendNotificationEmail(String to){
    
        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
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
            message.setSubject("Notification SopCov");
            
            // Now set the actual message
            message.setText("Bonjour,\n\n"
                    + "Nous sommes heureux de vous annoncer qu'il y a un nouveau conducteur susceptible de faire du covoiturage sur votre trajet.\n" 
                    + "Pour plus d'information veuillez vous rendre sur notre site SopCov : http://localhost:8080/SopCov/index.jsp\n"
                    + "Bonne journée.\n"
                    + "L'équipe SopCov");
                    
            
            // Send message
            Transport.send(message);
            
            System.out.println("Sent message successfully....");
            
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } 
    }
    
   
    
    //IL S'AGIT D'UN CODE DE TEST
    // DISPONIBLE A http://www.tutorialspoint.com/javamail_api/javamail_api_sending_simple_email.htm
  /*  public static void main(String[] args) {
        
        
        // ajouter critère de selection pour l'envoie de mail :
        
        init();
        sendNotificationEmail("sebastienneumann78@gmail.com");
        
       
        
    }*/
    
}
