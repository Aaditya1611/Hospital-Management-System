package com.hms.covidData.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.covidData.repositories.WorldoMeterRepo;

@Service
public class WorldoMeterService {
    
    @Autowired
    private WorldoMeterRepo worldoMeterRepo;
}
