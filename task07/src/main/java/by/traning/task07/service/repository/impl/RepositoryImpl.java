package by.traning.task07.service.repository.impl;

import by.traning.task07.bean.Component;
import by.traning.task07.service.repository.Repository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.TreeMap;

public class RepositoryImpl implements Repository {
    private static Logger logger = LogManager.getLogger(RepositoryImpl.class);
    private Map<Integer, Component> repository = new TreeMap<>();
    private static final String PARAM = "parameter is {}";
    private static final String RESULT = "return value is {}";

    @Override
    public Component readElement(Integer key) {
        logger.debug(PARAM, key);
        Component component = repository.get(key);
        logger.info(RESULT, component);
        return component;
    }

    @Override
    public void addTextObject(Component text) {
        logger.debug(PARAM, text);
        int id = repository.size();
        repository.put(id, text);
        logger.info(RESULT, repository);
    }

    @Override
    public Map<Integer, Component> receiveTextCollection() {
        logger.info(RESULT, repository);
        return repository;
    }
}
