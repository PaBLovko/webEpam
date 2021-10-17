package by.training.webapplication.dao.mapper;

import by.training.webapplication.dao.exception.DaoException;
import by.training.webapplication.dao.mapper.impl.*;
import by.training.webapplication.bean.Entity;


import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T extends Entity> {
    T map(ResultSet resultSet) throws SQLException;
    static RowMapper<? extends Entity> create (String table) throws DaoException {
        switch (table) {
            case "user": return new UserRowMapper();
            case "dish": return new DishRowMapper();
            case "order": return new OrderRowMapper();
            case "comment": return new CommentRowMapper();
            case "basket": return new BasketRowMapper();
            case "basket_product": return new BasketProductRowMapper();
            case "order_product": return new OrderProductRowMapper();

            default: throw new DaoException("Unknown table = " + table);
        }
    }

}
