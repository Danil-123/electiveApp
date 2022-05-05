package app.model.command.registerCommand;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

public class SendEmail {

    public static void sendEmail(String name, String surname) {
        final Properties properties = new Properties();
        try {
            properties.load(SendEmail.class.getClassLoader().getResourceAsStream("mail.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Session mailSession = Session.getDefaultInstance(properties);
        MimeMessage message = new MimeMessage(mailSession);
        try {
            message.setFrom("topprogrammerjava@gmail.com");

            message.addRecipient(Message.RecipientType.TO, new InternetAddress("danilshatov10@gmail.com"));
            message.setSubject("Elective");
            message.setText("Dear " + name + " " + surname + "," + "\n\nyou have successfully registered on the site!");

            Transport tr = mailSession.getTransport();
            tr.connect(null, "qwerty123456bnm");
            tr.sendMessage(message, message.getAllRecipients());

            tr.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
