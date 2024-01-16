package com.example.gascalcapp.model;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class CarburantiResponse {
    private static final Logger LOG = LoggerFactory.getLogger(CarburantiResponse.class);

    public CarburantiResponse(Map<Integer, JsonNode> rawJson) {
        LOG.info("u just instantiated the Response");
        this.rawJson = rawJson;
    }

    private Map<Integer, JsonNode> rawJson;

    public Map<Integer, JsonNode> getRawJson() {
        return rawJson;
    }

    public void setRawJson(Map<Integer, JsonNode>  rawJson) {
        this.rawJson = rawJson;
    }
}
