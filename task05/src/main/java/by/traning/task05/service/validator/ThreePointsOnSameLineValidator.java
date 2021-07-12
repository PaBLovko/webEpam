package by.traning.task05.service.validator;

import by.traning.task05.bean.Quadrilateral;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Three points on same line validation class
 */
public class ThreePointsOnSameLineValidator {
    private static Logger logger = LogManager.getLogger(ThreePointsOnSameLineValidator.class);

    /**
     * checking the position of three points on one straight line
     * @param pointA coordinate 1
     * @param pointB coordinate 2
     * @param pointC coordinate 3
     * @return true if 3 points are on the same line, false if they are on a different location
     */
    public boolean isThreePointsOnSameLine(Quadrilateral.Point pointA, Quadrilateral.Point pointB,
                                           Quadrilateral.Point pointC) {
        logger.debug(String.format("The method is invoked, pointA = %s, pointB = %s, pointC = %s",
                pointA, pointB, pointC));
        if(pointA == null || pointB == null || pointC == null){
            logger.info("The method worked correctly, result = false");
            return false;
        }
        int x1 = pointA.getCoordinateX();
        int y1 = pointA.getCoordinateY();
        int x2 = pointB.getCoordinateX();
        int y2 = pointB.getCoordinateY();
        int x3 = pointC.getCoordinateX();
        int y3 = pointC.getCoordinateY();
        boolean result = ((y3 - y2) * (x2 - x1) == (y2 - y1) * (x3 - x2));
        logger.info(String.format("The method worked correctly, result = %s", result));
        return result;
    }
}
