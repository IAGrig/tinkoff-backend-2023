package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public class SlashHandler extends DateHandler {

    @Override
    public Optional<LocalDate> parseDate(String string) {
        String[] parts = string.split("/");
        if (parts.length != CORRECT_DATE_PARTS_COUNT) {
            if (next != null) {
                return next.parseDate(string);
            }
            return Optional.empty();
        }
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        // special case of two-digit year representation (10.10.12 = 10.10.2012)
        final int CENTURY_YEARS = 100;
        final int CURENT_CENTURY_YEARS_DIFF = 2000;
        if (year < CENTURY_YEARS) {
            year += CURENT_CENTURY_YEARS_DIFF;
        }
        return Optional.of(LocalDate.of(year, month, day));
    }

}
