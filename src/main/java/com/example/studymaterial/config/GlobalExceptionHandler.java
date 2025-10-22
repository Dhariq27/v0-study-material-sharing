package com.example.studymaterial.config;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler for REST API endpoints
 * Ensures all exceptions return JSON responses instead of HTML error pages
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolation(DataIntegrityViolationException ex, WebRequest request) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        
        String message = ex.getMessage();
        if (message != null && message.contains("unique")) {
            response.put("message", "Username or email already exists");
        } else {
            response.put("message", "Data integrity violation: " + (ex.getMessage() != null ? ex.getMessage() : "Invalid data"));
        }
        response.put("error", "DataIntegrityViolationException");
        
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("message", ex.getMessage() != null ? ex.getMessage() : "An error occurred");
        response.put("error", ex.getClass().getSimpleName());
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("message", ex.getMessage());
        response.put("error", "IllegalArgumentException");
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
