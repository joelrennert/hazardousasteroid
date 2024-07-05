package org.joelr.service;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class EmailSender {

    private static final Properties PROPERTIES = new Properties();
    private static final String USERNAME = System.getenv("USERNAME");
    private static final String PASSWORD = System.getenv("PASSWORD");
    private static final String HOST = "smtp.gmail.com";

    static {
        PROPERTIES.put("mail.smtp.host", "smtp.gmail.com");
        PROPERTIES.put("mail.smtp.port", "587");
        PROPERTIES.put("mail.smtp.auth", "true");
        PROPERTIES.put("mail.smtp.starttls.enable", "true");
    }

    public static void sendPlainTextEmail(String from, String to, String subject, List<String> messages, boolean debug) {

        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        };

        Session session = Session.getInstance(PROPERTIES, authenticator);
        session.setDebug(debug);

        try {
            // create a message with headers
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setSentDate(new Date());

            // create message body
            Multipart mp = new MimeMultipart();
            for (String message : messages) {
                MimeBodyPart mbp = new MimeBodyPart();
                mbp.setText(message, "us-ascii");
                mp.addBodyPart(mbp);
            }
            msg.setContent(mp);

            // send the message
            Transport.send(msg);

        } catch (MessagingException mex) {
            System.err.println("Error sending plain text email: " + mex.getMessage());
            Exception ex = null;
            if ((ex = mex.getNextException()) != null) {
                System.err.println("Caused by: " + ex.getMessage());
            }
        }
    }

    // Method to send HTML email
    public static void sendHtmlEmail(String from, String to, String subject, String htmlContent) {
        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        };

        Session session = Session.getInstance(PROPERTIES, authenticator);


        try {
            // create a message with headers
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setSentDate(new Date());

            // create HTML message body
            Multipart mp = new MimeMultipart();
            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(htmlContent, "text/html");
            mp.addBodyPart(htmlPart);
            msg.setContent(mp);

            // send the message
            Transport.send(msg);

        } catch (MessagingException mex) {
            System.err.println("Error sending HTML email: " + mex.getMessage());
            Exception ex = null;
            if ((ex = mex.getNextException()) != null) {
                System.err.println("Caused by: " + ex.getMessage());
            }
        }
    }
}