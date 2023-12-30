package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Pattern;

public class DaysAgoHandler extends DateHandler {

    @Override
    public Optional<LocalDate> parseDate(String string) {
        if (Pattern.matches("days? ago", string)) {
            int countOfDaysAgo = Integer.parseInt(string.split(" ")[0]);
            return Optional.of(LocalDate.now().minusDays(countOfDaysAgo));
        }

        if (next != null) {
            return next.parseDate(string);
        }
        return Optional.empty();
    }
}
