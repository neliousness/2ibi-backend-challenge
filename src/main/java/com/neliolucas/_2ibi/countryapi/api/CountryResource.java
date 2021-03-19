package com.neliolucas._2ibi.countryapi.api;

import com.neliolucas._2ibi.countryapi.models.Country;
import com.neliolucas._2ibi.countryapi.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CountryResource {

    @Autowired
    CountryService countryService;


    /**
     * Inserts a country entry into database
     *
     * @param country request payload contain
     * @return request CREATED or CONFLICT request response with a response body of type String
     */
    @PostMapping("/country")
    public ResponseEntity<?> addCountry(@Valid @RequestBody Country country) {

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
        return foundCountry != null ?
                ResponseEntity.status(HttpStatus.FOUND).body(foundCountry) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("No country with id '" + id + "' found");
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
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("No changes in country '" + country.getName() + "' found");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
