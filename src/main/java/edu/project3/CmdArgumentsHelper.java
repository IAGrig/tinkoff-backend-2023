package edu.project3;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class CmdArgumentsHelper {
    private CmdArgumentsHelper() {
    }

    public static String getPath(String[] args) {
        if (args == null) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < args.length - 1; i++) { // length - 1 for avoiding access outside the array
            if (args[i].equals("--path")) {
                return args[i + 1];
            }
        }
        return "";
    }

    public static OffsetDateTime getFromDate(String[] args) {
        if (args == null) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < args.length - 1; i++) { // length - 1 for avoiding access outside the array
            if (args[i].equals("--from")) {
                String dateString = args[i + 1];
                return LocalDate.parse(dateString).atStartOfDay(ZoneOffset.UTC).toOffsetDateTime();
            }
        }
        return OffsetDateTime.MIN;
    }

    public static OffsetDateTime getToDate(String[] args) {
        if (args == null) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < args.length - 1; i++) { // length - 1 for avoiding access outside the array
            if (args[i].equals("--to")) {
                String dateString = args[i + 1];
                return LocalDate.parse(dateString).atStartOfDay(ZoneOffset.UTC).toOffsetDateTime();
            }
        }
        return OffsetDateTime.now();
    }

    public static OutputFormat getOutputFormat(String[] args) {
        if (args == null) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < args.length - 1; i++) { // length - 1 for avoiding access outside the array
            if (args[i].equals("--format")) {
                String formatString = args[i + 1];
                switch (formatString) {
                    case "markdown":
                        return OutputFormat.MARKDOWN;
                    case "adoc":
                        return OutputFormat.ADOC;
                    default:
                        return OutputFormat.MARKDOWN;
                }
            }
        }
        return null;
    }

    public static String getResultPath(String[] args) {
        if (args == null) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < args.length - 1; i++) { // length - 1 for avoiding access outside the array
            if (args[i].equals("--result")) {
                return args[i + 1];
            }
        }
        return "result";
    }
}
