package by.training.webapplication.controller.command.impl.common;

import by.training.webapplication.controller.command.Command;
import by.training.webapplication.controller.command.CommandResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationCommand implements Command {
    private static Logger log = LogManager.getLogger(RegistrationCommand.class);
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        log.info("User is logged in to the registration page.");
        return CommandResult.forward("/WEB-INF/jsp/user/registration.jsp");
    }
}
