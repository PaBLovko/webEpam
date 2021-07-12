package by.traning.task05.dao.factory;

import by.traning.task05.dao.QuadrilateralDAO;
import by.traning.task05.dao.impl.FileQuadrilateralDAO;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(staticName = "getInstance")
public final class DAOFactory {
    @Getter
    private final QuadrilateralDAO fileQuadrilateralImpl = new FileQuadrilateralDAO();
}