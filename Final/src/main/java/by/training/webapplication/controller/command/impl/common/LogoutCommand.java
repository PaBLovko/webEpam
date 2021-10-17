package by.training.webapplication.controller.command.impl.common;

import by.training.webapplication.controller.command.Command;
import by.training.webapplication.controller.command.CommandResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    private static Logger log = LogManager.getLogger(LogoutCommand.class);
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Log out user started.");
        HttpSession session = request.getSession();
        session.invalidate();
        log.debug("Log out user finished.");
        return CommandResult.redirect(request.getContextPath() + "controller?command=show_main_page");
    }
}
