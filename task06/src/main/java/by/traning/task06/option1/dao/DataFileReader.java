package by.traning.task06.option1.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataFileReader {

    private static Logger LOGGER = LogManager.getLogger(DataFileReader.class);

    public List<String> read(String filePath) throws DAOException {
        if (filePath == null || !Paths.get(filePath).toFile().exists()) {
            LOGGER.fatal(filePath + " doesn't exists.");
            throw new DAOException(filePath + " doesn't exists.");
        }
        try (Stream<String> lines = Files.newBufferedReader(Paths.get(filePath)).lines()) {
            LOGGER.info("File at " + filePath + " was read.");
            return lines.map(l -> l.trim().concat(" ")).collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.fatal("Can't read file.");
            throw new DAOException("Can't read file.");
        }
    }
}