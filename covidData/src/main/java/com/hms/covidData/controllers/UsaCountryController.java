package com.hms.covidData.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.covidData.entities.UsaCountry;
import com.hms.covidData.services.UsaCountryService;

import java.math.BigDecimal;
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
@RequestMapping("/covidData/usa")
public class UsaCountryController {

    @Autowired
    private UsaCountryService usaCountryService;

    @GetMapping("/all")
    public ResponseEntity<Page<UsaCountry>> getAllData(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        Page<UsaCountry> data = usaCountryService.findAllData(page, size);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/byprovince/{province}")
    public ResponseEntity<List<UsaCountry>> getByProvince(@PathVariable("province") String province) {

        String normalizedProvince = (province != null) ? province.trim() : "";
        List<UsaCountry> data = usaCountryService.findByProvince(normalizedProvince);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/bydate/{date}")
    public ResponseEntity<List<UsaCountry>> getByProvince(@PathVariable("date") LocalDate date) {

        List<UsaCountry> data = usaCountryService.findByDate(date);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/bylocation/{longitude}/{latitude}")
    public ResponseEntity<List<UsaCountry>> getByLocation(@PathVariable("longitude") BigDecimal longitude,
            @PathVariable("latitude") BigDecimal latitude) {

        List<UsaCountry> data = usaCountryService.findByLocation(longitude, latitude);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/byadmin/{admin}")
    public ResponseEntity<UsaCountry> getByAdmin(@PathVariable("admin") String admin) {

        UsaCountry data = usaCountryService.findByAdmin(admin);
        return ResponseEntity.ok(data);
    }

    @DeleteMapping("/delete/{date}")
    public ResponseEntity<?> deleteByDate(LocalDate date) {

        usaCountryService.deleteByDate(date);
        return ResponseEntity.ok("Deleted Successfully");
    }
}
