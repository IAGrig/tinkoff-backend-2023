package edu.project3.renderers;

import edu.project3.LogReport;
import edu.project3.OutputFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class RenderersHelper {
    private RenderersHelper() {
    }

    public static Renderer getRendererWithFormat(OutputFormat outputFormat) {
        return switch (outputFormat) {
            case MARKDOWN -> new MarkdownRenderer();
            case ADOC -> new AdocRenderer();
            case null -> new MarkdownRenderer();
        };
    }

    public static String getFromDate(LogReport logReport, DateTimeFormatter dateTimeFormatter) {
        String fromDate = "-";
        if (logReport == null || dateTimeFormatter == null) {
            throw new IllegalArgumentException();
        }
        if (logReport.getFromDate() == OffsetDateTime.MIN) {
            return fromDate;
        }
        if (logReport.getFromDate() != null) {
            fromDate = logReport.getFromDate().format(dateTimeFormatter);
        }
        return fromDate;
    }

    public static String getToDate(LogReport logReport, DateTimeFormatter dateTimeFormatter) {
        String toDate = "-";
        if (logReport == null || dateTimeFormatter == null) {
            throw new IllegalArgumentException();
        }
        if (logReport.getToDate() != null) {
            toDate = logReport.getToDate().format(dateTimeFormatter);
        }
        return toDate;
    }
}
