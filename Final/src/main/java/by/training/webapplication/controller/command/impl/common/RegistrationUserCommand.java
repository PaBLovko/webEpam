package by.training.webapplication.controller.command.impl.common;

import by.training.webapplication.controller.command.Command;
import by.training.webapplication.controller.command.CommandResult;
import by.training.webapplication.service.exception.LoginIsNotFreeException;
import by.training.webapplication.service.exception.ServiceException;
import by.training.webapplication.service.factory.ServiceFactory;
import by.training.webapplication.service.exception.ValidatorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegistrationUserCommand implements Command {
    private static final String SAVE_LOGIN = "saveLogin";
    private static final String SAVE_PASSWORD = "savePassword";
    private static final String USER_ROLE = "3";
    private static final double TOTAL = 0.00;
    private static final String SAVE_SURNAME = "saveSurname";
    private static final String SAVE_NAME = "saveName";
    private static final String SAVE_PATRONYMIC = "savePatronymic";
    private static final String SAVE_ADDRESS = "saveAddress";
    private static final String SAVE_PHONE = "savePhone";
    private static final String SAVE_NOTE = "saveNote";
    private static final String WRONG = "wrong";
    private static final String RIGHT = "right";
    private static final String WRONG_LOGIN = "wrongLogin";
    private static final String WRONG_DATA = "wrongMessage";
    private static final String RIGHT_MESSAGE = "rightRegistration";
    private static Logger log = LogManager.getLogger(RegistrationUserCommand.class);
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("User registration started.");
        HttpSession session = request.getSession();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        String login = request.getParameter(SAVE_LOGIN);
        String password = request.getParameter(SAVE_PASSWORD);
        String surname = request.getParameter(SAVE_SURNAME);
        String name = request.getParameter(SAVE_NAME);
        String patronymic = request.getParameter(SAVE_PATRONYMIC);
        String address = request.getParameter(SAVE_ADDRESS);
        String phone = request.getParameter(SAVE_PHONE);
        String note = request.getParameter(SAVE_NOTE);

        try {
            serviceFactory.getUserService().addUser(login, password, USER_ROLE, surname, name, patronymic, address, phone, note, TOTAL);
            session.setAttribute(RIGHT, RIGHT_MESSAGE);
        } catch (ValidatorException ex){
            log.error(ex.getMessage());
            session.setAttribute(WRONG, WRONG_DATA);
            return CommandResult.redirect(request.getContextPath() + "controller?command=registration");
        } catch (LoginIsNotFreeException exc){
            log.error(exc.getMessage());
            session.setAttribute(WRONG, WRONG_LOGIN);
            return CommandResult.redirect(request.getContextPath() + "controller?command=registration");
        } catch (ServiceException e) {
            log.error(e.getMessage());
            return CommandResult.forward("/WEB-INF/jsp/common/error.jsp");
        }
        log.debug("User registration finished.");
        return CommandResult.redirect(request.getContextPath() + "controller?command=show_main_page");
    }
}
