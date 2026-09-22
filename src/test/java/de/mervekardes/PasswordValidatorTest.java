package de.mervekardes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordValidatorTest {

    @Test
    void shouldValidateMinimumLength() {
        assertFalse(PasswordValidator.hasMinLength("Abc1def", 8));
        assertTrue(PasswordValidator.hasMinLength("Abc1defg", 8));
        assertTrue(PasswordValidator.hasMinLength("Abc1defgh", 8));
    }

    @Test
    void shouldRejectEmptyPassword() {
        assertFalse(PasswordValidator.hasMinLength("", 8));
    }

    @Test
    void shouldRejectNullPassword() {
        assertFalse(PasswordValidator.hasMinLength(null, 8));
    }

    @Test
    void shouldReturnFalseWhenPasswordContainsNoDigit() {
        assertFalse(PasswordValidator.containsDigit("Abcdefgh"));
    }

    @Test
    void shouldReturnTrueWhenPasswordContainsOneDigit() {
        assertTrue(PasswordValidator.containsDigit("Abcdefg1"));
    }

    @Test
    void shouldReturnTrueWhenPasswordContainsMultipleDigits() {
        assertTrue(PasswordValidator.containsDigit("Ab1c2def"));
    }

    @Test
    void shouldReturnTrueWhenPasswordContainsOnlyDigits() {
        assertTrue(PasswordValidator.containsDigit("12345678"));
    }

    @Test
    void shouldIgnoreNonAsciiDigits() {
        assertFalse(PasswordValidator.containsDigit("Abcdefg\uFF11"));
    }

    @Test
    void shouldReturnFalseForNullWhenCheckingDigit() {
        assertFalse(PasswordValidator.containsDigit(null));
    }
}