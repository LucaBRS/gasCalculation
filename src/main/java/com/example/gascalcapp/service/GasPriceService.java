package com.example.gascalcapp.service;

import com.example.gascalcapp.model.GasPrice;

public interface GasPriceService {
    GasPrice getGasPriceByLocation(String location);

    double calculateGasConsumption(double distance, double fuelEfficiency);

    double calculateFuelCost(double gasPrice, double gasConsumed);
}
