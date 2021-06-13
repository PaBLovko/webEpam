package by.traning.task03a.service.impl;

import by.traning.task03a.bean.Matrix;
import by.traning.task03a.dao.MatrixDAO;
import by.traning.task03a.dao.exception.DAOException;
import by.traning.task03a.dao.factory.DAOFactory;
import by.traning.task03a.service.MatrixService;
import by.traning.task03a.service.action.MatrixServiceAction;
import by.traning.task03a.service.creator.FileCreator;
import by.traning.task03a.service.creator.MatrixCreator;
import by.traning.task03a.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;


public class MatrixServiceImpl implements MatrixService{

    private static Logger logger = LogManager.getLogger(MatrixServiceImpl.class);

    /**
     * The string literal describing that method worked correctly
     */
    private static final String METHOD_WORKED_CORRECTLY= "The method worked correctly, matrix = %s";

    /**
     * The string literal describing that method is invoked
     */
    private static final String METHOD_IS_INVOKED= "The method is invoked";

    /**
     * The string literal describing that method is exception
     */
    private static final String ERROR_DURING_CALCULATION = "Error during calculation";

    /**
     * The string literal describing the path to the file with the result calculation
     */
    private static final String PATH_MATRIX_RESULT = "src/main/resources/matrixResult.txt";

    /**
     * The string literal describing the path to the file with the first matrix
     */
    private static final String PATH_MATRIX_FIRST = "src/main/resources/matrixFirst.txt";

    /**
     * The string literal describing the path to the file with the second matrix
     */
    private static final String PATH_MATRIX_SECOND = "src/main/resources/matrixSecond.txt.txt";

    /**
     * The string literal describing that method did not write data
     */
    private static final String ERROR_WRITE = "The method is invoked";

    @Override
    public void matrixMultiply() throws ServiceException {
        logger.debug(METHOD_IS_INVOKED);
        DAOFactory daoFactory = DAOFactory.getInstance();
        MatrixDAO<Integer> matrixDAO = daoFactory.getFileMatrixImpl();
        MatrixServiceAction matrixServiceAction = new MatrixServiceAction();
        Matrix<Integer> matrixFirst = matrixFill(matrixDAO, matrixServiceAction, PATH_MATRIX_FIRST);
        Matrix<Integer> matrixSecond = matrixFill(matrixDAO, matrixServiceAction, PATH_MATRIX_SECOND);
        Matrix<Integer> result = matrixServiceAction.matrixMultiply(matrixFirst, matrixSecond);
        pushData(matrixDAO, result);
        logger.info(String.format(METHOD_WORKED_CORRECTLY, result));
    }

    @Override
    public void matrixSummation() throws ServiceException {
        logger.debug(METHOD_IS_INVOKED);
        DAOFactory daoFactory = DAOFactory.getInstance();
        MatrixDAO<Integer> matrixDAO = daoFactory.getFileMatrixImpl();
        MatrixServiceAction matrixServiceAction = new MatrixServiceAction();
        Matrix<Integer> matrixFirst = matrixFill(matrixDAO, matrixServiceAction, PATH_MATRIX_FIRST);
        Matrix<Integer> matrixSecond = matrixFill(matrixDAO, matrixServiceAction, PATH_MATRIX_SECOND);
        Matrix<Integer> result = matrixServiceAction.matrixSummation(matrixFirst, matrixSecond);
        pushData(matrixDAO, result);
        logger.info(String.format(METHOD_WORKED_CORRECTLY, result));
    }

    @Override
    public void matrixSubtraction() throws ServiceException {
        logger.debug(METHOD_IS_INVOKED);
        DAOFactory daoFactory = DAOFactory.getInstance();
        MatrixDAO<Integer> matrixDAO = daoFactory.getFileMatrixImpl();
        MatrixServiceAction matrixServiceAction = new MatrixServiceAction();
        Matrix<Integer> matrixFirst = matrixFill(matrixDAO, matrixServiceAction, PATH_MATRIX_FIRST);
        Matrix<Integer> matrixSecond = matrixFill(matrixDAO, matrixServiceAction, PATH_MATRIX_SECOND);
        Matrix<Integer> result = matrixServiceAction.matrixSubtraction(matrixFirst, matrixSecond);
        pushData(matrixDAO, result);
        logger.info(String.format(METHOD_WORKED_CORRECTLY, result));
    }

    /**
     * The class fills in an matrix from the file and returns there
     * @param matrixDAO class that worked with the data source
     * @param matrixServiceAction the class containing methods for working with an matrix
     * @param path path to the data file
     * @return filled array
     * @throws ServiceException when the error occurred with the file or array
     */
    private Matrix<Integer> matrixFill(MatrixDAO<Integer> matrixDAO, MatrixServiceAction matrixServiceAction,
                                       String path) throws ServiceException{
        logger.debug(METHOD_IS_INVOKED);
        Matrix<Integer> matrix;
        try {
            List<List<Integer>> listList = matrixDAO.read(new FileCreator().create(path));
            matrix = new MatrixCreator().create(listList.size(), listList.get(0).size());
            matrixServiceAction.matrixFill(matrix, listList);
        }catch (DAOException e){
            logger.error(ERROR_DURING_CALCULATION, e);
            throw new ServiceException(ERROR_DURING_CALCULATION, e);
        }
        logger.info(String.format(METHOD_WORKED_CORRECTLY, matrix));
        return matrix;
    }

    /**
     * The method for push matrix
     * @param matrixDAO class that worked with the data source
     * @param matrix matrix with data
     * @throws ServiceException when the error occurred with the file
     */
    private void pushData(MatrixDAO<Integer> matrixDAO, Matrix<Integer> matrix) throws ServiceException{
        logger.debug(METHOD_IS_INVOKED);
        try {
            List<List<Integer>> listList = Arrays.stream(matrix.getValues()).
                    map(t -> Arrays.stream(t).toList()).toList();
            matrixDAO.write(listList, new FileCreator().create(PATH_MATRIX_RESULT));
        }catch (DAOException e){
            logger.error(ERROR_WRITE, e);
            throw new ServiceException(ERROR_WRITE, e);
        }
        logger.info(String.format(METHOD_WORKED_CORRECTLY, matrix));
    }
}
