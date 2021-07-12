package by.traning.task05.service.repository.specification.impl;

import by.traning.task05.bean.Quadrilateral;
import by.traning.task05.service.repository.specification.Specification;
import lombok.NonNull;

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
    public boolean specified(@NonNull Quadrilateral quadrilateral) {
        return id.equals(quadrilateral.getQuadrilateralId());
    }
}
