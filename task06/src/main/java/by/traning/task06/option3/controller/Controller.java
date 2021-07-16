package by.traning.task06.option3.controller;


import by.traning.task06.option3.bean.Matrix;
import by.traning.task06.option3.service.factory.Factory;
import by.traning.task06.option3.service.exception.ServiceException;
import by.traning.task06.option3.view.View;

import java.util.concurrent.Semaphore;

public class Controller {
    public void execute(String path) {
        Semaphore sem = new Semaphore(1);
        View viewer = new View();
        Factory serviceFactory = Factory.getInstance();
        Matrix matrix = null;
        try {
            matrix = serviceFactory.getMatrixService().createMatrix(sem, path);
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
            serviceFactory.getThreadService().startThreads(threadCount, matrix, sem);
        } catch (ServiceException e) {
            viewer.printResponse(e.getMessage());
        }
    }
}
