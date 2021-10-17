package by.training.webapplication.controller.command.impl.admin;

import by.training.webapplication.controller.command.Command;
import by.training.webapplication.service.exception.ServiceException;
import by.training.webapplication.service.factory.ServiceFactory;
import by.training.webapplication.controller.command.CommandResult;
import by.training.webapplication.bean.Basket;
import by.training.webapplication.bean.BasketProduct;
import by.training.webapplication.bean.Dish;
import by.training.webapplication.bean.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AdminAddNewOrderCommand implements Command {
    private static final String USER_FOR_ORDER = "userForOrder";
    private static final String DISHES = "dishes";
    private static final String TOTAL = "total";
    private static final String BASKET_PRODUCT = "basketProducts";
    private static Logger log = LogManager.getLogger(AdminAddNewOrderCommand.class.getName());

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Adding a new order started.");
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER_FOR_ORDER);
        List<Dish> dishes;
        try {
            dishes = serviceFactory.getDishService().showAllDishes();
            request.setAttribute(DISHES, dishes);
        } catch (ServiceException e) {
            log.error(this.getClass() + ":" + e.getMessage());
            return CommandResult.forward("/WEB-INF/jsp/common/error.jsp");
        }
        Basket basket;

        try {
            basket = serviceFactory.getBasketService().findBasketByUserLogin(user.getLogin());
            int basketId = basket.getId();
            double total = basket.getTotal();
            List<BasketProduct> basketProducts = serviceFactory.getBasketProductService().findProductByBasketId(basketId);
            session.setAttribute(BASKET_PRODUCT, basketProducts);
            request.setAttribute(TOTAL, total);
        } catch (ServiceException e) {
            log.error(this.getClass() + ":" + e.getMessage());
            return CommandResult.forward("/WEB-INF/jsp/common/error.jsp");
        }
        log.debug("Adding a new order finished.");
        return CommandResult.forward("/WEB-INF/jsp/admin/admin_add_new_order.jsp");
    }
}
