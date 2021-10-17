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

public class ChangeDishCommand implements Command {
    private static final String CHANGE_ID = "changeId";
    private static final String CHANGE_NAME = "changeName";
    private static final String CHANGE_PICTURE = "changePicture";
    private static final String CHANGE_WEIGHT = "changeWeight";
    private static final String CHANGE_PRICE = "changePrice";
    private static final String CHANGE_DESCRIPTION = "changeDescription";
    private static final String WRONG = "wrong";
    private static final String RIGHT = "right";
    private static final String WRONG_MESSAGE = "wrongMessage";
    private static final String RIGHT_MESSAGE = "rightDish";
    private static Logger log = LogManager.getLogger(ChangeDishCommand.class.getName());
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Changing dish started.");
        HttpSession session = request.getSession();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        String name = request.getParameter(CHANGE_NAME);
        String picture = request.getParameter(CHANGE_PICTURE);
        String description = request.getParameter(CHANGE_DESCRIPTION);
        String weight = request.getParameter(CHANGE_WEIGHT);
        String price = request.getParameter(CHANGE_PRICE);
        int dishId = Integer.parseInt(request.getParameter(CHANGE_ID));
        try{
            if(name.isEmpty() & picture.isEmpty() & description.isEmpty() & weight.isEmpty() & price.isEmpty()){
                session.setAttribute(WRONG, WRONG_MESSAGE);
            } else {
                if(!name.isEmpty()){
                    serviceFactory.getDishService().changeName(name, dishId);
                }
                if(!picture.isEmpty()){
                    serviceFactory.getDishService().changePicture(picture, dishId);
                }
                if(!description.isEmpty()){
                    serviceFactory.getDishService().changeDescription(description, dishId);
                }
                if(!weight.isEmpty()) {
                    serviceFactory.getDishService().changeWeight(weight, dishId);
                }
                if(!price.isEmpty()) {
                    serviceFactory.getDishService().changePrice(price, dishId);
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
        log.debug("Changing dish finished.");
        return CommandResult.redirect(request.getContextPath() + "controller?command=admin_dishes");
    }
}
