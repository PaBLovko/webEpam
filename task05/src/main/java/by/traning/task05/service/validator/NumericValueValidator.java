package by.traning.task05.service.validator;

import by.traning.task05.service.util.FrequentlyUsedRegex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Numeric value validation class
 */
public class NumericValueValidator {
    private static Logger logger = LogManager.getLogger(NumericValueValidator.class);

    /**
     * to check if the string is numeric value or not
     * @param str to check it is numeric value or not.
     * @return true if it is numeric value or false if it is not numeric value
     */
    public boolean isNumeric(String str) {
        logger.debug(String.format("The method is invoked, str = %s", str));
        boolean result = str != null && str.matches(FrequentlyUsedRegex.NUMERIC_VALUE);
        logger.info(String.format("The method worked correctly, result = %s", result));
        return result;
    }
}
