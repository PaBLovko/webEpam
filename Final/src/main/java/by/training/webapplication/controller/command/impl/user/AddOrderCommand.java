package by.training.webapplication.controller.command.impl.user;

import by.training.webapplication.controller.command.Command;
import by.training.webapplication.controller.command.CommandResult;
import by.training.webapplication.bean.BasketProduct;
import by.training.webapplication.bean.StatusEnum;
import by.training.webapplication.bean.User;
import by.training.webapplication.service.exception.ServiceException;
import by.training.webapplication.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AddOrderCommand implements Command {
    private static final String USER = "user";
    private static final double TOTAL = 0.0;
    private static final String BASKET_TOTAL = "total";
    private static final String BASKET_PRODUCT = "basketProducts";
    private static Logger log = LogManager.getLogger(AddOrderCommand.class.getName());
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Adding order started.");
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        double basketTotal = Double.parseDouble(request.getParameter(BASKET_TOTAL));
        HttpSession session = request.getSession();
        List<BasketProduct> basketProducts = (List<BasketProduct>) session.getAttribute(BASKET_PRODUCT);
        User user = (User) session.getAttribute(USER);
        int userId = user.getId();
        String login = user.getLogin();
        if (basketTotal == 0) {
            return CommandResult.forward("/WEB-INF/jsp/user/basket.jsp");
        } else {
            try {
                serviceFactory.getOrderService().saveOrder(userId, basketTotal, null, null, StatusEnum.NOT_READY.getValue(), basketProducts, login, TOTAL);
            }catch (ServiceException e){
                log.error(this.getClass() + ":" + e.getMessage());
                return CommandResult.forward("/WEB-INF/jsp/common/error.jsp");
            }
        }
        log.debug("Adding order finished.");
        return CommandResult.redirect(request.getContextPath() + "controller?command=show_basket");
    }
}
