package com.fullstack.ecom_spring_back.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        // return the exception message as response body
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentTypeMismatchException ex) {
        Map<String, String> errors = new HashMap<>();

        if(ex.getRequiredType() != null && ex.getRequiredType().isEnum()) {
            Class<?> enumType = ex.getRequiredType();
            String allowedValues = Arrays.stream(enumType.getEnumConstants())
                    .map(Object::toString)
                    .collect(Collectors.joining(", "));
            errors.put("error", "Invalid value for " + ex.getName());
            errors.put("message", "Allowed values are: " + allowedValues);
        } else {
            errors.put("error", "Invalid Parameter");
            errors.put("message", ex.getMessage());
        }

        return ResponseEntity.badRequest().body(errors);
    }

    //404 URL handler
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoHandlerFoundException(NoResourceFoundException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", "Not Found");
        errors.put("message", "This URL was not found on this server");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }
}
