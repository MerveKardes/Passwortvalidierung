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
    @Test
    void shouldDetectUppercaseLetter() {
        assertTrue(PasswordValidator.containsUppercase("Abcdefg1"));
    }

    @Test
    void shouldReturnFalseWhenUppercaseIsMissing() {
        assertFalse(PasswordValidator.containsUppercase("abcdefg1"));
    }

    @Test
    void shouldReturnFalseForNullWhenCheckingUppercase() {
        assertFalse(PasswordValidator.containsUppercase(null));
    }

    @Test
    void shouldDetectLowercaseLetter() {
        assertTrue(PasswordValidator.containsLowercase("ABCDefg1"));
    }

    @Test
    void shouldReturnFalseWhenLowercaseIsMissing() {
        assertFalse(PasswordValidator.containsLowercase("ABCDEFG1"));
    }

    @Test
    void shouldReturnFalseForNullWhenCheckingLowercase() {
        assertFalse(PasswordValidator.containsLowercase(null));
    }
    @Test
    void shouldDetectCommonPassword() {
        assertTrue(PasswordValidator.isCommonPassword("Passwort1"));
    }

    @Test
    void shouldDetectCommonPasswordIgnoringCase() {
        assertTrue(PasswordValidator.isCommonPassword("PASSWORD"));
    }

    @Test
    void shouldDetectCommonPasswordIgnoringSurroundingSpaces() {
        assertTrue(PasswordValidator.isCommonPassword("  Passwort1  "));
    }

    @Test
    void shouldReturnFalseForUncommonPassword() {
        assertFalse(PasswordValidator.isCommonPassword("MySecurePassword1"));
    }

    @Test
    void shouldReturnFalseForNullWhenCheckingCommonPassword() {
        assertFalse(PasswordValidator.isCommonPassword(null));
    }

    @Test
    void shouldAcceptValidPassword() {
        assertTrue(PasswordValidator.isValid("Abcdef1g"));
    }

    @Test
    void shouldRejectPasswordShorterThanEightCharacters() {
        assertFalse(PasswordValidator.isValid("Abc1def"));
    }

    @Test
    void shouldRejectPasswordWithoutDigit() {
        assertFalse(PasswordValidator.isValid("Abcdefgh"));
    }

    @Test
    void shouldRejectPasswordWithoutUppercaseLetter() {
        assertFalse(PasswordValidator.isValid("abcdefg1"));
    }

    @Test
    void shouldRejectPasswordWithoutLowercaseLetter() {
        assertFalse(PasswordValidator.isValid("ABCDEFG1"));
    }

    @Test
    void shouldRejectCommonPassword() {
        assertFalse(PasswordValidator.isValid("Passwort1"));
    }

    @Test
    void shouldRejectPasswordContainingOnlyDigits() {
        assertFalse(PasswordValidator.isValid("98765432"));
    }

    @Test
    void shouldRejectNullPassword() {
        assertFalse(PasswordValidator.isValid(null));
    }

    @Test
    void shouldAcceptVeryLongValidPassword() {
        assertTrue(PasswordValidator.isValid(
                "ThisIsAVeryLongAndSecurePassword123456789"
        ));
    }
}