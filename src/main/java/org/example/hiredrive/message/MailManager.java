package org.example.hiredrive.message;

import java.util.Properties;

import javax.mail.*;
import javax.mail.Message;
import javax.mail.internet.*;

public class MailManager {

    private static final String EMAILREGEX = ".*@gmail\\.com$";

    public static boolean isValidEmail(String email){
        return email.matches(EMAILREGEX);

    }
    //sends a given string as a notification in the form of mail
    public static void messageReturn(String notification, String mailTo){
        String from = "hiredrivecs@gmail.com";
        // Sender's password
        String password = "gvvv dvuq ogzn uyjn";
        // Recipient's email
        String to = mailTo;

        // SMTP server configuration
        String host = "smtp.gmail.com";
        int port = 587;

        // Create properties object
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        // Create session with authentication
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            // MimeMessage object
            javax.mail.Message message = new MimeMessage(session);
            // Set sender's email
            message.setFrom(new InternetAddress(from));
            // Set recipient's email
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            // Set email subject
            message.setSubject("HireDrive Notification");
            // Set email content
            message.setText(notification);

            // Send email
            Transport.send(message);
            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        
    }
    //creates and sends verification code
    public static int sendVerificationMail(String mailTo) {
        // Sender's email
        String from = "hiredrivecs@gmail.com";
        // Sender's password
        String password = "gvvv dvuq ogzn uyjn";
        // Recipient's email
        String to = mailTo;

        // SMTP server configuration
        String host = "smtp.gmail.com";
        int port = 587;

        // Create properties object
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        // Create session with authentication
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        int code = 0;
        try {
            // MimeMessage object
            javax.mail.Message message = new MimeMessage(session);
            // Set sender's email
            message.setFrom(new InternetAddress(from));
            // Set recipient's email
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            // Set email subject
            message.setSubject("HireDrive Authentication Code");
            // Creating the authentication code (6-digits)
            code = (int) (Math.random() * 900000) + 100000;
            // Set email content
            message.setText("The authentication code to login is: " + code + "");

            // Send email
            Transport.send(message);
            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return code;
    }

    //public static String displayMessage()
}
