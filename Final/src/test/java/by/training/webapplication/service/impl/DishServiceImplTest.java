package by.training.webapplication.service.impl;

import by.training.webapplication.bean.Dish;
import by.training.webapplication.service.exception.ServiceException;
import by.training.webapplication.service.exception.ValidatorException;
import by.training.webapplication.service.factory.ServiceFactory;
import by.training.webapplication.dao.exception.DaoException;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
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
import java.util.List;

public class DishServiceImplTest {
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private List<Dish> correctDishes = new ArrayList<>(Arrays.asList(new Dish(1,"Okroshka", 400, 11.50, "Okroshka on kefir, it's a cold drink.","image/OkroshkaSoup.jpg"),
            new Dish(2, "Mushroom",300, 14.00, "Specialty soup with porcini mushrooms and pearl barley.","image/MushroomSoup.jpg"),
            new Dish(3, "Browns", 900, 14.50, "Made from fresh potatoes, no overkill with flour and extra spices.","image/HashBrowns.jpg"),
            new Dish(4, "Chicken", 400, 12.00, "Fried chicken breast with a side dish of broccoli and herbs.","image/ChickenBreast.jpg")));
    private List<Dish> wrongDishes = new ArrayList<>(Arrays.asList(new Dish(1,"Okroshka", 400, 11.50, "Okroshka on kefir, it's a cold drink.","image/OkroshkaSoup.jpg"),
            new Dish(2, "Mushroom",300, 14.00, "Specialty soup with porcini mushrooms and pearl barley.","image/MushroomSoup.jpg"),
            new Dish(3, "Browns", 900, 14.50, "Made from fresh potatoes, no overkill with flour and extra spices.","image/HashBrowns.jpg")));
    private List<Dish> sortedByPriceIncrease = new ArrayList<>(Arrays.asList(new Dish(1,"Okroshka", 400, 11.50, "Okroshka on kefir, it's a cold drink.","image/OkroshkaSoup.jpg"),
            new Dish(4, "Chicken", 400, 12.00, "Fried chicken breast with a side dish of broccoli and herbs.","image/ChickenBreast.jpg"),
            new Dish(2, "Mushroom",300, 14.00, "Specialty soup with porcini mushrooms and pearl barley.","image/MushroomSoup.jpg"),
            new Dish(3, "Browns", 900, 14.50, "Made from fresh potatoes, no overkill with flour and extra spices.","image/HashBrowns.jpg")));
    private List<Dish> sortedByPriceDecrease = new ArrayList<>(Arrays.asList(new Dish(3, "Browns", 900, 14.50, "Made from fresh potatoes, no overkill with flour and extra spices.","image/HashBrowns.jpg"),
            new Dish(2, "Mushroom",300, 14.00, "Specialty soup with porcini mushrooms and pearl barley.","image/MushroomSoup.jpg"),
            new Dish(4, "Chicken", 400, 12.00, "Fried chicken breast with a side dish of broccoli and herbs.","image/ChickenBreast.jpg"),
            new Dish(1,"Okroshka", 400, 11.50, "Okroshka on kefir, it's a cold drink.","image/OkroshkaSoup.jpg")));
    private List<Dish> addDish = new ArrayList<>(Arrays.asList(new Dish(1,"Okroshka", 400, 11.50, "Okroshka on kefir, it's a cold drink.","image/OkroshkaSoup.jpg"),
            new Dish(2, "Mushroom",300, 14.00, "Specialty soup with porcini mushrooms and pearl barley.","image/MushroomSoup.jpg"),
            new Dish(3, "Browns", 900, 14.50, "Made from fresh potatoes, no overkill with flour and extra spices.","image/HashBrowns.jpg"),
            new Dish(4, "Chicken", 400, 12.00, "Fried chicken breast with a side dish of broccoli and herbs.","image/ChickenBreast.jpg"),
            new Dish(5, "Barbecue", 700, 35.00, "Fried potatoes, smoked brisket, leeks and mushrooms.","image/HotPan.jpg")));
    @Test
    public void testCorrectDishList() throws ServiceException {
            Assert.assertEquals(serviceFactory.getDishService().showAllDishes(), correctDishes);
    }
    @Test
    public void testWrongDishList() throws ServiceException {
            Assert.assertNotEquals(serviceFactory.getDishService().showAllDishes(), wrongDishes);
    }

