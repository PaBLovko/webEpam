package by.traning.task07.service;

import by.traning.task07.bean.Component;
import by.traning.task07.service.exception.ServiceException;

import java.util.List;

public interface SortService {
    List<Component> sortParagraphs(Component component);

    List<String> sortWords(String sentence);

    List<Component> sortLexemes(Component component, String symbol) throws ServiceException;
}
