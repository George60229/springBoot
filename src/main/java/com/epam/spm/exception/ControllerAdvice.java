package com.epam.spm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(CertificateNotFoundException.class)
    public ResponseEntity<Response> handleException(CertificateNotFoundException e) {
        Response response = new Response(e.getMessage(),ErrorCode._40401);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(TagNotFoundException.class)
    public ResponseEntity<Response> handleException(TagNotFoundException e) {
        Response response = new Response(e.getMessage(),ErrorCode._40402);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}

