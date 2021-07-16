package by.traning.task06.option4.service.factory;

import by.traning.task06.option4.service.MatrixService;
import by.traning.task06.option4.service.ThreadService;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Factory {
    private static Factory instance;
    @Getter
    private final MatrixService matrixService = new MatrixService();
    @Getter
    private final ThreadService threadService = new ThreadService();

    public static Factory getInstance() {
        if (instance == null) {
            instance = new Factory();
        }
        return instance;
    }
}
