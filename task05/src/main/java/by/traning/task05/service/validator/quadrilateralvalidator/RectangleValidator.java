package by.traning.task05.service.validator.quadrilateralvalidator;

import by.traning.task05.bean.Quadrilateral;
import by.traning.task05.service.action.CalculatorHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Rectangle validation class
 */
public class RectangleValidator {
    private static Logger logger = LogManager.getLogger(RectangleValidator.class);

    /**
     * By checking the distance between the points,
     * Rectangle has to have each 2 sides in parallel and the same length,
     * also the diagonals length has to be equal.
     * @param quadrilateral to check it if it is a Rectangle or not.
     * @return true if it is valid Rectangle, false if it is not valid Rectangle
     */
    public boolean isValidRectangle(Quadrilateral quadrilateral) {
        logger.debug(String.format("The method is invoked, quadrilateral = %s", quadrilateral));
        if (quadrilateral == null) {
            logger.info("The method worked correctly, result = false");
            return false;
        }
        Quadrilateral.Point pointA = quadrilateral.getPointA();
        Quadrilateral.Point pointB = quadrilateral.getPointB();
        Quadrilateral.Point pointC = quadrilateral.getPointC();
        Quadrilateral.Point pointD = quadrilateral.getPointD();
        CalculatorHelper calculatorHelper = new CalculatorHelper();

        double sideAB = calculatorHelper.distance(pointA, pointB);
        double sideBC = calculatorHelper.distance(pointB, pointC);
        double sideCD = calculatorHelper.distance(pointC, pointD);
        double sideDA = calculatorHelper.distance(pointD, pointA);

        double diagonalAC = calculatorHelper.distance(pointA, pointC);
        double diagonalBD = calculatorHelper.distance(pointB, pointD);
        if (sideAB == sideCD && sideBC == sideDA) {
            boolean result = diagonalAC == diagonalBD;
            logger.info(String.format("The method worked correctly, result = %s", result));
            return result;
        }
        logger.info("The method worked correctly, result = false");
        return false;
    }
}
