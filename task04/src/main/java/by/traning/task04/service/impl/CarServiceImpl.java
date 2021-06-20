package by.traning.task04.service.impl;

import by.traning.task04.bean.Car;
import by.traning.task04.dao.CarDAO;
import by.traning.task04.dao.exception.DAOException;
import by.traning.task04.dao.factory.DAOFactory;
import by.traning.task04.service.CarService;
import by.traning.task04.service.action.CarServiceAction;
import by.traning.task04.service.creator.CarCreator;
import by.traning.task04.service.exception.ServiceException;
import lombok.NonNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class CarServiceImpl implements CarService {
    private static Logger logger = LogManager.getLogger(CarServiceImpl.class);

    /**
     * The string literal describing that method worked correctly
     */
    private static final String METHOD_WORKED_CORRECTLY_CAR = "The method worked correctly, car = %s";

    /**
     * The string literal describing that method worked correctly
     */
    private static final String METHOD_WORKED_CORRECTLY_RESULT = "The method worked correctly, result = %s";

    /**
     * The string literal describing that method is invoked
     */
    private static final String METHOD_IS_INVOKED = "The method is invoked";

    /**
     * The string literal describing that method did not write data
     */
    private static final String ERROR_WRITE = "Error during writing";

    /**
     * The string literal describing that method is exception
     */
    private static final String ERROR_READ = "Error during reading";

    /**
     * The string literal describing that method is exception
     */
    private static final String ERROR_FIND = "Error during found";
    /**
     * The string literal describing the path to the array file
     */
    private static final String PATH_CARS = "src/main/resources/cars.txt";


    @NonNull
    private Car findCar(@NonNull String modelName) throws ServiceException {
        logger.debug(METHOD_IS_INVOKED);
        DAOFactory daoFactory = DAOFactory.getInstance();
        CarDAO carDAO = daoFactory.getFileCarImpl();
        Car car = null;
        try {
            List<String> cars = carDAO.read(new File(PATH_CARS));
            for (String s: cars){
                if (s.contains(modelName)){
                    List<String> carSegment = Arrays.asList(s.split("\\s"));
                    car = new CarCreator().create(carSegment.get(0), Integer.parseInt(carSegment.get(1)),
                            Double.parseDouble(carSegment.get(2)), Integer.parseInt(carSegment.get(3)));
                    break;
                }
            }
        }catch (DAOException e){
            logger.error(ERROR_READ, e);
            throw new ServiceException(ERROR_READ, e);
        }
        if (car == null){
            logger.error(ERROR_FIND);
            throw new ServiceException(ERROR_FIND);
        }
        logger.info(String.format(METHOD_WORKED_CORRECTLY_CAR, car));
        return car;
    }


    @Override
    public void run(String modelName) throws ServiceException {
        logger.debug(METHOD_IS_INVOKED);
        Car car = findCar(modelName);
        new CarServiceAction().run(car);
        logger.info(String.format(METHOD_WORKED_CORRECTLY_CAR, car));
    }

    @Override
    public String nameReceive(String modelName) throws ServiceException {
        logger.debug(METHOD_IS_INVOKED);
        Car car = findCar(modelName);
        String name = new CarServiceAction().modelName(car);
        logger.info(String.format(METHOD_WORKED_CORRECTLY_CAR, car));
        return name;
    }

    @Override
    public void wheelChange(String modelName) throws ServiceException {
        logger.debug(METHOD_IS_INVOKED);
        Car car = findCar(modelName);
        new CarServiceAction().wheelChange(car);
        logger.info(String.format(METHOD_WORKED_CORRECTLY_CAR, car));
    }

    @Override
    public void refuel(String modelName) throws ServiceException {
        logger.debug(METHOD_IS_INVOKED);
        Car car = findCar(modelName);
        new CarServiceAction().refuel(car);
        logger.info(String.format(METHOD_WORKED_CORRECTLY_CAR, car));
    }

    @Override
    public String all() throws ServiceException {
        logger.debug(METHOD_IS_INVOKED);
        DAOFactory daoFactory = DAOFactory.getInstance();
        CarDAO carDAO = daoFactory.getFileCarImpl();
        List<String> cars;
        try {
            cars = carDAO.read(new File(PATH_CARS));
        } catch (DAOException e) {
            logger.error(ERROR_READ, e);
            throw new ServiceException(ERROR_READ, e);
        }
        String result = cars.toString().replaceAll("^\\[|\\]$", "");
        logger.info(String.format(METHOD_WORKED_CORRECTLY_RESULT, result));
        return result;
    }
}
