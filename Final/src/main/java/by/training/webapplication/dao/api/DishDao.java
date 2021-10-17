package by.training.webapplication.dao.api;

import by.training.webapplication.bean.Dish;
import by.training.webapplication.dao.exception.DaoException;

import java.util.List;

public interface DishDao extends Dao<Dish>{
    List<Dish> sortByIncreasePrice() throws DaoException;
    List<Dish> sortByDecreasePrice() throws DaoException;
    Dish findByName(String name) throws DaoException;
    void changeName(String newName, int dishId) throws DaoException;
    void changePicture(String newPicture, int dishId) throws DaoException;
    void changeDescription(String newDescription, int dishId) throws DaoException;
    void changeWeight(String newWeight, int dishId) throws DaoException;
    void changePrice(String newPrice, int dishId) throws DaoException;
}
