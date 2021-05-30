package by.traning.task02.service;

import by.traning.task02.bean.Circle;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class CreatorCircleTest {
    private final CreatorCircle creatorCircle  = new CreatorCircle();

    @DataProvider(name = "positiveDataForCreateCircle")
    public Object[][] createPositiveDataForCreateFile(){

        return new Object[][]{
                {2, new Circle(2)},
                {3, new Circle(3)},
                {1, new Circle(1)},
                {Integer.MAX_VALUE, new Circle(Integer.MAX_VALUE)}
        };
    }

    @DataProvider(name = "negativeDataForCreateCircle")
    public Object[][] createNegativeDataForCreateFile(){

        return new Object[][]{
                {0},
                {-1},
                {Integer.MIN_VALUE}
        };
    }

    @Test(description = "Positive script of the creating a circle",
            dataProvider = "positiveDataForCreateCircle")
    public void creatorTest(int area, Circle expected){
        Circle actual = creatorCircle.create(area);
        assertEquals(actual, expected);
    }

    @Test(description = "Negative script of the creating a circle",
            dataProvider = "negativeDataForCreateCircle")
    public void creatorNegativeTest(int area){
        assertThrows(Exception.class,()-> creatorCircle.create(area));
    }
}
