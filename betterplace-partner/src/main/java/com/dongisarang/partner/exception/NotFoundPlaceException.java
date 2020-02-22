package com.dongisarang.partner.exception;

public class NotFoundPlaceException extends RuntimeException {
    public NotFoundPlaceException(String message) {
        super(message + "번 placeNo 정보가 없습니다.");
    }
}
