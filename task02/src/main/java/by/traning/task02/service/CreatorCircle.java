package by.traning.task02.service;

import by.traning.task02.bean.Circle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The class is the creator for the {@link by.traning.task02.bean.Circle Circle} class
 */
public class CreatorCircle {

    private static Logger logger = LogManager.getLogger(CreatorCircle.class);

    /**
     * The method that implements the creation of the Circle class
     * @param area area of the created class
     * @return created class
     * @throws IllegalArgumentException occurs when passing non-natural values to the method
     */
    public Circle create(int area) throws IllegalArgumentException{
        logger.debug(String.format("The method is invoked, area = %s",area));
        if (area <= 0) {
            logger.error("The method is exception, the area is not natural");
            throw new IllegalArgumentException("the data must be natural");
        }
        Circle circle = new Circle(area);
        logger.info(String.format("The method worked correctly, circle = %s", circle));
        return circle;
    }
}
