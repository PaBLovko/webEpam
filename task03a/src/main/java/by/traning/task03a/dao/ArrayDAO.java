package by.traning.task03a.dao;

import by.traning.task03a.bean.File;
import by.traning.task03a.dao.exception.DAOException;
import lombok.NonNull;

import java.util.List;

/**
 * The interface that implements working with data source
 */
public interface ArrayDAO <K extends Number>{

    /**
     * The method for read the contents of a file
     * @param file The text file
     * @return The text that is stored in the file
     * @throws DAOException when file did not read
     */
    List<K> read(@NonNull final File file) throws DAOException;

    /**
     * The method for write the contents of a file
     * @param matrix the data that need to write
     * @throws DAOException when data did not write
     */
    void write(@NonNull final List<K>  matrix, @NonNull File file) throws DAOException;
}
