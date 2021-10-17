package by.training.webapplication.service.impl;

import by.training.webapplication.bean.*;
import by.training.webapplication.service.exception.ServiceException;
import by.training.webapplication.service.factory.ServiceFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderProductServiceImplTest {
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @DataProvider
    public Object[][] forFindLimitOrderProduct() {
        List<OrderProduct> orderProducts1 = new ArrayList<>(Arrays.asList(new OrderProduct(6, new Order(3, new User(4, "user2", "user2", 3, "Zhukov", "Andrey", "Andreevich", "Alibegova 54-32, Minsk",
                        "8-029-965-31-32", "Always friendly staff"), 71.00, LocalDateTime.parse("2021-01-05T08:00:00"), LocalDateTime.parse("2021-01-05T15:30:00"), StatusEnum.DELIVERED),
                        new Dish(3, "Browns", 900, 14.50, "Made from fresh potatoes, no overkill with flour and extra spices.", "image/HashBrowns.jpg"), 1, 22.00),
                new OrderProduct(5, new Order(3, new User(4, "user2", "user2", 3, "Zhukov", "Andrey", "Andreevich", "Alibegova 54-32, Minsk",
                        "8-029-965-31-32", "Always friendly staff"), 71.00, LocalDateTime.parse("2021-01-05T08:00:00"), LocalDateTime.parse("2021-01-05T15:30:00"), StatusEnum.DELIVERED),
                        new Dish(4, "Chicken", 400, 12.00, "Fried chicken breast with a side dish of broccoli and herbs.", "image/ChickenBreast.jpg"), 1, 26.00),
                new OrderProduct(4, new Order(3, new User(4, "user2", "user2", 3, "Zhukov", "Andrey", "Andreevich", "Alibegova 54-32, Minsk",
                        "8-029-965-31-32", "Always friendly staff"), 71.00, LocalDateTime.parse("2021-01-05T08:00:00"), LocalDateTime.parse("2021-01-05T15:30:00"), StatusEnum.DELIVERED),
                        new Dish(3, "Browns", 900, 14.50, "Made from fresh potatoes, no overkill with flour and extra spices.", "image/HashBrowns.jpg"), 1, 25.00)));
        List<OrderProduct> orderProducts2 = new ArrayList<>(Arrays.asList(new OrderProduct(6, new Order(3, new User(4, "user2", "user2", 3, "Zhukov", "Andrey", "Andreevich", "Alibegova 54-32, Minsk",
                        "8-029-965-31-32", "Always friendly staff"), 71.00, LocalDateTime.parse("2021-01-05T08:00:00"), LocalDateTime.parse("2021-01-05T15:30:00"), StatusEnum.DELIVERED),
                        new Dish(3, "Browns", 900, 14.50, "Made from fresh potatoes, no overkill with flour and extra spices.", "image/HashBrowns.jpg"), 1, 22.00),
                new OrderProduct(5, new Order(3, new User(4, "user2", "user2", 3, "Zhukov", "Andrey", "Andreevich", "Alibegova 54-32, Minsk",
                        "8-029-965-31-32", "Always friendly staff"), 71.00, LocalDateTime.parse("2021-01-05T08:00:00"), LocalDateTime.parse("2021-01-05T15:30:00"), StatusEnum.DELIVERED),
                        new Dish(4, "Chicken", 400, 12.00, "Fried chicken breast with a side dish of broccoli and herbs.", "image/ChickenBreast.jpg"), 1, 26.00),
                new OrderProduct(4, new Order(3, new User(4, "user2", "user2", 3, "Zhukov", "Andrey", "Andreevich", "Alibegova 54-32, Minsk",
                        "8-029-965-31-32", "Always friendly staff"), 71.00, LocalDateTime.parse("2021-01-05T08:00:00"), LocalDateTime.parse("2021-01-05T15:30:00"), StatusEnum.DELIVERED),
                        new Dish(3, "Browns", 900, 14.50, "Made from fresh potatoes, no overkill with flour and extra spices.", "image/HashBrowns.jpg"), 1, 25.00),
                new OrderProduct(3, new Order(2, new User(3, "user1", "user1", 3, "Yakovleva", "Alesia", "Sidorovna", "Lubimova 27-19, Minsk",
                        "8-029-598-23-31", "Live music complemented the festive evening well"), 46.00, LocalDateTime.parse("2021-01-04T08:00:00"), LocalDateTime.parse("2021-01-04T17:00:00"), StatusEnum.DELIVERED),
                        new Dish(2, "Mushroom", 300, 14.00, "Specialty soup with porcini mushrooms and pearl barley.", "image/MushroomSoup.jpg"), 1, 25.00),
                new OrderProduct(2, new Order(2, new User(3, "user1", "user1", 3, "Yakovleva", "Alesia", "Sidorovna", "Lubimova 27-19, Minsk",
                        "8-029-598-23-31", "Live music complemented the festive evening well"), 46.00, LocalDateTime.parse("2021-01-04T08:00:00"), LocalDateTime.parse("2021-01-04T17:00:00"), StatusEnum.DELIVERED),
                        new Dish(1, "Okroshka", 400, 11.50, "Okroshka on kefir, it's a cold drink.", "image/OkroshkaSoup.jpg"), 1, 14.00)));
        int start = 0;
        int amount1 = 1;
        int amount2 = 2;
        return new Object[][]{
                {orderProducts1, start, amount1},
                {orderProducts2, start, amount2}
        };
    }

    @Test(dataProvider = "forFindLimitOrderProduct")
    public void testFindLimitOrderProduct(List<OrderProduct> orderProducts, int start, int amount) throws ServiceException {
        Assert.assertEquals(serviceFactory.getOrderProductService().findLimitOrderProduct(start, amount), orderProducts);
    }
}
