package by.traning.task05.service.action.impl;


import by.traning.task05.bean.Quadrilateral;
import by.traning.task05.service.action.CalculatorAction;
import by.traning.task05.service.action.CalculatorHelper;
import by.traning.task05.service.validator.quadrilateralvalidator.SquareValidator;
import lombok.NonNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * SquareCalculator class implementing AreaCalculator interface.
 * we have here only one method is to calculate the are of a Square according to the side formula
 * ( area = sideLength ^ 2 )
 */
public class SquareCalculatorAction implements CalculatorAction {
    private Logger logger = LogManager.getLogger(SquareCalculatorAction.class);

    /**
     * an object of the RhombusValidator class
     */
    private final SquareValidator squareValidator = new SquareValidator();

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
        String result = "Square";
        logger.info(String.format(METHOD_CORRECTLY, result));
        return result;
    }

    @Override
    public double areaCalculator(@NonNull Quadrilateral quadrilateral) {
        logger.debug(String.format("The method is invoked, quadrilateral = %s", quadrilateral));
        double area = 0;
        Quadrilateral.Point pointA = quadrilateral.getPointA();
        Quadrilateral.Point pointB = quadrilateral.getPointB();
        double sideLength = frequentlyUsedFormula.distance(pointA, pointB);
        if(squareValidator.isSquare(quadrilateral)){
            area = Math.pow(sideLength, 2);
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
        if(squareValidator.isSquare(quadrilateral)){
            perimeter = 4 * sideLength;
        }
        logger.info(String.format(METHOD_CORRECTLY, perimeter));
        return perimeter;
    }
}