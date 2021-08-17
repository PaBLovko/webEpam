package by.traning.task07.service.factory;

import by.traning.task07.service.SortService;
import by.traning.task07.service.SplitService;
import by.traning.task07.service.TextService;
import by.traning.task07.service.impl.SortServiceImpl;
import by.traning.task07.service.impl.SplitServiceImpl;
import by.traning.task07.service.impl.TextServiceImpl;
import by.traning.task07.service.repository.Repository;
import by.traning.task07.service.repository.impl.RepositoryImpl;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(staticName = "getInstance")
public class ServiceFactory {
    @Getter
    private final SplitService splitService = new SplitServiceImpl();
    @Getter
    private final TextService textService = new TextServiceImpl();
    @Getter
    private final SortService sortService = new SortServiceImpl();
    @Getter
    private final Repository repository = new RepositoryImpl();
}