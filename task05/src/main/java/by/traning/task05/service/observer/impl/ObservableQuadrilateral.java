package by.traning.task05.service.observer.impl;

import by.traning.task05.bean.Quadrilateral;
import by.traning.task05.service.observer.Observer;
import by.traning.task05.service.observer.Observable;
import lombok.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ObservableQuadrilateral extends Quadrilateral implements Observable {
    private static Logger logger = LogManager.getLogger(ObservableQuadrilateral.class);

    private List<Observer> observerList = new ArrayList<>();

    public ObservableQuadrilateral(Quadrilateral.Point pointA, Quadrilateral.Point pointB,
                                   Quadrilateral.Point pointC, Quadrilateral.Point pointD) {
        super(pointA, pointB, pointC, pointD);
    }

    @Override
    public void setPointA(@NotNull Quadrilateral.Point pointA) {
        super.setPointA(pointA);
        notifyObserver();
    }

    @Override
    public void setPointB(@NotNull Quadrilateral.Point pointB) {
        super.setPointB(pointB);
        notifyObserver();
    }

    @Override
    public void setPointC(@NotNull Quadrilateral.Point pointC) {
        super.setPointC(pointC);
        notifyObserver();
    }

    @Override
    public void setPointD(@NotNull Quadrilateral.Point pointD) {
        super.setPointD(pointD);
        notifyObserver();
    }

    public List<Observer> getObserverList() {
        return observerList;
    }

    @Override
    public void setQuadrilateralId(@NotNull Integer quadrilateralId) {
        super.setQuadrilateralId(quadrilateralId);
        notifyObserver();
    }

    @Override
    public void setGeneratedQuadrilateralId() {
        super.setGeneratedQuadrilateralId();
        notifyObserver();
    }

    @NotNull
    @Override
    public Integer getQuadrilateralId() {
        return super.getQuadrilateralId();
    }

    @Override
    public void addObserver(@NonNull Observer o) {
        observerList.add(o);
    }

    @Override
    public void removeObserver(@NonNull Observer o) {
        if(!observerList.isEmpty()){
            observerList.remove(o);
        }
        logger.info("Observer List is empty");
    }

    @Override
    public void notifyObserver() {
        for (Observer o : observerList) {
            o.update(this);
        }
    }
}
