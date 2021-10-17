package by.training.webapplication.service.impl;

import by.training.webapplication.dao.DaoHelperFactory;
import by.training.webapplication.bean.User;
import by.training.webapplication.service.api.UserService;
import by.training.webapplication.service.exception.LoginIsNotFreeException;
import by.training.webapplication.service.exception.ServiceException;
import by.training.webapplication.dao.DaoHelper;
import by.training.webapplication.dao.api.BasketDao;
import by.training.webapplication.dao.api.UserDao;
import by.training.webapplication.dao.exception.DaoException;
import by.training.webapplication.service.exception.ValidatorException;
import by.training.webapplication.service.validator.UserDataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
/**
 * Realization of {@link UserService} interface. Provides access to {@link UserService}
 */
public class UserServiceImpl implements UserService {

    /**
     * Factory for Dao
     */
    private DaoHelperFactory daoHelperFactory;

    /**
     * Validator for data checking
     */
    private UserDataValidator userDataValidator = new UserDataValidator();

    /**
     * Logger for this service
     */
    private static Logger log = LogManager.getLogger(UserServiceImpl.class.getName());

    /**
     * Constructor
     *
     * @param daoHelperFactory dao
     */
    public UserServiceImpl(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    /**
     * Get user by login and password
     *
     * @param login user's login
     * @param password user's password
     * @return user
     * @throws ServiceException if there is an error on DAO layer
     * @throws ValidatorException if there are validation error
     */
    @Override
    public User login(String login, String password) throws ServiceException, ValidatorException {
        log.debug("Service: Login user.");
        if(!userDataValidator.isLoginValid(login) || !userDataValidator.isPasswordValid(password)){
            log.error("The entered data is not correct!");
            throw new ValidatorException("The entered data is not correct!");
        }
        try (DaoHelper helper = daoHelperFactory.create()) {
            UserDao dao = helper.createUserDao();
            if (dao.findUserByLogin(login).isEmpty()){
                log.error("The entered data is not correct!");
                throw new ValidatorException("The entered data is not correct!");
            }
            String encodedPassword = dao.findUserByLogin(login).get(0).getPassword();
            if (!new BCryptPasswordEncoder().matches(password, encodedPassword)){
                log.error("The entered data is not correct!");
                throw new ValidatorException("The entered data is not correct!");
            }
            return dao.findByLoginAndPassword(login, encodedPassword);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Check login
     *
     * @param login user's login
     * @return list of users
     * @throws ServiceException if there is an error on DAO layer
     */
    public List<User> checkLogin(String login) throws ServiceException {
        log.debug("Service: checking login.");
        try (DaoHelper helper = daoHelperFactory.create()) {
            UserDao dao = helper.createUserDao();
            return dao.findUserByLogin(login);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Change user's name
     *
     * @param newName user's new name
     * @param userId user id
     * @throws ServiceException if there is an error on DAO layer
     * @throws ValidatorException if there are validation error
     */
    @Override
    public void changeName(String newName, int userId) throws ServiceException, ValidatorException {
        log.debug("Service: Changing name started.");
        if(!userDataValidator.isNameValid(newName)){
            log.error("The name is wrong");
            throw new ValidatorException("The name is wrong");
        }
        try (DaoHelper helper = daoHelperFactory.create()) {
            UserDao dao = helper.createUserDao();
            dao.changeName(newName, userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        log.debug("Service: Changing name finished.");
    }

    /**
     * Change user's surname
     *
     * @param newSurname user's new surname
     * @param userId user id
     * @throws ServiceException if there is an error on DAO layer
     * @throws ValidatorException if there are validation error
     */
    @Override
    public void changeSurname(String newSurname, int userId) throws ServiceException, ValidatorException {
        log.debug("Service: Changing surname started.");
        if(!userDataValidator.isSurnameValid(newSurname)){
            log.error("The surname is wrong");
            throw new ValidatorException("The surname is wrong");
        }
        try (DaoHelper helper = daoHelperFactory.create()) {
            UserDao dao = helper.createUserDao();
            dao.changeSurname(newSurname, userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        log.debug("Service: Changing surname finished.");
    }

    /**
     * Change user's patronymic
     *
     * @param newPatronymic user's new patronymic
     * @param userId user's id
     * @throws ServiceException if there is an error on DAO layer
     * @throws ValidatorException if there are validation error
     */
    @Override
    public void changePatronymic(String newPatronymic, int userId) throws ServiceException, ValidatorException {
        log.debug("Service: Changing patronymic started.");
        if(!userDataValidator.isPatronymicValid(newPatronymic)){
            log.error("The patronymic is wrong");
            throw new ValidatorException("The patronymic is wrong");
        }
        try (DaoHelper helper = daoHelperFactory.create()) {
            UserDao dao = helper.createUserDao();
            dao.changePatronymic(newPatronymic, userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        log.debug("Service: Changing patronymic finished.");
    }

    /**
     * Change user's address
     *
     * @param newAddress user's new address
     * @param userId user's id
     * @throws ServiceException if there is an error on DAO layer
     * @throws ValidatorException if there are validation error
     */
    @Override
    public void changeAddress(String newAddress, int userId) throws ServiceException, ValidatorException {
        log.debug("Service: Changing address started.");
        if(!userDataValidator.isAddressValid(newAddress)){
            log.error("The address is wrong");
            throw new ValidatorException("The address is wrong");
        }
        try (DaoHelper helper = daoHelperFactory.create()) {
            UserDao dao = helper.createUserDao();
            dao.changeAddress(newAddress, userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        log.debug("Service: Changing address finished.");
    }

    /**
     * Change user's phone
     *
     * @param newPhone user's new phone
     * @param userId user's id
     * @throws ServiceException if there is an error on DAO layer
     * @throws ValidatorException if there are validation error
     */
    @Override
    public void changePhone(String newPhone, int userId) throws ServiceException, ValidatorException {
        log.debug("Service: Changing phone started.");
        if(!userDataValidator.isPhoneValid(newPhone)){
            log.error("The phone is wrong");
            throw new ValidatorException("The phone is wrong");
        }
        try (DaoHelper helper = daoHelperFactory.create()) {
            UserDao dao = helper.createUserDao();
            dao.changePhone(newPhone, userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        log.debug("Service: Changing phone finished.");
    }

    /**
     * Change user's note
     *
     * @param newNote users's new note
     * @param userId user's id
     * @throws ServiceException if there is an error on DAO layer
     * @throws ValidatorException if there are validation error
     */
    @Override
    public void changeNote(String newNote, int userId) throws ServiceException, ValidatorException {
        log.debug("Service: Changing note started.");
        if (!userDataValidator.isNoteValid(newNote)) {
            log.error("The note is wrong");
            throw new ValidatorException("The note is wrong");
        }
        try (DaoHelper helper = daoHelperFactory.create()) {
            UserDao dao = helper.createUserDao();
            dao.changeNote(newNote, userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        log.debug("Service: Changing note finished.");
    }

    /**
     * Delete user
     *
     * @param id user's id
     * @throws ServiceException if there is an error on DAO layer
     */
    @Override
    public void deleteUser (int id) throws ServiceException {
        log.debug("Service: Deleting role started.");
        try (DaoHelper helper = daoHelperFactory.create()) {
            UserDao dao = helper.createUserDao();
            dao.removeById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        log.debug("Service: Deleting role finished.");
    }


    /**
     * Change user's role
     *
     * @param newRole user's new role
     * @param userId user's id
     * @throws ServiceException if there is an error on DAO layer
     * @throws ValidatorException if there are validation error
     */
    @Override
    public void changeRole(String newRole, int userId) throws ServiceException, ValidatorException {
        log.debug("Service: Changing role started.");
        if (!userDataValidator.isRoleValid(newRole)) {
            log.error("The role is wrong");
            throw new ValidatorException("The role is wrong");
        }
        try (DaoHelper helper = daoHelperFactory.create()) {
            UserDao dao = helper.createUserDao();
            dao.changeRole(newRole, userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        log.debug("Service: Changing role finished.");
    }

    /**
     * Find user by id
     *
     * @param userId user's id
     * @return user
     * @throws ServiceException if there is an error on DAO layer
     * @throws ValidatorException if there are validation error
     */
    @Override
    public User findClientById(String userId) throws ServiceException, ValidatorException {
        log.debug("Service: Getting user.");
        if(!userDataValidator.isIdValid(userId)){
            log.error("The id is wrong");
            throw new ValidatorException("The id is wrong");
        }
        try (DaoHelper helper = daoHelperFactory.create()) {
            UserDao dao = helper.createUserDao();
            return dao.findClientById(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Find number of clients
     *
     * @return number of users
     * @throws ServiceException if there is an error on DAO layer
     */
    private int findUserAmount () throws ServiceException{
        log.debug("Service: Getting user amount.");
        try (DaoHelper helper = daoHelperFactory.create()) {
            UserDao dao = helper.createUserDao();
            return dao.findAmount();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Find number of pages with users
     *
     * @param pageAmount number of users on page
     * @return number of pages
     * @throws ServiceException if there is an error on DAO layer
     */
    @Override
    public int findUserPageAmount (int pageAmount) throws ServiceException{
        log.debug("Service: Getting user page amount.");
        int amountAllUser = findUserAmount();
        return (int) Math.ceil((double) amountAllUser/pageAmount);
    }

    /**
     * Find list of users on 1 page
     *
     * @param start index of the first user on page
     * @param amount number users on page
     * @return list of users on 1 page
     * @throws ServiceException if there is an error on DAO layer
     */
    @Override
    public List<User> findLimitUser(int start, int amount) throws ServiceException {
        log.debug("Service: Getting user limit.");
        try (DaoHelper helper = daoHelperFactory.create()) {
            UserDao dao = helper.createUserDao();
            return dao.findLimit(start, amount);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Find list of clients on 1 page
     *
     * @param start index of the first client on page
     * @param amount number clients on page
     * @return list of clients on 1 page
     * @throws ServiceException if there is an error on DAO layer
     */
    @Override
    public List<User> findLimitClients(int start, int amount) throws ServiceException {
        log.debug("Service: Getting clients limit.");
        try (DaoHelper helper = daoHelperFactory.create()) {
            UserDao dao = helper.createUserDao();
            return dao.getLimitClients(start, amount);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Find number of clients
     *
     * @return number of clients
     * @throws ServiceException if there is an error on DAO layer
     */
    @Override
    public int findClientAmount() throws ServiceException {
        log.debug("Service: Getting clients amount.");
        try (DaoHelper helper = daoHelperFactory.create()) {
            UserDao dao = helper.createUserDao();
            return dao.findClientsAmount();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Find number of pages with clients
     *
     * @param pageAmount number of clients on page
     * @return number of pages
     * @throws ServiceException if there is an error on DAO layer
     */
    @Override
    public int findClientPageAmount (int pageAmount) throws ServiceException{
        log.debug("Service: Getting client page amount.");
        int amountAllClients = findClientAmount();
        return (int) Math.ceil((double) amountAllClients/pageAmount);
    }

    /**
     * Add new user
     *
     * @param login user's login
     * @param password user's password
     * @param role user's role
     * @param surname user's surname
     * @param name user's name
     * @param patronymic user's patronymic
     * @param address user's address
     * @param phone user's phone
     * @param note user's note
     * @param total user's basket total
     * @throws ServiceException if there is an error on DAO layer
     * @throws ValidatorException if there are validation error
     * @throws LoginIsNotFreeException if there is login is not free
     */
    @Override
    public void addUser(String login, String password, String role, String surname, String name, String patronymic, String address, String phone, String note, double total) throws ServiceException, ValidatorException, LoginIsNotFreeException {
        log.debug("Service: Adding user started.");
        if (!userDataValidator.isLoginValid(login) || !userDataValidator.isPasswordValid(password) ||
                !userDataValidator.isSurnameValid(surname) || !userDataValidator.isNameValid(name) ||
                !userDataValidator.isPatronymicValid(patronymic) || !userDataValidator.isAddressValid(address) ||
                !userDataValidator.isPhoneValid(phone) || !userDataValidator.isNoteValid(note) ||
                !userDataValidator.isRoleValid(role)) {
            log.error("The entered data is not correct!");
            throw new ValidatorException("The entered data is not correct!");
        }
        if (!checkLogin(login).isEmpty()) {
            log.error("Login is not free");
            throw new LoginIsNotFreeException("Login is not free");
        }
        try (DaoHelper helper = daoHelperFactory.create()) {
            UserDao userDao = helper.createUserDao();
            BasketDao basketDao = helper.createBasketDao();
            try {
                helper.startTransaction();
                userDao.save(login, new BCryptPasswordEncoder().encode(password), role, surname, name, patronymic, address, phone, note);
                if(role.equals("3")) {
                    basketDao.save(login, total);
                }
                helper.endTransaction();
            } catch (DaoException ex) {
                helper.backTransaction();
                throw new ServiceException(ex);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        log.debug("Service: Adding user finished.");
    }
}
