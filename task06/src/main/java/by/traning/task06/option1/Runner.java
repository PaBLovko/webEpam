package by.traning.task06.option1;

import by.traning.task06.option1.controller.Controller;

public class Runner {
    public static void main(String[] args) {
        Controller controller = new Controller();
        String path = "data/threads.txt";
        controller.execute(path);
    }
}
