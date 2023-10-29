package edu.hw3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public String atbash(String s) {
        char[] letters = s.toCharArray();
        for (int i = 0; i < letters.length; i++) {
            if ('A' <= letters[i] && letters[i] <= 'Z') {
                // positions 0,1,2...23,24,25 (char codes)
                int fromAPosition = letters[i] - 'A';
                int fromZPosition = 25 - fromAPosition;
                if (fromAPosition <= fromZPosition) {
                    letters[i] = (char) ('Z' - fromAPosition);
                } else {
                    letters[i] = (char) ('A' + fromZPosition);
                }
            }
            if ('a' <= letters[i] && letters[i] <= 'z') {
                // positions 0,1,2...23,24,25 (char codes)
                int fromAPosition = letters[i] - 'a';
                int fromZPosition = 25 - fromAPosition;
                if (fromAPosition <= fromZPosition) {
                    letters[i] = (char) ('z' - fromAPosition);
                } else {
                    letters[i] = (char) ('a' + fromZPosition);
                }
            }
        }
        return new String(letters);
    }

    public ArrayList<String> clusterize(String s) {
        int openBracketsCount = 0;
        StringBuilder currentCluster = new StringBuilder();
        ArrayList<String> result = new ArrayList<>();
        for (char c : s.toCharArray()) {
            switch (c) {
                case '(':
                    currentCluster.append('(');
                    openBracketsCount++;
                    break;
                case ')':
                    currentCluster.append(')');
                    openBracketsCount--;
                    if (openBracketsCount == 0) {
                        result.add(currentCluster.toString());
                        currentCluster = new StringBuilder();
                    }
            }
        }
        return result;
    }

    public <E> HashMap<E, Integer> freqDict(E[] array) {
        HashMap<E, Integer> result = new HashMap<E, Integer>();
        for (E item : array) {
            result.put(item, result.getOrDefault(item, 0) + 1);
        }
        return result;
    }

    public String convertToRoman(int number) {
        if (number < 1 || number > 3999) {
            return "Unable to convert.";
        }

        int digitsCount = (int) Math.ceil(Math.log10(number + 1));
        // 1234 -> [1000, 200, 30, 4]
        int[] primitives = new int[digitsCount];
        for (int i = 0; i < digitsCount; i++) {
            primitives[i] = (int) (number % Math.pow(10, digitsCount - i) - number % Math.pow(10, digitsCount - i - 1));
        }
        StringBuilder result = new StringBuilder();
        for (int primitive : primitives) {
            result.append(primitiveToRoman(primitive));
        }
        return result.toString();
    }

    public List<Contact> parseContacts(String[] namesArray, String sortingMode) {
        return Contact.parseContacts(namesArray, sortingMode);
    }

    private String primitiveToRoman(int number) {
        String lowerBase = "I";
        String middleBase = "V";
        String upperBase = "X";

        if (10 <= number && number <= 100) {
            lowerBase = "X";
            middleBase = "L";
            upperBase = "C";
            number /= 10;
        }
        if (100 <= number && number <= 1000) {
            lowerBase = "C";
            middleBase = "D";
            upperBase = "M";
            number /= 100;
        }
        String result = switch (number) {
            case 0:
                yield "";
            case 1:
                yield lowerBase;
            case 2:
                yield lowerBase + lowerBase;
            case 3:
                yield lowerBase + lowerBase + lowerBase;
            case 4:
                yield lowerBase + middleBase;
            case 5:
                yield middleBase;
            case 6:
                yield middleBase + lowerBase;
            case 7:
                yield middleBase + lowerBase + lowerBase;
            case 8:
                yield middleBase + lowerBase + lowerBase + lowerBase;
            case 9:
                yield lowerBase + upperBase;
            case 10:
                yield upperBase;
            default:
                yield "Something went wrong";
        };
        return result;
    }

}
