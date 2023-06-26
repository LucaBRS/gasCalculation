package com.example.gascalcapp.model;

import org.springframework.stereotype.Component;

@Component
public class GasPrice {
    private String gasType;
    private double price;

    public GasPrice(String gasType, double price) {
        this.gasType = gasType;
        this.price = price;
    }

    public String getGasType() {
        return gasType;
    }

    public void setGasType(String gasType) {
        this.gasType = gasType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
