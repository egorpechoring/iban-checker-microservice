package com.seb.ibanchecker.iban.entities;

import java.util.ArrayList;
import java.util.List;



public class IbanDTOFactory {
    public static List<IbanValidationDTO> createValidationDTOs(List<IbanEntity> ibans) {
        List<IbanValidationDTO> dtoList = new ArrayList<>();
        ibans.forEach(iban -> dtoList.add(
            new IbanValidationDTO(iban.getIbanString(), iban.getValidationStatus())));
        return dtoList;
    }
    public static List<IbanDetailsDTO> createDetailsDTOs(List<IbanEntity> ibans) {
        List<IbanDetailsDTO> dtoList = new ArrayList<>();
        ibans.forEach(iban -> dtoList.add(
            new IbanDetailsDTO(iban.getIbanString(), iban.getCorrespondingBank())));
        return dtoList;
    }
}
