package by.traning.task07.service;

import by.traning.task07.bean.Component;
import by.traning.task07.service.exception.ServiceException;

import java.util.List;

/**
 * the interface implements sorting
 */
public interface SortService {
    /**
     * sort Paragraphs
     * @param component Component
     * @return component sorted by paragraphs
     */
    List<Component> sortParagraphs(Component component);

    /**
     * sort Words
     * @param component sentence
     * @return sorted sentence in the form of a list
     */
    List<Component> sortWords(Component component);

    /**
     * sort Lexemes
     * @param component component
     * @param symbol symbol
     * @return sorted lexemes in the form of a list
     * @throws ServiceException when there are more than one characters
     */
    List<Component> sortLexemes(Component component, String symbol) throws ServiceException;
}
