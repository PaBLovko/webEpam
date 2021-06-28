package by.traning.task05.validator.quadrilateralvalidator;

import by.traning.task05.bean.Quadrilateral;
import by.traning.task05.service.CalculatorHelper;

public class TrapezoidValidator {

    private CalculatorHelper calculatorHelper = new CalculatorHelper();

    /**
     * Check if the figure is a Trapezoid or not .
     * @param quadrilateral shape to check if it is a Trapezoid
     * @return true if the shape is a Trapezoid, false if it is not a Trapezoid.
     */
    public boolean isTrapezoid(Quadrilateral quadrilateral) {
        if (quadrilateral == null) {
            return false;
        }

        Quadrilateral.Point pointA = quadrilateral.getPointA();
        Quadrilateral.Point pointB = quadrilateral.getPointB();
        Quadrilateral.Point pointC = quadrilateral.getPointC();
        Quadrilateral.Point pointD = quadrilateral.getPointD();

        double sideAB = calculatorHelper.slope(pointA, pointB);
        double sideBC = calculatorHelper.slope(pointB, pointC);
        double sideCD = calculatorHelper.slope(pointC, pointD);
        double sideDA = calculatorHelper.slope(pointD, pointA);

        if(sideAB == sideCD && sideBC != sideDA){
            return true;
        }

        return sideBC == sideDA && sideAB != sideCD;
    }
}
