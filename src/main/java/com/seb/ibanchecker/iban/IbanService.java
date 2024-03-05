package com.seb.ibanchecker.iban;

// import java.util.Collections;
import java.util.List;

import com.seb.ibanchecker.iban.models.IbanEntity;
import com.seb.ibanchecker.iban.models.IbanProcessingException;

public class IbanService {
    // ...

    IbanService(){}

    public List<IbanEntity> processIbans(List<String> ibansList){
        //return Collections.emptyList();
        throw new IbanProcessingException("test"); 
    }
}
