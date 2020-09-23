import javax.mail.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;

public class EmailWriter {

    public static void sendMail(String recipient, String subject, String content) throws MessagingException {
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccountEmail = "mycrowsawftburner@gmail.com";
        String password = "CS3250TEAM4";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        Message message = prepareMessage(subject, content, session, myAccountEmail, recipient);

        Transport.send(message);
        System.out.println("");
    }

    private static Message prepareMessage(String subject, String content, Session session, String myAccountEmail, String recipient){
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(subject);
            message.setText(content);
            return message;
        } catch (Exception ex){
            Logger.getLogger(EmailWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }
}
