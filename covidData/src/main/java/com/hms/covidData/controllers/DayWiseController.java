package com.hms.covidData.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.covidData.entities.DayWiseData;
import com.hms.covidData.services.DayWiseService;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/covidData/daywise")
public class DayWiseController {

    @Autowired
    private DayWiseService dayWiseService;
    
    @GetMapping("/all")
    public ResponseEntity<Page<DayWiseData>> getAllData(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        
        Page<DayWiseData> data = dayWiseService.findAllData(page, size);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/bydate/{date}")
    public ResponseEntity<List<DayWiseData>> getByDate(@PathVariable LocalDate date) {
        
        List<DayWiseData> data = dayWiseService.findByDate(date);
        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/delete/{date}")
    public ResponseEntity<?> deleteByDate(LocalDate date) {

        dayWiseService.deleteByDate(date);
        return ResponseEntity.ok("Deleted Successfully");
    }
    
    
}
