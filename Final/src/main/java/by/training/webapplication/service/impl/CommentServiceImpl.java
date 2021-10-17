package by.training.webapplication.service.impl;

import by.training.webapplication.dao.DaoHelperFactory;
import by.training.webapplication.bean.Comment;
import by.training.webapplication.service.api.CommentService;
import by.training.webapplication.service.exception.ServiceException;
import by.training.webapplication.dao.DaoHelper;
import by.training.webapplication.dao.api.CommentDao;
import by.training.webapplication.dao.exception.DaoException;
import by.training.webapplication.service.exception.ValidatorException;
import by.training.webapplication.service.validator.CommentDataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementation of {@link CommentService} interface. Provides access to {@link CommentService},
 */
public class CommentServiceImpl implements CommentService {

    /**
     * Logger for this service
     */
    private static Logger log = LogManager.getLogger(CommentServiceImpl.class.getName());

    /**
     * Factory for Dao
     */
    private DaoHelperFactory daoHelperFactory;

    /**
     * Validator for data checking
     */
    private CommentDataValidator commentDataValidator = new CommentDataValidator();

    /**
     * Constructor
     *
     * @param daoHelperFactory dao for this server
     */
    public CommentServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    /**
     * Save comment
     *
     * @param userId user's id
     * @param commentDate comment's date of posting
     * @param review user's comment
     * @throws ServiceException if there is an error on DAO layer
     * @throws ValidatorException if there are validation error
     */
    @Override
    public void save(int userId, LocalDateTime commentDate, String review) throws ServiceException, ValidatorException {
        log.debug("Service: saving comment started.");
        if(!commentDataValidator.isCommentValid(review)){
            log.error("The entered data is not correct!");
            throw new ValidatorException("The entered data is not correct!");
        }
        try (DaoHelper helper = daoHelperFactory.create()) {
            CommentDao dao = helper.createCommentDao();
            dao.save(userId, commentDate, review);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        log.debug("Service: saving comment finished.");
    }

    /**
     * Delete comment
     *
     * @param id comment's id
     * @throws ServiceException if there is an error on DAO layer
     */
    @Override
    public void deleteComment (int id) throws ServiceException {
        log.debug("Service: deleting comment started.");
        try (DaoHelper helper = daoHelperFactory.create()) {
            CommentDao dao = helper.createCommentDao();
            dao.removeById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        log.debug("Service: deleting comment finished.");
    }

    /**
     * Find number of comments
     *
     * @return number of comments
     * @throws ServiceException if there is an error on DAO layer
     */
    private int findCommentAmount () throws ServiceException{
        log.debug("Service: search comment.");
        try (DaoHelper helper = daoHelperFactory.create()) {
            CommentDao dao = helper.createCommentDao();
            return dao.findAmount();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Find number of pages with comments
     *
     * @param pageAmount number comments on page
     * @return number of pages with comments
     * @throws ServiceException if there is an error on DAO layer
     */
    @Override
    public int findCommentPageAmount (int pageAmount) throws ServiceException{
        log.debug("Service: search comment page amount.");
        int amountAllComments = findCommentAmount();
        return (int) Math.ceil((double) amountAllComments/pageAmount);
    }

    /**
     * Get list of comments on page
     *
     * @param start index of first comments on page
     * @param amount number comments on page
     * @return number of pages with comments
     * @throws ServiceException if there is an error on DAO layer
     */
    @Override
    public List<Comment> findLimitComment(int start, int amount) throws ServiceException {
        log.debug("Service: search limit comment on page.");
        try (DaoHelper helper = daoHelperFactory.create()) {
            CommentDao dao = helper.createCommentDao();
            return dao.findLimitComment(start, amount);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
