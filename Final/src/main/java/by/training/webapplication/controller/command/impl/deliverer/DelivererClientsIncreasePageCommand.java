package by.training.webapplication.controller.command.impl.deliverer;

import by.training.webapplication.controller.command.Command;
import by.training.webapplication.controller.command.CommandResult;
import by.training.webapplication.bean.User;
import by.training.webapplication.service.exception.ServiceException;
import by.training.webapplication.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DelivererClientsIncreasePageCommand implements Command {
    private static final String CLIENTS = "clients";
    private static final String PAGE = "page";
    private static final String COUNT = "count";
    private static final int AMOUNT = 5;
    private static Logger log = LogManager.getLogger(DelivererClientsIncreasePageCommand.class.getName());
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Page number increase for clients started.");
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        int currentPage = Integer.parseInt(request.getParameter(PAGE));
        int increasePage = currentPage + 1;
        int count = Integer.parseInt(request.getParameter(COUNT));
        List<User> clients;
        if (increasePage <= count) {
            try {
                clients = serviceFactory.getUserService().findLimitClients((increasePage - 1) * AMOUNT, AMOUNT);
                request.setAttribute(CLIENTS, clients);
            } catch (ServiceException e) {
                log.error(this.getClass() + ":" + e.getMessage());
                return CommandResult.forward("/WEB-INF/jsp/common/error.jsp");
            }
            request.setAttribute(PAGE, increasePage);
        } else {
            try {
                clients = serviceFactory.getUserService().findLimitClients((currentPage - 1) * AMOUNT, AMOUNT);
                request.setAttribute(CLIENTS, clients);
            } catch (ServiceException e) {
                log.error(this.getClass() + ":" + e.getMessage());
                return CommandResult.forward("/WEB-INF/jsp/common/error.jsp");
            }
            request.setAttribute(PAGE, currentPage);
        }
        request.setAttribute(COUNT, count);
        log.debug("Page number increase for clients finished.");
        return CommandResult.forward("/WEB-INF/jsp/deliverer/deliverer_clients.jsp");
    }
}
