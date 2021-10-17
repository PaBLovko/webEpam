package by.training.webapplication.controller.command.impl.admin;

import by.training.webapplication.controller.command.Command;
import by.training.webapplication.controller.command.CommandResult;
import by.training.webapplication.service.exception.ServiceException;
import by.training.webapplication.service.factory.ServiceFactory;
import by.training.webapplication.bean.Dish;
import by.training.webapplication.service.exception.ValidatorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class FindDishByIdCommand implements Command {
    private static final String DISH_ID = "dishId";
    private static final String DISHES = "dishes";
    private static final String WRONG = "wrong";
    private static final String WRONG_MESSAGE = "wrongMessage";
    private static final String WRONG_ID = "wrongId";
    private static final String NO_RECORDS = "No records";
    private static Logger log = LogManager.getLogger(FindDishByIdCommand.class.getName());
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Search dish by id started.");
        HttpSession session = request.getSession();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        String dishId = request.getParameter(DISH_ID);
        List<Dish> dishes = new ArrayList<>();
        Dish dish;
        try {
            dish = serviceFactory.getDishService().findDishById(dishId);
            dishes.add(dish);
        } catch (ValidatorException ex){
            log.error(this.getClass() + ":" + ex.getMessage());
            session.setAttribute(WRONG, WRONG_MESSAGE);
            return CommandResult.redirect(request.getContextPath() + "controller?command=admin_dishes");
        } catch (ServiceException e) {
            log.error(this.getClass() + ":" + e.getMessage());
            if(e.getCause().getMessage().equals(NO_RECORDS)){
                session.setAttribute(WRONG, WRONG_ID);
                return CommandResult.redirect(request.getContextPath() + "controller?command=admin_dishes");
            } else {
                return CommandResult.forward("/WEB-INF/jsp/common/error.jsp");
            }
        }
        request.setAttribute(DISHES, dishes);
        log.debug("Search dish by id finished.");
        return CommandResult.forward("/WEB-INF/jsp/admin/admin_dishes.jsp");
    }
}
