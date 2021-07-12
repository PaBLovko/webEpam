package by.traning.task05.service.repository.impl;

import by.traning.task05.bean.Quadrilateral;
import by.traning.task05.service.repository.Repository;
import by.traning.task05.service.repository.specification.Specification;
import by.traning.task05.service.repository.QuadrilateralComparatorClassic;
import by.traning.task05.service.exception.ServiceException;
import lombok.NonNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class RepositoryImpl implements Repository {
    private static Logger logger = LogManager.getLogger(RepositoryImpl.class);

    private List<Quadrilateral> quadrilateralList;

    private static final String THE_METHOD_WORKED_CORRECTLY = "The method worked correctly, quadrilateralList = %s";

    public RepositoryImpl(){
        quadrilateralList = new ArrayList<>();
    }
    public List<Quadrilateral> getQuadrilateralList(){
        return quadrilateralList;
    }

    @Override
    public void add(@NonNull Quadrilateral quadrilateral) {
        logger.debug(String.format("The method is invoked, quadrilateral = %s", quadrilateral));
        quadrilateralList.add(quadrilateral);
        logger.info(String.format(THE_METHOD_WORKED_CORRECTLY, quadrilateralList));
    }

    @Override
    public void add(@NonNull Iterable<Quadrilateral> quadrilaterals) {
        logger.debug(String.format("The method is invoked, quadrilaterals = %s", quadrilaterals));
        for(Quadrilateral q : quadrilaterals){
            quadrilateralList.add(q);
        }
        logger.info(String.format(THE_METHOD_WORKED_CORRECTLY, quadrilateralList));
    }

    @Override
    public void remove(@NonNull Quadrilateral quadrilateral) {
        logger.debug(String.format("The method is invoked, quadrilateral = %s", quadrilateral));
        if(!quadrilateralList.isEmpty()) {
            quadrilateralList.remove(quadrilateral);
            logger.info(String.format(THE_METHOD_WORKED_CORRECTLY, quadrilateralList));
        }
        logger.info("quadrilateralList is empty");
    }

    @Override
    public void remove(@NonNull Specification specification) {
        logger.debug(String.format("The method is invoked, specification = %s", specification));
        if(!quadrilateralList.isEmpty()){
            quadrilateralList.removeIf(specification::specified);
            logger.info(String.format(THE_METHOD_WORKED_CORRECTLY, quadrilateralList));
        }
        logger.info("quadrilateralList is empty");
    }

    @Override
    public void update(@NonNull Quadrilateral oldQuadrilateral, @NonNull Quadrilateral newQuadrilateral) {
        logger.debug(String.format("The method is invoked, oldQuadrilateral = %s, newQuadrilateral = %s",
                oldQuadrilateral, newQuadrilateral));
        if(!quadrilateralList.contains(oldQuadrilateral)){
            logger.info("The oldQuadrilateral object is not exist in the quadrilateralList so we can not update it");
        }
        int quadrilateralIndex = quadrilateralList.indexOf(oldQuadrilateral);
        quadrilateralList.set(quadrilateralIndex, newQuadrilateral);
        logger.info(String.format(THE_METHOD_WORKED_CORRECTLY, quadrilateralList));
    }

    @Override
    public List<Quadrilateral> query(@NonNull Specification specification) {
        logger.debug(String.format("The method is invoked, specification = %s", specification));
        List<Quadrilateral> findingQuadrilateral = new ArrayList<>();
        if(!quadrilateralList.isEmpty()){
            for(Quadrilateral q : quadrilateralList){
                if(specification.specified(q)){
                    findingQuadrilateral.add(q);
                }
            }
        }
        logger.info(String.format(THE_METHOD_WORKED_CORRECTLY, findingQuadrilateral));
        return findingQuadrilateral;
    }

    @Override
    public void sort(@NonNull QuadrilateralComparatorClassic compare) throws ServiceException {
        logger.debug(String.format("The method is invoked, compare = %s", compare));
        if (quadrilateralList == null) {
            throw new ServiceException("Not allow for the quadrilateralList or QuadrilateralComparator to be null");
        }
        getQuadrilateralList().sort(compare);
        logger.info(String.format(THE_METHOD_WORKED_CORRECTLY, quadrilateralList));
//        for(int i = 0; i < quadrilateralList.size(); i++){
//            for (int x = i; x < quadrilateralList.size(); x++){
//                if(compare.compare(quadrilateralList.get(i), quadrilateralList.get(x)) > 0){
//                    Quadrilateral o = quadrilateralList.get(x);
//                    quadrilateralList.set(x, quadrilateralList.get(i));
//                    quadrilateralList.set(i, o);
//                }
//            }
//        }
    }
}
