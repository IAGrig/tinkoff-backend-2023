package edu.hw3;

import java.util.Comparator;

public class ContactsComparatorReversed implements Comparator<Contact> {
    public int compare(Contact o1, Contact o2) {
        String comparationField1 = o1.lastName;
        String comparationField2 = o2.lastName;
        if (comparationField1.isBlank()) {
            comparationField1 = o1.firstName;
        }
        if (comparationField2.isBlank()) {
            comparationField2 = o2.firstName;
        }
        return -comparationField1.compareTo(comparationField2);
    }
}
