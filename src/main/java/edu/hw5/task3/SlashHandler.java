package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public class SlashHandler extends DateHandler {

    @Override
    public Optional<LocalDate> parseDate(String string) {
        String[] parts = string.split("/");
        if (parts.length != 3) { // can't handle
            if (next != null) {
                return next.parseDate(string);
            }
            return Optional.empty();
        }
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        if (year < 100) {
            year += 2000;
        }
        return Optional.of(LocalDate.of(year, month, day));
    }

}
