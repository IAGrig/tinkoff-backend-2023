package edu.project1;

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
        while (session.getCurrentState() == Session.state.ACTIVE) {
            char userLetter = consoleHandler.getUserLetter();
            if (userLetter == consoleHandler.EXIT_CHAR) {
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
