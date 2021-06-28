package by.traning.task05.validator.quadrilateralvalidator;

import by.traning.task05.bean.Quadrilateral;
import by.traning.task05.service.CalculatorHelper;

/**
 * To check if the shape is a Rhombus gavin four geometric points.
 */
public class RhombusValidator {

    private CalculatorHelper calculatorHelper = new CalculatorHelper();

    /**
     * Check if the figure is Rhombus or not by checking the distance between the two diagonal of the shape if they are not equals,
     * then checking if each two points that form side of the Rhombus,
     * if the side of the Rhombus are equals so it is a Rhombus.
     * @param quadrilateral shape to check if it is a Rhombus
     * @return true if the shape is a Rhombus, false if it is not a Rhombus.
     */
    public boolean isRhombus(Quadrilateral quadrilateral) {

        if (quadrilateral == null) {
            return false;
        }

        Quadrilateral.Point pointA = quadrilateral.getPointA();
        Quadrilateral.Point pointB = quadrilateral.getPointB();
        Quadrilateral.Point pointC = quadrilateral.getPointC();
        Quadrilateral.Point pointD = quadrilateral.getPointD();

        double sideAB = calculatorHelper.distance(pointA, pointB);
        double sideBC = calculatorHelper.distance(pointB, pointC);
        double sideCD = calculatorHelper.distance(pointC, pointD);
        double sideDA = calculatorHelper.distance(pointD, pointA);

        double diagonalAC = calculatorHelper.distance(pointA, pointC);
        double diagonalBD = calculatorHelper.distance(pointB, pointD);

        if (diagonalAC != diagonalBD) {
            return sideAB == sideBC && sideBC == sideCD && sideCD == sideDA;
        }

        return false;
    }
}
