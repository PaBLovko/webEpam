package by.traning.task02.service;

import by.traning.task02.bean.FileForData;
import lombok.NonNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * The class is the creator for the {@link by.traning.task02.bean.FileForData FileForData} class
 */
public class CreatorFileForData {

    private static Logger logger = LogManager.getLogger(CreatorFileForData.class);

    /**
     * The method that implements the creation of the FileForData class
     * @param path file path
     * @return created class
     * @throws NullPointerException occurs when passing an empty path
     * @throws IOException occurs when a file creation error occurs
     */
    public FileForData create(@NonNull String path) throws IOException{
        logger.debug(String.format("The method is invoked, path = %s",path));
        if (!path.contains("src/main/resources")){
            logger.error("The method is exception, the file was not found");
            throw new IOException("wrong path");
        }
        File file =  new File(path);
        if (!file.exists() && !file.createNewFile()){
            logger.error("The method is exception, the file was not created");
            throw new IOException("file creation error");
        }
        FileForData fileForData = new FileForData(path, file);
        logger.info(String.format("The method worked correctly, fileForData = %s", fileForData));
        return fileForData;
    }
}
