package by.traning.task04.service.creator;

import by.traning.task04.bean.Wheel;
import by.traning.task04.service.exception.ServiceException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class WheelCreatorTest {
    private final WheelCreator wheelCreator = new WheelCreator();

    @DataProvider(name = "positiveData")
    public Object[][] createPositiveDataForCreateFile(){

        return new Object[][]{
                {15, new Wheel(15)}
        };
    }

    @DataProvider(name = "negativeData")
    public Object[][] createNegativeDataForCreateFile(){

        return new Object[][]{
                {0},
                {Integer.MIN_VALUE}
        };
    }

    @Test(description = "Positive script of the creating a array",
            dataProvider = "positiveData")
    public void creatorTest(int a, Wheel expected) throws ServiceException {
        Wheel actual = wheelCreator.create(a);
        assertEquals(actual, expected);
    }

    @Test(description = "Negative script of the creating a array",
            dataProvider = "negativeData")
    public void creatorNegativeTest(int a){
        assertThrows(Exception.class,()-> wheelCreator.create(a));
    }

}
