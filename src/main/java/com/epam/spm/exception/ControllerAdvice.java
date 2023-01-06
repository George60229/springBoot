package com.epam.spm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Response> handleException(IllegalArgumentException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}

