package com.seb.ibanchecker.iban.entities;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import com.seb.ibanchecker.util.ApplicationLogger;

public class IbanValidator {
    private static Map<String, IbanRule> countryRules = new HashMap<String, IbanRule>() {{
        put("EE", new IbanRule(20, "EE\\d{18}", 4, 6));
        put("LT", new IbanRule(20, "LT\\d{18}", 4, 9));
        // TODO: add others (in future)
    }};

    // TODO: find a way to refactor extractBankCode & validatePattern

    public static String extractBankCode(String iban){
        iban = normalize(iban);
        if (iban == null || iban.length() == 0) {
            return null;
        }
        IbanRule rules = getRule(iban);
        if(rules == null || rules.getLength() != iban.length()){
            return null;
        }
        return iban.substring(rules.getBankIndexStart(), rules.getBankIndexBefore());
    }

    public static Boolean validatePattern(String iban){
        iban = normalize(iban);
        if (iban == null || iban.length() == 0) {
            return false;
        }
        IbanRule rules = getRule(iban);
        if(rules == null || rules.getLength() != iban.length()){
            return false;
        }
        try {
            Pattern pattern = Pattern.compile(rules.getRegexPattern());
            Matcher matcher = pattern.matcher(iban);
            return matcher.matches();
        } catch (PatternSyntaxException e) {
            ApplicationLogger.warning("Error in regex pattern: " + e.getMessage());
        }
        return false;
    }

    public static Boolean validateCheckNumber(String iban){
        throw new IbanProcessingException("public static Boolean validateCheckNumber(String iban){ not implemented yet");
    }

    private static IbanRule getRule(String iban) {
        String countryCode = substringCountryCode(iban);
        IbanRule rules = null;
    
        if (countryRules != null) {
            try {
                rules = countryRules.get(countryCode);
            } catch (NullPointerException e) {
                ApplicationLogger.warning("Attempt to get rule by " + countryCode + " country code: " + e.getMessage());
            }
        } else {
            ApplicationLogger.warning("getRules: countryRules is null.");
        }
    
        return rules;
    }

    private static String normalize(String iban) {
        return (iban != null && !iban.isEmpty()) ? iban.replaceAll("\\s", "").toUpperCase() : null;
    }
    
    private static String substringCountryCode(String iban) {
        return (iban != null && iban.length() >= 2) ? iban.substring(0, 2) : null;
    }

}
