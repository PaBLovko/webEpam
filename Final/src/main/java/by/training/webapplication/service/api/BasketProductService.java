package by.training.webapplication.service.api;

import by.training.webapplication.service.exception.ServiceException;
import by.training.webapplication.bean.BasketProduct;
import by.training.webapplication.service.exception.ValidatorException;

import java.util.List;

public interface BasketProductService {
    void saveBasketProduct(int basketId, int dishId, String amount, double price, double newTotal) throws ServiceException, ValidatorException;
    void deleteBasketProductById(int basketProductId, double newTotal, int basketId) throws ServiceException;
    List<BasketProduct> findProductByBasketId(int basketId) throws ServiceException;
    void clearBasket (int basketId, double newTotal) throws ServiceException;
}
