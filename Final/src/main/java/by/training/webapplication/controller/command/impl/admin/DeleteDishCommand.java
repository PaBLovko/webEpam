package by.training.webapplication.controller.command.impl.admin;

import by.training.webapplication.controller.command.Command;
import by.training.webapplication.controller.command.CommandResult;
import by.training.webapplication.service.exception.ServiceException;
import by.training.webapplication.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteDishCommand implements Command {
    private static Logger log = LogManager.getLogger(DeleteDishCommand.class.getName());
    private static final String DEL_ID = "delId";
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Deleting dish started.");
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        int dishId = Integer.parseInt(request.getParameter(DEL_ID));
        try {
            serviceFactory.getDishService().deleteDish(dishId);
        } catch (ServiceException e) {
            log.error(this.getClass() + ":" + e.getMessage());
            return CommandResult.forward("/WEB-INF/jsp/common/error.jsp");
        }
        log.debug("Deleting dish finished.");
        return CommandResult.redirect(request.getContextPath() + "controller?command=admin_dishes");
    }
}
