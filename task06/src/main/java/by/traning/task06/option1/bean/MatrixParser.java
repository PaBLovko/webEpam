package by.traning.task06.option1.bean;

import java.util.Arrays;
import java.util.List;

public class MatrixParser {

    public MatrixCell[][] parseMatrix(List<String> data) {
        String matrixSpacesRegexPattern = " ";
        MatrixCell[][] matrix = new MatrixCell[data.size()][data.size()];
        for (int i = 0; i < matrix.length; i++) {
            List<String> numbers = Arrays.asList(data.get(i).split(matrixSpacesRegexPattern));
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = new MatrixCell(Integer.parseInt(numbers.get(j)));
            }
        }
        return matrix;
    }
}
