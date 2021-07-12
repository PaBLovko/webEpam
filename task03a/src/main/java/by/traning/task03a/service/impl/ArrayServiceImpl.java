package by.traning.task03a.service.impl;

import by.traning.task03a.bean.Array;
import by.traning.task03a.dao.ArrayDAO;
import by.traning.task03a.dao.exception.DAOException;
import by.traning.task03a.dao.factory.DAOFactory;
import by.traning.task03a.service.ArrayService;
import by.traning.task03a.service.sort.ArrayServiceSort;
import by.traning.task03a.service.creator.ArrayCreator;
import by.traning.task03a.service.creator.FileCreator;
import by.traning.task03a.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

/**
 * The class that implements the interface
 */
public class ArrayServiceImpl implements ArrayService{

    private static Logger logger = LogManager.getLogger(ArrayServiceImpl.class);

    /**
     * The string literal describing that method worked correctly
     */
    private static final String METHOD_WORKED_CORRECTLY= "The method worked correctly, array = %s";

    /**
     * The string literal describing that method is invoked
     */
    private static final String METHOD_IS_INVOKED = "The method is invoked";

    /**
     * The string literal describing that method did not write data
     */
    private static final String ERROR_WRITE = "Error during writing";

    /**
     * The string literal describing that method is exception
     */
    private static final String ERROR_READ = "Error during reading";

    /**
     * The string literal describing the path to the array file
     */
    private static final String PATH_ARRAY = "src/main/resources/array.txt";

    /**
     * The string literal describing the path to the file with the result calculation
     */
    private static final String PATH_ARRAY_RESULT = "src/main/resources/arrayResult.txt";

    @Override
    public void arraySortBubble() throws ServiceException{
        logger.debug(METHOD_IS_INVOKED);
        DAOFactory daoFactory = DAOFactory.getInstance();
        ArrayDAO<Integer> arrayDAO = daoFactory.getFileArrayImpl();
        ArrayServiceSort arrayServiceAction = new ArrayServiceSort();
        Array<Integer> array = arrayFill(arrayDAO, arrayServiceAction);
        arrayServiceAction.arraySortBubble(array);
        pushData(arrayDAO, array);
        logger.info(String.format(METHOD_WORKED_CORRECTLY, array));
    }

    @Override
    public void arraySortShaker() throws ServiceException{
        logger.debug(METHOD_IS_INVOKED);
        DAOFactory daoFactory = DAOFactory.getInstance();
        ArrayDAO<Integer> arrayDAO = daoFactory.getFileArrayImpl();
        ArrayServiceSort arrayServiceAction = new ArrayServiceSort();
        Array<Integer> array = arrayFill(arrayDAO, arrayServiceAction);
        arrayServiceAction.arraySortShaker(array);
        pushData(arrayDAO, array);
        logger.info(String.format(METHOD_WORKED_CORRECTLY, array));
    }

    @Override
    public void arraySortSelection() throws ServiceException{
        logger.debug(METHOD_IS_INVOKED);
        DAOFactory daoFactory = DAOFactory.getInstance();
        ArrayDAO<Integer> arrayDAO = daoFactory.getFileArrayImpl();
        ArrayServiceSort arrayServiceAction = new ArrayServiceSort();
        Array<Integer> array = arrayFill(arrayDAO, arrayServiceAction);
        arrayServiceAction.arraySortSelection(array);
        pushData(arrayDAO, array);
        logger.info(String.format(METHOD_WORKED_CORRECTLY, array));
    }

    @Override
    public void arraySortInsertion() throws ServiceException {
        logger.debug(METHOD_IS_INVOKED);
        DAOFactory daoFactory = DAOFactory.getInstance();
        ArrayDAO<Integer> arrayDAO = daoFactory.getFileArrayImpl();
        ArrayServiceSort arrayServiceAction = new ArrayServiceSort();
        Array<Integer> array = arrayFill(arrayDAO, arrayServiceAction);
        arrayServiceAction.arraySortInsertion(array);
        pushData(arrayDAO, array);
        logger.info(String.format(METHOD_WORKED_CORRECTLY, array));
    }

    @Override
    public void arraySortHashing() throws ServiceException {
        logger.debug(METHOD_IS_INVOKED);
        DAOFactory daoFactory = DAOFactory.getInstance();
        ArrayDAO<Integer> arrayDAO = daoFactory.getFileArrayImpl();
        ArrayServiceSort arrayServiceAction = new ArrayServiceSort();
        Array<Integer> array = arrayFill(arrayDAO, arrayServiceAction);
        arrayServiceAction.arraySortHashing(array);
        pushData(arrayDAO, array);
        logger.info(String.format(METHOD_WORKED_CORRECTLY, array));
    }

    @Override
    public void arraySortMerge() throws ServiceException {
        logger.debug(METHOD_IS_INVOKED);
        DAOFactory daoFactory = DAOFactory.getInstance();
        ArrayDAO<Integer> arrayDAO = daoFactory.getFileArrayImpl();
        ArrayServiceSort arrayServiceAction = new ArrayServiceSort();
        Array<Integer> array = arrayFill(arrayDAO, arrayServiceAction);
        arrayServiceAction.arraySortMerge(array);
        pushData(arrayDAO, array);
        logger.info(String.format(METHOD_WORKED_CORRECTLY, array));
    }

    @Override
    public void arraySortShell() throws ServiceException {
        logger.debug(METHOD_IS_INVOKED);
        DAOFactory daoFactory = DAOFactory.getInstance();
        ArrayDAO<Integer> arrayDAO = daoFactory.getFileArrayImpl();
        ArrayServiceSort arrayServiceAction = new ArrayServiceSort();
        Array<Integer> array = arrayFill(arrayDAO, arrayServiceAction);
        arrayServiceAction.arraySortShell(array);
        pushData(arrayDAO, array);
        logger.info(String.format(METHOD_WORKED_CORRECTLY, array));
    }

    /**
     * The class fills in an array from the file and returns there
     * @param arrayDAO class that worked with the data source
     * @param arrayServiceAction the class containing methods for working with an array
     * @return filled array
     * @throws ServiceException when the error occurred with the file or array
     */
    private Array<Integer> arrayFill(ArrayDAO<Integer> arrayDAO, ArrayServiceSort arrayServiceAction)
            throws ServiceException{
        logger.debug(METHOD_IS_INVOKED);
        Array<Integer> array;
        try {
            List<Integer> list = arrayDAO.read(new FileCreator().create(PATH_ARRAY));
            array = new ArrayCreator().create(list.size());
            arrayServiceAction.arrayFill(array, list);
        }catch (DAOException e){
            logger.error(ERROR_READ, e);
            throw new ServiceException(ERROR_READ, e);
        }
        logger.info(String.format(METHOD_WORKED_CORRECTLY, array));
        return array;
    }

    /**
     * The method for push array
     * @param arrayDAO class that worked with the data source
     * @param array array with data
     * @throws ServiceException when the error occurred with the file
     */
    private void pushData(ArrayDAO<Integer> arrayDAO, Array<Integer> array) throws ServiceException{
        logger.debug(METHOD_IS_INVOKED);
        try {
            arrayDAO.write(Arrays.asList(array.getValues()), new FileCreator().create(PATH_ARRAY_RESULT));
        }catch (DAOException e){
            logger.error(ERROR_WRITE, e);
            throw new ServiceException(ERROR_WRITE, e);
        }
        logger.info(String.format(METHOD_WORKED_CORRECTLY, array));
    }
}
