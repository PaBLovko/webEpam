package by.traning.task05.service.repository.specification.impl;

import by.traning.task05.bean.Quadrilateral;
import by.traning.task05.service.repository.specification.Specification;
import lombok.NonNull;

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
    public boolean specified(@NonNull Quadrilateral quadrilateral) {
        return  point.equals(quadrilateral.getPointA()) ||
                point.equals(quadrilateral.getPointB()) ||
                point.equals(quadrilateral.getPointC()) ||
                point.equals(quadrilateral.getPointD());
    }
}
