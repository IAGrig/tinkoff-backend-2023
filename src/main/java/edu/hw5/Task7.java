package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task7 {
    @SuppressWarnings("MethodName")
    public boolean task7_1(String input) {
        Pattern pattern = Pattern.compile("^[01]{2}0[01]*");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    @SuppressWarnings("MethodName")
    public boolean task7_2(String input) {
        Pattern pattern = Pattern.compile("^([01])[01]*(\\1)$");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    @SuppressWarnings("MethodName")
    public boolean task7_3(String input) {
        Pattern pattern = Pattern.compile("^[01]{1,3}$");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }
}
