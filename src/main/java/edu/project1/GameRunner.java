package edu.project1;

import edu.project1.enums.State;

public class GameRunner {
    Dictionary dictionary;
    ConsoleHandler consoleHandler;

    public GameRunner(Dictionary dictionary) {
        this.dictionary = dictionary;
        this.consoleHandler = new ConsoleHandler();
    }

    public void runGame() {
        Session session = new Session(dictionary.randomWord());
        consoleHandler.printGreeting();
        consoleHandler.printCurrentWord(session.getCurrentWord());
        while (session.getCurrentState() == State.ACTIVE) {
            char userLetter = consoleHandler.getUserLetter();
            if (userLetter == ConsoleHandler.EXIT_CHAR) {
                session.giveUp();
            }
            consoleHandler.printTryResult(
                session.tryGuess(userLetter),
                session.getAttempts(),
                session.getMaxAttempts()
            );
            consoleHandler.printCurrentWord(session.getCurrentWord());
        }
    }
}
