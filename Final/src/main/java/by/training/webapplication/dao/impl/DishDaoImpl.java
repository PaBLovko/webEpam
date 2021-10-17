package by.training.webapplication.dao.impl;

import by.training.webapplication.dao.api.DishDao;
import by.training.webapplication.dao.exception.DaoException;
import by.training.webapplication.dao.mapper.impl.DishRowMapper;
import by.training.webapplication.bean.Dish;

import java.sql.Connection;
import java.util.List;

public class DishDaoImpl extends AbstractDao<Dish> implements DishDao {
    private static final String DISH_TABLE = "dish";
    private static final String ID_DISH = "id_dish";
    private static final String SORT_BY_INCREASE_PRICE ="SELECT * FROM dish ORDER BY price";
    private static final String SORT_BY_DECREASE_PRICE ="SELECT * FROM dish ORDER BY price DESC";
    private static final String FIND_BY_NAME ="SELECT * FROM dish WHERE name_dish = ?";
    private static final String SAVE_DISH = "INSERT INTO dish (name_dish, weight, price, description, picture)" +
            " VALUES(?, ?, ?, ?, ?)";
    private static final String CHANGE_NAME = "UPDATE dish SET name_dish = ? WHERE id_dish = ?";
    private static final String CHANGE_PICTURE = "UPDATE dish SET picture = ? WHERE id_dish = ?";
    private static final String CHANGE_DESCRIPTION = "UPDATE dish SET description = ? WHERE id_dish = ?";
    private static final String CHANGE_WEIGHT = "UPDATE dish SET weight = ? WHERE id_dish = ?";
    private static final String CHANGE_PRICE = "UPDATE dish SET price = ? WHERE id_dish = ?";
    public DishDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public List<Dish> sortByIncreasePrice() throws DaoException {
        return executeQuery(SORT_BY_INCREASE_PRICE, new DishRowMapper());
    }

    @Override
    public List<Dish> sortByDecreasePrice() throws DaoException {
        return executeQuery(SORT_BY_DECREASE_PRICE, new DishRowMapper());
    }

    @Override
    public Dish findByName(String name) throws DaoException {
        return executeForSingleResult(FIND_BY_NAME, new DishRowMapper(), name);
    }
    @Override
    protected String getTableName() {
        return DISH_TABLE;
    }

    @Override
    protected String getIdName() {
        return ID_DISH;
    }

    @Override
    public void save(Object... parameters) throws DaoException {
        executeUpdate(SAVE_DISH, parameters);
    }
    @Override
    public void changeName(String newName, int dishId) throws DaoException {
        executeUpdate(CHANGE_NAME, newName, dishId);
    }

    @Override
    public void changePicture(String newPicture, int dishId) throws DaoException {
        executeUpdate(CHANGE_PICTURE, newPicture, dishId);
    }

    @Override
    public void changeDescription(String newDescription, int dishId) throws DaoException {
        executeUpdate(CHANGE_DESCRIPTION, newDescription, dishId);
    }

    @Override
    public void changeWeight(String newWeight, int dishId) throws DaoException {
        executeUpdate(CHANGE_WEIGHT, newWeight, dishId);
    }

    @Override
    public void changePrice(String newPrice, int dishId) throws DaoException {
        executeUpdate(CHANGE_PRICE, newPrice, dishId);
    }
}
