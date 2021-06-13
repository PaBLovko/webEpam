package by.traning.task03a.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * The class that has the necessary data to work with matrix
 */
@AllArgsConstructor
@Data
public class Matrix <K extends Number>{
    /**
     * Matrix
     */
    private K[][] values;

    /**
     * The method returns the vertical value of the matrix
     * @return the vertical value of the matrix
     */
    public int getVerticalSize() {
        return values.length;
    }

    /**
     * The method returns the horizontal value of the matrix
     * @return the horizontal value of the matrix
     */
    public int getHorizontalSize() {
        return values[0].length;
    }

    /**
     * The method sets the new value of the matrix element
     * @param column number column
     * @param row number row
     * @param value new value
     */
    public void setElement(final int column, final  int row, final K value){
        values[column][row] = value;
    }

    /**
     * The method returns the element by the specified values
     * @param column number column
     * @param row number row
     * @return stored value
     */
    public K getElement(final int column, final int row){
        return values[column][row];
    }
}