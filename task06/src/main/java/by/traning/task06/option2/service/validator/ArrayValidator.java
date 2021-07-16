package by.traning.task06.option2.service.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArrayValidator {

    public boolean isLineValid(String line) {
        if (line == null) {
            return false;
        }
        String regex = "^(\\d+\\s+){9,11}\\d*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);
        return matcher.matches();
    }
}
