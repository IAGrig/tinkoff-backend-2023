package edu.project1;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsoleHandler {

    private final static Logger LOGGER = LogManager.getLogger();
    public final char EXIT_CHAR = '!';
    private final Scanner scanner = new Scanner(System.in);
    private final String EXIT_COMMAND = "!EXIT";

    public void printGreeting() {
        LOGGER.info("Welcome to HangmanConsoleGame!");
        LOGGER.info("If you want to give up, type " + EXIT_COMMAND + ".");
    }

    public void printTryResult(Session.status tryStatus, int attempt, int maxAttempts) {
        switch (tryStatus) {
            case HIT -> LOGGER.info("Hit!");
            case MISTAKE -> LOGGER.info("Missed, mistake " + attempt + " of " + maxAttempts);
            case VICTORY -> LOGGER.info("You won!");
            case DEFEAT -> LOGGER.info("You lost!");
            default -> LOGGER.info("Something went wrong...h");
        }
    }

    public void printCurrentWord(String word) {
        LOGGER.info("> " + word);
    }

    public char getUserLetter() {
        LOGGER.info("Guess a letter:");
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
        LOGGER.info("Sorry, enter letter or exit command");
        return getUserLetter();
    }
}
