package edu.hw7.task2;

import java.util.stream.LongStream;

public class ParallelFactorial {
    public static long calculateFactorial(long n) {
        return LongStream.range(1, n + 1).parallel().reduce((a, b) -> a * b).getAsLong();
    }
}
