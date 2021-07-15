package by.traning.task06.service.creator;

import by.traning.task06.bean.Array;
import by.traning.task06.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The class is the creator for the {@link Array Array} class
 */
public class ArrayCreator {
    private static Logger logger = LogManager.getLogger(ArrayCreator.class);

    /**
     * The method that implements the creation of the Array class
     * @param length size of array
     * @return created class
     * @throws ServiceException when there are no natural values of length
     */
    public Array<Integer> create(final int length) throws ServiceException {
        logger.debug(String.format("The method is invoked, length = %s", length));
        if (length < 1 ) {
            logger.error("The method is exception, non-natural values");
            throw new ServiceException("non-natural values");
        }
        Array<Integer> array = new Array<>(new Integer[length]);
        logger.info(String.format("The method worked correctly, array = %s", array));
        return array;
    }

    // public int[][] createArray(int n, int m, int minValue, int maxValue) {/*code*/
    // public void createFromFile(Matrix matrix, File f) { /* code */ }
    // public void createFromStream(Matrix matrix, Stream stream) { /* code */ }
}
