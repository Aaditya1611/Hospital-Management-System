package com.hms.covidData.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.covidData.entities.CovidComplete;
import com.hms.covidData.services.CovidCompleteService;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/covidData/covidcomplete")
public class CovidCompleteController {

    @Autowired
    private CovidCompleteService covidCompleteService;
    
    @GetMapping("/all")
    public ResponseEntity<Page<CovidComplete>> getAllData(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20")int size) {

        Page<CovidComplete> data = covidCompleteService.getAllCovidComplete(page, size);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/byname/{name}")
    public ResponseEntity<CovidComplete> getByCountryName(@PathVariable String name) {

        String normalizedName = (name != null) ? name.trim() : "";
        CovidComplete data = covidCompleteService.findByCountryName(normalizedName);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/bylocation/{longitude}/{latitude}")
    public ResponseEntity<List<CovidComplete>> getByLocation(@PathVariable("longitude") BigDecimal longitude, @PathVariable("latitude") BigDecimal latitude) {
        
        List<CovidComplete> data = covidCompleteService.findByLocation(longitude, latitude);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/byregion/{region}")
    public ResponseEntity<List<CovidComplete>> getByRegioEntity(@PathVariable("region") String region) {

        String regionName = (region != null) ? region.trim() : "";
        List<CovidComplete> data = covidCompleteService.findByWhoRegion(regionName);
        return ResponseEntity.ok(data);
    }
    
    @DeleteMapping("/delete{name}")
    public ResponseEntity<?> deleteCountryByName(String name) {

        covidCompleteService.deleteByCountry(name);
        return ResponseEntity.ok("Deleted Successfully");
    } 
}
