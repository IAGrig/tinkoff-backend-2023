package edu.hw5;

import java.time.LocalDate;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class Task2Test {
    private static Stream<Arguments> fridays13OnYearTestsProvider() {
        return Stream.of(
            arguments(1925, new String[] {"1925-02-13", "1925-03-13", "1925-11-13"},
                2024, new String[] {"2024-09-13", "2024-12-13"},
                2020, new String[] {"2020-03-13", "2020-11-13"},
                2025, new String[] {"2025-06-13"}
            )
        );
    }

    private static Stream<Arguments> nextFriday13TestsProvider() {
        return Stream.of(
            arguments(LocalDate.of(2023, 11, 13), "2024-09-13",
                LocalDate.of(2024, 10, 10), "2024-12-13",
                LocalDate.of(1925, 1, 1), "1925-02-13",
                LocalDate.of(1925, 11, 1), "1925-11-13"
            )
        );
    }

    @DisplayName("Задание 2.1")
    @ParameterizedTest
    @MethodSource("fridays13OnYearTestsProvider")
    public void fridays13OnYearTest(int argument, String[] expected) {
        Task2 task2 = new Task2();
        assertThat(task2.fridays13OnYear(argument)).isEqualTo(expected);
    }

    @DisplayName("Задание 2.2")
    @ParameterizedTest
    @MethodSource("nextFriday13TestsProvider")
    public void nextFriday13Test(LocalDate argument, String expected) {
        Task2 task2 = new Task2();
        assertThat(task2.getNextFriday13(argument)).isEqualTo(expected);
    }
}
