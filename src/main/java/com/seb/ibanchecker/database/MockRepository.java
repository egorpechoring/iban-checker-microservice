package com.seb.ibanchecker.database;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.seb.ibanchecker.iban.IbanRepository;
import com.seb.ibanchecker.util.ApplicationLogger;

@Repository
public class MockRepository implements IbanRepository {
    private final Map<String, Map<String, String>> banksByCountry = new HashMap<String, Map<String, String>>() {{
        put("EE", new HashMap<String, String>() {{
            put("10", "SEB");
            put("22", "Swedbank");
            put("77", "LHV");
        }});
        put("LT", new HashMap<String, String>() {{
            put("70440", "SEB");
            put("71800", "Siauliu");
            put("40100", "Luminor");
        }});
    }};

    @Override
    public String getBankByCode(String countryCode, String bankCode) {
        if (banksByCountry.containsKey(countryCode)) {
            Map<String, String> banks = banksByCountry.get(countryCode);
            if (banks.containsKey(bankCode)) {
                return banks.get(bankCode);
            } else {
                ApplicationLogger.warning("Bank with code '" + bankCode + "' not found in country '" + countryCode);
            }
        } else {
            ApplicationLogger.warning("Country with code '" + countryCode + "' was not found '");
        }
        return null;
    }
}
