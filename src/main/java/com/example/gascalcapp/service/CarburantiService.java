package com.example.gascalcapp.service;

import com.example.gascalcapp.dao.CarburantiDao;
import com.example.gascalcapp.model.CarburantiResponse;
import org.springframework.stereotype.Service;

@Service
public class CarburantiService {
    private final CarburantiDao carburantiDao;

    public CarburantiService(CarburantiDao carburantiDao) {
        this.carburantiDao = carburantiDao;
    }

    public CarburantiResponse searchArea(int region, String province, String town) {
        // Perform any business logic if needed
        // Delegate to DAO for data retrieval
        String rawJsonResponse = String.valueOf(carburantiDao.searchArea(region, province, town));
        return new CarburantiResponse(rawJsonResponse);
    }
}
