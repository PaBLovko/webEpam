package by.traning.task05.service.validator;

import by.traning.task05.bean.Quadrilateral;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ThreePointsOnSameLineValidatorTest {

    private final ThreePointsOnSameLineValidator threePointsOnSameLineValidator = new ThreePointsOnSameLineValidator();

    @DataProvider(name = "input_a")
    public Object[][] createCorrectData() {
        return
                new Object[][]{
                        {new Quadrilateral().new Point(3,4),
                                new Quadrilateral().new Point(3,5),
                                new Quadrilateral().new Point(3,6)},
                };
    }

    @DataProvider(name = "input_a_negative")
    public Object[][] createNegativeData() {
        return
                new Object[][]{
                        {new Quadrilateral().new Point(4,4),
                                new Quadrilateral().new Point(3,5),
                                new Quadrilateral().new Point(3,6)},
                        {null,null,null},
                };
    }

    @Test(description = "Positive script of the check point on a line",
            dataProvider = "input_a")
    public void isThreePointsOnSameLineTest(Quadrilateral.Point[] a){
        boolean actual = threePointsOnSameLineValidator.isThreePointsOnSameLine(a[0],a[1],a[2]);
        Assert.assertTrue(actual);
    }

    @Test(description = "Negative script of the check point on a line",
            dataProvider = "input_a_negative")
    public void isThreePointsOnSameLineNegativeTest(Quadrilateral.Point[] a){
        boolean actual = threePointsOnSameLineValidator.isThreePointsOnSameLine(a[0],a[1],a[2]);
        Assert.assertFalse(actual);
    }
}
