package com.hms.covidData.entities;

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
@Table(name = "full_grouped")
public class FullGrouped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (name = "date")
    private LocalDate date;

    @Column (name = "country")
    private String country;

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

    @Column (name = "who_region")
    private String region;
}
