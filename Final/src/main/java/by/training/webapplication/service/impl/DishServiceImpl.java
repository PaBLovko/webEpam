package by.training.webapplication.service.impl;

import by.training.webapplication.dao.DaoHelperFactory;
import by.training.webapplication.bean.Dish;
import by.training.webapplication.service.exception.ServiceException;
import by.training.webapplication.dao.DaoHelper;
import by.training.webapplication.dao.api.DishDao;
import by.training.webapplication.dao.exception.DaoException;
import by.training.webapplication.service.api.DishService;
import by.training.webapplication.service.exception.ValidatorException;
import by.training.webapplication.service.validator.DishDataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Realization of {@link DishService} interface. Provides access to {@link DishService},
 */
public class DishServiceImpl implements DishService {

    /**
     * Factory for Dao
     */
    private DaoHelperFactory daoHelperFactory;

    /**
     * Validator for data checking
     */
    private DishDataValidator dishDataValidator = new DishDataValidator();

    /**
     * Logger for this service
     */
    private static Logger log = LogManager.getLogger(DishServiceImpl.class.getName());

    /**
     * Constructor
     *
     * @param daoHelperFactory dao for this server
     */
    public DishServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    /**
     * Get list of dishes
     *
     * @return list of dishes
     * @throws ServiceException if there is an error on DAO layer
     */
    @Override
    public List<Dish> showAllDishes() throws ServiceException {
        log.debug("Service: Getting list of dishes.");
        try (DaoHelper helper = daoHelperFactory.create()) {
            DishDao dao = helper.createDishDao();
            return dao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Get list of sorted dishes by increase price
     *
     * @return sorted list of dishes
     * @throws ServiceException if there is an error on DAO layer
     */
    @Override
    public List<Dish> sortByPriceIncrease() throws ServiceException {
        log.debug("Service: Getting sort by price increase list of dishes.");
        try (DaoHelper helper = daoHelperFactory.create()) {
            DishDao dao = helper.createDishDao();
            return dao.sortByIncreasePrice();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Get list of sorted dishes by decrease price
     *
     * @return sorted list of dishes
     * @throws ServiceException if there is an error on DAO layer
     */
    @Override
    public List<Dish> sortByPriceDecrease() throws ServiceException {
        log.debug("Service: Getting sort by price decrease list of dishes.");
        try (DaoHelper helper = daoHelperFactory.create()) {
            DishDao dao = helper.createDishDao();
            return dao.sortByDecreasePrice();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Get dish by id
     *
     * @param dishId dish's id
     * @return dish
     * @throws ServiceException if there is an error on DAO layer
     * @throws ValidatorException if there are validation error
     */
    @Override
    public Dish findDishById(String dishId) throws ServiceException, ValidatorException {
        log.debug("Service: Getting dish by id.");
        if(!dishDataValidator.isIdValid(dishId)){
            log.error("The entered data is not correct!");
            throw new ValidatorException("The entered data is not correct!");
        }
        try (DaoHelper helper = daoHelperFactory.create()) {
            DishDao dao = helper.createDishDao();
            return dao.findById(dishId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Get dish by name
     *
     * @param name dish's id
     * @return dish
     * @throws ServiceException if there is an error on DAO layer
     * @throws ValidatorException if there are validation error
     */
    @Override
    public Dish findDishByName(String name) throws ServiceException, ValidatorException {
        log.debug("Service: Getting dish by name.");
        if (!dishDataValidator.isNameValid(name)) {
            log.error("The entered data is not correct!");
            throw new ValidatorException("The entered data is not correct!");
        }
            try (DaoHelper helper = daoHelperFactory.create()) {
                DishDao dao = helper.createDishDao();
                return dao.findByName(name);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }

    /**
     * Delete dish
     *
     * @param id dish's id
     * @throws ServiceException if there is an error on DAO layer
     */
    @Override
    public void deleteDish (int id) throws ServiceException {
        log.debug("Service: Deleting dish started.");
        try (DaoHelper helper = daoHelperFactory.create()) {
            DishDao dao = helper.createDishDao();
            dao.removeById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        log.debug("Service: Deleting dish finished.");
    }

    /**
     * Add new dish
     *
     * @param name dish's name
     * @param weight dish's weight
     * @param price dish's price
     * @param description dish's description
     * @param picture dish's picture
     * @throws ServiceException if there is an error on DAO layer
     * @throws ValidatorException if there are validation error
     */
    @Override
    public void addDish(String name, String weight, String price, String description, String picture) throws ServiceException, ValidatorException {
        log.debug("Service: Adding dish started.");
        if (!dishDataValidator.isNameValid(name) || !dishDataValidator.isWeightValid(weight) ||
                !dishDataValidator.isPriceValid(price) || !dishDataValidator.isDescriptionValid(description) ||
                !dishDataValidator.isPictureValid(picture)) {
            log.error("The entered data is not correct!");
            throw new ValidatorException("The entered data is not correct!");
        }
        try (DaoHelper helper = daoHelperFactory.create()) {
            DishDao dao = helper.createDishDao();
            dao.save(name, weight, price, description, picture);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        log.debug("Service: Adding dish finished.");
    }

    /**
     * Change dish's name
     *
     * @param newName dish's new name
     * @param dishId dish's id
     * @throws ServiceException if there is an error on DAO layer
     * @throws ValidatorException if there are validation error
     */
    @Override
    public void changeName(String newName, int dishId) throws ServiceException, ValidatorException {
        log.debug("Service: Changing name started.");
        if(!dishDataValidator.isNameValid(newName)){
            log.error("The entered data is not correct!");
            throw new ValidatorException("The entered data is not correct!");
        }
        try (DaoHelper helper = daoHelperFactory.create()) {
            DishDao dao = helper.createDishDao();
            dao.changeName(newName, dishId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        log.debug("Service: Changing name finished.");
    }

    /**
     * Change path of picture
     *
     * @param newPicture dish's new path of picture
     * @param dishId dish's id
     * @throws ServiceException if there is an error on DAO layer
     * @throws ValidatorException if there are validation error
     */
    @Override
    public void changePicture(String newPicture, int dishId) throws ServiceException, ValidatorException {
        log.debug("Service: Changing picture started.");
        if(!dishDataValidator.isPictureValid(newPicture)){
            log.error("The entered data is not correct!");
            throw new ValidatorException("The entered data is not correct!");
        }
        try (DaoHelper helper = daoHelperFactory.create()) {
            DishDao dao = helper.createDishDao();
            dao.changePicture(newPicture, dishId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        log.debug("Service: Changing picture finished.");
    }

    /**
     * Change dish's description
     *
     * @param newDescription dish's new description
     * @param dishId dish's id
     * @throws ServiceException if there is an error on DAO layer
     * @throws ValidatorException if there are validation error
     */
    @Override
    public void changeDescription(String newDescription, int dishId) throws ServiceException, ValidatorException {
        log.debug("Service: Changing description started.");
        if(!dishDataValidator.isDescriptionValid(newDescription)){
            log.error("The entered data is not correct!");
            throw new ValidatorException("The entered data is not correct!");
        }
        try (DaoHelper helper = daoHelperFactory.create()) {
            DishDao dao = helper.createDishDao();
            dao.changeDescription(newDescription, dishId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        log.debug("Service: Changing description finished.");
    }

    /**
     * Change dish's weight
     *
     * @param newWeight dish's new weight
     * @param dishId dish's id
     * @throws ServiceException if there is an error on DAO layer
     * @throws ValidatorException if there are validation error
     */
    @Override
    public void changeWeight(String newWeight, int dishId) throws ServiceException, ValidatorException {
        log.debug("Service: Changing weight started.");
        if(!dishDataValidator.isWeightValid(newWeight)){
            log.error("The entered data is not correct!");
            throw new ValidatorException("The entered data is not correct!");
        }
        try (DaoHelper helper = daoHelperFactory.create()) {
            DishDao dao = helper.createDishDao();
            dao.changeWeight(newWeight, dishId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        log.debug("Service: Changing weight finished.");
    }

    /**
     * Change dish's price
     *
     * @param newPrice dish's new price
     * @param dishId dish's id
     * @throws ServiceException if there is an error on DAO layer
     * @throws ValidatorException if there are validation error
     */
    @Override
    public void changePrice(String newPrice, int dishId) throws ServiceException, ValidatorException {
        log.debug("Service: Changing price started.");
        if(!dishDataValidator.isPriceValid(newPrice)){
            log.error("The entered data is not correct!");
            throw new ValidatorException("The entered data is not correct!");
        }
        try (DaoHelper helper = daoHelperFactory.create()) {
            DishDao dao = helper.createDishDao();
            dao.changePrice(newPrice, dishId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        log.debug("Service: Changing price finished.");
    }

    /**
     * Get sorted list of dishes
     *
     * @param value way of sort
     * @param increase value sorting by increase price
     * @param decrease value sorting by decrease price
     * @throws ServiceException if there is an error on DAO layer
     */
    @Override
    public List<Dish> getSortDishList(String value, String increase, String decrease) throws ServiceException {
        List<Dish> dishes = new ArrayList<>();
        if (value != null) {
            if (value.equals(increase)) {
                dishes = sortByPriceIncrease();
            } else if (value.equals(decrease)) {
                dishes = sortByPriceDecrease();
            }
        } else {
            dishes = showAllDishes();
        }
        return dishes;
    }
}
