package com.cisco.orderapp.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        Map<String, Object> object = new HashMap<>();
        object.put("timestamp", new Date());
        object.put("message", ex.getMessage());

        return new ResponseEntity<>(object, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, Object> object = new HashMap<>();
        object.put("timestamp", new Date());
        List<String> errors = ex.getBindingResult()
                .getFieldErrors().stream()
                .map(exception -> exception.getDefaultMessage())
                .collect(Collectors.toList());
        object.put("errors", errors);
        return new ResponseEntity<>(object, HttpStatus.NOT_FOUND);
    }
}
