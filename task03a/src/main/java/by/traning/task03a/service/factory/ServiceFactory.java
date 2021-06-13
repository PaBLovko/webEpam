package by.traning.task03a.service.factory;

import by.traning.task03a.service.ArrayService;
import by.traning.task03a.service.MatrixService;
import by.traning.task03a.service.impl.ArrayServiceImpl;
import by.traning.task03a.service.impl.MatrixServiceImpl;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(staticName = "getInstance")
public final class ServiceFactory {
    @Getter
    private final ArrayService arrayService = new ArrayServiceImpl();
    @Getter
    private final MatrixService matrixService = new MatrixServiceImpl();
}

