package by.traning.task05.service.creator;

import by.traning.task05.bean.Quadrilateral;
import by.traning.task05.service.exception.ServiceException;

import java.util.List;

public interface PointCreator {

    /**
     * to parse the list of point List into list of point object.
     * @param listOfPoints to parse into list of point object
     * @return list of isValidRectangle point object
     */
    List<Quadrilateral.Point> create(List<String> listOfPoints) throws ServiceException;
}