package by.traning.task06.option4.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReaderDAO {
    private static Logger log = LogManager.getLogger(ReaderDAO.class);

    public List<String> readLines(String path) throws DAOException {
        List<String> lines;
        Path source = Paths.get(path);
        try (Stream<String> lineStream = Files.lines(source)) {
            lines = lineStream.collect(Collectors.toList());
        } catch (IOException e) {
            log.error("File was not read");
            throw new DAOException("File reading problems", e);
        }
        return lines;
    }
}
