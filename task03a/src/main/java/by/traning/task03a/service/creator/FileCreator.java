package by.traning.task03a.service.creator;

import by.traning.task03a.bean.File;
import by.traning.task03a.service.exception.ServiceException;
import lombok.NonNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * The class is the creator for the {@link File FileForData} class
 */
public class FileCreator {
    private static Logger logger = LogManager.getLogger(FileCreator.class);

    /**
     * The method that implements the creation of the FileForData class
     * @param path file path
     * @return created class
     * @throws NullPointerException occurs when passing an empty path
     */
    public File create(@NonNull final String path) throws ServiceException {
        logger.debug(String.format("The method is invoked, path = %s",path));
        if (!path.contains("src/main/resources")){
            logger.error("The method is exception, the file was not found");
            throw new ServiceException("wrong path");
        }
        java.io.File file =  new java.io.File(path);
        boolean state = false;
        try {
            if (!file.isFile()){
                state = file.createNewFile();
            }
        }catch (IOException e){
            logger.error("The method is exception, the file was not created");
            throw new ServiceException("file is creation error", e);
        }
        File fileForData = new File(path, file);
        logger.info(String.format("The method worked correctly, fileForData = %s, created now = %s",
                fileForData, state));
        return fileForData;
    }
}
