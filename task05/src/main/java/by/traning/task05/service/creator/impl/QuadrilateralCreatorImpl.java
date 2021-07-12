package by.traning.task05.service.creator.impl;

import by.traning.task05.bean.Quadrilateral;
import by.traning.task05.service.creator.QuadrilateralCreator;
import by.traning.task05.service.exception.ServiceException;
import by.traning.task05.service.parser.QuadrilateralParser;
import by.traning.task05.service.parser.impl.QuadrilateralParserImpl;
import by.traning.task05.service.validator.quadrilateralvalidator.ConvexValidator;
import by.traning.task05.service.validator.quadrilateralvalidator.QuadrilateralValidator;

import java.util.ArrayList;
import java.util.List;

public class QuadrilateralCreatorImpl implements QuadrilateralCreator {

    @Override
    public List<Quadrilateral> create(List<Quadrilateral.Point> pointList) throws ServiceException {
        if (pointList == null) {
            throw new ServiceException("not allow for the pointList to be null");
        }
        List<Quadrilateral> quadrilateralList = new ArrayList<>();
        QuadrilateralParser quadrilateralParserImpl = new QuadrilateralParserImpl();
        for (List<Quadrilateral.Point> aPointsList : quadrilateralParserImpl.parse(pointList)) {
            Quadrilateral.Point pointA = aPointsList.get(0);
            Quadrilateral.Point pointB = aPointsList.get(1);
            Quadrilateral.Point pointC = aPointsList.get(2);
            Quadrilateral.Point pointD = aPointsList.get(3);
            ConvexValidator convexValidator = new ConvexValidator();
            QuadrilateralValidator quadrilateralValidator = new QuadrilateralValidator();

            if((!quadrilateralValidator.isValidQuadrilateral(pointA, pointB, pointC, pointD)) &&
                    (convexValidator.isConvex(pointA, pointB, pointC, pointD))){
                Quadrilateral quadrilateral = new Quadrilateral(pointA, pointB, pointC, pointD);
                //quadrilateral.setGeneratedQuadrilateralId();
                quadrilateralList.add(quadrilateral);
            }
        }
        return quadrilateralList;
    }

}
