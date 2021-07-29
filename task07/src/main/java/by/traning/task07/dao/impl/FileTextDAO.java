package by.traning.task07.dao.impl;

import by.traning.task07.dao.TextDAO;
import by.traning.task07.dao.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileTextDAO implements TextDAO {
    private final Logger logger = LogManager.getLogger(FileTextDAO.class);

    @Override
    public List<String> readFile(String pathname) throws DAOException {
        logger.debug("parameter is {}", pathname);
        List<String> strings = new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream(pathname);
             BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream))) {
            if (fileInputStream.available() == 0) {
                throw new DAOException("File is empty");
            }
            while (reader.ready()) {
                strings.add(reader.readLine());
            }
        } catch (IOException e) {
            throw new DAOException(e);
        }
        logger.info("return value is {}", strings);
        return strings;
    }
}
