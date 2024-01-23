package edu.project3;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class StatCounterTest {
    private static final List<String> files = List.of("test1.log", "test2.log", "test3.log");
    private static final StatCounter statCounter = new StatCounter(files, OffsetDateTime.MIN, OffsetDateTime.MAX);

    private static Stream<Arguments> getReportTestProvider() {
        return Stream.of(
            Arguments.of(Stream.empty()),
            Arguments.of(Stream.of("")),
            Arguments.of(Stream.of("test", "one more test 123.102"))
        );
    }

    @ParameterizedTest
    @MethodSource("getReportTestProvider")
    public void getReportTest(Stream<String> fileLines) {
        assertThat(statCounter.getReport(fileLines)).isNotNull();
    }

    @Test
    public void getReportNullTest() {
        assertThatThrownBy(() -> statCounter.getReport(null)).isExactlyInstanceOf(IllegalArgumentException.class);
    }
}
