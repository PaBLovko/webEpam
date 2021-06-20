package by.traning.task04.view.factory;

import by.traning.task04.view.View;
import by.traning.task04.view.impl.ConsoleView;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(staticName = "getInstance")
public final class ViewFactory {
    @Getter
    private final View consoleView = new ConsoleView();
}

