package com.seb.ibanchecker.iban;

import java.util.ArrayList;
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

    @Autowired
    public IbanService(IbanRepository repository) {
        this.repository = repository;
    }

    public List<IbanEntity> processIbans(List<String> strIbansList){
        // TODO: wrap in try catch
        List<IbanEntity> ibans = new ArrayList<>();
        if (strIbansList.size() == 0) {
            return ibans;
        }

        for(int i = 0; i < strIbansList.size(); i++){
            String iban = IbanValidator.normalize(strIbansList.get(i));

            Boolean isValid = IbanValidator.validatePattern(iban);
            if (isValid){
                isValid = IbanValidator.validateCheckNumber(iban);
            }

            String bank = repository.getBankByCode(IbanValidator.extractCountryCode(iban), IbanValidator.extractBankCode(iban));

            // TODO: create enum with messages?
            ibans.add(new IbanEntity(iban, isValid, bank != null ? bank : "-"));
        }
        
        return ibans;
    }
}
