package by.training.webapplication.controller.command.impl.admin;

import by.training.webapplication.controller.command.Command;
import by.training.webapplication.service.exception.ServiceException;
import by.training.webapplication.service.factory.ServiceFactory;
import by.training.webapplication.controller.command.CommandResult;
import by.training.webapplication.bean.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminUsersIncreasePageCommand implements Command {
    private static final String USERS = "users";
    private static final String PAGE = "page";
    private static final String COUNT = "count";
    private static final int AMOUNT = 5;
    private static Logger log = LogManager.getLogger(AdminUsersIncreasePageCommand.class.getName());

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Page number increase for users started.");
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        int currentPage = Integer.parseInt(request.getParameter(PAGE));
        int increasePage = currentPage + 1;
        int count = Integer.parseInt(request.getParameter(COUNT));
        List<User> users;
            if (increasePage <= count) {
                try {
                    users = serviceFactory.getUserService().findLimitUser((increasePage - 1) * AMOUNT, AMOUNT);
                    request.setAttribute(USERS, users);
                } catch (ServiceException e) {
                    log.error(this.getClass() + ":" + e.getMessage());
                    return CommandResult.forward("/WEB-INF/jsp/common/error.jsp");
                }
                request.setAttribute(PAGE, increasePage);
            } else {
                try {
                    users = serviceFactory.getUserService().findLimitUser((currentPage - 1) * AMOUNT, AMOUNT);
                    request.setAttribute(USERS, users);
                } catch (ServiceException e) {
                    log.error(this.getClass() + ":" + e.getMessage());
                    return CommandResult.forward("/WEB-INF/jsp/common/error.jsp");
                }
                request.setAttribute(PAGE, currentPage);
            }
            request.setAttribute(COUNT, count);
        log.debug("Page number increase for users finished.");
            return CommandResult.forward("/WEB-INF/jsp/admin/admin_users.jsp");
    }
}
