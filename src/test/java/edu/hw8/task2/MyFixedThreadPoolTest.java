package edu.hw8.task2;

import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class MyFixedThreadPoolTest {
    private static final int threadsCount = 7;
    private static Stream<Arguments> fibTestProvider() {
        return Stream.of(
            Arguments.of(
                new int[] {15, 16, 17, 18, 19, 20, 21, 22, 23},
                new long[] {610L, 987L, 1597L, 2584L, 4181L, 6765L, 10946L, 17711L, 28657L}
            )
        );
    }

    @ParameterizedTest
    @MethodSource("fibTestProvider")
    public void fibTest(int[] numbers, long[] expected) throws Exception {
        assert (numbers.length == expected.length);
        MyFixedThreadPool threadPool = MyFixedThreadPool.create(threadsCount);

        long[] results = new long[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            final int index = i;
            threadPool.execute(() -> results[index] = calculateFib(numbers[index]));
        }
        threadPool.start();

        threadPool.join(1000);
        assertThat(Arrays.equals(results, expected)).isTrue();
        threadPool.close();
    }

    private long calculateFib(int number) {
        if (number <= 2) {
            return 1;
        }
        return calculateFib(number - 1) + calculateFib(number - 2);
    }
}
