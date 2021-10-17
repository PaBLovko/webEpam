package by.training.webapplication.service.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDataValidator {
    private static final String REGEX_SURNAME = "(^[A-Z][a-z]{0,35}(-[A-Z])*[a-z]{0,35}$)|(^[А-Я][а-я]{0,35}(-[А-Я])*[а-я]{0,35}$)|(^[A-Z][a-z]{0,70}$)|(^[А-Я][а-я]{0,70}$)";
    private static final String REGEX_NAME = "(^[A-Z][a-z]{0,35}(-[A-Z])*[a-z]{0,35}$)|(^[А-Я][а-я]{0,35}(-[А-Я])*[а-я]{0,35}$)|(^[A-Z][a-z]{0,70}$)|(^[А-Я][а-я]{0,70}$)";
    private static final String REGEX_PATRONYMIC = "(^[A-Z][a-z]{0,35}(-[A-Z])*[a-z]{0,35}$)|(^[А-Я][а-я]{0,35}(-[А-Я])*[а-я]{0,35}$)|(^[A-Z][a-z]{0,70}$)|(^[А-Я][а-я]{0,70}$)";
    private static final String REGEX_ADDRESS = "^.{5,70}$";
    private static final String REGEX_NOTE = "^.{0,250}$";
    private static final String REGEX_PHONE = "^[8]-(033|029|044|017)-[1-9][0-9]{2}-[0-9]{2}-[0-9]{2}$";
    private static final String REGEX_LOGIN = "^[a-zA-Z0-9_-]{5,12}$";
    private static final String REGEX_PASSWORD = "^[a-zA-Z0-9_-]{5,12}$";
    private static final String REGEX_ID = "^([1-9][0-9]{0,8})$";
    private static final String REGEX_ROLE = "^(1|2|3)$";

    private Pattern pattern;
    private Matcher matcher;

    public boolean isSurnameValid(String surname) {
        if (surname.equals("")) {
            return false;
        }
        pattern = Pattern.compile(REGEX_SURNAME);
        matcher = pattern.matcher(surname);
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

    public boolean isPatronymicValid(String patronymic) {
        if (patronymic.equals("")) {
            return false;
        }
        pattern = Pattern.compile(REGEX_PATRONYMIC);
        matcher = pattern.matcher(patronymic);
        return matcher.matches();
    }

    public boolean isAddressValid(String address) {
        if (address.equals("")) {
            return false;
        }
        pattern = Pattern.compile(REGEX_ADDRESS);
        matcher = pattern.matcher(address);
        return matcher.matches();
    }

    public boolean isPhoneValid(String phone) {
        if (phone.equals("")) {
            return false;
        }
        pattern = Pattern.compile(REGEX_PHONE);
        matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    public boolean isLoginValid(String login) {
        if (login.equals("")) {
            return false;
        }
        pattern = Pattern.compile(REGEX_LOGIN);
        matcher = pattern.matcher(login);
        return matcher.matches();
    }

    public boolean isPasswordValid(String password) {
        if (password.equals("")) {
            return false;
        }
        pattern = Pattern.compile(REGEX_PASSWORD);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public boolean isNoteValid(String note) {
        if (note.equals("")) {
            return false;
        }
        pattern = Pattern.compile(REGEX_NOTE);
        matcher = pattern.matcher(note);
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
    public boolean isRoleValid(String role) {
        if (role.equals("")) {
            return false;
        }
        pattern = Pattern.compile(REGEX_ROLE);
        matcher = pattern.matcher(role);
        return matcher.matches();
    }
}
