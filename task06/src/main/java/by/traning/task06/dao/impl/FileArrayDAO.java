package by.traning.task06.dao.impl;

import by.traning.task06.bean.FileData;
import by.traning.task06.dao.ArrayDAO;
import by.traning.task06.dao.exception.DAOException;
import lombok.NonNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class FileArrayDAO implements ArrayDAO<Integer> {

    private static Logger logger = LogManager.getLogger(FileArrayDAO.class);

    @Override
    public List<Integer> read(@NonNull FileData fileData) throws DAOException {
        logger.debug(String.format("The method is invoked, file = %s", fileData));
        List<Integer> integerList;
        try {
            List<String> allLines = Files.readAllLines(fileData.getFile().toPath());
            integerList = allLines.stream().map(Integer::parseInt).collect(Collectors.toList());
        }catch (IOException e){
            logger.error(String.format("The method is exception.The file %s did not read", fileData));
            throw new DAOException("The file did not read", e);
        }catch (Exception e){
            logger.error(String.format("The method is exception.The file %s contains more than just numbers",
                    fileData));
            throw new DAOException("The file contains more than just numbers", e);
        }
        logger.info(String.format("The method worked correctly, integerList = %s", integerList));
        return integerList;
    }

    @Override
    public void write(@NonNull List<Integer> array, @NonNull FileData fileData) throws DAOException {
        logger.debug(String.format("The method is invoked, array = %s, file = %s",array, fileData));
        try {
            Files.write(fileData.getFile().toPath(), Collections.singleton(array.toString()), StandardOpenOption.APPEND);
        } catch (IOException e) {
            logger.error(String.format("The method is exception. The array %s didn't write", array));
            throw new DAOException("The array did not write", e);
        }
        logger.info("The method worked correctly");
    }
}
