package by.training.webapplication.service.api;

import by.training.webapplication.bean.BasketProduct;
import by.training.webapplication.service.exception.ServiceException;
import by.training.webapplication.service.exception.ValidatorException;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    void deleteOrderById(int orderId) throws ServiceException;
    void changePreparationTime(String newDate, int orderId) throws ServiceException, ValidatorException;
    void changeDeliveryTime(String newDate, int orderId) throws ServiceException,  ValidatorException;
    void changeStatus(String newStatus, int orderId) throws ServiceException, ValidatorException;
    int findOrderPageAmount (int pageAmount) throws ServiceException;
    int findOrderByUserIdPageAmount (int pageAmount, int userId) throws ServiceException;
    int findOrdersAmount() throws ServiceException;
    int findOrdersCost() throws ServiceException;
    void saveOrder (int userId, double total, LocalDateTime preparationTime, LocalDateTime deliveryTime, String status,
                    List<BasketProduct> basketProducts, String userLogin, double newTotal) throws ServiceException;
}
