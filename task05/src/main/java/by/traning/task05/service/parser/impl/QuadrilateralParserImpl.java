package by.traning.task05.service.parser.impl;

import by.traning.task05.bean.Quadrilateral;
import by.traning.task05.service.exception.ServiceException;
import by.traning.task05.service.parser.QuadrilateralParser;
import by.traning.task05.service.util.FrequentlyUsedRegex;

import java.util.ArrayList;
import java.util.List;

public class QuadrilateralParserImpl implements QuadrilateralParser {
    @Override
    public List<List<Quadrilateral.Point>> parse(List<Quadrilateral.Point> pointList) throws ServiceException {
        if (pointList == null) {
            throw new ServiceException("not allow for the pointList to be null");
        }
        List<Quadrilateral.Point> coordinate = new ArrayList<>();
        List<List<Quadrilateral.Point>> fourPointsList = new ArrayList<>();

        int counter = 0;
        for (Quadrilateral.Point point : pointList) {
            coordinate.add(point);
            counter++;
            if (counter == FrequentlyUsedRegex.VALID_ARRAY_POINTS_LENGTH) {
                fourPointsList.add(coordinate);
                coordinate = new ArrayList<>();
                counter = 0;
            }
        }
        return fourPointsList;
    }
}
