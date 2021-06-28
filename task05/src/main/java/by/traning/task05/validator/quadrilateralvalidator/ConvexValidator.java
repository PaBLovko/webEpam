package by.traning.task05.validator.quadrilateralvalidator;


import by.traning.task05.bean.Quadrilateral;

public class ConvexValidator {

    /**
     * @param pointA on the plan contains coordinate x and y.
     * @param pointB on the plan contains coordinate x and y.
     * @param pointC on the plan contains coordinate x and y.
     * @param pointD on the plan contains coordinate x and y.
     * @return true if the result of the multiplication is 1, that is mean that the 4 points forming convex figure.
     */
    public boolean isConvex(Quadrilateral.Point pointA, Quadrilateral.Point pointB, Quadrilateral.Point pointC, Quadrilateral.Point pointD) {

       /* Return 1 if the convex hull of pointA, pointB, pointC and pointD is a quadrilateral,
                -1 if a triangle, and 0 if any three of pointA, pointB, pointC and pointD are
        collinear (or if not all points are distinct). */

        int pointCalc = compareThreePoints(pointA, pointB, pointC) * compareThreePoints(pointA, pointB, pointD) *
                compareThreePoints(pointA, pointC, pointD) * compareThreePoints(pointB, pointC, pointD);

        return pointCalc == 1;
    }

    /**
     * @param pointA on the plan contains coordinate x and y.
     * @param pointB on the plan contains coordinate x and y.
     * @param pointC on the plan contains coordinate x and y.
     * @return 1 if pointA-pointB-pointC is a counterclockwise turn, -1 for clockwise,
     * and 0 if the points are collinear (or not all distinct).
     */
    private int compareThreePoints(Quadrilateral.Point pointA, Quadrilateral.Point pointB, Quadrilateral.Point pointC) {

        int x1 = pointA.getCoordinateX();
        int y1 = pointA.getCoordinateY();

        int x2 = pointB.getCoordinateX();
        int y2 = pointB.getCoordinateY();

        int x3 = pointC.getCoordinateX();
        int y3 = pointC.getCoordinateY();

        int disc = (x1 - x3) * (y2 - y3) - (y1 - y3) * (x2 - x3);

        return compare(disc);
    }


    /**
     * Helper method to be used inside the three point comparing
     * @param i number to be compared
     * @return -1 if i < 0, 1 if i > 0, 0 if i == 0
     */
    private int compare(int i) {
        return Integer.compare(i, 0);
    }
}
