package edu.project1;

class Main {
    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary();
        GameRunner gameRunner = new GameRunner(dictionary);
        gameRunner.runGame();
    }
}
