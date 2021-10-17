package by.training.webapplication.service.api;

import by.training.webapplication.bean.Basket;
import by.training.webapplication.service.exception.ServiceException;

public interface BasketService {
    Basket findBasketByUserLogin (String userLogin) throws ServiceException;
}
