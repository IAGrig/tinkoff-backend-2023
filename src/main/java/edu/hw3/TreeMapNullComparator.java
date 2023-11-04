package edu.hw3;

import java.util.Comparator;

public class TreeMapNullComparator implements Comparator<String> {
    public int compare(String o1, String o2) {
        if (o1 != null && o2 != null) {
            return o1.compareTo(o2);
        }
        return 0;
    }
}
