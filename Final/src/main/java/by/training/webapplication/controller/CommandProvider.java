package by.training.webapplication.controller;

import by.training.webapplication.controller.command.Command;
import by.training.webapplication.controller.command.CommandName;
import by.training.webapplication.controller.command.impl.admin.*;
import by.training.webapplication.controller.command.impl.common.*;
import by.training.webapplication.controller.command.impl.deliverer.*;
import by.training.webapplication.controller.command.impl.user.*;


import java.util.HashMap;
import java.util.Map;

final class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();

    CommandProvider() {
        repository.put(CommandName.SHOW_MAIN_PAGE, new ShowMainPageCommand());
        repository.put(CommandName.LOGIN,new LoginCommand());
        repository.put(CommandName.LOG_OUT, new LogoutCommand());
        repository.put(CommandName.SORT_BY_INCREASE_PRICE, new SortByPriceIncreaseCommand());
        repository.put(CommandName.SORT_BY_DECREASE_PRICE, new SortByPriceDecreaseCommand());
        repository.put(CommandName.PERSONAL_ACCOUNT, new PersonalAccountCommand());
        repository.put(CommandName.CHANGE_NAME, new ChangeNameCommand());
        repository.put(CommandName.CHANGE_SURNAME, new ChangeSurnameCommand());
        repository.put(CommandName.CHANGE_PATRONYMIC, new ChangePatronymicCommand());
        repository.put(CommandName.CHANGE_ADDRESS, new ChangeAddressCommand());
        repository.put(CommandName.CHANGE_PHONE, new ChangePhoneCommand());
        repository.put(CommandName.SHOW_COMMENT, new ShowCommentCommand());
        repository.put(CommandName.ADD_COMMENT, new AddCommentCommand());
        repository.put(CommandName.SHOW_ABOUT_US, new ShowAboutUsCommand());
        repository.put(CommandName.SHOW_CONTACTS, new ShowContactsCommand());
        repository.put(CommandName.SHOW_DELIVERY, new ShowDeliveryCommand());
        repository.put(CommandName.ADD_DISH, new AddDishCommand());
        repository.put(CommandName.CLEAR_BASKET, new ClearBasketCommand());
        repository.put(CommandName.ADD_ORDER, new AddOrderCommand());
        repository.put(CommandName.ADMIN_ACCOUNT, new AdminAccountCommand());
        repository.put(CommandName.ADMIN_DISHES, new AdminDishesCommand());
        repository.put(CommandName.ADMIN_COMMENT, new AdminCommentCommand());
        repository.put(CommandName.FIND_DISH_BY_ID, new FindDishByIdCommand());
        repository.put(CommandName.FIND_DISH_BY_NAME, new FindDishByNameCommand());
        repository.put(CommandName.DELETE_DISH, new DeleteDishCommand());
        repository.put(CommandName.SAVE_DISH, new SaveDishCommand());
        repository.put(CommandName.CHANGE_DISH, new ChangeDishCommand());
        repository.put(CommandName.DELETE_COMMENT, new DeleteCommentCommand());
        repository.put(CommandName.ADMIN_USERS, new AdminUsersCommand());
        repository.put(CommandName.DELETE_USER, new DeleteUserCommand());
        repository.put(CommandName.SAVE_USER, new SaveUserCommand());
        repository.put(CommandName.CHANGE_USER, new ChangeUserCommand());
        repository.put(CommandName.ADMIN_ORDER_PRODUCT, new AdminOrderProductCommand());
        repository.put(CommandName.SHOW_BASKET, new ShowBasketCommand());
        repository.put(CommandName.DELETE_DISH_FROM_BASKET, new DeleteDishFromBasketCommand());
        repository.put(CommandName.DELETE_ORDER, new DeleteOrderCommand());
        repository.put(CommandName.CHANGE_ORDER, new ChangeOrderCommand());
        repository.put(CommandName.DELETE_ORDER_PRODUCT, new DeleteOrderProductCommand());
        repository.put(CommandName.DELIVERER_ACCOUNT, new DelivererAccountCommand());
        repository.put(CommandName.DELIVERER_CLIENTS, new DelivererClientsCommand());
        repository.put(CommandName.CHANGE_NOTE, new ChangeNoteCommand());
        repository.put(CommandName.DELIVERER_ORDER, new DelivererOrderCommand());
        repository.put(CommandName.CHANGE_ORDER_STATUS, new ChangeOrderStatusCommand());
        repository.put(CommandName.WRONG_COMMAND, new WrongCommand());
        repository.put(CommandName.REGISTRATION, new RegistrationCommand());
        repository.put(CommandName.REGISTRATION_USER, new RegistrationUserCommand());
        repository.put(CommandName.ADMIN_ADD_NEW_ORDER, new AdminAddNewOrderCommand());
        repository.put(CommandName.ADMIN_ADD_DISH_TO_ORDER, new AdminAddDishToOrderCommand());
        repository.put(CommandName.ADMIN_ADD_NEW_USER_FOR_ORDER, new AdminAddNewUserForOrder());
        repository.put(CommandName.ADMIN_DELETE_DISH_FROM_ORDER, new AdminDeleteDishFromOrderCommand());
        repository.put(CommandName.ADMIN_PRODUCT_TO_ORDER, new AdminProductToOrder());
        repository.put(CommandName.ADMIN_USERS_INCREASE_PAGE, new AdminUsersIncreasePageCommand());
        repository.put(CommandName.ADMIN_USERS_DECREASE_PAGE, new AdminUsersDecreasePageCommand());
        repository.put(CommandName.ADMIN_COMMENT_INCREASE_PAGE, new AdminCommentIncreasePageCommand());
        repository.put(CommandName.ADMIN_COMMENT_DECREASE_PAGE, new AdminCommentDecreasePageCommand());
        repository.put(CommandName.SHOW_COMMENT_INCREASE_PAGE, new ShowCommentIncreasePageCommand());
        repository.put(CommandName.SHOW_COMMENT_DECREASE_PAGE, new ShowCommentDecreasePageCommand());
        repository.put(CommandName.DELIVERER_CLIENTS_INCREASE_PAGE, new DelivererClientsIncreasePageCommand());
        repository.put(CommandName.DELIVERER_CLIENTS_DECREASE_PAGE, new DelivererClientsDecreasePageCommand());
        repository.put(CommandName.ADMIN_ORDER_PRODUCT_INCREASE_PAGE, new AdminOrderProductIncreasePageCommand());
        repository.put(CommandName.ADMIN_ORDER_PRODUCT_DECREASE_PAGE, new AdminOrderProductDecreasePageCommand());
        repository.put(CommandName.DELIVERER_ORDER_INCREASE_PAGE, new DelivererOrderIncreasePageCommand());
        repository.put(CommandName.DELIVERER_ORDER_DECREASE_PAGE, new DelivererOrderDecreaseCommand());
        repository.put(CommandName.PERSONAL_ACCOUNT_INCREASE_PAGE, new PersonalAccountIncreasePageCommand());
        repository.put(CommandName.PERSONAL_ACCOUNT_DECREASE_PAGE, new PersonalAccountDecreasePageCommand());
    }

    Command getCommand(String name) {
        CommandName commandName;
        Command command;
        try {
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        } catch (IllegalArgumentException | NullPointerException ex) {
            command = repository.get(CommandName.WRONG_COMMAND);
        }
        return command;
    }
}
