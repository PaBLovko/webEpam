package by.traning.task04.service;

import by.traning.task04.service.exception.ServiceException;

/**
 * The interface containing the logic methods of car
 */
public interface CarService {

    /**
     * The method that implements the movement of the machine
     * @throws ServiceException error during the procedure
     */
    void run(String modelName) throws ServiceException;

    /**
     * The method that implements the display of the machine model
     * @return machine model
     * @throws ServiceException error during the procedure
     */
    String nameReceive(String modelName) throws ServiceException;

    /**
     * The method that implements machine wheel frames
     * @throws ServiceException error during the procedure
     */
    void wheelChange(String modelName) throws ServiceException;

    /**
     * The method that implements the refueling of the car
     * @throws ServiceException error during the procedure
     */
    void refuel(String modelName) throws ServiceException;

    /**
     * The method returns all the machines stored in the database
     * @return all the machines
     * @throws ServiceException error during the procedure
     */
    String all() throws ServiceException;
}