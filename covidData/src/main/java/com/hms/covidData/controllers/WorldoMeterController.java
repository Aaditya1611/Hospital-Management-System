package com.hms.covidData.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.covidData.entities.WorldoMeter;
import com.hms.covidData.services.WorldoMeterService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/covidData/world")
public class WorldoMeterController {

    @Autowired
    private WorldoMeterService worldoMeterService;

    @GetMapping("/all")
    public ResponseEntity<Page<WorldoMeter>> getAllData(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        Page<WorldoMeter> data = worldoMeterService.findAllData(page, size);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/bycontinent/{continent}")
    public ResponseEntity<List<WorldoMeter>> getByContinet(@PathVariable String continent) {

        List<WorldoMeter> data = worldoMeterService.findByContinent(continent);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/byregion/{region}")
    public ResponseEntity<List<WorldoMeter>> getByRegion(@PathVariable String region) {

        List<WorldoMeter> data = worldoMeterService.findByWhoRegion(region);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/bycountry/{country}")
    public ResponseEntity<WorldoMeter> getByCountry(@PathVariable String country) {

        WorldoMeter data = worldoMeterService.findByCountry(country);
        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<?> deleteByCountryName(String name) {

        worldoMeterService.deleteByCountry(name);
        return ResponseEntity.ok("Deleted Successfully");
    }
}
