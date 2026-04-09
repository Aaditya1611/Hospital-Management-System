package com.hms.covidData.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.covidData.repositories.CovidCompleteRepo;

@Service
public class CovidCompleteService {
    
    @Autowired
    private CovidCompleteRepo covidCompleteRepo;

    
}
