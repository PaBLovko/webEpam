package by.traning.task05.service.observer;

import by.traning.task05.bean.Quadrilateral;
import org.jetbrains.annotations.NotNull;

public interface Observer {
    void update(@NotNull Quadrilateral quadrilateral);
}