    @Test
    public void testCorrectDishListSortedByPriceIncrease() throws ServiceException {
            Assert.assertEquals(serviceFactory.getDishService().sortByPriceIncrease(), sortedByPriceIncrease);
    }

    @Test
    public void testWrongSortedByPriceIncrease() throws ServiceException {
            Assert.assertNotEquals(serviceFactory.getDishService().sortByPriceIncrease(), correctDishes);
    }

    @Test
    public void testCorrectDishListSortedByPriceReduce() throws ServiceException {
            Assert.assertEquals(serviceFactory.getDishService().sortByPriceDecrease(), sortedByPriceDecrease);
    }

    @Test
    public void testWrongSortedByPriceReduce() throws ServiceException {
        Assert.assertNotEquals(serviceFactory.getDishService().sortByPriceDecrease(), correctDishes);
    }


    @DataProvider
    public Object[][] forCorrectFindDishById() {
        Dish dish1 = new Dish(2, "Mushroom",300, 14.00, "Specialty soup with porcini mushrooms and pearl barley.","image/MushroomSoup.jpg");
        Dish dish2 = new Dish(3, "Browns", 900, 14.50, "Made from fresh potatoes, no overkill with flour and extra spices.","image/HashBrowns.jpg");
        String id1 = "2";
        String id2 = "3";
        return new Object[][]{
                {dish1, id1},
                {dish2, id2},
        };
    }

    @Test(dataProvider = "forCorrectFindDishById")
    public void testFindCorrectDishById(Dish dish, String id) throws ServiceException, ValidatorException {
        Assert.assertEquals(serviceFactory.getDishService().findDishById(id), dish);
    }
    @DataProvider
    public Object[][] forWrongFindDishById() {
        Dish dish1 = new Dish(2, "Mushroom",300, 14.00, "Specialty soup with porcini mushrooms and pearl barley.","image/MushroomSoup.jpg");
        Dish dish2 = new Dish(3, "Browns", 900, 14.50, "Made from fresh potatoes, no overkill with flour and extra spices.","image/HashBrowns.jpg");
        String id1 = "1";
        String id2 = "2";
        return new Object[][]{
                {dish1, id1},
                {dish2, id2},
        };
    }

    @Test(dataProvider = "forCorrectFindDishById")
    public void testFindWrongDishById(Dish dish, String id) throws ServiceException, ValidatorException {
        Assert.assertEquals(serviceFactory.getDishService().findDishById(id), dish);
    }

    @DataProvider
    public Object[][] forCorrectFindDishByName() {
        Dish dish1 = new Dish(2, "Mushroom",300, 14.00, "Specialty soup with porcini mushrooms and pearl barley.","image/MushroomSoup.jpg");
        Dish dish2 = new Dish(3, "Browns", 900, 14.50, "Made from fresh potatoes, no overkill with flour and extra spices.","image/HashBrowns.jpg");
        String name1 = "Mushroom";
        String name2 = "Browns";
        return new Object[][]{
                {dish1, name1},
                {dish2, name2},
        };
    }
    @Test(dataProvider = "forCorrectFindDishByName")
    public void testCorrectFindDishByName(Dish dish, String name) throws ServiceException, ValidatorException {
        Assert.assertEquals(serviceFactory.getDishService().findDishByName(name), dish);
    }

    @DataProvider
    public Object[][] forWrongDishByName() {
        Dish dish1 = new Dish(2, "Mushroom", 300, 14.00, "Specialty soup with porcini mushrooms and pearl barley.", "image/MushroomSoup.jpg");
        Dish dish2 = new Dish(3, "Browns", 900, 14.50, "Made from fresh potatoes, no overkill with flour and extra spices.", "image/HashBrowns.jpg");
        String name1 = "Okroshka";
        String name2 = "Mushroom";
        return new Object[][]{
                {dish1, name1},
                {dish2, name2},
        };
    }
    @Test(dataProvider = "forWrongDishByName")
        public void testWrongFindDishByName(Dish dish, String name) throws ServiceException, ValidatorException {
            Assert.assertNotEquals(serviceFactory.getDishService().findDishByName(name), dish);
    }

