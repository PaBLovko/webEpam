package by.traning.task05.repository.сomparator;

import by.traning.task05.bean.Quadrilateral;

import java.util.Comparator;

public interface QuadrilateralComparator extends Comparator<Quadrilateral> {
    int compare(Quadrilateral q1, Quadrilateral q2);
}
