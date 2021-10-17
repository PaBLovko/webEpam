package by.training.webapplication.dao.mapper.impl;

import by.training.webapplication.dao.mapper.RowMapper;
import by.training.webapplication.bean.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class OrderProductRowMapper implements RowMapper<OrderProduct> {
    @Override
    public OrderProduct map(ResultSet resultSet) throws SQLException {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setId(resultSet.getInt("id_order_product"));
        Order order = new Order();
        order.setId(resultSet.getInt("id_order"));
        order.setTotal(resultSet.getDouble("total"));
        String preparationTime = resultSet.getString("preparationTime");
        if(preparationTime == null){
            order.setPreparationTime(null);
        } else {
            order.setPreparationTime(LocalDateTime.parse(preparationTime.replace(" ", "T")));
        }
        String deliveryTime = resultSet.getString("deliveryTime");
        if(deliveryTime == null){
            order.setDeliveryTime(null);
        } else {
            order.setDeliveryTime(LocalDateTime.parse(deliveryTime.replace(" ", "T")));
        }
        order.setStatus(StatusEnum.valueOf(resultSet.getString("status").replace(" ", "_").toUpperCase()));

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
        order.setUser(user);
        orderProduct.setOrder(order);
        Dish dish = new Dish();
        dish.setId(resultSet.getInt("id_dish"));
        dish.setName(resultSet.getString("name_dish"));
        dish.setWeight(resultSet.getInt("weight"));
        dish.setPrice(resultSet.getDouble("price"));
        dish.setDescription(resultSet.getString("description"));
        dish.setPicture(resultSet.getString("picture"));
        orderProduct.setDish(dish);
        orderProduct.setAmount(resultSet.getInt("order_amount"));
        orderProduct.setCost(resultSet.getDouble("order_cost"));

        return orderProduct;
    }
}
