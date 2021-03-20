package com.neliolucas._2ibi.countryapi.api;

import com.neliolucas._2ibi.countryapi.models.Country;
import com.neliolucas._2ibi.countryapi.services.CountryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(value = "CountryResource")
public class CountryResource {

    @Autowired
    CountryService countryService;


    /**
     * Inserts a country record into database
     *
     * @param country valid country request body
     * @return return response code CREATED or CONFLICT
     */

    @ApiOperation(value = "Inserts a country record into database", tags = "addCountry")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 409, message = "Conflict"),})
    @PostMapping("/country")
    public ResponseEntity<String> addCountry(@Valid @RequestBody Country country) {

        return countryService.addCountry(country) ?
                ResponseEntity.status(HttpStatus.CREATED).body("Country '" + country.getName() + " added") :
                ResponseEntity.status(HttpStatus.CONFLICT).body("Country '" + country.getName() + "' " +
                        "already exists or is invalid");
    }


    /**
     * Fetches a list of all countries
     *
     * @return response code FOUND with array of countries in response body
     */
    @ApiOperation(value = "Fetches a list of all countries", tags = "fetchAllCountries")
    @ApiResponses(value = {
            @ApiResponse(code = 302, message = "Found"),
            @ApiResponse(code = 404, message = "Not Found"),})
    @GetMapping("/country/all")
    public ResponseEntity<List<Country>> fetchAllCountries() {

        List<Country> countries = countryService.findAll();
        return !countries.isEmpty() ?
                ResponseEntity.status(HttpStatus.FOUND).body(countries) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(countries);
    }

    /**
     * Fetches a list of countries sorted by country properties
     *
     * @param property country property
     * @return returns HTTP status FOUND if property is valid or BAD REQUEST if property is invalid
     */
    @ApiOperation(value = " Fetches a list of countries sorted by country properties", tags = "fetchCountriesSortedByProperties")
    @ApiResponses(value = {
            @ApiResponse(code = 302, message = "Found"),
            @ApiResponse(code = 400, message = "Bad Request"),})
    @GetMapping("/country/all/sortedBy/{property}")
    public ResponseEntity<List<Country>> fetchCountriesSortedByProperties(@PathVariable("property") String property) {

        List<Country> result = countryService.findAndSortAllByProperty(property);
        return !result.isEmpty() ?
                ResponseEntity.status(HttpStatus.FOUND).body(countryService.findAndSortAllByProperty(property)) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(countryService.findAndSortAllByProperty(property));
    }

    /**
     * Fetches a single country by ID
     *
     * @param id country ID
     * @return returns Http Status FOUND and response body of type country or HTTP status NOT FOUND
     */
    @ApiOperation(value = "Fetches a single country by ID", tags = "fetchSingleCountryById")
    @ApiResponses(value = {
            @ApiResponse(code = 302, message = "Found"),
            @ApiResponse(code = 404, message = "Not Found"),})
    @GetMapping("/country/id/{id}")
    public ResponseEntity<?> fetchSingleCountryById(@PathVariable("id") long id) {
        Country foundCountry = countryService.findById(id);
        return foundCountry != null ?
                ResponseEntity.status(HttpStatus.FOUND).body(foundCountry) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("No country with id '" + id + "' found");
    }

    /**
     * Fetches a single country by name
     *
     * @param name country ID
     * @return returns Http Status FOUND and response body of type country or HTTP status NOT FOUND
     */
    @ApiOperation(value = "Fetches a single country by name", tags = "fetchSingleCountryName")
    @ApiResponses(value = {
            @ApiResponse(code = 302, message = "Found"),
            @ApiResponse(code = 404, message = "Not Found"),})
    @GetMapping("/country/name/{name}")
    public ResponseEntity<?> fetchSingleCountryName(@PathVariable("name") String name) {
        Country foundCountry = countryService.findByName(name);
        return foundCountry != null ?
                ResponseEntity.status(HttpStatus.FOUND).body(foundCountry) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("No country named '" + name + "' found");
    }


    /**
     * Delete a single country by ID
     *
     * @param id country ID
     * @return returns Http status OK if succeeded or NOT FOUND if not found
     */
    @ApiOperation(value = " Delete a single country by ID", tags = "deleteCountry")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found"),})
    @DeleteMapping("/country/{id}")
    public ResponseEntity<String> deleteCountry(@PathVariable("id") long id) {
        return countryService.deleteCountry(id) ?
                ResponseEntity.ok().body("Country deleted") :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Unable to delete. Country not found");
    }


    /**
     * Update a single country By ID and request response of type country
     *
     * @param id      country ID
     * @param country request body of type country
     * @return returns HTTP Status OK if succeeded or HTTP Status NOT FOUND if no country with given ID exists
     */
    @ApiOperation(value = "Update a single country By ID and request response of type country",
            tags = "updateCountry")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Not Found"),})
    @PutMapping("/country/{id}")
    public ResponseEntity<String> updateCountry(@PathVariable("id") long id, @Valid @RequestBody Country country) {

        return countryService.update(id, country) ?
                ResponseEntity.ok().body("Country '" + country.getName() + "' updated") :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("No changes in '" + country.getName() + "' found");
    }


}
