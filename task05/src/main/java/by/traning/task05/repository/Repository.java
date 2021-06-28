package by.traning.task05.repository;

import by.traning.task05.bean.Quadrilateral;
import by.traning.task05.repository.specification.Specification;
import by.traning.task05.service.exception.ServiceException;

import java.util.List;

public interface Repository {
    void add(Quadrilateral quadrilateral);
    void add(Iterable<Quadrilateral> quadrilaterals);
    void remove(Quadrilateral quadrilateral);
    void remove(Specification specification);
    void update(Quadrilateral oldQuadrilateral, Quadrilateral newQuadrilateral);
    List<Quadrilateral> query(Specification specification);
    void sort(QuadrilateralComparatorClassic compare) throws ServiceException;
}
