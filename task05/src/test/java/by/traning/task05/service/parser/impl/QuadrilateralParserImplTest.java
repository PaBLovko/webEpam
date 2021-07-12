package by.traning.task05.service.parser.impl;

import by.traning.task05.bean.Quadrilateral;
import by.traning.task05.service.exception.ServiceException;
import by.traning.task05.service.parser.QuadrilateralParser;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class QuadrilateralParserImplTest {
    private final QuadrilateralParser quadrilateralParser = new QuadrilateralParserImpl();

    @DataProvider(name = "input_a_b_parse")
    public Object[][] createCorrectData() {
        List<Quadrilateral.Point> pointList = new ArrayList<>();

        pointList.add(new Quadrilateral().new Point(1, 5));
        pointList.add(new Quadrilateral().new Point(2, 3));
        pointList.add(new Quadrilateral().new Point(4, 5));
        pointList.add(new Quadrilateral().new Point(6, 4));

        List<List<Quadrilateral.Point>> expected = new ArrayList<>();
        List<Quadrilateral.Point> list = new ArrayList<>();
        expected.add(list);
        Quadrilateral.Point pointA = new Quadrilateral().new Point(1, 5);
        Quadrilateral.Point pointB = new Quadrilateral().new Point(2, 3);
        Quadrilateral.Point pointC = new Quadrilateral().new Point(4, 5);
        Quadrilateral.Point pointD = new Quadrilateral().new Point(6, 4);
        list.add(pointA);
        list.add(pointB);
        list.add(pointC);
        list.add(pointD);

        return
                new Object[][]{
                        {pointList, expected},
                };
    }

    @Test(description = "Positive script of the check distance",
            dataProvider = "input_a_b_parse")
    public void distanceTest(List<Quadrilateral.Point> a, List<List<Quadrilateral.Point>> expected) throws ServiceException {
        List<List<Quadrilateral.Point>> actual = quadrilateralParser.parse(a);
        Assert.assertEquals(actual, expected);
    }
}