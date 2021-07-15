package by.traning.task06.DAO.impl;

import by.traning.task06.bean.FileData;
import by.traning.task06.dao.exception.DAOException;
import by.traning.task06.dao.impl.FileMatrixDAO;
import by.traning.task06.service.creator.FileCreator;
import by.traning.task06.service.exception.ServiceException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class FileMatrixDAOTest {
    private final FileMatrixDAO fileArrayDAO = new FileMatrixDAO();
    private static final String PATH_DATA = "src/test/resources/file/matrixFileTest.txt";

    @DataProvider(name = "positiveDataForReadFile")
    public Object[][] createDataForReadFile() throws ServiceException {
        List<List<Integer>> listList = new ArrayList<>();
        listList.add(new ArrayList<>(3));
        listList.add(new ArrayList<>(3));
        listList.add(new ArrayList<>(3));
        listList.get(0).add(1);
        listList.get(0).add(3);
        listList.get(0).add(5);
        listList.get(1).add(3);
        listList.get(1).add(2);
        listList.get(1).add(4);
        listList.get(2).add(2);
        listList.get(2).add(4);
        listList.get(2).add(3);
        return new Object[][]{
                {listList, new FileCreator().create(PATH_DATA)}
        };
    }

    @Test(description = "Positive script for reading the file",
            dataProvider = "positiveDataForReadFile")
    public void readFileTest(List<List<Integer>> executed, FileData fileData) throws DAOException {
        List<List<Integer>> actual = fileArrayDAO.read(fileData);
        assertEquals(actual, executed);
    }
}
