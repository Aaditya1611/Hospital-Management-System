package com.hms.covidData.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hms.covidData.entities.CountryData;
import com.hms.covidData.entities.DayWiseData;

@Repository
public interface DayWiseRepo extends JpaRepository<DayWiseData, Integer> {
 
    List<DayWiseData> findByDate(LocalDate date);

    void deleteByDate(LocalDate date);
}
