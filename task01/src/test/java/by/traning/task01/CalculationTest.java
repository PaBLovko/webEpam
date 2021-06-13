package by.traning.task01;

import by.traning.task01.bean.Cube;
import by.traning.task01.service.Calculation;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class CalculationTest {

    private final Calculation calculation = new Calculation();

    @DataProvider(name = "input_a_b_trigonometricFormula")
    public Object[][] createCorrectData() {
        return
                new Object[][]{
                        {new int[]{0, 0}, 0},
                        {new int[]{20, 30}, 4.758},
                        {new int[]{10, 20}, 0.630},
                        {new int[]{20, 60}, -19.797},
                };
    }

    @DataProvider(name = "positiveDataForSumWithThree")
    public Object[][] createDataForSumWithThree(){
        return new Object[][]{
                {5, 8},
                {-3, 0},
                {-6, -3},
                {-11, -8},
                {-103, -100},
                {7, 10}
        };
    }

    @DataProvider(name = "input_cube_a")
    public Object[][] createCorrectDataForCube() {
        return
                new Object[][]{
                        {new Cube(1, 1, 1, 6), 1},
                        {new Cube(2, 4, 8, 24), 2},
                        {new Cube(3, 9, 27, 54), 3},
                        {new Cube(4, 16, 64, 96), 4},
                };
    }

    @DataProvider(name = "input_a_b_y_triangle")
    public Object[][] createCorrectDataForTriangle() {
        return
                new Object[][]{
                        {new double[]{8, 8, 90}, 32},
                        {new double[]{5, 10, 90}, 25},
                        {new double[]{6, 8, 90}, 24},
                        {new double[]{3, 4, 90}, 6},
                };
    }

    @DataProvider(name = "input_a_byte")
    public Object[][] createDataForByte(){
        return new Object[][]{
                {0, 0},
                {1, 0.001},
                {2, 0.002},
                {3, 0.003}
        };
    }

    @DataProvider(name = "input_a_negativeSumWithThree")
    public Object[][] createNegativeDataForSumWithThree() {
        return new Object[][]{
                {Integer.MAX_VALUE}
        };
    }

    @DataProvider(name = "input_a_negativeCubeParameters")
    public Object[][] createNegativeDataForCubeParameters() {
        return new Object[][]{
                {0},
                {-1}
        };
    }

    @DataProvider(name = "input_a_b_negativeTrigonometricFormula")
    public Object[][] createNegativeDataForTrigonometricFormula() {
        return new Object[][]{
                {-1, 0},
                {0, -1},
                {-1, 1},
                {1, -1},
                {-1, -1}
        };
    }

    @DataProvider(name = "input_a_b_y_negativeTriangle")
    public Object[][] createNegativeDataForTriangle() {
        return new Object[][]{
                {-1, 0, 0},
                {0, -1, 0},
                {-1, 1, 0},
                {1, -1, 0},
                {-1, -1, 0},
                {-1, 0, 1},
                {0, -1, 1},
                {-1, 1, 1},
                {1, -1, 1},
                {-1, -1, 1},
                {-1, 0, -1},
                {0, -1, -1},
                {-1, 1, -1},
                {1, -1, -1},
                {-1, -1, -1},
        };
    }

    @DataProvider(name = "input_a_negativeByte")
    public Object[][] createNegativeDataForByte() {
        return new Object[][]{
                {-1}
        };
    }

    @Test(description = "Negative script of the sum calculation with a three",
            dataProvider = "input_a_negativeSumWithThree" )
    public void foldWithThreeNegativeTest(int a){
        assertThrows(Exception.class,()-> new Calculation().foldWithThree(a));
    }

    @Test(description = "Positive script of the sum calculation with a three",
            dataProvider = "positiveDataForSumWithThree")
    public void foldWithThreeTest(int[] a) {
        int actual = calculation.foldWithThree(a[0]);
        int expected = a[1];
        assertEquals(actual, expected);
    }

    @Test(description = "Negative script of the trigonometric formula",
            dataProvider = "input_a_b_negativeTrigonometricFormula")
    public void trigonometricFormulaNegativeTest(double[] ab) {
        assertThrows(Exception.class,()-> new Calculation().trigonometricFormula(ab[0], ab[1]));
    }

    @Test(description = "Positive script of the trigonometric formula",
            dataProvider = "input_a_b_trigonometricFormula")
    public void trigonometricFormulaTest(int[] ab, double c) {
        double actual = calculation.trigonometricFormula(ab[0], ab[1]);
        assertEquals(actual, c, 0.001);
    }

    @Test(description = "Negative script of the calculates cube",
            dataProvider = "input_a_negativeCubeParameters" )
    public void calculatingCubeParametersNegativeTest(double a){
        assertThrows(Exception.class,()-> new Calculation().calculatingCubeParameters(a));
    }

    @Test(description = "Positive script of the calculates cube",
            dataProvider = "input_cube_a")
    public void calculatingCubeParametersTest(Cube expected, double a) {
        Cube actual = calculation.calculatingCubeParameters(a);
        assertEquals(actual, expected);
    }

    @Test(description = "Negative script of the area of a triangle",
            dataProvider = "input_a_b_y_negativeTriangle")
    public void areaTriangleNegativeTest(double[] aby) {
        assertThrows(Exception.class,()-> new Calculation().calculateAreaTriangle(aby[0],aby[1],aby[2]));
    }

    @Test(description = "Positive script of the area of a triangle",
            dataProvider = "input_a_b_y_triangle")
    public void areaTriangleTest(double[] aby, double expected) {
        double actual = calculation.calculateAreaTriangle(aby[0],aby[1],aby[2]);
        assertEquals(actual, expected);
    }

    @Test(description = "Negative script of the byte-to-kilobyte conversion",
            dataProvider = "input_a_negativeByte")
    public void bytesToKilobytesNegativeTest(double a) {
        assertThrows(Exception.class,()-> new Calculation().bytesToKilobytes(a));
    }

    @Test(description = "Positive script of the byte-to-kilobyte conversion",
            dataProvider = "input_a_byte")
    public void bytesToKilobytesTest(double a, double expected) {
        double actual = calculation.bytesToKilobytes(a);
        assertEquals(actual, expected,0.0001);
    }
}
