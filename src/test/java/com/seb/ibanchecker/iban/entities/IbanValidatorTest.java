package com.seb.ibanchecker.iban.entities;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class IbanValidatorTest {
    // Estonian IBANs
    // valid - EE541010846630271646
    // valid - EE651015872655548611

    @Test
    void testValidatePattern() {
        assertTrue(!IbanValidator.validatePattern("EE5&101048%632071646"));     // - symbols not allowed
        assertTrue(!IbanValidator.validatePattern(""));                         // - empty
        assertTrue(!IbanValidator.validatePattern(" "));                        // - empty
        assertTrue(!IbanValidator.validatePattern(null));                       // - empty
        assertTrue(!IbanValidator.validatePattern("CC541010846630271646"));     // - county code does not exists
    }

    @Test 
    void testValidatePattern_Estonia(){
        assertTrue( IbanValidator.validatePattern("EE541010846630271646"));     // + valid
        assertTrue(!IbanValidator.validatePattern("EE54101084663027164635"));   // - too long
        assertTrue(!IbanValidator.validatePattern("EE5410108466302716"));       // - too short
        assertTrue( IbanValidator.validatePattern("EE541010486632071646"));     // + iban is not valid, but fits the pattern
        assertTrue( IbanValidator.validatePattern("EE641010846630271646"));     // + check code not valid, but fits the pattern 
    }

    @Test
    void testValidateCheckNumber() {
        assertTrue(!IbanValidator.validateCheckNumber("EE5&101048%632071646"));     // - symbols not allowed
        assertTrue(!IbanValidator.validateCheckNumber(""));                         // - empty
        assertTrue(!IbanValidator.validateCheckNumber(" "));                        // - empty
        assertTrue(!IbanValidator.validateCheckNumber(null));                       // - empty
        assertTrue(!IbanValidator.validateCheckNumber("CC541010846630271646"));     // - county code does not exists
    }

    @Test
    void testValidateCheckNumber_Estonia() {
        assertTrue( IbanValidator.validateCheckNumber("EE651015872655548611"));     // + valid
        assertTrue( IbanValidator.validateCheckNumber("EE541010846630271646"));     // + valid
        assertTrue(!IbanValidator.validateCheckNumber("EE881010846630271646"));     // - invalid check code
        assertTrue(!IbanValidator.validateCheckNumber("EE54101084663027164635"));   // - wrong pattern
        assertTrue(!IbanValidator.validateCheckNumber("EE5410108466302716"));       // - wrong pattern
        assertTrue(!IbanValidator.validateCheckNumber("EE541010486632071646"));     // - swapped numbers
    }

    @Test
    void testExtractBankCode(){
        assertTrue(0==IbanValidator.extractBankCode(null).length());    // - 
        assertTrue(0==IbanValidator.extractBankCode("").length());      // - 
        assertTrue(0==IbanValidator.extractBankCode(" ").length());     // - 
        assertTrue(0==IbanValidator.extractBankCode("&%^").length());   // - 
    }

    @Test
    void testExtractBankCode_Estonia(){
        assertTrue("SEB"==IbanValidator.extractBankCode("EE651015872655548611"));
        assertTrue("Swedbank"==IbanValidator.extractBankCode("EE652215872655548611"));
        assertTrue("LHV"==IbanValidator.extractBankCode("EE657715872655548611"));
        assertTrue(0==IbanValidator.extractBankCode("EE653315872655548611").length());  // 33 bank code does not exist at all
        assertTrue(0==IbanValidator.extractBankCode("EE654215872655548611").length());  // Coop existsbut does not supported in the solution yet
        assertTrue(0==IbanValidator.extractBankCode("EE653A315872655548611").length()); // letter A on place where digit should be
        assertTrue("SEB"==IbanValidator.extractBankCode("EE0010158726555486AA"));       // wrong iban, but valid SEB code
    }
}
