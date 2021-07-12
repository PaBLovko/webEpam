package by.traning.task05.service.action;

import by.traning.task05.bean.Quadrilateral;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CalculatorHelperTest {

    private final CalculatorHelper calculatorHelper = new CalculatorHelper();
    private static final double DELTA = 0.01;

    @DataProvider(name = "input_a_b_distance")
    public Object[][] createCorrectDataForDistance() {
        return
                new Object[][]{
                        {new Quadrilateral.Point[] {
                                new Quadrilateral().new Point(3,5),
                                new Quadrilateral().new Point(-1,1)},
                                5.65},
                };
    }

    @DataProvider(name = "input_a_b_slope")
    public Object[][] createCorrectDataForSlope() {
        return
                new Object[][]{
                        {new Quadrilateral.Point[] {
                                new Quadrilateral().new Point(3,5),
                                new Quadrilateral().new Point(-1,1)},
                                1},
                };
    }

    @DataProvider(name = "input_a_b_triangleArea")
    public Object[][] createCorrectDataForTriangleArea() {
        Quadrilateral.Point pointA = new Quadrilateral().new Point(31, 15);
        Quadrilateral.Point pointB = new Quadrilateral().new Point(20, 41);
        Quadrilateral.Point pointC = new Quadrilateral().new Point(58, 29);
        double sideAB = calculatorHelper.distance(pointA, pointB);
        double sideBC = calculatorHelper.distance(pointB, pointC);
        double sideCA = calculatorHelper.distance(pointC, pointA);
        return
                new Object[][]{
                        {new double[] {sideAB, sideBC, sideCA}, 428},
                };
    }

    @Test(description = "Positive script of the check distance",
            dataProvider = "input_a_b_distance")
    public void distanceTest(Quadrilateral.Point[] a, double expected){
        double actual = calculatorHelper.distance(a[0], a[1]);
        Assert.assertEquals(actual,expected,DELTA);
    }

    @Test(description = "Positive script of the check slope",
            dataProvider = "input_a_b_slope")
    public void slopeTest(Quadrilateral.Point[] a, double expected){
        double actual = calculatorHelper.slope(a[0], a[1]);
        Assert.assertEquals(actual,expected,DELTA);
    }

    @Test(description = "Positive script of the check triangle area",
            dataProvider = "input_a_b_triangleArea")
    public void triangleAreaTest(double[] a, double expected){
        double actual = calculatorHelper.triangleArea(a[0], a[1], a[2]);
        Assert.assertEquals(actual,expected,DELTA);
    }
}
