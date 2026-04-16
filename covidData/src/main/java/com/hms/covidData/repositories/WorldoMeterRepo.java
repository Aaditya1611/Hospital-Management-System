package com.hms.covidData.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hms.covidData.entities.WorldoMeter;

@Repository
public interface WorldoMeterRepo extends JpaRepository<WorldoMeter, Integer> {

    List<WorldoMeter> findByContinent(String continent);

    List<WorldoMeter> findByRegion(String region);

    WorldoMeter findByCountry(String country);

    void deleteByCountry(String country);
    
}
