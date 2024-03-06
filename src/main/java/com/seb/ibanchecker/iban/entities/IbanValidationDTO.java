package com.seb.ibanchecker.iban.entities;

public class IbanValidationDTO extends IbanBaseDTO {
    private final Boolean status;

    IbanValidationDTO(String iban, Boolean status){
        super(iban);
        this.status = status;
    }
    
    public Boolean getStatus(){
        return this.status;
    }
}
