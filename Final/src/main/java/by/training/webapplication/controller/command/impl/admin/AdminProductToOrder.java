package by.training.webapplication.controller.command.impl.admin;

import by.training.webapplication.controller.command.Command;
import by.training.webapplication.service.exception.ServiceException;
import by.training.webapplication.service.factory.ServiceFactory;
import by.training.webapplication.controller.command.CommandResult;
import by.training.webapplication.bean.BasketProduct;
import by.training.webapplication.bean.StatusEnum;
import by.training.webapplication.bean.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AdminProductToOrder implements Command {
    private static final String USER_FOR_ORDER = "userForOrder";
    private static final double TOTAL = 0.0;
    private static final String BASKET_TOTAL = "total";
    private static final String BASKET_PRODUCT = "basketProducts";
    private static Logger log = LogManager.getLogger(AdminProductToOrder.class.getName());

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Adding products to order started.");
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER_FOR_ORDER);
        int userId = user.getId();
        String login = user.getLogin();
        double basketTotal = Double.parseDouble(request.getParameter(BASKET_TOTAL));
        List<BasketProduct> basketProducts = (List<BasketProduct>) session.getAttribute(BASKET_PRODUCT);
        if (basketTotal == 0) {
            return CommandResult.redirect(request.getContextPath() + "controller?command=admin_add_new_order");
        } else {
            try {
                serviceFactory.getOrderService().saveOrder(userId, basketTotal, null, null, StatusEnum.NOT_READY.getValue(), basketProducts, login, TOTAL);
            } catch (ServiceException e) {
                log.error(this.getClass() + ":" + e.getMessage());
                return CommandResult.forward("/WEB-INF/jsp/common/error.jsp");
            }
        }
        session.removeAttribute(BASKET_PRODUCT);
        log.debug("Adding products to order finished.");
        return CommandResult.redirect(request.getContextPath() + "controller?command=admin_add_new_order");
    }
}
