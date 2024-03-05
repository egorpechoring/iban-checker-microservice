package com.seb.ibanchecker.iban;

// import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seb.ibanchecker.iban.entities.IbanEntity;
import com.seb.ibanchecker.iban.entities.IbanProcessingException;
import com.seb.ibanchecker.iban.entities.IbanValidator;

@Service
public class IbanService {
    @Autowired
    private IbanRepository repository;

    IbanService(){}

    public List<IbanEntity> processIbans(List<String> ibansList){
        //return Collections.emptyList();
        // foreach string build iban using validator
        // call repo to get bank
        throw new IbanProcessingException("test"); 
    }
}
