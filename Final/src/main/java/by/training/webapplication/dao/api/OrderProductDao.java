package by.training.webapplication.dao.api;

import by.training.webapplication.bean.OrderProduct;
import by.training.webapplication.dao.exception.DaoException;

import java.util.List;

public interface OrderProductDao extends Dao<OrderProduct> {
    OrderProduct findOrderProductById(int orderProductId) throws DaoException;
    List<OrderProduct> findLimitOrderProduct(int start, int amount) throws DaoException;
    List<OrderProduct> findLimitOrderProductByUserId(int userId, int start, int amount) throws DaoException;
}
