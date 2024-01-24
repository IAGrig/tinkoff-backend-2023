package edu.hw1;

import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Tasks {
    private final static Logger LOGGER = LogManager.getLogger();

    public void loggerHelloWorld() {
        LOGGER.info("Привет, мир!");
    }

    public int minutesToSeconds(String str) {
        final int SECONDS_PER_MINUTE = 60;

        String[] splitString = str.split(":");
        if (splitString.length != 2) {
            return -1;
        }

        int minutes;
        int seconds;
        try {
            minutes = Integer.parseInt(splitString[0]);
            seconds = Integer.parseInt(splitString[1]);
        } catch (NumberFormatException e) {
            return -1;
        }

        if (seconds >= SECONDS_PER_MINUTE || minutes < 0 || seconds < 0) {
            return -1;
        }

        return (SECONDS_PER_MINUTE * minutes) + seconds;
    }

    public int countDigits(int number) {
        int absNumber = Math.abs(number);
        if (absNumber == 0) {
            return 1;
        }
        return (int) Math.ceil(Math.log10(absNumber + 1));
    }

    public boolean isNestable(int[] a1, int[] a2) {
        if (a1.length == 0) {
            return true;
        }
        if (a2.length == 0 && a1.length > 0) {
            return false;
        }

        int min1 = Arrays.stream(a1).min().getAsInt();
        int min2 = Arrays.stream(a2).min().getAsInt();
        int max1 = Arrays.stream(a1).max().getAsInt();
        int max2 = Arrays.stream(a2).max().getAsInt();

        return (min1 > min2 && max1 < max2);
    }

    public String fixString(String str) {
        char[] array = str.toCharArray();
        for (int i = 1; i < str.length(); i += 2) {
            char tmp = array[i - 1];
            array[i - 1] = array[i];
            array[i] = tmp;
        }
        return new String(array);
    }

    public boolean isPalindromeDescendant(int number) {
        int[] digits = intToArray(number);

        StringBuilder children = new StringBuilder();
        if (digits.length % 2 == 0) {
            for (int i = 0; i < digits.length; i += 2) {
                children.append(digits[i] + digits[i + 1]);
            }
        }

        if (children.length() > 1) {
            return isPalindrome(number) || isPalindromeDescendant(Integer.parseInt(children.toString()));
        }
        return isPalindrome(number);
    }

    public boolean isPalindrome(int number) {
        int[] digits = intToArray(number);
        for (int i = 0; i < digits.length / 2; i++) {
            if (digits[i] != digits[digits.length - 1 - i]) {
                return false;
            }
        }
        return true;
    }

    @SuppressWarnings("MagicNumber")
    public int countK(int number) {
        // if not 4-digit number
        if (number < 1001 || number >= 9999) {
            return -1;
        }
        if (number == 6174) {
            return 0;
        }

        int[] digits = new int[4];
        for (int i = 0; i < 4; i++) {
            digits[i] = (int) (number % Math.pow(10, 4 - i) / Math.pow(10, 3 - i));
        }
        // if digits are the same
        if (digits[0] == digits[1] && digits[1] == digits[2] && digits[2] == digits[3]) {
            return -1;
        }

        Arrays.sort(digits);
        int maxNumber = digits[3] * 1000 + digits[2] * 100 + digits[1] * 10 + digits[0];
        int minNumber = digits[0] * 1000 + digits[1] * 100 + digits[2] * 10 + digits[3];
        int step = 0;

        return countK(maxNumber - minNumber, step + 1);
    }

    @SuppressWarnings("MagicNumber")
    public int countK(int number, int step) {
        if (number == 6174) {
            return step;
        }
        int[] digits = new int[4];
        for (int i = 0; i < 4; i++) {
            digits[i] = (int) (number % Math.pow(10, 4 - i) / Math.pow(10, 3 - i));
        }

        Arrays.sort(digits);
        int maxNumber = digits[3] * 1000 + digits[2] * 100 + digits[1] * 10 + digits[0];
        int minNumber = digits[0] * 1000 + digits[1] * 100 + digits[2] * 10 + digits[3];

        return countK(maxNumber - minNumber, step + 1);
    }

    public int rotateLeft1Position(int n, int countOfBinaryDigits) {
        int result = n << 1;
        if (result >= Math.pow(2, countOfBinaryDigits)) {
            result -= Math.pow(2, countOfBinaryDigits);
            result += 1;
        }
        return result;
    }

    public int rotateLeft(int n, int shift) {
        int countOfBinaryDigits = (int) Math.ceil(Math.log(n + 1) / Math.log(2));
        int clearShift = shift % countOfBinaryDigits;
        int result = n;
        for (int i = 0; i < clearShift; i++) {
            result = rotateLeft1Position(result, countOfBinaryDigits);
        }
        return result;
    }

    public int rotateRight(int n, int shift) {
        int countOfBinaryDigits = (int) Math.ceil(Math.log(n + 1) / Math.log(2));
        int clearShift = shift % countOfBinaryDigits;
        return rotateLeft(n, countOfBinaryDigits - clearShift);
    }

    @SuppressWarnings({"CyclomaticComplexity", "ReturnCount"})
    public boolean knightBoardCapture(int[][] desk) {
        final int CHESSBOARD_WIDTH = 8;
        for (int i = 0; i < CHESSBOARD_WIDTH; i++) {
            for (int j = 0; j < CHESSBOARD_WIDTH; j++) {
                if (desk[i][j] == 1) {
                    if (i - 2 >= 0) {
                        if (j - 1 >= 0 && desk[i - 2][j - 1] == 1) {
                            return false;
                        }
                        if (j + 1 < CHESSBOARD_WIDTH && desk[i - 2][j + 1] == 1) {
                            return false;
                        }
                    }
                    if (i + 2 < CHESSBOARD_WIDTH) {
                        if (j - 1 >= 0 && desk[i + 2][j - 1] == 1) {
                            return false;
                        }
                        if (j + 1 < CHESSBOARD_WIDTH && desk[i + 2][j + 1] == 1) {
                            return false;
                        }
                    }
                    if (j - 2 >= 0) {
                        if (i - 1 >= 0 && desk[i - 1][j - 2] == 1) {
                            return false;
                        }
                        if (i + 1 < CHESSBOARD_WIDTH && desk[i + 1][j - 2] == 1) {
                            return false;
                        }
                    }
                    if (j + 2 < CHESSBOARD_WIDTH) {
                        if (i - 1 >= 0 && desk[i - 1][j + 2] == 1) {
                            return false;
                        }
                        if (i + 1 < CHESSBOARD_WIDTH && desk[i + 1][j + 2] == 1) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private int[] intToArray(int number) {
        final int DECIMAL_BASE = 10;
        int countOfDigits = countDigits(number);
        int[] digits = new int[countOfDigits];
        for (int i = 0; i < countOfDigits; i++) {
            digits[i] = (int) (number % Math.pow(DECIMAL_BASE, countOfDigits - i)
                / Math.pow(DECIMAL_BASE, countOfDigits - 1 - i));
        }
        return digits;
    }
}
