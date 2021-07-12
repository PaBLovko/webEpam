package by.traning.task05.service.action.impl;

import by.traning.task05.bean.Quadrilateral;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ParallelogramCalculatorActionTest {

    private final ParallelogramCalculatorAction parallelogram = new ParallelogramCalculatorAction();
    private static final double DELTA = 0.01;

    @DataProvider(name = "input_a_resultingFigureName")
    public Object[][] createCorrectDataForResultingFigureName() {
        return
                new Object[][]{
                        {"Parallelogram"},
                };
    }

    @DataProvider(name = "input_a_b_areaCalculator")
    public Object[][] createCorrectDataForAreaCalculator() {
        return
                new Object[][]{
                        {new Quadrilateral(
                                new Quadrilateral().new Point(-7,5),
                                new Quadrilateral().new Point(6,5),
                                new Quadrilateral().new Point(4,-2),
                                new Quadrilateral().new Point(-9,-2)),
                                90.99},
                };
    }

    @DataProvider(name = "input_a_b_perimeterCalculator")
    public Object[][] createCorrectDataForPerimeterCalculator() {
        return
                new Object[][]{
                        {new Quadrilateral(
                                new Quadrilateral().new Point(-7,5),
                                new Quadrilateral().new Point(6,5),
                                new Quadrilateral().new Point(4,-2),
                                new Quadrilateral().new Point(-9,-2)),
                                40.56},
                };
    }

    @Test(description = "Positive script of the resulting figure name",
            dataProvider = "input_a_resultingFigureName")
    public void resultingFigureNameTest(String expected){
        String actual = parallelogram.resultingFigureName();
        Assert.assertEquals(actual,expected);
    }

    @Test(description = "Positive script of the area calculator",
            dataProvider = "input_a_b_areaCalculator")
    public void areaCalculatorTest(Quadrilateral a, double expected){
        double actual = parallelogram.areaCalculator(a);
        Assert.assertEquals(actual,expected,DELTA);
    }


    @Test(description = "Positive script of the perimeter calculator",
            dataProvider = "input_a_b_perimeterCalculator")
    public void perimeterCalculatorTest(Quadrilateral a, double expected){
        double actual = parallelogram.perimeterCalculator(a);
        Assert.assertEquals(actual,expected,DELTA);
    }
}
