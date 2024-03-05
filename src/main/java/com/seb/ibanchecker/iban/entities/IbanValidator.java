package com.seb.ibanchecker.iban.entities;

public class IbanValidator {
    // hashmap for rules, hardcoded?!  

    public static String extractBankCode(String iban){
        throw new IbanProcessingException("public static String extractBankCode(String iban) not implemented yet");
    }

    public static Boolean validatePattern(String iban){
        throw new IbanProcessingException("public static Boolean isValid(String iban) not implemented yet");
    }

    public static Boolean validateCheckNumber(String iban){
        throw new IbanProcessingException("public static Boolean validateCheckNumber(String iban){ not implemented yet");
    }
}
