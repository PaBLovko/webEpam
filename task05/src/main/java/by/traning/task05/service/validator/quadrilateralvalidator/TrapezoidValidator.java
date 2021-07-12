package by.traning.task05.service.validator.quadrilateralvalidator;

import by.traning.task05.bean.Quadrilateral;
import by.traning.task05.service.action.CalculatorHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Trapezoid validation class
 */
public class TrapezoidValidator {
    private static Logger logger = LogManager.getLogger(TrapezoidValidator.class);

    /**
     * Check if the figure is a Trapezoid or not .
     * @param quadrilateral shape to check if it is a Trapezoid
     * @return true if the shape is a Trapezoid, false if it is not a Trapezoid.
     */
    public boolean isTrapezoid(Quadrilateral quadrilateral) {
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

        double sideAB = calculatorHelper.slope(pointA, pointB);
        double sideBC = calculatorHelper.slope(pointB, pointC);
        double sideCD = calculatorHelper.slope(pointC, pointD);
        double sideDA = calculatorHelper.slope(pointD, pointA);

        if(sideAB == sideCD && sideBC != sideDA){
            logger.info("The method worked correctly, result = true");
            return true;
        }
        boolean result = sideBC == sideDA && sideAB != sideCD;
        logger.info(String.format("The method worked correctly, result = %s", result));
        return result;
    }
}
