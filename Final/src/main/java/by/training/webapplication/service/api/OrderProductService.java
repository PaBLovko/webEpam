package by.training.webapplication.service.api;

import by.training.webapplication.service.exception.ServiceException;
import by.training.webapplication.bean.OrderProduct;

import java.util.List;

public interface OrderProductService {
    void save(int orderId, int dishId,int amount, double cost) throws ServiceException;
    List<OrderProduct> findLimitOrderProduct(int start, int amount) throws ServiceException;
    List<OrderProduct> findLimitOrderProductByUserId(int userId, int start, int amount) throws ServiceException;
    void deleteOrderProduct(int orderProductId) throws ServiceException;
}
