package by.training.webapplication.controller.command.impl.admin;

import by.training.webapplication.service.factory.ServiceFactory;
import by.training.webapplication.controller.command.Command;
import by.training.webapplication.controller.command.CommandResult;
import by.training.webapplication.service.exception.ServiceException;
import by.training.webapplication.service.exception.ValidatorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangeOrderCommand implements Command {
    private static final String CHANGE_PREPARATION_TIME = "preparationTime";
    private static final String CHANGE_DELIVERY_TIME = "deliveryTime";
    private static final String CHANGE_STATUS = "changeStatus";
    private static final String ID_ORDER = "changeId";
    private static final String WRONG = "wrong";
    private static final String RIGHT = "right";
    private static final String WRONG_MESSAGE = "wrongMessage";
    private static final String RIGHT_MESSAGE = "rightOrder";
    private static Logger log = LogManager.getLogger(ChangeOrderCommand.class.getName());
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Changing order started.");
        HttpSession session = request.getSession();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        String newPreparationTime = request.getParameter(CHANGE_PREPARATION_TIME);
        String newDeliveryTime = request.getParameter(CHANGE_DELIVERY_TIME);
        String newStatus = request.getParameter(CHANGE_STATUS);
        int orderId = Integer.parseInt(request.getParameter(ID_ORDER));
        try{
            if(newPreparationTime.isEmpty() & newDeliveryTime.isEmpty() & newStatus.isEmpty()){
                session.setAttribute(WRONG, WRONG_MESSAGE);
            } else {
                if(!newPreparationTime.isEmpty()){
                    serviceFactory.getOrderService().changePreparationTime(newPreparationTime, orderId );
                }
                if(!newDeliveryTime.isEmpty()){
                    serviceFactory.getOrderService().changeDeliveryTime(newDeliveryTime, orderId );
                }
                if(!newStatus.isEmpty()){
                    serviceFactory.getOrderService().changeStatus(newStatus, orderId);
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
        log.debug("Changing order finished.");
        return CommandResult.redirect(request.getContextPath() + "controller?command=admin_order_product&page=1");
    }
}
