package by.traning.task05.view.factory;

import by.traning.task05.view.Screen;
import by.traning.task05.view.impl.ConsoleScreen;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(staticName = "getInstance")
public final class ScreenFactory {
    @Getter
    private final Screen consoleScreen = new ConsoleScreen();
}

