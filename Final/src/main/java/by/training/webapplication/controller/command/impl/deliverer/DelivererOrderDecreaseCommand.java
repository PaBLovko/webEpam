package by.training.webapplication.controller.command.impl.deliverer;

import by.training.webapplication.controller.command.Command;
import by.training.webapplication.controller.command.CommandResult;
import by.training.webapplication.bean.OrderProduct;
import by.training.webapplication.service.exception.ServiceException;
import by.training.webapplication.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DelivererOrderDecreaseCommand implements Command {
    private static final String ORDER_PRODUCTS = "orderProducts";
    private static final String PAGE = "page";
    private static final String COUNT = "count";
    private static final int AMOUNT = 5;
    private static Logger log = LogManager.getLogger(DelivererOrderDecreaseCommand.class.getName());
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Page number reduction for orders started.");
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        int currentPage = Integer.parseInt(request.getParameter(PAGE));
        int decreasePage = currentPage - 1;
        int count = Integer.parseInt(request.getParameter(COUNT));
        List<OrderProduct> orderProducts;
        if (decreasePage >= 1) {
            try {
                orderProducts= serviceFactory.getOrderProductService().findLimitOrderProduct((decreasePage - 1) * AMOUNT, AMOUNT);
                request.setAttribute(ORDER_PRODUCTS, orderProducts);
            } catch (ServiceException e) {
                log.error(this.getClass() + ":" + e.getMessage());
                return CommandResult.forward("/WEB-INF/jsp/common/error.jsp");
            }
            request.setAttribute(PAGE, decreasePage);
        } else {
            try {
                orderProducts = serviceFactory.getOrderProductService().findLimitOrderProduct((currentPage - 1) * AMOUNT, AMOUNT);
                request.setAttribute(ORDER_PRODUCTS, orderProducts);
            } catch (ServiceException e) {
                log.error(this.getClass() + ":" + e.getMessage());
                return CommandResult.forward("/WEB-INF/jsp/common/error.jsp");
            }
            request.setAttribute(PAGE, currentPage);
        }
        request.setAttribute(COUNT, count);
        log.debug("Page number reduction for orders finished.");
        return CommandResult.forward("/WEB-INF/jsp/deliverer/deliverer_order.jsp");
    }
}
