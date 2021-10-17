package by.training.webapplication.service.impl;

import by.training.webapplication.bean.Dish;
import by.training.webapplication.bean.User;
import by.training.webapplication.service.exception.ServiceException;
import by.training.webapplication.service.exception.ValidatorException;
import by.training.webapplication.service.factory.ServiceFactory;
import by.training.webapplication.bean.Basket;
import by.training.webapplication.bean.BasketProduct;
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

public class BasketProductServiceImplTest {
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private List<BasketProduct> basketProducts = new ArrayList<>(Collections.singletonList(new BasketProduct(1, new Basket(1, new User(3, "user1", "user1", 3, "Yakovleva", "Alesia", "Sidorovna", "Lubimova 27-19, Minsk",
            "8-029-598-23-31", "Live music complemented the festive evening well"), 11.5), new Dish(1, "Okroshka", 400, 11.50, "Okroshka on kefir, it's a cold drink.","image/OkroshkaSoup.jpg"), 1, 11.50)));

    @DataProvider
    public Object[][] forSaveBasketProduct() {
        List<BasketProduct> basketProducts1 = new ArrayList<>(Collections.singletonList(new BasketProduct(1, new Basket(1, new User(3, "user1", "user1", 3, "Yakovleva", "Alesia", "Sidorovna", "Lubimova 27-19, Minsk",
                "8-029-598-23-31", "Live music complemented the festive evening well"), 11.5), new Dish(1, "Okroshka", 400, 11.50, "Okroshka on kefir, it's a cold drink.","image/OkroshkaSoup.jpg"), 1, 11.50)));
        List<BasketProduct> basketProducts2 = new ArrayList<>(Arrays.asList(new BasketProduct(1, new Basket(1, new User(3, "user1", "user1", 3, "Yakovleva", "Alesia", "Sidorovna", "Lubimova 27-19, Minsk",
                        "8-029-598-23-31", "Live music complemented the festive evening well"), 23.0), new Dish(1, "Okroshka", 400, 11.50, "Okroshka on kefir, it's a cold drink.","image/OkroshkaSoup.jpg"), 1, 11.50),
                new BasketProduct(2, new Basket(1, new User(3, "user1", "user1", 3, "Yakovleva", "Alesia", "Sidorovna", "Lubimova 27-19, Minsk",
                        "8-029-598-23-31", "Live music complemented the festive evening well"), 23.0), new Dish(2, "Mushroom",300, 14.00, "Specialty soup with porcini mushrooms and pearl barley.","image/MushroomSoup.jpg"), 1, 14.00)));
        int basketId = 1;
        int dishId1 = 1;
        int dishId2 = 2;
        String amount = "1";
        double price1 = 11.50;
        double price2 = 14.00;
        double total1 =11.50;
        double total2 = 23.00;
        return new Object[][]{
                {basketProducts1, basketId, dishId1, amount, price1, total1},
                {basketProducts2, basketId, dishId2, amount, price2, total2}
        };
    }

    @Test(dataProvider = "forSaveBasketProduct")
    public void testSaveBasketProduct(List<BasketProduct> products, int basketId, int dishId, String amount, double price,
                                      double newTotal) throws ServiceException, ValidatorException {
        serviceFactory.getBasketProductService().saveBasketProduct(basketId, dishId, amount, price, newTotal);
        Assert.assertEquals(serviceFactory.getBasketProductService().findProductByBasketId(basketId), products);
    }

    @DataProvider
    public Object[][] forSaveBasketProductThrowValidatorException() {
        int basketId = 1;
        int dishId1 = 1;
        int dishId2 = 2;
        String amount1 = "100";
        String amount2 = "";
        double price1 = 11.50;
        double price2 = 14.00;
        double total1 = 11.50;
        double total2 = 23.00;
        return new Object[][]{
                {basketId, dishId1, amount1, price1, total1},
                {basketId, dishId2, amount2, price2, total2}
        };
    }

    @Test(dataProvider = "forSaveBasketProductThrowValidatorException", expectedExceptions = ValidatorException.class)
    public void testSaveBasketProductThrowValidatorException(int basketId, int dishId, String amount, double price,
                                                             double newTotal) throws ServiceException, ValidatorException {
        serviceFactory.getBasketProductService().saveBasketProduct(basketId, dishId, amount, price, newTotal);
    }

    @Test(priority = 1)
    public void testDeleteBasketProductById() throws ServiceException {
        serviceFactory.getBasketProductService().deleteBasketProductById(2, 11.5, 1);
        Assert.assertEquals(serviceFactory.getBasketProductService().findProductByBasketId(1), basketProducts);
    }


    @Test(priority = 2)
    public void testClearBasket() throws ServiceException {
        serviceFactory.getBasketProductService().clearBasket(1, 0.0);
        Assert.assertEquals(serviceFactory.getBasketService().findBasketByUserLogin("user1").getTotal(), 0.0);
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
