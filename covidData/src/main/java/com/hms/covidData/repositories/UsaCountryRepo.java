package com.hms.covidData.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.covidData.entities.UsaCountry;

public interface UsaCountryRepo extends JpaRepository<UsaCountry, Integer>{
    
}
