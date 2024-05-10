package org.example.hiredrive;

import java.util.Properties;

import javax.mail.*;
import javax.mail.Message;
import javax.mail.internet.*;


public class SendEmail {
    public static void main(String[] args) {
        // Sender's email
        String from = "hiredrivecs@gmail.com";
        // Sender's password
        String password = "gvvv dvuq ogzn uyjn";
        // Recipient's email
        String to = "tayyibe395@gmail.com";

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
            message.setSubject("HireDrive Authentication Code");
            // Creating the authentication code (6-digits)
            int code = (int) (Math.random() * 900000) + 100000;
            // Set email content
            message.setText("The authentication code to login is: " + code + "");

            // Send email
            Transport.send(message);
            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
