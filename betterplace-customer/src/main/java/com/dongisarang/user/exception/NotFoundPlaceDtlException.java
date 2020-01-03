package com.dongisarang.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotFoundPlaceDtlException extends RuntimeException {
    public NotFoundPlaceDtlException() {
        super();
    }

    public NotFoundPlaceDtlException(String message) {
        super(message);
    }

    public NotFoundPlaceDtlException(String message, Throwable cause) {
        super(message, cause);
    }

}
