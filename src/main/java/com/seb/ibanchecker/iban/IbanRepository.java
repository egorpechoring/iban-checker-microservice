package com.seb.ibanchecker.iban;

public interface IbanRepository {
    void getBankByCode(String code);
}
