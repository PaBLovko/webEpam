package by.traning.task05.view.factory;

import by.traning.task05.view.View;
import by.traning.task05.view.impl.ConsoleView;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(staticName = "getInstance")
public final class ViewFactory {
    @Getter
    private final View consoleView = new ConsoleView();
}

