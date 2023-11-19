package edu.project3;

import java.time.OffsetDateTime;

public class CmdArgumentsHandler {
    public static String getPath(String[] args) {
        for (int i = 0; i < args.length - 1; i++) { // length - 1 for avoiding access outside the array
            if (args[i].equals("--path")) {
                return args[i + 1];
            }
        }
        return "";
    }

    public static OffsetDateTime getFromDate(String[] args) {
        for (int i = 0; i < args.length - 1; i++) { // length - 1 for avoiding access outside the array
            if (args[i].equals("--from")) {
                String dateString = args[i + 1];
                return OffsetDateTime.parse(dateString);
            }
        }
        return OffsetDateTime.MIN;
    }

    public static OffsetDateTime getToDate(String[] args) {
        for (int i = 0; i < args.length - 1; i++) { // length - 1 for avoiding access outside the array
            if (args[i].equals("--to")) {
                String dateString = args[i + 1];
                return OffsetDateTime.parse(dateString);
            }
        }
        return OffsetDateTime.now();
    }

    public static OutputFormat getOutputFormat(String[] args) {
        for (int i = 0; i < args.length - 1; i++) { // length - 1 for avoiding access outside the array
            if (args[i].equals("--format")) {
                String formatString = args[i + 1];
                switch (formatString) {
                    case "markdown":
                        return OutputFormat.MARKDOWN;
                    case "adoc":
                        return OutputFormat.ADOC;
                }
            }
        }
        return null;
    }
}
