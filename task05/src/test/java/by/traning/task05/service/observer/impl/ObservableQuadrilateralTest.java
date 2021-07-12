package by.traning.task05.service.observer.impl;

import by.traning.task05.bean.Quadrilateral;
import by.traning.task05.bean.QuadrilateralParameter;
import by.traning.task05.service.action.impl.SquareCalculatorAction;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ObservableQuadrilateralTest {
//
//    private final ObservableQuadrilateral observableQuadrilateral = new ObservableQuadrilateral();
//
//    @DataProvider(name = "input_a")
//    public Object[][] createCorrectData() {
//        return
//                new Object[][]{
//                        {"4400"},
//                        {String.valueOf(Integer.MAX_VALUE)},
//                        {String.valueOf(Integer.MIN_VALUE)},
//                };
//    }
//
//    @DataProvider(name = "input_a_negative")
//    public Object[][] createNegativeData() {
//        return
//                new Object[][]{
//                        {"4400Done"},
//                        {null},
//                };
//    }
//
//    @Test(description = "Positive script of the check number",
//            dataProvider = "input_a")
//    public void isNumericTest(String a){
//        boolean actual = observableQuadrilateral.isNumeric(a);
//        Assert.assertTrue(actual);
//    }
//
//    @Test(description = "Negative script of the check number",
//            dataProvider = "input_a_negative")
//    public void isNumericNegativeTest(String a){
//        boolean actual = observableQuadrilateral.isNumeric(a);
//        Assert.assertFalse(actual);
//    }


    private ObservableQuadrilateral quadrilateral;

    @BeforeClass(description = "Initial creation of the object")
    public void init() {
        quadrilateral = new ObservableQuadrilateral(
                new Quadrilateral().new Point(10, 20),
                new Quadrilateral().new Point(20, 20),
                new Quadrilateral().new Point(10, 10),
                new Quadrilateral().new Point(20, 10));
        quadrilateral.setQuadrilateralId(1);

        ObserverQuadrilateral.getInstance().setCalculator(new SquareCalculatorAction());
        ObserverQuadrilateral.getInstance().update(quadrilateral);
    }

    @Test(description = "Test will fail if the notify works and the." +
            "comment quadrilateral.addObserver(QuadrilateralObserver.getInstance()); to check the results")
    public void returnFalseIfTheUpdateNotifyWorks(){
        quadrilateral.addObserver(ObserverQuadrilateral.getInstance());
        QuadrilateralParameter parameter = ObserverQuadrilateral.getInstance().getQuadrilateralParameterByItemId(
                quadrilateral.getQuadrilateralId());

        quadrilateral.setPointA(new Quadrilateral().new Point(100, 200));
        quadrilateral.setPointB(new Quadrilateral().new Point(200, 200));
        quadrilateral.setPointC(new Quadrilateral().new Point(100, 100));
        quadrilateral.setPointD(new Quadrilateral().new Point(200, 100));

        QuadrilateralParameter parameter1 = ObserverQuadrilateral.getInstance().getQuadrilateralParameterByItemId(
                quadrilateral.getQuadrilateralId());

        Assert.assertEquals(parameter, parameter1);
    }
}
