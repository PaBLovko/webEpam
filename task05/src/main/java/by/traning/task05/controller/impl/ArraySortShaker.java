package by.traning.task05.controller.impl;

import by.traning.task05.controller.Command;
import by.traning.task05.service.exception.ServiceException;
import by.traning.task05.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The class implementing method "array sort shaker"
 */
public class ArraySortShaker implements Command {
    private static Logger logger = LogManager.getLogger(ArraySortShaker.class);

    @Override
    public String execute(String request) {
        logger.debug(String.format("The method is invoked, request = %s", request));
        String response;
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
//        ArrayService arrayService = serviceFactory.getArrayService();
//        try {
//            arrayService.arraySortShaker();
            response = "response.ready";
//        } catch (ServiceException e) {
//            logger.error("The method is exception, error during the procedure", e);
//            response = "Error during the procedure";
//        }
        logger.info(String.format("The method worked correctly, response = %s", response));
        return response;
    }
}
