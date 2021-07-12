package by.traning.task05.service.repository.specification.impl;

import by.traning.task05.bean.Quadrilateral;
import by.traning.task05.service.repository.specification.Specification;
import lombok.NonNull;

public class SpecifiedByCoordinateY implements Specification {

    private int coordinateY;

    public SpecifiedByCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    @Override
    public boolean specified(@NonNull Quadrilateral quadrilateral) {
        return  coordinateY == quadrilateral.getPointA().getCoordinateY() ||
                coordinateY == quadrilateral.getPointB().getCoordinateY() ||
                coordinateY == quadrilateral.getPointC().getCoordinateY() ||
                coordinateY == quadrilateral.getPointD().getCoordinateY();
    }
}
