package de.mervekardes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


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
    void shouldRejectNullPasswordForMinimumLength() {
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
        assertTrue(PasswordValidator.isValid("Abcdef1!"));
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
    void shouldRejectNullPasswordForCompleteValidation() {
        assertFalse(PasswordValidator.isValid(null));
    }

    @Test
    void shouldAcceptVeryLongValidPassword() {
        assertTrue(PasswordValidator.isValid(
                "ThisIsAVeryLongAndSecurePassword123456789!"
        ));
    }
    @Test
    void shouldDetectUppercaseAsLetter() {
        assertTrue(PasswordValidator.containsLetter("123A456"));
    }

    @Test
    void shouldDetectLowercaseAsLetter() {
        assertTrue(PasswordValidator.containsLetter("123a456"));
    }

    @Test
    void shouldReturnFalseWhenPasswordContainsNoLetter() {
        assertFalse(PasswordValidator.containsLetter("12345678"));
    }

    @Test
    void shouldReturnFalseForNullWhenCheckingLetter() {
        assertFalse(PasswordValidator.containsLetter(null));
    }
    @Test
    void shouldDetectAllowedSpecialCharacter() {
        assertTrue(
                PasswordValidator.containsSpecialChar(
                        "Abcdef1!",
                        "!@#$%&*"
                )
        );
    }

    @Test
    void shouldReturnFalseWhenSpecialCharacterIsMissing() {
        assertFalse(
                PasswordValidator.containsSpecialChar(
                        "Abcdefg1",
                        "!@#$%&*"
                )
        );
    }

    @Test
    void shouldRejectSpecialCharacterOutsideAllowedSet() {
        assertFalse(
                PasswordValidator.containsSpecialChar(
                        "Abcdef1_",
                        "!@#$%&*"
                )
        );
    }

    @Test
    void shouldReturnFalseForNullWhenCheckingSpecialCharacter() {
        assertFalse(
                PasswordValidator.containsSpecialChar(
                        null,
                        "!@#$%&*"
                )
        );
    }

    @Test
    void shouldReturnFalseWhenAllowedSpecialCharactersAreNull() {
        assertFalse(
                PasswordValidator.containsSpecialChar(
                        "Abcdef1!",
                        null
                )
        );
    }
    @Test
    void shouldRejectPasswordWithoutSpecialCharacter() {
        assertFalse(PasswordValidator.isValid("Abcdef1g"));
    }

    @ParameterizedTest(name = "{index}: isValid({0}) should return {1}")
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
    void shouldValidatePasswordsUsingMultipleTestCases(
            String password,
            boolean expected
    ) {
        assertEquals(expected, PasswordValidator.isValid(password));
    }
}