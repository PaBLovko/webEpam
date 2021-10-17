package by.training.webapplication.controller.command.impl.admin;

import by.training.webapplication.controller.command.Command;
import by.training.webapplication.service.exception.ServiceException;
import by.training.webapplication.service.factory.ServiceFactory;
import by.training.webapplication.controller.command.CommandResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteCommentCommand implements Command {
    private static final String COMMENT_ID = "commentId";
    private static Logger log = LogManager.getLogger(DeleteCommentCommand.class.getName());

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Deleting comment started.");
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        int commentId = Integer.parseInt(request.getParameter(COMMENT_ID));
        try {
            serviceFactory.getCommentService().deleteComment(commentId);
        } catch (ServiceException e) {
            log.error(this.getClass() + ":" + e.getMessage());
            return CommandResult.forward("/WEB-INF/jsp/common/error.jsp");
        }
        log.debug("Deleting comment finished.");
        return CommandResult.redirect(request.getContextPath() + "controller?command=admin_comment&page=1");
    }
}
