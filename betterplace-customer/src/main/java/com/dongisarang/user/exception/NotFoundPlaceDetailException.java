package com.dongisarang.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotFoundPlaceDetailException extends RuntimeException {
    public NotFoundPlaceDetailException() {
        super();
    }

    public NotFoundPlaceDetailException(String message) {
        super(message);
    }

    public NotFoundPlaceDetailException(String message, Throwable cause) {
        super(message, cause);
    }

}
