package edu.project3;

import edu.project3.renderers.AdocRenderer;
import edu.project3.renderers.MarkdownRenderer;
import edu.project3.renderers.RenderersHelper;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class RendererHelperTest {
    private static Stream<Arguments> LogReportProvider() {
        OffsetDateTime fromDate = OffsetDateTime.MIN;
        OffsetDateTime toDate = OffsetDateTime.now();
        FilesReader filesReader = new FilesReader("src/main/resources/project3/logs.txt");
        Stream<String> fileLinesStream = filesReader.lines();
        List<String> files = filesReader.getPaths();
        StatCounter statCounter = new StatCounter(files, fromDate, toDate);
        LogReport logReport = statCounter.getReport(fileLinesStream);
        return Stream.of(
            Arguments.of(logReport)
        );
    }

    @Test
    public void getRendererWithFormatTest() {
        assertThat(RenderersHelper.getRendererWithFormat(OutputFormat.ADOC)).isInstanceOf(AdocRenderer.class);
        assertThat(RenderersHelper.getRendererWithFormat(OutputFormat.MARKDOWN)).isInstanceOf(MarkdownRenderer.class);
        assertThat(RenderersHelper.getRendererWithFormat(null)).isInstanceOf(MarkdownRenderer.class);
    }

    @Test
    public void getFromDateNullTest() {
        assertThatThrownBy(() -> {
            RenderersHelper.getFromDate(null, DateTimeFormatter.ISO_DATE_TIME);
        }).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("LogReportProvider")
    public void getFromDateTest(LogReport logReport) {
        assertThat(RenderersHelper.getFromDate(logReport, DateTimeFormatter.ISO_DATE_TIME)).isEqualTo("-");
    }

    @Test
    public void getToDateNullTest() {
        assertThatThrownBy(() -> {
            RenderersHelper.getToDate(null, DateTimeFormatter.ISO_DATE_TIME);
        }).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("LogReportProvider")
    public void getToDateTest(LogReport logReport) {
        assertThat(RenderersHelper.getToDate(logReport, DateTimeFormatter.ISO_DATE_TIME)).isNotBlank();
    }
}
