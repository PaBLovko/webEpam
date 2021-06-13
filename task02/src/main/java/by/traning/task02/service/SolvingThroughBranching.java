package by.traning.task02.service;

import by.traning.task02.bean.Circle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * This class consists exclusively of branch methods
 */
public class SolvingThroughBranching {

    private static Logger logger = LogManager.getLogger(SolvingThroughBranching.class);

    /**
     * The string literal describing that method worked correctly
     */
    private static final String METHOD_WORKED_CORRECTLY= "The method worked correctly, result = %s";

    /**
     * 2 - The method for comparing two numbers 1 and 2.
     * If 1 is less than 2, it returns "yes", otherwise "no"
     * @param a the number under the number 1
     * @param b the number under the number 2
     * @return comparison result
     */
    public String comparingTwoNumbers(int a, int b){
        logger.debug(String.format("The method is invoked, a = %s, b = %s",a,b));
        String result = a<b ? "yes" : "no";
        logger.info(String.format(METHOD_WORKED_CORRECTLY, result));
        return result;
    }

    /**
     * 10 - The method that determines the area of which circle is smaller
     * @param firstCircle the first circle
     * @param secondCircle the second circle
     * @return comparison result
     */
    public String comparisonOfAreaOfCircles(Circle firstCircle, Circle secondCircle){
        logger.debug(String.format("The method is invoked, firstCircle = %s, secondCircle = %s",
                firstCircle,secondCircle));
        String result = "First circle lesser then second? - "+
                comparingTwoNumbers(firstCircle.getArea(), secondCircle.getArea());
        logger.info(String.format(METHOD_WORKED_CORRECTLY, result));
        return result;
    }

    /**
     * 18 - The method that counts the number of negatives among the numbers a, b, and c.
     * @param a the first number
     * @param b the second number
     * @param c the third number
     * @return number of negative numbers
     */
    public int numberOfNegativeNumbers(int a, int b, int c){
        logger.debug(String.format("The method is invoked, a = %s, b = %s, c = %s",a,b,c));
        int[] numbers = new int[]{a,b,c};
        int result = (int) Arrays.stream(numbers).filter(x -> x<0).count();
        logger.info(String.format(METHOD_WORKED_CORRECTLY, result));
        return result;
    }

    /**
     * 26 - The method for finding the sum of the larger and smaller of three numbers.
     * @param a the first number
     * @param b the second number
     * @param c the third number
     * @return the sum of the larger and smaller numbers
     */
    public long sumOfMoreAndLess(int a, int b, int c){
        logger.debug(String.format("The method is invoked, a = %s, b = %s, c = %s",a,b,c));
        int[] numbers = new int[]{a,b,c};
        int max = Arrays.stream(numbers).max().getAsInt();
        int min = Arrays.stream(numbers).min().getAsInt();
        BigInteger bigInteger = BigInteger.valueOf(max);
        bigInteger = bigInteger.add(BigInteger.valueOf(min));
        long result = bigInteger.longValue();
        logger.info(String.format(METHOD_WORKED_CORRECTLY, result));
        return result;
    }

    /**
     * 34 - The method that implements an episode of using a computer in a bookstore. The method
     * requests the cost of books, the amount of money deposited by the buyer;
     * if no change is required, it returns "thank you";
     * if more money is deposited than necessary, it returns "take the change" and indicates the amount of change;
     * if there is not enough money, it returns a message about this and indicates the amount of the missing amount.
     * @param moneyClient amount deposited by the client
     * @param bookValue valid amount of books
     * @return the message with one of three choices
     */
    public String paymentVerification(double moneyClient, double bookValue) throws IllegalArgumentException{
        logger.debug(String.format("The method is invoked, moneyClient = %s, bookValue = %s",
                moneyClient,bookValue));
        if (moneyClient<Double.MIN_NORMAL || bookValue<Double.MIN_NORMAL){
            logger.error("The method is exception, the numbers must be non-negative and not equal to zero");
            throw new IllegalArgumentException("the negative numbers");
        }
        BigDecimal bigDecimalMoneyClient = BigDecimal.valueOf(moneyClient);
        BigDecimal bigDecimalBookValue = BigDecimal.valueOf(bookValue);
        String result;
        if (bigDecimalMoneyClient.compareTo(bigDecimalBookValue) == 0){
            result = "Thanks!";
        } else if(bigDecimalMoneyClient.compareTo(bigDecimalBookValue) > 0.00001) {
            result = "Change "+bigDecimalMoneyClient.subtract(bigDecimalBookValue);
        } else {
            result = "Need more "+bigDecimalBookValue.subtract(bigDecimalMoneyClient);
        }
        logger.info(String.format(METHOD_WORKED_CORRECTLY, result));
        return result;
    }
}