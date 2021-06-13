package by.traning.task03a.bean;

import lombok.Data;
import lombok.NonNull;

/**
 * The class that has the necessary data to work with files
 */
@Data
public class File {
    /**
     * The file path
     */
    @NonNull
    private final String pathFromContentRoot;
    /**
     * The abstract representation of file
     */
    @NonNull
    private final java.io.File file;
}

