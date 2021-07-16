package by.traning.task06.option1.service;

import by.traning.task06.option1.dao.DataFileReader;
import by.traning.task06.option1.service.validator.ThreadValidator;
import by.traning.task06.option1.service.exception.ServiceException;
import by.traning.task06.option1.dao.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.stream.Collectors;

public class ThreadCreator {
    private static Logger LOGGER = LogManager.getLogger();

    public List<DiagonalChangerThread> create(String path) throws ServiceException{
        DataFileReader dataFileReader = new DataFileReader();
        ThreadValidator threadValidator = new ThreadValidator();
        List<String> matrixDigits = new ArrayList<>();
        try {
            matrixDigits = dataFileReader.read(path)
                    .stream()
                    .map(String::trim)
                    .collect(Collectors.toList());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        List<DiagonalChangerThread> diagonalChangers = new ArrayList<>();
        if (threadValidator.validate(matrixDigits)) {
            Phaser phaser = new Phaser();
            diagonalChangers = matrixDigits
                    .stream()
                    .map(Integer::parseInt)
                    .map(d -> new DiagonalChangerThread(d, phaser))
                    .collect(Collectors.toList());
        } else {
            LOGGER.warn("Input data for threads is incorrect.");
        }
        return diagonalChangers;
    }
}
