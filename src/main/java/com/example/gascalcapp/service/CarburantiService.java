package com.example.gascalcapp.service;

import com.example.gascalcapp.dao.CarburantiDao;
import com.example.gascalcapp.model.CarburantiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CarburantiService {
    private final CarburantiDao carburantiDao;
    private static final Logger LOG = LoggerFactory.getLogger(CarburantiService.class);
    private int flagTotElemNumber = 0;

    public CarburantiService(CarburantiDao carburantiDao) {
        this.carburantiDao = carburantiDao;
    }

    public CarburantiResponse searchArea(int region, String province, String town) throws JsonProcessingException {
        LOG.info("u are in Service");

        Map<Integer, JsonNode> convertedJsonToMap = new HashMap<>();
        ResponseEntity<String> cResponse = carburantiDao.searchArea(region, province, town);
        for (int i = 1; i <= 20; i++) {
            convertedJsonToMap.putAll(convertJsonToMap(carburantiDao.searchArea(i, province, town), flagTotElemNumber));
        }
        if (flagTotElemNumber == convertedJsonToMap.size()) {
            LOG.info("total number of element OK: " + flagTotElemNumber);
        } else {
            LOG.error("totlal num elem NOT ok: " + flagTotElemNumber);
        }
        flagTotElemNumber = 0;
        return new CarburantiResponse(convertedJsonToMap);
    }

    private Map<Integer, JsonNode> convertJsonToMap(ResponseEntity<String> cResponse, int totElemNumber) throws JsonProcessingException {
        LOG.info("starting conversion of json body to map");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode responseJsonNode = null;
        Map<Integer, JsonNode> resultMap = new HashMap<>();

        HttpStatus statusCode = (HttpStatus) cResponse.getStatusCode();
        HttpHeaders responseHeaders = cResponse.getHeaders();
        responseJsonNode = objectMapper.readTree(cResponse.getBody()).get("results");

        for (int i = 0; i < responseJsonNode.size(); i++) {
            JsonNode elemNode = responseJsonNode.get(i);
            int id = elemNode.get("id").asInt();
            ((ObjectNode) elemNode).remove("id");
            JsonNode restElem = elemNode;
            resultMap.put(id, restElem);
        }

        if (responseJsonNode.size() == resultMap.size()) {
            LOG.info("the json list and map have same length:" + resultMap.size());
        } else {
            LOG.error("the json list and map have NOT same length:");
        }
        flagTotElemNumber += resultMap.size();
        return resultMap;
    }
}
