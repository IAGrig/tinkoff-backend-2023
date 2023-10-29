package edu.hw3;

public class Stock {
    String name;
    int price;

    public Stock(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return name + ": " + price;
    }
}
