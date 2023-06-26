package com.example.gascalcapp.controller;

import com.example.gascalcapp.model.GasPrice;
import com.example.gascalcapp.service.GasPriceService;
import com.example.gascalcapp.visualization.GasPriceVisualization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GasPriceController {
    private final GasPriceService gasPriceService;
    private final GasPriceVisualization gasPriceVisualization;

    @Autowired
    public GasPriceController(GasPriceService gasPriceService, GasPriceVisualization gasPriceVisualization) {
        this.gasPriceService = gasPriceService;
        this.gasPriceVisualization = gasPriceVisualization;
    }

    @GetMapping("/gas-price/{location}")
    public void getGasPrice(@PathVariable String location) {
        GasPrice gasPrice = gasPriceService.getGasPriceByLocation(location);
        gasPriceVisualization.displayGasPrice(gasPrice);
    }
}