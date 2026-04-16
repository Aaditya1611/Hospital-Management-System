package com.hms.covidData.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.covidData.services.FullGroupedService;
import com.hms.covidData.entities.FullGrouped;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/covidData/fullgroup")
public class FullGroupedController {

    @Autowired
    private FullGroupedService fullGroupedService;
    
    @GetMapping("/all")
    public ResponseEntity<Page<FullGrouped>> getMethodName(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        
        Page<FullGrouped> data = fullGroupedService.findAllData(page, size);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/byname/{name}")
    public ResponseEntity<FullGrouped> getByName(@PathVariable("name") String name) {
        
        String normalizedName = (name != null) ? name.trim() : "";
        FullGrouped data = fullGroupedService.findByCountryName(normalizedName);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/byregion/{region}")
    public ResponseEntity<List<FullGrouped>> getByRegion(@PathVariable("region") String region) {
        
        String normalizedRegion = (region != null) ? region.trim() : "";
        List<FullGrouped> data = fullGroupedService.findByRegion(normalizedRegion);
        return ResponseEntity.ok(data);
    }
    
    @DeleteMapping("/delete/{name}")
    public ResponseEntity<?> deleteByCountryName(String name) {

        fullGroupedService.deleteByCountry(name);
        return ResponseEntity.ok("Deleted Successfully");
    }
}
