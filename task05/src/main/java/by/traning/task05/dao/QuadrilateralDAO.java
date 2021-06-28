package by.traning.task05.dao;

import by.traning.task05.bean.Quadrilateral;
import by.traning.task05.dao.exception.DAOException;

import java.util.List;

/**
 * The interface that implements working with data source
 */
public interface QuadrilateralDAO {
    /**
     * The method for read the contents of a file
     * @param url file path
     * @return The text that is stored in the file
     * @throws DAOException when file did not read
     */
    List<String> read(String url) throws DAOException;

    /**
     * The method for write the contents of a file
     * @param quadrilateralList the data that need to write
     * @param url file path
     * @throws DAOException when data did not write
     */
    void save(List<Quadrilateral> quadrilateralList, String url) throws DAOException;
}
