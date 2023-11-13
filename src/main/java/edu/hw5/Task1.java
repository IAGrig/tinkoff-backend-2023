package edu.hw5;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.stream.IntStream;

public class Task1 {
    public String calculateAverageTime(String[] strings) {
        int[] durations = new int[strings.length];
        for (int session = 0; session < strings.length; session++) {
            String[] parts = strings[session].split(" - ");
            if (parts.length != 2) {
                return null;
            }
            SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd, HH:mm");
            try {
                Date dateStart = parser.parse(parts[0]);
                Date dateEnd = parser.parse(parts[1]);
                Duration duration = Duration.between(dateStart.toInstant(), dateEnd.toInstant());
                durations[session] = (int) duration.getSeconds();
            } catch (java.text.ParseException e) {
                return null;
            }
        }
        int averageSecondsCount = IntStream.of(durations).sum() / durations.length;
        StringBuilder result = new StringBuilder();
        final int SECONDS_PER_MINUTE = 60;
        final int SECONDS_PER_HOUR = SECONDS_PER_MINUTE * 60;
        final int SECONDS_PER_DAY = SECONDS_PER_HOUR * 24;

        if (averageSecondsCount / SECONDS_PER_DAY > 0) {
            result.append(String.format("%dд ", averageSecondsCount / SECONDS_PER_DAY));
            averageSecondsCount -= (averageSecondsCount / SECONDS_PER_DAY) * SECONDS_PER_DAY;
        }
        if (averageSecondsCount / SECONDS_PER_HOUR > 0) {
            result.append(String.format("%dч ", averageSecondsCount / SECONDS_PER_HOUR));
            averageSecondsCount -= (averageSecondsCount / SECONDS_PER_HOUR) * SECONDS_PER_HOUR;
        }
        if (averageSecondsCount / SECONDS_PER_MINUTE > 0) {
            result.append(String.format("%dм ", averageSecondsCount / SECONDS_PER_MINUTE));
            averageSecondsCount -= (averageSecondsCount / SECONDS_PER_MINUTE) * SECONDS_PER_MINUTE;
        }

        return result.toString().trim();
    }
}
