package by.training.webapplication.controller.command.impl.deliverer;

import by.training.webapplication.controller.command.Command;
import by.training.webapplication.controller.command.CommandResult;
import by.training.webapplication.service.exception.ServiceException;
import by.training.webapplication.service.factory.ServiceFactory;
import by.training.webapplication.service.exception.ValidatorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangeOrderStatusCommand implements Command {
    private static final String CHANGE_STATUS = "changeStatus";
    private static final String ID_ORDER = "changeId";
    private static final String RIGHT = "right";
    private static final String WRONG = "wrong";
    private static final String WRONG_MESSAGE = "wrongMessage";
    private static final String RIGHT_MESSAGE = "rightStatus";
    private static Logger log = LogManager.getLogger(ChangeOrderStatusCommand.class.getName());

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Changing order status started.");
        HttpSession session = request.getSession();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        String newStatus = request.getParameter(CHANGE_STATUS);
        int orderId = Integer.parseInt(request.getParameter(ID_ORDER));
        if(!newStatus.isEmpty()){
            try {
                serviceFactory.getOrderService().changeStatus(newStatus, orderId);
                session.setAttribute(RIGHT, RIGHT_MESSAGE);
            } catch (ValidatorException ex) {
                log.error(this.getClass() + ":" + ex.getMessage());
                session.setAttribute(WRONG, WRONG_MESSAGE);
            } catch (ServiceException e) {
                log.error(this.getClass() + ":" + e.getMessage());
                return CommandResult.forward("/WEB-INF/jsp/common/error.jsp");
            }
        }else {
            session.setAttribute(WRONG, WRONG_MESSAGE);
        }
        log.debug("Changing order status finished.");
        return CommandResult.redirect(request.getContextPath() + "controller?command=deliverer_order&page=1");
    }
}
