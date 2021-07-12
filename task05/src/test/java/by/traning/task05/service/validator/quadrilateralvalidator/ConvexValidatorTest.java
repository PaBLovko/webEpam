package by.traning.task05.service.validator.quadrilateralvalidator;

import by.traning.task05.bean.Quadrilateral;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ConvexValidatorTest {

    private final ConvexValidator convexValidator = new ConvexValidator();

    @DataProvider(name = "input_a")
    public Object[][] createCorrectData() {
        return
                new Object[][]{
                        {new Quadrilateral().new Point(1,15),
                                new Quadrilateral().new Point(10,0),
                                new Quadrilateral().new Point(3,8),
                                new Quadrilateral().new Point(10,5)},
                };
    }

    @DataProvider(name = "input_a_negative")
    public Object[][] createNegativeData() {
        return
                new Object[][]{
                        {new Quadrilateral().new Point(3,4),
                                new Quadrilateral().new Point(3,5),
                                new Quadrilateral().new Point(3,6),
                                new Quadrilateral().new Point(3,6)},
                        {null, null, null, null},
                };
    }

    @Test(description = "Positive script of the check convex square",
            dataProvider = "input_a")
    public void isConvexTest(Quadrilateral.Point[] a){
        boolean actual = convexValidator.isConvex(a[0], a[1], a[2], a[3]);
        Assert.assertTrue(actual);
    }

    @Test(description = "Negative script of the check convex square",
            dataProvider = "input_a_negative")
    public void isConvexNegativeTest(Quadrilateral.Point[] a){
        boolean actual = convexValidator.isConvex(a[0], a[1], a[2], a[3]);
        Assert.assertFalse(actual);
    }
}
