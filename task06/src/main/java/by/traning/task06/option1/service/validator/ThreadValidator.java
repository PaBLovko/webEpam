package by.traning.task06.option1.service.validator;

import java.util.List;

public class ThreadValidator implements Validator {

    private static final int MIN_THREAD_AMOUNT = 4;
    private static final int MAX_THREAD_AMOUNT = 6;
    private static final String THREAD_REGEX_PATTERN = "^[^-0]\\d*";

    @Override
    public boolean validate(List<String> list) {
        return list.size() >= MIN_THREAD_AMOUNT && list.size() <= MAX_THREAD_AMOUNT
                && list.stream().allMatch(s -> s.matches(THREAD_REGEX_PATTERN));
    }
}