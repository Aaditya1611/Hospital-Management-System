package com.hms.covidData.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.covidData.entities.CovidComplete;

public interface CovidCompleteRepo extends JpaRepository<CovidComplete, Integer> {
    
}
