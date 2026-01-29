package com.car.infra.presentation.exception;

import com.car.core.usecases.car.exceptions.PlateAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(PlateAlreadyExistsException.class)
    public ResponseEntity<String> handlePlateAlreadyExistsException(PlateAlreadyExistsException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}
