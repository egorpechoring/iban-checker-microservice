package com.seb.ibanchecker.iban.entities;

import java.util.Arrays;
import java.util.List;

import com.seb.ibanchecker.util.ApplicationLogger;


public class IbanDTOFactory {
    public static List<IbanValidationDTO> createValidationDTOs(List<IbanEntity> entity) {
        ApplicationLogger.info("received entities "+entity.size());
        List<IbanValidationDTO> dtoList = Arrays.asList(
            new IbanValidationDTO("test1"),
            new IbanValidationDTO("test2")
        );
        return dtoList;
    }
    public static List<IbanDetailsDTO> createDetailsDTOs(List<IbanEntity> entity) {
        ApplicationLogger.info("received entities "+entity.size());
        List<IbanDetailsDTO> dtoList = Arrays.asList(
            new IbanDetailsDTO(),
            new IbanDetailsDTO()
        );
        return dtoList;
    }
}
