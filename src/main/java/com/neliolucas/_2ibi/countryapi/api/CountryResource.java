package com.neliolucas._2ibi.countryapi.api;

import com.neliolucas._2ibi.countryapi.models.Country;
import com.neliolucas._2ibi.countryapi.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CountryResource {

    final
    CountryService countryService;

    public CountryResource(CountryService countryService) {
        this.countryService = countryService;
    }


    /**
     * Inserts a country entry into database
     * Ret
     * @param country request payload contain
     * @return request CREATED or CONFLICT request response with a response body of type String
     */
    @PostMapping("/country")
    public ResponseEntity<?> addCountry(@RequestBody Country country) {

            return countryService.addCountry(country) ?
                    ResponseEntity.status(HttpStatus.CREATED).body("Country '" + country.getName() + " added") :
                    ResponseEntity.status(HttpStatus.CONFLICT).body("Country '" + country.getName() + "' " +
                            "already exists");
    }


    @GetMapping("/country/all")
    public ResponseEntity<List<Country>> fetchAllCountries() {
        return ResponseEntity.status(HttpStatus.FOUND).body(countryService.findAll());
    }

    @GetMapping("/country/all/sortedBy/{property}")
    public ResponseEntity<List<Country>> fetchCountriesSortedByProperties(@PathVariable("property") String property) {
        return ResponseEntity.status(HttpStatus.FOUND).body(countryService.findAndSortAllByProperty(property));
    }

    @GetMapping("/country/{id}")
    public ResponseEntity<?> fetchSingleCountry(@PathVariable("id") long id) {
        Country foundCountry = countryService.findById(id);
        return foundCountry != null ? ResponseEntity.ok().body(foundCountry) : ResponseEntity.ok().body("No country with id '" + id + "' found");
    }


    @DeleteMapping("/country/{id}")
    public ResponseEntity<?> delteCountry(@PathVariable("id") long id) {
        return countryService.deleteCountry(id) ?
                ResponseEntity.ok().body("Country deleted") :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Unable to delete. Country not dound");
    }

    @PutMapping("/country/{id}")
    public ResponseEntity<?> updateSingleCountry(@PathVariable("id") long id, @RequestBody Country country) {

        return countryService.update(id, country) ?
                ResponseEntity.ok().body("Country '" + country.getName() + "' updated") :
                ResponseEntity.ok().body("No changes in country '" + country.getName() + "' found");
    }

}
