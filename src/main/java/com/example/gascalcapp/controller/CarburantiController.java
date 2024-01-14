package com.example.gascalcapp.controller;

import com.example.gascalcapp.model.CarburantiResponse;
import com.example.gascalcapp.model.SearchRequest;
import com.example.gascalcapp.service.CarburantiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/carburanti")
public class CarburantiController {
    private final CarburantiService carburantiService;

    public CarburantiController(CarburantiService carburantiService) {
        this.carburantiService = carburantiService;
    }

    @PostMapping("/search/area")
    public ResponseEntity<CarburantiResponse> searchArea(@RequestBody SearchRequest searchRequest) {
        // You can create a separate class for SearchRequest if needed
        int region = searchRequest.getRegion();
        String province = searchRequest.getProvince();
        String town = searchRequest.getTown();

        CarburantiResponse response = carburantiService.searchArea(region, province, town);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
