package by.traning.task03a.service.creator;

import by.traning.task03a.bean.Matrix;
import by.traning.task03a.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The class is the creator for the {@link by.traning.task03a.bean.Matrix Matrix} class
 */
public class MatrixCreator {
    private static Logger logger = LogManager.getLogger(MatrixCreator.class);

    /**
     * The method that implements the creation of the Matrix class
     * @param column number column
     * @param row number row
     * @return created class
     * @throws ServiceException when there are no natural values of rows or columns
     */
    public Matrix<Integer> create(final int column, final int row) throws ServiceException {
        logger.debug(String.format("The method is invoked, column = %s, row = %s", column, row));
        if (column < 1 || row < 1) {
            logger.error("The method is exception, non-natural values");
            throw new ServiceException("non-natural values");
        }
        Matrix<Integer> matrix = new Matrix<>(new Integer[column][row]);
        logger.info(String.format("The method worked correctly, matrix = %s", matrix));
        return matrix;
    }
}
