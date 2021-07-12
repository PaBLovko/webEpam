package by.traning.task05.service.creator.impl;

import by.traning.task05.bean.Quadrilateral;
import by.traning.task05.service.creator.PointCreator;
import by.traning.task05.service.exception.ServiceException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PointCreatorImplTest {
    private final PointCreator pointCreator = new PointCreatorImpl();

    @DataProvider(name = "input_a_b_parse")
    public Object[][] createCorrectData() {
        List<String> stringList = Arrays.asList("1.5 2.3 4.5 6.4", "1.a b.5 5.5 -4.3");
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
    public void distanceTest(List<String> a, List<Quadrilateral.Point> expected) throws ServiceException {
        List<Quadrilateral.Point> actual = pointCreator.create(a);
        Assert.assertEquals(actual,expected);
    }
}
