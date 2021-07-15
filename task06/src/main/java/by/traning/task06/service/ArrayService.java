package by.traning.task06.service;

import by.traning.task06.service.exception.ServiceException;

/**
 * The interface containing the sort methods
 */
public interface ArrayService{
    void arraySortBubble() throws ServiceException;
    void arraySortShaker() throws ServiceException;
    void arraySortSelection() throws ServiceException;
    void arraySortInsertion() throws ServiceException;
    void arraySortHashing() throws ServiceException;
    void arraySortMerge() throws ServiceException;
    void arraySortShell() throws ServiceException;
}
