package by.traning.task02.service;

import by.traning.task02.bean.FileForData;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class FileForDataLogicTest {
    private final FileForDataLogic fileForDataLogic = new FileForDataLogic();
    private static final String PATH_DATA = "src/main/resources/testFile.txt";

    @DataProvider(name = "positiveDataForReadFile")
    public Object[][] createDataForReadFile() throws IOException {
        return new Object[][]{
                {"1", new CreatorFileForData().create(PATH_DATA)}
        };
    }

    @Test(description = "Positive script for reading the file",
            dataProvider = "positiveDataForReadFile")
    public void readFileToString(String text, FileForData FileForData) {
        String actual = fileForDataLogic.readFileToString(FileForData);
        assertEquals(actual, text);
    }
}
