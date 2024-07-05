package org.joelr.service;

import java.sql.SQLOutput;

public class EmailService {

    public void sendHazardousAsteroidEmail(String emailContent) {
        String fullEmailContent = "<html>" +
                                  "<body>" +
                                    "<i>" +
                                        "<h3 style=\"color:red;\">HAZARDOUS ASTEROIDS ARE NEARBY</h3>" +
                                    "</i>" +
                                    "<p>" + emailContent + "</p>" +
                                  "</body>" +
                                  "</html>";

        EmailSender.sendHtmlEmail("asteroidemailer@gmail.com",
                                  "asteroidemailer@gmail.com",
                                  "HAZARDOUS ASTEROID ALERT",
                                  fullEmailContent);
        System.out.println("**********");
        System.out.println("Email sent");
        System.out.println("**********");
    }
}