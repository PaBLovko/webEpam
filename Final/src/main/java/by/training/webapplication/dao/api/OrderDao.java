package by.training.webapplication.dao.api;

import by.training.webapplication.dao.exception.DaoException;
import by.training.webapplication.bean.Order;

import java.time.LocalDateTime;

public interface OrderDao extends Dao<Order> {
    Order findLastUserOrderById(int userId) throws DaoException;
    void changePreparationTime(LocalDateTime newDate, int orderId) throws DaoException;
    void changeDeliveryTime(LocalDateTime newDate, int orderId) throws DaoException;
    void changeStatus(String newStatus, int orderId) throws DaoException;
    void changeTotal(double newTotal, int orderId) throws DaoException;
    int findAmountOrdersByUserId (int userId) throws DaoException;
    int findOrdersAmount () throws DaoException;
    int findOrdersCost () throws DaoException;
}
