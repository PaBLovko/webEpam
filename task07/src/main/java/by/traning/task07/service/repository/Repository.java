package by.traning.task07.service.repository;

import by.traning.task07.bean.Component;

import java.util.Map;

/**
 * Repository template class
 */
public interface Repository {

    /**
     * getting an element
     * @param key the element key
     */
    Component readElement(Integer key);

    /**
     * adding an element
     * @param text the element to be added
     */
    void addTextObject(Component text);

    /**
     * receive text.txt collection
     * @return collection
     */
    Map<Integer, Component> receiveTextCollection();
}