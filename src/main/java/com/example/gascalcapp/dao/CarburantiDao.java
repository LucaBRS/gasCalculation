package com.example.gascalcapp.dao;

import com.example.gascalcapp.controller.CarburantiController;
import com.example.gascalcapp.model.CarburantiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


// Example using WebClient
@Service
public class CarburantiDao {
    private final WebClient webClient;
    private static final Logger LOG = LoggerFactory.getLogger(CarburantiDao.class);

    public CarburantiDao(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://carburanti.mise.gov.it/ospzApi").build();
    }

    public CarburantiResponse searchArea(int region, String province, String town) throws JsonProcessingException {
        LOG.info("u are in dao");
        String uriPlus = "/search/area";
        // Build and make the POST request using WebClient
        // Parse the response and return the result

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString("{\"region\": " + region + ", \"province\": \"" + province + "\", \"town\": \"" + town + "\"}");


        CarburantiResponse cResponse = new CarburantiResponse(String.valueOf(webClient.post()
                .uri("/search/area")
                .bodyValue("{\"region\": " + region + ", \"province\": \"" + province + "\", \"town\": \"" + town + "\"}").retrieve().bodyToMono(String.class).flatMap(json -> {
                    LOG.info("Dao response: " + json);
                    return Mono.just(new CarburantiResponse(json));
                })

        ));

        LOG.info("Dao response: " + String.valueOf(cResponse.getRawJson()));

        return cResponse; // Blocking for simplicity, handle it asynchronously in a real application
    }
}