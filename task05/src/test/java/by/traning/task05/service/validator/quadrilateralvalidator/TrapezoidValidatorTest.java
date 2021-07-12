package by.traning.task05.service.validator.quadrilateralvalidator;

import by.traning.task05.bean.Quadrilateral;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TrapezoidValidatorTest {

    private final TrapezoidValidator trapezoidValidator = new TrapezoidValidator();

    @DataProvider(name = "input_a")
    public Object[][] createCorrectData() {
        return
                new Object[][]{
                        {new Quadrilateral(new Quadrilateral().new Point(-3,-3),
                                new Quadrilateral().new Point(5,1),
                                new Quadrilateral().new Point(10,-2),
                                new Quadrilateral().new Point(-4,-9))},
                };
    }

    @DataProvider(name = "input_a_negative")
    public Object[][] createNegativeData() {
        return
                new Object[][]{
                        {new Quadrilateral(new Quadrilateral().new Point(3,4),
                                new Quadrilateral().new Point(3,5),
                                new Quadrilateral().new Point(3,6),
                                new Quadrilateral().new Point(3,6))},
                        {null},
                };
    }

    @Test(description = "Positive script of the check trapezoid",
            dataProvider = "input_a")
    public void isConvexTest(Quadrilateral a){
        boolean actual = trapezoidValidator.isTrapezoid(a);
        Assert.assertTrue(actual);
    }

    @Test(description = "Negative script of the check trapezoid",
            dataProvider = "input_a_negative")
    public void isConvexNegativeTest(Quadrilateral a){
        boolean actual = trapezoidValidator.isTrapezoid(a);
        Assert.assertFalse(actual);
    }
}
