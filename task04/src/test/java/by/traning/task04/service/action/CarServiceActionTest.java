package by.traning.task04.service.action;


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

public class CarServiceActionTest {
    private final CarServiceAction carServiceAction = new CarServiceAction();

    @DataProvider(name = "input_a_modelName")
    public Object[][] createCorrectDataForModelName(){
        List<Wheel> wheels = new ArrayList<>();
        Wheel disk = new Wheel(15);
        for (int i = 0; i < 4; i++){
            wheels.add(disk);
        }
        return
                new Object[][]{
                        {new Car("bmw", new Engine(100, 2.5), wheels), "bmw"}
                };
    }

    @DataProvider(name = "input_a_refuel")
    public Object[][] createCorrectDataForRefuel(){
        List<Wheel> wheels = new ArrayList<>();
        Wheel disk = new Wheel(15);
        for (int i = 0; i < 4; i++){
            wheels.add(disk);
        }
        return
                new Object[][]{
                        {new Car("bmw", new Engine(100, 2.5), wheels), true}
                };
    }

    @DataProvider(name = "negative_input_a_wheelChange")
    public Object[][] createCorrectDataForWheelChange(){
        List<Wheel> wheels = new ArrayList<>();
        Wheel disk = new Wheel(15);
        for (int i = 0; i < 3; i++){
            wheels.add(disk);
        }
        return
                new Object[][]{
                        {new Car("bmw", new Engine(100, 2.5), wheels)}
                };
    }

    @Test(description = "Positive script of the receive car model name ",
            dataProvider = "input_a_modelName")
    public void modelNameTest(Car a, String expected){
        String actual = carServiceAction.modelName(a);
        assertEquals(actual, expected);
    }

    @Test(description = "Positive script of the refuel",
            dataProvider = "input_a_refuel")
    public void refuelTest(Car a, boolean expected) throws ServiceException {
        boolean actual = carServiceAction.refuel(a);
        assertEquals(actual, expected);
    }

    @Test(description = "Negative script of the change wheels",
            dataProvider = "negative_input_a_wheelChange")
    public void wheelChangeTest(Car a){
        assertThrows(Exception.class,()-> carServiceAction.wheelChange(a));
    }
}
