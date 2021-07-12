package by.traning.task05.service.factory;

import by.traning.task05.service.action.CalculatorAction;
import by.traning.task05.service.action.impl.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(staticName = "getInstance")
public final class ServiceFactory {
    @Getter
    private final CalculatorAction parallelogram = new ParallelogramCalculatorAction();
    @Getter
    private final CalculatorAction rectangle = new RectangleCalculatorAction();
    @Getter
    private final CalculatorAction rhombus = new RhombusCalculatorAction();
    @Getter
    private final CalculatorAction square = new SquareCalculatorAction();
    @Getter
    private final CalculatorAction trapezoid = new TrapezoidCalculatorAction();
}

