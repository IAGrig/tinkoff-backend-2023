package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public class WordDateHandler extends DateHandler {

    @SuppressWarnings("ReturnCount")
    @Override
    public Optional<LocalDate> parseDate(String string) {
        switch (string) {
            case "tomorrow" -> {
                return Optional.of(LocalDate.now().plusDays(1));
            }
            case "today" -> {
                return Optional.of(LocalDate.now());
            }
            case "yesterday" -> {
                return Optional.of(LocalDate.now().minusDays(1));
            }
            default -> {
                if (next != null) {
                    return next.parseDate(string);
                }
                return Optional.empty();
            }
        }
    }
}
