package by.training.webapplication.service.api;

import by.training.webapplication.service.exception.ServiceException;
import by.training.webapplication.bean.Comment;
import by.training.webapplication.service.exception.ValidatorException;

import java.time.LocalDateTime;
import java.util.List;

public interface CommentService {
    void save (int userId, LocalDateTime CommentDate, String review) throws ServiceException, ValidatorException;;
    void deleteComment (int id) throws ServiceException;
    int findCommentPageAmount (int pageAmount) throws ServiceException;
    List<Comment> findLimitComment(int start, int amount) throws ServiceException;
}
