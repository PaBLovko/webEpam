package by.traning.task05.service.observer.impl;

import by.traning.task05.bean.Quadrilateral;
import by.traning.task05.bean.QuadrilateralParameter;
import by.traning.task05.service.observer.Observer;
import by.traning.task05.service.action.CalculatorAction;
import lombok.NonNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class ObserverQuadrilateral implements Observer {
    private static Logger logger = LogManager.getLogger(ObserverQuadrilateral.class);
    private CalculatorAction calculator;
    private static ObserverQuadrilateral instance;

    private Map<Integer, QuadrilateralParameter> quadrilateralParameter;

    private ObserverQuadrilateral() {
        quadrilateralParameter = new HashMap<>();
    }

    public static ObserverQuadrilateral getInstance() {
        if (instance == null) {
            instance = new ObserverQuadrilateral();
        }
        return instance;
    }

    /**
     * get the quadrilateral parameter by the element ID
     * @param itemId as a key to find in the map
     * @return QuadrilateralParameter object if the key is in the map or null if it is not exist.
     */
    public QuadrilateralParameter getQuadrilateralParameterByItemId(@NonNull Integer itemId) {
        if (itemId < 0) {
            throw new IllegalArgumentException("Item Id not allow to be negative value");
            //TODO
        }
        if (!quadrilateralParameter.containsKey(itemId)) {
            logger.info("The item id is not exist as a key so we returns");
        }
        return quadrilateralParameter.get(itemId);
    }

    public Map<Integer, QuadrilateralParameter> getQuadrilateralParameter() {
        return quadrilateralParameter;
    }

    /**
     * to set the calculator to the desired calculator implementation if it square or rectangle,,,, etc.
     * @param calculator reference to the desired calculator implementation
     */
    public void setCalculator(@NonNull CalculatorAction calculator) {
        this.calculator = calculator;
    }

    /**
     * updating parameters
     * @param item to store in the map also to use it in the observable class
     */
    @Override
    public void update(@NotNull Quadrilateral item) {
        double area = calculator.areaCalculator(item);
        double perimeter = calculator.perimeterCalculator(item);
        quadrilateralParameter.put(item.getQuadrilateralId(), new QuadrilateralParameter(perimeter, area));
    }
}
