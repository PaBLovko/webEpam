package by.traning.task03a.service;

import by.traning.task03a.service.exception.ServiceException;

/**
 * The interface containing the calculate methods
 */
public interface MatrixService {
    void matrixMultiply() throws ServiceException;
    void matrixSummation() throws ServiceException;
    void matrixSubtraction() throws ServiceException;
}
