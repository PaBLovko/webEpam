package by.traning.task06.option1.bean;

import lombok.Data;

@Data
public class MatrixCell {

    private int cellValue;
    private boolean isChanged;

    public MatrixCell(int cellValue) {
        this.cellValue = cellValue;
    }

    @Override
    public String toString() {
        return "[" + cellValue + "]";
    }
}
