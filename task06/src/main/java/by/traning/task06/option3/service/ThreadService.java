package by.traning.task06.option3.service;

import by.traning.task06.option3.bean.Matrix;
import by.traning.task06.option3.bean.ThreadInMatrix;
import by.traning.task06.option3.dao.DAOException;
import by.traning.task06.option3.dao.ReaderDAO;
import by.traning.task06.option3.service.exception.ServiceException;
import by.traning.task06.option3.service.parser.LineParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Semaphore;

public class ThreadService {
    private static Logger log = LogManager.getLogger(ThreadService.class.getName());

    public int returnThreadsCount(String path) throws ServiceException {
        ReaderDAO readerDAO = new ReaderDAO();
        LineParser lineParser = new LineParser();
        int threadCount;
        try {
            threadCount = lineParser.returnThreadCount(readerDAO.readLines(path));
        } catch (ServiceException | DAOException e) {
            throw new ServiceException(e);
        }
        log.info("Count of thread successfully read.");
        return threadCount;
    }

    public void startThreads(int threadCount, Matrix matrix, Semaphore sem) throws ServiceException {
        for (int i = 1; i <= threadCount; i++) {
            ThreadInMatrix myThread = new ThreadInMatrix(matrix, sem, i);
            myThread.start();
            log.info("Thread " + i + " started.");
        }
    }
}
