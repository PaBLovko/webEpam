package by.traning.task05.validator.quadrilateralvalidator;

import by.traning.task05.bean.Quadrilateral;
import by.traning.task05.validator.ThreePointsOnSameLineValidator;

public class QuadrilateralValidator {

    private ThreePointsOnSameLineValidator pointsValidator = new ThreePointsOnSameLineValidator();

    /**
     *
     * @param pointA coordinate 1
     * @param pointB coordinate 2
     * @param pointC coordinate 3
     * @param pointD coordinate 4
     * @return true is one of the combination of 3 points is on the same line, false if they are on different location
     */
    public boolean isValidQuadrilateral(Quadrilateral.Point pointA, Quadrilateral.Point pointB,
                                        Quadrilateral.Point pointC, Quadrilateral.Point pointD) {
        if(pointA == null || pointB == null || pointC == null || pointD == null){
            return false;
        }
        return  pointsValidator.isThreePointsOnSameLine(pointA, pointB, pointC) ||
                pointsValidator.isThreePointsOnSameLine(pointB, pointC, pointD) ||
                pointsValidator.isThreePointsOnSameLine(pointC, pointD, pointA) ||
                pointsValidator.isThreePointsOnSameLine(pointD, pointA, pointC);
    }
}
