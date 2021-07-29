package by.traning.task07.controller.impl;

import by.traning.task07.controller.Command;
import by.traning.task07.service.SortService;
import by.traning.task07.service.exception.ServiceException;
import by.traning.task07.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The class implementing method "matrix subtraction"
 */
public class SortLexeme implements Command {
    private static Logger logger = LogManager.getLogger(SortLexeme.class);

    @Override
    public String execute(String request) {
        logger.debug(String.format("The method is invoked, request = %s", request));
        String response = null;
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        SortService sortService = serviceFactory.getSortService();
//        try {
//            sortService.sortLexemes();
//            response = "response.ready";
//        } catch (ServiceException e) {
//            logger.error("The method is exception, error during the procedure", e);
//            response = "Error during the procedure";
//        }
        logger.info(String.format("The method worked correctly, response = %s", response));
        return response;
    }
}
