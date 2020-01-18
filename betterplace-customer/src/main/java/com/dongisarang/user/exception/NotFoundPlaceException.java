package com.dongisarang.user.exception;

public class NotFoundPlaceException extends RuntimeException{

    public NotFoundPlaceException() {
    }

    public NotFoundPlaceException(String message) {
        super(message+ " Place 정보를 찾을 수 없습니다.");
    }
}
