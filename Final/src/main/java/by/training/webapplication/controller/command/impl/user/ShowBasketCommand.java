package by.training.webapplication.controller.command.impl.user;

import by.training.webapplication.controller.command.Command;
import by.training.webapplication.service.exception.ServiceException;
import by.training.webapplication.service.factory.ServiceFactory;
import by.training.webapplication.controller.command.CommandResult;
import by.training.webapplication.bean.Basket;
import by.training.webapplication.bean.BasketProduct;
import by.training.webapplication.bean.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowBasketCommand implements Command {
    private static final String BASKET_PRODUCT = "basketProducts";
    private static final String TOTAL = "total";
    private static final String USER = "user";
    private static Logger log = LogManager.getLogger(ShowBasketCommand.class.getName());
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Loading products from basket started.");
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        Basket basket;
        try {
            basket = serviceFactory.getBasketService().findBasketByUserLogin(user.getLogin());
            int basketId = basket.getId();
            double total = basket.getTotal();
            List<BasketProduct> basketProducts = serviceFactory.getBasketProductService().findProductByBasketId(basketId);
            session.setAttribute(TOTAL, total);
            session.setAttribute(BASKET_PRODUCT, basketProducts);
        } catch (ServiceException e) {
            log.error(this.getClass() + ":" + e.getMessage());
            return CommandResult.forward("/WEB-INF/jsp/common/error.jsp");
        }
        log.debug("Loading products from basket finished.");
        return CommandResult.forward("/WEB-INF/jsp/user/basket.jsp");
    }
}
