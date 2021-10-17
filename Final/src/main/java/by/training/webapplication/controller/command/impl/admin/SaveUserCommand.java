package by.training.webapplication.controller.command.impl.admin;

import by.training.webapplication.controller.command.Command;
import by.training.webapplication.controller.command.CommandResult;
import by.training.webapplication.service.exception.LoginIsNotFreeException;
import by.training.webapplication.service.exception.ServiceException;
import by.training.webapplication.service.factory.ServiceFactory;
import by.training.webapplication.service.exception.ValidatorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SaveUserCommand implements Command {
    private static final double TOTAL = 0.00;
    private static final String SAVE_LOGIN = "saveLogin";
    private static final String SAVE_PASSWORD = "savePassword";
    private static final String SAVE_ROLE = "saveRole";
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
    private static final String RIGHT_MESSAGE = "rightSaveUser";
    private static Logger log = LogManager.getLogger(SaveUserCommand.class.getName());

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Saving user started.");
        HttpSession session = request.getSession();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        String login = request.getParameter(SAVE_LOGIN);
        String password = request.getParameter(SAVE_PASSWORD);
        String role = request.getParameter(SAVE_ROLE);
        String surname = request.getParameter(SAVE_SURNAME);
        String name = request.getParameter(SAVE_NAME);
        String patronymic = request.getParameter(SAVE_PATRONYMIC);
        String address = request.getParameter(SAVE_ADDRESS);
        String phone = request.getParameter(SAVE_PHONE);
        String note = request.getParameter(SAVE_NOTE);
        try {
            try {
                serviceFactory.getUserService().addUser(login, password, role, surname, name, patronymic, address, phone, note, TOTAL);
                session.setAttribute(RIGHT, RIGHT_MESSAGE);
            } catch (ValidatorException ex){
                log.error(ex.getMessage());
                session.setAttribute(WRONG, WRONG_DATA);
            } catch (LoginIsNotFreeException exc){
                log.error(exc.getMessage());
                session.setAttribute(WRONG, WRONG_LOGIN);
            }
        } catch (ServiceException e) {
            log.error(e.getMessage());
            return CommandResult.forward("/WEB-INF/jsp/common/error.jsp");
        }
        log.debug("Saving user finished.");
        return CommandResult.redirect(request.getContextPath() + "controller?command=admin_users&page=1");
    }
}
