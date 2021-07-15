package by.traning.task06.service.factory;

import by.traning.task06.service.ArrayService;
import by.traning.task06.service.MatrixService;
import by.traning.task06.service.impl.ArrayServiceImpl;
import by.traning.task06.service.impl.MatrixServiceImpl;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(staticName = "getInstance")
public final class ServiceFactory {
    @Getter
    private final ArrayService arrayService = new ArrayServiceImpl();
    @Getter
    private final MatrixService matrixService = new MatrixServiceImpl();
}

