package by.traning.task05.service.creator.impl;

import by.traning.task05.bean.Quadrilateral;
import by.traning.task05.service.creator.QuadrilateralCreator;
import by.traning.task05.service.exception.ServiceException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuadrilateralCreatorImplTest {
    private final QuadrilateralCreator quadrilateralCreator = new QuadrilateralCreatorImpl();

    @DataProvider(name = "input_a_b_parse")
    public Object[][] createCorrectData() {
        List<Quadrilateral.Point> pointList = new ArrayList<>();
        Quadrilateral.Point pointA = new Quadrilateral().new Point(1, 5);
        Quadrilateral.Point pointB = new Quadrilateral().new Point(2, 3);
        Quadrilateral.Point pointC = new Quadrilateral().new Point(4, 5);
        Quadrilateral.Point pointD = new Quadrilateral().new Point(6, 4);

        pointList.add(new Quadrilateral().new Point(1, 5));
        pointList.add(new Quadrilateral().new Point(2, 3));
        pointList.add(new Quadrilateral().new Point(4, 5));
        pointList.add(new Quadrilateral().new Point(6, 4));

        Quadrilateral expected = new Quadrilateral(pointA, pointB, pointC, pointD);
        return
                new Object[][]{
                        {pointList, Collections.singletonList(expected)},
                };
    }

    @Test(description = "Positive script of the check distance",
            dataProvider = "input_a_b_parse")
    public void distanceTest(List<Quadrilateral.Point> a, List<Quadrilateral> expected) throws ServiceException {
        List<Quadrilateral> actual = quadrilateralCreator.create(a);
        Assert.assertEquals(actual, expected);
    }
}
