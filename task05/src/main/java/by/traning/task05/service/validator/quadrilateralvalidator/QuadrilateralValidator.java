package by.traning.task05.service.validator.quadrilateralvalidator;

import by.traning.task05.bean.Quadrilateral;
import by.traning.task05.service.validator.ThreePointsOnSameLineValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Quadrilateral validation class
 */
public class QuadrilateralValidator {
    private static Logger logger = LogManager.getLogger(QuadrilateralValidator.class);

    /**
     * Checking the validity of a square
     * @param pointA coordinate 1
     * @param pointB coordinate 2
     * @param pointC coordinate 3
     * @param pointD coordinate 4
     * @return true is one of the combination of 3 points is on the same line, false if they are on different location
     */
    public boolean isValidQuadrilateral(Quadrilateral.Point pointA, Quadrilateral.Point pointB,
                                        Quadrilateral.Point pointC, Quadrilateral.Point pointD) {
        logger.debug(String.format("The method is invoked, pointA = %s, pointB = %s, pointC = %s",
                pointA, pointB, pointC));
        if(pointA == null || pointB == null || pointC == null || pointD == null){
            logger.info("The method worked correctly, result = false");
            return false;
        }
        ThreePointsOnSameLineValidator pointsValidator = new ThreePointsOnSameLineValidator();
        boolean result = pointsValidator.isThreePointsOnSameLine(pointA, pointB, pointC) ||
                pointsValidator.isThreePointsOnSameLine(pointB, pointC, pointD) ||
                pointsValidator.isThreePointsOnSameLine(pointC, pointD, pointA) ||
                pointsValidator.isThreePointsOnSameLine(pointD, pointA, pointC);
        logger.info(String.format("The method worked correctly, result = %s", result));
        return result;
    }
}
