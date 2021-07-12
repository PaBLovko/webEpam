package by.traning.task05.dao.impl;

import by.traning.task05.bean.Quadrilateral;
import by.traning.task05.dao.exception.DAOException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertThrows;

public class FileQuadrilateralDAOTest {

    private final FileQuadrilateralDAO fileQuadrilateralDAO = new FileQuadrilateralDAO();

    @DataProvider(name = "input_a_b")
    public Object[][] createCorrectDataForRead() {
        return
                new Object[][]{
                        {"file/point.txt",Arrays.asList("1,5 2,3 4,5 6,4",
                                "1,a b,5 5,5 -4,3",
                                "0,8 h,4 1,6 5,5",
                                "10,5 2,-3 4,7 4,4",
                                "1,8 6,5 5,-5 -4,3",
                                "0,8 h,4 1,6 5,5")},
                };
    }

    @DataProvider(name = "input_a_negative")
    public Object[][] createNegativeDataForRead() {
        return
                new Object[][]{
                        {"file/point1.txt"},
                        {null}
                };
    }

    @DataProvider(name = "input_a_b_c")
    public Object[][] createCorrectDataForWrite() {
        Quadrilateral quadrilateralA = new Quadrilateral(
                new Quadrilateral().new Point(1, 5),
                new Quadrilateral().new Point(2, 3),
                new Quadrilateral().new Point(4, 5),
                new Quadrilateral().new Point(6, 4));
        Quadrilateral quadrilateralB = new Quadrilateral(
                new Quadrilateral().new Point(10, 20),
                new Quadrilateral().new Point(20, 20),
                new Quadrilateral().new Point(10, 10),
                new Quadrilateral().new Point(20, 10));
        List<Quadrilateral> quadrilateralList = Arrays.asList(quadrilateralA, quadrilateralB);
        List<String> expected = Arrays.asList("1.5 2.3 4.5 6.4",
                "10.20 20.20 10.10 20.10");
        return
                new Object[][]{
                        {quadrilateralList,"file/point2.txt",expected},
                };
    }

    @DataProvider(name = "input_a_b_negative")
    public Object[][] createNegativeDataForWrite() {
        return
                new Object[][]{
                        {Arrays.asList("1,5 2,3 4,5 6,4",
                                "1,a b,5 5,5 -4,3",
                                "0,8 h,4 1,6 5,5",
                                "10,5 2,-3 4,7 4,4",
                                "1,8 6,5 5,-5 -4,3",
                                "0,8 h,4 1,6 5,5"), null},
                        {null, "file/point1.txt"},
                        {null, null},

                };
    }

    @Test(description = "Positive script of read",
            dataProvider = "input_a_b")
    public void fileQuadrilateralDAOTest(String a, List<String> expected) throws DAOException {
        List<String> actual = fileQuadrilateralDAO.read(a);
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "Negative script of read",
            dataProvider = "input_a_negative")
    public void fileQuadrilateralDAONegativeTest(String a){
        assertThrows(Exception.class,()-> fileQuadrilateralDAO.read(a));
    }

    @Test(description = "Positive script of save",
            dataProvider = "input_a_b_c")
    public void isNumericTest(List<Quadrilateral> a, String b, List<String> expected) throws DAOException {
        fileQuadrilateralDAO.save(a,b);
        List<String> actual = fileQuadrilateralDAO.read(b);
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "Negative script of save",
            dataProvider = "input_a_b_negative")
    public void isNumericNegativeTest(List<Quadrilateral> a, String b){
        assertThrows(Exception.class,()-> fileQuadrilateralDAO.save(a,b));
    }
}
