package com.seb.ibanchecker.iban.entities;

public class IbanBaseDTO {
    private final String iban;

    public IbanBaseDTO(String iban) {
        this.iban = iban;
    }

    public String getIban() {
        return this.iban;
    }
}
