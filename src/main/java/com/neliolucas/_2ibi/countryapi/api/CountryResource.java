package com.neliolucas._2ibi.countryapi.api;

import com.neliolucas._2ibi.countryapi.models.Country;
import com.neliolucas._2ibi.countryapi.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * @author Nelio
 * @date 18/03/2021
 */
@RestController
@RequestMapping("/api")
public class CountryResource {

    @Autowired
    CountryService countryService;


    /**
     * Inserts a country record into database
     *
     * @param country valid country request body
     * @return  return response code CREATED or CONFLICT
     */
    @PostMapping("/country")
    public ResponseEntity<String> addCountry(@Valid @RequestBody Country country) {

            return countryService.addCountry(country) ?
                    ResponseEntity.status(HttpStatus.CREATED).body("Country '" + country.getName() + " added") :
                    ResponseEntity.status(HttpStatus.CONFLICT).body("Country '" + country.getName() + "' " +
                            "already exists or is invalid");
    }


    /**
     * Fetches a list of all counties
     * @return response code 409 with array of countries in response body
     */
    @GetMapping("/country/all")
    public ResponseEntity<List<Country>> fetchAllCountries() {
        return ResponseEntity.status(HttpStatus.FOUND).body(countryService.findAll());
    }

    /**
     * Fetches a list of countries sorted by country properties
     * @param property country property
     * @return returns HTTP status FOUND if property is valid or BAD REQUEST if property is invalid
     */
    @GetMapping("/country/all/sortedBy/{property}")
    public ResponseEntity<List<Country>> fetchCountriesSortedByProperties(@PathVariable("property") String property) {

        List<Country> result = countryService.findAndSortAllByProperty(property);
        return !result.isEmpty() ?
                ResponseEntity.status(HttpStatus.FOUND).body(countryService.findAndSortAllByProperty(property)) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(countryService.findAndSortAllByProperty(property));
    }

    /**
     * Fetches a single country by ID
     * @param id country ID
     * @return returns Http Status FOUND and response body of type country or HTTP status NOT FOUND
     */
    @GetMapping("/country/{id}")
    public ResponseEntity<?> fetchSingleCountry(@PathVariable("id") long id) {
        Country foundCountry = countryService.findById(id);
        return foundCountry != null ?
                ResponseEntity.status(HttpStatus.FOUND).body(foundCountry) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("No country with id '" + id + "' found");
    }


    /**
     * Delete a single country by ID
     * @param id country ID
     * @return returns Http status OK if succeeded or NOT FOUND if not found
     */
    @DeleteMapping("/country/{id}")
    public ResponseEntity<String> deleteCountry(@PathVariable("id") long id) {
        return countryService.deleteCountry(id) ?
                ResponseEntity.ok().body("Country deleted") :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Unable to delete. Country not found");
    }

    /**
     * Update a single country By ID and request response of type country
     * @param id country ID
     * @param country request body of type country
     * @return returns HTTP Status OK if succeeded or HTTP Status NOT FOUND if no country with given ID exists
     */
    @PutMapping("/country/{id}")
    public ResponseEntity<String> updateSingleCountry(@PathVariable("id") long id, @Valid @RequestBody Country country) {

        return countryService.update(id, country) ?
                ResponseEntity.ok().body("Country '" + country.getName() + "' updated") :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("No changes in '" + country.getName() + "' found");
    }



}
