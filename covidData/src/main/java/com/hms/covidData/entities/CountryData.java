package com.hms.covidData.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "country_wise")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CountryData {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "country_region")
    private String countryName;

    @Column(name = "confirmed")
    private Long confirmedCases;

    @Column(name = "deaths")
    private Long deaths;

    @Column(name = "recovered")
    private Long recovered;

    @Column(name = "active")
    private Long activeCases;

    @Column(name = "new_cases")
    private Long newCases;

    @Column(name = "new_deaths")
    private Integer newDeaths;

    @Column(name = "new_recovered")
    private Integer newRecovered;

    @Column(name = "deaths_per_100_cases")
    private BigDecimal deathsPerHundredCases;
    
    @Column(name = "recovered_per_100_cacses")
    private BigDecimal recoveredPerHundredCases;

    @Column(name = "deaths_per_100_recovered")
    private BigDecimal deathsPerHundredRecovered;
    
    @Column(name = "conf_last_week")
    private Long confirmedLastWeek;

    @Column(name = "weekly_change")
    private Long weeklyChange;
    
    @Column(name = "weekly_percent_inc")
    private BigDecimal weeklyPercentIncrease;

    @Column(name = "who_region")
    private String whoRegion;
}
