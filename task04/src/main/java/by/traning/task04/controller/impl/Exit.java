package by.traning.task04.controller.impl;

import by.traning.task04.controller.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.traning.task04.MessageManager.DEFAULT;

/**
 * The class implementing command "exit"
 */
public class Exit implements Command {
    private static Logger logger = LogManager.getLogger(Exit.class);

    @Override
    public String execute(String request) {
        logger.debug(String.format("The method is invoked, request = %s", request));
        String response = DEFAULT.getString("response.exit");
        logger.info(String.format("The method worked correctly, response = %s", response));
        return response;
    }
}
