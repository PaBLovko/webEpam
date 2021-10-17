package by.training.webapplication.controller.command.impl.admin;

import by.training.webapplication.controller.command.Command;
import by.training.webapplication.controller.command.CommandResult;
import by.training.webapplication.bean.User;
import by.training.webapplication.service.exception.ServiceException;
import by.training.webapplication.service.exception.ValidatorException;
import by.training.webapplication.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminAddNewUserForOrder implements Command {
    private static final String USER_FOR_ORDER_ID = "userForOrderId";
    private static final String USER_FOR_ORDER = "userForOrder";
    private static final String WRONG = "wrong";
    private static final String WRONG_MESSAGE = "wrongMessage";
    private static final String WRONG_ID = "wrongId";
    private static final String NO_RECORDS = "No records";
    private static Logger log = LogManager.getLogger(AdminAddNewUserForOrder.class.getName());

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Adding user for new order started.");
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        HttpSession session = request.getSession();
        String userId = request.getParameter(USER_FOR_ORDER_ID);
        User user;
        try {
            user = serviceFactory.getUserService().findClientById(userId);
            session.setAttribute(USER_FOR_ORDER, user);
        } catch (ValidatorException ex){
            log.error(this.getClass() + ":" + ex.getMessage());
            session.setAttribute(WRONG, WRONG_MESSAGE);
            return CommandResult.redirect(request.getContextPath() + "controller?command=admin_account");
        } catch (ServiceException e) {
            log.error(this.getClass() + ":" + e.getMessage());
            if(e.getCause().getMessage().equals(NO_RECORDS)){
                session.setAttribute(WRONG, WRONG_ID);
                return CommandResult.redirect(request.getContextPath() + "controller?command=admin_account");
            } else {
                return CommandResult.forward("/WEB-INF/jsp/common/error.jsp");
            }
        }
        log.debug("Adding user for new order finished.");
        return CommandResult.redirect(request.getContextPath() + "controller?command=admin_add_new_order");
    }
}
