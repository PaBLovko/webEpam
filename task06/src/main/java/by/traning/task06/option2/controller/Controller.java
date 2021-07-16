package by.traning.task06.option2.controller;



import by.traning.task06.option2.bean.Matrix;
import by.traning.task06.option2.service.factory.Factory;
import by.traning.task06.option2.service.exception.ServiceException;
import by.traning.task06.option2.view.View;

import java.util.concurrent.locks.ReentrantLock;

public class Controller {
    public void execute(String path) {
        ReentrantLock locker = new ReentrantLock();
        View viewer = new View();
        Factory serviceFactory = Factory.getInstance();
        Matrix matrix = null;
        try {
            matrix = serviceFactory.getMatrixService().createMatrix(locker, path);
        } catch (ServiceException e) {
            viewer.printResponse(e.getMessage());
        }
        int threadCount = 0;
        try {
            threadCount = serviceFactory.getThreadService().returnThreadsCount(path);
        } catch (ServiceException e) {
            viewer.printResponse(e.getMessage());
        }
        try {
            serviceFactory.getThreadService().startThreads(threadCount, matrix, locker);
        } catch (ServiceException e) {
            viewer.printResponse(e.getMessage());
        }
    }
}