    @Test
    public void testChangeWeight() {
    }

    @Test(priority = 1)
    public void testAddDish() throws ServiceException, ValidatorException, DaoException {
        serviceFactory.getDishService().addDish("Barbecue", "700", "35.00", "Fried potatoes, smoked brisket, leeks and mushrooms.","image/HotPan.jpg");
        Assert.assertEquals(serviceFactory.getDishService().showAllDishes(), addDish);
    }
    @Test(priority = 2)
    public void testDeleteDish() throws ServiceException {
        serviceFactory.getDishService().deleteDish(5);
        Assert.assertEquals(serviceFactory.getDishService().showAllDishes(), correctDishes);
    }
    @DataProvider
    public Object[][] forCorrectSortDishList() {
        String value1 = "increase";
        String value2 = "reduce";
        return new Object[][]{
                {sortedByPriceIncrease, value1, value1, value2},
                {sortedByPriceDecrease, value2 ,value1, value2},
                {correctDishes, null, value1, value2},
        };
    }

    @Test(dataProvider = "forCorrectSortDishList")
    public void testGetCorrectSortDishList(List<Dish> dishes, String correctValue, String increase, String reduce) throws ServiceException {
        Assert.assertEquals(serviceFactory.getDishService().getSortDishList(correctValue, increase, reduce), dishes);

    }

    @DataProvider
    public Object[][] forWrongSortDishList() {
        String value1 = "increase";
        String value2 = "reduce";
        return new Object[][]{
                {sortedByPriceIncrease, value2, value1, value2},
                {sortedByPriceDecrease, null ,value1, value2},
                {correctDishes, value1, value1, value2},
        };
    }

    @Test(dataProvider = "forWrongSortDishList")
    public void testGetWrongSortDishList(List<Dish> dishes, String correctValue, String increase, String reduce) throws ServiceException {
        Assert.assertNotEquals(serviceFactory.getDishService().getSortDishList(correctValue, increase, reduce), dishes);
    }

    @DataProvider
    public Object[][] forChangeName() {
        Dish dish1 = new Dish(2, "Mushroom", 300, 14.00, "Specialty soup with porcini mushrooms and pearl barley.", "image/MushroomSoup.jpg");
        Dish dish2 = new Dish(2, "Broccoli", 300, 14.00, "Specialty soup with porcini mushrooms and pearl barley.", "image/MushroomSoup.jpg");
        String name1 = "Mushroom";
        String name2 = "Broccoli";
        int id = 2;
        return new Object[][]{
                {name1, id, dish1},
                {name2, id, dish2},
        };
    }

    @Test(priority = 3, dataProvider = "forChangeName")
    public void testChangeName(String newName, int id, Dish dish) throws ServiceException, ValidatorException {
        serviceFactory.getDishService().changeName(newName, id);
        Assert.assertEquals(serviceFactory.getDishService().findDishByName(newName), dish);
    }

    @DataProvider
    public Object[][] forChangeNameThrowValidatorException() {
        String name1 = "ooo";
        String name2 = "Okroshka3";
        int id = 1;
        return new Object[][]{
                {name1, id},
                {name2, id},
        };
    }

    @Test(expectedExceptions = ValidatorException.class, dataProvider = "forChangeNameThrowValidatorException")
    public void testChangeNameThrowValidatorException(String newName, int id) throws ServiceException, ValidatorException {
        serviceFactory.getDishService().changeName(newName, id);
    }

    @DataProvider
    public Object[][] forChangePicture() {
        Dish dish1 = new Dish(1,"Okroshka", 400, 11.50, "Okroshka on kefir, it's a cold drink.","image/MushroomSoup.jpg");
        Dish dish2 = new Dish(1,"Okroshka", 400, 11.50, "Okroshka on kefir, it's a cold drink.","image/OkroshkaSoup.jpg");
        String picture1 = "image/MushroomSoup.jpg";
        String picture2 = "image/OkroshkaSoup.jpg";
        int id = 1;
        return new Object[][]{
                {picture1, id, dish1},
                {picture2, id, dish2},
        };
    }

    @Test(priority = 4, dataProvider = "forChangePicture")
    public void testChangePicture(String newPicture, int id, Dish dish) throws ServiceException, ValidatorException {
        serviceFactory.getDishService().changePicture(newPicture, id);
        Assert.assertEquals(serviceFactory.getDishService().findDishById(String.valueOf(id)), dish);
    }


