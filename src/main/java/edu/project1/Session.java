package edu.project1;

import java.util.Arrays;

class Session {
    public final int maxAttempts;
    private final char[] answer;
    private final char[] userAnswer;
    public STATES state;
    public int attempts;

    Session(String answer) {
        this.answer = answer.toCharArray();
        this.userAnswer = answer.toCharArray();
        Arrays.fill(this.userAnswer, '*');
        this.maxAttempts = answer.length();
        this.attempts = 0;
        if (2 < answer.length() && answer.length() < 40) {
            state = STATES.ACTIVE;
        } else {
            state = STATES.FINISHED;
        }
    }

    public String getCurrentWord() {
        return new String(userAnswer);
    }

    public STATUSES tryGuess(char letter) {
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
            state = STATES.FINISHED;
            if (Arrays.equals(userAnswer, answer)) {
                return STATUSES.VICTORY;
            } else {
                return STATUSES.DEFEAT;
            }
        }

        if (Arrays.equals(userAnswer, answer)) {
            state = STATES.FINISHED;
            return STATUSES.VICTORY;
        }

        return succeedTry ? STATUSES.HIT : STATUSES.MISTAKE;
    }

    public void giveUp() {
        state = STATES.FINISHED;
    }

    public enum STATUSES {HIT, MISTAKE, VICTORY, DEFEAT}

    public enum STATES {ACTIVE, FINISHED}
}
