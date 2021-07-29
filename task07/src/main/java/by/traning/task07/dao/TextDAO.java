package by.traning.task07.dao;


import by.traning.task07.dao.exception.DAOException;

import java.util.List;

/**
 * The interface that implements working with data source
 */
public interface TextDAO {

    /**
     * The method for read the contents of a file
     * @param pathname file path
     * @return The text.txt that is stored in the file
     * @throws DAOException when file did not read
     */
    List<String> readFile(String pathname) throws DAOException;
}

