package by.traning.task06.option4.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicInteger;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Matrix {
    private AtomicInteger[][] matrix;

    public int getValue(int row, int column) {
        return matrix[row][column].get();
    }
}
