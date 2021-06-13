package by.traning.task03a;

import by.traning.task03a.controller.Controller;
import by.traning.task03a.view.Console;
import by.traning.task03a.view.MessageManager;

import static by.traning.task03a.view.MessageManager.EN;
import static by.traning.task03a.view.MessageManager.RU;

public class Runnable {
    public static void main(String[] args) {
        Console console = new Console();
        console.selectLanguage(EN, "menu.lang");
        MessageManager messageManager = console.readConsole().equals("1") ? EN : RU;
        boolean isCycleWorks = true;
        while (isCycleWorks) {
            console.showMenu(messageManager, "menu.mode");
            Controller controller = new Controller();
            String answer = controller.executeTask(console.readConsole());
            console.result(messageManager, answer);
            isCycleWorks = !answer.equals("response.exit");
        }
    }
}