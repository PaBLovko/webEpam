package by.training.webapplication.controller.command.impl.user;

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

public class ChangePhoneCommand implements Command {
    private static final String USER = "user";
    private static final String NEW_PHONE = "newPhone";
    private static final String RIGHT = "right";
    private static final String WRONG = "wrong";
    private static final String WRONG_PHONE_MESSAGE = "wrongPhone";
    private static final String RIGHT_PHONE_MESSAGE = "rightPhone";
    private static final String PAGE = "page";
    private static Logger log = LogManager.getLogger(ChangePhoneCommand.class.getName());
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Changing phone started.");
        String page = request.getParameter(PAGE);
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        String newPhone = request.getParameter(NEW_PHONE);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        int userId = user.getId();
        try {
            serviceFactory.getUserService().changePhone(newPhone, userId);
            user.setPhone(newPhone);
            session.setAttribute(USER, user);
            session.setAttribute(RIGHT, RIGHT_PHONE_MESSAGE);
        } catch (ValidatorException ex){
            log.error(this.getClass() + ":" + ex.getMessage());
            session.setAttribute(WRONG, WRONG_PHONE_MESSAGE);
        } catch (ServiceException e) {
            log.error(this.getClass() + ":" + e.getMessage());
            return CommandResult.forward("/WEB-INF/jsp/common/error.jsp");
        }
        log.debug("Changing phone finished.");
        return CommandResult.redirect(request.getContextPath() + "controller?command=personal_account&page=" + page);
    }
}
