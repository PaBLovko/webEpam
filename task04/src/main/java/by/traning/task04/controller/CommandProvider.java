package by.traning.task04.controller;

import by.traning.task04.controller.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.EnumMap;
import java.util.Map;

/**
 * The class containing commands
 */
final class CommandProvider {
    private static Logger logger = LogManager.getLogger(CommandProvider.class);

    /**
     * The object of the EnumMap class where the key is the name of the command,
     * and the value is the class that implements this command
     */
    private final Map<CommandName, Command> repository = new EnumMap<>(CommandName.class);

    CommandProvider(){
        repository.put(CommandName.CAR_ALL, new CarAll());
        repository.put(CommandName.CAR_RUN, new CarRun());
        repository.put(CommandName.CAR_NAME_RECEIVE, new CarNameReceive());
        repository.put(CommandName.CAR_WHEEL_CHANGE, new CarWheelChange());
        repository.put(CommandName.CAR_REFUEL, new CarRefuel());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
        repository.put(CommandName.EXIT, new Exit());
    }

    /**
     * The method that gets a command from the repository variable by the received command name
     * @param name the name command
     * @return an interview containing the method of the selected command
     */
    Command getCommand(String name){
        logger.debug(String.format("The method is invoked, name = %s", name));
        CommandName commandName;
        Command command;
        try{
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        }catch(IllegalArgumentException | NullPointerException e){
            logger.error("The method is exception, wrong request");
            command = repository.get(CommandName.WRONG_REQUEST);
        }
        logger.info(String.format("The method worked correctly, command = %s", command));
        return command;
    }
}