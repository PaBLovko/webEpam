package by.training.webapplication.service.factory;

import by.training.webapplication.service.api.*;
import by.training.webapplication.dao.DaoHelperFactory;
import by.training.webapplication.service.impl.*;


public final class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final DishService dishService = new DishServiceImpl(new DaoHelperFactory());
    private final UserService userService = new UserServiceImpl(new DaoHelperFactory());
    private final CommentService commentService = new CommentServiceImpl(new DaoHelperFactory());
    private final BasketService basketService = new BasketServiceImpl(new DaoHelperFactory());
    private final OrderService orderService = new OrderServiceImpl(new DaoHelperFactory());
    private final BasketProductService basketProductService = new BasketProductServiceImpl(new DaoHelperFactory());
    private final OrderProductService orderProductService = new OrderProductServiceImpl(new DaoHelperFactory());

    private ServiceFactory() {
    }
    public OrderProductService getOrderProductService() {
        return orderProductService;
    }
    public OrderService getOrderService() {
        return orderService;
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public DishService getDishService() {
        return dishService;
    }

    public UserService getUserService() {
        return userService;
    }
    public CommentService getCommentService(){return commentService;}
    public BasketService getBasketService() {
        return basketService;
    }

    public BasketProductService getBasketProductService() {
        return basketProductService;
    }
}
