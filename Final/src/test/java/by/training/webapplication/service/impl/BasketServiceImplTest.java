package by.training.webapplication.service.impl;

import by.training.webapplication.bean.Basket;
import by.training.webapplication.bean.User;
import by.training.webapplication.service.exception.ServiceException;
import by.training.webapplication.service.factory.ServiceFactory;
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

public class BasketServiceImplTest {
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @DataProvider
    public Object[][] forFindBasketByUserLogin() {
        Basket basket1 = new Basket(1, new User(3, "user1", "user1", 3, "Yakovleva", "Alesia", "Sidorovna", "Lubimova 27-19, Minsk",
                "8-029-598-23-31", "Live music complemented the festive evening well"), 0.0);
        Basket basket2 = new Basket(2, new User(4, "user2", "user2", 3, "Zhukov", "Andrey", "Andreevich", "Alibegova 54-32, Minsk",
                "8-029-965-31-32", "Always friendly staff"), 0.0);
        String userLogin1 = "user1";
        String userLogin2 = "user2";

        return new Object[][]{
                {basket1, userLogin1},
                {basket2, userLogin2}
        };
    }

    @Test(dataProvider = "forFindBasketByUserLogin")
    public void testFindBasketByUserLogin(Basket basket, String userLogin) throws ServiceException {
        Assert.assertEquals(serviceFactory.getBasketService().findBasketByUserLogin(userLogin), basket);
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
