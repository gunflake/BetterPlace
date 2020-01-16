package com.dongisarang.user.exception;

public class NotFoundPlaceException extends RuntimeException{

    public NotFoundPlaceException() {
    }

    public NotFoundPlaceException(String message) {
        super(message);
    }
}
