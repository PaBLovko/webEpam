package by.training.webapplication.dao;
import by.training.webapplication.dao.connection.ConnectionPool;
import by.training.webapplication.dao.connection.ProxyConnection;
import by.training.webapplication.dao.impl.*;
import by.training.webapplication.dao.exception.DaoException;


import java.sql.SQLException;

public class DaoHelper implements AutoCloseable{
    private ProxyConnection connection;

    public DaoHelper(ConnectionPool connectionPool) {
        this.connection = connectionPool.getConnection();
    }
    public UserDaoImpl createUserDao() {
        return new UserDaoImpl(connection);
    }
    public DishDaoImpl createDishDao(){return new DishDaoImpl(connection);}

    public CommentDaoImpl createCommentDao(){
        return new CommentDaoImpl(connection);
    }

    public OrderDaoImpl createOrderDao() {
        return new OrderDaoImpl(connection);
    }
    public OrderProductDaoImpl createOrderProductDao() {
        return new OrderProductDaoImpl(connection);
    }

    public BasketDaoImpl createBasketDao() {
        return new BasketDaoImpl(connection);
    }

    public BasketProductDaoImpl createBasketProductDao() {
        return new BasketProductDaoImpl(connection);
    }

    @Override
    public void close() throws DaoException {
        try {
            connection.setAutoCommit(true);
            connection.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void startTransaction() throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
    public void endTransaction() throws DaoException {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void backTransaction() throws DaoException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}

