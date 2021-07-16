package by.traning.task06.option4.service;

import by.traning.task06.option4.dao.ReaderDAO;
import by.traning.task06.option4.dao.DAOException;
import by.traning.task06.option4.bean.Matrix;
import by.traning.task06.option4.service.exception.ServiceException;
import by.traning.task06.option4.service.parser.LineParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.atomic.AtomicInteger;

public class MatrixService {
    private static Logger log = LogManager.getLogger(MatrixService.class);

    public Matrix createMatrix(String path) throws ServiceException {

        ReaderDAO readerDAO = new ReaderDAO();
        LineParser lineParser = new LineParser();
        AtomicInteger[][] array;
        try {
            array = lineParser.returnTwoDimArray(readerDAO.readLines(path));
        } catch (ServiceException | DAOException e) {
            throw new ServiceException(e);
        }
        log.info("Matrix created successfully.");
        return new Matrix(array);
    }
}
