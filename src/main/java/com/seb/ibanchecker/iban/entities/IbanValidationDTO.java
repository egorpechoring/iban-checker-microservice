package com.seb.ibanchecker.iban.entities;

public class IbanValidationDTO {
    private final String iban;
    private final Boolean status;

    IbanValidationDTO(String iban, Boolean status){
        this.iban = iban;
        this.status = status;
    }

    public String getIban(){
        return this.iban;
    }

    public Boolean getStatus(){
        return this.status;
    }
}
