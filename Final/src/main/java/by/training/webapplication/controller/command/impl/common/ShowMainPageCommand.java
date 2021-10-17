package by.training.webapplication.controller.command.impl.common;

import by.training.webapplication.controller.command.Command;
import by.training.webapplication.controller.command.CommandResult;
import by.training.webapplication.service.exception.ServiceException;
import by.training.webapplication.service.factory.ServiceFactory;
import by.training.webapplication.bean.Dish;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowMainPageCommand implements Command {
    private static Logger log = LogManager.getLogger(ShowMainPageCommand.class);
    private static final String SORT_STATUS = "sortStatus";
    private static final String PRICE_INCREASE = "increasePrice";
    private static final String PRICE_DECREASE = "decreasePrice";
    private static final String DISHES = "dishes";
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Loading list of dishes started.");
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        HttpSession session = request.getSession();
        String value = (String) session.getAttribute(SORT_STATUS);
        List<Dish> dishes;
        try{
            dishes = serviceFactory.getDishService().getSortDishList(value, PRICE_INCREASE, PRICE_DECREASE);
            session.setAttribute(DISHES, dishes);
        }catch (ServiceException e){
            log.debug("Loading list of dishes started.");
            return CommandResult.forward("/WEB-INF/jsp/common/error.jsp");
        }
        log.debug("Loading list of dishes finished.");
        return CommandResult.forward("/WEB-INF/jsp/common/dishes.jsp");
    }
}
