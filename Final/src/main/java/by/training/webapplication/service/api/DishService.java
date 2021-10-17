package by.training.webapplication.service.api;

import by.training.webapplication.bean.Dish;
import by.training.webapplication.service.exception.ServiceException;
import by.training.webapplication.service.exception.ValidatorException;

import java.util.List;

public interface DishService {
    List<Dish> showAllDishes() throws ServiceException;
    List<Dish> sortByPriceIncrease() throws ServiceException;
    List<Dish> sortByPriceDecrease() throws ServiceException;
    Dish findDishById (String dishId) throws ServiceException, ValidatorException;
    Dish findDishByName (String name) throws ServiceException, ValidatorException;
    void deleteDish (int id) throws ServiceException;
    void addDish (String name, String weight, String price, String description, String picture) throws ServiceException, ValidatorException;;
    void changeName(String newName, int dishId) throws ServiceException, ValidatorException;;
    void changePicture(String newPicture, int dishId) throws ServiceException, ValidatorException;;
    void changeDescription(String newDescription, int dishId) throws ServiceException, ValidatorException;;
    void changeWeight(String newWeight, int dishId) throws ServiceException, ValidatorException;;
    void changePrice(String newPrice, int dishId) throws ServiceException, ValidatorException;;
    List<Dish> getSortDishList (String value, String increase, String decrease) throws ServiceException;
}
