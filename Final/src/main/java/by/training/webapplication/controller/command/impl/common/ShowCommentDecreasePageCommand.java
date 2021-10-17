package by.training.webapplication.controller.command.impl.common;

import by.training.webapplication.controller.command.Command;
import by.training.webapplication.controller.command.CommandResult;
import by.training.webapplication.service.exception.ServiceException;
import by.training.webapplication.service.factory.ServiceFactory;
import by.training.webapplication.bean.Comment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowCommentDecreasePageCommand implements Command {
    private static final String COMMENT = "comment";
    private static final String PAGE = "page";
    private static final String COUNT = "count";
    private static final int AMOUNT = 10;
    private static Logger log = LogManager.getLogger(ShowCommentDecreasePageCommand.class);
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Page number reduction for comments started.");
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        int currentPage = Integer.parseInt(request.getParameter(PAGE));
        int decreasePage = currentPage - 1;
        int count = Integer.parseInt(request.getParameter(COUNT));
        List<Comment> comments;
        if (decreasePage >= 1) {
            try {
                comments = serviceFactory.getCommentService().findLimitComment((decreasePage - 1) * AMOUNT, AMOUNT);
                request.setAttribute(COMMENT, comments);
            } catch (ServiceException e) {
                log.error(e.getMessage());
                return CommandResult.forward("/WEB-INF/jsp/common/error.jsp");
            }
            request.setAttribute(PAGE, decreasePage);
        } else {
            try {
                comments = serviceFactory.getCommentService().findLimitComment((currentPage - 1) * AMOUNT, AMOUNT);
                request.setAttribute(COMMENT, comments);
            } catch (ServiceException e) {
                log.error(e.getMessage());
                return CommandResult.forward("/WEB-INF/jsp/common/error.jsp");
            }
            request.setAttribute(PAGE, currentPage);
        }
        request.setAttribute(COUNT, count);
        log.debug("Page number reduction for comments finished.");
        return CommandResult.forward("/WEB-INF/jsp/common/comment.jsp");
    }
}
