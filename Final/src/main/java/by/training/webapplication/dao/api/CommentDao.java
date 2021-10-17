package by.training.webapplication.dao.api;

import by.training.webapplication.bean.Comment;
import by.training.webapplication.dao.exception.DaoException;

import java.util.List;

public interface CommentDao extends Dao<Comment>{
    List<Comment> findLimitComment(int start, int amount) throws DaoException;
}
