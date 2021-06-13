package by.traning.task03a.service.impl;

import by.traning.task03a.bean.Matrix;
import by.traning.task03a.service.action.MatrixServiceAction;
import by.traning.task03a.service.exception.ServiceException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class MatrixServiceActionTest {
    private final MatrixServiceAction matrixServiceAction = new MatrixServiceAction();

    @DataProvider(name = "input_a_b_multiply")
    public Object[][] createCorrectDataForMultiply() {
        return
                new Object[][]{
                        {new Matrix[]{new Matrix<>(new Integer[][]{{5,7,3,17}, {7,0,1,12}, {8,1,2,3}, {1,2,3,4}}),
                                new Matrix<>(new Integer[][]{{1,2,3,4}, {1,2,3,4}, {5,6,7,8}, {9,10,11,12}})},
                                new Matrix<>(new Integer[][]{{180,212,244,276}, {120,140,160,180},
                                        {46,60,74,88}, {54,64,74,84}})}
                };
    }

    @DataProvider(name = "input_a_b_subtraction")
    public Object[][] createCorrectDataForSubtraction() {
        return
                new Object[][]{
                        {new Matrix[]{new Matrix<>(new Integer[][]{{5,7,3,17}, {7,0,1,12}, {8,1,2,3}, {1,2,3,4}}),
                                new Matrix<>(new Integer[][]{{1,2,3,4}, {1,2,3,4}, {5,6,7,8}, {9,10,11,12}})},
                                new Matrix<>(new Integer[][]{{4,5,0,13}, {6,-2,-2,8}, {3,-5,-5,-5}, {-8,-8,-8,-8}})}
                };
    }

    @DataProvider(name = "input_a_b_summation")
    public Object[][] createCorrectDataForSummation() {
        return
                new Object[][]{
                        {new Matrix[]{new Matrix<>(new Integer[][]{{5,7,3,17}, {7,0,1,12}, {8,1,2,3}, {1,2,3,4}}),
                                new Matrix<>(new Integer[][]{{1,2,3,4}, {1,2,3,4}, {5,6,7,8}, {9,10,11,12}})},
                                new Matrix<>(new Integer[][]{{6,9,6,21}, {8,2,4,16}, {13,7,9,11}, {10,12,14,16}})}
                };
    }

    @DataProvider(name = "input_a_b_negative")
    public Object[][] createNegativeDataForSummation() {
        return
                new Object[][]{
                        {new Matrix[]{new Matrix<>(new Integer[][]{{5,7,3,17}, {7,0,1,12}, {8,1,2,3}, {1,2,3,4}}),
                                new Matrix<>(new Integer[][]{{}, {}, {}, {}})}},
                        {new Matrix[]{new Matrix<>(new Integer[][]{{}, {}, {}, {}}),
                                new Matrix<>(new Integer[][]{{}, {}, {}, {}})}},
                        {new Matrix[]{new Matrix<>(new Integer[][]{{}, {}, {}, {}}),
                                new Matrix<>(new Integer[][]{{5,7,3,17}, {7,0,1,12}, {8,1,2,3}, {1,2,3,4}})}},
                        {new Matrix[]{null,null}}
                };
    }

    @Test(description = "Positive script of the multiply",
            dataProvider = "input_a_b_multiply")
    public void multiplyTest(Matrix<Integer>[] a, Matrix<Integer> expected) throws ServiceException {
        Matrix<Integer> actual = matrixServiceAction.matrixMultiply(a[0], a[1]);
        assertEquals(actual, expected);
    }

    @Test(description = "Positive script of the subtraction",
            dataProvider = "input_a_b_subtraction")
    public void subtractionTest(Matrix<Integer>[] a, Matrix<Integer> expected) throws ServiceException {
        Matrix<Integer> actual = matrixServiceAction.matrixSubtraction(a[0], a[1]);
        assertEquals(actual, expected);
    }

    @Test(description = "Positive script of the summation",
            dataProvider = "input_a_b_summation")
    public void summationTest(Matrix<Integer>[] a, Matrix<Integer> expected) throws ServiceException {
        Matrix<Integer> actual = matrixServiceAction.matrixSummation(a[0], a[1]);
        assertEquals(actual, expected);
    }

    @Test(description = "Negative script of the summation",
            dataProvider = "input_a_b_negative")
    public void summationNegativeTest(Matrix<Integer>[] a) {
        assertThrows(Exception.class,()-> matrixServiceAction.matrixSummation(a[0], a[1]));
    }

    @Test(description = "Negative script of the subtraction",
            dataProvider = "input_a_b_negative")
    public void subtractionNegativeTest(Matrix<Integer>[] a) {
        assertThrows(Exception.class,()-> matrixServiceAction.matrixSubtraction(a[0], a[1]));
    }

    @Test(description = "Negative script of the multiply",
            dataProvider = "input_a_b_negative")
    public void multiplyNegativeTest(Matrix<Integer>[] a) {
        assertThrows(Exception.class,()-> matrixServiceAction.matrixMultiply(a[0], a[1]));
    }
}
