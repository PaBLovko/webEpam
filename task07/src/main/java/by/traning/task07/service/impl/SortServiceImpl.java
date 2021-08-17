package by.traning.task07.service.impl;

import by.traning.task07.bean.Component;
import by.traning.task07.bean.Composite;
import by.traning.task07.bean.Type;
import by.traning.task07.service.SortService;
import by.traning.task07.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;

public class SortServiceImpl implements SortService {
  private static Logger logger = LogManager.getLogger(SortServiceImpl.class);

    /**
     * The string literal describing that method is invoked
     */
    private static final String METHOD_IS_INVOKED = "The method is invoked";

    /**
     * The string literal describing that method worked correctly
     */
    private static final String THE_METHOD_WORKED_CORRECTLY = "The method worked correctly";

    /**
     * The string literal describing that method is exception
     */
    private static final String ERROR_FIND = "Error during found";

    @Override
    public List<Component> sortWords(Component component) {
        logger.debug(METHOD_IS_INVOKED);
        List<Component> components = ((Composite) component).getByType(Type.WORD);
        Comparator<Component> comparator = Comparator.comparingInt(o -> ((Composite) o).getComponents().size());
        components.sort(comparator);
        logger.info(THE_METHOD_WORKED_CORRECTLY);
        return components;
    }

    @Override
    public List<Component> sortLexemes(Component component, String symbol) throws ServiceException {
        logger.debug(METHOD_IS_INVOKED);
        if (symbol.length() > 1) {
            logger.error(ERROR_FIND);
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
        logger.info(THE_METHOD_WORKED_CORRECTLY);
        return components;
    }

    @Override
    public List<Component> sortParagraphs(Component component) {
        logger.debug(METHOD_IS_INVOKED);
        List<Component> components = ((Composite) component).getByType(Type.PARAGRAPH);
        Comparator<Component> comparator = Comparator.comparingInt(o -> ((Composite) o).getComponents().size());
        components.sort(comparator);
        logger.info(THE_METHOD_WORKED_CORRECTLY);
        return components;
    }
}