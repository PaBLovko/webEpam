package by.traning.task07.dao.factory;

import by.traning.task07.dao.TextDAO;
import by.traning.task07.dao.impl.FileTextDAO;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(staticName = "getInstance")
public final class DAOFactory {
    @Getter
    private final TextDAO fileTextDAO = new FileTextDAO();
}