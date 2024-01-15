package com.example.gascalcapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarburantiApplication {
    private static final Logger LOG = LoggerFactory.getLogger(CarburantiApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CarburantiApplication.class, args);
    }
}