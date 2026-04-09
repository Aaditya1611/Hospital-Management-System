package com.hms.covidData.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.covidData.repositories.FullGroupedRepo;

@Service
public class FullGroupedService {
    
    @Autowired
    private FullGroupedRepo fullGroupedRepo;
}
