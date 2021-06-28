package by.traning.task05.repository.specification.impl;

import by.traning.task05.bean.Quadrilateral;
import by.traning.task05.repository.specification.Specification;

public class SpecifiedByPoint implements Specification {

    private Quadrilateral.Point point;

    public SpecifiedByPoint(Quadrilateral.Point point) {
        setPoint(point);
    }

    private void setPoint(Quadrilateral.Point point){
        if (point == null) {
            throw new IllegalArgumentException("not allow for a point to be null");
        }
        this.point = point;
    }
    @Override
    public boolean specified(Quadrilateral quadrilateral) {
        if (quadrilateral == null) {
            throw new IllegalArgumentException("not allow for the quadrilateral to be null");
        }
        return  point.equals(quadrilateral.getPointA()) ||
                point.equals(quadrilateral.getPointB()) ||
                point.equals(quadrilateral.getPointC()) ||
                point.equals(quadrilateral.getPointD());
    }
}
