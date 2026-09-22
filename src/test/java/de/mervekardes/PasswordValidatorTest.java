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
}