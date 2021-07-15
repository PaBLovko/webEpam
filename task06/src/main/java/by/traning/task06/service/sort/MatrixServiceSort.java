package by.traning.task06.service.sort;

import by.traning.task06.bean.Matrix;
import by.traning.task06.service.creator.MatrixCreator;
import by.traning.task06.service.exception.ServiceException;
import lombok.NonNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigInteger;
import java.util.List;

/**
 * This class represents the logic of the {@link by.traning.task03a.bean.Matrix Matrix} class
 */
public class MatrixServiceSort {
    private static Logger logger = LogManager.getLogger(MatrixServiceSort.class);

    /**
     * The string literal describing incompatible matrices
     */
    private static final String INCOMPATIBLE_MATRICES = "incompatible matrices";

    /**
     * The string literal describing that method is invoked
     */
    private static final String METHOD_IS_INVOKED= "The method is invoked, first = %s, second = %s";

    /**
     * The string literal describing that method worked correctly
     */
    private static final String METHOD_WORKED_CORRECTLY= "The method worked correctly, result = %s";

    /**
     * The string literal describing that method is exception
     */
    private static final String METHOD_IS_EXCEPTION = "The method is exception, incompatible matrices";

    /**
     * The method of filling the matrix with random values from the minimum and maximum specified values
     * @param matrix the object of the matrix on which the action takes place
     * @param minValue minimum value of the matrix
     * @param maxValue maximum value of the matrix
     */
    public void matrixFillRandomized(@NonNull Matrix<Integer> matrix, int minValue, int maxValue){
        logger.debug(String.format("The method is invoked, matrix = %s, minValue = %s, maxValue = %s",
                matrix, minValue, maxValue));
        for(int i = 0; i < matrix.getVerticalSize(); i++) {
            for(int j = 0; j < matrix.getHorizontalSize(); j++) {
                try {
                    setElement(matrix, i, j, (int) ((Math.random() * (maxValue - minValue)) + minValue));
                } catch (ServiceException e) {
                    logger.error("The method is exception, exception impossible");
                }
            }
        }
        logger.info(String.format(METHOD_WORKED_CORRECTLY, matrix));
    }

    /**
     * The method of filling in the matrix from the passed collection
     * @param matrix the object of the matrix in which the values will be written
     * @param listList transferred collection
     */
    public void matrixFill(Matrix<Integer> matrix, List<List<Integer>> listList) {
        logger.debug(String.format("The method is invoked, matrix = %s, listList = %s", matrix, listList));
        for(int i = 0; i < matrix.getVerticalSize(); i++) {
            for(int j = 0; j < matrix.getHorizontalSize(); j++) {
                try {
                    setElement(matrix, i, j, listList.get(i).get(j));
                } catch (ServiceException e) {
                    logger.error("The method is exception, exception impossible");
                }
            }
        }
        logger.info(String.format(METHOD_WORKED_CORRECTLY, matrix));
    }

    /**
     * The method that performs matrix multiplication
     * @param first matrix
     * @param second matrix
     * @return new object of the multiplied matrix
     * @throws ServiceException when incompatible matrices or overflow
     */
    public Matrix<Integer> matrixMultiply(@NonNull final Matrix<Integer> first, @NonNull final Matrix<Integer> second)
            throws ServiceException {
        logger.debug(String.format(METHOD_IS_INVOKED, first, second));
        if (first.getHorizontalSize() != second.getVerticalSize()) {
            throw new ServiceException(INCOMPATIBLE_MATRICES);
        }
        Matrix<Integer> result =  new MatrixCreator().create(first.getVerticalSize(), second.getHorizontalSize());
            for (int i = 0; i < first.getVerticalSize(); i++) {
                for (int j = 0; j < second.getHorizontalSize(); j++) {
                    int value = 0;
                    for (int k = 0; k < first.getHorizontalSize(); k++) {
                        int multiply = multiply(getElement(first, i, k), getElement(second, k, j));
                        value = summation(value, multiply) ;
                    }
                    setElement(result, i, j, value);
                }
            }
        logger.info(String.format(METHOD_WORKED_CORRECTLY, result));
        return result;
    }

