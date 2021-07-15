package by.traning.task06.bean;

import lombok.Data;
import lombok.NonNull;

import java.io.File;

/**
 * The class that has the necessary data to work with files
 */
@Data
public class FileData {
    /**
     * The file path
     */
    @NonNull
    private final String pathFromContentRoot;
    /**
     * The abstract representation of file
     */
    @NonNull
    private final File file;
}

