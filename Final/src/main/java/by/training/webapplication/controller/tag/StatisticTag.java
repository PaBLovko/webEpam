package by.training.webapplication.controller.tag;

import by.training.webapplication.service.exception.ServiceException;
import by.training.webapplication.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Locale;

public class StatisticTag extends TagSupport {
    private static Logger log = LogManager.getLogger(StatisticTag.class);

    @Override
    public int doStartTag() {
        log.debug("Loading statistic data started");
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        HttpSession session = request.getSession();
        String amountUser;
        String amountOrders;
        String ordersCost;
        String language = (String) session.getAttribute("language");
        if(language == null) {
            language = Locale.getDefault().toString();
        }
        try {
            int clients = serviceFactory.getUserService().findClientAmount();
            int orders = serviceFactory.getOrderService().findOrdersAmount();
            int ordersSum = serviceFactory.getOrderService().findOrdersCost();
            switch (language){
                case "en_EN":
                    amountUser = "<p>Number of registered users: <strong>" + clients + "!</strong></p>";
                    amountOrders = "<p>Number of delivered orders: <strong>" + orders + "!</strong></p>";
                    ordersCost = "<p>The total amount of orders is: <strong>" + ordersSum + "0 RUB!</strong></p>";
                    break;
                case "ru_RU":
                    amountUser = "<p>Количество зарегистрированных пользователей: <strong>" + clients + "!</strong></p>";
                    amountOrders = "<p>Количество заказов: <strong>" + orders + "!</strong></p>";
                    ordersCost = "<p>Общая сумма заказов: <strong>" + ordersSum + "0 RUB!</strong></p>";
                    break;
                case "be_BE":
                    amountUser = "<p>Колькасць зарэгістраваных карыстальнікаў: <strong>" + clients + "!</strong></p>";
                    amountOrders = "<p>Колькасць заказаў: <strong>" + orders + "!</strong></p>";
                    ordersCost = "<p>Агульная сума заказаў: <strong>" + ordersSum + "0 RUB!</strong></p>";
                    break;
                default:
                    amountUser = "";
                    amountOrders = "";
                    ordersCost = "";
            }
        } catch (ServiceException e) {
            amountUser = "";
            amountOrders = "";
            ordersCost = "";
        }
        JspWriter out = pageContext.getOut();
        try {
            out.write(amountUser + amountOrders + ordersCost);
            log.info("Statistic data recorded successfully");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        log.debug("Loading statistic data finished");
        return SKIP_BODY;
    }
}
