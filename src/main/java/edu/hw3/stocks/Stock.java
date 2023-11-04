package edu.hw3.stocks;

public class Stock {
    String name;
    double price;

    public Stock(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return name + ": " + price;
    }
}
