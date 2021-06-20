package by.traning.task04.service.creator;

import by.traning.task04.bean.Engine;
import by.traning.task04.bean.Wheel;
import by.traning.task04.service.exception.ServiceException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class EngineCreatorTest {
    private final EngineCreator engineCreator = new EngineCreator();

    @DataProvider(name = "positiveData")
    public Object[][] createPositiveDataForCreateFile(){

        return new Object[][]{
                {new Object[]{100, 2.5}, new Engine(100,2.5)}
        };
    }

    @DataProvider(name = "negativeData")
    public Object[][] createNegativeDataForCreateFile(){

        return new Object[][]{
                {0},
                {Integer.MIN_VALUE}
        };
    }

    @Test(description = "Positive script of the creating a engine",
            dataProvider = "positiveData")
    public void creatorTest(Object[] a, Engine expected) throws ServiceException {
        Engine actual = engineCreator.create((Integer) a[0], (Double) a[1]);
        assertEquals(actual, expected);
    }

    @Test(description = "Negative script of the creating a engine",
            dataProvider = "negativeData")
    public void creatorNegativeTest(Object[] a){
        assertThrows(Exception.class,()-> engineCreator.create((Integer) a[0], (Double) a[1]));
    }
}
