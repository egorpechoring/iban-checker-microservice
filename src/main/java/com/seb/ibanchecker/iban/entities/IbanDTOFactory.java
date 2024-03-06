package com.seb.ibanchecker.iban.entities;

import java.util.ArrayList;
import java.util.List;

public class IbanDTOFactory {
    public static List<IbanBaseDTO> createValidationDTOs(List<IbanEntity> ibans) {
        List<IbanBaseDTO> dtoList = new ArrayList<>();
        ibans.forEach(iban -> dtoList.add(
            new IbanValidationDTO(iban.getIbanString(), iban.getValidationStatus())));
        return dtoList;
    }
    public static List<IbanBaseDTO> createDetailsDTOs(List<IbanEntity> ibans) {
        List<IbanBaseDTO> dtoList = new ArrayList<>();
        ibans.forEach(iban -> dtoList.add(
            new IbanDetailsDTO(iban.getIbanString(), iban.getCorrespondingBank())));
        return dtoList;
    }
}
