package edu.hw7.task2;

import java.util.stream.LongStream;

public class ParallelFactorial {
    private ParallelFactorial() {
    }

    public static long calculateFactorial(long n) {
        // (1, 2) range = (1) special for case with n = 0
        return LongStream.concat(LongStream.range(1, 2), LongStream.range(1, n + 1))
            .parallel()
            .reduce((a, b) -> a * b)
            .getAsLong();
    }
}
