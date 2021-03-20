package com.neliolucas._2ibi.countryapi.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
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

@Api(value = "ErrorHandlerController")
@RestControllerAdvice
public class ErrorHandlerController{

    /**
     * Handles invalid country request body for /api/country POST and PUT methods
     * @param ex
     * @return retuens error message
     */
    @ApiOperation(value = "Handles invalid country request body for /api/country POST and PUT methods", tags = "handleValidationExceptions")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ApiResponse(code = 400, message = "bad request")
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return   ResponseEntity.badRequest().body(errors);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<Map<String, String>> handleSortingExceptions(
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
