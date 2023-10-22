package edu.project1;

class Main {
    public static void main(String[] args) {
        ConsoleHandler consoleHandler = new ConsoleHandler();
        Dictionary dictionary = new Dictionary();
        Session session = new Session(dictionary.randomWord());

        consoleHandler.printGreeting();
        consoleHandler.printCurrentWord(session.getCurrentWord());
        while (session.state == Session.STATES.ACTIVE) {
            char userLetter = consoleHandler.getUserLetter();
            if (userLetter == consoleHandler.EXIT_CHAR) {
                session.giveUp();
            }
            consoleHandler.printTryResult(session.tryGuess(userLetter), session.attempts, session.maxAttempts);
            consoleHandler.printCurrentWord(session.getCurrentWord());
        }
    }
}
