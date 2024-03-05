package com.seb.ibanchecker.iban;

public interface IbanRepository {
    void getBankByCode(String countryCode, String bankCode);
}
