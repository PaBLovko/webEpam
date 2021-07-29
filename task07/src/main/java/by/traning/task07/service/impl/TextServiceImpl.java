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

import java.util.List;
import java.util.Map;

public class TextServiceImpl implements TextService {
    private Repository repository = new RepositoryImpl();

    @Override
    public Map<Integer, Component> receiveTextCollection() {
        Map<Integer, Component> result = repository.receiveTextCollection();
        return result;
    }

    @Override
    public String joinTree(Component component) {
        String temp = component.collect();
        String result = temp.substring(0, temp.length() - 1);
        return result;
    }

    @Override
    public Component createTree(String pathname) throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        TextDAO textDAO = factory.getFileTextDAO();
        List<String> strings;
        try {
            strings = textDAO.readFile(pathname);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        Composite text = new Composite(Type.TEXT);

        StringBuilder stringBuilder = new StringBuilder();
        for (String string : strings) {
            stringBuilder.append(string).append("\n");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        String content = stringBuilder.toString();
        Handler parser6 = new CharacterParser();
        Handler parser5 = new MarkParser(parser6);
        Handler parser4 = new WordParser(parser6);
        Handler parser3 = new LexemeParser(parser4, parser5);
        Handler parser2 = new SentenceParser(parser3);
        Handler parser = new ParagraphParser(parser2);
        parser.handleSplit(text, content);
        repository.addTextObject(text);
        return text;
    }
}
