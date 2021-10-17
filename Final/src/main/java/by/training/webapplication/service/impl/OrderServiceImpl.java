package by.training.webapplication.service.impl;

import by.training.webapplication.dao.DaoHelperFactory;
import by.training.webapplication.dao.api.BasketProductDao;
import by.training.webapplication.dao.api.OrderDao;
import by.training.webapplication.service.exception.ServiceException;
import by.training.webapplication.dao.DaoHelper;
import by.training.webapplication.dao.api.BasketDao;
import by.training.webapplication.dao.api.OrderProductDao;
import by.training.webapplication.dao.exception.DaoException;
import by.training.webapplication.bean.Basket;
import by.training.webapplication.bean.BasketProduct;
import by.training.webapplication.bean.Order;
import by.training.webapplication.service.api.OrderService;
import by.training.webapplication.service.exception.ValidatorException;
import by.training.webapplication.service.validator.OrderDataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Realization of {@link OrderService} interface. Provides access to {@link OrderService},
 */
public class OrderServiceImpl implements OrderService {
    /**
     * Factory for Dao
     */
    private DaoHelperFactory daoHelperFactory;

    /**
     * Validator for data checking
     */
    private OrderDataValidator orderDataValidator = new OrderDataValidator();

    /**
     * Logger for this service
     */
    private static Logger log = LogManager.getLogger(OrderServiceImpl.class.getName());

