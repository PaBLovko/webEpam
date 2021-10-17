package by.training.webapplication.controller.command.impl.admin;

import by.training.webapplication.controller.command.Command;
import by.training.webapplication.controller.command.CommandResult;
import by.training.webapplication.service.exception.ServiceException;
import by.training.webapplication.service.factory.ServiceFactory;
import by.training.webapplication.bean.Dish;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminDishesCommand implements Command {
    private static final String DISHES = "dishes";
    private static Logger log = LogManager.getLogger(AdminDishesCommand.class.getName());
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Login to edit dishes started.");
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        List<Dish> dishes;
        try {
            dishes = serviceFactory.getDishService().showAllDishes();
        } catch (ServiceException e) {
            log.error(this.getClass() + ":" + e.getMessage());
            return CommandResult.forward("/WEB-INF/jsp/common/error.jsp");
        }
        request.setAttribute(DISHES, dishes);
        log.debug("Login to edit dishes finished.");
        return CommandResult.forward("/WEB-INF/jsp/admin/admin_dishes.jsp");
    }
}
