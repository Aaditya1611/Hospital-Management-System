package com.hms.covidData.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.covidData.entities.CountryData;
import com.hms.covidData.services.CountryDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/covidData/countrydata")
public class CountryDataController {

    @Autowired
    private CountryDataService countryDataService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @GetMapping("/all")
    public ResponseEntity<Page<CountryData>> getAllCountiresData(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        Page<CountryData> data = countryDataService.getAllCountires(page, size);
        if (data == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(data);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @GetMapping("/byname/{name}")
    public ResponseEntity<CountryData> getCountryDataByName(@PathVariable("name") String name) {

        String normalizedName = (name != null) ? name.trim() : "";
        CountryData data = countryDataService.getCountryData(normalizedName);
        if (data == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(data);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
    @GetMapping("/bywhoregion/{region}")
    public ResponseEntity<Page<CountryData>> getCountriesByRegion(@PathVariable("region") String name,
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

        String normalizedName = (name != null) ? name.trim() : "";
        Page<CountryData> data = countryDataService.getCountriesByWhoRegion(normalizedName, page, size);
        if (data == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(data);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PatchMapping("/addcase/{id}/cases")
    public ResponseEntity<?> addNewCase(@PathVariable("id") Integer id, @RequestParam Long count) {

        countryDataService.updateNewCases(id, count);
        return ResponseEntity.ok("New Case records updated");
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PatchMapping("/adddeaths/{id}/deaths")
    public ResponseEntity<?> addNewDeath(@PathVariable("id") Integer id, @RequestParam Integer count) {

        countryDataService.updateNewDeaths(id, count);
        return ResponseEntity.ok("Death case records updated");
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PatchMapping("/addrecovered/{id}/recovered")
    public ResponseEntity<?> addNewRecovered(@PathVariable("id") Integer id, @RequestParam Integer count) {

        countryDataService.updateNewRecovered(id, count);
        return ResponseEntity.ok("Recovered case records updated");
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PatchMapping("/reduceactive/{id}/active")
    public ResponseEntity<?> deleteActiveCases(@PathVariable("id") Integer id, @RequestParam Long count) {

        countryDataService.deleteActiveCases(id, count);
        return ResponseEntity.ok("Active case records updated");
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/delete/{name}")
    public ResponseEntity<?> deleteCountryByName(@PathVariable("name") String name) {

        countryDataService.deleteByCountry(name);
        return ResponseEntity.ok("Deleted successfully");
    }
}
