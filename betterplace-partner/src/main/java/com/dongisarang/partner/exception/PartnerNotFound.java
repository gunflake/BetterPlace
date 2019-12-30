package com.dongisarang.partner.exception;

public class PartnerNotFound extends RuntimeException{
    public PartnerNotFound() {
        super();
    }

    public PartnerNotFound(String message) {
        super(message);
    }
}
