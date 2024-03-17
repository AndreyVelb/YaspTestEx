package com.velb.yasptestex.controller;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    public final ResponseEntity<Object> handleConstraintViolationEx(ConstraintViolationException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        StringBuilder stringBuilder = new StringBuilder();
        ex.getConstraintViolations().forEach(constraintViolation -> stringBuilder.append(constraintViolation.getMessage()));
        return new ResponseEntity<>(new ExceptionResponse(stringBuilder.toString()), headers, HttpStatus.BAD_REQUEST);
    }

}

