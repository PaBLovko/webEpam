package by.traning.task05.service.validator.quadrilateralvalidator;

import by.traning.task05.bean.Quadrilateral;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SquareValidatorTest {

    private final SquareValidator squareValidator = new SquareValidator();

    @DataProvider(name = "input_a")
    public Object[][] createCorrectData() {
        return
                new Object[][]{
                        {new Quadrilateral(new Quadrilateral().new Point(20,10),
                                new Quadrilateral().new Point(20,20),
                                new Quadrilateral().new Point(10,20),
                                new Quadrilateral().new Point(10,10))},
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

    @Test(description = "Positive script of the check square",
            dataProvider = "input_a")
    public void isConvexTest(Quadrilateral a){
        boolean actual = squareValidator.isSquare(a);
        Assert.assertTrue(actual);
    }

    @Test(description = "Negative script of the check square",
            dataProvider = "input_a_negative")
    public void isConvexNegativeTest(Quadrilateral a){
        boolean actual = squareValidator.isSquare(a);
        Assert.assertFalse(actual);
    }
}
