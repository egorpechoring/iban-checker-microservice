package com.seb.ibanchecker.iban.entities;

public class IbanValidationDTO {
    private final String test;

    IbanValidationDTO(String test){
        this.test = test;
    }

    public String getTestString(){
        return this.test;
    }

}
