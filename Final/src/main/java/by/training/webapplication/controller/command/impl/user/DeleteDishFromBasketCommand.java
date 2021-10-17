package by.training.webapplication.controller.command.impl.user;

import by.training.webapplication.controller.command.Command;
import by.training.webapplication.controller.command.CommandResult;
import by.training.webapplication.service.exception.ServiceException;
import by.training.webapplication.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteDishFromBasketCommand implements Command {
    private static final String BASKET_PRODUCT_ID = "basketProductId";
    private static final String BASKET_PRODUCT_COST = "productCost";
    private static final String BASKET_ID = "basketId";
    private static final String BASKET_TOTAL = "basketTotal";
    private static Logger log = LogManager.getLogger(DeleteDishFromBasketCommand.class.getName());
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Deleting dish from basket started.");
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        int basketProductId = Integer.parseInt(request.getParameter(BASKET_PRODUCT_ID));
        int basketId = Integer.parseInt(request.getParameter(BASKET_ID));
        double productCost = Double.parseDouble(request.getParameter(BASKET_PRODUCT_COST));
        double basketTotal = Double.parseDouble(request.getParameter(BASKET_TOTAL));
        try {
            serviceFactory.getBasketProductService().deleteBasketProductById(basketProductId,basketTotal - productCost , basketId);
        } catch (ServiceException e) {
            log.error(this.getClass() + ":" + e.getMessage());
            return CommandResult.forward("/WEB-INF/jsp/common/error.jsp");
        }
        log.debug("Deleting dish from basket finished.");
        return CommandResult.redirect(request.getContextPath() + "controller?command=show_basket");
    }
}
