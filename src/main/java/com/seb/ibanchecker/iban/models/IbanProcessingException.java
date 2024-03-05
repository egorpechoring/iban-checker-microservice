package com.seb.ibanchecker.iban.models;

public class IbanProcessingException extends RuntimeException {
    public IbanProcessingException(String message) {
        super(message);
    }
}
