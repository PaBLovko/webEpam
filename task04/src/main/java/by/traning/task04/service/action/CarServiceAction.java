package by.traning.task04.service.action;

import by.traning.task04.bean.Car;
import by.traning.task04.bean.Wheel;
import by.traning.task04.service.creator.WheelCreator;
import by.traning.task04.service.exception.ServiceException;
import lombok.NonNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Random;

/**
 * This class represents the logic of the {@link Car Car} class
 */
public class CarServiceAction {
    private static Logger logger = LogManager.getLogger(CarServiceAction.class);

    /**
     * The string literal describing that method is invoked
     */
    private static final String METHOD_IS_INVOKED = "The method is invoked, car = %s";

    /**
     * The string literal describing that method worked correctly
     */
    private static final String METHOD_WORKED_CORRECTLY_CAR = "The method worked correctly, car = %s";

    /**
     * The string literal describing that method worked correctly
     */
    private static final String METHOD_WORKED_CORRECTLY_RESULT = "The method worked correctly, result = %s";

    /**
     * The method that implements the auto movement
     * @param car the object of the class on which the action is performed
     */
    public void run(@NonNull Car car){
        logger.debug(String.format(METHOD_IS_INVOKED, car));
        int event = new Random().nextInt(3);
        if (event == 0){
            breakWheel(car);
        }else if (event == 1){
            outOfFuel(car);
        }
        logger.info(String.format(METHOD_WORKED_CORRECTLY_CAR, car));
    }

    /**
     * The method that implements wheel breakage
     * @param car the object of the class on which the action is performed
     */
    private void breakWheel(@NonNull Car car){
        logger.debug(String.format(METHOD_IS_INVOKED, car));
        car.getWheels().remove(new Random().nextInt(4));
        logger.info(String.format(METHOD_WORKED_CORRECTLY_CAR, car));
    }

    /**
     * The method that implements emptying the tank
     * @param car the object of the class on which the action is performed
     */
    private void outOfFuel(@NonNull Car car){
        logger.debug(String.format(METHOD_IS_INVOKED, car));
        car.setFuel(false);
        logger.info(String.format(METHOD_WORKED_CORRECTLY_CAR, car));
    }

    /**
     * The method that implements filling the tank
     * @param car the object of the class on which the action is performed
     */
    public boolean refuel(@NonNull Car car) throws ServiceException{
        logger.debug(String.format(METHOD_IS_INVOKED, car));
        if (car.isFuel()){
            throw new ServiceException("the tank is full");
        }
        car.setFuel(true);
        boolean result = car.isFuel();
        logger.info(String.format(METHOD_WORKED_CORRECTLY_RESULT, result));
        return result;
    }

    /**
     * The method that implements wheel replacement
     * @param car the object of the class on which the action is performed
     * @throws ServiceException when all wheels in stock
     */
    public void wheelChange(@NonNull Car car) throws ServiceException {
        logger.debug(String.format(METHOD_IS_INVOKED, car));
        if (isAllWheels(car.getWheels())) {
            car.getWheels().add(new WheelCreator().create(car.getWheels().get(0).getDiameter()));
        }else throw new ServiceException("all wheels are in place");
        logger.info(String.format(METHOD_WORKED_CORRECTLY_CAR, car));
    }

    /**
     * The method checks for the presence of all four wheels in the car
     * @param wheels wheel collection
     * @return true if all wheels in stock else return false
     */
    private boolean isAllWheels(@NonNull List<Wheel> wheels){
        logger.debug(String.format("The method is invoked, wheels = %s", wheels));
        boolean result = wheels.size() >= 4;
        logger.info(String.format(METHOD_WORKED_CORRECTLY_RESULT, result));
        return result;
    }

    /**
     * The method that car name search
     * @param car the object of the class on which the action is performed
     * @return car model name
     */
    public String modelName(@NonNull Car car){
        logger.debug(String.format(METHOD_IS_INVOKED, car));
        String modelName = car.getModelName();
        logger.info(String.format(METHOD_WORKED_CORRECTLY_CAR, car));
        return modelName;
    }
}
