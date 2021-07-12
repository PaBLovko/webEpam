package by.traning.task05.service.action;

import by.traning.task05.bean.Quadrilateral;
import lombok.NonNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The class that helps in calculating additional parameters of a square
 */
public class CalculatorHelper {
    private static Logger logger = LogManager.getLogger(CalculatorHelper.class);

    /**
     * string class literal for logging the correct operation of the method
     */
    public static final String METHOD_CORRECTLY = "The method worked correctly, result = %s";

    /**
     * To return the distance between 2 different points.
     * @param pointA that representing a coordinate A
     * @param pointB that representing a coordinate B
     * @return the distance between 2 points A and B
     */
    public double distance(@NonNull Quadrilateral.Point pointA, @NonNull Quadrilateral.Point pointB){
        logger.debug(String.format("The method is invoked, pointA = %s, pointB = %s", pointA, pointB));
        int x1 = pointA.getCoordinateX();
        int y1 = pointA.getCoordinateY();
        int x2 = pointB.getCoordinateX();
        int y2 = pointB.getCoordinateY();
        double result = Math.sqrt( (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
        logger.info(String.format(METHOD_CORRECTLY, result));
        return result;
    }

    /**
     * To return the slope between 2 different points.
     * @param pointA that representing a coordinate A
     * @param pointB that representing a coordinate B
     * @return the slope of  betwwen t2 Points A and B
     */
    public double slope(@NonNull Quadrilateral.Point pointA, @NonNull Quadrilateral.Point pointB){
        logger.debug(String.format("The method is invoked, pointA = %s, pointB = %s", pointA, pointB));
        double x1 = pointA.getCoordinateX();
        double y1 = pointA.getCoordinateY();
        double x2 = pointB.getCoordinateX();
        double y2 = pointB.getCoordinateY();
        double result = (y2 - y1) / (x2 - x1);
        logger.info(String.format(METHOD_CORRECTLY, result));
        return result;
    }

    /**
     * Using the formula to get the triangle perimeter then divide it by 2
     * subtracting each side from the half perimeter and store it in a variable,
     * using square root for the multiplication of half perimeter and the result of subtracting each side from
     * the half perimeter.
     * @param sideA length of side A
     * @param sideB length of side B
     * @param sideC length of side B
     * @return area of a triangle depending on the side length.
     */
    public double triangleArea(double sideA, double sideB, double sideC){
        logger.debug(String.format("The method is invoked, sideA = %s, sideB = %s, sideC = %s", sideA, sideB, sideC));
        double halfPerimeter = (sideA + sideB + sideC) / 2;
        double s1 = halfPerimeter - sideA;
        double s2 = halfPerimeter - sideB;
        double s3 = halfPerimeter - sideC;
        double result = Math.sqrt(halfPerimeter * s1 * s2 * s3);
        logger.info(String.format(METHOD_CORRECTLY, result));
        return result;
    }
}
