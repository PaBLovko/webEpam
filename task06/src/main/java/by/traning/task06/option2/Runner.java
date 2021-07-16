package by.traning.task06.option2;

import by.traning.task06.option2.controller.Controller;

public class Runner {
    public static void main(String[] args) {
        Controller controller = new Controller();
        String path = "data/matrix.txt";
        controller.execute(path);
    }
}
