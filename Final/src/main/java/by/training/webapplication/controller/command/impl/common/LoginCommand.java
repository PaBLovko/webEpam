package by.training.webapplication.controller.command.impl.common;

import by.training.webapplication.controller.command.Command;
import by.training.webapplication.controller.command.CommandResult;
import by.training.webapplication.service.exception.ServiceException;
import by.training.webapplication.service.factory.ServiceFactory;
import by.training.webapplication.bean.User;
import by.training.webapplication.service.exception.ValidatorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {
    private static Logger log = LogManager.getLogger(LoginCommand.class);
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String USER = "user";
    private static final String WRONG_LOGIN = "incorrectLogin";
    private static final String WRONG_DATA = "wrongMessage";
    private static final String WRONG = "wrong";
    private static final String NO_RECORDS = "No records";
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Login user started.");
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        HttpSession session = request.getSession();
        User user;
        try {
            user = serviceFactory.getUserService().login(login, password);
            session.setAttribute(USER, user);
        } catch (ValidatorException ex){
            log.error(ex.getMessage());
            session.setAttribute(WRONG, WRONG_DATA);
        }catch (ServiceException e){
            log.error(e.getMessage());
            if(e.getCause().getMessage().equals(NO_RECORDS)){
                session.setAttribute(WRONG, WRONG_LOGIN);
            } else {
                return CommandResult.forward("/WEB-INF/jsp/common/error.jsp");
            }
        }
        log.debug("Login user finished.");
        return CommandResult.redirect(request.getContextPath() + "controller?command=show_main_page");
    }
}
