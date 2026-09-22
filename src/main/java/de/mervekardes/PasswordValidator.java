package de.mervekardes;

public class PasswordValidator {
    private PasswordValidator() {
    }

    public static boolean hasMinLength(String password, int min) {
        return password != null && password.length() >= min;
    }

}
