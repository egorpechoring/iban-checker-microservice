package com.seb.ibanchecker.iban;

public interface IbanRepository {
    String getBankByCode(String countryCode, String bankCode);
}
