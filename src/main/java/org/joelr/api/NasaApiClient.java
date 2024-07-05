package org.joelr.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.joelr.model.NasaNeoResponse;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class NasaApiClient {

    private static final String API_URL = "https://api.nasa.gov/neo/rest/v1/neo/browse?api_key=";

    public List<NasaNeoResponse.NearEarthObject> fetchNearEarthObjects(String apiKey) throws IOException {
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalArgumentException("API key not found, check environment variables");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        NasaNeoResponse response = objectMapper.readValue(new URL(API_URL + apiKey), NasaNeoResponse.class);
        return response.getNearEarthObjects();
    }
}