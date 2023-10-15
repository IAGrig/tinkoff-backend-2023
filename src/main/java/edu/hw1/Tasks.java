package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Arrays;

public final class Tasks {
    private final static Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {

    }
    public void logger_hello_world(){
        LOGGER.info("Привет, мир!");
    }

    public int minutesToSeconds(String str){
        final int SECONDS_PER_MINUTE = 60;
        String[] splitString = str.split(":");
        int minutes = Integer.parseInt(splitString[0]);
        int seconds = Integer.parseInt(splitString[1]);
        if(seconds >= SECONDS_PER_MINUTE) return -1;
        if(minutes < 0 || seconds < 0) return -1;

        return (SECONDS_PER_MINUTE * minutes) + seconds;
    }

    public int countDigits(int number){
        number = Math.abs(number);
        if(number == 0) return 1;
        return (int) Math.ceil(Math.log10(number+1));
    }

    public boolean isNestable(int[] a1, int[] a2){
        if(a1.length == 0) return true;
        if(a2.length == 0 && a1.length > 0) return false;
        int min1 = a1[0], min2 = a2[0], max1 = a1[0], max2 = a2[0];
        for(int x:a1){
            min1 = Math.min(min1, x);
            max1 = Math.max(max1, x);
        }
        for(int x:a2){
            min2 = Math.min(min2, x);
            max2 = Math.max(max2, x);
        }
        return (min1 > min2 && max1 < max2);
    }

    public int[] intToArray(int number){
        int countOfDigits = countDigits(number);
        int[] digits = new int[countOfDigits];
        for(int i = 0; i < countOfDigits; i++){
            digits[i] = (int)(number % Math.pow(10, countOfDigits-i) / Math.pow(10, countOfDigits-1-i));
        }
        return digits;
    }

    public String fixString(String str){
        char[] array = str.toCharArray();
        for(int i = 1; i < str.length(); i+=2){
            char tmp = array[i-1];
            array[i-1] = array[i];
            array[i] = tmp;
        }
        return new String(array);
    }

    public boolean isPalindromeDescendant(int number){
        int[] digits = intToArray(number);
        int children = 0;
        for(int i = digits.length-1; i > 0; i-=2){
            // what if sum is > 9???
            children += (int) ((digits[i-1] + digits[i])*Math.pow(10, (digits.length - i - 1) /2 ));
        }
        if(countDigits(children) > 1)  return isPalindrome(number) || isPalindromeDescendant(children);
        return isPalindrome(number);
    }

    public boolean isPalindrome(int number){
        int[] digits = intToArray(number);
        for(int i = 0; i < digits.length/2; i++){
            if(digits[i] != digits[digits.length-1-i]) return false;
        }
        return true;
    }

    public int countK(int number){
        // if not 4-digit number
        if(number < 1001 || number >= 9999) return -1;
        if(number == 6174) return 0;

        int[] digits = new int[4];
        for(int i = 0; i < 4; i++){
            digits[i] = (int)(number % Math.pow(10, 4-i) / Math.pow(10, 3-i));
        }
        // if digits are the same
        if(digits[0]==digits[1] && digits[1] == digits[2] && digits[2] == digits[3]) return -1;

        Arrays.sort(digits);
        int maxNumber = digits[3]*1000 + digits[2]*100 + digits[1]*10 + digits[0];
        int minNumber = digits[0]*1000 + digits[1]*100 + digits[2]*10 + digits[3];
        int step = 0;

        return countK(maxNumber-minNumber, step+1);
    }

    public int countK(int number, int step){
        if(number == 6174) return step;
        int[] digits = new int[4];
        for(int i = 0; i < 4; i++){
            digits[i] = (int)(number % Math.pow(10, 4-i) / Math.pow(10, 3-i));
        }

        Arrays.sort(digits);
        int maxNumber = digits[3]*1000 + digits[2]*100 + digits[1]*10 + digits[0];
        int minNumber = digits[0]*1000 + digits[1]*100 + digits[2]*10 + digits[3];

        return countK(maxNumber-minNumber, step+1);
    }

    public int rotateLeft1Position(int n, int countOfBinaryDigits){
        n = n << 1;
        if (n >= Math.pow(2, countOfBinaryDigits)) {
            n -= Math.pow(2, countOfBinaryDigits);
            n += 1;
        }
        return n;
    }

    public int rotateLeft(int n, int shift){
        int countOfBinaryDigits = (int) Math.ceil(Math.log(n+1)/Math.log(2));
        shift %= countOfBinaryDigits;
        for(int i = 0; i < shift; i++) {
            n = rotateLeft1Position(n, countOfBinaryDigits);
        }
        return n;
    }

    public int rotateRight(int n, int shift){
        int countOfBinaryDigits = (int) Math.ceil(Math.log(n+1)/Math.log(2));
        return rotateLeft(n, countOfBinaryDigits-shift);
    }

    public boolean knightBoardCapture(int[][] desk){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(desk[i][j] == 1){
                    if(i-2 >= 0){
                        if(j-1 >= 0 && desk[i-2][j-1] == 1) return false;
                        if(j+1 <= 7 && desk[i-2][j+1] == 1) return false;
                    }
                    if(i+2 <= 7){
                        if(j-1 >= 0 && desk[i+2][j-1] == 1) return false;
                        if(j+1 <= 7 && desk[i+2][j+1] == 1) return false;
                    }
                    if(j-2 >= 0){
                        if(i-1 >= 0 && desk[i-1][j-2] == 1) return false;
                        if(i+1 <= 7 && desk[i+1][j-2] == 1) return false;
                    }
                    if(j+2 <= 7){
                        if(i-1 >= 0 && desk[i-1][j+2] == 1) return false;
                        if(i+1 <= 7 && desk[i+1][j+2] == 1) return false;
                    }
                }
            }
        }
        return true;
    }
}
