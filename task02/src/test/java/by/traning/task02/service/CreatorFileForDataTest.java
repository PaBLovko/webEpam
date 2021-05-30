package by.traning.task02.service;

import by.traning.task02.bean.FileForData;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class CreatorFileForDataTest {
    private final CreatorFileForData creatorFileForData = new CreatorFileForData();
    private static final String PATH_DATA = "src/main/resources/testFile.txt";

    @DataProvider(name = "positiveDataForCreateFile")
    public Object[][] createDataForCreateFile(){

        return new Object[][]{
                {PATH_DATA, new FileForData(PATH_DATA, new File(PATH_DATA))}
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
    public void creatorTest(String path, FileForData expected) throws IOException {
        FileForData actual = creatorFileForData.create(path);
        assertEquals(actual, expected);
    }

    @Test(description = "Negative script of the creating a file",
            dataProvider = "negativeDataForCreateFile")
    public void creatorNegativeTest(String path){
        assertThrows(Exception.class,()-> creatorFileForData.create(path));
    }
}
