package org.joelr;

import org.joelr.service.NasaApiClient;
import org.joelr.model.NasaNeoResponse;
import org.joelr.service.EmailService;
import org.joelr.util.AsteroidInfoBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

   /*

        ██   ██  █████  ███████  █████  ██████  ██████   ██████  ██    ██ ███████
        ██   ██ ██   ██    ███  ██   ██ ██   ██ ██   ██ ██    ██ ██    ██ ██
        ███████ ███████   ███   ███████ ██████  ██   ██ ██    ██ ██    ██ ███████
        ██   ██ ██   ██  ███    ██   ██ ██   ██ ██   ██ ██    ██ ██    ██      ██
        ██   ██ ██   ██ ███████ ██   ██ ██   ██ ██████   ██████   ██████  ███████

                 █████  ███████ ████████ ███████ ██████   ██████  ██ ██████
                ██   ██ ██         ██    ██      ██   ██ ██    ██ ██ ██   ██
                ███████ ███████    ██    █████   ██████  ██    ██ ██ ██   ██
                ██   ██      ██    ██    ██      ██   ██ ██    ██ ██ ██   ██
                ██   ██ ███████    ██    ███████ ██   ██  ██████  ██ ██████
                                                 HAZARDOUS ASTEROID EMAILER

    This program connects to a NASA Open API and receives data about nearby asteroids
    If any are marked as hazardous it sends an email with information about them
                              made by Joel Rennert / 2023

   */

public class AsteroidEmailer {

    public static void main(String[] args) {
        String apiKey = System.getenv("apiKey");
        NasaApiClient apiClient = new NasaApiClient();
        EmailService emailService = new EmailService();
        AsteroidInfoBuilder infoBuilder = new AsteroidInfoBuilder();

        try {
            List<NasaNeoResponse.NearEarthObject> nearEarthObjects = apiClient.fetchNearEarthObjects(apiKey);

            List<NasaNeoResponse.NearEarthObject> hazardousObjects = new ArrayList<>();
            for (NasaNeoResponse.NearEarthObject neo : nearEarthObjects) {
                if (neo.isPotentiallyHazardousAsteroid()) {
                    hazardousObjects.add(neo);
                }
            }

            StringBuilder emailContent = new StringBuilder();
            for (NasaNeoResponse.NearEarthObject neo : hazardousObjects) {
                String neoInfo = infoBuilder.buildAsteroidInfo(neo);
                emailContent.append(neoInfo).append("<br/><br/>");
            }

            if (emailContent.length() > 0) {
                emailService.sendHazardousAsteroidEmail(emailContent.toString());
            } else {
                System.out.println("No hazardous asteroids found. No email sent.");
            }

        } catch (IOException e) {
            System.err.println("Error fetching data from NASA NEO API: " + e.getMessage());
        }
    }
}