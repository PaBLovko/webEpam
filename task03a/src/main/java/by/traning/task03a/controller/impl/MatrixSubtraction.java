package by.traning.task03a.controller.impl;

import by.traning.task03a.controller.Command;
import by.traning.task03a.service.MatrixService;
import by.traning.task03a.service.exception.ServiceException;
import by.traning.task03a.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The class implementing method "matrix subtraction"
 */
public class MatrixSubtraction implements Command {
    private static Logger logger = LogManager.getLogger(MatrixSubtraction.class);

    @Override
    public String execute(String request) {
        logger.debug(String.format("The method is invoked, request = %s", request));
        String response;
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        MatrixService matrixService = serviceFactory.getMatrixService();
        try {
            matrixService.matrixSubtraction();
            response = "response.ready";
        } catch (ServiceException e) {
            logger.error("The method is exception, error during the procedure", e);
            response = "Error during the procedure";
        }
        logger.info(String.format("The method worked correctly, response = %s", response));
        return response;
    }
}
