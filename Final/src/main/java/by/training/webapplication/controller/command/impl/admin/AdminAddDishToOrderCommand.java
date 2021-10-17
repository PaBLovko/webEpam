package by.training.webapplication.controller.command.impl.admin;

import by.training.webapplication.controller.command.Command;
import by.training.webapplication.controller.command.CommandResult;
import by.training.webapplication.bean.Basket;
import by.training.webapplication.bean.User;
import by.training.webapplication.service.exception.ServiceException;
import by.training.webapplication.service.exception.ValidatorException;
import by.training.webapplication.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminAddDishToOrderCommand implements Command {
    private static final String DISH_ID = "dishId";
    private static final String DISH_PRICE = "dishPrice";
    private static final String USER_FOR_ORDER = "userForOrder";
    private static final String DISH_AMOUNT = "dishAmount";
    private static final String RIGHT = "right";
    private static final String WRONG = "wrong";
    private static final String WRONG_AMOUNT_MESSAGE = "wrongAmount";
    private static final String RIGHT_AMOUNT_MESSAGE = "rightAmount";
    private static Logger log = LogManager.getLogger(AdminAddDishToOrderCommand.class.getName());

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Adding dish to order started.");
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER_FOR_ORDER);
        int dishId = Integer.parseInt(request.getParameter(DISH_ID));
        double dishPrice = Double.parseDouble(request.getParameter(DISH_PRICE));
        String amount = request.getParameter(DISH_AMOUNT);
        Basket basket;
        try {
            basket = serviceFactory.getBasketService().findBasketByUserLogin(user.getLogin());
            int basketId = basket.getId();
            int dishAmount = Integer.parseInt(amount);
            double cost = dishAmount * dishPrice;
            double total = basket.getTotal();
            serviceFactory.getBasketProductService().saveBasketProduct(basketId, dishId, amount, dishPrice, (total + cost));
            session.setAttribute(RIGHT, RIGHT_AMOUNT_MESSAGE);
        } catch (ValidatorException ex){
            log.error(this.getClass() + ":" + ex.getMessage());
            session.setAttribute(WRONG, WRONG_AMOUNT_MESSAGE);
        }
        catch (ServiceException e) {
            log.error(this.getClass() + ":" + e.getMessage());
            return CommandResult.forward("/WEB-INF/jsp/common/error.jsp");
        }
        log.debug("Adding dish to order finished.");
        return CommandResult.redirect(request.getContextPath() + "controller?command=admin_add_new_order");
    }
}
