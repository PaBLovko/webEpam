package by.traning.task04.view.impl;

import by.traning.task04.view.View;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * The class implementing {@link View View}
 */
public class ConsoleView implements View {
    private static Logger logger = LogManager.getLogger(ConsoleView.class);

    private static final String  METHOD_IS_INVOKED = "The method is invoked";

    private static final String METHOD_WORKED_CORRECTLY = "The method worked correctly, result = %s";

    @Override
    public void show(String string){
        logger.debug(METHOD_IS_INVOKED);
        System.out.println(string);
        logger.info(String.format(METHOD_WORKED_CORRECTLY, string));
    }

    @Override
    public String read(){
        logger.debug(METHOD_IS_INVOKED);
        Scanner scanner = new Scanner(System.in);
        String result = scanner.nextLine();
        logger.info(String.format(METHOD_WORKED_CORRECTLY, result));
        return result;
    }
}
