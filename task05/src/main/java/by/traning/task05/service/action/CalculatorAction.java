package by.traning.task05.service.action;

import by.traning.task05.bean.Quadrilateral;

/**
 * The interface containing the calculate methods
 */
public interface CalculatorAction {
    /**
     * the resulting shape name
     * @return shape name
     */
    String resultingFigureName();

    /**
     * Figure area calculator
     * @param quadrilateral the figure
     * @return area value
     */
    double areaCalculator(Quadrilateral quadrilateral);

    /**
     * Figure perimeter calculator
     * @param quadrilateral the figure
     * @return perimeter value
     */
    double perimeterCalculator(Quadrilateral quadrilateral);
}
