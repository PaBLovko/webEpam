package by.traning.task03a.service.creator;

import by.traning.task03a.bean.FileData;
import by.traning.task03a.service.exception.ServiceException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class FileCreatorTest {
    private final FileCreator creatorFileForData = new FileCreator();
    private static final String PATH_DATA = "src/main/resources/arrayFileTest.txt";

    @DataProvider(name = "positiveDataForCreateFile")
    public Object[][] createDataForCreateFile(){

        return new Object[][]{
                {PATH_DATA, new FileData(PATH_DATA, new java.io.File(PATH_DATA))}
        };
    }

    @DataProvider(name = "negativeDataForCreateFile")
    public Object[][] createNegativeDataForCreateFile(){

        return new Object[][]{
                {null},
                {""}
        };
    }

    @Test(description = "Positive script of the creating a file",
            dataProvider = "positiveDataForCreateFile")
    public void creatorTest(String path, FileData expected) throws ServiceException {
        FileData actual = creatorFileForData.create(path);
        assertEquals(actual, expected);
    }

    @Test(description = "Negative script of the creating a file",
            dataProvider = "negativeDataForCreateFile")
    public void creatorNegativeTest(String path){
        assertThrows(Exception.class,()-> creatorFileForData.create(path));
    }
}
