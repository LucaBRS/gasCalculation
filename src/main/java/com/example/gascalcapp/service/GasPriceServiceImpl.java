package com.example.gascalcapp.service;

import com.example.gascalcapp.dao.GasPriceDao;
import com.example.gascalcapp.model.GasPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GasPriceServiceImpl implements GasPriceService {

    private final GasPriceDao gasPriceDao;

    @Autowired
    public GasPriceServiceImpl(GasPriceDao gasPriceDao) {
        this.gasPriceDao = gasPriceDao;
    }

    @Override
    public GasPrice getGasPriceByLocation(String location) {
        return gasPriceDao.getGasPriceByLocation(location);
    }

    @Override
    public double calculateGasConsumption(double distance, double fuelEfficiency) {
        // Calculation logic goes here
        return 0.0; // Placeholder value
    }

    @Override
    public double calculateFuelCost(double gasPrice, double gasConsumed) {
        // Calculation logic goes here
        return 0.0; // Placeholder value
    }
}
