package com.example.gascalcapp.controller;

import com.example.gascalcapp.model.CarburantiResponse;
import com.example.gascalcapp.service.CarburantiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carburanti")
public class CarburantiController {
    private final CarburantiService carburantiService;
    private static final Logger LOG = LoggerFactory.getLogger(CarburantiController.class);

    public CarburantiController(CarburantiService carburantiService) {
        this.carburantiService = carburantiService;
    }

    @GetMapping("/prices/{regionId}")
    public ResponseEntity<String> searchArea(@PathVariable("regionId") int regionId) {
        LOG.info("U are in controller the id is: "+regionId);
        // You can create a separate class for SearchRequest if needed
        int region = regionId;
        String province = null;
        String town = null;

        CarburantiResponse response = carburantiService.searchArea(region, province, town);
        return new ResponseEntity<>(response.getRawJson(), HttpStatus.OK);
    }
}
