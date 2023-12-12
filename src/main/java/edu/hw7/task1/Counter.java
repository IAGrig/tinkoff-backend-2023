package edu.hw7.task1;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
    private final AtomicInteger integer;

    public Counter(int number) {
        this.integer = new AtomicInteger(number);
    }

    public Counter() {
        this.integer = new AtomicInteger();
    }

    public int increment() {
        return integer.incrementAndGet();
    }

    public int getValue() {
        return integer.intValue();
    }
}
