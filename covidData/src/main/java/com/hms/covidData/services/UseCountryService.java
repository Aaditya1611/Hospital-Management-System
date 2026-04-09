package com.hms.covidData.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.covidData.repositories.UsaCountryRepo;

@Service
public class UseCountryService {
    
    @Autowired
    private UsaCountryRepo usaCountryRepo;
}
