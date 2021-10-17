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

public class ShowCommentCommand implements Command {
    private static final String COMMENT = "comment";
    private static final String PAGE = "page";
    private static final String COUNT = "count";
    private static final int AMOUNT = 10;
    private static Logger log = LogManager.getLogger(ShowCommentCommand.class);
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        log.debug("Loading comments started.");
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        int page = Integer.parseInt(request.getParameter(PAGE));
        List<Comment> comments;
        try{
            comments = serviceFactory.getCommentService().findLimitComment((page - 1) * AMOUNT, AMOUNT);
            request.setAttribute(COMMENT, comments);
        }catch (ServiceException e){
            log.error(e.getMessage());
            return CommandResult.forward("/WEB-INF/jsp/common/error.jsp");
        }
        try{
            int count = serviceFactory.getCommentService().findCommentPageAmount(AMOUNT);
            request.setAttribute(COUNT, count);
        } catch (ServiceException e) {
            log.error(e.getMessage());
            return CommandResult.forward("/WEB-INF/jsp/common/error.jsp");
        }
        request.setAttribute(PAGE, page);
        log.debug("Loading comments finished.");
        return CommandResult.forward("/WEB-INF/jsp/common/comment.jsp");
    }
}
