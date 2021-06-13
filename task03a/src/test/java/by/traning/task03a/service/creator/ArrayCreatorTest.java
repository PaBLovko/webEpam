package by.traning.task03a.service.creator;

import by.traning.task03a.bean.Array;
import by.traning.task03a.service.exception.ServiceException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class ArrayCreatorTest {
    private final ArrayCreator arrayCreator = new ArrayCreator();

    @DataProvider(name = "positiveDataForCreateArray")
    public Object[][] createPositiveDataForCreateFile(){

        return new Object[][]{
                {3, new Array<>(new Integer[3])}
        };
    }

    @DataProvider(name = "negativeDataForCreateArray")
    public Object[][] createNegativeDataForCreateFile(){

        return new Object[][]{
                {0},
                {Integer.MIN_VALUE}
        };
    }

    @Test(description = "Positive script of the creating a array",
            dataProvider = "positiveDataForCreateArray")
    public void creatorTest(int a, Array<Integer> expected) throws ServiceException {
        Array<Integer> actual = arrayCreator.create(a);
        assertEquals(actual, expected);
    }

    @Test(description = "Negative script of the creating a array",
            dataProvider = "negativeDataForCreateArray")
    public void creatorNegativeTest(int a){
        assertThrows(Exception.class,()-> arrayCreator.create(a));
    }

}
