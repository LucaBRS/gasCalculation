package com.example.gascalcapp.model;

import com.example.gascalcapp.service.CarburantiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CarburantiResponse {
    private static final Logger LOG = LoggerFactory.getLogger(CarburantiResponse.class);

    public CarburantiResponse(String rawJson) {
        LOG.info("u just instantiated the Response");
        this.rawJson = rawJson;
    }

    private String rawJson;

    public String getRawJson() {
        return rawJson;
    }

    public void setRawJson(String rawJson) {
        this.rawJson = rawJson;
    }
}
