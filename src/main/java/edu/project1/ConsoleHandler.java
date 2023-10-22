package edu.project1;

import java.util.Scanner;

public class ConsoleHandler {

    public final char EXIT_CHAR = '!';
    private final Scanner scanner = new Scanner(System.in);
    private final String EXIT_COMMAND = "!EXIT";

    public void printGreeting() {
        System.out.println("Welcome to HangmanConsoleGame!");
        System.out.printf("If you want to give up, type '%s'.%n", EXIT_COMMAND);
    }

    public void printTryResult(Session.STATUSES status, int attempt, int maxAttempts) {
        switch (status) {
            case HIT -> System.out.println("Hit!");
            case MISTAKE -> System.out.printf("Missed, mistake %d of %d%n", attempt, maxAttempts);
            case VICTORY -> System.out.println("You won!");
            case DEFEAT -> System.out.println("You lost!");
            default ->
        }
    }

    public void printCurrentWord(String word) {
        System.out.printf("> %s %n", word);
    }

    public char getUserLetter() {
        System.out.println("Guess a letter:");
        System.out.print("> ");
        String input = scanner.nextLine();
        if (input.equals(EXIT_COMMAND)) {
            return EXIT_CHAR;
        }
        if (input.length() == 1) {
            char letter = input.toCharArray()[0];
            if ('a' <= letter && letter <= 'z') {
                return letter;
            }
        }
        System.out.println("Sorry, enter letter or exit command");
        return getUserLetter();
    }
}
