package by.traning.task06.option3.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.concurrent.Semaphore;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Matrix {
    private int[][] matrix;
    private List<Item> diagonal;
    private Semaphore sem;
}
