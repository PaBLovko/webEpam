package by.traning.task05.service.validator.quadrilateralvalidator;

import by.traning.task05.bean.Quadrilateral;
import lombok.NonNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Convex square validation class
 */
public class ConvexValidator {
    private static Logger logger = LogManager.getLogger(ConvexValidator.class);

    /**
     * The string literal describing that method worked correctly
     */
    public static final String METHOD_CORRECTLY = "The method worked correctly, result = %s";

    /**
     * a method that checks whether this square is convex
     * @param pointA on the plan contains coordinate x and y.
     * @param pointB on the plan contains coordinate x and y.
     * @param pointC on the plan contains coordinate x and y.
     * @param pointD on the plan contains coordinate x and y.
     * @return true if the result of the multiplication is 1, that is mean that the 4 points forming convex figure.
     */
    public boolean isConvex(Quadrilateral.Point pointA, Quadrilateral.Point pointB,
                            Quadrilateral.Point pointC, Quadrilateral.Point pointD) {
        logger.debug(String.format("The method is invoked, pointA = %s, pointB = %s, pointC = %s, pointD = %s",
                pointA, pointB, pointC, pointD));
        if(pointA == null || pointB == null || pointC == null || pointD == null){
            logger.info("The method worked correctly, result = false");
            return false;
        }
        int pointCalc = compareThreePoints(pointA, pointB, pointC) * compareThreePoints(pointA, pointB, pointD) *
                compareThreePoints(pointA, pointC, pointD) * compareThreePoints(pointB, pointC, pointD);
        boolean result = pointCalc == 1;
        logger.info(String.format(METHOD_CORRECTLY, result));
        return result;
    }

    /**
     * Comparing points
     * @param pointA on the plan contains coordinate x and y.
     * @param pointB on the plan contains coordinate x and y.
     * @param pointC on the plan contains coordinate x and y.
     * @return 1 if pointA-pointB-pointC is a counterclockwise turn, -1 for clockwise,
     * and 0 if the points are collinear (or not all distinct).
     */
    private int compareThreePoints(@NonNull Quadrilateral.Point pointA, @NonNull Quadrilateral.Point pointB,
                                   @NonNull Quadrilateral.Point pointC) {
        logger.debug(String.format("The method is invoked, pointA = %s, pointB = %s, pointC = %s",
                pointA, pointB, pointC));
        int x1 = pointA.getCoordinateX();
        int y1 = pointA.getCoordinateY();
        int x2 = pointB.getCoordinateX();
        int y2 = pointB.getCoordinateY();
        int x3 = pointC.getCoordinateX();
        int y3 = pointC.getCoordinateY();
        int disc = (x1 - x3) * (y2 - y3) - (y1 - y3) * (x2 - x3);
        int result = compare(disc);
        logger.info(String.format(METHOD_CORRECTLY, result));
        return result;
    }

    /**
     * Helper method to be used inside the three point comparing
     * @param i number to be compared
     * @return -1 if i < 0, 1 if i > 0, 0 if i == 0
     */
    private int compare(int i) {
        logger.debug(String.format("The method is invoked, i = %s", i));
        int result = Integer.compare(i, 0);
        logger.info(String.format(METHOD_CORRECTLY, result));
        return result;
    }
}