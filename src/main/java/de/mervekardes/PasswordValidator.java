package de.mervekardes;

public class PasswordValidator {
    private PasswordValidator() {
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

}
