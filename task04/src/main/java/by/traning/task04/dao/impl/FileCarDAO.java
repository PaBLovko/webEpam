package by.traning.task04.dao.impl;

import by.traning.task04.dao.CarDAO;
import by.traning.task04.dao.exception.DAOException;
import lombok.NonNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;

public class FileCarDAO implements CarDAO {

    private static Logger logger = LogManager.getLogger(FileCarDAO.class);

    @Override
    public List<String> read(@NonNull File file) throws DAOException {
        logger.debug(String.format("The method is invoked, file = %s", file));
        List<String> result;
        try {
            result = Files.readAllLines(file.toPath());
        }catch (IOException e){
            logger.error(String.format("The method is exception.The file %s did not read", file));
            throw new DAOException("The file did not read", e);
        }catch (Exception e){
            logger.error(String.format("The method is exception.The file %s contains wrong numbers", file));
            throw new DAOException("The file contains wrong numbers", e);
        }
        logger.info(String.format("The method worked correctly, result = %s", result));
        return result;
    }

    @Override
    public void write(@NonNull List<List<String>> listList, @NonNull File file) throws DAOException {
        logger.debug(String.format("The method is invoked, cars = %s, file = %s", listList, file));
        try {
            Files.newInputStream(file.toPath() , StandardOpenOption.TRUNCATE_EXISTING);
            for (List<String> car : listList){
                Files.write(file.toPath(), Collections.singleton(car.toString()),
                        StandardOpenOption.APPEND);
            }
        }catch (IOException e){
            logger.error(String.format("The method is exception. The matrix %s didn't write", listList));
            throw new DAOException("The array did not write", e);
        }
        logger.info("The method worked correctly");
    }
}
