package com.seb.ibanchecker.iban.entities;

public class IbanValidator {
    // hashmap for rules, hardcoded?!

    // public static Rule get Rule By Country Code () ???

    public static Boolean validatePattern(String iban){
        throw new IbanProcessingException("public static Boolean isValid(String iban) not implemented yet");
    }

    public static Boolean validateCheckNumber(String iban){
        throw new IbanProcessingException("public static Boolean validateCheckNumber(String iban){ not implemented yet");
    }
}
