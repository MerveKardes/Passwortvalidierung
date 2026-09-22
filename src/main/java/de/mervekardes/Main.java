package de.mervekardes;

import java.util.Scanner;

public final class Main {

    private Main() {
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Bitte geben Sie ein Passwort ein: ");
        String password = scanner.nextLine();

        boolean valid = PasswordValidator.isValid(password);

        if (valid) {
            System.out.println("Das Passwort ist gültig.");
        } else {
            System.out.println("Das Passwort ist ungültig.");
        }
    }
}