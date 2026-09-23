package de.mervekardes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordValidatorTest {

    @Test
    void hasMinLength_returnsExpectedResult_forDifferentLengths() {
        assertFalse(PasswordValidator.hasMinLength("Abc1def", 8));
        assertTrue(PasswordValidator.hasMinLength("Abc1defg", 8));
        assertTrue(PasswordValidator.hasMinLength("Abc1defgh", 8));
    }

    @Test
    void hasMinLength_returnsFalse_whenPasswordIsEmpty() {
        assertFalse(PasswordValidator.hasMinLength("", 8));
    }

    @Test
    void hasMinLength_returnsFalse_whenPasswordIsNull() {
        assertFalse(PasswordValidator.hasMinLength(null, 8));
    }

    @Test
    void containsDigit_returnsFalse_whenPasswordContainsNoDigit() {
        assertFalse(PasswordValidator.containsDigit("Abcdefgh"));
    }

    @Test
    void containsDigit_returnsTrue_whenPasswordContainsOneDigit() {
        assertTrue(PasswordValidator.containsDigit("Abcdefg1"));
    }

    @Test
    void containsDigit_returnsTrue_whenPasswordContainsMultipleDigits() {
        assertTrue(PasswordValidator.containsDigit("Ab1c2def"));
    }

    @Test
    void containsDigit_returnsTrue_whenPasswordContainsOnlyDigits() {
        assertTrue(PasswordValidator.containsDigit("12345678"));
    }

    @Test
    void containsDigit_returnsFalse_whenPasswordContainsOnlyNonAsciiDigit() {
        assertFalse(PasswordValidator.containsDigit("Abcdefg\uFF11"));
    }

    @Test
    void containsDigit_returnsFalse_whenPasswordIsNull() {
        assertFalse(PasswordValidator.containsDigit(null));
    }

    @Test
    void containsUppercase_returnsTrue_whenUppercaseLetterExists() {
        assertTrue(PasswordValidator.containsUppercase("Abcdefg1"));
    }

    @Test
    void containsUppercase_returnsFalse_whenUppercaseLetterIsMissing() {
        assertFalse(PasswordValidator.containsUppercase("abcdefg1"));
    }

    @Test
    void containsUppercase_returnsFalse_whenPasswordIsNull() {
        assertFalse(PasswordValidator.containsUppercase(null));
    }

    @Test
    void containsLowercase_returnsTrue_whenLowercaseLetterExists() {
        assertTrue(PasswordValidator.containsLowercase("ABCDefg1"));
    }

    @Test
    void containsLowercase_returnsFalse_whenLowercaseLetterIsMissing() {
        assertFalse(PasswordValidator.containsLowercase("ABCDEFG1"));
    }

    @Test
    void containsLowercase_returnsFalse_whenPasswordIsNull() {
        assertFalse(PasswordValidator.containsLowercase(null));
    }

    @Test
    void isCommonPassword_returnsTrue_whenPasswordIsInCommonList() {
        assertTrue(PasswordValidator.isCommonPassword("Passwort1"));
    }

    @Test
    void isCommonPassword_returnsTrue_whenLetterCaseIsDifferent() {
        assertTrue(PasswordValidator.isCommonPassword("PASSWORD"));
    }

    @Test
    void isCommonPassword_returnsTrue_whenPasswordHasSurroundingSpaces() {
        assertTrue(PasswordValidator.isCommonPassword("  Passwort1  "));
    }

    @Test
    void isCommonPassword_returnsFalse_whenPasswordIsNotInCommonList() {
        assertFalse(PasswordValidator.isCommonPassword("MySecurePassword1"));
    }

    @Test
    void isCommonPassword_returnsFalse_whenPasswordIsNull() {
        assertFalse(PasswordValidator.isCommonPassword(null));
    }

    @Test
    void isValid_returnsTrue_whenPasswordIsValid() {
        assertTrue(PasswordValidator.isValid("Abcdef1!"));
    }

    @Test
    void isValid_returnsFalse_whenPasswordIsTooShort() {
        assertFalse(PasswordValidator.isValid("Abc1def"));
    }

    @Test
    void isValid_returnsFalse_whenDigitIsMissing() {
        assertFalse(PasswordValidator.isValid("Abcdefgh"));
    }

    @Test
    void isValid_returnsFalse_whenUppercaseLetterIsMissing() {
        assertFalse(PasswordValidator.isValid("abcdefg1"));
    }

    @Test
    void isValid_returnsFalse_whenLowercaseLetterIsMissing() {
        assertFalse(PasswordValidator.isValid("ABCDEFG1"));
    }

    @Test
    void isValid_returnsFalse_whenPasswordIsCommon() {
        assertFalse(PasswordValidator.isValid("Passwort1"));
    }

    @Test
    void isValid_returnsFalse_whenPasswordContainsOnlyDigits() {
        assertFalse(PasswordValidator.isValid("98765432"));
    }

    @Test
    void isValid_returnsFalse_whenPasswordIsNull() {
        assertFalse(PasswordValidator.isValid(null));
    }

    @Test
    void isValid_returnsTrue_whenPasswordIsVeryLongAndValid() {
        assertTrue(PasswordValidator.isValid(
                "ThisIsAVeryLongAndSecurePassword123456789!"
        ));
    }

    @Test
    void containsSpecialChar_returnsTrue_whenAllowedSpecialCharacterExists() {
        assertTrue(
                PasswordValidator.containsSpecialChar(
                        "Abcdef1!",
                        "!@#$%&*"
                )
        );
    }

    @Test
    void containsSpecialChar_returnsFalse_whenSpecialCharacterIsMissing() {
        assertFalse(
                PasswordValidator.containsSpecialChar(
                        "Abcdefg1",
                        "!@#$%&*"
                )
        );
    }

    @Test
    void containsSpecialChar_returnsFalse_whenSpecialCharacterIsNotAllowed() {
        assertFalse(
                PasswordValidator.containsSpecialChar(
                        "Abcdef1_",
                        "!@#$%&*"
                )
        );
    }

    @Test
    void containsSpecialChar_returnsFalse_whenPasswordIsNull() {
        assertFalse(
                PasswordValidator.containsSpecialChar(
                        null,
                        "!@#$%&*"
                )
        );
    }

    @Test
    void containsSpecialChar_returnsFalse_whenAllowedCharactersAreNull() {
        assertFalse(
                PasswordValidator.containsSpecialChar(
                        "Abcdef1!",
                        null
                )
        );
    }

    @Test
    void isValid_returnsFalse_whenSpecialCharacterIsMissing() {
        assertFalse(PasswordValidator.isValid("Abcdef1g"));
    }

    @ParameterizedTest(name = "{index}: isValid({0}) returns {1}")
    @CsvSource({
            "'Abcdef1!', true",
            "'StrongPassword9@', true",
            "'Abc1de!', false",
            "'Abcdefg!', false",
            "'abcdef1!', false",
            "'ABCDEFG1!', false",
            "'Abcdef1g', false",
            "'Aa345678', false"
    })
    void isValid_returnsExpectedResult_forDifferentPasswords(
            String password,
            boolean expected
    ) {
        assertEquals(expected, PasswordValidator.isValid(password));
    }
}