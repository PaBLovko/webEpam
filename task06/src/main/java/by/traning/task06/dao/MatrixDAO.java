package by.traning.task06.dao;

import by.traning.task06.bean.FileData;
import by.traning.task06.dao.exception.DAOException;
import lombok.NonNull;

import java.util.List;

public interface MatrixDAO <K extends Number>{

    /**
     * The method for read the contents of a file
     * @param fileData The text file
     * @return The text that is stored in the file
     * @throws DAOException when file did not read
     */
    List<List<K>> read(@NonNull final FileData fileData) throws DAOException;

    /**
     * The method for write the contents of a file
     * @param matrix the data that need to write
     * @param fileData The text file
     * @throws DAOException when file did not write
     */
    void write(@NonNull final List<List<K>> matrix, @NonNull final FileData fileData) throws DAOException;
}
