package by.traning.task06.option4.service;

import by.traning.task06.option4.dao.ReaderDAO;
import by.traning.task06.option4.dao.DAOException;
import by.traning.task06.option4.bean.Matrix;
import by.traning.task06.option4.bean.ThreadInMatrix;
import by.traning.task06.option4.service.exception.ServiceException;
import by.traning.task06.option4.service.parser.LineParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    public void startThreads(int threadCount, Matrix matrix) throws ServiceException {
        for (int i = 1; i <= threadCount; i++) {
           ThreadInMatrix myThread = new ThreadInMatrix(matrix, i);
           myThread.start();
            log.info("Thread " + i + " started.");
        }
    }
}
