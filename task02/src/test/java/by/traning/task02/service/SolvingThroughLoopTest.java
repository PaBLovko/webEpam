package by.traning.task02.service;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigInteger;

import static org.testng.Assert.assertEquals;

public class SolvingThroughLoopTest {
    private final SolvingThroughLoop solvingThroughLoop = new SolvingThroughLoop();

    @DataProvider(name = "gettingNumbersInRange")
    public Object[][] createCorrectDataForGettingNumbersInRange() {
        return
                new Object[][]{
                        {new int[]{5,4,3,2,1}}
                };
    }

    @DataProvider(name = "gettingGivenProduct")
    public Object[][] createCorrectDataForGettingGivenProduct() {
        String result = "62198123175637948999999750170003022636103004290840213579558541607678056770122962707119" +
                "474875527477186755048113086733272839860891567821760620894433414353290315741601505323199208565384" +
                "62751596161278122728703497952087581686756098212923839681896203473592988213369645672689362820030" +
                "573718559448485050498576045694551050335876661780521861255985901018148604602336443893004324569600" +
                "09702905584857393518877079243717213370983146491503406155228997954249347719005783769360467152555" +
                "665800216223615428450836858053400856713359967484823371026535062161096211713506798207812398746" +
                "913836648755132232834523663952442186966337759051603462287553956523494664588575257708095078400" +
                "000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        return
                new Object[][]{
                        {new BigInteger(result)}
                };
    }

    @DataProvider(name = "input_a_b_getSumOfRow")
    public Object[][] createCorrectDataForGetSumOfRow() {
        return
                new Object[][]{
                        {0.001, new double[]{1, 1}, 2},
                        {0.001, new double[]{20, 30}, -0.083},
                        {0.001, new double[]{10, 20}, -0.150},
                        {0.001, new double[]{20, 60}, -0.066},
                };
    }

    @DataProvider(name = "getRatio")
    public Object[][] createCorrectDataForGetRatio() {
        return
                new Object[][]{
                        {95}
                };
    }

    @DataProvider(name = "getFourDigitNumbers")
    public Object[][] createCorrectDataForgetFourDigitNumbers() {
        return
                new Object[][]{
                        {519}
                };
    }

    @Test(description = "Positive script of the getting numbers in a range from 5 to 1",
            dataProvider = "gettingNumbersInRange")
    public void gettingNumbersInRangeTest(int[] expected) {
        int[] actual = solvingThroughLoop.calculateNumbersInRange();
        assertEquals(actual, expected);
    }

    @Test(description = "Positive script for finding the product of the squares of the first two hundred numbers",
            dataProvider = "gettingGivenProduct")
    public void gettingGivenProductTest(BigInteger expected) {
        BigInteger actual = solvingThroughLoop.calculateGivenProduct();
        assertEquals(actual, expected);
    }

    @Test(description = "Positive script for finding the sum of the terms of a series",
            dataProvider = "input_a_b_getSumOfRow")
    public void getSumOfRowTest(double a, double[] b, double expected) {
        double actual = solvingThroughLoop.calculateSumOfRow(a, b);
        assertEquals(actual, expected, 0.001);
    }

    @Test(description = "Positive script of the getting numbers in range ASCII",
            dataProvider = "getRatio")
    public void getRatioTest(int expected) {
        int actual = solvingThroughLoop.calculateRatio().size();
        assertEquals(actual, expected);
    }

    @Test(description = "Positive script for finds four-digit numbers, the sum of the digits of each of which is 15",
            dataProvider = "getFourDigitNumbers")
    public void getFourDigitNumbersTest(int expected) {
        int actual = solvingThroughLoop.calculateFourDigitNumbers().size();
        assertEquals(actual, expected);
    }
}
