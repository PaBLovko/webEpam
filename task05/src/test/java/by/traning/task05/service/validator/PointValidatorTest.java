package by.traning.task05.service.validator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PointValidatorTest {

    private final PointValidator pointValidator = new PointValidator();

    @DataProvider(name = "input_a")
    public Object[][] createCorrectData() {
        return
                new Object[][]{
                        {Integer.MAX_VALUE+".2","4.5", "0.0","1."+Integer.MIN_VALUE},
                        {"1.5", "2.3", "4.5", "6.4"},
                };
    }

    @DataProvider(name = "input_a_negative")
    public Object[][] createNegativeData() {
        return
                new Object[][]{
                        {"1.5", "2.3", "4.5", "6.F"},
                        {null},
                };
    }

    @Test(description = "Positive script of the check point",
            dataProvider = "input_a")
    public void isValidPointTest(String[] a){
        boolean actual = pointValidator.isValidPoint(a);
        Assert.assertTrue(actual);
    }

    @Test(description = "Negative script of the check point",
            dataProvider = "input_a_negative")
    public void isValidPointNegativeTest(String[] a){
        boolean actual = pointValidator.isValidPoint(a);
        Assert.assertFalse(actual);
    }
}
