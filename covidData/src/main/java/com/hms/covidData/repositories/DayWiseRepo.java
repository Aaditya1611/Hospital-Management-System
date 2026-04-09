package com.hms.covidData.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.covidData.entities.DayWiseData;

public interface DayWiseRepo extends JpaRepository<DayWiseData, Integer> {
    
}
