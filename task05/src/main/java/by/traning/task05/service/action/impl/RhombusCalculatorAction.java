package by.traning.task05.service.action.impl;

import by.traning.task05.bean.Quadrilateral;
import by.traning.task05.service.action.CalculatorAction;
import by.traning.task05.service.action.CalculatorHelper;
import by.traning.task05.service.validator.quadrilateralvalidator.RhombusValidator;
import lombok.NonNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * RhombusCalculator class implementing AreaCalculator interface.
 * we have here only one method is to calculate the are of a Rhombus according to the diagonals formula
 * ( 0.5 * (product of the diagonals))
 */
public class RhombusCalculatorAction implements CalculatorAction {
    private Logger logger = LogManager.getLogger(RhombusCalculatorAction.class);

    /**
     * an object of the RhombusValidator class
     */
    private final RhombusValidator rhombusValidator = new RhombusValidator();

    /**
     * an object of the CalculatorHelper class
     */
    private final CalculatorHelper frequentlyUsedFormula = new CalculatorHelper();

    /**
     * string class literal for logging the correct operation of the method
     */
    public static final String METHOD_CORRECTLY = "The method worked correctly, result = %s";

    @Override
    public String resultingFigureName() {
        logger.debug("The method is invoked");
        String result = "Rhombus";
        logger.info(String.format(METHOD_CORRECTLY, result));
        return result;
    }

    @Override
    public double areaCalculator(@NonNull Quadrilateral quadrilateral) {
        logger.debug(String.format("The method is invoked, quadrilateral = %s", quadrilateral));
        double area = 0;
        Quadrilateral.Point pointA = quadrilateral.getPointA();
        Quadrilateral.Point pointB = quadrilateral.getPointB();
        Quadrilateral.Point pointC = quadrilateral.getPointC();
        Quadrilateral.Point pointD = quadrilateral.getPointD();
        if(rhombusValidator.isRhombus(quadrilateral)){
            double diagonalAC = frequentlyUsedFormula.distance(pointA, pointC);
            double diagonalBD = frequentlyUsedFormula.distance(pointB, pointD);
            area = 0.5 * (diagonalAC * diagonalBD);
            //TODO(validator)
        }
        logger.info(String.format(METHOD_CORRECTLY, area));
        return area;
    }

    @Override
    public double perimeterCalculator(@NonNull Quadrilateral quadrilateral) {
        logger.debug(String.format("The method is invoked, quadrilateral = %s", quadrilateral));
        double perimeter = 0;
        Quadrilateral.Point pointA = quadrilateral.getPointA();
        Quadrilateral.Point pointB = quadrilateral.getPointB();
        double sideLength = frequentlyUsedFormula.distance(pointA, pointB);
        if(rhombusValidator.isRhombus(quadrilateral)){
            perimeter = 4 * sideLength;
        }
        logger.info(String.format(METHOD_CORRECTLY, perimeter));
        return perimeter;
    }
}