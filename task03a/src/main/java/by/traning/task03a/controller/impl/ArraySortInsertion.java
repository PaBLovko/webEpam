package by.traning.task03a.controller.impl;

import by.traning.task03a.controller.Command;
import by.traning.task03a.service.ArrayService;
import by.traning.task03a.service.exception.ServiceException;
import by.traning.task03a.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The class implementing method "array sort insertion"
 */
public class ArraySortInsertion implements Command {
    private static Logger logger = LogManager.getLogger(ArraySortInsertion.class);

    @Override
    public String execute(String request) {
        logger.debug(String.format("The method is invoked, request = %s", request));
        String response;
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ArrayService arrayService = serviceFactory.getArrayService();
        try {
            arrayService.arraySortInsertion();
            response = "response.ready";
        } catch (ServiceException e) {
            logger.error("The method is exception, error during the procedure", e);
            response = "Error during the procedure";
        }
        logger.info(String.format("The method worked correctly, response = %s", response));
        return response;
    }
}
