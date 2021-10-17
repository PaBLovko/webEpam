package by.training.webapplication.dao.impl;

import by.training.webapplication.dao.mapper.impl.UserRowMapper;
import by.training.webapplication.dao.api.UserDao;
import by.training.webapplication.dao.exception.DaoException;
import by.training.webapplication.bean.User;

import java.sql.Connection;
import java.util.List;

public class UserDaoImpl extends AbstractDao <User> implements UserDao {
    private static final String USER_TABLE = "user";
    private static final String ID_USER = "id_user";
    private static final String FIND_BY_LOGIN_AND_PASSWORD ="SELECT * FROM user WHERE login = ? AND password = ?";
    private static final String FIND_LIMIT_CLIENTS ="SELECT * FROM user WHERE role = 3 LIMIT ?, ?";
    private static final String CHANGE_NOTE = "UPDATE user SET note = ? WHERE id_user = ?";
    private static final String CHANGE_NAME = "UPDATE user SET name_user = ? WHERE id_user = ?";
    private static final String CHANGE_SURNAME = "UPDATE user SET surname = ? WHERE id_user = ?";
    private static final String CHANGE_PATRONYMIC = "UPDATE user SET patronymic = ? WHERE id_user = ?";
    private static final String CHANGE_ADDRESS = "UPDATE user SET address = ? WHERE id_user = ?";
    private static final String CHANGE_PHONE = "UPDATE user SET phone = ? WHERE id_user = ?";
    private static final String SAVE_USER = "INSERT INTO user (login, password, role, surname, name_user, patronymic, address, phone, note)" +
            " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String CHANGE_ROLE = "UPDATE user SET role = ? WHERE id_user = ?";
    private static final String FIND_CLIENT_BY_ID ="SELECT * FROM user WHERE role = 3 AND id_user = ?";
    private static final String FIND_CLIENTS_COUNT = "SELECT COUNT(*) AS amount FROM user WHERE role = 3";
    private static final String FIND_USER_BY_LOGIN = "SELECT * FROM user WHERE login = ?";
    private static final String AMOUNT_CLIENTS = "amount";

    @Override
    protected String getTableName() {
        return USER_TABLE;
    }

    @Override
    protected String getIdName() {
        return ID_USER;
    }

    public UserDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public User findByLoginAndPassword(String login, String password) throws DaoException {
        return executeForSingleResult(FIND_BY_LOGIN_AND_PASSWORD, new UserRowMapper(),login,password);
    }
    @Override
    public List<User> findUserByLogin(String login) throws DaoException {
        return executeQuery(FIND_USER_BY_LOGIN, new UserRowMapper(), login);
    }
    @Override
    public void changeRole(String newRole, int userId) throws DaoException {
        executeUpdate(CHANGE_ROLE, newRole, userId);
    }

    @Override
    public void changeNote(String note, int userId) throws DaoException {
        executeUpdate(CHANGE_NOTE, note, userId);
    }
    @Override
    public List<User> getLimitClients(int start, int amount) throws DaoException {
        return executeQuery(FIND_LIMIT_CLIENTS, new UserRowMapper(), start, amount);
    }
    @Override
    public int findClientsAmount () throws DaoException{
        return executeQuery(FIND_CLIENTS_COUNT, AMOUNT_CLIENTS);
    }
   @Override
    public void save(Object... parameters) throws DaoException{
        executeUpdate(SAVE_USER, parameters);
   }
   @Override
    public void changeName (String newName, int userId) throws DaoException {
        executeUpdate(CHANGE_NAME,newName,userId);
   }

    @Override
    public void changeSurname(String newSurname, int userId) throws DaoException {
        executeUpdate(CHANGE_SURNAME,newSurname, userId);
    }

    @Override
    public void changePatronymic(String newPatronymic, int userId) throws DaoException {
        executeUpdate(CHANGE_PATRONYMIC, newPatronymic, userId);
    }

    @Override
    public void changeAddress(String newAddress, int userId) throws DaoException {
        executeUpdate(CHANGE_ADDRESS, newAddress, userId);
    }

    @Override
    public void changePhone(String newPhone, int userId) throws DaoException {
        executeUpdate(CHANGE_PHONE, newPhone, userId);
    }

    @Override
    public User findClientById(String userId) throws DaoException {
        return executeForSingleResult(FIND_CLIENT_BY_ID, new UserRowMapper(), userId);
    }
}
