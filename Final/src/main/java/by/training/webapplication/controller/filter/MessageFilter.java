package by.training.webapplication.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MessageFilter implements Filter {
    private static final String RIGHT = "right";
    private static final String WRONG = "wrong";
    private Map<String, String> messagesEn = new HashMap<>();
    private Map<String, String> messagesRu = new HashMap<>();
    private Map<String, String> messagesBe = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        messagesEn.put("wrongId", "The entered id not found in database!");
        messagesEn.put("wrongMessage", "The entered data is not correct!");
        messagesEn.put("wrongAmount", "The number of dishes is wrong!");
        messagesEn.put("rightAmount", "Product added to basket!");
        messagesEn.put("rightOrder", "The order was changed successfully!");
        messagesEn.put("rightDish", "The dish was changed successfully!");
        messagesEn.put("rightUser", "User was changed successfully!");
        messagesEn.put("wrongName", "The entered name not found in database!");
        messagesEn.put("rightSaveDish", "The dish was added successfully!");
        messagesEn.put("rightSaveUser", "User was added successfully!");
        messagesEn.put("wrongLogin", " - this login is not free!");
        messagesEn.put("incorrectLogin", "Incorrect login or password!");
        messagesEn.put("rightRegistration", "Registration completed successfully!");
        messagesEn.put("rightNote", "Note was changed successfully!");
        messagesEn.put("rightStatus", "Status was changed successfully!");
        messagesEn.put("rightComment", "Your comment has been successfully added!");
        messagesEn.put("wrongAddress", "The new address is wrong!");
        messagesEn.put("rightAddress", "The address changed!");
        messagesEn.put("incorrectName", "The new name is wrong!");
        messagesEn.put("rightName", "The name changed!");
        messagesEn.put("wrongPatronymic", "The new patronymic is wrong!");
        messagesEn.put("rightPatronymic", "The patronymic changed!");
        messagesEn.put("wrongPhone", "The new phone is wrong!");
        messagesEn.put("rightPhone", "The phone changed!");
        messagesEn.put("wrongSurname", "The new surname is wrong!");
        messagesEn.put("rightSurname", "The surname changed!");


        messagesRu.put("wrongId", "Введенный номер не найден в базе данных!");
        messagesRu.put("wrongMessage", "Введенные данные некорректные!");
        messagesRu.put("wrongAmount", "Количество блюд неверное!");
        messagesRu.put("rightAmount", "Товар добавлен в корзину!");
        messagesRu.put("rightOrder", "Заказ был изменен успешно!");
        messagesRu.put("rightDish", "Блюдо был изменено успешно!");
        messagesRu.put("rightUser", "Пользователь был изменен успешно!");
        messagesRu.put("wrongName", "Введенное имя не найдено в базе данных!");
        messagesRu.put("rightSaveDish", "Блюдо было сохранено успешно!");
        messagesRu.put("rightSaveUser", "Пользователь был добавлен успешно!");
        messagesRu.put("wrongLogin", " - этот логин занят!");
        messagesRu.put("incorrectLogin", "Неправильный логин или пароль!");
        messagesRu.put("rightRegistration", "Регистрация прошла успешно!");
        messagesRu.put("rightNote", "Примечание было изменено успешно!");
        messagesRu.put("rightStatus", "Статус был изменен успешно!");
        messagesRu.put("rightComment", "Ваш отзыв был добавлен успешно!");
        messagesRu.put("wrongAddress", "Новый адрес неверный!");
        messagesRu.put("rightAddress", "Адрес изменен!");
        messagesRu.put("incorrectName", "Имя некорректное!");
        messagesRu.put("rightName", "Имя изменено!");
        messagesRu.put("wrongPatronymic", "Отчество некорректное!");
        messagesRu.put("rightPatronymic", "Отчество изменено!");
        messagesRu.put("wrongPhone", "Номер телефона некорректный!");
        messagesRu.put("rightPhone", "Номер телефона изменен!");
        messagesRu.put("wrongSurname", "Фамилия некорректная!");
        messagesRu.put("rightSurname", "Фамилия изменена!");

        messagesBe.put("wrongId", "Уведзены нумар не знойдзены ў базе даных!");
        messagesBe.put("wrongMessage", "Уведзеныя дадзеныя некарэктныя!");
        messagesBe.put("wrongAmount", "Колькасць блюд няправільнае!");
        messagesBe.put("rightAmount", "Тавар дададзены ў карзіну!");
        messagesBe.put("rightOrder", "Заказ быў зменены паспяхова!");
        messagesBe.put("rightDish", "Блюдо было зменено паспяхова!");
        messagesBe.put("rightUser", "Карыстальнік быў зменены паспяхова!");
        messagesBe.put("wrongName", "Уведзенае імя не знойдзена ў базе даных!");
        messagesBe.put("rightSaveDish", "Блюдо было захавана паспяхова!");
        messagesBe.put("rightSaveUser", "Карыстальнік быў дададзены паспяхова!");
        messagesBe.put("wrongLogin", " - гэты лагін заняты!");
        messagesBe.put("incorrectLogin", "Няправільны імя або пароль!");
        messagesBe.put("rightRegistration", "Рэгістрацыя прайшла паспяхова!");
        messagesBe.put("rightNote", "Заўвага было зменена паспяхова!");
        messagesBe.put("rightStatus", "Статус быў зменены паспяхова!");
        messagesBe.put("rightComment", "Ваш водзыў быў дададзены паспяхова!");
        messagesBe.put("wrongAddress", "Новы адрас няверны!");
        messagesBe.put("rightAddress", "Адрас зменены!");
        messagesBe.put("incorrectName", "Імя некарэктнае!");
        messagesBe.put("rightName", "Імя змененае!");
        messagesBe.put("wrongPatronymic", "Імя па бацьку некарэктнае!");
        messagesBe.put("rightPatronymic", "Імя па бацьку зменена!");
        messagesBe.put("wrongPhone", "Нумар тэлефона некарэктны!");
        messagesBe.put("rightPhone", "Нумар тэлефона зменены!");
        messagesBe.put("wrongSurname", "Прозвішча некарэктная!");
        messagesBe.put("rightSurname", "Прозвішча зменена!");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession session = req.getSession();
        String rightMessage = (String) session.getAttribute(RIGHT);
        String wrongMessage = (String) session.getAttribute(WRONG);
        String language = (String) session.getAttribute("language");
        if(language == null) {
            language = Locale.getDefault().toString();
        }
        if (rightMessage != null) {
            switch (language){
                case "en_EN":
                    req.setAttribute(RIGHT, messagesEn.get(rightMessage));
                    break;
                case "ru_RU":
                    req.setAttribute(RIGHT, messagesRu.get(rightMessage));
                    break;
                case "be_BE":
                    req.setAttribute(RIGHT, messagesBe.get(rightMessage));
                    break;
                default:
                    req.setAttribute(RIGHT, null);
            }
            session.setAttribute(RIGHT, null);
        }
        if(wrongMessage != null) {
            switch (language){
                case "en_EN":
                    req.setAttribute(WRONG, messagesEn.get(wrongMessage));
                    break;
                case "ru_RU":
                    req.setAttribute(WRONG, messagesRu.get(wrongMessage));
                    break;
                case "be_BE":
                    req.setAttribute(WRONG, messagesBe.get(wrongMessage));
                    break;
                default:
                    req.setAttribute(WRONG, null);
            }
            session.setAttribute(WRONG, null);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}

