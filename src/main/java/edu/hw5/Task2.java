package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;

public class Task2 {
    private static final int MONTHS_COUNT = 12;
    private static final int DAY_NUMBER = 13;

    public String[] fridays13OnYear(int year) {
        ArrayList<String> result = new ArrayList<>();
        LocalDate localDate = LocalDate.of(year, 1, DAY_NUMBER);
        for (int i = 0; i < MONTHS_COUNT; i++) {
            DayOfWeek dayOfWeek = localDate.getDayOfWeek();
            if (dayOfWeek == DayOfWeek.FRIDAY) {
                result.add(localDate.toString());
            }
            localDate = localDate.plusMonths(1);
        }
        return result.toArray(new String[0]);
    }

    public String getNextFriday13(LocalDate date) {
        LocalDate result = date;
        while (result.getDayOfMonth() != DAY_NUMBER || result.getDayOfWeek() != DayOfWeek.FRIDAY) {
            result = result.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        }
        return result.toString();
    }
}
