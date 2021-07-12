package by.traning.task05.service.action.impl;

import by.traning.task05.bean.Quadrilateral;
import by.traning.task05.service.action.CalculatorAction;
import by.traning.task05.service.action.CalculatorHelper;
import lombok.NonNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The class that implements the interface
 */
public class RectangleCalculatorAction implements CalculatorAction {
    private static Logger logger = LogManager.getLogger(RectangleCalculatorAction.class);

    /**
     * an object of the CalculatorHelper class
     */
    private final CalculatorHelper calculatorHelper = new CalculatorHelper();

    /**
     * string class literal for logging the correct operation of the method
     */
    public static final String METHOD_CORRECTLY = "The method worked correctly, result = %s";

    @Override
    public String resultingFigureName() {
        logger.debug("The method is invoked");
        String result = "Rectangle";
        logger.info(String.format(METHOD_CORRECTLY, result));
        return result;
    }

    @Override
    public double areaCalculator(@NonNull Quadrilateral quadrilateral) {
        logger.debug(String.format("The method is invoked, quadrilateral = %s", quadrilateral));
        Quadrilateral.Point pointA = quadrilateral.getPointA();
        Quadrilateral.Point pointB = quadrilateral.getPointB();
        Quadrilateral.Point pointC = quadrilateral.getPointC();
        double sideAB = calculatorHelper.distance(pointA, pointB);
        double sideBC = calculatorHelper.distance(pointB, pointC);
        double result = sideAB * sideBC;
        logger.info(String.format(METHOD_CORRECTLY, result));
        return result;
    }

    @Override
    public double perimeterCalculator(@NonNull Quadrilateral quadrilateral) {
        logger.debug(String.format("The method is invoked, quadrilateral = %s", quadrilateral));
        Quadrilateral.Point pointA = quadrilateral.getPointA();
        Quadrilateral.Point pointB = quadrilateral.getPointB();
        Quadrilateral.Point pointC = quadrilateral.getPointC();
        double sideAB = calculatorHelper.distance(pointA, pointB);
        double sideBC = calculatorHelper.distance(pointB, pointC);
        double result = 2 * (sideAB + sideBC);
        logger.info(String.format(METHOD_CORRECTLY, result));
        return result;
    }
}
