package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task6 {
    public boolean isSubstringOf(String S, String T) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : S.toCharArray()){
            stringBuilder.append(Pattern.quote(Character.valueOf(c).toString())).append(".*");
        }
        Pattern pattern = Pattern.compile(stringBuilder.toString());
        Matcher matcher = pattern.matcher(T);
        return matcher.find();
    }
}
