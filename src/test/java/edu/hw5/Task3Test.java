package edu.hw5;

import edu.hw5.task3.DateParser;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class Task3Test {
    private static Stream<Arguments> parseDateTestsProvider() {
        return Stream.of(
            arguments("2020-10-10", Optional.of(LocalDate.of(2020, 10, 10)),
                "2020-12-2", Optional.of(LocalDate.of(2020, 12, 2)),
                "1/3/1976", Optional.of(LocalDate.of(1976, 3, 1)),
                "1/3/20", Optional.of(LocalDate.of(2020, 3, 21)),
                "tomorrow", Optional.of(LocalDate.now().plusDays(1)),
                "today", Optional.of(LocalDate.now()),
                "yesterday", Optional.of(LocalDate.now().minusDays(1)),
                "1 day ago", Optional.of(LocalDate.now().minusDays(1)),
                "2234 days ago", Optional.of(LocalDate.now().minusDays(2234)),
                "test", Optional.empty()
            )
        );
    }

    @DisplayName("Задание 3")
    @ParameterizedTest
    @MethodSource("parseDateTestsProvider")
    public void parseDateTest(String argument, Optional<LocalDate> expected) {
        DateParser dateParser = new DateParser();
        assertThat(dateParser.parseDate(argument)).isEqualTo(expected);
    }
}
