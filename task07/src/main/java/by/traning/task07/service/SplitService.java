package by.traning.task07.service;

import by.traning.task07.bean.Type;

import java.util.List;

/**
 * an interface that implements the division of an element into its corresponding type
 */
public interface SplitService {
    /**
     * the method that implements the division of an element into its corresponding type
     * @param element string
     * @param dest the type through which a certain template for division will be called
     * @return an element divided by a certain type
     */
    List<String> split(String element, Type dest);
}
