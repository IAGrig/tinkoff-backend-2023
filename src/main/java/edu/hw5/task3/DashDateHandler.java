package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public class DashDateHandler extends DateHandler {

    @Override
    public Optional<LocalDate> parseDate(String string) {
        String[] parts = string.split("-");
        if (parts.length != CORRECT_DATE_PARTS_COUNT) {
            if (next != null) {
                return next.parseDate(string);
            }
            return Optional.empty();
        }
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);
        return Optional.of(LocalDate.of(year, month, day));
    }
}
