package by.traning.task06.service.creator;

import by.traning.task06.bean.Matrix;
import by.traning.task06.service.exception.ServiceException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertThrows;
import static org.testng.Assert.assertEquals;

public class MatrixCreatorTest {
    private final MatrixCreator matrixCreator = new MatrixCreator();

    @DataProvider(name = "positiveDataForCreateMatrix")
    public Object[][] createPositiveDataForCreateFile(){

        return new Object[][]{
                {new int[]{3,3}, new Matrix<>(new Integer[][]{new Integer[3],new Integer[3],new Integer[3]})},
        };
    }

    @DataProvider(name = "negativeDataForCreateMatrix")
    public Object[][] createNegativeDataForCreateFile(){

        return new Object[][]{
                {0, 0},
                {-1, 0},
                {0, -1},
                {-1, -1},
        };
    }

    @Test(description = "Positive script of the creating a matrix",
            dataProvider = "positiveDataForCreateMatrix")
    public void creatorTest(int[] a, Matrix<Integer> expected) throws ServiceException {
        Matrix<Integer> actual = matrixCreator.create(a[0], a[1]);
        assertEquals(actual, expected);
    }

    @Test(description = "Negative script of the creating a matrix",
            dataProvider = "negativeDataForCreateMatrix")
    public void creatorNegativeTest(int[] a){
        assertThrows(Exception.class,()-> matrixCreator.create(a[0], a[1]));
    }
}
