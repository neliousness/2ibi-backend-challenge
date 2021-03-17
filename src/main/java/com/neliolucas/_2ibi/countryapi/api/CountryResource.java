package com.neliolucas._2ibi.countryapi.api;

import com.neliolucas._2ibi.countryapi.models.Country;
import com.neliolucas._2ibi.countryapi.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class CountryResource {

    @Autowired
    CountryService countryService;

    @PostMapping("/country")
    public ResponseEntity<?> addCountry(@RequestBody Country country)
    {
        if (countryService.addCountry(country))
        {
            return ResponseEntity.status(HttpStatus.CREATED).body("Country '" + country.getName() + " added");
        }
        else
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Country '" + country.getName() + "' already exists");
        }
    }
}
