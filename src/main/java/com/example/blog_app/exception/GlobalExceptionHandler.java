package com.example.blog_app.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.blog_app.dto.ApiResponse;

/**
 * Global exception handler to handle application-wide exceptions.
 * Uses @ControllerAdvice to intercept exceptions from all controllers.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles validation exceptions for method arguments annotated with @Valid.
     *
     * @param ex the MethodArgumentNotValidException containing validation errors
     * @return a ResponseEntity containing an ApiResponse with validation error details and a BAD_REQUEST (400) status
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // Map to store field-specific validation error messages
        Map<String, String> errors = new HashMap<>();

         // Populate the errors map with field names and corresponding error messages
        ex.getBindingResult().getFieldErrors().forEach(error ->
            errors.put(error.getField(), error.getDefaultMessage())
        );

        // Return a response entity with the error details and HTTP status 400
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>("Validation failed", errors, 400));
    }
}
