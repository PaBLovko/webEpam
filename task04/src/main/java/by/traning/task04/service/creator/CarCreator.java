package by.traning.task04.service.creator;

import by.traning.task04.bean.Car;
import by.traning.task04.bean.Engine;
import by.traning.task04.bean.Wheel;
import by.traning.task04.service.exception.ServiceException;
import lombok.NonNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * The class is the creator for the {@link Car Car} class
 */
public class CarCreator {
    private static Logger logger = LogManager.getLogger(CarCreator.class);

    /**
     * The method that implements the creation of the Car class
     * @param modelName car model name
     * @param power car power
     * @param volume car volume
     * @param diameter car diameter
     * @return created class
     * @throws ServiceException when wrong parameters
     */
    public Car create(@NonNull final String modelName, final int power, final double volume, final int diameter)
            throws ServiceException {
        logger.debug(String.format("The method is invoked, modelName = %s, power = %s, volume = %s, diameter = %s",
                modelName, power, volume, diameter));
        Engine engine = new EngineCreator().create(power, volume);
        List<Wheel> wheels = new ArrayList<>();
        WheelCreator wheelCreator = new WheelCreator();
        for (int i = 0; i < 4; i++){
            wheels.add(wheelCreator.create(diameter));
        }
        Car car = new Car(modelName, engine, wheels);
        logger.info(String.format("The method worked correctly, car = %s", car));
        return car;
    }
}
