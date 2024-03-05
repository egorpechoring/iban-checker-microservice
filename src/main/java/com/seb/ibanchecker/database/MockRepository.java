package com.seb.ibanchecker.database;

import org.springframework.stereotype.Repository;

import com.seb.ibanchecker.iban.IbanRepository;
import com.seb.ibanchecker.iban.entities.IbanProcessingException;

@Repository
public class MockRepository implements IbanRepository {
    // Estonia: https://www.pangaliit.ee/settlements-and-standards/bank-codes
    // SEB - 10
    // Swedbank - 22
    // LHV - 77

    // Lithuania: https://www.lb.lt/en/iban-and-financial-institution-codes
    // SEB - 70440
    // Siauliu - 71800
    // Luminor - 40100

    @Override
    public void getBankByCode(String countryCode, String bankCode) {
        // TODO: with what banks solution will work with
        throw new IbanProcessingException("Unimplemented method 'getBankByCode'");
    }
    
}
