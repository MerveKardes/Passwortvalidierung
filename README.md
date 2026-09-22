# Passwortvalidierung

Ein testgetrieben entwickeltes Java-Projekt zur Validierung von Passwörtern.

## Passwort-Policy

Ein gültiges Passwort muss:

- mindestens 8 Zeichen lang sein,
- mindestens einen Großbuchstaben enthalten,
- mindestens einen Kleinbuchstaben enthalten,
- mindestens eine Ziffer von 0 bis 9 enthalten,
- darf nicht in der internen Liste verbotener Passwörter enthalten sein.

`null` ist kein gültiges Passwort.

## Verwendete Technologien

- Java 25
- Maven
- JUnit 5

## Build und Tests

```bash
mvn clean verify
```