    @DataProvider
    public Object[][] forChangeDescription() {
        Dish dish1 = new Dish(1,"Okroshka", 400, 11.50, "Okroshka on kefir, it's a cold drink.","image/OkroshkaSoup.jpg");
        Dish dish2 = new Dish(1,"Okroshka", 400, 11.50, "Specialty soup with porcini mushrooms and pearl barley.","image/OkroshkaSoup.jpg");
        String description1 = "Okroshka on kefir, it's a cold drink.";
        String description2 = "Specialty soup with porcini mushrooms and pearl barley.";
        int id = 1;
        return new Object[][]{
                {description1, id, dish1},
                {description2, id, dish2},
        };
    }

    @Test(priority = 5, dataProvider = "forChangeDescription")
    public void testChangeDescription(String newDescription, int id, Dish dish) throws ServiceException, ValidatorException {
        serviceFactory.getDishService().changeDescription(newDescription, id);
        Assert.assertEquals(serviceFactory.getDishService().findDishById(String.valueOf(id)), dish);
    }


    @DataProvider
    public Object[][] forChangeWeightThrowValidatorException() {
        String name1 = "two";
        String name2 = "4334";
        int id = 1;
        return new Object[][]{
                {name1, id},
                {name2, id},
        };
    }

    @Test(expectedExceptions = ValidatorException.class, dataProvider = "forChangeWeightThrowValidatorException")
    public void testChangeWeightThrowValidatorException(String newWeight, int id) throws ServiceException, ValidatorException {
        serviceFactory.getDishService().changeWeight(newWeight, id);
    }

    @DataProvider
    public Object[][] forChangeWeight() {
        Dish dish1 = new Dish(4, "Chicken", 400, 12.00, "Fried chicken breast with a side dish of broccoli and herbs.","image/ChickenBreast.jpg");
        Dish dish2 = new Dish(4, "Chicken", 550, 12.00, "Fried chicken breast with a side dish of broccoli and herbs.","image/ChickenBreast.jpg");
        String weight1 = "400";
        String weight2 = "550";
        int id =4;
        return new Object[][]{
                {weight1, id, dish1},
                {weight2, id, dish2},
        };
    }

    @Test(priority = 5, dataProvider = "forChangeWeight")
    public void testChangeWeight(String newWeight, int id, Dish dish) throws ServiceException, ValidatorException {
        serviceFactory.getDishService().changeWeight(newWeight, id);
        Assert.assertEquals(serviceFactory.getDishService().findDishById(String.valueOf(id)), dish);
    }

    @DataProvider
    public Object[][] forChangePriceThrowValidatorException() {
        String name1 = "1";
        String name2 = "two";
        int id = 1;
        return new Object[][]{
                {name1, id},
                {name2, id},
        };
    }

    @Test(expectedExceptions = ValidatorException.class, dataProvider = "forChangePriceThrowValidatorException")
    public void testChangePriceThrowValidatorException(String newPrice, int id) throws ServiceException, ValidatorException {
        serviceFactory.getDishService().changePrice(newPrice, id);
    }

    @DataProvider
    public Object[][] forChangePrice() {
        Dish dish1 = new Dish(3, "Browns", 900, 14.00, "Made from fresh potatoes, no overkill with flour and extra spices.","image/HashBrowns.jpg");
        Dish dish2 = new Dish(3, "Browns", 900, 15.00, "Made from fresh potatoes, no overkill with flour and extra spices.","image/HashBrowns.jpg");
        String price1 = "14.00";
        String price2 = "15.00";
        int id = 3;
        return new Object[][]{
                {price1, id, dish1},
                {price2, id, dish2},
        };
    }

    @Test(priority = 5, dataProvider = "forChangePrice")
    public void testChangePrice(String newPrice, int id, Dish dish) throws ServiceException, ValidatorException {
        serviceFactory.getDishService().changePrice(newPrice, id);
        Assert.assertEquals(serviceFactory.getDishService().findDishById(String.valueOf(id)), dish);
    }

    @BeforeTest
    public void beforeTest() throws SQLException, IOException{
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
