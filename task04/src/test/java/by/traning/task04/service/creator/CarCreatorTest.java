package by.traning.task04.service.creator;

import by.traning.task04.bean.Car;
import by.traning.task04.bean.Engine;
import by.traning.task04.bean.Wheel;
import by.traning.task04.service.exception.ServiceException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class CarCreatorTest {
    private final CarCreator carCreator = new CarCreator();

    @DataProvider(name = "positiveData")
    public Object[][] createPositiveData() {
        List<Wheel> wheels = new ArrayList<>();
        Wheel disk = new Wheel(15);
        for (int i = 0; i < 4; i++){
            wheels.add(disk);
        }
        return new Object[][]{
                {new Object[]{"bmw", 100, 2.5, 15, 4, true},
                        new Car("bmw", new Engine(100, 2.5), wheels, true)}
        };
    }

    @DataProvider(name = "negativeData")
    public Object[][] createNegativeData(){

        return new Object[][]{
                {0},
                {Integer.MIN_VALUE}
        };
    }

    @Test(description = "Positive script of the creating a car",
            dataProvider = "positiveData")
    public void creatorTest(Object[] a, Car expected) throws ServiceException {
        Car actual = carCreator.create((String) a[0], (Integer) a[1], (Double) a[2], (Integer) a[3], (Integer) a[4],
                (Boolean) a[5]);
        assertEquals(actual, expected);
    }

    @Test(description = "Negative script of the creating a car",
            dataProvider = "negativeData")
    public void creatorNegativeTest(Object[] a){
        assertThrows(Exception.class,()->
                carCreator.create((String) a[0], (Integer) a[1], (Double) a[2], (Integer) a[3], (Integer) a[4],
                        (Boolean) a[5]));
    }

}
