package de.mervekardes;

import java.util.Scanner;

public final class Main {

    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final String ALLOWED_SPECIAL_CHARACTERS = "!@#$%&*";

    private Main() {
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Bitte geben Sie ein Passwort ein: ");
        String password = scanner.nextLine();

        if (PasswordValidator.isValid(password)) {
            System.out.println("Das Passwort ist gültig.");
        } else {
            System.out.println("Das Passwort ist ungültig:");
            printValidationErrors(password);
        }
    }

    private static void printValidationErrors(String password) {
        if (!PasswordValidator.hasMinLength(
                password,
                MIN_PASSWORD_LENGTH
        )) {
            System.out.println(
                    "- Das Passwort muss mindestens 8 Zeichen lang sein."
            );
        }

        if (!PasswordValidator.containsLetter(password)) {
            System.out.println(
                    "- Das Passwort muss mindestens einen Buchstaben enthalten."
            );
        }

        if (!PasswordValidator.containsUppercase(password)) {
            System.out.println(
                    "- Das Passwort muss mindestens einen Großbuchstaben enthalten."
            );
        }

        if (!PasswordValidator.containsLowercase(password)) {
            System.out.println(
                    "- Das Passwort muss mindestens einen Kleinbuchstaben enthalten."
            );
        }

        if (!PasswordValidator.containsDigit(password)) {
            System.out.println(
                    "- Das Passwort muss mindestens eine Ziffer enthalten."
            );
        }

        if (!PasswordValidator.containsSpecialChar(
                password,
                ALLOWED_SPECIAL_CHARACTERS
        )) {
            System.out.println(
                    "- Das Passwort muss mindestens eines der Sonderzeichen "
                            + ALLOWED_SPECIAL_CHARACTERS
                            + " enthalten."
            );
        }

        if (PasswordValidator.isCommonPassword(password)) {
            System.out.println(
                    "- Das Passwort steht auf der Liste häufiger Passwörter."
            );
        }
    }
}