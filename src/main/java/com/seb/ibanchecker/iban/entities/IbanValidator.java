package com.seb.ibanchecker.iban.entities;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import com.seb.ibanchecker.util.ApplicationLogger;

public class IbanValidator {
    private static final BigInteger NINETY_SEVEN = new BigInteger("97");

    private static Map<String, IbanRule> countryRules = new HashMap<String, IbanRule>() {{
        put("EE", new IbanRule(20, "EE\\d{18}", 4, 6));
        put("LT", new IbanRule(20, "LT\\d{18}", 4, 9));
        // TODO: add others (in future)
    }};

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

    public static Boolean validateCheckNumber(String iban) {
        iban = normalize(iban);
        if (iban == null || iban.length() < 4) {
            return false;
        }

        final char[] buffer = new char[iban.length() * 2];

        int offset = modifyIbanDigitBuffer(iban, 4, iban.length(), buffer, 0);
        offset = modifyIbanDigitBuffer(iban, 0, 4, buffer, offset);

        final BigInteger sum = new BigInteger(new String(buffer, 0, offset));
        final BigInteger remainder = sum.remainder(NINETY_SEVEN);

        return remainder.intValue() == 1;
    }

    public static String normalize(String iban) {
        return (iban != null && !iban.isEmpty() && iban.length() >= 2) ? iban.replaceAll("\\s", "").toUpperCase() : null;
    }

    public static String extractCountryCode(String iban) {
        return (iban != null && !iban.isEmpty() && iban.length() >= 2) ? iban.substring(0, 2) : null;
    }

    private static int modifyIbanDigitBuffer(final CharSequence ibanStr, final int readFromIndex,
                                    final int readToIndex, final char[] place, int putToIndex) {
        for (int i = readFromIndex; i < readToIndex; i++) {
            char c = ibanStr.charAt(i);
            if (c >= '0' && c <= '9') {
                place[putToIndex++] = c;
            } else if (c >= 'A' && c <= 'Z') {
                int tmp = 10 + (c - 'A');
                place[putToIndex++] = (char)('0' + tmp / 10);
                place[putToIndex++] = (char)('0' + tmp % 10);
            } 
        }
        return putToIndex;
    }

    private static IbanRule getRule(String iban) {
        String countryCode = extractCountryCode(iban);
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
}
