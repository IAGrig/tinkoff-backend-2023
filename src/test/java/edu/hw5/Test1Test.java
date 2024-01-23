package edu.hw5;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class Test1Test {
    private static Stream<Arguments> calculateAverageTimeTestsProvider() {
        return Stream.of(
            arguments(new String[] {"2022-03-12, 20:20 - 2022-03-12, 23:50",
                    "2022-04-01, 21:30 - 2022-04-02, 01:20"}, "3ч 40м",
                new String[] {"2023-11-13, 12:00 - 2023-11-13, 16:00",
                    "2018-01-01, 12:00 - 2018-01-01, 13:00"}, "2ч 30м",
                new String[] {"2020-10-10, 18:30 - 2020-10-11, 18:30"}, "1д"
            )
        );
    }

    @DisplayName("Задание 1")
    @ParameterizedTest
    @MethodSource("calculateAverageTimeTestsProvider")
    public void calculateAverageTimeTest(String[] arguments, String expected) {
        Task1 task1 = new Task1();
        String result = task1.calculateAverageTime(arguments);
        assertThat(result).isEqualTo(expected);
    }
}
