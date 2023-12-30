package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public class DateParser {
    public Optional<LocalDate> parseDate(String string) {
        DateHandler dashHandler = new DashDateHandler();
        DateHandler slashHandler = new SlashHandler();
        DateHandler wordDateHandler = new WordDateHandler();
        DateHandler daysAgoHandler = new DaysAgoHandler();

        dashHandler.setNextHandler(slashHandler);
        slashHandler.setNextHandler(wordDateHandler);
        wordDateHandler.setNextHandler(daysAgoHandler);
        return dashHandler.parseDate(string);
    }
}
