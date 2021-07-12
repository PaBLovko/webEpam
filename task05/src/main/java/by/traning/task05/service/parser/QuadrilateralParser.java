package by.traning.task05.service.parser;

import by.traning.task05.bean.Quadrilateral;
import by.traning.task05.service.exception.ServiceException;

import java.util.List;

public interface QuadrilateralParser {

    /**
     * Helper method to return list of list containing just 4 points which we can use to form a quadrilateral figure
     * we are using it in the quadrilateralParser method.
     * @param pointList isValidRectangle point list to form a quadrilateral figure.
     * @return list of point list contains of 4 points each list.
     */
    List<List<Quadrilateral.Point>> parse(List<Quadrilateral.Point> pointList) throws ServiceException;
}
