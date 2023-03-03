package com.example.employee.exception;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CentralExceptionHandler {
    @Autowired
    Environment environment;
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorInfo> handleBookNotFoundException(EmployeeNotFoundException ex){
        ErrorInfo info = new ErrorInfo();
        info.setErrorCode(HttpStatus.NOT_FOUND.value());
        info.setMessage(environment.getProperty("Book_Not_Found_Exception"));
        info.setTime(LocalDateTime.now());
        return new ResponseEntity<ErrorInfo>(info,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorInfo> exceptionHandler(ConstraintViolationException exception) {
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
        errorInfo.setTime(LocalDateTime.now());

        String errorMsg = exception.getConstraintViolations().stream().map((cv)->cv.getMessage()).collect(Collectors.joining(","));
        errorInfo.setMessage(errorMsg);

        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }
}
