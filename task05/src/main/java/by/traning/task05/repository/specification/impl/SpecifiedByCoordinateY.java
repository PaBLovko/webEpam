package by.traning.task05.repository.specification.impl;

import by.traning.task05.bean.Quadrilateral;
import by.traning.task05.repository.specification.Specification;

public class SpecifiedByCoordinateY implements Specification {

    private int coordinateY;

    public SpecifiedByCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    @Override
    public boolean specified(Quadrilateral quadrilateral) {
        if (quadrilateral == null) {
            throw new IllegalArgumentException("not allow for the quadrilateral to be null");
        }
        return  coordinateY == quadrilateral.getPointA().getCoordinateY() ||
                coordinateY == quadrilateral.getPointB().getCoordinateY() ||
                coordinateY == quadrilateral.getPointC().getCoordinateY() ||
                coordinateY == quadrilateral.getPointD().getCoordinateY();
    }
}
