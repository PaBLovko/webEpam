package by.traning.task04.service.creator;

import by.traning.task04.bean.Wheel;
import by.traning.task04.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The class is the creator for the {@link Wheel Wheel} class
 */
public class WheelCreator {
    private static Logger logger = LogManager.getLogger(WheelCreator.class);

    /**
     * The method that implements the creation of the Wheel class
     * @param diameter wheel diameter
     * @return created class
     * @throws ServiceException when wrong parameters
     */
    public Wheel create(final int diameter) throws ServiceException {
        logger.debug(String.format("The method is invoked, diameter = %s", diameter));
        if (diameter < 15 || diameter > 30) {
            logger.error("The method is exception, non-existent dimensions");
            throw new ServiceException("non-existent dimensions");
        }
        Wheel wheel = new Wheel(diameter);
        logger.info(String.format("The method worked correctly, wheel = %s", wheel));
        return wheel;
    }
}