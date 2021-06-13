package by.traning.task03a.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Scanner;

public class Console {
    private static Logger logger = LogManager.getLogger(Console.class);

    private static final String  METHOD_IS_INVOKED = "The method is invoked";

    private static final String METHOD_WORKED_CORRECTLY = "The method worked correctly, result = %s";
    public void showMenu(MessageManager massageManager, String string){
        logger.debug(METHOD_IS_INVOKED);
        String result = massageManager.getString(string);
        System.out.println(result);
        logger.info(String.format(METHOD_WORKED_CORRECTLY, result));
    }

    public void selectLanguage(MessageManager massageManager,String string){
        logger.debug(METHOD_IS_INVOKED);
        String result = massageManager.getString(string);
        System.out.println(result);
        logger.info(String.format(METHOD_WORKED_CORRECTLY, result));
    }

    public String readConsole(){
        logger.debug(METHOD_IS_INVOKED);
        Scanner scanner = new Scanner(System.in);
        String result = scanner.next();
        logger.info(String.format(METHOD_WORKED_CORRECTLY, result));
        return result;
    }

    public void result(MessageManager messageManager, String result){
        logger.debug(METHOD_IS_INVOKED);
        System.out.println(messageManager.getString(result));
        logger.info(String.format(METHOD_WORKED_CORRECTLY, result));
    }
}
