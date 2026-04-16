package com.hms.covidData.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hms.covidData.entities.CovidComplete;

@Repository
public interface CovidCompleteRepo extends JpaRepository<CovidComplete, Integer> {

    CovidComplete findByCountryIgnoreCase(String country);

    @Query("SELECT c FROM CovidComplete c WHERE " +
           "c.latitude BETWEEN :lat - 0.5 AND :lat + 0.5 AND " +
           "c.longitude BETWEEN :lon - 0.5 AND :lon + 0.5")
    List<CovidComplete> findByLocationNear(@Param("lat") BigDecimal lat, @Param("lon") BigDecimal lon);

    List<CovidComplete> findByRegion(String name);

    void deleteByCountry(String name);
    
}
