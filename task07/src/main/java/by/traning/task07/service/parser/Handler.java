package by.traning.task07.service.parser;

import by.traning.task07.bean.Component;

/**
 * interface that implements the template Chain of Responsibility
 */
public interface Handler {
    void handleSplit(Component component, String content);
}
