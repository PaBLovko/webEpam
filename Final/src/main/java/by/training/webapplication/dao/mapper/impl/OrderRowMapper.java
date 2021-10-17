package by.training.webapplication.dao.mapper.impl;

import by.training.webapplication.dao.mapper.RowMapper;
import by.training.webapplication.bean.Order;
import by.training.webapplication.bean.StatusEnum;
import by.training.webapplication.bean.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class OrderRowMapper implements RowMapper<Order> {
    @Override
    public Order map(ResultSet resultSet) throws SQLException {
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
        return order;
    }
}
