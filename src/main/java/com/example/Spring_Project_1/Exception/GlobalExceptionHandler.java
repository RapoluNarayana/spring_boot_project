package com.example.Spring_Project_1.Exception;

import com.example.Spring_Project_1.CustomException.EmployeeNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.NameNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleEntityNotFoundException(EntityNotFoundException entityNotFoundException){
        return entityNotFoundException.getMessage();
    }
    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public String handleDuplicate(DuplicateKeyException duplicateKeyException){
        return duplicateKeyException.getMessage();
    }
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public String handleIllegalArgumentException(IllegalArgumentException illegalArgumentException){
        return illegalArgumentException.getMessage();
    }
    @ExceptionHandler(NameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNameNotFoundException(NameNotFoundException nameNotFoundException){
        return nameNotFoundException.getMessage();
    }
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED)
    public String handleRuntimeException(RuntimeException runtimeException){
        return runtimeException.getMessage();
    }
    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleEmployeeNotFoundException(EmployeeNotFoundException employeeNotFoundException){
        return employeeNotFoundException.getMessage();
    }
}
