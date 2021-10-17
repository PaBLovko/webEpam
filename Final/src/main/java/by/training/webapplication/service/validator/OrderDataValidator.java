package by.training.webapplication.service.validator;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderDataValidator {
    private static final String REGEX_STATUS = "^(not ready|ready|delivered|not delivered)$";
    private static final String REGEX_DATE = "^((2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])T(0[0-9]|1[0-9]|2[0-3])(:([0-5][0-9]))$";

    private Pattern pattern;
    private Matcher matcher;

    public boolean isStatusValid(String status) {
        if (status.equals("")) {
            return false;
        }
        pattern = Pattern.compile(REGEX_STATUS);
        matcher = pattern.matcher(status);
        return matcher.matches();
    }
    public boolean isDateValid(String date) {
        if (date.equals("")) {
            return false;
        }
        pattern = Pattern.compile(REGEX_DATE);
        matcher = pattern.matcher(date);
        return matcher.matches();
    }

    public boolean isDateAfter(LocalDateTime newDate) {
        LocalDateTime now = LocalDateTime.now();
        return newDate.isAfter(now);
    }
}
