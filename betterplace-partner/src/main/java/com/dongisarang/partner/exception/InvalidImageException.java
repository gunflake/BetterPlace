package com.dongisarang.partner.exception;

public class InvalidImageException extends RuntimeException {
    public InvalidImageException() {
    }

    public InvalidImageException(String message) {
        super(message);
    }
}
