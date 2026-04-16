package com.hms.covidData.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hms.covidData.entities.DayWiseData;
import com.hms.covidData.repositories.DayWiseRepo;

import jakarta.transaction.Transactional;

@Service
public class DayWiseService {

    @Autowired
    private DayWiseRepo dayWiseRepo;

    public Page<DayWiseData> findAllData(int page, int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("newCases").descending());
        return dayWiseRepo.findAll(pageable);
    }

    public List<DayWiseData> findByDate(LocalDate date) {

        return dayWiseRepo.findByDate(date);
    }

    @Transactional
    public void deleteByDate(LocalDate date) {
        dayWiseRepo.deleteByDate(date);
    }

}
