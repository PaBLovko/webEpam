package by.training.webapplication.controller.command.impl.admin;

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

public class ChangeUserCommand implements Command {
    private static final String CHANGE_ROLE = "changeRole";
    private static final String CHANGE_ADDRESS = "changeAddress";
    private static final String CHANGE_PHONE = "changePhone";
    private static final String CHANGE_NOTE = "changeNote";
    private static final String CHANGE_ID = "changeId";
    private static final String WRONG = "wrong";
    private static final String RIGHT = "right";
    private static final String WRONG_MESSAGE = "wrongMessage";
    private static final String RIGHT_MESSAGE = "rightUser";
    private static Logger log = LogManager.getLogger(ChangeUserCommand.class.getName());


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Changing user started.");
        HttpSession session = request.getSession();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        String address = request.getParameter(CHANGE_ADDRESS);
        String phone = request.getParameter(CHANGE_PHONE);
        String note = request.getParameter(CHANGE_NOTE);
        String role = request.getParameter(CHANGE_ROLE);
        int userId = Integer.parseInt(request.getParameter(CHANGE_ID));
        try{
            if(role.isEmpty() & note.isEmpty() & phone.isEmpty() & address.isEmpty()){
                session.setAttribute(WRONG, WRONG_MESSAGE);
            } else {
                if(!address.isEmpty()){
                    serviceFactory.getUserService().changeAddress(address, userId);
                }
                if(!phone.isEmpty()){
                    serviceFactory.getUserService().changePhone(phone, userId);
                }
                if(!note.isEmpty()){
                    serviceFactory.getUserService().changeNote(note, userId);
                }
                if(!role.isEmpty()){
                    serviceFactory.getUserService().changeRole(role, userId);
                }
                session.setAttribute(RIGHT, RIGHT_MESSAGE);
            }
        } catch (ValidatorException ex){
            log.error(this.getClass() + ":" + ex.getMessage());
            session.setAttribute(WRONG, WRONG_MESSAGE);
        } catch (ServiceException e){
            log.error(this.getClass() + ":" + e.getMessage());
            return CommandResult.forward("/WEB-INF/jsp/common/error.jsp");
        }
        log.debug("Changing user finished.");
        return CommandResult.redirect(request.getContextPath() + "controller?command=admin_users&page=1");
    }
}
