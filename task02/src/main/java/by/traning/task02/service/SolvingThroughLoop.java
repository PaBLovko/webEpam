package by.traning.task02.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigInteger;
import java.util.*;

/**
 * This class consists exclusively of methods of cyclic algorithms
 */
public class SolvingThroughLoop {

    private static Logger logger = LogManager.getLogger(SolvingThroughLoop.class);

    /**
     * The string literal describing that method is invoked
     */
    private static final String METHOD_IS_INVOKED = "The method is invoked";

    /**
     * The string literal describing that method worked correctly
     */
    private static final String METHOD_WORKED_CORRECTLY = "The method worked correctly, result = %s";

    /**
     * The numeric literal that contains the initial value of an ASCII table (excluding control characters)
     */
    private static final int START_TABLE = 32;

    /**
     * The numeric literal that contains the final value of the ASCII table (excluding control characters)
     */
    private static final int END_TABLE = 126;

    /**
     * 2 - The method for calculate an array of numbers from 5 to 1
     * @return array of numbers from 5 to 1
     */
    public int[] calculateNumbersInRange(){
        logger.debug(METHOD_IS_INVOKED);
        int[] result = new int[]{5,4,3,2,1};
        logger.info(METHOD_WORKED_CORRECTLY);
        return result;
    }

    /**
     * 10 - The method for finding the product of the squares of the first two hundred numbers
     * @return the value that contains this result
     */
    public BigInteger calculateGivenProduct() {
        logger.debug(METHOD_IS_INVOKED);
        BigInteger result = BigInteger.valueOf(1);
        for(int i = 2; i <= 200; i++) {
            result = result.multiply(BigInteger.valueOf((long) i * i));
        }
        logger.info(String.format(METHOD_WORKED_CORRECTLY, result));
        return result;
    }

    /**
     * 18 - The method for finding the sum of the terms of a series whose modulus is greater than or
     * equal to a given e. The common term of the series has the form: an = (-1)^(n-1)/n
     * @param e some number to compare with
     * @param numberSeries a number series in the form of a double array
     * @return The sum of the series members satisfying the condition
     */
    public double calculateSumOfRow(double e, double[] numberSeries){
        logger.debug(String.format("The method is invoked, e = %s, numberSeries = %s",
                e, Arrays.toString(numberSeries)));
        double result = 0;
        for (double number : numberSeries) {
            double buffer = Math.pow(-1, number - 1) / number;
            if (Math.abs(buffer) >= e) {
                result += buffer;
            }
        }
        logger.info(String.format(METHOD_WORKED_CORRECTLY, result));
        return result;
    }

    /**
     * 26 - The method for finding matches between characters and
     * their numerical designations in computer memory (ASCII table)
     * @return The key (a value in memory) and the value (ASCII)
     */
    public Map<Integer, Character> calculateRatio(){
        logger.debug(METHOD_IS_INVOKED);
        Map<Integer, Character> result = new HashMap<>();
        for (int i = START_TABLE; i <= END_TABLE; i++) {
            result.put(i, (char) i);
        }
        logger.info(String.format(METHOD_WORKED_CORRECTLY, result));
        return result;
    }

    /**
     * 34 - The method finds four-digit numbers, the sum of the digits of each of which is 15
     * @return The collection containing all the numbers included in the condition
     */
    public List<Integer> calculateFourDigitNumbers(){
        logger.debug(METHOD_IS_INVOKED);
        List<Integer> result = new ArrayList<>();
        for(int number=1000;number<10000;number++){
            int a=number%10;
            int b=number/10%10;
            int c=number/100%10;
            int d=number/1000%10;
            if ((a+b+c+d)==15){
                result.add(number);
            }
        }
        logger.info(String.format(METHOD_WORKED_CORRECTLY, result));
        return result;
    }
}
