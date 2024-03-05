package com.seb.ibanchecker.iban.models;

import java.util.Arrays;
import java.util.List;

public class IbanDTOFactory {
    public static List<IbanValidationDTO> createValidationDTOs(List<IbanEntity> entity) {
        List<IbanValidationDTO> dtoList = Arrays.asList(
            new IbanValidationDTO("test1"),
            new IbanValidationDTO("test2")
        );
        return dtoList;
    }
    public static List<IbanDetailsDTO> createDetailsDTOs(List<IbanEntity> entity) {
        List<IbanDetailsDTO> dtoList = Arrays.asList(
            new IbanDetailsDTO(),
            new IbanDetailsDTO()
        );
        return dtoList;
    }
}
