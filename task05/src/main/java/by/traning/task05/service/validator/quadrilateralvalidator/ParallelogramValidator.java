package by.traning.task05.service.validator.quadrilateralvalidator;

import by.traning.task05.bean.Quadrilateral;
import by.traning.task05.service.action.CalculatorHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Parallelogram validation class
 */
public class ParallelogramValidator {
    private static Logger logger = LogManager.getLogger(ParallelogramValidator.class);

    /**
     * By checking the distance between the points, Parallelogram has to have each 2 sides in parallel and the same length,
     * also the diagonals length has to not be equal.
     * @param quadrilateral to check it if it is a Parallelogram or not.
     * @return true if it is valid Parallelogram, false iif it is not valid Parallelogram
     */
    public boolean isValidParallelogram(Quadrilateral quadrilateral) {
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
            boolean result = diagonalAC != diagonalBD;
            logger.info(String.format("The method worked correctly, result = %s", result));
            return result;
        }
        logger.info("The method worked correctly, result = false");
        return false;
    }
}
