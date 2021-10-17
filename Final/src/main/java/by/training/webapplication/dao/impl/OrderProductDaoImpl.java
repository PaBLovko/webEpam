package by.training.webapplication.dao.impl;

import by.training.webapplication.dao.mapper.impl.OrderProductRowMapper;
import by.training.webapplication.dao.api.OrderProductDao;
import by.training.webapplication.dao.exception.DaoException;
import by.training.webapplication.bean.OrderProduct;

import java.sql.Connection;
import java.util.List;

public class OrderProductDaoImpl extends AbstractDao<OrderProduct> implements OrderProductDao {
    private static final String ORDER_PRODUCT_TABLE = "order_product";
    private static final String ID_ORDER_PRODUCT = "id_order_product";
    private static final String SAVE_ORDER_PRODUCT = "INSERT INTO `order_product` (order_id, dish_id, order_amount, order_cost)" +
            " VALUES(?, ?, ?, ?)";
    private static final String FIND_ORDER_PRODUCT_BY_ORDER_PRODUCT_ID = "SELECT * FROM order_product INNER JOIN `order` ON order_product.order_id=`order`.id_order INNER JOIN dish ON order_product.dish_id=dish.id_dish INNER JOIN user ON `order`.user_id=user.id_user WHERE id_order_product = ?";
    private static final String FIND_LIMIT_ORDER_PRODUCT = "SELECT * FROM order_product " +
            "INNER JOIN (SELECT * FROM `order` ORDER BY id_order DESC LIMIT ? , ?) AS `orderLimit` ON order_product.order_id=`orderLimit`.id_order " +
            "INNER JOIN dish ON order_product.dish_id=dish.id_dish " +
            "INNER JOIN user ON `orderLimit`.user_id=user.id_user " +
            "ORDER BY id_order_product DESC";
    private static final String FIND_LIMIT_ORDER_PRODUCT_BY_USER_ID = "SELECT * FROM order_product " +
            "INNER JOIN (SELECT * FROM `order` WHERE user_id= ? ORDER BY id_order DESC LIMIT ? , ?) AS `orderLimit` ON order_product.order_id=`orderLimit`.id_order " +
            "INNER JOIN dish ON order_product.dish_id=dish.id_dish " +
            "INNER JOIN user ON `orderLimit`.user_id=user.id_user " +
            "ORDER BY id_order_product DESC";
    public OrderProductDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return ORDER_PRODUCT_TABLE;
    }

    @Override
    protected String getIdName() {
        return ID_ORDER_PRODUCT;
    }

    @Override
    public void save(Object... parameters) throws DaoException {
        executeUpdate(SAVE_ORDER_PRODUCT, parameters);
    }
    @Override
    public OrderProduct findOrderProductById(int orderProductId) throws DaoException {
        return executeForSingleResult(FIND_ORDER_PRODUCT_BY_ORDER_PRODUCT_ID, new OrderProductRowMapper(), orderProductId);
    }
    @Override
    public List<OrderProduct> findLimitOrderProduct(int start, int amount) throws DaoException {
        return executeQuery(FIND_LIMIT_ORDER_PRODUCT, new OrderProductRowMapper(), start, amount);
    }
    @Override
    public List<OrderProduct> findLimitOrderProductByUserId(int userId, int start, int amount) throws DaoException {
        return executeQuery(FIND_LIMIT_ORDER_PRODUCT_BY_USER_ID, new OrderProductRowMapper(), userId, start, amount);
    }
}
