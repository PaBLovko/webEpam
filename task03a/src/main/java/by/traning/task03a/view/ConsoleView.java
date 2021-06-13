package by.traning.task03a.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Scanner;

/**
 * The class implementing {@link by.traning.task03a.view.View View}
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
    public String readConsole(){
        logger.debug(METHOD_IS_INVOKED);
        Scanner scanner = new Scanner(System.in);
        String result = scanner.next();
        logger.info(String.format(METHOD_WORKED_CORRECTLY, result));
        return result;
    }
}
