package com.example.gascalcapp.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


// Example using WebClient
@Service
public class CarburantiDao {
    private static final Logger LOG = LoggerFactory.getLogger(CarburantiDao.class);


    public ResponseEntity<String> searchArea(int region, String province, String town) {
        // Create a RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Create the request headers
        HttpHeaders headers = new HttpHeaders();

        LOG.info("u are in dao");

        // Define the URL and request body
        String url = "https://carburanti.mise.gov.it/ospzApi/search/area";
        String requestBody = "{\"region\":"+region+" , \"province\": null, \"town\": null}";

        //Set the request headers
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        // Make the POST request and get the response

        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
    }
}