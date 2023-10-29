package edu.hw3;

import java.util.List;
import java.util.PriorityQueue;

public class Contact {
    String firstName;
    String lastName;

    public Contact(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Contact(String firstName) {
        this.firstName = firstName;
        this.lastName = "";
    }

    public static List<Contact> parseContacts(String[] names, String sortingMode) {
        PriorityQueue<Contact> contacts;
        if (sortingMode.equals("ASC")) {
            ContactsComparator comparator = new ContactsComparator();
            contacts = new PriorityQueue<>(comparator);
        } else if (sortingMode.equals("DESC")) {
            ContactsComparatorReversed comparator = new ContactsComparatorReversed();
            contacts = new PriorityQueue<>(comparator);
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

    @Override
    public String toString() {
        return (this.firstName + " " + this.lastName).trim();
    }

    public boolean equals(Contact obj) {
        return (this.firstName.equals(obj.firstName) && this.lastName.equals(obj.lastName));
    }
}
