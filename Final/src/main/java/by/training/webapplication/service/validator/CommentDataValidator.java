package by.training.webapplication.service.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommentDataValidator {
    private static final String REGEX_COMMENT = "^.{0,2000}$";

    private Pattern pattern;
    private Matcher matcher;

    public boolean isCommentValid(String comment) {
        if (comment.equals("")) {
            return false;
        }
        pattern = Pattern.compile(REGEX_COMMENT);
        matcher = pattern.matcher(comment);
        return matcher.matches();
    }
}
