package edu.hw3;

import java.util.PriorityQueue;

public class StockMarket {
    PriorityQueue<Stock> stocks;

    StockMarket() {
        StocksComparator comparator = new StocksComparator();
        this.stocks = new PriorityQueue<>(comparator);
    }

    public void add(Stock stock) {
        stocks.add(stock);
    }

    public void remove(Stock stock) {
        stocks.remove(stock);
    }

    public Stock mostValuableStock() {
        return stocks.peek();
    }
}
