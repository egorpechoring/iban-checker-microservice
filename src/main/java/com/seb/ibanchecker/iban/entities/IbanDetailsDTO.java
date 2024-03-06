package com.seb.ibanchecker.iban.entities;

public class IbanDetailsDTO {
    private final String iban;
    private final String bank;

    IbanDetailsDTO(String iban, String bank){
        this.iban = iban;
        this.bank = bank;
    }

    public String getIban(){
        return this.iban;
    }

    public String getStatus(){
        return this.bank;
    }
}
