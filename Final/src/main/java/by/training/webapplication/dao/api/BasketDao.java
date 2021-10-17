package by.training.webapplication.dao.api;

import by.training.webapplication.dao.exception.DaoException;
import by.training.webapplication.bean.Basket;

public interface BasketDao extends Dao<Basket> {
    Basket getBasketByUserLogin(String userLogin) throws DaoException;
    void changeTotal(double newTotal, int basketId) throws DaoException;
}
