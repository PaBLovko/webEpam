package by.traning.task04.controller.impl;

import by.traning.task04.controller.Command;
import by.traning.task04.service.CarService;
import by.traning.task04.service.exception.ServiceException;
import by.traning.task04.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.traning.task04.MessageManager.DEFAULT;

/**
 * The class implementing method "car wheel change"
 */
public class CarWheelChange implements Command {
    private static Logger logger = LogManager.getLogger(CarWheelChange.class);

    @Override
    public String execute(String request) {
        logger.debug(String.format("The method is invoked, request = %s", request));
        String response;
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        CarService carService = serviceFactory.getCarService();
        try {
            String modelName = request.substring(request.indexOf(PARAM_DELIMITER)+1);
            carService.wheelChange(modelName);
            response = DEFAULT.getString("response.ready");
        } catch (ServiceException e) {
            logger.error("The method is exception, error during the procedure", e);
            response = DEFAULT.getString("response.error");
        }
        logger.info(String.format("The method worked correctly, response = %s", response));
        return response;
    }
}
