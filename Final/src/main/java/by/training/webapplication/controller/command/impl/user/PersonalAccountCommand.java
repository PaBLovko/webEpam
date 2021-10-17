package by.training.webapplication.controller.command.impl.user;

import by.training.webapplication.controller.command.Command;
import by.training.webapplication.controller.command.CommandResult;
import by.training.webapplication.bean.OrderProduct;
import by.training.webapplication.bean.User;
import by.training.webapplication.service.exception.ServiceException;
import by.training.webapplication.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class PersonalAccountCommand implements Command {
    private static final String USER = "user";
    private static final String USER_ORDER_PRODUCTS = "userOrderProducts";
    private static final String PAGE = "page";
    private static final String COUNT = "count";
    private static final int AMOUNT = 5;
    private static Logger log = LogManager.getLogger(PersonalAccountCommand.class.getName());
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        log.info("User is logged in to the deliverer profile.");
        log.debug("Loading orders started.");
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        int page = Integer.parseInt(request.getParameter(PAGE));
        int userId = user.getId();
        List<OrderProduct> orderProducts;
        try {
            orderProducts = serviceFactory.getOrderProductService().findLimitOrderProductByUserId(userId, (page - 1) * AMOUNT, AMOUNT);
            request.setAttribute(USER_ORDER_PRODUCTS, orderProducts);
        } catch (ServiceException e) {
            log.error(this.getClass() + ":" + e.getMessage());
            return CommandResult.forward("/WEB-INF/jsp/common/error.jsp");
        }
        try {
            int count = serviceFactory.getOrderService().findOrderByUserIdPageAmount(AMOUNT, userId);
            request.setAttribute(COUNT, count);
        } catch (ServiceException e) {
            log.error(this.getClass() + ":" + e.getMessage());
            return CommandResult.forward("/WEB-INF/jsp/common/error.jsp");
        }
        request.setAttribute(PAGE, page);
        log.debug("Loading orders finished.");
        return CommandResult.forward("/WEB-INF/jsp/user/personal_account.jsp");
    }
}