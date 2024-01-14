package com.example.gascalcapp.model;

public class CarburantiResponse {

    public CarburantiResponse(String rawJson) {
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
