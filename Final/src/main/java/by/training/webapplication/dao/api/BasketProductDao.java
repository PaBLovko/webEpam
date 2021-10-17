package by.training.webapplication.dao.api;

import by.training.webapplication.dao.exception.DaoException;
import by.training.webapplication.bean.BasketProduct;

import java.util.List;

public interface BasketProductDao extends Dao<BasketProduct> {
    void removeByBasketId(int basketId) throws DaoException;
    List<BasketProduct> findByBasketId(int basketId) throws DaoException;
    void changeBasketByBasketId(Object... parameters) throws DaoException;
}
