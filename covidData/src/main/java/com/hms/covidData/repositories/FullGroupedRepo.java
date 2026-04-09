package com.hms.covidData.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.covidData.entities.FullGrouped;

public interface FullGroupedRepo extends JpaRepository<FullGrouped, Integer> {
    
}
