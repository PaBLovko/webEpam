package by.traning.task02.bean;

import lombok.Data;
import lombok.NonNull;

import java.io.File;

/**
 * The class that has the necessary data to work with files
 */
@Data
public class FileForData {
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

