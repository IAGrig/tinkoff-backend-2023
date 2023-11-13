package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task6 {
    public boolean isSubstringOf(String S, String T) {
        Pattern pattern = Pattern.compile(S, Pattern.LITERAL);
        Matcher matcher = pattern.matcher(T);
        return matcher.find();
    }
}
