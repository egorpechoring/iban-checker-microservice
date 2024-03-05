package com.seb.ibanchecker.iban.entities;

public class IbanRule {
    private final int length;
    private final String regexPattern;
    private final int bankCodeFromIndex;
    private final int bankCodeBeforeIndex;

    public IbanRule(int length, String regexPattern, int bankCodeFromIndex, int bankCodeBeforeIndex) {
        this.length = length;
        this.regexPattern = regexPattern;
        this.bankCodeFromIndex = bankCodeFromIndex;
        this.bankCodeBeforeIndex = bankCodeBeforeIndex;
    }

    public int getLength() {
        return length;
    }

    public String getRegexPattern() {
        return regexPattern;
    }

    public int getBankIndexStart() {
        return bankCodeFromIndex;
    }

    public int getBankIndexBefore() {
        return bankCodeBeforeIndex;
    }
}
