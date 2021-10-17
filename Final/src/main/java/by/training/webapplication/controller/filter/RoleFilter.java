package by.training.webapplication.controller.filter;

import by.training.webapplication.controller.command.CommandName;
import by.training.webapplication.bean.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

public class RoleFilter implements Filter {
    private static final Map<CommandName, List<Integer>> commandsUsers = new HashMap<>();
    private static final String COMMAND = "command";
    private static final String USER = "user";
    private static Logger log = LogManager.getLogger(RoleFilter.class.getName());
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        List<Integer> allUsers = Arrays.asList(0, 1, 2, 3);
        List<Integer> admin = Collections.singletonList(1);
        List<Integer> deliverer = Collections.singletonList(2);
        List<Integer> client = Collections.singletonList(3);
        commandsUsers.put(CommandName.SHOW_MAIN_PAGE, allUsers);
        commandsUsers.put(CommandName.LOGIN, allUsers);
        commandsUsers.put(CommandName.LOG_OUT, allUsers);
        commandsUsers.put(CommandName.REGISTRATION, allUsers);
        commandsUsers.put(CommandName.REGISTRATION_USER, allUsers);
        commandsUsers.put(CommandName.SHOW_ABOUT_US, allUsers);
        commandsUsers.put(CommandName.SHOW_CONTACTS, allUsers);
        commandsUsers.put(CommandName.SHOW_DELIVERY, allUsers);
        commandsUsers.put(CommandName.SHOW_COMMENT, allUsers);
        commandsUsers.put(CommandName.SHOW_COMMENT_INCREASE_PAGE, allUsers);
        commandsUsers.put(CommandName.SHOW_COMMENT_DECREASE_PAGE, allUsers);
        commandsUsers.put(CommandName.SORT_BY_DECREASE_PRICE, allUsers);
        commandsUsers.put(CommandName.SORT_BY_INCREASE_PRICE, allUsers);
        commandsUsers.put(CommandName.CHANGE_NOTE, deliverer);
        commandsUsers.put(CommandName.CHANGE_ORDER_STATUS, deliverer);
        commandsUsers.put(CommandName.DELIVERER_CLIENTS, deliverer);
        commandsUsers.put(CommandName.DELIVERER_ACCOUNT, deliverer);
        commandsUsers.put(CommandName.DELIVERER_CLIENTS_INCREASE_PAGE, deliverer);
        commandsUsers.put(CommandName.DELIVERER_CLIENTS_DECREASE_PAGE, deliverer);
        commandsUsers.put(CommandName.DELIVERER_ORDER, deliverer);
        commandsUsers.put(CommandName.DELIVERER_ORDER_INCREASE_PAGE, deliverer);
        commandsUsers.put(CommandName.DELIVERER_ORDER_DECREASE_PAGE, deliverer);
        commandsUsers.put(CommandName.ADD_COMMENT, client);
        commandsUsers.put(CommandName.ADD_ORDER, client);
        commandsUsers.put(CommandName.ADD_DISH, client);
        commandsUsers.put(CommandName.CHANGE_ADDRESS, client);
        commandsUsers.put(CommandName.CHANGE_NAME, client);
        commandsUsers.put(CommandName.CHANGE_PATRONYMIC, client);
        commandsUsers.put(CommandName.CHANGE_SURNAME, client);
        commandsUsers.put(CommandName.CHANGE_PHONE, client);
        commandsUsers.put(CommandName.CLEAR_BASKET, client);
        commandsUsers.put(CommandName.DELETE_DISH_FROM_BASKET, client);
        commandsUsers.put(CommandName.PERSONAL_ACCOUNT, client);
        commandsUsers.put(CommandName.PERSONAL_ACCOUNT_DECREASE_PAGE, client);
        commandsUsers.put(CommandName.PERSONAL_ACCOUNT_INCREASE_PAGE, client);
        commandsUsers.put(CommandName.SHOW_BASKET, client);
        commandsUsers.put(CommandName.ADMIN_ACCOUNT, admin);
        commandsUsers.put(CommandName.ADMIN_ADD_NEW_ORDER, admin);
        commandsUsers.put(CommandName.ADMIN_ADD_NEW_USER_FOR_ORDER, admin);
        commandsUsers.put(CommandName.ADMIN_ADD_DISH_TO_ORDER, admin);
        commandsUsers.put(CommandName.ADMIN_DELETE_DISH_FROM_ORDER, admin);
        commandsUsers.put(CommandName.ADMIN_COMMENT, admin);
        commandsUsers.put(CommandName.ADMIN_COMMENT_DECREASE_PAGE, admin);
        commandsUsers.put(CommandName.ADMIN_COMMENT_INCREASE_PAGE, admin);
        commandsUsers.put(CommandName.ADMIN_ORDER_PRODUCT, admin);
        commandsUsers.put(CommandName.ADMIN_ORDER_PRODUCT_INCREASE_PAGE, admin);
        commandsUsers.put(CommandName.ADMIN_ORDER_PRODUCT_DECREASE_PAGE, admin);
        commandsUsers.put(CommandName.ADMIN_DISHES, admin);
        commandsUsers.put(CommandName.ADMIN_PRODUCT_TO_ORDER, admin);
        commandsUsers.put(CommandName.ADMIN_USERS, admin);
        commandsUsers.put(CommandName.ADMIN_USERS_INCREASE_PAGE, admin);
        commandsUsers.put(CommandName.ADMIN_USERS_DECREASE_PAGE, admin);
        commandsUsers.put(CommandName.CHANGE_ORDER, admin);
        commandsUsers.put(CommandName.CHANGE_DISH, admin);
        commandsUsers.put(CommandName.CHANGE_USER, admin);
        commandsUsers.put(CommandName.DELETE_COMMENT, admin);
        commandsUsers.put(CommandName.DELETE_ORDER, admin);
        commandsUsers.put(CommandName.DELETE_ORDER_PRODUCT, admin);
        commandsUsers.put(CommandName.DELETE_DISH, admin);
        commandsUsers.put(CommandName.DELETE_USER, admin);
        commandsUsers.put(CommandName.FIND_DISH_BY_ID, admin);
        commandsUsers.put(CommandName.FIND_DISH_BY_NAME, admin);
        commandsUsers.put(CommandName.SAVE_DISH, admin);
        commandsUsers.put(CommandName.SAVE_USER, admin);
    }
//TODO: get POST
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String command = request.getParameter(COMMAND);
        if(command != null){
            CommandName commandName = CommandName.valueOf(command.toUpperCase());
            User user = (User) request.getSession().getAttribute(USER);
            int role = 0;
            if (user != null) {
                role = user.getRole();
            }
            List<Integer> roles = commandsUsers.get(commandName);
            if (roles != null && !roles.contains(role)) {
                log.info("Wrong role for command");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/common/errorRole.jsp");
                dispatcher.forward(request, servletResponse);
                return;
            }
        }
        filterChain.doFilter(request,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
