package by.traning.task03a.dao.impl;

import by.traning.task03a.bean.File;
import by.traning.task03a.dao.MatrixDAO;
import by.traning.task03a.dao.exception.DAOException;
import lombok.NonNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The responsible for the DAO {@link File File}
 */
public class FileMatrixDAO implements MatrixDAO<Integer> {

    private static Logger logger = LogManager.getLogger(FileMatrixDAO.class);

    @Override
    public List<List<Integer>> read(@NonNull File file) throws DAOException {
        logger.debug(String.format("The method is invoked, file = %s", file));
        List<List<String>> sendData = new ArrayList<>();
        List<List<Integer>> result;
        try {
            List<String> allLines = Files.readAllLines(file.getFile().toPath());
            for (String string : allLines){
                sendData.add(Arrays.asList(string.split("\\s")));
            }
            result = sendData.stream().map(t -> t.stream().map(Integer::parseInt).
                    collect(Collectors.toList())).collect(Collectors.toList());
        }catch (IOException e){
            logger.error(String.format("The method is exception.The file %s did not read", file));
            throw new DAOException("The file did not read", e);
        }catch (Exception e){
            logger.error(String.format("The method is exception.The file %s contains wrong numbers",
                    file));
            throw new DAOException("The file contains wrong numbers", e);
        }
        logger.info(String.format("The method worked correctly, result = %s", result));
        return result;
    }

    @Override
    public void write(@NonNull List<List<Integer>> matrix, @NonNull File file) throws DAOException {
        logger.debug(String.format("The method is invoked, matrix = %s, file = %s",matrix, file));
        try {
            Files.newInputStream(file.getFile().toPath() , StandardOpenOption.TRUNCATE_EXISTING);
            for (List<Integer> array : matrix){
                Files.write(file.getFile().toPath(), Collections.singleton(array.toString()),
                        StandardOpenOption.APPEND);
            }
        }catch (IOException e){
            logger.error(String.format("The method is exception. The matrix %s didn't write", matrix));
            throw new DAOException("The array did not write", e);
        }
        logger.info("The method worked correctly");
    }
}
