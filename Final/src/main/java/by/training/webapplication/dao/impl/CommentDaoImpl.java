package by.training.webapplication.dao.impl;

import by.training.webapplication.dao.mapper.impl.CommentRowMapper;
import by.training.webapplication.dao.api.CommentDao;
import by.training.webapplication.dao.exception.DaoException;
import by.training.webapplication.bean.Comment;

import java.sql.Connection;
import java.util.List;

public class CommentDaoImpl extends AbstractDao<Comment> implements CommentDao {
    private static final String COMMENT_TABLE = "comment";
    private static final String ID_COMMENT = "id_comment";
    private static final String FIND_LIMIT_COMMENT = "SELECT * FROM user INNER JOIN comment ON user.id_user=comment.user_id ORDER BY id_comment DESC LIMIT ? , ?";
    private static final String SAVE_COMMENT = "INSERT INTO comment (user_id, comment_date, review)" +
            " VALUES(?, ?, ?)";
    public CommentDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public List<Comment> findLimitComment(int start, int amount) throws DaoException {
        return executeQuery(FIND_LIMIT_COMMENT, new CommentRowMapper(), start, amount);
    }
    @Override
    public void save(Object... parameters) throws DaoException {
        executeUpdate(SAVE_COMMENT, parameters);
    }

    @Override
    protected String getTableName() {
        return COMMENT_TABLE;
    }

    @Override
    protected String getIdName() {
        return ID_COMMENT;
    }
}
