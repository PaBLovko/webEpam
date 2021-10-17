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

public class SortByPriceDecreaseCommand implements Command {
    private static final String SORT_STATUS = "sortStatus";
    private static final String PRICE_DECREASE = "decreasePrice";
    private static Logger log = LogManager.getLogger(SortByPriceDecreaseCommand.class);
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Loading list of dishes sorted by reduction price started.");
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        List<Dish> dishes;
        HttpSession session = request.getSession();
        try{
            dishes = serviceFactory.getDishService().sortByPriceDecrease();
        }catch (ServiceException e){
            log.error(e.getMessage());
            return CommandResult.forward("/WEB-INF/jsp/common/error.jsp");
        }
        session.setAttribute(SORT_STATUS,PRICE_DECREASE);
        session.setAttribute("dishes", dishes);
        log.debug("Loading list of dishes sorted by reduction price finished.");
        return CommandResult.forward("/WEB-INF/jsp/common/dishes.jsp");
    }
}
