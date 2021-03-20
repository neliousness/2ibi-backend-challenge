package com.neliolucas._2ibi.countryapi.controllers;

import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Nelio
 * @date 19/03/2021
 */

@RestControllerAdvice
public class ErrorHandlerController{

    /**
     * Handles invalid country request body for /api/country POST and PUT methods
     * @param ex
     * @return retuens error message
     */
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

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<?> handleSortingExceptions(
            PropertyReferenceException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", "Invalid property");

        try {
            errors.put("message", ex.getMessage() );
        }
        catch (NullPointerException ignored)
        {

        }

        return ResponseEntity.badRequest().body(errors);
    }


}
