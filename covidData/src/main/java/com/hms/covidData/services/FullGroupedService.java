package com.hms.covidData.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hms.covidData.entities.FullGrouped;
import com.hms.covidData.repositories.FullGroupedRepo;

import jakarta.transaction.Transactional;

@Service
public class FullGroupedService {
    
    @Autowired
    private FullGroupedRepo fullGroupedRepo;

    public Page<FullGrouped> findAllData(int page, int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("active").descending());
        return fullGroupedRepo.findAll(pageable);
    } 

    public FullGrouped findByCountryName(String name) {

        return fullGroupedRepo.findByCountry(name);
    }

    public List<FullGrouped> findByRegion(String region) {

        return fullGroupedRepo.findByRegion(region);
    }

     @Transactional
    public void deleteByCountry(String name) {
        fullGroupedRepo.deleteByCountry(name);
    }
}
