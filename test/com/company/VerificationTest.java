package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VerificationTest {

    @Test
    void shouldReturnThreeWhenCountingThreeDigitNumber() {
        assertFalse(Verification.verifyPhoneNumber("123"));
    }

    @Test
    void shouldReturnTenWhenCountingTenDigitNumber() {
        assertTrue(Verification.verifyPhoneNumber("1234567890"));
    }
}
