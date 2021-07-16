package by.traning.task06.option1.controller;

import by.traning.task06.option1.bean.Matrix;
import by.traning.task06.option1.service.DiagonalChangerThread;
import by.traning.task06.option1.service.ThreadCreator;
import by.traning.task06.option1.view.View;
import by.traning.task06.option1.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Controller {
    private static Logger LOGGER = LogManager.getLogger(Controller.class);

    public void execute(String path) {
        View viewer = new View();
        int timeoutInMilliseconds = 2000;
        ThreadCreator threadCreator = new ThreadCreator();
        Matrix matrix = Matrix.getInstance();
        LOGGER.info("\n" + matrix + " was initialized.");
        List<DiagonalChangerThread> diagonalChangerThreads = new ArrayList<>();
        try {
            diagonalChangerThreads = threadCreator.create(path);
        } catch (ServiceException e) {
            viewer.printResponse(e.getMessage());
        }
            ExecutorService executorService = Executors.newFixedThreadPool(diagonalChangerThreads.size());
            diagonalChangerThreads.forEach(executorService::execute);
            executorService.shutdown();
        try {
            executorService.awaitTermination(timeoutInMilliseconds, TimeUnit.MILLISECONDS);
            LOGGER.info("\n" + matrix + " was updated.");
        } catch (InterruptedException e) {
            viewer.printResponse(e.getMessage());
        }
    }
}
