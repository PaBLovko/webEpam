package by.traning.task05.service.observer;

import org.jetbrains.annotations.NotNull;

public interface Observable {
    void addObserver(@NotNull Observer o);
    void removeObserver(@NotNull Observer o);
    void notifyObserver();
}
