package by.training.webapplication.controller.command.impl.user;

import by.training.webapplication.bean.BasketProduct;
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
import java.util.List;

public class AddDishCommand implements Command {
    private static final String DISH_ID = "dishId";
    private static final String DISH_PRICE = "dishPrice";
    private static final String DISH_AMOUNT = "dishAmount";
    private static final String USER = "user";
    private static final String RIGHT = "right";
    private static final String WRONG = "wrong";
    private static final String WRONG_AMOUNT_MESSAGE = "wrongAmount";
    private static final String RIGHT_AMOUNT_MESSAGE = "rightAmount";
    private static Logger log = LogManager.getLogger(AddOrderCommand.class.getName());

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Adding dish to basket started.");
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        String amount = request.getParameter(DISH_AMOUNT);
        int dishId = Integer.parseInt(request.getParameter(DISH_ID));
        double dishPrice = Double.parseDouble(request.getParameter(DISH_PRICE));
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        Basket basket;
        try {
            basket = serviceFactory.getBasketService().findBasketByUserLogin(user.getLogin());
            int basketId = basket.getId();
//            List<BasketProduct> basketProducts = serviceFactory.getBasketProductService().findProductByBasketId(basketId);
            int dishAmount = Integer.parseInt(amount);
            double cost = dishAmount * dishPrice;
            double total = basket.getTotal();
            serviceFactory.getBasketProductService().saveBasketProduct(basketId, dishId, amount, dishPrice, (total + cost));
            session.setAttribute(RIGHT, RIGHT_AMOUNT_MESSAGE);
        } catch (ValidatorException e) {
            log.error(this.getClass() + ":" + e.getMessage());
            session.setAttribute(WRONG, WRONG_AMOUNT_MESSAGE);
        } catch (ServiceException e) {
            log.error(this.getClass() + ":" + e.getMessage());
            return CommandResult.forward("/WEB-INF/jsp/common/error.jsp");
        }
        log.debug("Adding dish to basket finished.");
        return CommandResult.redirect(request.getContextPath() + "controller?command=show_main_page");
    }
}
