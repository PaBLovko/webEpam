package by.training.webapplication.dao.impl;

import by.training.webapplication.dao.mapper.impl.BasketProductRowMapper;
import by.training.webapplication.dao.api.BasketProductDao;
import by.training.webapplication.dao.exception.DaoException;
import by.training.webapplication.bean.BasketProduct;

import java.sql.Connection;
import java.util.List;

public class BasketProductDaoImpl extends AbstractDao<BasketProduct> implements BasketProductDao {

    private static final String BASKET_PRODUCT_TABLE = "basket_product";
    private static final String ID_BASKET_PRODUCT = "id_basket_product";
    private static final String SAVE_BASKET_PRODUCT = "INSERT INTO basket_product (basket_id, dish_id, basket_amount, basket_cost)" +
            " VALUES(?, ?, ?, ?)";
    private static final String DELETE_BASKET_PRODUCT = "DELETE FROM basket_product WHERE basket_id = ? ";
    private static final String FIND_PRODUCTS_BY_BASKET_ID = "SELECT * FROM basket_product " +
            "INNER JOIN (SELECT * FROM basket WHERE id_basket = ?) AS basketProduct ON basket_product.basket_id=basketProduct.id_basket " +
            "INNER JOIN dish ON basket_product.dish_id=dish.id_dish " +
            "INNER JOIN user ON basketProduct.user_login=user.login;";
    private static final String CHANGE_BASKET_PRODUCT = "UPDATE basket_product SET basket_amount = ?, basket_cost = ? WHERE basket_id = ?";


    public BasketProductDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return BASKET_PRODUCT_TABLE;
    }

    @Override
    protected String getIdName() {
        return ID_BASKET_PRODUCT;
    }

    @Override
    public void save(Object... parameters) throws DaoException {
        executeUpdate(SAVE_BASKET_PRODUCT, parameters);
    }
    @Override
    public void removeByBasketId(int basketId) throws DaoException {
        executeUpdate(DELETE_BASKET_PRODUCT, basketId);
    }
    @Override
    public List<BasketProduct> findByBasketId(int basketId) throws DaoException {
        return executeQuery(FIND_PRODUCTS_BY_BASKET_ID, new BasketProductRowMapper(), basketId);
    }

    @Override
    public void changeBasketByBasketId(Object... parameters) throws DaoException {
        executeUpdate(CHANGE_BASKET_PRODUCT, parameters);
    }
}
