package com.example.gascalcapp.service;

import com.example.gascalcapp.dao.CarburantiDao;
import com.example.gascalcapp.model.CarburantiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CarburantiService {
    private final CarburantiDao carburantiDao;
    private static final Logger LOG = LoggerFactory.getLogger(CarburantiService.class);

    public CarburantiService(CarburantiDao carburantiDao) {
        this.carburantiDao = carburantiDao;
    }

    public CarburantiResponse searchArea(int region, String province, String town) {
        LOG.info("u are in Service");
        // Perform any business logic if needed
        // Delegate to DAO for data retrieval
        CarburantiResponse cResponse = carburantiDao.searchArea(region, province, town);
        return cResponse;
    }
}
