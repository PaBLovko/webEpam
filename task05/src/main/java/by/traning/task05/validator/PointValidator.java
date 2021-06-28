package by.traning.task05.validator;

import by.traning.task05.util.FrequentlyUsedRegex;

public class PointValidator {

    private NumericValueValidator numericValidator = new NumericValueValidator();

    /**
     * To check if the array of string is containing 4 points coordinate and all of the points are numbers by:
     * 1 - check if the length of the string array is exactly 4, if it is not 4 incrementing the invalidCoordinate by 1.
     * 2 - if the number is not numeric also incrementing the invalidCoordinate by 1.
     * Any incrementing means that the line of point is not a isValidRectangle point.
     * @param arrayOfPoints to check if it is has isValidRectangle points coordinate
     * @return true if the string isValidRectangle 4 points also they are all numbers.
     */
    public boolean isValidPoint(String[] arrayOfPoints) {
        if(arrayOfPoints == null){
            throw new IllegalArgumentException("arrayOfPoints is not allow to be null");
        }
        int invalidCoordinate = 0;
        if (arrayOfPoints.length < FrequentlyUsedRegex.VALID_ARRAY_POINTS_LENGTH ||
                arrayOfPoints.length > FrequentlyUsedRegex.VALID_ARRAY_POINTS_LENGTH) {
            invalidCoordinate++;
        }

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
        return invalidCoordinate == 0;
    }
}