    /**
     * Constructor
     *
     * @param daoHelperFactory dao for this server
     */
    public OrderServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    /**
     * Save order
     *
     * @param userId user's id
     * @param total order's total
     * @param preparationTime time of production dish
     * @param deliveryTime time of delivery dish
     * @param status order's status
     * @param basketProducts list of dishes from basket
     * @param userLogin user's login
     * @param newTotal new total of basket after removing list of dishes to order
     * @throws ServiceException if there is an error on DAO layer
     */
    @Override
    public void saveOrder (int userId, double total, LocalDateTime preparationTime, LocalDateTime deliveryTime, String status,
                           List<BasketProduct> basketProducts, String userLogin, double newTotal) throws ServiceException {
        log.debug("Service: saving order started.");
        try (DaoHelper helper = daoHelperFactory.create()) {
            OrderDao orderDao = helper.createOrderDao();
            OrderProductDao orderProductDao = helper.createOrderProductDao();
            BasketProductDao basketProductDao = helper.createBasketProductDao();
            BasketDao basketDao = helper.createBasketDao();
            try {
                helper.startTransaction();
                orderDao.save(userId, total, preparationTime, deliveryTime, status);
                Order order = orderDao.findLastUserOrderById(userId);
                int orderId = order.getId();
                for(BasketProduct basketProduct: basketProducts){
                    orderProductDao.save(orderId, basketProduct.getDish().getId(), basketProduct.getAmount(), basketProduct.getCost());
                }
                Basket basket = basketDao.getBasketByUserLogin(userLogin);
                int basketId = basket.getId();
                basketProductDao.removeByBasketId(basketId);
                basketDao.changeTotal(newTotal, basketId);
                helper.endTransaction();
            } catch (DaoException ex) {
                helper.backTransaction();
                throw new ServiceException(ex);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        log.debug("Service: saving order finished.");
    }

    /**
     * Delete order
     *
     * @param orderId order's id
     * @throws ServiceException if there is an error on DAO layer
     */
    @Override
    public void deleteOrderById(int orderId) throws ServiceException {
        log.debug("Service: deleting order by id started.");
        try (DaoHelper helper = daoHelperFactory.create()) {
            OrderDao dao = helper.createOrderDao();
            dao.removeById(orderId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        log.debug("Service: deleting order by id finished.");
    }

    /**
     * Change preparation time
     *
     * @param newDate date of dish production
     * @param orderId dish id
     * @throws ServiceException if there is an error on DAO layer
     * @throws ValidatorException if there are validation error
     */
    @Override
    public void changePreparationTime(String newDate, int orderId) throws ServiceException, ValidatorException {
        log.debug("Service: changing production started.");
            if(!orderDataValidator.isDateValid(newDate)){
                log.error("The entered data is not correct!");
                throw new ValidatorException("The entered data is not correct!");
            }
            LocalDateTime newLocalDateTime = LocalDateTime.parse(newDate);
            if(!orderDataValidator.isDateAfter(newLocalDateTime)){
                log.error("The entered data is not correct!");
                throw new ValidatorException("The entered data is not correct!");
            }
        try (DaoHelper helper = daoHelperFactory.create()) {
            OrderDao dao = helper.createOrderDao();
            dao.changePreparationTime(newLocalDateTime, orderId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        log.debug("Service: changing production finished.");
    }

    /**
     * Change delivery time
     *
     * @param newDate date of dish delivery
     * @param orderId dish id
     * @throws ServiceException if there is an error on DAO layer
     * @throws ValidatorException if there are validation error
     */
    @Override
    public void changeDeliveryTime(String newDate, int orderId) throws ServiceException, ValidatorException {
        log.debug("Service: changing production started.");
        if(!orderDataValidator.isDateValid(newDate)){
            log.error("The entered data is not correct!");
            throw new ValidatorException("The entered data is not correct!");
        }
        LocalDateTime newLocalDateTime = LocalDateTime.parse(newDate);
        if(!orderDataValidator.isDateAfter(newLocalDateTime)){
            log.error("The entered data is not correct!");
            throw new ValidatorException("The entered data is not correct!");
        }
        try (DaoHelper helper = daoHelperFactory.create()) {
            OrderDao dao = helper.createOrderDao();
            dao.changeDeliveryTime(newLocalDateTime, orderId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        log.debug("Service: changing production finished.");
    }

    /**
     * Change order's status
     *
     * @param newStatus order's status
     * @param orderId dish id
     * @throws ServiceException if there is an error on DAO layer
     * @throws ValidatorException if there are validation error
     */
    @Override
    public void changeStatus(String newStatus, int orderId) throws ServiceException, ValidatorException {
        log.debug("Service: changing status started.");
        if(!orderDataValidator.isStatusValid(newStatus)) {
            log.error("The entered data is not correct!");
            throw new ValidatorException("The entered data is not correct!");
        }
        try (DaoHelper helper = daoHelperFactory.create()) {
            OrderDao dao = helper.createOrderDao();
            dao.changeStatus(newStatus, orderId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        log.debug("Service: changing status finished.");
    }

    /**
     * Find number of orders
     *
     * @return number of orders
     * @throws ServiceException if there is an error on DAO layer
     */
    private int findOrderAmount () throws ServiceException{
        log.debug("Service: Getting order amount.");
        try (DaoHelper helper = daoHelperFactory.create()) {
            OrderDao dao = helper.createOrderDao();
            return dao.findAmount();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Find number of pages with orders
     *
     * @param pageAmount number of order on page
     * @return numbers of pages with orders
     * @throws ServiceException if there is an error on DAO layer
     */
    @Override
    public int findOrderPageAmount (int pageAmount) throws ServiceException{
        log.debug("Service: Getting order page amount.");
        int amountAllOrders = findOrderAmount();
        return (int) Math.ceil((double) amountAllOrders/pageAmount);
    }

    /**
     * Find number of orders by user
     *
     * @param userId user's id
     * @return numbers of orders by user
     * @throws ServiceException if there is an error on DAO layer
     */
    private int findAmountOrderByUserId (int userId) throws ServiceException{
        log.debug("Service: Getting amount order by user id.");
        try (DaoHelper helper = daoHelperFactory.create()) {
            OrderDao dao = helper.createOrderDao();
            return dao.findAmountOrdersByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Find number of pages with orders by user
     *
     * @param pageAmount number of order on page
     * @param userId user's id
     * @return numbers of pages with orders by user
     * @throws ServiceException if there is an error on DAO layer
     */
    @Override
    public int findOrderByUserIdPageAmount (int pageAmount, int userId) throws ServiceException{
        log.debug("Service: Getting amount order by user id on page.");
        int amountAllOrders = findAmountOrderByUserId(userId);
        return (int) Math.ceil((double) amountAllOrders/pageAmount);
    }
    /**
     * Find number of orders
     *
     * @return numbers of orders
     * @throws ServiceException if there is an error on DAO layer
     */
    @Override
    public int findOrdersAmount() throws ServiceException {
        log.debug("Service: Getting amount orders.");
        try (DaoHelper helper = daoHelperFactory.create()) {
            OrderDao dao = helper.createOrderDao();
            return dao.findOrdersAmount();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Find order's cost
     *
     * @return order's cost
     * @throws ServiceException if there is an error on DAO layer
     */
    @Override
    public int findOrdersCost() throws ServiceException {
        log.debug("Service: Getting orders costs.");
        try (DaoHelper helper = daoHelperFactory.create()) {
            OrderDao dao = helper.createOrderDao();
            return dao.findOrdersCost();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
