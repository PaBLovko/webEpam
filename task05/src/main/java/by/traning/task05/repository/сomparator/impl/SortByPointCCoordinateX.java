package by.traning.task05.repository.сomparator.impl;

import by.traning.task05.bean.Quadrilateral;
import by.traning.task05.repository.сomparator.QuadrilateralComparator;

public class SortByPointCCoordinateX implements QuadrilateralComparator {

    @Override
    public int compare(Quadrilateral q1, Quadrilateral q2) {
        return q1.getPointC().getCoordinateX() - q2.getPointC().getCoordinateX();
    }
}
