package by.training.webapplication.dao.mapper.impl;

import by.training.webapplication.dao.mapper.RowMapper;
import by.training.webapplication.bean.Basket;
import by.training.webapplication.bean.BasketProduct;
import by.training.webapplication.bean.Dish;
import by.training.webapplication.bean.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BasketProductRowMapper implements RowMapper<BasketProduct> {
    @Override
    public BasketProduct map(ResultSet resultSet) throws SQLException {
        BasketProduct basketProduct = new BasketProduct();
        basketProduct.setId(resultSet.getInt("id_basket_product"));
        Dish dish = new Dish();
        dish.setId(resultSet.getInt("id_dish"));
        dish.setName(resultSet.getString("name_dish"));
        dish.setWeight(resultSet.getInt("weight"));
        dish.setPrice(resultSet.getDouble("price"));
        dish.setDescription(resultSet.getString("description"));
        dish.setPicture(resultSet.getString("picture"));
        basketProduct.setDish(dish);
        Basket basket = new Basket();
        basket.setId(resultSet.getInt("id_basket"));
        basket.setTotal(resultSet.getDouble("total"));
        User user = new User();
        user.setId(resultSet.getInt("id_user"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setRole(resultSet.getInt("role"));
        user.setSurname(resultSet.getString("surname"));
        user.setName(resultSet.getString("name_user"));
        user.setPatronymic(resultSet.getString("patronymic"));
        user.setAddress(resultSet.getString("address"));
        user.setPhone(resultSet.getString("phone"));
        user.setNote(resultSet.getString("note"));
        basket.setUser(user);
        basketProduct.setBasket(basket);
        basketProduct.setAmount(resultSet.getInt("basket_amount"));
        basketProduct.setCost(resultSet.getDouble("basket_cost"));
        return basketProduct;
    }
}
