package by.traning.task06.option2.service;

import by.traning.task06.option2.dao.DAOException;
import by.traning.task06.option2.dao.ReaderDAO;
import by.traning.task06.option2.bean.Matrix;
import by.traning.task06.option2.bean.Item;
import by.traning.task06.option2.service.exception.ServiceException;
import by.traning.task06.option2.service.parser.LineParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class MatrixService {
    private static Logger log = LogManager.getLogger(MatrixService.class);

    public Matrix createMatrix(ReentrantLock locker, String path) throws ServiceException {

        ReaderDAO readerDAO = new ReaderDAO();
        LineParser lineParser = new LineParser();
        int[][] array;
        try {
            array = lineParser.returnTwoDimArray(readerDAO.readLines(path));
        } catch (ServiceException | DAOException e) {
            throw new ServiceException(e);
        }
        log.info("Matrix created successfully.");
        return new Matrix(array, getListElement(locker, array), locker);
    }

    private List<Item> getListElement(ReentrantLock locker, int[][] matrix) {
        List<Item> elements = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            elements.add(new Item(locker, matrix[i][i], i, i));
        }
        return elements;
    }
}
