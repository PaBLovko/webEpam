package by.training.webapplication.dao;

import by.training.webapplication.dao.connection.ConnectionPool;

public class DaoHelperFactory {
 public DaoHelper create() {
     return new DaoHelper(ConnectionPool.getInstance());
 }
}
