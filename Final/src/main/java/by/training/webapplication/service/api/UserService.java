package by.training.webapplication.service.api;

import by.training.webapplication.service.exception.LoginIsNotFreeException;
import by.training.webapplication.service.exception.ServiceException;
import by.training.webapplication.bean.User;
import by.training.webapplication.service.exception.ValidatorException;

import java.util.List;

public interface UserService {
    User login(String login, String password) throws ServiceException, ValidatorException;
    void changeName(String newName, int userId) throws ServiceException, ValidatorException;
    void changeSurname(String newSurname, int userId) throws ServiceException, ValidatorException;
    void changePatronymic(String newPatronymic, int userId) throws ServiceException, ValidatorException;
    void changeAddress(String newAddress, int userId) throws ServiceException, ValidatorException;
    void changePhone(String newPhone, int userId) throws ServiceException, ValidatorException;
    void deleteUser (int id) throws ServiceException;
    void changeNote(String newNote, int userId) throws ServiceException, ValidatorException;
    void changeRole(String newRole, int userId) throws ServiceException, ValidatorException;
    List<User> findLimitClients(int start, int amount) throws ServiceException;
    User findClientById (String userId) throws ServiceException, ValidatorException;
    int findUserPageAmount (int pageAmount) throws ServiceException;
    List<User> findLimitUser(int start, int amount) throws ServiceException;
    int findClientPageAmount (int pageAmount) throws ServiceException;
    int findClientAmount() throws ServiceException;
    void addUser(String login, String password, String role, String surname, String name, String patronymic, String address, String phone, String note, double total) throws ServiceException, ValidatorException, LoginIsNotFreeException;
}
