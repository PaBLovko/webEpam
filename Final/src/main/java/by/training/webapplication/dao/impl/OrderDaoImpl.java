package by.training.webapplication.dao.impl;

import by.training.webapplication.dao.mapper.impl.OrderRowMapper;
import by.training.webapplication.dao.api.OrderDao;
import by.training.webapplication.dao.exception.DaoException;
import by.training.webapplication.bean.Order;

import java.sql.Connection;
import java.time.LocalDateTime;

public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao {
    private static final String ORDER_TABLE = "`order`";
    private static final String ID_ORDER = "id_order";
    private static final String SAVE_ORDER = "INSERT INTO `order` (user_id, total, preparationTime, deliveryTime, status)" +
            " VALUES(?, ?, ?, ?, ?)";
    private static final String FIND_LAST_ORDER = "SELECT * FROM user INNER JOIN `order` ON user.id_user= ? AND `order`.user_id= ? ORDER BY id_order DESC LIMIT 1";
    private static final String CHANGE_PREPARATION_TIME = "UPDATE `order` SET preparationTime = ? WHERE id_order = ?";
    private static final String CHANGE_DELIVERY_TIME = "UPDATE `order` SET deliveryTime = ? WHERE id_order = ?";
    private static final String CHANGE_STATUS = "UPDATE `order` SET status = ? WHERE id_order = ?";
    private static final String CHANGE_TOTAL = "UPDATE `order` SET total = ? WHERE id_order = ?";
    private static final String FIND_ORDERS_COUNT_BY_USER_ID = "SELECT COUNT(*) AS amount FROM `order` WHERE user_id = ?";
    private static final String AMOUNT_ORDERS_BY_USER_ID = "amount";
    private static final String FIND_ORDERS_AMOUNT = "SELECT COUNT(*) FROM `order`";
    private static final String FIND_ORDERS_COST = "SELECT SUM(total) FROM `order`";
    private static final String COUNT = "COUNT(*)";
    private static final String SUM = "SUM(total)";

    public OrderDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return ORDER_TABLE;
    }

    @Override
    protected String getIdName() {
        return ID_ORDER;
    }

    @Override
    public void save(Object... parameters) throws DaoException {
        executeUpdate(SAVE_ORDER, parameters);
    }
    @Override
    public void changePreparationTime(LocalDateTime newDate, int orderId) throws DaoException {
        executeUpdate(CHANGE_PREPARATION_TIME, newDate, orderId);
    }

    @Override
    public void changeDeliveryTime(LocalDateTime newDate, int orderId) throws DaoException {
        executeUpdate(CHANGE_DELIVERY_TIME, newDate, orderId);
    }

    @Override
    public void changeStatus(String newStatus, int orderId) throws DaoException {
        executeUpdate(CHANGE_STATUS, newStatus, orderId);
    }

    @Override
    public void changeTotal(double newTotal, int orderId) throws DaoException {
        executeUpdate(CHANGE_TOTAL, newTotal, orderId);
    }

    @Override
    public int findAmountOrdersByUserId (int userId) throws DaoException{
        return executeQuery(FIND_ORDERS_COUNT_BY_USER_ID, AMOUNT_ORDERS_BY_USER_ID, userId);
    }
    @Override
    public Order findLastUserOrderById(int userId) throws DaoException {
        return executeForSingleResult(FIND_LAST_ORDER, new OrderRowMapper(), userId, userId);
    }
    @Override
    public int findOrdersAmount () throws DaoException{
        return executeQuery(FIND_ORDERS_AMOUNT, COUNT);
    }

    @Override
    public int findOrdersCost () throws DaoException{
        return executeQuery(FIND_ORDERS_COST, SUM);
    }

}
