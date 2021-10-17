package by.training.webapplication.controller.command.impl.deliverer;

import by.training.webapplication.controller.command.Command;
import by.training.webapplication.controller.command.CommandResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DelivererAccountCommand implements Command {
    private static Logger log = LogManager.getLogger(DelivererAccountCommand.class.getName());
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        log.info("User is logged in to the deliverer profile.");
        return CommandResult.forward("/WEB-INF/jsp/deliverer/deliverer_account.jsp");
    }
}
