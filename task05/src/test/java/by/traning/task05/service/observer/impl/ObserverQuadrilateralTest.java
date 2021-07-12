package by.traning.task05.service.observer.impl;

import by.traning.task05.bean.Quadrilateral;
import by.traning.task05.bean.QuadrilateralParameter;
import by.traning.task05.service.action.impl.SquareCalculatorAction;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ObserverQuadrilateralTest {

    private ObservableQuadrilateral quadrilateral;

    @BeforeClass(description = "Initial creation of the object")
    public void init() {
        quadrilateral = new ObservableQuadrilateral(
                new Quadrilateral().new Point(10, 10),
                new Quadrilateral().new Point(20, 10),
                new Quadrilateral().new Point(20, 20),
                new Quadrilateral().new Point(10, 20));
        quadrilateral.setQuadrilateralId(1);

        ObserverQuadrilateral.getInstance().setCalculator(new SquareCalculatorAction());
        ObserverQuadrilateral.getInstance().update(quadrilateral);

    }

    @DataProvider(name = "input_a_update")
    public Object[][] createDataForUpdate() {
        return
                new Object[][]{
                        {1},
                };
    }

    @DataProvider(name = "input_a_get")
    public Object[][] createDataForGetQuadrilateralParameter() {
        return
                new Object[][]{
                        {new QuadrilateralParameter(40.0, 100.0)},
                };
    }

    @Test(description = "Initial creation of the object",
            dataProvider = "input_a_update")
    public void updateTest(int expected) {
        int actual = ObserverQuadrilateral.getInstance().getQuadrilateralParameter().size();
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "Initial creation of the object",
            dataProvider = "input_a_get")
    public void getQuadrilateralParameterByItemIdTest(QuadrilateralParameter expected) {
        QuadrilateralParameter actual = ObserverQuadrilateral.getInstance().getQuadrilateralParameterByItemId(
                quadrilateral.getQuadrilateralId());
        Assert.assertEquals(actual, expected);
    }
}
