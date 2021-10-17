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

public class ChangePatronymicCommand implements Command {
    private static final String USER = "user";
    private static final String NEW_PATRONYMIC = "newPatronymic";
    private static final String RIGHT = "right";
    private static final String WRONG = "wrong";
    private static final String WRONG_PATRONYMIC_MESSAGE = "wrongPatronymic";
    private static final String RIGHT_PATRONYMIC_MESSAGE = "rightPatronymic";
    private static final String PAGE = "page";
    private static Logger log = LogManager.getLogger(ChangePatronymicCommand.class.getName());
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Changing patronymic started.");
        String page = request.getParameter(PAGE);
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        String newPatronymic = request.getParameter(NEW_PATRONYMIC);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        int userId = user.getId();
        try {
            serviceFactory.getUserService().changePatronymic(newPatronymic, userId);
            user.setPatronymic(newPatronymic);
            session.setAttribute(USER, user);
            session.setAttribute(RIGHT, RIGHT_PATRONYMIC_MESSAGE);
        } catch (ValidatorException ex){
            log.error(this.getClass() + ":" + ex.getMessage());
            session.setAttribute(WRONG, WRONG_PATRONYMIC_MESSAGE);
        } catch (ServiceException e) {
            log.error(this.getClass() + ":" + e.getMessage());
            return CommandResult.forward("/WEB-INF/jsp/common/error.jsp");
        }
        log.debug("Changing patronymic finished.");
        return CommandResult.redirect(request.getContextPath() + "controller?command=personal_account&page=" + page);
    }
}
