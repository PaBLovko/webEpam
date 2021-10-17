package by.training.webapplication.service.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DishDataValidator {
    private static final String REGEX_AMOUNT = "^(([1-9]{1,2})|([1-9][0]))$";
    private static final String REGEX_ID = "^([1-9][0-9]{0,8})$";
    private static final String REGEX_WEIGHT = "^((3000)|([1-9][0-9][0-9])|[1-2][0-9][0-9][0-9])$";
    private static final String REGEX_PICTURE = "^.{0,255}$";
    private static final String REGEX_DESCRIPTION = "^.{0,1900}$";
    private static final String REGEX_NAME = "^[A-Z][a-z]{3,20}$";
    private static final String REGEX_PRICE = "^(([3-9]\\.00)|([1-9][0-9]\\.00)|(1[0-9][0-9]\\.00))$";

    private Pattern pattern;
    private Matcher matcher;

    public boolean isDishAmountValid(String amount) {
        if (amount.equals("")) {
            return false;
        }
        pattern = Pattern.compile(REGEX_AMOUNT);
        matcher = pattern.matcher(amount);
        return matcher.matches();
    }
    public boolean isWeightValid(String weight) {
        if (weight.equals("")) {
            return false;
        }
        pattern = Pattern.compile(REGEX_WEIGHT);
        matcher = pattern.matcher(weight);
        return matcher.matches();
    }

    public boolean isPriceValid(String price) {
        if (price.equals("")) {
            return false;
        }
        pattern = Pattern.compile(REGEX_PRICE);
        matcher = pattern.matcher(price);
        return matcher.matches();
    }

    public boolean isPictureValid(String picture) {
        if (picture.equals("")) {
            return false;
        }
        pattern = Pattern.compile(REGEX_PICTURE);
        matcher = pattern.matcher(picture);
        return matcher.matches();
    }

    public boolean isDescriptionValid(String description) {
        if (description.equals("")) {
            return false;
        }
        pattern = Pattern.compile(REGEX_DESCRIPTION);
        matcher = pattern.matcher(description);
        return matcher.matches();
    }

    public boolean isNameValid(String name) {
        if (name.equals("")) {
            return false;
        }
        pattern = Pattern.compile(REGEX_NAME);
        matcher = pattern.matcher(name);
        return matcher.matches();
    }
    public boolean isIdValid(String id) {
        if (id.equals("")) {
            return false;
        }
        pattern = Pattern.compile(REGEX_ID);
        matcher = pattern.matcher(id);
        return matcher.matches();
    }
}

