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
@Table(name = "usa_country_wise")
public class UsaCountry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (name = "iso2")
    private String isoTwo;

    @Column (name = "iso3")
    private String isoThree;

    @Column (name = "code3")
    private Long codeThree;

    @Column (name = "fips")
    private BigDecimal fips;

    @Column (name = "admin2")
    private String admin;
    
    @Column (name = "province")
    private String province;

    @Column (name = "country")
    private String country;

    @Column (name = "latitude")
    private BigDecimal latitude;

    @Column (name = "combined_key")
    private String combinedKey;

    @Column (name = "date")
    private LocalDate date;

    @Column (name = "confirmed")
    private Long confirmed;

    @Column (name = "deaths")
    private Long deaths;
}
