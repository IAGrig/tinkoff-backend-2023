package edu.hw8.task3;

import java.util.NoSuchElementException;

public class PasswordsGenerator {
    private final String alphabet;
    private final int passwordsLength;
    private final int startIndex;
    private final int finishIndex;
    private final int[] charsByIndexes; // [0, 0, 0] -> "aaa", [0, 1, 2] -> "abc" with "abc..." alphabet
    private long passwordsCount;

    public PasswordsGenerator(String alphabet, int passwordsLength) {
        this.alphabet = alphabet;
        this.passwordsLength = passwordsLength;
        this.passwordsCount = (long) Math.pow(alphabet.length(), passwordsLength);
        this.charsByIndexes = new int[passwordsLength];
        this.startIndex = 0;
        this.finishIndex = alphabet.length();
    }

    public PasswordsGenerator(String alphabet, int passwordsLength, int startIndex, int finishIndex) {
        this.alphabet = alphabet;
        this.passwordsLength = passwordsLength;
        this.passwordsCount = (long) Math.pow(alphabet.length(), passwordsLength);
        this.charsByIndexes = new int[passwordsLength];
        this.charsByIndexes[0] = startIndex;
        this.startIndex = startIndex;
        this.finishIndex = finishIndex;
    }

    public String nextPassword() {
        if (passwordsCount == 0) {
            throw new NoSuchElementException();
        }
        if (passwordsLength == 0) {
            passwordsCount--;
            return "";
        }

        StringBuilder newPassword = new StringBuilder();
        for (int characterIndex : charsByIndexes) {
            if (characterIndex >= alphabet.length()) {
                throw new NoSuchElementException();
            }
            newPassword.append(alphabet.charAt(characterIndex));
        }
        passwordsCount--;
        updateChars();
        return newPassword.toString();
    }

    private void updateChars() {
        charsByIndexes[0]++;
        for (int i = 0; i < passwordsLength - 1; i++) {
            if (i == 0 && charsByIndexes[i] >= finishIndex) {
                charsByIndexes[i + 1]++;
                charsByIndexes[i] = startIndex;
                continue;
            }
            if (charsByIndexes[i] >= alphabet.length()) {
                charsByIndexes[i + 1]++;
                charsByIndexes[i] = 0;
            }
        }
    }
}
