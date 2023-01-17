package com.epam.spm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppControllerAdvice {

    @ExceptionHandler(AppNotFoundException.class)
    public ResponseEntity<Response> handleException(AppNotFoundException e) {
        Response response = new Response(e.getMessage(),e.getErrorCode().getCode());
        return new ResponseEntity<>(response,ErrorCode.CERTIFICATE_NOT_FOUND.getStatus());
    }


}

