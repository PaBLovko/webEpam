package by.traning.task05.service.validator.quadrilateralvalidator;

import by.traning.task05.bean.Quadrilateral;
import by.traning.task05.service.action.CalculatorHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * To check if the shape is a Rhombus gavin four geometric points.
 */
public class RhombusValidator {
    private static Logger logger = LogManager.getLogger(RhombusValidator.class);

    /**
     * Check if the figure is Rhombus or not by checking the distance between the two diagonal of the shape if they are not equals,
     * then checking if each two points that form side of the Rhombus,
     * if the side of the Rhombus are equals so it is a Rhombus.
     * @param quadrilateral shape to check if it is a Rhombus
     * @return true if the shape is a Rhombus, false if it is not a Rhombus.
     */
    public boolean isRhombus(Quadrilateral quadrilateral) {
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

        if (diagonalAC != diagonalBD) {
            boolean result = sideAB == sideBC && sideBC == sideCD && sideCD == sideDA;
            logger.info(String.format("The method worked correctly, result = %s", result));
            return result;
        }
        logger.info("The method worked correctly, result = false");
        return false;
    }
}
