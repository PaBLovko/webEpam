package by.traning.task05.service;

import by.traning.task05.bean.Quadrilateral;

public class CalculatorHelper {

    /**
     * To return the distance between 2 different points.
     * @param pointA that representing a coordinate A
     * @param pointB that representing a coordinate B
     * @return the distance between 2 points A and B
     */
    public double distance(Quadrilateral.Point pointA, Quadrilateral.Point pointB){
        if(pointA == null || pointB == null){
            throw new IllegalArgumentException(" not allow for PointA or PointB to be null");
        }

        int x1 = pointA.getCoordinateX();
        int y1 = pointA.getCoordinateY();

        int x2 = pointB.getCoordinateX();
        int y2 = pointB.getCoordinateY();


        return  Math.sqrt( (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

    /**
     * To return the slope between 2 different points.
     * @param pointA that representing a coordinate A
     * @param pointB that representing a coordinate B
     * @return the slope of  betwwen t2 Points A and B
     */
    public double slope(Quadrilateral.Point pointA, Quadrilateral.Point pointB){
        if(pointA == null || pointB == null){
            throw new IllegalArgumentException(" not allow for PointA or PointB to be null");
        }
        double x1 = pointA.getCoordinateX();
        double y1 = pointA.getCoordinateY();

        double x2 = pointB.getCoordinateX();
        double y2 = pointB.getCoordinateY();

        return (y2 - y1) / (x2 - x1);
    }

    /**
     * Using the formula to get the triangle perimeter then divide it by 2
     * subtracting each side from the half perimeter and store it in a variable,
     * using square root for the multiplication of half perimeter and the result of subtracting each side from the half perimeter.
     * @param sideA length of side A
     * @param sideB length of side B
     * @param sideC length of side B
     * @return area of a triangle depending on the side length.
     */
    public double triangleArea(double sideA, double sideB, double sideC){

        double halfPerimeter = (sideA + sideB + sideC) / 2;
        double s1 = halfPerimeter - sideA;
        double s2 = halfPerimeter - sideB;
        double s3 = halfPerimeter - sideC;

        return Math.sqrt(halfPerimeter * s1 * s2 * s3);
    }
}
