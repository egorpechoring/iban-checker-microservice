package com.seb.ibanchecker.iban.entities;

public class IbanProcessingException extends RuntimeException {
    public IbanProcessingException(String message) {
        super(message);
    }
}
