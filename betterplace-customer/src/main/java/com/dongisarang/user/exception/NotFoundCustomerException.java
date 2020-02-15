package com.dongisarang.user.exception;

public class NotFoundCustomerException extends RuntimeException {
    public NotFoundCustomerException() {
    }

    public NotFoundCustomerException(String message) {
        super(message + " 회원 정보를 찾을 수 없습니다.");
    }
}
