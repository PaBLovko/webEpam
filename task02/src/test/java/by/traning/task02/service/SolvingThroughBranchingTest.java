package by.traning.task02.service;

import by.traning.task02.bean.Circle;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class SolvingThroughBranchingTest {
    private final SolvingThroughBranching solvingThroughBranching = new SolvingThroughBranching();

    @DataProvider(name = "input_a_b_comparingTwoNumbers")
    public Object[][] createCorrectDataForComparingTwoNumbers() {
        return
                new Object[][]{
                        {new int[]{0, 0}, "no"},
                        {new int[]{20, 30}, "yes"},
                        {new int[]{10, 20}, "yes"},
                        {new int[]{20, 60}, "yes"},
                };
    }

    @DataProvider(name = "input_a_b_comparisonOfAreaOfCircles")
    public Object[][] createCorrectDataForComparisonOfAreaOfCircles() {
        return
                new Object[][]{
                        {new Circle[]{new Circle(2), new Circle(2)},
                                "First circle lesser then second? - no"},
                        {new Circle[]{new Circle(10), new Circle(20)},
                                "First circle lesser then second? - yes"},
                        {new Circle[]{new Circle(30), new Circle(40)},
                                "First circle lesser then second? - yes"},
                        {new Circle[]{new Circle(9), new Circle(10)},
                                "First circle lesser then second? - yes"},
                };
    }

    @DataProvider(name = "input_a_b_c_numberOfNegativeNumbers")
    public Object[][] createCorrectDataForNumberOfNegativeNumbers() {
        return
                new Object[][]{
                        {new int[]{0, 0, 0}, 0},
                        {new int[]{-1, 30, 0}, 1},
                        {new int[]{0, -10, 20}, 1},
                        {new int[]{0, 20, -60}, 1},
                        {new int[]{-1, -30, 0}, 2},
                        {new int[]{0, -10, -20}, 2},
                        {new int[]{-1, 20, -60}, 2},
                        {new int[]{-1, -20, -60}, 3},
                        {new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE}, 0},
                        {new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE}, 3}
                };
    }

    @DataProvider(name = "input_a_b_c_sumOfMoreAndLess")
    public Object[][] createCorrectDataForSumOfMoreAndLess() {
        return
                new Object[][]{
                        {new int[]{0, 0, 0}, 0},
                        {new int[]{-1, 30, 0}, 29},
                        {new int[]{0, -10, 20}, 10},
                        {new int[]{0, 20, -60}, -40},
                        {new int[]{-1, -30, 0}, -30},
                        {new int[]{0, -10, -20}, -20},
                        {new int[]{-1, 20, -60}, -40},
                        {new int[]{-1, -20, -60}, -61},
                        {new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE}, 4294967294L},
                        {new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE}, -4294967296L}
                };
    }

    @DataProvider(name = "input_a_b_paymentVerification")
    public Object[][] createCorrectDataForPaymentVerification() {
        return
                new Object[][]{
                        {new double[]{0.1, 0.1}, "Спасибо!"},
                        {new double[]{20, 30}, "Нужно еще 10.0"},
                        {new double[]{20, 10}, "Сдача 10.0"},
                        {new double[]{Double.MAX_VALUE, Double.MAX_VALUE}, "Спасибо!"},
                        {new double[]{Double.MIN_NORMAL, Double.MIN_NORMAL}, "Спасибо!"}
                };
    }

    @DataProvider(name = "input_a_b_negativePaymentVerification")
    public Object[][] createNegativeDataForPaymentVerification() {
        return
                new Object[][]{
                        {new double[]{0, 0}},
                        {new double[]{Double.MIN_NORMAL-1, Double.MIN_NORMAL-1}},
                        {new double[]{-1, 0}},
                        {new double[]{0, -1}},
                        {new double[]{-1, -1}},
                        {new double[]{1, -1}},
                        {new double[]{-1, 1}},
                        {new double[]{1, 0}},
                        {new double[]{0, 1}},
                        {new double[]{Double.MIN_NORMAL-1, Double.MIN_NORMAL-1}},
                        {new double[]{Double.MIN_VALUE, Double.MIN_VALUE}}
                };
    }


    @Test(description = "Positive script of the comparing two numbers",
            dataProvider = "input_a_b_comparingTwoNumbers")
    public void comparingTwoNumbersTest(int[] a, String expected) {
        String actual = solvingThroughBranching.comparingTwoNumbers(a[0], a[1]);
        assertEquals(actual, expected);
    }

    @Test(description = "Positive script of the comparison of the area of circles",
            dataProvider = "input_a_b_comparisonOfAreaOfCircles")
    public void comparisonOfAreaOfCirclesTest(Circle[] a, String expected) {
        String actual = solvingThroughBranching.comparisonOfAreaOfCircles(a[0], a[1]);
        assertEquals(actual, expected);
    }

    @Test(description = "Positive script of the number of negative numbers",
            dataProvider = "input_a_b_c_numberOfNegativeNumbers")
    public void numberOfNegativeNumbersTest(int[] a, int expected) {
        int actual = solvingThroughBranching.numberOfNegativeNumbers(a[0], a[1], a[2]);
        assertEquals(actual, expected);
    }

    @Test(description = "Positive script of the sum of more and less",
            dataProvider = "input_a_b_c_sumOfMoreAndLess")
    public void sumOfMoreAndLessTest(int[] a, long expected) {
        long actual = solvingThroughBranching.sumOfMoreAndLess(a[0], a[1], a[2]);
        assertEquals(actual, expected);
    }

    @Test(description = "Positive script of the payment verification",
            dataProvider = "input_a_b_paymentVerification")
    public void paymentVerificationPositiveTest(double[] a, String expected) {
        String actual = solvingThroughBranching.paymentVerification(a[0], a[1]);
        assertEquals(actual, expected);
    }

    @Test(description = "Negative script of the payment verification",
            dataProvider = "input_a_b_negativePaymentVerification")
    public void paymentVerificationNegativeTest(double[] a) {
        assertThrows(Exception.class,()-> solvingThroughBranching.paymentVerification(a[0], a[1]));
    }
}
