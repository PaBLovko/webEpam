package by.traning.task05.service.parser.impl;

import by.traning.task05.bean.Quadrilateral;
import by.traning.task05.service.exception.ServiceException;
import by.traning.task05.service.parser.PointParser;
import by.traning.task05.service.util.FrequentlyUsedRegex;

import java.util.ArrayList;
import java.util.List;

public class PointParserImpl implements PointParser {
    @Override
    public List<Quadrilateral.Point> parse(String[] listOfPoints) throws ServiceException {
        if (listOfPoints == null) {
            throw new ServiceException("not allow for the list of points to be null");
        }
        List<Quadrilateral.Point> pointList = new ArrayList<>();
        for (String arrayOfPoint : listOfPoints) {
            int delimiter = arrayOfPoint.indexOf(FrequentlyUsedRegex.COMMA_SEPARATOR);
            int x = Integer.parseInt(arrayOfPoint.substring(FrequentlyUsedRegex.INDEX_ZERO, delimiter));
            int y = Integer.parseInt(arrayOfPoint.substring(delimiter + FrequentlyUsedRegex.NEXT_INDEX));
            Quadrilateral.Point point = new Quadrilateral().new Point(x, y);
            pointList.add(point);
        }
        return pointList;
    }
}
