package de.mervekardes;

import java.util.Locale;
import java.util.Set;

public class PasswordValidator {
    private static final int MIN_PASSWORD_LENGTH = 8;

    private static final Set<String> COMMON_PASSWORDS = Set.of(
            "password",
            "passwort1",
            "12345678",
            "aa345678"
    );

    private PasswordValidator() {
    }

    public static boolean isValid(String password) {
        if (!hasMinLength(password, MIN_PASSWORD_LENGTH)) {
            return false;
        }

        if (!containsDigit(password)) {
            return false;
        }

        if (!containsUppercase(password)) {
            return false;
        }

        if (!containsLowercase(password)) {
            return false;
        }

        if (isCommonPassword(password)) {
            return false;
        }

        return true;
    }

    public static boolean hasMinLength(String password, int min) {
        return password != null && password.length() >= min;
    }

    public static boolean containsDigit(String password) {
        if (password == null) {
            return false;
        }

        char[] characters = password.toCharArray();

        for (char character : characters) {
            if (character >= '0' && character <= '9') {
                return true;
            }
        }

        return false;
    }

    public static boolean containsUppercase(String password) {
        if (password == null) {
            return false;
        }

        char[] characters = password.toCharArray();

        for (char character : characters) {
            if (Character.isUpperCase(character)) {
                return true;
            }
        }

        return false;
    }

    public static boolean containsLowercase(String password) {
        if (password == null) {
            return false;
        }

        char[] characters = password.toCharArray();

        for (char character : characters) {
            if (Character.isLowerCase(character)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isCommonPassword(String password) {
        if (password == null) {
            return false;
        }

        String normalizedPassword = password
                .trim()
                .toLowerCase(Locale.ROOT);

        return COMMON_PASSWORDS.contains(normalizedPassword);
    }

}
