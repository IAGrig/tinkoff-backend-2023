package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public abstract class DateHandler {
    protected static final int CORRECT_DATE_PARTS_COUNT = 3;
    protected DateHandler next;

    public void setNextHandler(DateHandler dateHandler) {
        this.next = dateHandler;
    }

    public abstract Optional<LocalDate> parseDate(String string);
}
