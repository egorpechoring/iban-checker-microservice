package com.seb.ibanchecker.database;

import org.springframework.stereotype.Repository;

import com.seb.ibanchecker.iban.IbanRepository;
import com.seb.ibanchecker.iban.entities.IbanProcessingException;

@Repository
public class MockRepository implements IbanRepository {

    @Override
    public void getBankByCode(String code) {
        // TODO: with what banks solution will work with
        throw new IbanProcessingException("Unimplemented method 'getBankByCode'");
    }
    
}
