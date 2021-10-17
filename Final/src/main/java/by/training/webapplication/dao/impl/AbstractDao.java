package by.training.webapplication.dao.impl;

import by.training.webapplication.dao.api.Dao;
import by.training.webapplication.dao.exception.DaoException;
import by.training.webapplication.bean.Entity;
import by.training.webapplication.dao.mapper.RowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<T extends Entity> implements Dao<T> {
    private Connection connection;
    private static final String FIND_ALL = "SELECT * FROM ";
    private static final String REMOVE_BY_ID = "DELETE FROM ";
    private static final String WHERE = " WHERE ";
    private static final String FIND_COUNT = "SELECT COUNT(*) AS amount FROM ";
    private static final String AMOUNT = "amount";
    private static final String LIMIT = " LIMIT ? , ?";

    AbstractDao(Connection connection) {
        this.connection = connection;
    }

    List<T> executeQuery(String query, RowMapper<T> mapper, Object... params) throws DaoException {
        List<T> entities = new ArrayList<>();
        try (PreparedStatement statement = createStatement(query, params);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                T entity = mapper.map(resultSet);
                entities.add(entity);
            }
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
        return entities;
    }

    int executeQuery(String query, String column, Object... params) throws DaoException {
        try (PreparedStatement statement = createStatement(query, params);
             ResultSet resultSet = statement.executeQuery()) {
            resultSet.next();
            return resultSet.getInt(column);
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
    }
    void executeUpdate(String query, Object... params) throws DaoException {
        try (PreparedStatement statement = createStatement(query, params)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    PreparedStatement createStatement(String query, Object... params) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        int arrayLength = params.length;
        for (int i = 1; i <= arrayLength; i++) {
            statement.setObject(i, params[i - 1]);
        }
        return statement;
    }
    T executeForSingleResult(String query, RowMapper<T> builder, Object... params) throws DaoException {
        List<T> items = executeQuery(query, builder, params);
        if (items.size() == 1) {
            return items.get(0);
        } else if (items.size() > 1) {
            throw new DaoException("More than one record found");
        } else {
            throw new DaoException("without record");
        }
    }


    @Override
    public List<T> findAll() throws DaoException {
        String table = getTableName();
        RowMapper<T> mapper = (RowMapper<T>) RowMapper.create(table);
        return executeQuery(FIND_ALL + table, mapper);
    }

    @Override
    public void removeById(int id) throws DaoException {
        String table = getTableName();
        String idName = getIdName();
        String query = REMOVE_BY_ID + table + WHERE + idName + " = ?";
        executeUpdate(query, id);
    }
    @Override
    public T findById(String id) throws DaoException {
        String table = getTableName();
        String idName = getIdName();
        RowMapper<T> mapper = (RowMapper<T>) RowMapper.create(table);
        String query = FIND_ALL + table + WHERE + idName + " =? ";
        return executeForSingleResult(query, mapper, id);
    }
    @Override
    public int findAmount () throws DaoException{
        String table = getTableName();
        String query = FIND_COUNT + table;
        return executeQuery(query, AMOUNT);
    }

    @Override
    public List<T> findLimit(int start, int amount) throws DaoException {
        String table = getTableName();
        RowMapper<T> mapper = (RowMapper<T>) RowMapper.create(table);

        return executeQuery(FIND_ALL + table + LIMIT, mapper, start, amount);
    }
    protected abstract String getTableName();
    protected abstract String getIdName();
}
