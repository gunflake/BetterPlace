package com.dongisarang.user.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {NotFoundCustomerException.class})
    public ResponseEntity invalidAccount(NotFoundCustomerException ex, WebRequest request) {
        log.error("handling NotFoundCustomerException...");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다");
    }

}
