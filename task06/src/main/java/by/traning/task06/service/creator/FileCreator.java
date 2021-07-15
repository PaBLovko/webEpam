package by.traning.task06.service.creator;

import by.traning.task06.bean.FileData;
import by.traning.task06.service.exception.ServiceException;
import lombok.NonNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * The class is the creator for the {@link FileData FileForData} class
 */
public class FileCreator {
    private static Logger logger = LogManager.getLogger(FileCreator.class);

    /**
     * The method that implements the creation of the FileForData class
     * @param path file path
     * @return created class
     * @throws ServiceException occurs when passing an empty path
     */
    public FileData create(@NonNull final String path) throws ServiceException {
        logger.debug(String.format("The method is invoked, path = %s",path));
        if (!path.contains("file")){
            logger.error("The method is exception, the file was not found");
            throw new ServiceException("wrong path");
        }
        File file =  new File(path);
        boolean state = false;
        try {
            if (!file.isFile()){
                state = file.createNewFile();
            }
        }catch (IOException e){
            logger.error("The method is exception, the file was not created");
            throw new ServiceException("file is creation error", e);
        }
        FileData fileData = new FileData(path, file);
        logger.info(String.format("The method worked correctly, fileForData = %s, created now = %s",
                fileData, state));
        return fileData;
    }
}
