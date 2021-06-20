package by.traning.task03a;

import by.traning.task03a.controller.Controller;
import by.traning.task03a.view.View;
import by.traning.task03a.view.factory.ViewFactory;

import static by.traning.task03a.MessageManager.EN;
import static by.traning.task03a.MessageManager.RU;

public class Runnable {
    public static void main(String[] args) {
        ViewFactory viewFactory = ViewFactory.getInstance();
        View consoleView = viewFactory.getConsoleView();
        consoleView.show(EN.getString("menu.lang"));
        MessageManager messageManager = consoleView.read().equals("1") ? EN : RU;
        Controller controller = new Controller();
        boolean isCycleWorks = true;
        while (isCycleWorks) {
            consoleView.show(messageManager.getString("menu.mode"));
            String answer = controller.executeTask(consoleView.read());
            consoleView.show(messageManager.getString(answer));
            isCycleWorks = !answer.equals("response.exit");
        }
    }

}