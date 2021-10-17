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

public class ShowCommentIncreasePageCommand implements Command {
    private static final String COMMENT = "comment";
    private static final String PAGE = "page";
    private static final String COUNT = "count";
    private static final int AMOUNT = 10;
    private static Logger log = LogManager.getLogger(ShowCommentIncreasePageCommand.class);
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Page number increase for comments started.");
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        int currentPage = Integer.parseInt(request.getParameter(PAGE));
        int increasePage = currentPage + 1;
        int count = Integer.parseInt(request.getParameter(COUNT));
        List<Comment> comments;
        if (increasePage <= count) {
            try {
                comments = serviceFactory.getCommentService().findLimitComment((increasePage - 1) * AMOUNT, AMOUNT);
                request.setAttribute(COMMENT, comments);
            } catch (ServiceException e) {
                log.error(e.getMessage());
                return CommandResult.forward("/WEB-INF/jsp/common/error.jsp");
            }
            request.setAttribute(PAGE, increasePage);
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
        log.debug("Page number increase for comments finished.");
        return CommandResult.forward("/WEB-INF/jsp/common/comment.jsp");
    }

}
