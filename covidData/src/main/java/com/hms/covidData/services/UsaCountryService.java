package com.hms.covidData.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hms.covidData.entities.UsaCountry;
import com.hms.covidData.repositories.UsaCountryRepo;

import jakarta.transaction.Transactional;

@Service
public class UsaCountryService {
    
    @Autowired
    private UsaCountryRepo usaCountryRepo;

    public Page<UsaCountry> findAllData(int page, int size) {
        
        Pageable pageable = PageRequest.of(page, size, Sort.by("confirmed").descending());
        return usaCountryRepo.findAll(pageable);
    }

    public List<UsaCountry> findByProvince(String province) {

        return usaCountryRepo.findByProvince(province);
    }

    public List<UsaCountry> findByDate(LocalDate date) {

        return usaCountryRepo.findByDate(date);
    }

    public List<UsaCountry> findByLocation(BigDecimal longitude, BigDecimal latitude) {

        return usaCountryRepo.findByLocationNear(latitude, longitude);
    }

    public UsaCountry findByAdmin(String name) {

        return usaCountryRepo.findByAdmin(name);
    }

     @Transactional
    public void deleteByDate(LocalDate date) {
        usaCountryRepo.deleteByDate(date);
    }
}
