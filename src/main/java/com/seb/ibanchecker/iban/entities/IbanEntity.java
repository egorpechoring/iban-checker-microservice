package com.seb.ibanchecker.iban.entities;

public class IbanEntity {
    private final String str;
    private final Boolean isValid;
    private final String bank;

    public IbanEntity(String str, Boolean isValid, String bank){
        this.str = str;
        this.isValid = isValid;
        this.bank = bank;
    }

    public String getString(){
        return this.str;
    }

    public Boolean getValidationStatus(){
        return this.isValid;
    }

    public String getCorrespondingBank(){
        return this.bank;
    }
}
