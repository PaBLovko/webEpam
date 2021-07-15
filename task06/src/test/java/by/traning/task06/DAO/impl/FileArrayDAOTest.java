package by.traning.task06.DAO.impl;

import by.traning.task06.bean.FileData;
import by.traning.task06.dao.exception.DAOException;
import by.traning.task06.dao.impl.FileArrayDAO;
import by.traning.task06.service.creator.FileCreator;
import by.traning.task06.service.exception.ServiceException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class FileArrayDAOTest {
    private final FileArrayDAO fileArrayDAO = new FileArrayDAO();
    private static final String PATH_DATA = "src/test/resources/file/arrayFileTest.txt";

    @DataProvider(name = "positiveDataForReadFile")
    public Object[][] createDataForReadFile() throws ServiceException {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(2);
        return new Object[][]{
                {list, new FileCreator().create(PATH_DATA)}
        };
    }

    @Test(description = "Positive script for reading the file",
            dataProvider = "positiveDataForReadFile")
    public void readFileTest(List<Integer> executed, FileData fileData) throws DAOException {
        List<Integer> actual = fileArrayDAO.read(fileData);
        assertEquals(actual, executed);
    }
}
