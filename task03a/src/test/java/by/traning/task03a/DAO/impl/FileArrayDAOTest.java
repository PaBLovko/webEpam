package by.traning.task03a.DAO.impl;

import by.traning.task03a.bean.FileData;
import by.traning.task03a.dao.exception.DAOException;
import by.traning.task03a.dao.impl.FileArrayDAO;
import by.traning.task03a.service.creator.FileCreator;
import by.traning.task03a.service.exception.ServiceException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class FileArrayDAOTest {
    private final FileArrayDAO fileArrayDAO = new FileArrayDAO();
    private static final String PATH_DATA = "src/main/resources/arrayFileTest.txt";

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
