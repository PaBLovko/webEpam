package by.traning.task02.service;

import by.traning.task02.bean.FileForData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The responsible for the logic {@link by.traning.task02.bean.FileForData FileForData}
 */
public class FileForDataLogic {
    private static Logger logger = LogManager.getLogger(FileForDataLogic.class);
    /**
     * The method for getting the contents of a file
     * @param fileForData The text file
     * @return The text that is stored in the file
     */
    public String readFileToString(FileForData fileForData) {
        logger.info(String.format("readFileToString() is invoked, fileForData = %s",fileForData));
        String data = null;
        try {
            Scanner scanner = new Scanner(Paths.get(fileForData.getFile().getPath()), StandardCharsets.UTF_8.name());
            if (scanner.hasNext()){
                data = scanner.useDelimiter("\r\n").next();
            }
            scanner.close();
        }catch (NoSuchElementException e){
            logger.error(String.format("readFileToString() is exception.The fileForData %s did not such element",
                    fileForData));
        } catch (IOException e) {
            logger.error(String.format("readFileToString() is exception.The fileForData %s did not read",fileForData));
        }
        return data;
    }
}
