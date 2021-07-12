package by.traning.task05.service.repository.impl;

import by.traning.task05.bean.Quadrilateral;
import by.traning.task05.service.exception.ServiceException;
import by.traning.task05.service.parser.PointParser;
import by.traning.task05.service.parser.impl.PointParserImpl;
import by.traning.task05.service.repository.specification.impl.SpecifiedByID;
import by.traning.task05.service.repository.specification.impl.SpecifiedByPoint;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class RepositoryImplTest {

    private static Quadrilateral.Point pointA;
    private static Quadrilateral.Point pointB;
    private static Quadrilateral.Point pointC;
    private static Quadrilateral.Point pointD;

    private static Quadrilateral.Point pointE;
    private static Quadrilateral.Point pointF;
    private static Quadrilateral.Point pointG;
    private static Quadrilateral.Point pointH;

    private static Quadrilateral quadrilateral;
    private static Quadrilateral quadrilateral1;
    private static List<Quadrilateral> quadrilateralList;

    @DataProvider(name = "input_a_twoQuadrilateral")
    public Object[][] createCorrectDataWithTwoQuadrilateral() {
        RepositoryImpl repository = new RepositoryImpl();
        List<Quadrilateral> expected = new ArrayList<>();
        expected.add(quadrilateral1);
        expected.add(quadrilateral1);
        return
                new Object[][]{
                        {repository, expected},
                };
    }

    @DataProvider(name = "input_a_oneQuadrilateral")
    public Object[][] createCorrectDataWithOneQuadrilateral() {
        RepositoryImpl repository = new RepositoryImpl();
        List<Quadrilateral> expected = new ArrayList<>();
        expected.add(quadrilateral1);
        return
                new Object[][]{
                        {repository, expected},
                };
    }

    @DataProvider(name = "input_a_repository")
    public Object[][] createCorrectDataWithListQuadrilateral() {
        RepositoryImpl repository = new RepositoryImpl();
        return
                new Object[][]{
                        {repository},
                };
    }

    @BeforeClass(description = "Initial creation of the object")
    public static void init() {
        pointA = new Quadrilateral().new Point(1, 5);
        pointB = new Quadrilateral().new Point(10, 5);
        pointC = new Quadrilateral().new Point(3, 8);
        pointD = new Quadrilateral().new Point(10, 5);

        pointE = new Quadrilateral().new Point(18, 25);
        pointF = new Quadrilateral().new Point(0, 15);
        pointG = new Quadrilateral().new Point(30, 18);
        pointH = new Quadrilateral().new Point(50, 5);

        quadrilateral = new Quadrilateral(pointA, pointB, pointC, pointD);
        quadrilateral.setQuadrilateralId(1);
        quadrilateral1 = new Quadrilateral(pointE, pointF, pointG, pointH);
        quadrilateral1.setQuadrilateralId(2);

        quadrilateralList = new ArrayList<>();
        quadrilateralList.add(quadrilateral);
        quadrilateralList.add(quadrilateral1);

    }

    @AfterClass(description = "erasing an object")
    public static void afterClass() {
        pointA = null;
        pointB = null;
        pointC = null;
        pointD = null;

        pointE = null;
        pointF = null;
        pointG = null;
        pointH = null;

        quadrilateral = null;
        quadrilateral1 = null;

        quadrilateralList = null;
    }

    @Test(description = "Positive script of the check add by one quadrilateral at a time",
            dataProvider = "input_a_repository")
    public void addByOneQuadrilateralAtATimeTest(RepositoryImpl repository) {
        repository.add(quadrilateral);
        repository.add(quadrilateral1);

        List<Quadrilateral> actual = repository.getQuadrilateralList();
        Assert.assertEquals(actual, quadrilateralList);
    }

    @Test(description = "Positive script of the check add list of quadrilateral",
            dataProvider = "input_a_repository")
    public void addListOfQuadrilateralTest(RepositoryImpl repository) {
        repository.add(quadrilateralList);
        List<Quadrilateral> actual = repository.getQuadrilateralList();
        Assert.assertEquals(actual, quadrilateralList);
    }

    @Test(description = "Positive script of the check remove by quadrilateral",
            dataProvider = "input_a_oneQuadrilateral")
    public void removeByQuadrilateralTest(RepositoryImpl repository, List<Quadrilateral> expected) {
        repository.add(quadrilateral);
        repository.add(quadrilateral1);
        repository.remove(quadrilateral);
        List<Quadrilateral> actual = repository.getQuadrilateralList();
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "Positive script of the check update old quadrilateral by new quadrilateral",
            dataProvider = "input_a_oneQuadrilateral")
    public void removeBySpecificCriteriaTest(RepositoryImpl repository, List<Quadrilateral> expected) {
        repository.add(quadrilateralList);
        repository.remove(new SpecifiedByID(1));
        List<Quadrilateral> actual = repository.getQuadrilateralList();
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "Positive script of the check update old quadrilateral by new quadrilateral",
            dataProvider = "input_a_twoQuadrilateral")
    public void updateOldQuadrilateralByNewQuadrilateralTest(RepositoryImpl repository, List<Quadrilateral> expected) {
        repository.add(quadrilateralList);
        repository.update(quadrilateral, quadrilateral1);
        List<Quadrilateral> actual = repository.getQuadrilateralList();
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "Positive script of the check query",
            dataProvider = "input_a_twoQuadrilateral")
    public void queryTest(RepositoryImpl repository, List<Quadrilateral> expected) {
        Quadrilateral.Point pointTest = new Quadrilateral().new Point(18, 25);
        repository.add(quadrilateralList);
        repository.add(quadrilateralList);
        List<Quadrilateral> actual = repository.query(new SpecifiedByPoint(pointTest));
        Assert.assertEquals(actual, expected);
    }
}
