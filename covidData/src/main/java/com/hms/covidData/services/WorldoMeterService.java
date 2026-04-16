package com.hms.covidData.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hms.covidData.repositories.WorldoMeterRepo;

import jakarta.transaction.Transactional;

import com.hms.covidData.entities.WorldoMeter;

@Service
public class WorldoMeterService {
    
    @Autowired
    private WorldoMeterRepo worldoMeterRepo;

    public Page<WorldoMeter> findAllData(int page, int size) {

       Pageable pageable = PageRequest.of(page, size, Sort.by("totalCases").descending()); 
       return worldoMeterRepo.findAll(pageable);
    }

    public List<WorldoMeter> findByContinent(String continent) {

        return worldoMeterRepo.findByContinent(continent);
    }

    public List<WorldoMeter> findByWhoRegion(String region) {

        return worldoMeterRepo.findByRegion(region);
    }

    public WorldoMeter findByCountry(String country) {

        return worldoMeterRepo.findByCountry(country);
    }

     @Transactional
    public void deleteByCountry(String name) {
        worldoMeterRepo.deleteByCountry(name);
    }
} 
