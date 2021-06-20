package by.traning.task03a.view.factory;

import by.traning.task03a.view.View;
import by.traning.task03a.view.impl.ConsoleView;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(staticName = "getInstance")
public final class ViewFactory {
    @Getter
    private final View consoleView = new ConsoleView();
}

