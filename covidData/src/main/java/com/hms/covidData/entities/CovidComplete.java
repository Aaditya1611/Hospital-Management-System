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
@Table(name = "cov19_complete")
public class CovidComplete {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (name = "province")
    private String province;

    @Column (name = "country")
    private String country;

    @Column (name = "latitude")
    private BigDecimal latitude;

    @Column (name = "longitude")
    private BigDecimal longitude;

    @Column (name = "date")
    private LocalDate date;

    @Column (name = "confirmed")
    private Long confirmed;

    @Column (name = "death")
    private Long death;

    @Column (name = "recovered")
    private Long recovered;

    @Column (name = "active")
    private Long active;

    @Column (name = "who_region")
    private String region;
}
