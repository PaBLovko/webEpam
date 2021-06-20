package by.traning.task04.dao;

import by.traning.task04.dao.exception.DAOException;
import lombok.NonNull;

import java.io.File;
import java.util.List;

/**
 * The interface that implements working with data source
 */
public interface CarDAO{

    /**
     * The method for read the contents of a file
     * @param file The text file
     * @return The text that is stored in the file
     * @throws DAOException when file did not read
     */
    List<String> read(@NonNull final File file) throws DAOException;

    /**
     * The method for write the contents of a file
     * @param listList the data that need to write
     * @throws DAOException when data did not write
     */
    void write(@NonNull final List<List<String>> listList, @NonNull File file) throws DAOException;
}
