package com.hms.covidData.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.covidData.entities.WorldoMeter;

public interface WorldoMeterRepo extends JpaRepository<WorldoMeter, Integer> {
    
}
