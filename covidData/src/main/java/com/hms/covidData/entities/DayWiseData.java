package com.hms.covidData.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

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
@Table(name = "day_wise")
public class DayWiseData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (name = "date")
    private LocalDate date;

    @Column (name = "confirmed")
    private Long confirmed;

    @Column (name = "deaths")
    private Long deaths;

    @Column (name = "recovered")
    private Long recovered;

    @Column (name = "active")
    private Long active;

    @Column (name = "new_cases")
    private Long newCases;

    @Column (name = "new_deaths")
    private Long newDeaths;

    @Column (name = "new_recovered")
    private Long newRecovered;

    @Column (name = "deaths_per_100_cases")
    private BigDecimal deathsPerHundred;

    @Column (name = "recovered_per_100_cases")
    private BigDecimal recoveredPerHundred;

    @Column (name = "deaths_per_100_recovered")
    private BigDecimal deathsPerHundredRecovered;

    @Column (name = "countires_count")
    private Integer countriesCount;
}
