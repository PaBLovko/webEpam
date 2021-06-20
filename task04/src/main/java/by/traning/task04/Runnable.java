package by.traning.task04;


import by.traning.task04.controller.Controller;
import by.traning.task04.view.factory.ViewFactory;
import by.traning.task04.view.View;

import static by.traning.task04.MessageManager.EN;
import static by.traning.task04.MessageManager.RU;

public class Runnable {
    public static void main(String[] args) {
        ViewFactory viewFactory = ViewFactory.getInstance();
        View consoleView = viewFactory.getConsoleView();
        consoleView.show(EN.getString("menu.lang"));
        MessageManager messageManager = consoleView.read().equals("1") ? EN : RU;
        Controller controller = new Controller();
        consoleView.show(messageManager.getString("menu.car"));
        consoleView.show(controller.executeTask("car_all "));
        boolean isCycleWorks = true;
        while (isCycleWorks) {
            consoleView.show(messageManager.getString("menu.mode"));
            String answer = controller.executeTask(consoleView.read());
            consoleView.show(answer);
            isCycleWorks = !answer.equals("response.exit");
        }
    }
}