package by.traning.task04.dao.factory;


import by.traning.task04.dao.CarDAO;
import by.traning.task04.dao.impl.FileCarDAO;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(staticName = "getInstance")
public final class DAOFactory{
    @Getter
    private final CarDAO fileCarImpl = new FileCarDAO();
}