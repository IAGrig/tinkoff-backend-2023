package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task5 {
    public boolean checkCarNumber(String string) {
        Pattern pattern = Pattern.compile("^[АВЕКМНОРСТУХ][0-9]{3}[АВЕКМНОРСТУХ]{2}[0-9]{2,3}$");
        Matcher matcher = pattern.matcher(string);
        return matcher.find();
    }
}
