package by.traning.task06.dao.factory;

import by.traning.task06.dao.ArrayDAO;
import by.traning.task06.dao.MatrixDAO;
import by.traning.task06.dao.impl.FileArrayDAO;
import by.traning.task06.dao.impl.FileMatrixDAO;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(staticName = "getInstance")
public final class DAOFactory{
    @Getter
    private final ArrayDAO<Integer> fileArrayImpl = new FileArrayDAO();
    @Getter
    private final MatrixDAO<Integer> fileMatrixImpl = new FileMatrixDAO();
}