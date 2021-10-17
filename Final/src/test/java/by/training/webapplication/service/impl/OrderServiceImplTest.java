package by.training.webapplication.service.impl;

import by.training.webapplication.service.exception.ServiceException;
import by.training.webapplication.service.factory.ServiceFactory;
import by.training.webapplication.service.exception.ValidatorException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class OrderServiceImplTest {
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @DataProvider
    public Object[][] forFindCorrectOrderPageAmount() {
        int ordersOnPage1 = 1;
        int ordersOnPage2 = 2;
        int ordersOnPage3 = 3;
        int pageAmount1 = 3;
        int pageAmount2 = 2;
        int pageAmount3 = 1;
        return new Object[][]{
                {ordersOnPage1, pageAmount1},
                {ordersOnPage2, pageAmount2},
                {ordersOnPage3, pageAmount3},
        };
    }
    @Test(dataProvider = "forFindCorrectOrderPageAmount")
    public void testFindCorrectOrderPageAmount(int ordersOnPage, int pageAmount) throws ServiceException {
        Assert.assertEquals(serviceFactory.getOrderService().findOrderPageAmount(ordersOnPage), pageAmount);
    }

    @DataProvider
    public Object[][] forFindWrongOrderPageAmount() {
        int ordersOnPage1 = 1;
        int ordersOnPage2 = 2;
        int ordersOnPage3 = 3;
        int pageAmount1 = 2;
        int pageAmount2 = 3;
        int pageAmount3 = 2;
        return new Object[][]{
                {ordersOnPage1, pageAmount1},
                {ordersOnPage2, pageAmount2},
                {ordersOnPage3, pageAmount3},
        };
    }

    @Test(dataProvider = "forFindWrongOrderPageAmount")
    public void testFindWrongOrderPageAmount(int ordersOnPage, int pageAmount) throws ServiceException {
        Assert.assertNotEquals(serviceFactory.getOrderService().findOrderPageAmount(ordersOnPage), pageAmount);
    }

    @DataProvider
    public Object[][] forFindCorrectOrderByUserIdPageAmount() {
        int ordersOnPage1 = 1;
        int ordersOnPage2 = 1;
        int pageAmount1 = 1;
        int pageAmount2 = 2;
        int userId1 = 4;
        int userId2 = 3;
        return new Object[][]{
                {ordersOnPage1, pageAmount1, userId1},
                {ordersOnPage2, pageAmount2, userId2}
        };
    }

    @Test(dataProvider = "forFindCorrectOrderByUserIdPageAmount")
    public void testFindCorrectOrderByUserIdPageAmount(int ordersOnPage, int pageAmount, int userId) throws ServiceException {
        Assert.assertEquals(serviceFactory.getOrderService().findOrderByUserIdPageAmount(ordersOnPage, userId), pageAmount);
    }

    @DataProvider
    public Object[][] forFindWrongOrderByUserIdPageAmount() {
        int ordersOnPage1 = 1;
        int ordersOnPage2 = 1;
        int pageAmount1 = 2;
        int pageAmount2 = 1;
        int userId1 = 4;
        int userId2 = 3;
        return new Object[][]{
                {ordersOnPage1, pageAmount1, userId1},
                {ordersOnPage2, pageAmount2, userId2}
        };
    }

    @Test(dataProvider = "forFindWrongOrderByUserIdPageAmount")
    public void testFindWrongOrderByUserIdPageAmount(int ordersOnPage, int pageAmount, int userId) throws ServiceException {
        Assert.assertNotEquals(serviceFactory.getOrderService().findOrderByUserIdPageAmount(ordersOnPage, userId), pageAmount);
    }

    @Test
    public void testFindOrdersAmount() throws ServiceException {
        double ordersAmount = 3;
        Assert.assertEquals(serviceFactory.getOrderService().findOrdersAmount(), ordersAmount);
    }

    @Test
    public void testFindOrdersCost() throws ServiceException {
        double ordersCost = 179.00;
        Assert.assertEquals(serviceFactory.getOrderService().findOrdersCost(), ordersCost);
    }

    @DataProvider
    public Object[][] forChangeDate() {
        String newDate1 = "2021-01-29T08:00:00";
        String newDate2 = "2021-01-30 14:00:00";
        String newDate3 = "2021-01-29T24:00:00";
        int orderId1 = 1;
        int orderId2 = 2;
        int orderId3 = 3;
        return new Object[][]{
                {newDate1, orderId1},
                {newDate2, orderId2},
                {newDate3, orderId3}
        };
    }

    @Test(expectedExceptions = ValidatorException.class, dataProvider = "forChangeDate")
    public void testChangePreparationTime(String newDate, int orderId) throws ServiceException, ValidatorException {
        serviceFactory.getOrderService().changePreparationTime(newDate, orderId);
    }

    @Test(expectedExceptions = ValidatorException.class, dataProvider = "forChangeDate")
    public void testChangeDeliveryTime(String newDate, int orderId) throws ServiceException, ValidatorException {
        serviceFactory.getOrderService().changeDeliveryTime(newDate, orderId);
    }
    @DataProvider
    public Object[][] forChangeStatus() {
        String newStatus1 = "";
        String newStatus2 = "1notready";
        String newStatus3 = "notr eady";
        int orderId1 = 1;
        int orderId2 = 2;
        int orderId3 = 3;
        return new Object[][]{
                {newStatus1, orderId1},
                {newStatus2, orderId2},
                {newStatus3, orderId3}
        };
    }

    @Test(expectedExceptions = ValidatorException.class, dataProvider = "forChangeStatus")
    public void testChangeStatus(String newStatus, int orderId) throws ServiceException, ValidatorException {
        serviceFactory.getOrderService().changeStatus(newStatus, orderId);
    }
}
