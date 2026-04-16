package com.hms.covidData.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.hms.covidData.entities.CovidComplete;
import com.hms.covidData.repositories.CovidCompleteRepo;

import jakarta.transaction.Transactional;

@Service
public class CovidCompleteService {

    @Autowired
    private CovidCompleteRepo covidCompleteRepo;

    public Page<CovidComplete> getAllCovidComplete(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        return covidCompleteRepo.findAll(pageable);
    }

    public CovidComplete findByCountryName(String country) {

        return covidCompleteRepo.findByCountryIgnoreCase(country);
    }

    public List<CovidComplete> findByLocation(BigDecimal longitude, BigDecimal latitude) {

        return covidCompleteRepo.findByLocationNear(latitude, longitude);
    }

    public List<CovidComplete> findByWhoRegion(String name) {

        return covidCompleteRepo.findByRegion(name);
    }

    @Transactional
    public void deleteByCountry(String name) {
        covidCompleteRepo.deleteByCountry(name);
    }

}
