package by.traning.task01.service;

import by.traning.task01.bean.FileForData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The responsible for the logic {@link by.traning.task01.bean.FileForData FileForData}
 */
public class FileForDataLogic {
    private static Logger logger = LogManager.getLogger(FileForDataLogic.class);

    /**
     * The method for creating a file
     * @param fileForData The text file
     * @return The result whether the method is executed or not
     */
    public boolean createFile(FileForData fileForData){
        logger.info(String.format("createFile() is invoked, fileForData = %s",fileForData));
        if(!fileForData.getFile().exists()){
            try {
                return fileForData.getFile().createNewFile();
            }catch (IOException ex){
                logger.error(String.format("createFile() is exception. The fileForData %s did not creat",fileForData));
                return false;
            }
        }else{
            logger.info(String.format("createFile() is invoked. The fileForData %s did creat earlier",fileForData));
            return false;
        }
    }

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
