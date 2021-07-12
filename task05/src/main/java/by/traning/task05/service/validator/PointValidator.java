package by.traning.task05.service.validator;

import by.traning.task05.service.util.FrequentlyUsedRegex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Point validation class
 */
public class PointValidator {
    private static Logger logger = LogManager.getLogger(PointValidator.class);

    /**
     * To check if the array of string is containing 4 points coordinate and all of the points are numbers by:
     * 1 - check if the length of the string array is exactly 4, if it is not 4 incrementing the invalidCoordinate by 1.
     * 2 - if the number is not numeric also incrementing the invalidCoordinate by 1.
     * Any incrementing means that the line of point is not a isValidRectangle point.
     * @param arrayOfPoints to check if it is has isValidRectangle points coordinate
     * @return true if the string isValidRectangle 4 points also they are all numbers.
     */
    public boolean isValidPoint(String[] arrayOfPoints) {
        logger.debug("The method is invoked");
        if(arrayOfPoints == null){
            logger.info("The method worked correctly, result = false");
            return false;
        }
        int invalidCoordinate = 0;
        if (arrayOfPoints.length < FrequentlyUsedRegex.VALID_ARRAY_POINTS_LENGTH ||
                arrayOfPoints.length > FrequentlyUsedRegex.VALID_ARRAY_POINTS_LENGTH) {
            invalidCoordinate++;
        }
        NumericValueValidator numericValidator = new NumericValueValidator();
        for (String arrayOfPoint : arrayOfPoints) {
            int delimiter = arrayOfPoint.indexOf(FrequentlyUsedRegex.COMMA_SEPARATOR);
            if(delimiter != FrequentlyUsedRegex.INDEX_ZERO){
                if (!(numericValidator.isNumeric(arrayOfPoint.substring(
                        FrequentlyUsedRegex.INDEX_ZERO, delimiter)))) {
                    invalidCoordinate++;
                }else if (!numericValidator.isNumeric(arrayOfPoint.substring(
                        delimiter + FrequentlyUsedRegex.NEXT_INDEX))){
                    invalidCoordinate++;
                }
            }
        }
        boolean result = invalidCoordinate == 0;
        logger.info(String.format("The method worked correctly, result = %s", result));
        return result;
    }
}
