package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;

public class Task2 {
    public String[] fridays13OnYear(int year) {
        ArrayList<String> result = new ArrayList<>();
        LocalDate localDate = LocalDate.of(year, 1, 13);
        for (int i = 0; i < 12; i++) {
            DayOfWeek dayOfWeek = localDate.getDayOfWeek();
            if (dayOfWeek == DayOfWeek.FRIDAY) {
                result.add(localDate.toString());
            }
            localDate = localDate.plusMonths(1);
        }
        return result.toArray(new String[0]);
    }

    public String getNextFriday13(LocalDate date) {
        while (date.getDayOfMonth() != 13 || date.getDayOfWeek() != DayOfWeek.FRIDAY) {
            date = date.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        }
        return date.toString();
    }
}
