package by.traning.task07.service.impl;

import by.traning.task07.bean.Component;
import by.traning.task07.bean.Composite;
import by.traning.task07.bean.Type;
import by.traning.task07.dao.TextDAO;
import by.traning.task07.dao.exception.DAOException;
import by.traning.task07.dao.factory.DAOFactory;
import by.traning.task07.service.exception.ServiceException;
import by.traning.task07.service.TextService;
import by.traning.task07.service.parser.*;
import by.traning.task07.service.parser.impl.*;
import by.traning.task07.service.repository.Repository;
import by.traning.task07.service.repository.impl.RepositoryImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

public class TextServiceImpl implements TextService {
    private Repository repository = new RepositoryImpl();
    private static Logger logger = LogManager.getLogger(TextServiceImpl.class);
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
    public Map<Integer, Component> receiveTextCollection() {
        logger.debug(METHOD_IS_INVOKED);
        Map<Integer, Component> result = repository.receiveTextCollection();
        logger.info(THE_METHOD_WORKED_CORRECTLY);
        return result;
    }

    @Override
    public Component createTree(String pathname) throws ServiceException {
        logger.debug(METHOD_IS_INVOKED);
        DAOFactory factory = DAOFactory.getInstance();
        TextDAO textDAO = factory.getFileTextDAO();
        List<String> strings;
        try {
            strings = textDAO.readFile(pathname);
        } catch (DAOException e) {
            logger.error(ERROR_FIND);
            throw new ServiceException(e);
        }
        Composite text = new Composite(Type.TEXT);

        StringBuilder stringBuilder = new StringBuilder();
        for (String string : strings) {
            stringBuilder.append(string).append("\n");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        String content = stringBuilder.toString();
        Handler characterParser = new CharacterParser();
        Handler markParser = new MarkParser(characterParser);
        Handler wordParser = new WordParser(characterParser);
        Handler lexemeParser = new LexemeParser(wordParser, markParser);
        Handler sentenceParser = new SentenceParser(lexemeParser);
        Handler parser = new ParagraphParser(characterParser);
        parser.handleSplit(text, content);
        repository.addTextObject(text);
        logger.info(THE_METHOD_WORKED_CORRECTLY);
        return text;
    }
}
