package by.traning.task07.service.impl;

import by.traning.task07.bean.Component;
import by.traning.task07.bean.Composite;
import by.traning.task07.bean.Type;
import by.traning.task07.service.SortService;
import by.traning.task07.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SortServiceImpl implements SortService {
  private final Logger logger = LogManager.getLogger();

    @Override
    public List<String> sortWords(String sentence) {
        String regex = "[.,! '?]+";
        List<String> words = Arrays.asList(sentence.split(regex));
        Comparator<String> comparator = Comparator.comparingInt(String::length);
        words.sort(comparator);
        return words;
    }

    @Override
    public List<Component> sortLexemes(Component component, String symbol) throws ServiceException {
        if (symbol.length() > 1) {
            throw new ServiceException("char consists more than one symbol");
        }
        List<Component> components = ((Composite) component).getByType(Type.LEXEME);
        Comparator<Component> comparator = (o1, o2) -> {
            String strO1 = String.valueOf(o1.collect());
            String strO2 = String.valueOf(o2.collect());
            long countO1 = strO1.chars().filter(c -> c == symbol.charAt(0)).count();
            long countO2 = strO2.chars().filter(c -> c == symbol.charAt(0)).count();
            int difference = (int) (countO2 - countO1);
            if (difference == 0) {
                for (int i = 0; i < strO1.length() && i < strO2.length(); i++) {
                    if (strO1.toUpperCase().charAt(i) != strO2.toUpperCase().charAt(i)) {
                        if (strO1.charAt(i) > strO2.charAt(i)) {
                            return 1;
                        } else {
                            return -1;
                        }
                    }
                }
            }
            return difference;
        };
        components.sort(comparator);
        return components;
    }

    @Override
    public List<Component> sortParagraphs(Component component) {
        List<Component> components = ((Composite) component).getByType(Type.PARAGRAPH);
        Comparator<Component> comparator = Comparator.comparingInt(o -> ((Composite) o).getComponents().size());
        components.sort(comparator);
        return components;
    }
}