    /**
     * The method that performs matrix addition
     * @param first matrix
     * @param second matrix
     * @return new object of the summed matrix
     * @throws ServiceException when incompatible matrices or overflow
     */
    public Matrix<Integer> matrixSummation(@NonNull final Matrix<Integer> first, @NonNull final Matrix<Integer> second)
            throws ServiceException {
        logger.debug(String.format(METHOD_IS_INVOKED, first, second));
        if (first.getVerticalSize() != second.getVerticalSize() ||
                first.getHorizontalSize() != second.getHorizontalSize()) {
            throw new ServiceException(INCOMPATIBLE_MATRICES);
        }
        Matrix<Integer> result =  new MatrixCreator().create(first.getVerticalSize(), first.getHorizontalSize());
        for (int i = 0; i < first.getVerticalSize(); i++) {
            for (int j = 0; j < first.getHorizontalSize(); j++) {
                setElement(result, i, j, summation(getElement(first, i, j), getElement(second, i, j)));
            }
        }
        logger.info(String.format(METHOD_WORKED_CORRECTLY, result));
        return result;
    }

    /**
     * The method that performs matrix subtraction
     * @param first matrix
     * @param second matrix
     * @return new object of the subtracted matrix
     * @throws ServiceException when incompatible matrices or overflow
     */
    public Matrix<Integer> matrixSubtraction(@NonNull final Matrix<Integer> first, @NonNull final Matrix<Integer> second)
            throws ServiceException {
        logger.debug(String.format(METHOD_IS_INVOKED, first, second));
        if (first.getVerticalSize() != second.getVerticalSize() ||
                first.getHorizontalSize() != second.getHorizontalSize()) {
            logger.error(METHOD_IS_EXCEPTION);
            throw new ServiceException(INCOMPATIBLE_MATRICES);
        }
        Matrix<Integer> result = new MatrixCreator().create(first.getVerticalSize(), first.getHorizontalSize());
        try {
            for (int i = 0; i < first.getVerticalSize(); i++) {
                for (int j = 0; j < first.getHorizontalSize(); j++) {
                    setElement(result, i, j, subtraction(getElement(first, i, j), getElement(second, i, j)));
                }
            }
        }catch (ServiceException e){
            logger.error("The method is exception, variable overflow when subtracting the matrix");
            throw new ServiceException("variable overflow when subtracting the matrix",e);
        }
        logger.info(String.format(METHOD_WORKED_CORRECTLY, result));
        return result;
    }

    /**
     * The method for subtracting int variables with overflow check
     * @param first value
     * @param second value
     * @return the subtracted value
     * @throws ServiceException when overflow
     */
    private int subtraction(final int first, final int second) throws ServiceException {
        logger.debug(String.format(METHOD_IS_INVOKED, first, second));
        if (!checkForOverflowOnSubtraction(first,second)){
            int result = first - second;
            logger.info(String.format(METHOD_WORKED_CORRECTLY, result));
            return result;
        }else {
            logger.error("The method is exception, overflow on subtraction");
            throw new ServiceException("overflow on subtraction");
        }
    }

    /**
     * The method for multiplying int variables with overflow check
     * @param first value
     * @param second value
     * @return the multiplied value
     * @throws ServiceException when overflow
     */
    private int multiply(final int first, final int second) throws ServiceException {
        logger.debug(String.format(METHOD_IS_INVOKED, first, second));
        if (!checkForOverflowDuringMultiply(first,second)){
            int result = first * second;
            logger.info(String.format(METHOD_WORKED_CORRECTLY, result));
            return result;
        }else {
            logger.error("The method is exception, overflow during multiplication");
            throw new ServiceException("overflow during multiplication");
        }
    }

    /**\
     * The method for summing int variables with overflow check
     * @param first value
     * @param second value
     * @return the summed value
     * @throws ServiceException when overflow
     */
    private int summation(final int first, final int second) throws ServiceException {
        logger.debug(String.format(METHOD_IS_INVOKED, first, second));
        if (!checkForOverflowDuringSummation(first,second)){
            int result = first + second;
            logger.info(String.format(METHOD_WORKED_CORRECTLY, result));
            return result;
        }else {
            logger.error("The method is exception, overflow on addition");
            throw new ServiceException("overflow on addition");
        }
    }

