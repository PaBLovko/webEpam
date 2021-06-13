package by.traning.task03a.controller.impl;

import by.traning.task03a.controller.Command;
import by.traning.task03a.service.ArrayService;
import by.traning.task03a.service.exception.ServiceException;
import by.traning.task03a.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The class implementing method "array sort hashing"
 */
public class ArraySortHashing implements Command {
    private static Logger logger = LogManager.getLogger(ArraySortHashing.class);

    @Override
    public String execute(String request) {
        logger.debug(String.format("The method is invoked, request = %s", request));
        String response;
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ArrayService arrayService = serviceFactory.getArrayService();
        try {
            arrayService.arraySortHashing();
            response = "response.ready";
        } catch (ServiceException e) {
            logger.error("The method is exception, error during the procedure", e);
            response = "Error during the procedure";
        }
        logger.info(String.format("The method worked correctly, response = %s", response));
        return response;
    }
}
