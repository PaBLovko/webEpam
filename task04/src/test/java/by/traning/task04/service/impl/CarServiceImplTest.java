package by.traning.task04.service.impl;

import by.traning.task04.bean.Wheel;
import by.traning.task04.service.exception.ServiceException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class CarServiceImplTest {
    private final CarServiceImpl carService = new CarServiceImpl();

    @DataProvider(name = "input_a_correct")
    public Object[][] createCorrectData(){
        List<Wheel> wheels = new ArrayList<>();
        Wheel disk = new Wheel(15);
        for (int i = 0; i < 4; i++){
            wheels.add(disk);
        }
        return
                new Object[][]{
                        {"Acura-CL", "Acura-CL"}
                };
    }

    @DataProvider(name = "expected_allCars")
    public Object[][] createCorrectDataForAll(){
        return
                new Object[][]{
                        {new File("src/main/resources/cars.txt").length()}
                };
    }

    @DataProvider(name = "input_a_negative")
    public Object[][] createNegativeData(){
        List<Wheel> wheels = new ArrayList<>();
        Wheel disk = new Wheel(15);
        for (int i = 0; i < 4; i++){
            wheels.add(disk);
        }
        return
                new Object[][]{
                        {"mers"}
                };
    }

    @Test(description = "Positive script of the receive all cars",
            dataProvider = "expected_allCars")
    public void allTest(long expected) throws ServiceException {
        String actual = carService.all();
        assertEquals(actual.length(), expected);
    }

    @Test(description = "Positive script of the name receive",
            dataProvider = "input_a_correct")
    public void nameReceiveTest(String a, String expected) throws ServiceException {
        String actual = carService.nameReceive(a);
        assertEquals(actual, expected);
    }

    @Test(description = "Negative script of the name receive",
            dataProvider = "input_a_negative")
    public void nameReceiveNegativeTest(String a){
        assertThrows(Exception.class,()-> carService.nameReceive(a));
    }
}
