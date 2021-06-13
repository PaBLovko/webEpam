package by.traning.task01;

import by.traning.task01.bean.FileForData;
import by.traning.task01.dal.FileForDataLogic;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class FileForDataLogicTest {
    private final FileForDataLogic fileForDataLogic = new FileForDataLogic();
    private static final String PATH_DATA = "src/main/resources/testFile.txt";

    @DataProvider(name = "positiveDataForCreateFile")
    public Object[][] createDataForCreateFile(){

        return new Object[][]{
                {new FileForData(PATH_DATA), false}
        };
    }

    @DataProvider(name = "positiveDataForReadFile")
    public Object[][] createDataForReadFile() {
        return new Object[][]{
                {"1", new FileForData(PATH_DATA)}
        };
    }

    @Test(description = "Positive script of the creating a file",
            dataProvider = "positiveDataForCreateFile")
    public void createFile(FileForData FileForData, boolean expected) {
        boolean actual = fileForDataLogic.createFile(FileForData);
        assertEquals(actual, expected);
    }

    @Test(description = "Positive script for reading the file",
            dataProvider = "positiveDataForReadFile")
    public void readFileToString(String text, FileForData FileForData) {
        String actual = fileForDataLogic.readFileToString(FileForData);
        assertEquals(actual, text);
    }
}
