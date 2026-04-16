package com.hms.covidData.repositories;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hms.covidData.entities.UsaCountry;

@Repository
public interface UsaCountryRepo extends JpaRepository<UsaCountry, Integer> {

    List<UsaCountry> findByProvince(String province);

    List<UsaCountry> findByDate(LocalDate date);

    @Query("SELECT c FROM UsaCountry c WHERE " +
            "c.latitude BETWEEN :lat - 0.5 AND :lat + 0.5 AND " +
            "c.longitude BETWEEN :lon - 0.5 AND :lon + 0.5")
    List<UsaCountry> findByLocationNear(@Param("lat") BigDecimal lat, @Param("lon") BigDecimal lon);

    UsaCountry findByAdmin(String name);

    void deleteByDate(LocalDate date);

}
