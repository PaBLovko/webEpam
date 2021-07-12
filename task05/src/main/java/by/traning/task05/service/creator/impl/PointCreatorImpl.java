package by.traning.task05.service.creator.impl;

import by.traning.task05.bean.Quadrilateral;
import by.traning.task05.service.creator.PointCreator;
import by.traning.task05.service.exception.ServiceException;
import by.traning.task05.service.parser.PointParser;
import by.traning.task05.service.parser.impl.PointParserImpl;
import by.traning.task05.service.util.FrequentlyUsedRegex;
import by.traning.task05.service.validator.PointValidator;

import java.util.ArrayList;
import java.util.List;

public class PointCreatorImpl implements PointCreator {

    @Override
    public List<Quadrilateral.Point> create(List<String> listOfPoints) throws ServiceException {
        if (listOfPoints == null) {
            throw new ServiceException("not allow for the list of points to be null");
        }
        List<Quadrilateral.Point> pointList = new ArrayList<>();
        PointValidator pointValidator = new PointValidator();
        PointParser pointParserImpl = new PointParserImpl();
        for (String points : listOfPoints) {
            points = points.trim();
            String[] arrayOfPoints = points.split(FrequentlyUsedRegex.WHITE_SPACE);
            if (pointValidator.isValidPoint(arrayOfPoints)) {
                pointList.addAll(pointParserImpl.parse(arrayOfPoints));
            }
        }
        return pointList;
    }
}
