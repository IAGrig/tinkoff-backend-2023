package edu.project1;

import java.util.Arrays;

class Session {
    private final int maxAttempts;
    private final char[] answer;
    private final char[] userAnswer;
    private state currentState;
    private int attempts;

    protected Session(String answer) {
        this.answer = answer.toCharArray();
        this.userAnswer = answer.toCharArray();
        Arrays.fill(this.userAnswer, '*');
        this.maxAttempts = answer.length();
        this.attempts = 0;
        if (2 < answer.length() && answer.length() < 40) {
            currentState = state.ACTIVE;
        } else {
            currentState = state.FINISHED;
        }
    }

    public String getCurrentWord() {
        return new String(userAnswer);
    }

    public status tryGuess(char letter) {
        boolean succeedTry = false;
        for (int i = 0; i < userAnswer.length; i++) {
            if (answer[i] == letter) {
                userAnswer[i] = letter;
                succeedTry = true;
            }
        }
        if (!succeedTry) {
            attempts++;
        }
        if (attempts >= maxAttempts) {
            currentState = state.FINISHED;
            if (Arrays.equals(userAnswer, answer)) {
                return status.VICTORY;
            } else {
                return status.DEFEAT;
            }
        }

        if (Arrays.equals(userAnswer, answer)) {
            currentState = state.FINISHED;
            return status.VICTORY;
        }

        return succeedTry ? status.HIT : status.MISTAKE;
    }

    public void giveUp() {
        currentState = state.FINISHED;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public int getAttempts() {
        return attempts;
    }

    public state getCurrentState() {
        return currentState;
    }

    public enum status {HIT, MISTAKE, VICTORY, DEFEAT}

    public enum state {ACTIVE, FINISHED}
}
