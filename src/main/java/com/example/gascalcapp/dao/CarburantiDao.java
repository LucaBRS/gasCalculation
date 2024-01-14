package com.example.gascalcapp.dao;

import com.example.gascalcapp.model.CarburantiResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


// Example using WebClient
@Service
public class CarburantiDao {
    private final WebClient webClient;

    public CarburantiDao(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://carburanti.mise.gov.it/ospzApi").build();
    }

    public CarburantiResponse searchArea(int region, String province, String town) {
        // Build and make the POST request using WebClient
        // Parse the response and return the result
        return new CarburantiResponse(webClient.post()
                .uri("/search/area")
                .bodyValue("{\"region\": " + region + ", \"province\": \"" + province + "\", \"town\": \"" + town + "\"}")
                .retrieve()
                .bodyToMono(String.class)
                .block()); // Blocking for simplicity, handle it asynchronously in a real applicatio
    }
}