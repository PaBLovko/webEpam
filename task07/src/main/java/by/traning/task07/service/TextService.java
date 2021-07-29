package by.traning.task07.service;

import by.traning.task07.bean.Component;
import by.traning.task07.service.exception.ServiceException;

import java.util.Map;

public interface TextService {
    Map<Integer, Component> receiveTextCollection();

    Component createTree(String pathname) throws ServiceException;

    String joinTree(Component component);
}
