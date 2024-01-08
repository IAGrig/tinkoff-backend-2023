package edu.project3;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class StatCounter {
    private final List<String> files;
    private final OffsetDateTime fromDate;
    private final OffsetDateTime toDate;
    LogReport report;

    public StatCounter(List<String> files, OffsetDateTime fromDate, OffsetDateTime toDate) {
        this.files = files;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.report = new LogReport(files, fromDate, toDate);
    }

    public LogReport getReport(Stream<String> fileLines) {
        if (fileLines == null) {
            throw new IllegalArgumentException();
        }
        fileLines.forEach(this::handleLine);
        return report;
    }

    private void handleLine(String line) {
        Pattern pattern = Pattern.compile(
            "^((?:\\d{1,3}\\.){3}\\d{1,3})\\s-\\s((?:\\d{1,3}\\.){3}\\d{1,3}|-)\\s\\[(.*)\\]\\s\\\"(.+?)\\\"\\s(\\d{3})\\s(\\d*)\\s\\\"(.+?)\\\"\\s\\\"(.+?)\\\"$");
        Matcher matcher = pattern.matcher(line);

        OffsetDateTime dateTime;
        String dateString;
        String request;
        int answerCode;
        int answerSize;
        String userAgent;

        if (matcher.find()) {
            dateString = matcher.group(3);
            request = matcher.group(4);
            answerCode = Integer.parseInt(matcher.group(5));
            answerSize = Integer.parseInt(matcher.group(6));
            userAgent = matcher.group(8);
        } else {
            return;
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);
        dateTime = OffsetDateTime.parse(dateString, dateTimeFormatter);

        if (dateTime.isAfter(fromDate) && dateTime.isBefore(toDate)) {
            String[] requestParts = request.split(" ");
            if (requestParts.length == 3) {
                report.addHttpMethod(requestParts[0]);
                report.addRequest(requestParts[1]);
            }
            report.addAnswer(answerCode, answerSize);
            report.addUserAgent(userAgent);
        }
    }
}
