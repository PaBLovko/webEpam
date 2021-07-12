package by.traning.task05.service.repository;

import by.traning.task05.bean.Quadrilateral;
import by.traning.task05.service.repository.specification.Specification;
import by.traning.task05.service.exception.ServiceException;
import lombok.NonNull;

import java.util.List;

/**
 * Repository template class
 */
public interface Repository {
    /**
     * adding an element
     * @param quadrilateral the element to be added
     */
    void add(@NonNull Quadrilateral quadrilateral);

    /**
     * adding a number of elements
     * @param quadrilaterals the row of elements itself
     */
    void add(@NonNull Iterable<Quadrilateral> quadrilaterals);

    /**
     * deleting an element by object
     * @param quadrilateral the object itself
     */
    void remove(@NonNull Quadrilateral quadrilateral);

    /**
     * deleting an object according to the specified specification
     * @param specification the specification itself
     */
    void remove(@NonNull Specification specification);

    /**
     * updating an object
     * @param oldQuadrilateral old object
     * @param newQuadrilateral new object
     */
    void update(@NonNull Quadrilateral oldQuadrilateral, @NonNull Quadrilateral newQuadrilateral);

    /**
     * creates a queue with the matching specification passed to this method
     * @param specification the specification itself
     * @return returns a queue with a matching specification
     */
    List<Quadrilateral> query(@NonNull Specification specification);

    /**
     * sorting method by the passed parameter
     * @param compare sorting method
     * @throws ServiceException when list is empty
     */
    void sort(@NonNull QuadrilateralComparatorClassic compare) throws ServiceException;
}
