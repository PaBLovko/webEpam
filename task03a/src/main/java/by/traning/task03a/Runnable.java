package by.traning.task03a;

import by.traning.task03a.controller.Controller;
import by.traning.task03a.view.ConsoleView;
import by.traning.task03a.view.View;

import static by.traning.task03a.MessageManager.EN;
import static by.traning.task03a.MessageManager.RU;

public class Runnable {
    public static void main(String[] args) {
        View view = new ConsoleView();
        view.show(EN.getString("menu.lang"));
        MessageManager messageManager = view.readConsole().equals("1") ? EN : RU;
        boolean isCycleWorks = true;
        while (isCycleWorks) {
            view.show(messageManager.getString("menu.mode"));
            Controller controller = new Controller();
            String answer = controller.executeTask(view.readConsole());
            view.show(messageManager.getString(answer));
            isCycleWorks = !answer.equals("response.exit");
        }
    }
}