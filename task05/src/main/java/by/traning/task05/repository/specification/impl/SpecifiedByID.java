package by.traning.task05.repository.specification.impl;

import by.traning.task05.bean.Quadrilateral;
import by.traning.task05.repository.specification.Specification;

public class SpecifiedByID implements Specification {
    private Integer id;

    public SpecifiedByID(Integer id){
        setId(id);
    }

    private void setId(Integer id){
        if(id == null){
            throw new IllegalArgumentException("Id not allow to be null");
        }

        this.id = id;
    }
    @Override
    public boolean specified(Quadrilateral quadrilateral) {
        if (quadrilateral == null) {
            throw new IllegalArgumentException("not allow for the quadrilateral to be null");
        }
        return id.equals(quadrilateral.getQuadrilateralId());
    }
}
