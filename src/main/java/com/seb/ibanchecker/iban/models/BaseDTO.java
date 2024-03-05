package com.seb.ibanchecker.iban.models;

import java.util.Arrays;
import java.util.List;

import com.seb.ibanchecker.util.ApplicationLogger;

public class BaseDTO {
    public static List<IbanValidationDTO> createValidationDTOs(List<IbanEntity> entity) {
        ApplicationLogger.info("DTO from ibans function works");
        List<IbanValidationDTO> dtoList = Arrays.asList(
            new IbanValidationDTO("test1"),
            new IbanValidationDTO("test2")
        );
        return dtoList;
    }
}
