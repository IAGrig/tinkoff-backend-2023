package edu.hw5;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.stream.IntStream;

public class Task1 {
    public String calculateAverageTime(String[] strings) {
        Duration totalDuration = Duration.ZERO;
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
                totalDuration = totalDuration.plus(duration);
            } catch (java.text.ParseException e) {
                return null;
            }
        }

        StringBuilder result = new StringBuilder();
        totalDuration = totalDuration.dividedBy(strings.length);

        if (totalDuration.toDaysPart() > 0) {
            result.append(String.format("%dд ", totalDuration.toDaysPart()));
        }
        if (totalDuration.toHoursPart() > 0) {
            result.append(String.format("%dч ", totalDuration.toHoursPart()));
        }
        if (totalDuration.toMinutesPart() > 0) {
            result.append(String.format("%dм ", totalDuration.toMinutesPart()));
        }

        return result.toString().trim();
    }
}
