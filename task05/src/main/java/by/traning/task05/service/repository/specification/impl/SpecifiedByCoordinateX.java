package by.traning.task05.service.repository.specification.impl;

import by.traning.task05.bean.Quadrilateral;
import by.traning.task05.service.repository.specification.Specification;
import lombok.NonNull;

public class SpecifiedByCoordinateX implements Specification {

    private int coordinateX;

    public SpecifiedByCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    @Override
    public boolean specified(@NonNull Quadrilateral quadrilateral) {
        return  coordinateX == quadrilateral.getPointA().getCoordinateX() ||
                coordinateX == quadrilateral.getPointB().getCoordinateX() ||
                coordinateX == quadrilateral.getPointC().getCoordinateX() ||
                coordinateX == quadrilateral.getPointD().getCoordinateX();
    }
}
