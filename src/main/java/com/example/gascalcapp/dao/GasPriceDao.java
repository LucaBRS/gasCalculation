package com.example.gascalcapp.dao;

import com.example.gascalcapp.model.GasPrice;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GasPriceDao {
    @Value("${gas.price.api.url}")
    private String gasPriceApiUrl;

    public List<GasPrice> getAllGasPrices() {
        // Implement the logic to fetch all gas prices from the API or data source
        // Here is a sample implementation using Apache HttpClient
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(gasPriceApiUrl);

        try {
            HttpResponse response = httpClient.execute(request);
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            String line;
            StringBuilder responseBody = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                responseBody.append(line);
            }

            // Parse the response and construct GasPrice objects
            List<GasPrice> gasPrices = new ArrayList<>();
            // Implement the logic to parse the response and populate gasPrices list

            return gasPrices;
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>(); // Return an empty list in case of error
        }
    }

    public GasPrice getGasPriceByType(String gasType) {
        // Implement the logic to fetch gas price by type from the API or data source
        // Here is a sample implementation using Apache HttpClient
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(gasPriceApiUrl + "?type=" + gasType);

        try {
            HttpResponse response = httpClient.execute(request);
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            String line;
            StringBuilder responseBody = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                responseBody.append(line);
            }

            GasPrice gasPrice = null;
            // Parse the response and construct a GasPrice object
            // Implement the logic to parse the response and populate a GasPrice object

            return gasPrice; // Return the GasPrice object
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Return null in case of error
        }
    }
}