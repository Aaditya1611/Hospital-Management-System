package com.hms.covidData.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hms.covidData.entities.FullGrouped;

@Repository
public interface FullGroupedRepo extends JpaRepository<FullGrouped, Integer> {

    List<FullGrouped> findByRegion(String region);

    FullGrouped findByCountry(String name);

    void deleteByCountry(String name);
    
}
