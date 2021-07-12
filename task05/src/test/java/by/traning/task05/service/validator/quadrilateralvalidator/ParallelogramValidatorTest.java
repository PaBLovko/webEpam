package by.traning.task05.service.validator.quadrilateralvalidator;

import by.traning.task05.bean.Quadrilateral;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ParallelogramValidatorTest {

    private final ParallelogramValidator parallelogramValidator = new ParallelogramValidator();

    @DataProvider(name = "input_a")
    public Object[][] createCorrectData() {
        return
                new Object[][]{
                        {new Quadrilateral(new Quadrilateral().new Point(-7,5),
                                new Quadrilateral().new Point(6,5),
                                new Quadrilateral().new Point(4,-2),
                                new Quadrilateral().new Point(-9,-2))},
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

    @Test(description = "Positive script of the check parallelogram",
            dataProvider = "input_a")
    public void isConvexTest(Quadrilateral a){
        boolean actual = parallelogramValidator.isValidParallelogram(a);
        Assert.assertTrue(actual);
    }

    @Test(description = "Negative script of the check parallelogram",
            dataProvider = "input_a_negative")
    public void isConvexNegativeTest(Quadrilateral a){
        boolean actual = parallelogramValidator.isValidParallelogram(a);
        Assert.assertFalse(actual);
    }
}
