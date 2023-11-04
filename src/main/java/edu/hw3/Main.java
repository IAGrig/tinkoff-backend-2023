package edu.hw3;

import edu.hw3.contacts.Contact;
import edu.hw3.contacts.ContactsComparator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Main {
    public static List<Contact> parseContacts(String[] names, String sortingMode) {
        PriorityQueue<Contact> contacts;
        if (sortingMode.equals("ASC")) {
            ContactsComparator comparator = new ContactsComparator();
            contacts = new PriorityQueue<>(comparator);
        } else if (sortingMode.equals("DESC")) {
            ContactsComparator comparator = new ContactsComparator();
            contacts = new PriorityQueue<>(comparator.reversed());
        } else {
            contacts = new PriorityQueue<>();
        }

        for (String s : names) {
            if (s == null || s.isBlank()) {
                continue;
            }
            String[] splittedName = s.split(" ");
            if (splittedName.length == 1) {
                contacts.add(new Contact(splittedName[0]));
            } else {
                contacts.add(new Contact(splittedName[0], splittedName[1]));
            }
        }
        return contacts.stream().toList();
    }

    public String atbash(String s) {
        char[] letters = s.toCharArray();
        for (int i = 0; i < letters.length; i++) {
            letters[i] = getEncryptedChar(letters[i]);
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

    private char getEncryptedChar(char character) {
        Map<Character, Character> map = Map.ofEntries(
            Map.entry('a', 'z'),
            Map.entry('b', 'y'),
            Map.entry('c', 'x'),
            Map.entry('d', 'w'),
            Map.entry('e', 'v'),
            Map.entry('f', 'u'),
            Map.entry('g', 't'),
            Map.entry('h', 's'),
            Map.entry('i', 'r'),
            Map.entry('j', 'q'),
            Map.entry('k', 'p'),
            Map.entry('l', 'o'),
            Map.entry('m', 'n'),
            Map.entry('n', 'm'),
            Map.entry('o', 'l'),
            Map.entry('p', 'k'),
            Map.entry('q', 'j'),
            Map.entry('r', 'i'),
            Map.entry('s', 'h'),
            Map.entry('t', 'g'),
            Map.entry('u', 'f'),
            Map.entry('v', 'e'),
            Map.entry('w', 'd'),
            Map.entry('x', 'c'),
            Map.entry('y', 'b'),
            Map.entry('z', 'a')
        );
        if ('a' <= character && character <= 'z') {
            return map.get(character);
        } else if ('A' <= character && character <= 'Z') {
            int capitalizingDiff = 'a' - 'A'; // 97 - 65
            return (map.get(character - capitalizingDiff));
        } else {
            return character;
        }
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
