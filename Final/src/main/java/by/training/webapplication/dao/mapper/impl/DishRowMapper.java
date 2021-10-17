package by.training.webapplication.dao.mapper.impl;

import by.training.webapplication.dao.mapper.RowMapper;
import by.training.webapplication.bean.Dish;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DishRowMapper implements RowMapper<Dish> {
    @Override
    public Dish map(ResultSet resultSet) throws SQLException {
        Dish dish = new Dish();
        dish.setId(resultSet.getInt("id_dish"));
        dish.setName(resultSet.getString("name_dish"));
        dish.setWeight(resultSet.getInt("weight"));
        dish.setPrice(resultSet.getDouble("price"));
        dish.setDescription(resultSet.getString("description"));
        dish.setPicture(resultSet.getString("picture"));

        return dish;
    }
}
