package com.hms.covidData.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "worldometer_data")
public class WorldoMeter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (name = "country")
    private String country;

    @Column (name = "continent")
    private String continent;

    @Column (name = "population")
    private Long population;

    @Column (name = "totalcases")
    private Long totalCases;

    @Column (name = "new_cases")
    private Long newCases;

    @Column (name = "total_deaths")
    private Long totalDeaths;

    @Column (name = "total_recovered")
    private Long totalRecovered;

    @Column (name = "new_recovered")
    private Long newRecovered;

    @Column (name = "active_cases")
    private Long activeCases;

    @Column (name = "critical_cases")
    private Long criticalCases;

    @Column (name = "total_cases_per_mil_pop")
    private BigDecimal totalCasesPerMil;

    @Column (name = "deaths_per_mil_pop")
    private BigDecimal deathPerMil;

    @Column (name = "total_test")
    private Long totalTest;

    @Column (name = "tests_per_mil_pop")
    private BigDecimal testPerMil;

    @Column (name = "who_region")
    private String region;
}
