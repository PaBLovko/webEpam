package by.training.webapplication.service.impl;

import by.training.webapplication.dao.DaoHelperFactory;
import by.training.webapplication.service.api.BasketService;
import by.training.webapplication.service.exception.ServiceException;
import by.training.webapplication.dao.DaoHelper;
import by.training.webapplication.dao.api.BasketDao;
import by.training.webapplication.dao.exception.DaoException;
import by.training.webapplication.bean.Basket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Realization of {@link BasketService} interface. Provides access to {@link BasketService},
 */
public class BasketServiceImpl implements BasketService {

    /**
     * Factory for Dao
     */
    private DaoHelperFactory daoHelperFactory;

    /**
     * Logger for this service
     */
    private static Logger log = LogManager.getLogger(BasketServiceImpl.class.getName());

    /**
     * Constructor - creating a new object
     *
     * @param daoHelperFactory dao for this server
     */
    public BasketServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    /**
     * Find basket by user's login
     *
     * @param userLogin user's login
     * @return Basket
     * @throws ServiceException if there is an error on DAO layer
     */
    @Override
    public Basket findBasketByUserLogin (String userLogin) throws ServiceException{
        log.debug("Service: search basket by user login.");
        try (DaoHelper helper = daoHelperFactory.create()) {
            BasketDao dao = helper.createBasketDao();
            return dao.getBasketByUserLogin(userLogin);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
