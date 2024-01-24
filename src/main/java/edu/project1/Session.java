package edu.project1;

import edu.project1.enums.State;
import edu.project1.enums.Status;
import java.util.Arrays;


class Session {
    private final int maxAttempts;
    private final char[] answer;
    private final char[] userAnswer;
    private State currentState;
    private int attempts;

    protected Session(String answer) {
        final int ANSWER_LENGTH_LOWER_BOUND = 2;
        final int ANSWER_LENGTH_UPPER_BOUND = 40;
        this.answer = answer.toCharArray();
        this.userAnswer = answer.toCharArray();
        Arrays.fill(this.userAnswer, '*');
        this.maxAttempts = answer.length();
        this.attempts = 0;
        if (ANSWER_LENGTH_LOWER_BOUND < answer.length() && answer.length() < ANSWER_LENGTH_UPPER_BOUND) {
            currentState = State.ACTIVE;
        } else {
            currentState = State.FINISHED;
        }
    }

    public String getCurrentWord() {
        return new String(userAnswer);
    }

    public Status tryGuess(char letter) {
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
            currentState = State.FINISHED;
            if (Arrays.equals(userAnswer, answer)) {
                return Status.VICTORY;
            } else {
                return Status.DEFEAT;
            }
        }

        if (Arrays.equals(userAnswer, answer)) {
            currentState = State.FINISHED;
            return Status.VICTORY;
        }

        return succeedTry ? Status.HIT : Status.MISTAKE;
    }

    public void giveUp() {
        currentState = State.FINISHED;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public int getAttempts() {
        return attempts;
    }

    public State getCurrentState() {
        return currentState;
    }
}