    /**
     * The Method that checks for overflow of int variables when subtracting
     * @param first matrix element
     * @param second matrix element
     * @return true if there was an overflow otherwise false
     */
    private boolean checkForOverflowOnSubtraction(final int first, final int second) {
        logger.debug(String.format(METHOD_IS_INVOKED, first, second));
        BigInteger result = BigInteger.valueOf(first);
        result = result.subtract(BigInteger.valueOf(second));
        logger.info(String.format(METHOD_WORKED_CORRECTLY,result));
        return result.compareTo(BigInteger.valueOf(Integer.MIN_VALUE)) < 0;
    }

    /**
     * The Method that checks for overflow of int variables when multiplying
     * @param first matrix element
     * @param second matrix element
     * @return true if there was an overflow otherwise false
     */
    private boolean checkForOverflowDuringMultiply(final int first, final int second){
        logger.debug(String.format(METHOD_IS_INVOKED, first, second));
        BigInteger result = BigInteger.valueOf(first);
        result = result.multiply(BigInteger.valueOf(second));
        logger.info(String.format(METHOD_WORKED_CORRECTLY,result));
        return result.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0;
    }

    /**
     * The method that checks for overflow of int variables when summation
     * @param first matrix element
     * @param second matrix element
     * @return true if there was an overflow otherwise false
     */
    private boolean checkForOverflowDuringSummation(final int first, final int second){
        logger.debug(String.format(METHOD_IS_INVOKED, first, second));
        BigInteger result = BigInteger.valueOf(first);
        result = result.add(BigInteger.valueOf(second));
        logger.info(String.format(METHOD_WORKED_CORRECTLY,result));
        return result.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0;
    }

    /**
     * The method for getting a value for a specified column and row
     * @param matrix the object of the matrix on which the action takes place
     * @param column column number
     * @param row row number
     * @return the variable contained in the specified location
     * @throws ServiceException when invalid column or column values are specified
     */
    private int getElement(@NonNull final Matrix<Integer> matrix, final int column, final int row)
            throws ServiceException {
        logger.debug(String.format("The method is invoked, matrix = %s, column = %s, row = %s",
                matrix, column, row));
        if (checkRange(matrix,column, row)) {
            int result = matrix.getElement(column,row);
            logger.info(String.format(METHOD_WORKED_CORRECTLY, result));
            return result;
        } else {
            logger.error("The method is exception, not correct values");
            throw new ServiceException("Not correct values");
        }
    }

    /**
     * The method for setting a value for a specified column and row
     * @param matrix the object of the matrix on which the action takes place
     * @param column column number
     * @param row row number
     * @param value new value
     * @throws ServiceException when invalid column or column values are specified
     */
    private void setElement(@NonNull Matrix<Integer> matrix, final int column, final int row, final int value)
            throws ServiceException {
        logger.debug(String.format("The method is invoked, matrix = %s, column = %s, row = %s, value = %s",
                matrix, column, row, value));
        if (checkRange(matrix,column, row)) {
            matrix.setElement(column,row,value);
            logger.info(String.format("The method worked correctly, value = %s, column = %s, row = %s",
                    value, column, row));
        } else {
            logger.error("The method is exception, not correct values");
            throw new ServiceException("Not correct values");
        }
    }

    /**
     * The method that checks the boundaries of a matrix
     * @param matrix the object of the matrix on which the action takes place
     * @param column column number
     * @param row row number
     * @return true if the value is within the matrix otherwise returns false
     */
    private boolean checkRange(@NonNull final Matrix<Integer> matrix, final int column, final int row) {
        logger.debug(String.format("The method is invoked, matrix = %s, column = %s, row = %s",matrix, column, row));
        boolean result = column >= 0 && column <= matrix.getVerticalSize() - 1 &&
                row >= 0 && row <= matrix.getHorizontalSize() - 1;
        logger.info(String.format(METHOD_WORKED_CORRECTLY,result));
        return result;
    }
}
