package by.traning.task05.service.parser.impl;

import by.traning.task05.bean.Quadrilateral;
import by.traning.task05.service.exception.ServiceException;
import by.traning.task05.service.parser.PointParser;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;

import java.util.List;

public class PointParserImplTest {
    private final PointParser pointParser = new PointParserImpl();

    @DataProvider(name = "input_a_b_parse")
    public Object[][] createCorrectData() {
        String[] stringList = {"1.5", "2.3", "4.5", "6.4"};
        List<Quadrilateral.Point> expected = new ArrayList<>();
        expected.add(new Quadrilateral().new Point(1, 5));
        expected.add(new Quadrilateral().new Point(2, 3));
        expected.add(new Quadrilateral().new Point(4, 5));
        expected.add(new Quadrilateral().new Point(6, 4));
        return
                new Object[][]{
                        {stringList, expected},
                };
    }

    @Test(description = "Positive script of the check distance",
            dataProvider = "input_a_b_parse")
    public void distanceTest(String[] a, List<Quadrilateral.Point> expected) throws ServiceException {
        List<Quadrilateral.Point> actual = pointParser.parse(a);
        Assert.assertEquals(actual,expected);
    }

}
