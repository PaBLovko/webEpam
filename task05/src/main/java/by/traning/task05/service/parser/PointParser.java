package by.traning.task05.service.parser;

import by.traning.task05.bean.Quadrilateral;
import by.traning.task05.service.exception.ServiceException;

import java.util.List;

public interface PointParser {

    /**
     * helper method to clean the code inside  method (pointParser)
     * @param listOfPoints that is already isValidRectangle
     * @return list of points
     */
    List<Quadrilateral.Point> parse(String[] listOfPoints) throws ServiceException;
}
