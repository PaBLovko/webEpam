package by.traning.task05.view.impl;

import by.traning.task05.view.MessageManager;
import by.traning.task05.view.Screen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

import static by.traning.task05.view.MessageManager.EN;

/**
 * The class implementing {@link Screen View}
 */
public class ConsoleScreen implements Screen {
    private static Logger logger = LogManager.getLogger(ConsoleScreen.class);

    private static final String METHOD_IS_INVOKED = "The method is invoked";

    private static final String METHOD_WORKED_CORRECTLY = "The method worked correctly, result = %s";

    private MessageManager messageManager = EN;

    @Override
    public void show(String string){
        logger.debug(METHOD_IS_INVOKED);
        System.out.println(messageManager.getString(string));
        logger.info(String.format(METHOD_WORKED_CORRECTLY, string));
    }

    @Override
    public String read(){
        logger.debug(METHOD_IS_INVOKED);
        Scanner scanner = new Scanner(System.in);
        String result = scanner.next();
        logger.info(String.format(METHOD_WORKED_CORRECTLY, result));
        return result;
    }

    @Override
    public void setMessageManager(MessageManager messageManager) {
        this.messageManager = messageManager;
    }
}
