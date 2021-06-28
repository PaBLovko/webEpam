package by.traning.task05.validator.quadrilateralvalidator;

import by.traning.task05.bean.Quadrilateral;
import by.traning.task05.service.CalculatorHelper;

public class ParallelogramValidator {
    private CalculatorHelper calculatorHelper = new CalculatorHelper();

    /**
     * By checking the distance between the points, Parallelogram has to have each 2 sides in parallel and the same length,
     * also the diagonals length has to not be equal.
     * @param quadrilateral to check it if it is a Parallelogram or not.
     * @return true if it is valid Parallelogram, false iif it is not valid Parallelogram
     */
    public boolean isValidParallelogram(Quadrilateral quadrilateral) {
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
        if (sideAB == sideCD && sideBC == sideDA) {
            return diagonalAC != diagonalBD;
        }
        return false;
    }
}
