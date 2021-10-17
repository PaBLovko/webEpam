package by.training.webapplication.controller.command.impl.user;

import by.training.webapplication.service.factory.ServiceFactory;
import by.training.webapplication.controller.command.Command;
import by.training.webapplication.controller.command.CommandResult;
import by.training.webapplication.bean.User;
import by.training.webapplication.service.exception.ServiceException;
import by.training.webapplication.service.exception.ValidatorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

public class AddCommentCommand implements Command {
    private static final String USER = "user";
    private static final String REVIEW = "review";
    private static final String WRONG = "wrong";
    private static final String RIGHT = "right";
    private static final String WRONG_COMMENT = "wrongMessage";
    private static final String RIGHT_COMMENT = "rightComment";
    private static Logger log = LogManager.getLogger(AddCommentCommand.class.getName());
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Adding comment started.");
        String value = request.getParameter(REVIEW);
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        try {
            serviceFactory.getCommentService().save(user.getId(), LocalDateTime.now(), value);
            session.setAttribute(RIGHT, RIGHT_COMMENT);
        }  catch (ValidatorException ex){
            log.error(this.getClass() + ":" + ex.getMessage());
            session.setAttribute(WRONG, WRONG_COMMENT);

        } catch (ServiceException e) {
            log.error(this.getClass() + ":" + e.getMessage());
            return CommandResult.forward("/WEB-INF/jsp/common/error.jsp");
        }
        log.debug("Adding comment finished.");
        return CommandResult.redirect(request.getContextPath() + "controller?command=show_comment&page=1");
    }
}
