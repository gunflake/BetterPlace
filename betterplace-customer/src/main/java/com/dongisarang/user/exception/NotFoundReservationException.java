package com.dongisarang.user.exception;

public class NotFoundReservationException extends RuntimeException{
    public NotFoundReservationException() {
    }

    public NotFoundReservationException(String message) {
        super(message+"님의 예약 정보를 찾을 수 없습니다.");
    }
}
