package by.traning.task01.service;

import by.traning.task01.bean.Cube;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class consist exclusively of linear algorithm methods
 */
public class Calculation {
    private static Logger logger = LogManager.getLogger(Calculation.class);
    /**
     * 2 - There is a function c = 3 + a,
     * method finds the value of the expression
     * @param a this is the value with which the summation occurs
     * @return double value that is calculation result
     */
    public int foldWithThree(int a) throws ArithmeticException {
        logger.info(String.format("foldWithThree() is invoked, a = %s",a));
        checkingForOverflow(a);
        return 3 + a;
    }

    /**
     * The method that checks a variable "int" for overflow
     * @param a this is a variable that has been submitted for verification
     * @throws ArithmeticException this is an exception if the "int" variable is overflowing
     */
    private void checkingForOverflow(int a) throws ArithmeticException{
        logger.info(String.format("checkingForOverflow() is invoked, a = %s",a));
        if (a >= Integer.MAX_VALUE){
            checkingValueForNegation(a);
            logger.error(String.format("when summing the value of %s with a triple, an overflow occurs",a));
            throw new ArithmeticException("Summation cannot be performed");
        }
    }

    /**
     * 10 - There is a function c = (sin x + cos y) / (cos x - sin y) * tg xy,
     * method finds the value of the expression
     * @param x this is the value to calculate
     * @param y this is the value to calculate
     * @return double value that is calculation result
     * @throws NumberFormatException this is an exception in the case of an inappropriate value
     */
    public double trigonometricFormula(double x, double y) throws NumberFormatException{
        logger.info(String.format("trigonometricFormula() is invoked, x = %s, y = %s",x,y));
        checkingValueForNegation(x);
        checkingValueForNegation(y);
        return (Math.sin(Math.toRadians(x)) + Math.cos(Math.toRadians(y))) /
                (Math.cos(Math.toRadians(x)) - Math.sin(Math.toRadians(y))) *
                Math.tan(Math.toRadians(x * y));
    }

    /**
     * 18 - The method calculates the area of a face,
     * the total surface area, and the volume of that cube
     * @param cubeEdgeLength this is the length of the edge of the cube
     * @return The cube object that is the result of the calculation
     * @throws NumberFormatException this is an exception in the case of an inappropriate value
     */
    public Cube calculatingCubeParameters(double cubeEdgeLength) throws NumberFormatException{
        logger.info(String.format("calculatingCubeParameters() is invoked, cubeEdgeLength = %s",cubeEdgeLength));
        checkingExistenceOfValue(cubeEdgeLength);
        double faceArea=getFaceArea(cubeEdgeLength);
        double cubeVolume=getCubeVolume(cubeEdgeLength);
        double cubeArea=getCubeArea(cubeEdgeLength);
        return new Cube(cubeEdgeLength, faceArea, cubeVolume, cubeArea);
    }

    /**
     * The method that checks whether a variable can exist
     * @param value this is a variable that has been submitted for verification
     * @throws NumberFormatException this is an exception in the case of an inappropriate value
     */
    private void checkingExistenceOfValue(double value) throws NumberFormatException{
        logger.info(String.format("checkingExistenceOfValue() is invoked, value = %s",value));
        checkingValueForZero(value);
        checkingValueForNegation(value);
    }

    /**
     * The method that checks a variable for zero
     * @param value this is a variable that has been submitted for verification
     * @throws NumberFormatException this is an exception in the case of a zero value
     */
    private void checkingValueForZero(double value) throws NumberFormatException{
        logger.info(String.format("checkingValueForZero() is invoked, value = %s",value));
        if (value == 0){
            logger.error(String.format("The number %s did not pass the check",value));
            throw new NumberFormatException("The value cannot be zero");
        }
    }

    /**
     * The method that checks a variable for negation
     * @param value this is a variable that has been submitted for verification
     * @throws NumberFormatException this is an exception in the case of a negative value
     */
    private void checkingValueForNegation(double value) throws NumberFormatException{
        logger.info(String.format("checkingValueForNegation() is invoked, value = %s",value));
        if (value < 0){
            logger.error(String.format("The number %s did not pass the check",value));
            throw new NumberFormatException("The value cannot be negative");
        }
    }

    /**
     * 18 - The method calculates the area of a face
     * @param cubeEdgeLength this is the length of the edge of the cube
     * @return double value that is calculation result
     */
    private double getFaceArea(double cubeEdgeLength) throws NumberFormatException{
        logger.info(String.format("getFaceArea() is invoked, cubeEdgeLength = %s",cubeEdgeLength));
        return Math.pow(cubeEdgeLength,2);
    }

    /**
     * 18 - The method calculates the volume of that cube
     * @param cubeEdgeLength this is the length of the edge of the cube
     * @return double value that is calculation result
     */
    private double getCubeVolume(double cubeEdgeLength) throws NumberFormatException{
        logger.info(String.format("getCubeVolume() is invoked, cubeEdgeLength = %s",cubeEdgeLength));
        return Math.pow(cubeEdgeLength,3);
    }

    /**
     * 18 - The method calculates the total surface area
     * @param cubeEdgeLength this is the length of the edge of the cube
     * @return double value that is calculation result
     */
    private double getCubeArea(double cubeEdgeLength) throws NumberFormatException{
        logger.info(String.format("getCubeArea() is invoked, cubeEdgeLength = %s",cubeEdgeLength));
        return Math.pow(cubeEdgeLength,2)*6;
    }

    /**
     * 26 - The method calculates the area of a triangle
     * @param a this is the first side between the corner
     * @param b this is the second side between the corner
     * @param y this is the angle between the sides
     * @return double value that is calculation result
     */
    public double getAreaTriangle(double a, double b, double y) throws NumberFormatException{
        logger.info(String.format("getAreaTriangle() is invoked, a = %s, b = %s, y = %s",a,b,y));
        checkingExistenceOfValue(a);
        checkingExistenceOfValue(b);
        checkingExistenceOfValue(y);
        return 0.5*a*b*Math.sin(Math.toRadians(y));
    }

    /**
     * 34 - The method converts from bytes to kilobytes
     * @param bytes this is a value that expresses the amount of information in bytes
     * @return double value that is calculation result
     * @throws NumberFormatException this is an exception in the case of an inappropriate value
     */
    public double bytesToKilobytes(double bytes) throws NumberFormatException{
        logger.info(String.format("bytesToKilobytes() is invoked, bytes = %s",bytes));
        checkingValueForNegation(bytes);
        if (bytes == 0){
            return 0;
        }
        return bytes / 1000;
    }
}
