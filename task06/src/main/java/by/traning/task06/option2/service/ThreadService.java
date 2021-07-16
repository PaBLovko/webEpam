package by.traning.task06.option2.service;


import by.traning.task06.option2.dao.DAOException;
import by.traning.task06.option2.dao.ReaderDAO;
import by.traning.task06.option2.bean.Matrix;
import by.traning.task06.option2.bean.ThreadInMatrix;
import by.traning.task06.option2.service.exception.ServiceException;
import by.traning.task06.option2.service.parser.LineParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadService {
    private static Logger log = LogManager.getLogger(ThreadService.class);

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

    public void startThreads(int threadCount, Matrix matrix, ReentrantLock lock) throws ServiceException {
        for (int i = 1; i <= threadCount; i++) {
            ThreadInMatrix myThread = new ThreadInMatrix(matrix, lock, i);
            myThread.start();
            log.info("Thread " + i + " started.");
        }
    }
}
