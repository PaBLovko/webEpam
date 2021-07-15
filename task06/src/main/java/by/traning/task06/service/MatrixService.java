package by.traning.task06.service;

import by.traning.task06.service.exception.ServiceException;

/**
 * The interface containing the calculate methods
 */
public interface MatrixService {
    void matrixMultiply() throws ServiceException;
    void matrixSummation() throws ServiceException;
    void matrixSubtraction() throws ServiceException;
}
