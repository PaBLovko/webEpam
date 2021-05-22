package by.traning.task01.bean;

import java.io.File;
import java.util.Objects;

/**
 * The class that has the necessary data to work with files
 */
public class FileForData {

    /**
     * The file path
     */
    private final String pathFromContentRoot;

    /**
     * The abstract representation of file
     */
    private final File file;

    /**
     * Allocates a new {@code FileForData}
     * @param pathFromContentRoot The file path
     */
    public FileForData(String pathFromContentRoot) {
        this.pathFromContentRoot = pathFromContentRoot;
        this.file = new File(pathFromContentRoot);
    }

    /**
     * The method for getting the abstract representation of file
     * @return The abstract representation of file
     */
    public File getFile() {
        return file;
    }

    /**
     * The method for getting the file path
     * @return The file path
     */
    public String getPathFromContentRoot() {
        return pathFromContentRoot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileForData that = (FileForData) o;
        return Objects.equals(pathFromContentRoot, that.pathFromContentRoot) &&
                Objects.equals(file, that.file);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pathFromContentRoot, file);
    }

    @Override
    public String toString() {
        return "FileForData{" +
                "pathFromContentRoot='" + pathFromContentRoot + '\'' +
                ", file=" + file +
                '}';
    }
}

