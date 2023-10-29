package edu.hw3;

import java.util.Comparator;

public class StocksComparator implements Comparator<Stock> {
    public int compare(Stock o1, Stock o2) {
        return Integer.compare(o2.price, o1.price);
    }
}
