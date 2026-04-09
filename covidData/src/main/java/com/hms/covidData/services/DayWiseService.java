package com.hms.covidData.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.covidData.repositories.DayWiseRepo;

@Service
public class DayWiseService {
    
    @Autowired
    private DayWiseRepo dayWiseRepo;
}
