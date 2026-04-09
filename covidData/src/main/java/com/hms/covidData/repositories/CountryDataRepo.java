package com.hms.covidData.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.covidData.entities.CountryData;

public interface CountryDataRepo extends JpaRepository<CountryData, Integer>{
    
    CountryData findByCountryNameIgnoreCase(String name);
    Page<CountryData> findByWhoRegionIgnoreCase(String region, Pageable pageable);
}
