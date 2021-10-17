package by.training.webapplication.dao.api;

import by.training.webapplication.dao.exception.DaoException;
import by.training.webapplication.bean.Entity;

import java.util.List;

public interface Dao<T extends Entity> {
    T findById(String id) throws DaoException;
    List<T> findAll() throws DaoException;
    void save(Object... parameters) throws DaoException;
    void removeById(int id) throws DaoException;
    int findAmount() throws DaoException;
    List<T> findLimit(int start, int amount) throws DaoException;
}
