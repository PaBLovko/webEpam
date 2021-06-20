package by.traning.task03a.service;

import by.traning.task03a.service.exception.ServiceException;

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
