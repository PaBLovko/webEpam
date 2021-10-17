package by.training.webapplication.service.impl;

import by.training.webapplication.bean.User;
import by.training.webapplication.service.exception.LoginIsNotFreeException;
import by.training.webapplication.service.exception.ServiceException;
import by.training.webapplication.service.factory.ServiceFactory;
import by.training.webapplication.service.exception.ValidatorException;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UserServiceImplTest {
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @DataProvider
    public Object[][] forFindClientById() {
        User user1 = new User(3, "user1", "user1", 3, "Yakovleva", "Alesia", "Sidorovna", "Lubimova 27-19, Minsk",
                "8-029-598-23-31", "Live music complemented the festive evening well");
        User user2 = new User(4, "user2", "user2", 3, "Zhukov", "Andrey", "Andreevich", "Alibegova 54-32, Minsk",
                "8-029-965-31-32", "Always friendly staff");
        String id1 = "3";
        String id2 = "4";
        return new Object[][]{
                {user1, id1},
                {user2, id2}
        };
    }

    @Test(dataProvider = "forFindClientById")
    public void testFindClientById(User user, String id) throws ServiceException, ValidatorException {
        Assert.assertEquals(serviceFactory.getUserService().findClientById(id), user);
    }

    @DataProvider
    public Object[][] forFindClientByIdThrowValidatorException() {
        User user1 = new User(3, "user1", "user1", 3, "Yakovleva", "Alesia", "Sidorovna", "Lubimova 27-19, Minsk",
                "8-029-598-23-31", "Live music complemented the festive evening well");
        User user2 = new User(4, "user2", "user2", 3, "Zhukov", "Andrey", "Andreevich", "Alibegova 54-32, Minsk",
                "8-029-965-31-32", "Always friendly staff");
        String id1 = "two";
        String id2 = "1 0";
        String id3 = "";
        return new Object[][]{
                {user1, id1},
                {user2, id2},
                {user2, id3}
        };
    }

    @Test(expectedExceptions = ValidatorException.class, dataProvider = "forFindClientByIdThrowValidatorException")
    public void testFindClientByIdThrowValidatorException(User user, String id) throws ServiceException, ValidatorException {
        Assert.assertEquals(serviceFactory.getUserService().findClientById(id), user);
    }

    @DataProvider
    public Object[][] forCorrectLogin() {
        User user1 = new User(3, "user1", "user1", 3, "Yakovleva", "Alesia", "Sidorovna", "Lubimova 27-19, Minsk",
                "8-029-598-23-31", "Live music complemented the festive evening well");
        User user2 = new User(4, "user2", "user2", 3, "Zhukov", "Andrey", "Andreevich", "Alibegova 54-32, Minsk",
                "8-029-965-31-32", "Always friendly staff");
        String login1 = "user1";
        String login2 = "user2";
        String password1 = "user1";
        String password2 = "user2";
        return new Object[][]{
                {user1, login1, password1},
                {user2, login2, password2}
        };
    }

    @Test(dataProvider = "forCorrectLogin")
    public void testCorrectLogin(User user, String login, String password) throws ServiceException, ValidatorException {
        Assert.assertEquals(serviceFactory.getUserService().login(login, password), user);
    }

    @DataProvider
    public Object[][] forWrongLoginThrowServiceException() {
        User user1 = new User(3, "user1", "user1", 3, "Yakovleva", "Alesia", "Sidorovna", "Lubimova 27-19, Minsk",
                "8-029-598-23-31", "Live music complemented the festive evening well");
        User user2 = new User(4, "user2", "user2", 3, "Zhukov", "Andrey", "Andreevich", "Alibegova 54-32, Minsk",
                "8-029-965-31-32", "Always friendly staff");
        String login1 = "user1";
        String login2 = "user2";
        String password1 = "user2";
        String password2 = "user1";
        return new Object[][]{
                {user1, login1, password1},
                {user2, login2, password2}
        };
    }

    @Test(expectedExceptions = ServiceException.class, dataProvider = "forWrongLoginThrowServiceException")
    public void testWrongLoginThrowServiceException(User user, String login, String password) throws ServiceException, ValidatorException {
        Assert.assertNotEquals(serviceFactory.getUserService().login(login, password), user);
    }

    @DataProvider
    public Object[][] forWrongLoginThrowValidatorException() {
        User user1 = new User(3, "user1", "user1", 3, "Yakovleva", "Alesia", "Sidorovna", "Lubimova 27-19, Minsk",
                "8-029-598-23-31", "Live music complemented the festive evening well");
        User user2 = new User(4, "user2", "user2", 3, "Zhukov", "Andrey", "Andreevich", "Alibegova 54-32, Minsk",
                "8-029-965-31-32", "Always friendly staff");
        String login1 = "пароль";
        String login2 = "userTwo";
        String password1 = "user2";
        String password2 = "логин";
        return new Object[][]{
                {user1, login1, password1},
                {user2, login2, password2}
        };
    }

    @Test(expectedExceptions = ValidatorException.class, dataProvider = "forWrongLoginThrowValidatorException")
    public void testWrongLoginThrowValidatorException(User user, String login, String password) throws ServiceException, ValidatorException {
        Assert.assertNotEquals(serviceFactory.getUserService().login(login, password), user);
    }

    @DataProvider
    public Object[][] forFindCorrectUserPageAmount() {
        int usersOnPage1 = 1;
        int usersOnPage2 = 2;
        int usersOnPage3 = 3;
        int usersOnPage4 = 4;
        int pageAmount1 = 4;
        int pageAmount2 = 2;
        int pageAmount3 = 2;
        int pageAmount4 = 1;
        return new Object[][]{
                {usersOnPage1, pageAmount1},
                {usersOnPage2, pageAmount2},
                {usersOnPage3, pageAmount3},
                {usersOnPage4, pageAmount4}
        };
    }

    @Test(dataProvider = "forFindCorrectUserPageAmount")
    public void testFindCorrectUserPageAmount(int userOnPage, int pageAmount) throws ServiceException {
        Assert.assertEquals(serviceFactory.getUserService().findUserPageAmount(userOnPage), pageAmount);
    }

    @DataProvider
    public Object[][] forFindWrongUserPageAmount() {
        int usersOnPage1 = 1;
        int usersOnPage2 = 2;
        int usersOnPage3 = 3;
        int usersOnPage4 = 4;
        int pageAmount1 = 3;
        int pageAmount2 = 1;
        int pageAmount3 = 3;
        int pageAmount4 = 2;
        return new Object[][]{
                {usersOnPage1, pageAmount1},
                {usersOnPage2, pageAmount2},
                {usersOnPage3, pageAmount3},
                {usersOnPage4, pageAmount4}
        };
    }

    @Test(dataProvider = "forFindWrongUserPageAmount")
    public void testFindWrongUserPageAmount(int userOnPage, int pageAmount) throws ServiceException {
        Assert.assertNotEquals(serviceFactory.getUserService().findUserPageAmount(userOnPage), pageAmount);
    }

    @DataProvider
    public Object[][] forFindCorrectLimitUserOnPage() {
        List<User> users1 = new ArrayList<>(Arrays.asList(new User(1, "admin", "admin", 1, "Naumenka", "Daniil", "Konstantsinavich", "Kamennogorskay 16-253, Minsk", "8-029-302-18-13", "admin"),
                new User(2, "deliverer1", "deliverer1", 2, "Frolov", "Konstantin", "Ivanovich", "Rafieva 2-111, Minsk",
                        "8-029-447-43-45", "deliverer1")));
        List<User> users2 = new ArrayList<>(Arrays.asList(new User(3, "user1", "user1", 3, "Yakovleva", "Alesia", "Sidorovna", "Lubimova 27-19, Minsk",
                        "8-029-598-23-31", "Live music complemented the festive evening well"),
                new User(4, "user2", "user2", 3, "Zhukov", "Andrey", "Andreevich", "Alibegova 54-32, Minsk",
                        "8-029-965-31-32", "Always friendly staff")));
        int start1 = 0;
        int start2 = 2;
        int amount = 2;
        return new Object[][]{
                {users1, start1, amount},
                {users2, start2, amount}
        };
    }

    @Test(dataProvider = "forFindCorrectLimitUserOnPage")
    public void testFindCorrectLimitUserOnPage(List<User> users, int start, int amount) throws ServiceException {
        Assert.assertEquals(serviceFactory.getUserService().findLimitUser(start, amount), users);
    }

    @DataProvider
    public Object[][] forFindWrongLimitUserOnPage() {
        List<User> users1 = new ArrayList<>(Arrays.asList(new User(1, "admin", "admin", 1, "Naumenka", "Daniil", "Konstantsinavich", "Kamennogorskay 16-253, Minsk", "8-029-302-18-13", "admin"),
                new User(2, "deliverer1", "deliverer1", 2, "Frolov", "Konstantin", "Ivanovich", "Rafieva 2-111, Minsk",
                        "8-029-447-43-45", "deliverer1")));
        List<User> users2 = new ArrayList<>(Arrays.asList(new User(3, "user1", "user1", 3, "Yakovleva", "Alesia", "Sidorovna", "Lubimova 27-19, Minsk",
                        "8-029-598-23-31", "Live music complemented the festive evening well"),
                new User(4, "user2", "user2", 3, "Zhukov", "Andrey", "Andreevich", "Alibegova 54-32, Minsk",
                        "8-029-965-31-32", "Always friendly staff")));
        int start1 = 1;
        int start2 = 3;
        int amount = 2;
        return new Object[][]{
                {users1, start1, amount},
                {users2, start2, amount}
        };
    }

    @Test(dataProvider = "forFindWrongLimitUserOnPage")
    public void testFindWrongLimitUserOnPage(List<User> users, int start, int amount) throws ServiceException {
        Assert.assertNotEquals(serviceFactory.getUserService().findLimitUser(start, amount), users);
    }

    @DataProvider
    public Object[][] forFindCorrectLimitClientsOnPage() {
        List<User> clients1 = new ArrayList<>(Collections.singletonList(new User(3, "user1", "user1", 3, "Yakovleva", "Alesia", "Sidorovna", "Lubimova 27-19, Minsk",
                "8-029-598-23-31", "Live music complemented the festive evening well")));
        List<User> clients2 = new ArrayList<>(Collections.singletonList(new User(4, "user2", "user2", 3, "Zhukov", "Andrey", "Andreevich", "Alibegova 54-32, Minsk",
                "8-029-965-31-32", "Always friendly staff")));
        int start1 = 0;
        int start2 = 1;
        int amount = 1;
        return new Object[][]{
                {clients1, start1, amount},
                {clients2, start2, amount}
        };
    }

    @Test(dataProvider = "forFindCorrectLimitClientsOnPage")
    public void testFindCorrectLimitClientsOnPage(List<User> users, int start, int amount) throws ServiceException {
        Assert.assertEquals(serviceFactory.getUserService().findLimitClients(start, amount), users);
    }
    @Test
    public void testFindCorrectClientAmount() throws ServiceException {
        int clientAmount = 2;
        Assert.assertEquals(serviceFactory.getUserService().findClientAmount(), clientAmount);
    }

    @DataProvider
    public Object[] forFindWrongClientsAmount() {
        int amount1 = 4;
        int amount2 = 3;
        int amount3 = 0;
        return new Object[]{ amount1, amount2, amount3};
    }

    @Test(dataProvider = "forFindWrongClientsAmount")
    public void testFindWrongClientAmount(int amount) throws ServiceException {
        Assert.assertNotEquals(serviceFactory.getUserService().findClientAmount(), amount);
    }

    @DataProvider
    public Object[][] forFindCorrectClientPageAmount() {
        int clientsOnPage1 = 1;
        int clientsOnPage2 = 2;
        int pageAmount1 = 2;
        int pageAmount2 = 1;
        return new Object[][]{
                {clientsOnPage1, pageAmount1},
                {clientsOnPage2, pageAmount2}
        };
    }

    @Test(dataProvider = "forFindCorrectClientPageAmount")
    public void testFindCorrectClientPageAmount(int clientsOnPage, int pageAmount) throws ServiceException {
        Assert.assertEquals(serviceFactory.getUserService().findClientPageAmount(clientsOnPage), pageAmount);
    }

    @DataProvider
    public Object[][] forFindWrongClientPageAmount() {
        int clientsOnPage1 = 1;
        int clientsOnPage2 = 2;
        int pageAmount1 = 1;
        int pageAmount2 = 2;
        return new Object[][]{
                {clientsOnPage1, pageAmount1},
                {clientsOnPage2, pageAmount2}
        };
    }

    @Test(dataProvider = "forFindWrongClientPageAmount")
    public void testFindWrongClientPageAmount(int clientsOnPage, int pageAmount) throws ServiceException {
        Assert.assertNotEquals(serviceFactory.getUserService().findClientPageAmount(clientsOnPage), pageAmount);
    }

    @DataProvider
    public Object[][] forChangeName() {
        User user1 = new User(3, "user1", "user1", 3, "Yakovleva", "Alesia", "Sidorovna", "Lubimova 27-19, Minsk",
                "8-029-598-23-31", "Live music complemented the festive evening well");
        User user2 = new User(3, "user1", "user1", 3, "Yakovleva", "Anna", "Sidorovna", "Lubimova 27-19, Minsk",
                "8-029-598-23-31", "Live music complemented the festive evening well");
        String newName1 = "Alesia";
        String newName2 = "Anna";
        String id = "3";
        return new Object[][]{
                {user1, newName1, id},
                {user2, newName2, id}
        };
    }
    @Test(priority = 1, dataProvider = "forChangeName")
    public void testChangeName(User user, String newName, String userId) throws ServiceException, ValidatorException {
        serviceFactory.getUserService().changeName(newName, Integer.parseInt(userId));
        Assert.assertEquals(serviceFactory.getUserService().findClientById(userId), user);
    }

    @DataProvider
    public Object[][] forChangeNameThrowValidatorException() {
        String newName1 = "1Anna";
        String newNAme2 = "anna";
        String newNAme3 = "";
        int id = 3;
        return new Object[][]{
                {newName1, id},
                {newNAme2, id},
                {newNAme3, id}
        };
    }
    @Test(expectedExceptions = ValidatorException.class, dataProvider = "forChangeNameThrowValidatorException")
    public void testChangeNameThrowValidatorException(String newName, int userId) throws ServiceException, ValidatorException {
        serviceFactory.getUserService().changeName(newName, userId);
    }

    @DataProvider
    public Object[][] forChangeSurname() {
        User user1 = new User(3, "user1", "user1", 3, "Yakovleva", "Alesia", "Sidorovna", "Lubimova 27-19, Minsk",
                "8-029-598-23-31", "Live music complemented the festive evening well");
        User user2 = new User(3, "user1", "user1", 3, "Rose", "Alesia", "Sidorovna", "Lubimova 27-19, Minsk",
                "8-029-598-23-31", "Live music complemented the festive evening well");
        String newSurname1 = "Yakovleva";
        String newSurname2 = "Rose";
        String id = "3";
        return new Object[][]{
                {user1, newSurname1, id},
                {user2, newSurname2, id}
        };
    }
    @Test(priority = 2, dataProvider = "forChangeSurname")
    public void testChangeSurname(User user, String newSurname, String userId) throws ServiceException, ValidatorException {
        serviceFactory.getUserService().changeSurname(newSurname, Integer.parseInt(userId));
        Assert.assertEquals(serviceFactory.getUserService().findClientById(userId), user);
    }

    @DataProvider
    public Object[][] forChangeSurnameThrowValidatorException() {
        String newSurname1 = "1Yakovleva";
        String newSurname2 = "Rose r";
        String newSurname3 = "";
        int id = 3;
        return new Object[][]{
                {newSurname1, id},
                {newSurname2, id},
                {newSurname3, id}
        };
    }
    @Test(expectedExceptions = ValidatorException.class, dataProvider = "forChangeSurnameThrowValidatorException")
    public void testChangeSurnameThrowValidatorException(String newSurname, int userId) throws ServiceException, ValidatorException {
        serviceFactory.getUserService().changeSurname(newSurname, userId);
    }

    @DataProvider
    public Object[][] forChangePatronymic() {
        User user1 = new User(3, "user1", "user1", 3, "Yakovleva", "Alesia", "Sidorovna", "Lubimova 27-19, Minsk",
                "8-029-598-23-31", "Live music complemented the festive evening well");
        User user2 = new User(3, "user1", "user1", 3, "Yakovleva", "Alesia", "Daniilavna", "Lubimova 27-19, Minsk",
                "8-029-598-23-31", "Live music complemented the festive evening well");
        String newPatronymic1 = "Sidorovna";
        String newPatronymic2 = "Daniilavna";
        String id = "3";
        return new Object[][]{
                {user1, newPatronymic1, id},
                {user2, newPatronymic2, id}
        };
    }
    @Test(priority = 3, dataProvider = "forChangePatronymic")
    public void testChangePatronymic(User user, String newPatronymic, String userId) throws ServiceException, ValidatorException {
        serviceFactory.getUserService().changePatronymic(newPatronymic, Integer.parseInt(userId));
        Assert.assertEquals(serviceFactory.getUserService().findClientById(userId), user);
    }

    @DataProvider
    public Object[][] forChangePatronymicThrowValidatorException() {
        String newPatronymic1 = "1Sidorovna";
        String newPatronymic2= "Daniilavna d";
        String newPatronymic3 = "";
        int id = 3;
        return new Object[][]{
                {newPatronymic1, id},
                {newPatronymic2, id},
                {newPatronymic3, id}
        };
    }
    @Test(expectedExceptions = ValidatorException.class, dataProvider = "forChangePatronymicThrowValidatorException")
    public void testChangePatronymicThrowValidatorException(String newPatronymic, int userId) throws ServiceException, ValidatorException {
        serviceFactory.getUserService().changePatronymic(newPatronymic, userId);
    }

    @DataProvider
    public Object[][] forChangeAddress() {
        User user1 = new User(3, "user1", "user1", 3, "Yakovleva", "Alesia", "Sidorovna", "Lubimova 27-19, Minsk",
                "8-029-598-23-31", "Live music complemented the festive evening well");
        User user2 = new User(3, "user1", "user1", 3, "Yakovleva", "Alesia", "Sidorovna", "Kirova 20-9, Minsk",
                "8-029-598-23-31", "Live music complemented the festive evening well");
        String newAddress1 = "Lubimova 27-19, Minsk";
        String newAddress2 = "Kirova 20-9, Minsk";
        String id = "3";
        return new Object[][]{
                {user1, newAddress1, id},
                {user2, newAddress2, id}
        };
    }

    @Test(priority = 4, dataProvider = "forChangeAddress")
    public void testChangeAddress(User user, String newAddress, String userId) throws ServiceException, ValidatorException {
        serviceFactory.getUserService().changeAddress(newAddress, Integer.parseInt(userId));
        Assert.assertEquals(serviceFactory.getUserService().findClientById(userId), user);
    }

    @DataProvider
    public Object[][] forChangeAddressThrowValidatorException() {
        String newAddress1 = "1one";
        String newAddress2 = "hom";
        String newAddress3 = "";
        int id = 3;
        return new Object[][]{
                {newAddress1, id},
                {newAddress2, id},
                {newAddress3, id}
        };
    }

    @Test(expectedExceptions = ValidatorException.class, dataProvider = "forChangeAddressThrowValidatorException")
    public void testChangeAddressThrowValidatorException(String newAddress, int userId) throws ServiceException, ValidatorException {
        serviceFactory.getUserService().changeAddress(newAddress, userId);
    }

    @DataProvider
    public Object[][] forChangePhone() {
        User user1 = new User(3, "user1", "user1", 3, "Yakovleva", "Alesia", "Sidorovna", "Lubimova 27-19, Minsk",
                "8-029-598-23-31", "Live music complemented the festive evening well");
        User user2 = new User(3, "user1", "user1", 3, "Yakovleva", "Alesia", "Sidorovna", "Lubimova 27-19, Minsk",
                "8-044-558-53-31", "Live music complemented the festive evening well");
        String newPhone1 = "8-029-598-23-31";
        String newPhone2 = "8-044-558-53-31";
        String id = "3";
        return new Object[][]{
                {user1, newPhone1, id},
                {user2, newPhone2, id}
        };
    }

    @Test(priority = 5, dataProvider = "forChangePhone")
    public void testChangePhone(User user, String newPhone, String userId) throws ServiceException, ValidatorException {
        serviceFactory.getUserService().changePhone(newPhone, Integer.parseInt(userId));
        Assert.assertEquals(serviceFactory.getUserService().findClientById(userId), user);
    }

    @DataProvider
    public Object[][] forChangePhoneThrowValidatorException() {
        String newPhone1 = "8029-5445167";
        String newPhone2 = "8-29-544-51-60";
        String newPhone3 = "phone";
        String newPhone4 = "";
        int id = 3;
        return new Object[][]{
                {newPhone1, id},
                {newPhone2, id},
                {newPhone3, id},
                {newPhone4, id}
        };
    }

    @Test(expectedExceptions = ValidatorException.class, dataProvider = "forChangePhoneThrowValidatorException")
    public void testChangePhoneThrowValidatorException(String newPhone, int userId) throws ServiceException, ValidatorException {
        serviceFactory.getUserService().changePhone(newPhone, userId);
    }
    @DataProvider
    public Object[][] forChangeNote() {
        User user1 = new User(3, "user1", "user1", 3, "Yakovleva", "Alesia", "Sidorovna", "Lubimova 27-19, Minsk",
                "8-029-598-23-31", "Live music complemented the festive evening well");
        User user2 = new User(3, "user1", "user1", 3, "Yakovleva", "Alesia", "Sidorovna", "Lubimova 27-19, Minsk",
                "8-029-598-23-31", "All is good");
        String newNote1 = "Live music complemented the festive evening well";
        String newNote2 = "All is good";
        String id = "3";
        return new Object[][]{
                {user1, newNote1, id},
                {user2, newNote2, id}
        };
    }

    @Test(priority = 6, dataProvider = "forChangeNote")
    public void testChangeNote(User user, String newNote, String userId) throws ServiceException, ValidatorException {
        serviceFactory.getUserService().changeNote(newNote, Integer.parseInt(userId));
        Assert.assertEquals(serviceFactory.getUserService().findClientById(userId), user);
    }

    @DataProvider
    public Object[][] forChangeRole() {
        User user1 = new User(3, "user1", "user1", 2, "Yakovleva", "Alesia", "Sidorovna", "Lubimova 27-19, Minsk",
                "8-029-598-23-31", "Live music complemented the festive evening well");
        User user2 = new User(3, "user1", "user1", 3, "Yakovleva", "Alesia", "Sidorovna", "Lubimova 27-19, Minsk",
                "8-029-598-23-31", "Live music complemented the festive evening well");
        String newRole1 = "2";
        String newRole2 = "3";
        String id = "3";
        String login = "user1";
        String password = "user1";
        return new Object[][]{
                {user1, newRole1, id, login, password},
                {user2, newRole2, id, login, password}
        };
    }

    @Test(priority = 7, dataProvider = "forChangeRole")
    public void testChangeRole(User user, String newRole, String userId, String login, String password) throws ServiceException, ValidatorException {
        serviceFactory.getUserService().changeRole(newRole, Integer.parseInt(userId));
        Assert.assertEquals(serviceFactory.getUserService().login(login, password), user);
    }

    @DataProvider
    public Object[][] forChangeRoleThrowValidatorException() {
        String newRole1 = "0";
        String newRole2 = "5";
        String newRole3 = "role";
        String newRole4 = "";
        int id = 3;
        return new Object[][]{
                {newRole1, id},
                {newRole2, id},
                {newRole3, id},
                {newRole4, id}
        };
    }

    @Test(expectedExceptions = ValidatorException.class, dataProvider = "forChangeRoleThrowValidatorException")
    public void testChangeRoleThrowValidatorException(String newRole, int userId) throws ServiceException, ValidatorException {
        serviceFactory.getUserService().changeRole(newRole, userId);
    }

    @DataProvider
    public Object[][] forDeleteUser() {
        List<User> users1 = new ArrayList<>(Arrays.asList(new User(1, "admin", "admin", 1, "Naumenka", "Daniil", "Konstantsinavich", "Kamennogorskay 16-253, Minsk",
                        "8-029-302-18-13", "admin"),
                new User(2, "deliverer1", "deliverer1", 2, "Frolov", "Konstantin", "Ivanovich", "Rafieva 2-111, Minsk",
                        "8-029-447-43-45", "deliverer1"),
                new User(3, "user1", "user1", 3, "Yakovleva", "Alesia", "Sidorovna", "Lubimova 27-19, Minsk",
                        "8-029-598-23-31", "Live music complemented the festive evening well")));
        List<User> users2 = new ArrayList<>(Arrays.asList(new User(1, "admin", "admin", 1, "Naumenka", "Daniil", "Konstantsinavich", "Kamennogorskay 16-253, Minsk", "8-029-302-18-13", "admin"),
                new User(2, "deliverer1", "deliverer1", 2, "Frolov", "Konstantin", "Ivanovich", "Rafieva 2-111, Minsk",
                        "8-029-447-43-45", "deliverer1")));
        int start = 0;
        int amount = 4;
        int id1 = 4;
        int id2 = 3;
        return new Object[][]{
                {users1, id1, start, amount},
                {users2, id2, start, amount}
        };
    }

    @Test(priority = 8, dataProvider = "forDeleteUser")
    public void testDeleteUser(List<User> users, int userId, int start, int amount) throws ServiceException {
        serviceFactory.getUserService().deleteUser(userId);
        Assert.assertEquals(serviceFactory.getUserService().findLimitUser(start, amount), users);
    }
    @DataProvider
    public Object[][] forAddUser() {
        List<User> users1 = new ArrayList<>(Arrays.asList(new User(1, "admin", "admin", 1, "Naumenka", "Daniil", "Konstantsinavich", "Kamennogorskay 16-253, Minsk",
                        "8-029-302-18-13", "admin"),
                new User(2, "deliverer1", "deliverer1", 2, "Frolov", "Konstantin", "Ivanovich", "Rafieva 2-111, Minsk",
                        "8-029-447-43-45", "deliverer1"),
                new User(5, "user1", "user1", 3, "Yakovleva", "Alesia", "Sidorovna", "Lubimova 27-19, Minsk",
                        "8-029-598-23-31", "Live music complemented the festive evening well")));
        List<User> users2 = new ArrayList<>(Arrays.asList(new User(1, "admin", "admin", 1, "Naumenka", "Daniil", "Konstantsinavich", "Kamennogorskay 16-253, Minsk",
                        "8-029-302-18-13", "admin"),
                new User(2, "deliverer1", "deliverer1", 2, "Frolov", "Konstantin", "Ivanovich", "Rafieva 2-111, Minsk",
                        "8-029-447-43-45", "deliverer1"),
                new User(5, "user1", "user1", 3, "Yakovleva", "Alesia", "Sidorovna", "Lubimova 27-19, Minsk",
                        "8-029-598-23-31", "Live music complemented the festive evening well"),
                new User(6, "user2", "user2", 3, "Zhukov", "Andrey", "Andreevich", "Alibegova 54-32, Minsk",
                        "8-029-965-31-32", "Always friendly staff")));
        int start = 0;
        int amount = 4;
        String login1 = "user1";
        String login2 = "user2";
        String password1 = "user1";
        String password2 = "user2";
        String role1 = "3";
        String role2 = "3";
        String surname1 = "Yakovleva";
        String surname2 = "Zhukov";
        String name1 = "Alesia";
        String name2 = "Andrey";
        String patronymic1 = "Sidorovna";
        String patronymic2 = "Andreevich";
        String address1 = "Lubimova 27-19, Minsk";
        String address2 = "Alibegova 54-32, Minsk";
        String phone1 = "8-029-598-23-31";
        String phone2 = "8-029-965-31-32";
        String note1 = "Live music complemented the festive evening well";
        String note2 = "Always friendly staff";
        double total = 0.0;
        return new Object[][]{
                {users1, start, amount, login1, password1, role1, surname1, name1, patronymic1, address1, phone1, note1, total},
                {users2, start, amount, login2, password2, role2, surname2, name2, patronymic2, address2, phone2, note2, total}
        };
    }

    @Test(priority = 9, dataProvider = "forAddUser")
    public void testAddUser(List<User> users, int start, int amount, String login, String password, String role, String surname,
                            String name, String patronymic, String address, String phone, String note, double total) throws ValidatorException, ServiceException, LoginIsNotFreeException {
        serviceFactory.getUserService().addUser(login, password, role, surname, name, patronymic, address, phone, note, total);
        Assert.assertEquals(serviceFactory.getUserService().findLimitUser(start, amount), users);
    }

    @DataProvider
    public Object[][] forAddUserThrowValidatorException() {
        String login1 = "юзер1";
        String login2 = "u ser2";
        String password1 = "юзер 1";
        String password2 = "user 2";
        String role1 = "0";
        String role2 = "5";
        String surname1 = "";
        String surname2 = "zhukov";
        String name1 = "Alesia";
        String name2 = "Andrey";
        String patronymic1 = "sidorovna";
        String patronymic2 = "андреевич";
        String address1 = "Min";
        String address2 = "45";
        String phone1 = "8-029743-45-46";
        String phone2 = "8-44-573-76-40";
        String note1 = "Live music complemented the festive evening well";
        String note2 = "Always friendly staff";
        double total = 0.0;
        return new Object[][]{
                {login1, password1, role1, surname1, name1, patronymic1, address1, phone1, note1, total},
                {login2, password2, role2, surname2, name2, patronymic2, address2, phone2, note2, total}
        };
    }

    @Test(dataProvider = "forAddUserThrowValidatorException", expectedExceptions = ValidatorException.class)
    public void testAddUserThrowValidatorException(String login, String password, String role, String surname, String name, String patronymic, String address,
                                                   String phone, String note, double total) throws ValidatorException, ServiceException, LoginIsNotFreeException {
        serviceFactory.getUserService().addUser(login, password, role, surname, name, patronymic, address, phone, note, total);
    }

    @DataProvider
    public Object[][] forAddUserThrowLoginIsNotFreeException() {
        String login1 = "user1";
        String login2 = "user2";
        String password1 = "user1";
        String password2 = "user2";
        String role1 = "3";
        String role2 = "3";
        String surname1 = "Yakovleva";
        String surname2 = "Zhukov";
        String name1 = "Alesia";
        String name2 = "Andrey";
        String patronymic1 = "Sidorovna";
        String patronymic2 = "Andreevich";
        String address1 = "Lubimova 27-19, Minsk";
        String address2 = "Alibegova 54-32, Minsk";
        String phone1 = "8-029-598-23-31";
        String phone2 = "8-029-965-31-32";
        String note1 = "Live music complemented the festive evening well";
        String note2 = "Always friendly staff";
        double total = 0.0;
        return new Object[][]{
                {login1, password1, role1, surname1, name1, patronymic1, address1, phone1, note1, total},
                {login2, password2, role2, surname2, name2, patronymic2, address2, phone2, note2, total}
        };
    }

    @Test(priority = 10, dataProvider = "forAddUserThrowLoginIsNotFreeException", expectedExceptions = LoginIsNotFreeException.class)
    public void testAddUserThrowLoginIsNotFreeException(String login, String password, String role, String surname, String name, String patronymic, String address,
                                                        String phone, String note, double total) throws ValidatorException, ServiceException, LoginIsNotFreeException {
        serviceFactory.getUserService().addUser(login, password, role, surname, name, patronymic, address, phone, note, total);
    }

    @AfterTest
    void tearDown() throws SQLException, IOException {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        String mysqlUrl = "jdbc:mysql://localhost:3306/test_restaurant_db";
        Connection con = DriverManager.getConnection(mysqlUrl, "root", "9142danii");
        ScriptRunner sr = new ScriptRunner(con);
        Reader reader = new BufferedReader(new FileReader("src/test/resources/sql/test_database.sql"));
        sr.runScript(reader);
        con.close();
        reader.close();
        sr.closeConnection();
    }
}

