package by.training.webapplication.controller.command.impl.user;

import by.training.webapplication.controller.command.Command;
import by.training.webapplication.controller.command.CommandResult;
import by.training.webapplication.bean.Basket;
import by.training.webapplication.bean.User;
import by.training.webapplication.service.exception.ServiceException;
import by.training.webapplication.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ClearBasketCommand implements Command {
    private static final String USER = "user";
    private static final double TOTAL = 0.0;
    private static final String BASKET_PRODUCT = "basketProducts";
    private static Logger log = LogManager.getLogger(ClearBasketCommand.class.getName());

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Basket cleaning started.");
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        Basket basket;
        try {
            basket = serviceFactory.getBasketService().findBasketByUserLogin(user.getLogin());
            int basketId = basket.getId();
            serviceFactory.getBasketProductService().clearBasket(basketId, TOTAL);
            session.removeAttribute(BASKET_PRODUCT);
        } catch (ServiceException e) {
            log.error(this.getClass() + ":" + e.getMessage());
            return CommandResult.forward("/WEB-INF/jsp/common/error.jsp");
        }
        log.debug("Basket cleaning started.");
        return CommandResult.redirect(request.getContextPath() + "controller?command=show_basket");
    }
}
