package com.hms.covidData.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hms.covidData.entities.CountryData;

@Repository
public interface CountryDataRepo extends JpaRepository<CountryData, Integer>{
    
    CountryData findByCountryNameIgnoreCase(String name);
    Page<CountryData> findByWhoRegionIgnoreCase(String region, Pageable pageable);
    void deleteByCountryName(String name);
}
