package by.traning.task07.service;

import by.traning.task07.bean.Component;
import by.traning.task07.service.exception.ServiceException;

import java.util.Map;

/**
 * an interface that implements working with text
 */
public interface TextService {
    /**
     *
     * @return
     */
    Map<Integer, Component> receiveTextCollection();

    /**
     * creating a component tree
     * @param pathname file path
     * @return the component tree
     * @throws ServiceException when there are problems with the file
     */
    Component createTree(String pathname) throws ServiceException;
}
