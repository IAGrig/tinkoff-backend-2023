package edu.hw3.contacts;

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

    @Override
    public String toString() {
        return (this.firstName + " " + this.lastName).trim();
    }

}
