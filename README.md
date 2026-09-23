# Passwortvalidierung

[![CI](https://github.com/MerveKardes/Passwortvalidierung/actions/workflows/ci.yml/badge.svg)](https://github.com/MerveKardes/Passwortvalidierung/actions/workflows/ci.yml)

Ein testgetrieben entwickeltes Java-Projekt zur Validierung von Passwörtern.

## Passwort-Policy

Ein gültiges Passwort muss:

- mindestens 8 Zeichen lang sein,
- mindestens einen Großbuchstaben enthalten,
- mindestens einen Kleinbuchstaben enthalten,
- mindestens eine ASCII-Ziffer von `0` bis `9` enthalten,
- mindestens eines der erlaubten Sonderzeichen `!@#$%&*` enthalten,
- darf nicht in der internen Liste häufiger Passwörter enthalten sein.

`null` ist kein gültiges Passwort.

## Validierungsmethoden

Die Klasse `PasswordValidator` stellt folgende Methoden bereit:

- `hasMinLength(String password, int min)`
- `containsDigit(String password)`
- `containsUppercase(String password)`
- `containsLowercase(String password)`
- `containsSpecialChar(String password, String allowed)`
- `isCommonPassword(String password)`
- `isValid(String password)`

Die Methode `isValid()` kombiniert alle einzelnen Prüfungen.

## Verwendete Technologien

- Java 25
- Maven
- JUnit 5
- GitHub Actions

## Voraussetzungen

- Java 25 oder neuer
- Maven 3.9 oder neuer

Die installierten Versionen können so geprüft werden:

```bash
java -version
mvn -v
```

## Build und Tests

Projekt vollständig bauen und alle Tests ausführen:

```bash
mvn clean verify
```

Nur die Tests ausführen:

```bash
mvn test
```

Eine ausführbare JAR-Datei erstellen:

```bash
mvn package
```

Das erzeugte Artefakt befindet sich anschließend unter:

```text
target/passwortvalidierung-1.0-SNAPSHOT.jar
```

## Anwendung starten

Die Kommandozeilenanwendung kann nach dem Build so gestartet werden:

```bash
java -jar target/passwortvalidierung-1.0-SNAPSHOT.jar
```

Danach kann ein Passwort eingegeben werden:

```text
Bitte geben Sie ein Passwort ein: Abcdef1!
Das Passwort ist gültig.
```

## Verwendung im Java-Code

Die Validierung kann direkt über die statische Methode `isValid()` verwendet werden:

```java
boolean valid = PasswordValidator.isValid("Abcdef1!");

System.out.println(valid); // true
```

Weitere Beispiele:

```java
PasswordValidator.isValid("Abcdef1!");  // true
PasswordValidator.isValid("Ab1!");      // false: zu kurz
PasswordValidator.isValid("Abcdefg!");  // false: keine Ziffer
PasswordValidator.isValid("abcdef1!");  // false: kein Großbuchstabe
PasswordValidator.isValid("ABCDEFG1!"); // false: kein Kleinbuchstabe
PasswordValidator.isValid("Abcdef1g");  // false: kein erlaubtes Sonderzeichen

PasswordValidator.isCommonPassword("Passwort1"); // true
```

## Continuous Integration

Bei jedem Push und Pull Request führt GitHub Actions automatisch folgenden Maven-Befehl aus:

```bash
mvn -B clean package
```

Dafür wird eine temporäre Ubuntu-Umgebung mit Java 25 eingerichtet. Das Projekt wird dort kompiliert, getestet und als JAR-Datei gebaut.

## Testgetriebene Entwicklung

Die Funktionen wurden schrittweise nach dem Red-Green-Refactor-Prinzip entwickelt:

1. Red: Zuerst wurde ein fehlschlagender Test geschrieben.
2. Green: Danach wurde die minimale Implementierung ergänzt.
3. Refactor: Abschließend wurde der Code überprüft und verbessert.

## Bonusaufgaben

Folgende Bonusaufgaben wurden umgesetzt:

- Sonderzeichenprüfung mit einer definierten erlaubten Zeichenmenge
- parametrisierte Tests mit JUnit 5 und `@ParameterizedTest`
- Kommandozeilenanwendung mit `Scanner` und konkreten Fehlermeldungen