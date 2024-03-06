package com.seb.ibanchecker.iban.entities;

public class IbanDetailsDTO extends IbanBaseDTO {
    private final String bank;

    IbanDetailsDTO(String iban, String bank){
        super(iban);
        this.bank = bank;
    }
    
    public String getBank(){
        return this.bank;
    }
}
