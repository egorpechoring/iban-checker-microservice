package com.seb.ibanchecker.iban.models;

public class IbanNotStringException extends RuntimeException {
    IbanNotStringException(String msg){
        super(msg);
    }
}
