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

public class ChangeSurnameCommand implements Command {
    private static final String USER = "user";
    private static final String NEW_SURNAME = "newSurname";
    private static final String RIGHT = "right";
    private static final String WRONG = "wrong";
    private static final String WRONG_SURNAME_MESSAGE = "wrongSurname";
    private static final String RIGHT_SURNAME_MESSAGE = "rightSurname";
    private static final String PAGE = "page";
    private static Logger log = LogManager.getLogger(ChangeSurnameCommand.class.getName());

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Changing surname started.");
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        String page = request.getParameter(PAGE);
        String newSurname = request.getParameter(NEW_SURNAME);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        int userId = user.getId();
        try {
            serviceFactory.getUserService().changeSurname(newSurname, userId);
            user.setSurname(newSurname);
            session.setAttribute(USER, user);
            session.setAttribute(RIGHT, RIGHT_SURNAME_MESSAGE);
        } catch (ValidatorException ex){
            log.error(this.getClass() + ":" + ex.getMessage());
            session.setAttribute(WRONG, WRONG_SURNAME_MESSAGE);
        } catch (ServiceException e) {
            log.error(this.getClass() + ":" + e.getMessage());
            return CommandResult.forward("/WEB-INF/jsp/common/error.jsp");
        }
        log.debug("Changing surname finished.");
        return CommandResult.redirect(request.getContextPath() + "controller?command=personal_account&page=" + page);
    }
}
