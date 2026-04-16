package com.hms.covidData.services;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.hms.covidData.entities.CountryData;
import com.hms.covidData.repositories.CountryDataRepo;

import org.springframework.transaction.annotation.Transactional;

@Service
public class CountryDataService {

    @Autowired
    private CountryDataRepo countryDataRepo;

    public CountryData getCountryData(String name) {

        return countryDataRepo.findByCountryNameIgnoreCase(name);
    }

    public Page<CountryData> getCountriesByWhoRegion(String name, int page, int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("confirmedCases").descending());
        return countryDataRepo.findByWhoRegionIgnoreCase(name, pageable);
    }

    public Page<CountryData> getAllCountires(int page, int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("confirmedCases").descending());
        return countryDataRepo.findAll(pageable);
    }

    @Transactional
    public CountryData updateNewCases(Integer id, Long addedCases) {

        CountryData data = countryDataRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Country not found"));

        data.setNewCases(data.getNewCases() + addedCases);
        data.setConfirmedCases(data.getConfirmedCases() + addedCases);
        data.setActiveCases(data.getActiveCases() + addedCases);

        return countryDataRepo.save(recalculateDependentFields(data));
    }

    @Transactional
    public CountryData updateNewDeaths(Integer id, Integer addedDeaths) {

        CountryData data = countryDataRepo.findById(id).orElseThrow(() -> new RuntimeException("Country not found"));

        data.setNewDeaths(data.getNewDeaths() + addedDeaths);
        data.setDeaths(data.getDeaths() + addedDeaths);

        return countryDataRepo.save(recalculateDependentFields(data));
    }

    @Transactional
    public CountryData updateNewRecovered(Integer id, Integer addedRecovered) {

        CountryData data = countryDataRepo.findById(id).orElseThrow(() -> new RuntimeException("Country not found"));

        data.setNewRecovered(data.getNewRecovered() + addedRecovered);
        data.setRecovered(data.getRecovered() + addedRecovered);

        return countryDataRepo.save(recalculateDependentFields(data));
    }

    @Transactional
    public CountryData deleteActiveCases(Integer id, Long active) {

        CountryData data = countryDataRepo.findById(id).orElseThrow(() -> new RuntimeException("Country not found"));

        data.setActiveCases(data.getActiveCases() - active);
        data.setWeeklyChange(data.getWeeklyChange() - active);
        return countryDataRepo.save(data);
    }

    @Transactional
    public CountryData recalculateDependentFields(CountryData data) {

        data.setActiveCases(data.getConfirmedCases() - data.getDeaths() - data.getRecovered());

        if (data.getConfirmedLastWeek() != null) {
            data.setWeeklyChange(data.getConfirmedCases() - data.getConfirmedLastWeek());
        }

        if (data.getConfirmedCases() > 0) {
            BigDecimal total = BigDecimal.valueOf(data.getConfirmedCases());

            data.setDeathsPerHundredCases(BigDecimal.valueOf(data.getDeaths())
                    .multiply(new BigDecimal(100))
                    .divide(total, 3, RoundingMode.HALF_UP));
            data.setRecoveredPerHundredCases(BigDecimal.valueOf(data.getRecovered())
                    .multiply(new BigDecimal(100))
                    .divide(total, 3, RoundingMode.HALF_UP));
        }
        return data;
    }

    @Transactional
    public void deleteByCountry(String name) {
        countryDataRepo.deleteByCountryName(name);
    }
}
