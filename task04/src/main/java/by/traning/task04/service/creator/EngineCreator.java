package by.traning.task04.service.creator;

import by.traning.task04.bean.Engine;
import by.traning.task04.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The class is the creator for the {@link Engine Engine} class
 */
public class EngineCreator {
    private static Logger logger = LogManager.getLogger(EngineCreator.class);

    /**
     * The method that implements the creation of the Engine class
     * @param power engine power
     * @param volume engine volume
     * @return created class
     * @throws ServiceException when wrong parameters
     */
    public Engine create(final int power, final double volume) throws ServiceException {
        logger.debug(String.format("The method is invoked, power = %s, volume = %s", power, volume));
        if ((power < 15 || power > 3000) && (volume < 1 || volume > 12)) {
            logger.error("The method is exception, non-existent dimensions");
            throw new ServiceException("non-existent dimensions");
        }
        Engine engine = new Engine(power, volume);
        logger.info(String.format("The method worked correctly, engine = %s", engine));
        return engine;
    }
}
