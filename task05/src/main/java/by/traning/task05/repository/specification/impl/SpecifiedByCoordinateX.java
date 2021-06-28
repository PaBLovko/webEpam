package by.traning.task05.repository.specification.impl;

import by.traning.task05.bean.Quadrilateral;
import by.traning.task05.repository.specification.Specification;

public class SpecifiedByCoordinateX implements Specification {

    private int coordinateX;

    public SpecifiedByCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    @Override
    public boolean specified(Quadrilateral quadrilateral) {
        if (quadrilateral == null) {
            throw new IllegalArgumentException("not allow for the quadrilateral to be null");
        }
        return  coordinateX == quadrilateral.getPointA().getCoordinateX() ||
                coordinateX == quadrilateral.getPointB().getCoordinateX() ||
                coordinateX == quadrilateral.getPointC().getCoordinateX() ||
                coordinateX == quadrilateral.getPointD().getCoordinateX();
    }
}
