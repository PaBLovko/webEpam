package by.traning.task05.service.action.impl;


import by.traning.task05.bean.Quadrilateral;
import by.traning.task05.service.action.CalculatorAction;
import by.traning.task05.service.action.CalculatorHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TrapezoidCalculatorAction implements CalculatorAction {
    private Logger logger = LogManager.getLogger(TrapezoidCalculatorAction.class);

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
        String result = "Trapezoid";
        logger.info(String.format(METHOD_CORRECTLY, result));
        return result;
    }

    /**
     * calculating the are of a Trapezoid by dividing the Trapezoid into 2 triangle, calculating the are of each
     * triangle separately then adding them together to get the area of the Trapezoid.
     * @param quadrilateral to calculate it is area as a Trapezoid
     * @return area of a Trapezoid
     */
    @Override
    public double areaCalculator(Quadrilateral quadrilateral) {
        logger.debug(String.format("The method is invoked, quadrilateral = %s", quadrilateral));
        Quadrilateral.Point pointA = quadrilateral.getPointA();
        Quadrilateral.Point pointB = quadrilateral.getPointB();
        Quadrilateral.Point pointC = quadrilateral.getPointC();
        Quadrilateral.Point pointD = quadrilateral.getPointD();

        double sideAB = calculatorHelper.distance(pointA, pointB);
        double sideBC = calculatorHelper.distance(pointB, pointC);
        double sideCD = calculatorHelper.distance(pointC, pointD);
        double sideDA = calculatorHelper.distance(pointD, pointA);

        double diagonalAC = calculatorHelper.distance(pointA, pointC);

        double firstTriangleArea = calculatorHelper.triangleArea(sideAB, sideBC, diagonalAC);
        double secondTriangleArea = calculatorHelper.triangleArea(sideCD, sideDA, diagonalAC);

        double result = firstTriangleArea + secondTriangleArea;
        logger.info(String.format(METHOD_CORRECTLY, result));
        return result;
    }

    /**
     * @param quadrilateral to calculate it is perimeter as a Trapezoid
     * @return perimeter of a Trapezoid
     */
    @Override
    public double perimeterCalculator(Quadrilateral quadrilateral) {
        logger.debug(String.format("The method is invoked, quadrilateral = %s", quadrilateral));
        Quadrilateral.Point pointA = quadrilateral.getPointA();
        Quadrilateral.Point pointB = quadrilateral.getPointB();
        Quadrilateral.Point pointC = quadrilateral.getPointC();
        Quadrilateral.Point pointD = quadrilateral.getPointD();
        double sideAB = calculatorHelper.distance(pointA, pointB);
        double sideBC = calculatorHelper.distance(pointB, pointC);
        double sideCD = calculatorHelper.distance(pointC, pointD);
        double sideDA = calculatorHelper.distance(pointD, pointA);
        double result = sideAB + sideBC + sideCD + sideDA;
        logger.info(String.format(METHOD_CORRECTLY, result));
        return result;
    }
}