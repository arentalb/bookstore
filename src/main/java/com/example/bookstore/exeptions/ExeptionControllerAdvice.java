package com.example.bookstore.exeptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExeptionControllerAdvice {

    @ExceptionHandler(NotAdminExeption.class)
    public ResponseEntity<ErrorDetail> exceptionNotAdminHandler(){
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setMessage("Not admin user ");
        return ResponseEntity
                .badRequest()
                .body(errorDetail);

    }
}

