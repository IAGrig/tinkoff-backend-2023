package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task8 {
    public boolean oddLength(String string) {
        Pattern pattern = Pattern.compile("^[01](?:[01]{2})*$");
        Matcher matcher = pattern.matcher(string);
        return matcher.find();
    }

    public boolean without11And111(String string) {
        Pattern pattern = Pattern.compile("^[01]*$(?<!^(?:11|111)$)");
        Matcher matcher = pattern.matcher(string);
        return matcher.find();
    }

    public boolean oneOnOddPositions(String string) {
        Pattern pattern = Pattern.compile("^1(?:[01]1)*[01]?$");
        Matcher matcher = pattern.matcher(string);
        return matcher.find();
    }

    public boolean noConsecutiveOne(String string){
        Pattern pattern = Pattern.compile("^[01]*$(?<!11)");
        Matcher matcher = pattern.matcher(string);
        return matcher.find();
    }

}
