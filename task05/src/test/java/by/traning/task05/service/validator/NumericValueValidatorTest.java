package by.traning.task05.service.validator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class NumericValueValidatorTest {

    private final NumericValueValidator numericValue = new NumericValueValidator();

    @DataProvider(name = "input_a")
    public Object[][] createCorrectData() {
        return
                new Object[][]{
                        {"4400"},
                        {String.valueOf(Integer.MAX_VALUE)},
                        {String.valueOf(Integer.MIN_VALUE)},
                };
    }

    @DataProvider(name = "input_a_negative")
    public Object[][] createNegativeData() {
        return
                new Object[][]{
                        {"4400Done"},
                        {null},
                };
    }

    @Test(description = "Positive script of the check number",
            dataProvider = "input_a")
    public void isNumericTest(String a){
        boolean actual = numericValue.isNumeric(a);
        Assert.assertTrue(actual);
    }

    @Test(description = "Negative script of the check number",
            dataProvider = "input_a_negative")
    public void isNumericNegativeTest(String a){
        boolean actual = numericValue.isNumeric(a);
        Assert.assertFalse(actual);
    }
}
