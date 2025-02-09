package org.joelr.controller;

import org.joelr.model.NasaNeoResponse;
import org.joelr.service.NasaApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/asteroids")
public class AsteroidController {

    private final NasaApiClient nasaApiClient;

    @Autowired
    public AsteroidController(NasaApiClient nasaApiClient) {
        this.nasaApiClient = nasaApiClient;
    }

    @GetMapping
    public List<NasaNeoResponse.NearEarthObject> getNearEarthObjects(@RequestParam String apiKey) throws IOException {
        return nasaApiClient.fetchNearEarthObjects(apiKey);
    }
}