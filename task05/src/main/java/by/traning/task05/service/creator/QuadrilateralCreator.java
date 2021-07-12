package by.traning.task05.service.creator;

import by.traning.task05.bean.Quadrilateral;
import by.traning.task05.service.exception.ServiceException;

import java.util.List;

public interface QuadrilateralCreator {

    /**
     * To create a list of quadrilateral figures after checking that no 3 points on the same line
     * @param pointList isValidRectangle point list to form a quadrilateral figure.
     * @return list of quadrilateral figures.
     */
    List<Quadrilateral> create(List<Quadrilateral.Point> pointList) throws ServiceException;
}
