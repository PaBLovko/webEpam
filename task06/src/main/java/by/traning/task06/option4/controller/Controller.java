package by.traning.task06.option4.controller;


import by.traning.task06.option4.bean.Matrix;
import by.traning.task06.option4.service.factory.Factory;
import by.traning.task06.option4.service.exception.ServiceException;
import by.traning.task06.option4.view.View;

public class Controller {
    public void execute(String path) {
        View viewer = new View();
        Factory serviceFactory = Factory.getInstance();
        Matrix matrix = null;
        try {
            matrix = serviceFactory.getMatrixService().createMatrix(path);
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
            serviceFactory.getThreadService().startThreads(threadCount, matrix);
        } catch (ServiceException e) {
            viewer.printResponse(e.getMessage());
        }
    }
}
