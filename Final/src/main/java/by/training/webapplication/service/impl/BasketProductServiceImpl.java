package by.training.webapplication.service.impl;

import by.training.webapplication.dao.DaoHelperFactory;
import by.training.webapplication.service.api.BasketProductService;
import by.training.webapplication.service.exception.ServiceException;
import by.training.webapplication.dao.DaoHelper;
import by.training.webapplication.dao.api.BasketDao;
import by.training.webapplication.dao.api.BasketProductDao;
import by.training.webapplication.dao.exception.DaoException;
import by.training.webapplication.bean.BasketProduct;
import by.training.webapplication.service.exception.ValidatorException;
import by.training.webapplication.service.validator.DishDataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Realization of {@link BasketProductService} interface. Provides access to {@link BasketProductService},
 */
public class BasketProductServiceImpl implements BasketProductService {

    /**
     * Logger for this service
     */
    private static Logger log = LogManager.getLogger(BasketProductServiceImpl.class.getName());

    /**
     * Factory for Dao
     */
    private DaoHelperFactory daoHelperFactory;

    /**
     * Validator for data checking
     */
    private DishDataValidator dishDataValidator = new DishDataValidator();

    /**
     * Constructor
     *
     * @param daoHelperFactory dao for this server
     */
    public BasketProductServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    /**
     * Save product to basket
     *
     * @param basketId basket's id
     * @param dishId dish's id
     * @param amount dish's amount
     * @param price dish's price
     * @param newTotal basket's new total
     * @throws ServiceException if there is an error on DAO layer
     * @throws ValidatorException if there are validation error
     */
    @Override
    public void saveBasketProduct(int basketId, int dishId, String amount, double price, double newTotal) throws ServiceException, ValidatorException {
        log.debug("Service: saving basket product started.");
        if (!dishDataValidator.isDishAmountValid(amount)) {
            log.error("The number of dishes is wrong");
            throw new ValidatorException("The number of dishes is wrong");
        }
        int dishAmount = Integer.parseInt(amount);
        double cost = dishAmount * price;
        List<BasketProduct> basketProducts = findProductByBasketId(basketId);
        boolean sameProduct = false;
        for(BasketProduct basketProduct : basketProducts){
            if (basketProduct.getDish().getId() == dishId){
                sameProduct = true;
                dishAmount += basketProduct.getAmount();
                amount = String.valueOf(dishAmount);
                cost = dishAmount * price;
                break;
            }
        }
        try (DaoHelper helper = daoHelperFactory.create()) {
            BasketProductDao basketProductDao = helper.createBasketProductDao();
            BasketDao basketDao = helper.createBasketDao();
            try {
                helper.startTransaction();
                if (sameProduct){
                    basketProductDao.changeBasketByBasketId(amount, cost, basketId);
                }else {
                    basketProductDao.save(basketId, dishId, amount, cost);
                }
                basketDao.changeTotal(newTotal, basketId);
                helper.endTransaction();
            } catch (DaoException ex) {
                helper.backTransaction();
                throw new ServiceException(ex);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        log.debug("Service: saving basket product finished.");
    }

    /**
     * Clear basket
     *
     * @param basketId basket's id
     * @param newTotal basket's new total
     * @throws ServiceException if there is an error on DAO layer
     */
    @Override
    public void clearBasket (int basketId, double newTotal) throws ServiceException {
        log.debug("Service: deleting basket product by basket id started.");
        try (DaoHelper helper = daoHelperFactory.create()) {
            BasketProductDao basketProductDao = helper.createBasketProductDao();
            BasketDao basketDao = helper.createBasketDao();
            try {
                helper.startTransaction();
                basketProductDao.removeByBasketId(basketId);
                basketDao.changeTotal(newTotal, basketId);
                helper.endTransaction();
            } catch (DaoException ex) {
                helper.backTransaction();
                throw new ServiceException(ex);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        log.debug("Service: deleting basket product by basket id finished.");
    }

    /**
     * Delete product from basket by product from basket id
     *
     * @param basketProductId product's id
     * @param basketId basket's id
     * @param newTotal basket's new total
     * @throws ServiceException if there is an error on DAO layer
     */
    @Override
    public void deleteBasketProductById(int basketProductId, double newTotal, int basketId) throws ServiceException {
        log.debug("Service: deleting basket product by id started.");
        try (DaoHelper helper = daoHelperFactory.create()) {
            BasketProductDao basketProductDao = helper.createBasketProductDao();
            BasketDao basketDao = helper.createBasketDao();
            try {
                helper.startTransaction();
                basketProductDao.removeById(basketProductId);
                basketDao.changeTotal(newTotal, basketId);
                helper.endTransaction();
            } catch (DaoException ex) {
                helper.backTransaction();
                throw new ServiceException(ex);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        log.debug("Service: deleting basket product by id finished.");
    }

    /**
     * Find all products by basket id
     *
     * @param basketId basket's id
     * @throws ServiceException if there is an error on DAO layer
     */
    @Override
    public List<BasketProduct> findProductByBasketId(int basketId) throws ServiceException {
        log.debug("Service: search product by basket id.");
        try (DaoHelper helper = daoHelperFactory.create()) {
            BasketProductDao dao = helper.createBasketProductDao();
            return dao.findByBasketId(basketId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
