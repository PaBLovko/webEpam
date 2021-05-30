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
     * 2 - The method for getting an array of numbers from 5 to 1
     * @return array of numbers from 5 to 1
     */
    public int[] gettingNumbersInRange(){
        logger.info("gettingNumbersInRange() is invoked");
        return new int[]{5,4,3,2,1};
    }

    /**
     * 10 - The method for finding the product of the squares of the first two hundred numbers
     * @return the value that contains this result
     */
    public BigInteger gettingGivenProduct() {
        logger.info("gettingGivenProduct() is invoked");
        BigInteger result = BigInteger.valueOf(1);
        for(int i = 2; i <= 200; i++) {
            result = result.multiply(BigInteger.valueOf((long) i * i));
        }
        return result;
    }

    /**
     * 18 - The method for finding the sum of the terms of a series whose modulus is greater than or
     * equal to a given e. The common term of the series has the form: an = (-1)^(n-1)/n
     * @param e some number to compare with
     * @param numberSeries a number series in the form of a double array
     * @return The sum of the series members satisfying the condition
     */
    public double getSumOfRow(double e, double[] numberSeries){
        logger.info(String.format("getSumOfRow() is invoked, e = %s, numberSeries = %s",
                e, Arrays.toString(numberSeries)));
        double sum = 0;
        for (double number : numberSeries) {
            double buffer = Math.pow(-1, number - 1) / number;
            if (Math.abs(buffer) >= e) {
                sum += buffer;
            }
        }
        return sum;
    }

    /**
     * 26 - The method for finding matches between characters and
     * their numerical designations in computer memory (ASCII table)
     * @return The key (a value in memory) and the value (ASCII)
     */
    public Map<Integer, Character> getRatio(){
        logger.info("getRatio() is invoked");
        Map<Integer, Character> result = new HashMap<>();
        for (int i = 32; i <= 126; i++) {
            result.put(i, (char) i);
        }
        return result;
    }

    /**
     * 34 - The method finds four-digit numbers, the sum of the digits of each of which is 15
     * @return The collection containing all the numbers included in the condition
     */
    public List<Integer> getFourDigitNumbers(){
        logger.info("getFourDigitNumbers() is invoked");
        List<Integer> fourDigitNumbers = new ArrayList<>();
        for(int number=1000;number<10000;number++){
            int a=number%10;
            int b=number/10%10;
            int c=number/100%10;
            int d=number/1000%10;
            if ((a+b+c+d)==15){
                fourDigitNumbers.add(number);
            }
        }
        return fourDigitNumbers;
    }
}
